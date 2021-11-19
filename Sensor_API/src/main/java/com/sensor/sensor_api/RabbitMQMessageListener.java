package com.sensor.sensor_api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.sensor.sensor_api.room.Room;
import com.sensor.sensor_api.room.RoomRepository;
import com.sensor.sensor_api.utils.RoomDeserializer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class RabbitMQMessageListener implements MessageListener {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RoomRepository _roomRepository;

    public RabbitMQMessageListener(RoomRepository _roomRepository) {
        this._roomRepository = _roomRepository;
        SimpleModule module = new SimpleModule("RoomDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(Room.class, new RoomDeserializer());
        objectMapper.registerModule(module);
    }

    @Override
    public void onMessage(Message message) {
        String messageString = new String(message.getBody());
        try {
            Room room = objectMapper.readValue(messageString, Room.class);
            System.out.println("message = " + messageString);
            _roomRepository.save(room);
        } catch (JsonProcessingException e) {
            System.out.println(e.toString());
        }
    }
}

