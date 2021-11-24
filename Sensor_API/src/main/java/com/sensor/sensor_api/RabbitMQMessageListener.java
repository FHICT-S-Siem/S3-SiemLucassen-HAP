package com.sensor.sensor_api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.sensor.sensor_api.measurement.Measurement;
import com.sensor.sensor_api.measurement.MeasurementRepository;
import com.sensor.sensor_api.utils.MeasurementDeserializer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class RabbitMQMessageListener implements MessageListener {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final MeasurementRepository _measurementRepository;

    public RabbitMQMessageListener(MeasurementRepository _measurementRepository) {
        this._measurementRepository = _measurementRepository;
        SimpleModule module = new SimpleModule("MeasurementDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(Measurement.class, new MeasurementDeserializer());
        objectMapper.registerModule(module);
    }

    @Override
    public void onMessage(Message message) {
        String messageString = new String(message.getBody());
        try {
            Measurement measurement = objectMapper.readValue(messageString, Measurement.class);
            System.out.println("message = " + messageString);
            _measurementRepository.save(measurement);
        } catch (JsonProcessingException e) {
            System.out.println(e.toString());
        }
    }
}

