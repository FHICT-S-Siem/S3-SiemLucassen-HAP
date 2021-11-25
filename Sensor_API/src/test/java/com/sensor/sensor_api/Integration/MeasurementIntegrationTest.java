package com.sensor.sensor_api.Integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sensor.sensor_api.measurement.Measurement;
import com.sensor.sensor_api.room.Room;
import com.sensor.sensor_api.room.RoomController;
import com.sensor.sensor_api.room.RoomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoomController.class)
public class MeasurementIntegrationTest {
    @MockBean
    RoomService roomService;

    @Autowired
    private MockMvc mockMvc;
    private ArrayList<Measurement> measurements = new ArrayList<>();

    @Test
    void shouldGetMeasurementsByRoomName() throws Exception {
        String roomName = "siem";
        // measurements.add(new Measurement(1, "siem", 10, 25, Date.valueOf(LocalDate.now())));
        // add set<measurements> to room.
        Room room = new Room(
        1,
        "siem"

        );

        when(roomService.getMeasurementsByRoom(roomName)).thenReturn(java.util.Optional.of(room));

        mockMvc.perform(get("/api/v1/rooms/{roomName}", roomName))
                .andDo(print()).andExpect(status().isOk()).andExpect(content().json(convertObjectToJsonString(room)));
    }

    private String convertObjectToJsonString(Room room) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(room);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

}
