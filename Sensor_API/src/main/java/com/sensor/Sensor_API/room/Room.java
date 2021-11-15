package com.sensor.Sensor_API.room;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table
public class Room {

    @Id
    @SequenceGenerator(
            name = "name_sequence",
            sequenceName = "name_sequence",
            allocationSize = 1
    )
    @GeneratedValue (
            strategy = GenerationType.SEQUENCE,
            generator = "name_sequence"
    )
    private Integer id;
    private String name;
    private Integer brightness;
    private Integer temperature;
    private Date datetime;

    public Room() {
    }

    public Room(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Room(Integer id, String name, Integer brightness, Integer temperature) {
        this.id = id;
        this.name = name;
        this.brightness = brightness;
        this.temperature = temperature;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setDatetime(Date date) {
        this.datetime = date;
    }
}
