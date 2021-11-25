package com.sensor.sensor_api.measurement;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table
public class Measurement implements Serializable {

    @Id
    @SequenceGenerator(
            name = "name_sequence",
            sequenceName = "name_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "name_sequence"
    )
    private Integer id;
    private String roomName;
    private Integer brightness;
    private Integer temperature;
    private Date datetime;

    public Measurement() {

    }

    public Measurement(Integer id, String roomName, Integer brightness, Integer temperature, Date datetime) {
        this.id = id;
        this.roomName = roomName;
        this.brightness = brightness;
        this.temperature = temperature;
        this.datetime = datetime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getBrightness() {
        return brightness;
    }

    public void setBrightness(Integer brightness) {
        this.brightness = brightness;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

}
