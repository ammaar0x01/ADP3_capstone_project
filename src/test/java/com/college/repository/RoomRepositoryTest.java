/* RoomRepositoryTest .java
RoomRepositoryTest (Tests for RoomRepository class)
Author: joshua twigg (222153881)
Date: 27 March 2025
*/
package com.college.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.college.domain.Room;
import com.college.factory.RoomFactory;

import java.util.HashMap;

public class RoomRepositoryTest {
    private RoomRepository roomRepository;
    private Room roomOne;
    private Room roomTwo;
    HashMap<String, Room> map;

    @BeforeEach
    public void setUp() {

        roomRepository = RoomRepository.getInstance();

        roomOne = RoomFactory.createRoom(12, "Single", 100.0f, true, "WiFi, TV");
        roomTwo = RoomFactory.createRoom(14, "double", 10.0f, true, "Swimming Pool");

        roomRepository.create(roomOne);
        roomRepository.create(roomTwo);

    }

    @Test
    public void testGetAll() {
        map = roomRepository.getAll();
        assertNotNull(map);
    }

    @Test
    public void testCreateRoom() {

        assertNotNull(roomOne, "fail, room is null");
    }

    @Test
    public void testReadRoom() {

        Room retrievedRoom = roomRepository.read(12);
        assertNotNull(retrievedRoom);
        assertEquals("A101", retrievedRoom.getRoomID());
        assertEquals("Single", retrievedRoom.getRoomType());
        assertEquals(100.0f, retrievedRoom.getPricePerNight());
        assertTrue(retrievedRoom.getAvailability());
        assertEquals("WiFi, TV", retrievedRoom.getFeatures());
    }

    @Test
    public void testUpdateRoom() {

        Room updatedRoom = RoomFactory.createRoom(12, "Single", 150.0f, true, "WiFi, TV, Refrigerator");
//        roomRepository.update(12, updatedRoom);

        Room retrievedRoom = roomRepository.read(12);
        assertNotNull(retrievedRoom);
        assertEquals(150.0f, retrievedRoom.getPricePerNight());
        assertEquals("WiFi, TV, Refrigerator", retrievedRoom.getFeatures());
    }

    @Test
    public void testDeleteRoom() {

        boolean isDeleted = roomRepository.delete(12);
        assertTrue(isDeleted);

        Room retrievedRoom = roomRepository.read(12);
        assertNull(retrievedRoom);
    }
}