package za.ac.cput.repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import za.ac.cput.entity.Room;
import za.ac.cput.repository.RoomRepository;
import za.ac.cput.factory.RoomFactory;

public class RoomRepositoryTest {

    private RoomRepository roomRepository;
    private Room roomOne;
    private Room roomTwo;

    @BeforeEach
    public void setUp() {

        roomRepository = RoomRepository.getInstance();

        roomOne = RoomFactory.createRoom("A101", "Single", 100.0f, true, "WiFi, TV");
        roomTwo = RoomFactory.createRoom("A102", "double", 10.0f, true, "Swimming Pool");

        roomRepository.create(roomOne);
        roomRepository.create(roomTwo);

    }

    @Test
    public void testCreateRoom() {

        assertNotNull(roomOne, "fail, room is null");
    }

    @Test
    public void testReadRoom() {

        Room retrievedRoom = roomRepository.read("A101");
        assertNotNull(retrievedRoom);
        assertEquals("A101", retrievedRoom.getRoomID());
        assertEquals("Single", retrievedRoom.getRoomType());
        assertEquals(100.0f, retrievedRoom.getPricePerNight());
        assertTrue(retrievedRoom.getAvailability());
        assertEquals("WiFi, TV", retrievedRoom.getFeatures());
    }

    @Test
    public void testUpdateRoom() {

        Room updatedRoom = RoomFactory.createRoom("A101", "Single", 150.0f, true, "WiFi, TV, Refrigerator");
        roomRepository.update("A101", updatedRoom);

        Room retrievedRoom = roomRepository.read("A101");
        assertNotNull(retrievedRoom);
        assertEquals(150.0f, retrievedRoom.getPricePerNight());
        assertEquals("WiFi, TV, Refrigerator", retrievedRoom.getFeatures());
    }

    @Test
    public void testDeleteRoom() {

        boolean isDeleted = roomRepository.delete("A102");
        assertTrue(isDeleted);

        Room retrievedRoom = roomRepository.read("B102");
        assertNull(retrievedRoom);
    }
}
