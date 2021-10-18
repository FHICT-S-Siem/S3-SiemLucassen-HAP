package com.sensor.Sensor_API.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public void deleteRoom(Integer roomId) {
        boolean exists = roomRepository.existsById(roomId);
        if (!exists) {
            throw new IllegalStateException("Room with id " + roomId + " does not exists");
        }
        roomRepository.deleteById(roomId);
        System.out.println("room " + roomId + " deleted!");
    }

    public void createRoom(Room room) {
        Optional<Room> roomByName = roomRepository.findRoomByRoom(room.getRoom());
        if (roomByName.isPresent()) {
            throw new EntityExistsException("Name already taken!");
        }
        roomRepository.save(room);
    }

    public boolean updateRoom(Integer id, Room room) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()) {
            Room actualRoom = optionalRoom.get();
            actualRoom.setRoom(room.getRoom());
            roomRepository.save(actualRoom);
            return true;
        }
        return false;
    }

    public List<Room> getRooms(){
        return roomRepository.findAll();
    }

    public Optional<Room> getRoomByName(String name) {return roomRepository.findRoomByRoom(name);}

}
