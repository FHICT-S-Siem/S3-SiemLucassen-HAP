package com.sensor.sensor_api.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.sensor.sensor_api.exceptions.ApiRequestException;
import com.sensor.sensor_api.measurement.Measurement;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MeasurementDeserializer extends StdDeserializer<Measurement> {
    public final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("nl-NL")); //removed static because of sonarqube bug reliability reasons

    public MeasurementDeserializer() {
        this(null);
    }

    public MeasurementDeserializer(Class<?> vc) {
        super(vc);
    }
    @Override
    public Measurement deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException {
        Measurement measurement = new Measurement();
        ObjectCodec codec = parser.getCodec();
        JsonNode node = codec.readTree(parser);
        String date = node.get("datetime").asText();
        try {
            measurement.setRoom(node.get("room").asText());
            measurement.setBrightness(node.get("brightness").asInt());
            measurement.setTemperature(node.get("temperature").asInt());
            measurement.setDatetime(FORMATTER.parse(date));
        }
        
        catch (ParseException e) {
            if (measurement.getDatetime() == null)
                measurement.setDatetime(new Date(Long.parseLong(date)));
            else
                throw new ApiRequestException("Measurement datetime is null.", e);
        }
        return measurement;
    }
}
