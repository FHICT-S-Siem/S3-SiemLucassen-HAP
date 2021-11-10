package com.sensor.Sensor_API.room;

import com.sensor.Sensor_API.exceptions.ApiRequestException;
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
            throw new ApiRequestException("Room with id " + roomId + " does not exist");
        }
        roomRepository.deleteById(roomId);
        System.out.println("Room " + roomId + " deleted!");
    }

    public void createRoom(Room room) {
        Optional<Room> roomByName = roomRepository.findRoomByRoom(room.getRoom());
        if (roomByName.isPresent()) {
            throw new ApiRequestException("Name already taken!");
        }
        roomRepository.save(room);
    }

    public boolean updateRoom(Integer id, Room room) {
        Optional<Room> oldRoomById = roomRepository.findById(id);
        if (oldRoomById.isPresent()) {
            Room newRoom = oldRoomById.get();
            newRoom.setRoom(room.getRoom());
            newRoom.setDatetime(room.getDatetime());
            roomRepository.save(newRoom);
            return true;
        }
        else
        {
            throw new ApiRequestException("Room does not exist with given id");
        }
    }

    public List<Room> getRooms(){
        return roomRepository.findAll();
    }

    public Optional<Room> getRoomByName(String name) {return roomRepository.findRoomByRoom(name);}

}
