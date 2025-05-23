/*  HousekeeperServiceTest.java
    HousekeeperService Test
    Author: Muaath Slamong (230074138)
    Date: 22 May 2025
*/

package com.college.service;

import com.college.entity.Housekeeper;
import com.college.factory.HousekeeperFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HousekeeperServiceTest {

    @Autowired
    private HousekeeperService housekeeperService;

    private final Housekeeper housekeeper = HousekeeperFactory.createHousekeeper(
            1, "Alice", "Smith"
    );

    @Test
    void create() {
        Housekeeper created = housekeeperService.create(housekeeper);
        assertNotNull(created);
        System.out.println(created);
    }

    @Test
    void read() {
        Housekeeper read = housekeeperService.findById(housekeeper.getHousekeeperId());
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void update() {
        Housekeeper updatedHousekeeper = new Housekeeper.HousekeeperBuilder()
                .setHousekeeperId(housekeeper.getHousekeeperId())
                .setHousekeeperName("Alicia")
                .setHousekeeperSurname("Johnson")
                .build();
        Housekeeper updated = housekeeperService.update(updatedHousekeeper);
        assertNotNull(updated);
        System.out.println(updated);
    }

    @Test
    void getAll() {
        System.out.println(housekeeperService.getAll());
    }
}
