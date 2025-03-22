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
import com.college.repository.iGuestRepository;
import com.college.repository.GuestRepository;

public class Main {
    public static void main(String[] args) {
        // Create IGuestRepository instance
        iGuestRepository guestRepository = new GuestRepository();

        // Create a Guest object
        Guest guest1 = GuestFactory.createGuest(101, "Alice", "Smith", "9876543210", "alice@email.com", "PayPal");

        // Save guest to repository
        guestRepository.create(guest1);

        // Retrieve and print guest details
        Guest retrievedGuest = guestRepository.read(101);
        System.out.println("Retrieved Guest: " + retrievedGuest);

        // Update guest information
        Guest updatedGuest = new Guest.GuestBuilder()
                .setGuestID(101)
                .setName("Alice")
                .setSurname("Johnson")
                .setContactNumber("9876543210")
                .setEmail("alice@email.com")
                .setPaymentDetails("Credit Card")
                .build();
        guestRepository.update(updatedGuest);

        // Delete guest
        guestRepository.delete(101);
        System.out.println("Guest deleted. Remaining guests: " + guestRepository.getAll().size());
    }
}
