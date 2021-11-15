package com.sensor.Sensor_API.Integration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sensor.Sensor_API.exceptions.ApiRequestException;
import com.sensor.Sensor_API.room.Room;
import com.sensor.Sensor_API.room.RoomController;
import com.sensor.Sensor_API.room.RoomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RoomController.class)
public class RoomIntegrationTest {
    @MockBean
    RoomService roomService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getRoomByName() throws Exception {
        String roomName = "Siem";
        Room room = new Room(1, roomName);

        when(roomService.getRoomByName(roomName)).thenReturn(java.util.Optional.of(room));

        mockMvc.perform(get("/api/v1/rooms/{roomName}", roomName))
                .andDo(print()).andExpect(status().isOk()).andExpect(content().json(convertObjectToJsonString(room)));
    }


    @Test
    public void shouldReturnExceptionMessage() throws Exception {
        String roomName = "Siem";
        when(roomService.getRoomByName(roomName)).thenThrow(new ApiRequestException("There are no rooms with this name found"));

        mockMvc.perform(get("/api/v1/rooms/{roomName}", roomName))
                .andDo(print()).andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ApiRequestException))
                .andExpect(result -> assertEquals("There are no rooms with this name found", result.getResolvedException().getMessage()));
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
