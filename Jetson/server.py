from abc import abstractmethod
from flask import Flask  # Flask web app - http://home:5000
import pika
from threading import Thread, Event
from datetime import datetime
import json
import os
from random import Random

class HAPDataProvider(object):
    @abstractmethod
    def get_temperature(self):
        pass

    @abstractmethod
    def get_brightness(self):
        pass

# used for testing locally
class RandomDataProvider(HAPDataProvider):
    def __init__(self):
        self.rng = Random(42)

    def get_temperature(self):
        return int(self.rng.uniform(15, 30))

    def get_brightness(self):
        return int(self.rng.uniform(0, 50))

# used for the jetson nano with smbus for the SensorHub board
class SensorDataProvider(HAPDataProvider):
    def __init__(self):
        import smbus
        self.DEVICE_BUS = 1
        self.DEVICE_ADDR = 0x17
        self.TEMP_REG = 1
        self.LIGHT_REG_L = 2
        self.LIGHT_REG_H = 3
        self.STATUS_REG = 4
        self.ON_BOARD_TEMP_REG = 5
        self.ON_BOARD_HUMIDITY_REG = 6
        self.ON_BOARD_SENSOR_ERROR = 7
        self.BMP280_TEMP_REG = 8
        self.BMP280_PRESSURE_REG_L = 9
        self.BMP280_PRESSURE_REG_M = 10
        self.BMP280_PRESSURE_REG_H = 11
        self.BMP280_STATUS = 12
        self.HUMAN_DETECT = 13
        self.bus = smbus.SMBus(self.DEVICE_BUS)

    def get_temperature(self):
        temperature = self.bus.read_byte_data(self.DEVICE_ADDR, self.TEMP_REG)
        return str(temperature)

    def get_brightness(self):
        light_reg_l = self.bus.read_byte_data(self.DEVICE_ADDR, self.LIGHT_REG_L)
        light_reg_h = self.bus.read_byte_data(self.DEVICE_ADDR, self.LIGHT_REG_H)
        return str(light_reg_h << 8 | light_reg_l)

# data plubisher to RabbitMQ
class SensorDataPublisher(Thread):
  def __init__(self, stopped, sensor_data_provider, room_name):
      Thread.__init__(self)
      self.sensor_data_provider = sensor_data_provider
      self.room_name = room_name
      self.stopped = stopped
      self.connection = pika.BlockingConnection(
          pika.ConnectionParameters(host='siemvm2'))
      self.channel = self.connection.channel()
      self.channel.queue_declare(queue='sensor_data', durable=True)
  
  # Publish sensor data every minute 
  def run(self):
      while not self.stopped.wait(60):
          self.publish_sensor_data()

  def publish_sensor_data(self):
      brightness = self.sensor_data_provider.get_brightness()
      temperature = self.sensor_data_provider.get_temperature()
      timestamp = str(datetime.now())
      data = {
          'temperature': temperature,
          'brightness': brightness,
          'datetime': timestamp,
          'room': self.room_name
      }
      json_data = json.dumps(data)
      self.channel.basic_publish(
        exchange='', 
        routing_key='sensor_data',
        body=json_data, 
        properties=pika.BasicProperties(delivery_mode=2))

app = Flask(__name__)
room_name = os.environ.get('ROOM')
sensor_data_provider = SensorDataProvider()

@app.route("/")
def index():
    return f"Welcome to the jetson nano sensor hub for {room_name} room!<br>Try:<br>/temperature<br>/brightness"

@app.route("/temperature")
def get_temperature():
    return sensor_data_provider.get_temperature()

@app.route("/brightness")
def get_brightness():
    return sensor_data_provider.get_brightness()

if __name__ == '__main__':
    print(f"Configured for gathering data in room: {room_name}")
    stopped = Event()
    thread = SensorDataPublisher(stopped, sensor_data_provider, room_name)
    thread.start()
    app.run(host="0.0.0.0", port="5000", debug=False, use_reloader=False)

