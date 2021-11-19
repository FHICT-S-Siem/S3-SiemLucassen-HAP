package com.sensor.sensor_api.room;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{roomName}")
    public Optional<Room> getRoomByName(@PathVariable("roomName") String roomName) { return roomService.getRoomByName(roomName);}

    @GetMapping
    public List<Room> getRooms()
    {
        return roomService.getRooms();
    }

    @PostMapping
    public void createRoom(@RequestBody Room room){
        roomService.createRoom(room);
    }

    @PutMapping(path = "/{roomId}")
    public ResponseEntity<Room> updateRoom(@RequestBody Room room, @PathVariable("roomId") Integer roomId) {
        roomService.updateRoom(roomId, room);
        return new ResponseEntity<>(room, HttpStatus.OK);
//        boolean success = roomService.updateRoom(roomId, room);
//        if (success)
//            return new ResponseEntity<>(room, HttpStatus.OK);
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/{roomId}")
    public void deleteRoom(@PathVariable("roomId") Integer roomId) {
        roomService.deleteRoom(roomId);
    }
}
