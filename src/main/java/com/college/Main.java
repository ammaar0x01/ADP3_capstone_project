/*

Project:    temp
Desc:       temp
Started:    11.03.25
Updated:    11.03.25
---

Members
-------
Name and surname | Student id | GitHub profile                |
---------------------------------------------------------------
Zaid Theunissen | 221084142  | https://github.com/zaid-xt |
---------------------------------------------------------------
*/

package com.college;

import com.college.domain.Guest;
import com.college.factory.GuestFactory;
import com.college.repository.GuestRepository;

public class Main {
    public static void main(String[] args) {

        // Create GuestRepository instance
        GuestRepository guestRepository = new GuestRepository();

        // Create a Guest object
        Guest guest1 = GuestFactory.createGuest(101, "Alice", "Smith", "9876543210", "alice@email.com", "PayPal");

        // Save guest to repository
        guestRepository.save(guest1);

        // Retrieve and print guest details
        Guest retrievedGuest = guestRepository.findByGuestID(101);
        System.out.println("Retrieved Guest: " + retrievedGuest);
    }
}
