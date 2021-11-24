package com.sensor.sensor_api.measurement;

import com.sensor.sensor_api.exceptions.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {
    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public List<Measurement> getMeasurements() { return measurementRepository.findAll(); }

    public Optional<Measurement> getMeasurementsByRoomName(String roomName) {
        if (roomName.isEmpty() )
        {
            throw new ApiRequestException("There are no measurements with this roomId found");
        }
        else {
            return measurementRepository.findMeasurementsByRoomName(roomName);
        }
    }
}
