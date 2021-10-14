package com.sensor.Sensor_API.models;

public class Jetson {
    private Integer id;
    private String temperature;
    private String brightness;

    public Jetson() {
    }

    public Jetson(Integer id, String temperature, String brightness) {
        this.id = id;
        this.temperature = temperature;
        this.brightness = brightness;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getBrightness() {
        return brightness;
    }

    public void setBrightness(String brightness) {
        this.brightness = brightness;
    }
}
