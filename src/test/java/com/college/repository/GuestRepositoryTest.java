package com.college.repository;

import com.college.domain.Guest;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class GuestRepositoryTest {

    @Test
    void create() {
        Guest newGuest = new Guest(102, "Alice", "Brown", "0987654321", "alice.brown@example.com", "Unpaid");
        Guest created = repository.create(newGuest);
        assertNotNull(created, "Guest should be created successfully");
        assertEquals(newGuest.getGuestID(), created.getGuestID());
    }

    @Test
    void read() {
        Guest found = repository.read(101);
        assertNotNull(found, "Guest should be found");
        assertEquals("John", found.getName());
    }

    @Test
    void update() {
        Guest updatedGuest = new Guest(101, "John", "Doe", "1111111111", "john.doe@example.com", "Paid");
        Guest updated = repository.update(updatedGuest);
        assertNotNull(updated);
        assertEquals("1111111111", updated.getContactNumber());
    }

    @Test
    void delete() {
        boolean result = repository.delete(101);
        assertTrue(result, "Guest should be deleted");
        assertNull(repository.read(101));
    }

    @Test
    void getAll() {
        Set<Guest> guests = repository.getAll();
        assertFalse(guests.isEmpty(), "Guest repository should not be empty");
    }

    @Test
    void findByName() {
        Guest found = repository.findByName("John");
        assertNotNull(found, "Guest should be found by name");
        assertEquals("John", found.getName());
    }
}