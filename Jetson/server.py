from flask import Flask  # Flask web app - http://home:5000
import smbus
import pika
from threading import Thread, Event
from datetime import datetime
import json

DEVICE_BUS = 1
DEVICE_ADDR = 0x17

TEMP_REG = 1
LIGHT_REG_L = 2
LIGHT_REG_H = 3
STATUS_REG = 4
ON_BOARD_TEMP_REG = 5
ON_BOARD_HUMIDITY_REG = 6
ON_BOARD_SENSOR_ERROR = 7
BMP280_TEMP_REG = 8
BMP280_PRESSURE_REG_L = 9
BMP280_PRESSURE_REG_M = 10
BMP280_PRESSURE_REG_H = 11
BMP280_STATUS = 12
HUMAN_DETECT = 13

bus = smbus.SMBus(DEVICE_BUS)

app = Flask(__name__)


@app.route("/")
def index():
    return "Welcome to the jetson nano sensor hub!<br>Try:<br>/temperature<br>/brightness"


@app.route("/temperature")
def get_temperature():
    temperature = bus.read_byte_data(DEVICE_ADDR, TEMP_REG)
    return str(temperature)


@app.route("/brightness")
def get_brightness():
    light_reg_l = bus.read_byte_data(DEVICE_ADDR, LIGHT_REG_L)
    light_reg_h = bus.read_byte_data(DEVICE_ADDR, LIGHT_REG_H)
    return str(light_reg_h << 8 | light_reg_l)


class SensorDataPublisher(Thread):
  def __init__(self):
      Thread.__init__(self)
      self.connection = pika.BlockingConnection(
          pika.ConnectionParameters(host='siemvm2'))
      self.channel = self.connection.channel()
      self.channel.queue_declare(queue='sensor_data', durable=True)
  
  # Publish sensor data every 5 seconds 
  def run(self):
      while not self.stopped.wait(5):
          self.publish_sensor_data()

  def publish_sensor_data(self):
      brightness = get_brightness()
      temperature = get_temperature()
      timestamp = str(datetime.now())
      data = {
          'temperature': temperature,
          'brightness': brightness,
          'datetime': timestamp,
          'room': 'siem'
      }
      json_data = json.dumps(data, indent=4)
      self.channel.basic_publish(
        exchange='', 
        routing_key='sensor_data',
        body=json_data, 
        properties=pika.BasicProperties(delivery_mode=2))


if __name__ == '__main__':
    thread = SensorDataPublisher()
    thread.start()
    app.run(host="0.0.0.0", port="5000", debug=False, use_reloader=False)

