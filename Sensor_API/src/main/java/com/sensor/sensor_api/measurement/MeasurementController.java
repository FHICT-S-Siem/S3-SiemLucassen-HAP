package com.sensor.sensor_api.measurement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;

    @Autowired
    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @GetMapping("/{roomName}")
    public Optional<Measurement> getMeasurementsByRoomName(@PathVariable("roomName") String roomName) { return measurementService.getMeasurementsByRoomName(roomName);}

    @GetMapping
    public List<Measurement> getMeasurements(){return measurementService.getMeasurements();}

}
