package com.sensor.Sensor_API.room;

import javax.persistence.*;

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
    private String name;

    public Room() {
    }

    public Room(String name) {
        this.name = name;
    }

    public Room(Integer id, String name) {
        this.id = id;
        this.name = name;
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
}
