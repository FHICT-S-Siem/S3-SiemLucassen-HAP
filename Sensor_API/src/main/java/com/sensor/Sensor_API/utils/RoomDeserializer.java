package com.sensor.Sensor_API.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.sensor.Sensor_API.exceptions.ApiRequestException;
import com.sensor.Sensor_API.room.Room;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RoomDeserializer extends StdDeserializer<Room> {
    public final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //removed static because of sonarqube bug reliability reasons

    public RoomDeserializer() {
        this(null);
    }

    public RoomDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Room deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException {
        Room room = new Room();
        ObjectCodec codec = parser.getCodec();
        JsonNode node = codec.readTree(parser);
        String date = node.get("datetime").asText();
        try {
            room.setName(node.get("room").asText());
            room.setBrightness(node.get("brightness").asInt());
            room.setTemperature(node.get("temperature").asInt());
            room.setDatetime(FORMATTER.parse(date));
        } catch (ParseException e) {
            if (room.getDatetime() == null)
                room.setDatetime(new Date(Long.parseLong(date)));
            else
                throw new ApiRequestException("Room datetime is null.", e);
        }
        return room;
    }
}
