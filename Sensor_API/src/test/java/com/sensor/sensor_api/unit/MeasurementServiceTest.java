package com.sensor.sensor_api.unit;

import com.sensor.sensor_api.measurement.Measurement;
import com.sensor.sensor_api.measurement.MeasurementRepository;
import com.sensor.sensor_api.measurement.MeasurementService;
import com.sensor.sensor_api.room.Room;
import com.sensor.sensor_api.room.RoomRepository;
import com.sensor.sensor_api.room.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MeasurementServiceTest {
    @Mock
    private RoomRepository roomRepository;
    private RoomService underTest;

    @BeforeEach
    void setUp() {
        underTest = new RoomService(roomRepository);
    }

    @Test
    void Should_Get_Room_By_Name() {
        // given

        // measurements.add(new Measurement(1, "siem", 10, 25, Date.valueOf(LocalDate.now())));
        // add set<measurements> to room.
        Room room = new Room(
                1,
                "Siem"
        );

        given(roomRepository.findMeasurementByRoomName(room.getName())).willReturn(java.util.Optional.of(room));
        // when
        underTest.getMeasurementByRoomName(room.getName());
        // then
        verify(roomRepository).findMeasurementByRoomName(room.getName());

//        System.out.println("roomname: " + measurement.getRoomName());
//        System.out.println("date of measurement: " + measurement.getDatetime());
//        System.out.println("brightness: " + measurement.getBrightness());
//        System.out.println("temperature: " + measurement.getTemperature());

    }
}
