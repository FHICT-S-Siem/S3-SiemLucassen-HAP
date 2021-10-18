package com.sensor.Sensor_API.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.sensor.Sensor_API.room.Room;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RoomDeserializer extends StdDeserializer<Room> {
    public static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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

        try {
            room.setRoom(node.get("room").asText());
            room.setBrightness(node.get("brightness").asInt());
            room.setTemperature(node.get("temperature").asInt());
            room.setDatetime(FORMATTER.parse(node.get("datetime").asText()));
            room.setRoom(node.get("room").asText());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return room;
    }
}
