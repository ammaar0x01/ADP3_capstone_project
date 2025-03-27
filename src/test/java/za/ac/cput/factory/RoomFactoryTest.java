/* RoomFactoryTest .java
RoomFactoryTest (Tests for RoomFactory class)
Author: joshua twigg (222153881)
Date: 27 March 2025
*/

package za.ac.cput.factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.entity.Room;

import static org.junit.jupiter.api.Assertions.*;

class RoomFactoryTest {

    private Room room;

    @BeforeEach
    public void setUp() {

        room = RoomFactory.createRoom("A101", "Single", 100.0f, true, "WiFi, TV");
    }

    @Test
    public void testCreateRoom() {

        assertNotNull(room, "fail, room is null");

    }

    @Test
    public void testValidInputs() {

        String roomID = "A101";
        String roomType = "Single";
        float roomPrice = 100.0f;
        boolean availability = true;
        String features = "WiFi, TV";

        assertEquals(roomID, room.getRoomID());
        assertEquals(roomType, room.getRoomType());
        assertEquals(roomPrice, room.getPricePerNight());
        assertTrue(room.getAvailability());
        assertEquals(features, room.getFeatures());
    }

    @Test
    public void testEmptyFeatures() {

        String roomID = " ";
        String roomType = " ";
        float roomPrice = 150.0f;
        boolean availability = true;
        String features = "";

        Room roomTwo = RoomFactory.createRoom(roomID, roomType, roomPrice, availability, features);

        assertNotNull(roomTwo);
    }

    @Test
    public void testEqualityOfObjects() {
        String roomID = "";
        String roomType = "";
        float roomPrice = 0;
        boolean availability = false;
        String features = "";

        Room roomTwo = RoomFactory.createRoom(roomID, roomType, roomPrice, availability, features);


        assertNotEquals(roomTwo,room);
    }

}
