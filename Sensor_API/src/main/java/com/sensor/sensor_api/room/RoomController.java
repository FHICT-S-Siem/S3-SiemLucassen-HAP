package com.sensor.sensor_api.room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/rooms")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/{room}")
    public Optional<Room> getMeasurementsByRoom(@PathVariable("room") String room) {
        return roomService.getMeasurementsByRoom(room);
    }

    @GetMapping
    public Page<Room> getRooms(Optional<Integer> page, Optional<String> sortBy) {
        return roomService.getRooms(page, sortBy);
    }

    @PostMapping
    public void createRoom(@RequestBody Room room){ roomService.createRoom(room);}

    @PutMapping(path = "/{roomId}")
    public ResponseEntity<Room> updateRoom(@RequestBody Room room, @PathVariable("roomId") Integer roomId) {
        roomService.updateRoom(roomId, room);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{roomId}")
    public void deleteRoom(@PathVariable("roomId") Integer roomId) {
        roomService.deleteRoom(roomId);
    }
}
