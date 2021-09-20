from flask import Flask
import smbus

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

if __name__ == '__main__':
  app.run()