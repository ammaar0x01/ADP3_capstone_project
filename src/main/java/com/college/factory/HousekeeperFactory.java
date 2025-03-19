package com.college.factory;

import com.college.entity.Housekeeper;

public class HousekeeperFactory {

    // Create Housekeeper
    public static Housekeeper createHousekeeper(int housekeeperId, String housekeeperName, String housekeeperSurname, String housekeeperRole) {
        return new Housekeeper.HousekeeperBuilder()
                .setHousekeeperId(housekeeperId)
                .setHousekeeperName(housekeeperName)
                .setHousekeeperSurname(housekeeperSurname)
                .setHousekeeperRole(housekeeperRole)
                .build();
    }
}
