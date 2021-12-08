package com.sensor.sensor_api.Integration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sensor.sensor_api.exceptions.ApiRequestException;
import com.sensor.sensor_api.room.Room;
import com.sensor.sensor_api.room.RoomController;
import com.sensor.sensor_api.room.RoomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RoomController.class)
public class RoomIntegrationTest {
    @MockBean
    RoomService roomService;

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    void shouldReturnRooms() throws Exception {
//        Room room1 = new Room(1, "Mario");
//        Room room2 = new Room(2, "Siem");
//        List<Room> roomList = new ArrayList<>();
//        roomList.add(room1);
//        roomList.add(room2);
//
//        when(roomService.getRooms()).thenReturn(roomList);
//
//        mockMvc.perform(get("/api/v1/rooms"))
//                .andDo(print()).andExpect(status().isOk()).andExpect(content().json(convertObjectToJsonString(roomList)));
//    }

    @Test
    void shouldGetMeasurementsByRoomName() throws Exception {
        String roomName = "Siem";
        Room room = new Room(1, roomName);

        when(roomService.getMeasurementsByRoom(roomName)).thenReturn(java.util.Optional.of(room));

        mockMvc.perform(get("/api/v1/rooms/{roomName}", roomName))
                .andDo(print()).andExpect(status().isOk()).andExpect(content().json(convertObjectToJsonString(room)));
    }

    @Test
    void shouldCreateRoom() throws Exception {
        Room room = new Room(1, "Siem");

        mockMvc.perform(post("/api/v1/rooms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonString(room)))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void shouldUpdateRoom() throws Exception {
        int roomId = 1;
        Room room = new Room(roomId, "siem");

        mockMvc.perform(put("/api/v1/rooms/{roomId}", roomId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonString(room)))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void shouldDeleteRoom() throws Exception {
        mockMvc.perform(delete("/api/v1/rooms/{roomId}", 1))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldReturnExceptionMessage() throws Exception {
        String roomName = "siem";
        when(roomService.getMeasurementsByRoom(roomName)).thenThrow(new ApiRequestException("There are no measurements with this room name found"));

        mockMvc.perform(get("/api/v1/rooms/{roomName}", roomName))
                .andDo(print()).andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ApiRequestException))
                .andExpect(result -> assertEquals("There are no measurements with this room name found", result.getResolvedException().getMessage()));
    }

    private String convertObjectToJsonString(List<Room> roomList) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(roomList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
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
