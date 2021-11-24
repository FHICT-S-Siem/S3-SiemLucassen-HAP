package com.sensor.sensor_api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.sensor.sensor_api.measurement.Measurement;
import com.sensor.sensor_api.measurement.MeasurementRepository;
import com.sensor.sensor_api.utils.MeasurementDeserializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.amqp.core.Message;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RabbitMQMessageListenerTest {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Mock public MeasurementRepository _measurementRepository;
    public RabbitMQMessageListener rabbitMQMessageListener;

    @BeforeEach
    public void setUp() {
        SimpleModule module = new SimpleModule("MeasurementDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(Measurement.class, new MeasurementDeserializer());
        objectMapper.registerModule(module);
        _measurementRepository = mock(MeasurementRepository.class);
        rabbitMQMessageListener = new RabbitMQMessageListener(_measurementRepository);
    }

    @Test
    public void shouldReceiveMessage() throws JsonProcessingException {
        // Arrange
        Measurement measurement = new Measurement();
        measurement.setDatetime(new Date());
        measurement.setRoomName("Siem");
        measurement.setBrightness(5);
        measurement.setTemperature(25);

        byte[] messageBody = objectMapper.writeValueAsBytes(measurement);
        Message msg = new Message(messageBody);

        // Act
        rabbitMQMessageListener.onMessage(msg);

        // Assert
        verify(_measurementRepository, times(1)).save(any());
    }
}