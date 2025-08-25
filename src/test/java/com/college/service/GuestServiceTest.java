package com.college.service;

import com.college.domain.Guest;
import com.college.factory.GuestFactory;
import com.college.repository.GuestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GuestServiceTest {

    @Autowired
    private GuestService guestService;
    private Guest guest = GuestFactory.createGuest( 21, "John", "Doe", "1234567890", "john@gmail.com","card");

    @Test
    void create() {
        Guest created = guestService.create(guest);
        assertNotNull(created);
        System.out.println(created);
    }

    @Test
    void read() {
        Guest read = guestService.read(guest.getGuestID());
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void update() {
        Guest newGuest = new Guest.GuestBuilder().copy(guest).setName("Jane").build();
        Guest updated = guestService.update(newGuest);
        assertNotNull(updated);
        System.out.println(updated);
    }

    @Test
    void getAll() {
        System.out.println(guestService.getAll());
    }
}
