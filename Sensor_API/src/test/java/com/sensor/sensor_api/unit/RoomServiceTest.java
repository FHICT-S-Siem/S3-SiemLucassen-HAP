package com.sensor.sensor_api.unit;
import com.sensor.sensor_api.exceptions.ApiRequestException;
import com.sensor.sensor_api.room.Room;
import com.sensor.sensor_api.room.RoomRepository;
import com.sensor.sensor_api.room.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {
    @Mock
    private RoomRepository roomRepository;
    private RoomService underTest;

    @BeforeEach
    void setUp() {
        underTest = new RoomService(roomRepository);
    }

    @Test
    void Should_Get_All_Rooms(){
        // when
        underTest.getRooms();
        // then
        verify(roomRepository).findAll();
    }

    @Test
    void Should_Get_Room_By_Name() {
        // given
        Room room = new Room(
                1,
                "Siem"
        );

        given(roomRepository.findRoomByName(room.getName())).willReturn(java.util.Optional.of(room));
        // when
        underTest.getRoomByName(room.getName());
        // then
        verify(roomRepository).findRoomByName(room.getName());
    }

    @Test
    void Should_Create_Room() {
        // given
        Room expected = new Room(
                1,
                "Siem"
        );
        // when
        underTest.createRoom(expected);

        ArgumentCaptor<Room> dishArgumentCaptor =
                ArgumentCaptor.forClass(Room.class);
        verify(roomRepository)
                .save(dishArgumentCaptor.capture());

        Room actual = dishArgumentCaptor.getValue();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Should_Delete_Room() {
        // given
        int dishId = 1;

        given(roomRepository.existsById(dishId)).willReturn(true);
        // when
        // then
        underTest.deleteRoom(dishId);
        verify(roomRepository).deleteById(dishId);
    }

    @Test
    void Should_Update_Room() {
        // given
        Room expected = new Room(
                1,
                "siem"
        );

        given(roomRepository.findById(expected.getId())).willReturn(Optional.of(expected));

        //when
        underTest.updateRoom(expected.getId(), expected);
        ArgumentCaptor<Room> dishArgumentCaptor =
                ArgumentCaptor.forClass(Room.class);
        verify(roomRepository)
                .save(dishArgumentCaptor.capture());

        Room actual = dishArgumentCaptor.getValue();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Throw_Exception_When_Room_Is_Empty() {
        // given
        Room room = new Room(
                1,
                "Siem"
        );

        // when
        given(roomRepository.findById(room.getId()))
                .willReturn(Optional.empty());

        // then
        assertThatThrownBy(() -> underTest.updateRoom(room.getId(), room))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("Room does not exist with given id");
        verify(roomRepository, never()).save(any());
    }

    @Test
    void Throw_Exception_When_Room_Is_Taken() {
        // given
        Room room = new Room(
                1,
                "Siem"
        );

        // when
        given(roomRepository.findRoomByName("Siem"))
                .willReturn(java.util.Optional.of(room));
        // then
        assertThatThrownBy(() -> underTest.createRoom(room))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("Name already taken!");
        verify(roomRepository, never()).save(any());
    }

    @Test
    void Throw_Exception_When_Id_Does_Not_Exist() {
        // given
        int roomId = 1;

        given(roomRepository.existsById(roomId)).willReturn(false);

        assertThatThrownBy(() -> underTest.deleteRoom(roomId))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("Room with id "+ roomId +" does not exist");
    }
}
