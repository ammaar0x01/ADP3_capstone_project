package com.college.service;

import com.college.domain.Room;
import com.college.factory.RoomFactory;
import com.college.repository.RoomRepositoryJpa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoomServiceTest {


    @Autowired
    RoomRepositoryJpa repository;

    @Autowired
    RoomService service;

    Room roomOne;
    Room roomTwo;

    @BeforeEach
    void setUp() {

       roomOne = RoomFactory.createRoom(1, "medium", 1200.50f, true, "Sea view, King bed, WiFi");
       roomTwo = RoomFactory.createRoom(2, "suite", 100.50f, true, "King123 bed, sink");

    }


    @Test
    void testSaveRoom() {

        assertNotNull(service.create(roomOne));
        System.out.println(roomOne + " saved ");
    }


    @Test
    void testReadRoom() {

       Room r = service.read(2);
        System.out.println(r + " retrieved ");
    }



    @Test
    void testUpdateRoom() {

        service.update(roomTwo);
        System.out.println(" updated: " + roomTwo);
    }


    @Test
    void testDeleteRoom() {

        service.delete(1);
        System.out.println(" room deleted");
    }


}