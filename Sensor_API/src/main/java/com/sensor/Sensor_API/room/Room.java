package com.sensor.Sensor_API.room;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table
public class Room {

    @Id
    @SequenceGenerator(
            name = "room_sequence",
            sequenceName = "room_sequence",
            allocationSize = 1
    )
    @GeneratedValue (
            strategy = GenerationType.SEQUENCE,
            generator = "room_sequence"
    )
    private Integer id;
    private String room;
    private Integer brightness;
    private Integer temperature;
    private Date datetime;

    public Room() {
    }

    public Room(String name) {
        this.room = name;
    }

    public Room(Integer id, String name) {
        this.id = id;
        this.room = name;
    }

    public Room(Integer id, String name, Date date) {
        this.id = id;
        this.room = name;
        this.datetime = date;
    }

    public Room(Integer id, String room, Integer brightness, Integer temperature, Date datetime) {
        this.id = id;
        this.room = room;
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

    public String getRoom() {
        return room;
    }

    public void setRoom(String name) {
        this.room = name;
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
