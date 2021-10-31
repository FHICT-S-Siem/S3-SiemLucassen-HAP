package com.sensor.Sensor_API;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.sensor.Sensor_API.room.Room;
import com.sensor.Sensor_API.room.RoomRepository;
import com.sensor.Sensor_API.utils.RoomDeserializer;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.amqp.core.Message;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RabbitMQMessageListenerTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock public RoomRepository _roomRepository;

    public RabbitMQMessageListener rabbitMQMessageListener;

    @Before
    public void setUp() {
        SimpleModule module = new SimpleModule("RoomDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(Room.class, new RoomDeserializer());
        objectMapper.registerModule(module);
        _roomRepository = mock(RoomRepository.class);
        rabbitMQMessageListener = new RabbitMQMessageListener(_roomRepository);
    }

    @Test
    public void shouldReceiveMessage() throws JsonProcessingException {
        // Arrange
        Room room = new Room();
        room.setDatetime(new Date());
        room.setRoom("Mario");
        room.setBrightness(5);
        room.setTemperature(25);

        byte[] messageBody = objectMapper.writeValueAsBytes(room);
        Message msg = new Message(messageBody);

        // Act
        rabbitMQMessageListener.onMessage(msg);

        // Assert
        verify(_roomRepository, times(1)).save(any());
    }
}