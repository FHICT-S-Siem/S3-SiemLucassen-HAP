package com.sensor.sensor_api.room;

import com.sensor.sensor_api.measurement.Measurement;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Room implements Serializable {

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "room", referencedColumnName = "name")
    private Set<Measurement> measurements;

    public Room() {
    }

    public Room(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Room(Integer id, String name, Set<Measurement> measurements) {
        this.id = id;
        this.name = name;
        this.measurements = measurements;
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

    public Set<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(Set<Measurement> measurements) {
        this.measurements = measurements;
    }

    public void addMeasurement(Measurement measurement) {
        measurement.setRoom(this.name);
        this.measurements.add(measurement);
    }
}
