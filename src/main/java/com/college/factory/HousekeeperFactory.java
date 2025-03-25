/* HousekeeperFactory.java
   Housekeeper Factory Class
   Author: Muaath Slamong (230074138)
   Date: 19 March 2025
*/

package com.college.factory;

import com.college.entity.Housekeeper;

public class HousekeeperFactory {

    // Create Housekeeper
    public static Housekeeper createHousekeeper(int housekeeperId, String housekeeperName, String housekeeperSurname) {
        return new Housekeeper.HousekeeperBuilder()
                .setHousekeeperId(housekeeperId)
                .setHousekeeperName(housekeeperName)
                .setHousekeeperSurname(housekeeperSurname)
                .build();
    }
}
