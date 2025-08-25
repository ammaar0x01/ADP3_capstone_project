/*  HousekeeperControllerTest.java
    HousekeeperController Test
    Author: Muaath Slamong (230074138)
    Date: 22 May 2025
*/

package com.college.controller;

import com.college.domain.Housekeeper;
import com.college.factory.HousekeeperFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HousekeeperControllerTest {

    private static Housekeeper housekeeper;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8080";

    @BeforeAll
    public static void setUp() {
        housekeeper = HousekeeperFactory.createHousekeeper(1, "Alice", "Smith");
    }

    @Test
    void create() {
        String url = BASE_URL + "/create/housekeeper";
        Housekeeper created = this.restTemplate.postForObject(url, housekeeper, Housekeeper.class);
        assertNotNull(created);
        assertEquals(housekeeper.getHousekeeperName(), created.getHousekeeperName());
        System.out.println("Created: " + created);
    }

    @Test
    void read() {
        String url = BASE_URL + "/get/housekeeper/" + housekeeper.getHousekeeperId();
        Housekeeper read = this.restTemplate.getForObject(url, Housekeeper.class);
        assertNotNull(read);
        assertEquals(housekeeper.getHousekeeperId(), read.getHousekeeperId());
        System.out.println("Read: " + read);
    }

    @Test
    void update() {
        String url = BASE_URL + "/update/housekeeper";
        Housekeeper updated = new Housekeeper.HousekeeperBuilder()
                .setHousekeeperId(housekeeper.getHousekeeperId())
                .setHousekeeperName("Alicia")
                .setHousekeeperSurname("Johnson")
                .build();
        this.restTemplate.put(url, updated);

        String getUrl = BASE_URL + "/get/housekeeper/" + housekeeper.getHousekeeperId();
        Housekeeper readUpdated = this.restTemplate.getForObject(getUrl, Housekeeper.class);
        assertNotNull(readUpdated);
        assertEquals("Alicia", readUpdated.getHousekeeperName());
        System.out.println("Updated: " + readUpdated);
    }

    @Test
    void delete() {
        String url = BASE_URL + "/delete/housekeeper";
        this.restTemplate.delete(url, housekeeper.getHousekeeperId());

        String getUrl = BASE_URL + "/get/housekeeper/" + housekeeper.getHousekeeperId();
        Housekeeper deleted = this.restTemplate.getForObject(getUrl, Housekeeper.class);
        assertNull(deleted);
        System.out.println("Deleted: " + housekeeper.getHousekeeperId());
    }

    @Test
    void getAllHousekeepers() {
        String url = BASE_URL + "/get/housekeepers";
        Housekeeper[] all = this.restTemplate.getForObject(url, Housekeeper[].class);
        assertNotNull(all);
        System.out.println("All Housekeepers:");
        for (Housekeeper hk : all) {
            System.out.println(hk);
        }
    }
}
