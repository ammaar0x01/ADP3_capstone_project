package com.college.controller;

import com.college.domain.Room;
import com.college.factory.RoomFactory;
import com.college.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class RoomControllerTest {

    @Autowired
    private RoomService roomService;

    @Autowired
    RoomController controller;

    Room roomOne;
    Room roomTwo;


    @BeforeEach
    void setUp() {
        roomOne = RoomFactory.createRoom(20, "small", 1200.50f, true, "Sea view, King bed, WiFi");
        roomTwo = RoomFactory.createRoom(21, "big", 100.50f, true, "sofa, bed");
    }

    @Test
    void testSaveRoom() {

        assertNotNull(controller.save(roomOne));
        System.out.println(roomOne + " saved ");

    }


    @Test
    void testReadRoom() {

        controller.read(21);
        System.out.println( " retrieved ");

    }



    @Test
    void testUpdateRoom() {

        controller.update(roomTwo);
        System.out.println(" updated " + roomTwo);

    }


    @Test
    void testDeleteRoom() {

        controller.delete(21);
        System.out.println(" room deleted ");

    }


}