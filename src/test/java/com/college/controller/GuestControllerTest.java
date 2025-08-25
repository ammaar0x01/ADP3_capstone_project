package com.college.controller;

import com.college.domain.Guest;
import com.college.factory.GuestFactory;
import com.college.service.GuestService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GuestControllerTest {

    private static Guest guest;

    @Autowired
    private TestRestTemplate restTemplate;
    private static final String BASE_URL = "http://localhost:8080/guest";

    @BeforeAll
    public static void setUp() {
        guest = GuestFactory.createGuest(21, "John", "Doe", "1234567890", "john@gmail.com","card");
    }

    @Test
    void create() {
        String url = BASE_URL + "/create";
        Guest createdGuest = this.restTemplate.postForObject(url, guest, Guest.class);
        assertNotNull(createdGuest);
        assertNotEquals(guest.getGuestID(), createdGuest.getGuestID());
        System.out.println("Created: " + createdGuest);
    }

    @Test
    void read() {
        String url = BASE_URL + "/read/" + guest.getGuestID();
        Guest readGuest = this.restTemplate.getForObject(url, Guest.class);
        assertNotNull(readGuest);
        assertNotEquals(guest.getGuestID(), readGuest.getGuestID());
        System.out.println("Read: " + readGuest);
    }

    @Test
    void update() {
        String url = BASE_URL + "/update";
        Guest updatedGuest = new Guest.GuestBuilder().copy(guest).setName("Jane").build();
        this.restTemplate.put(url, updatedGuest);
        Guest readUpdatedGuest = this.restTemplate.getForObject(url + guest.getGuestID(), Guest.class);
        assertNotNull(readUpdatedGuest);
        assertNotEquals(guest.getName(), readUpdatedGuest.getName());
        System.out.println("Updated: " + readUpdatedGuest);
    }

    @Test
    void delete() {
        String url = BASE_URL + "/delete/";
        this.restTemplate.delete((url) + guest.getGuestID());
        Guest deletedGuest = this.restTemplate.getForObject(url + guest.getGuestID(), Guest.class);
        assertNotNull(deletedGuest);
        System.out.println("Deleted: " + deletedGuest);
    }

    @Test
    void getAllGuest() {
        String url = BASE_URL + "/getall";
        Guest[] guests = this.restTemplate.getForObject(url, Guest[].class);
        assertNotNull(guests);
        System.out.println("All Guests: ");
        for (Guest guest : guests) {
            System.out.println(guest);
        }
    }
}
