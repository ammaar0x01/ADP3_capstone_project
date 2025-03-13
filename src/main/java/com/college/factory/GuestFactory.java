package com.college.factory;

import com.college.domain.Guest;
import com.college.util.Helper;

public class GuestFactory {

    public static Guest createGuest(int guestID, String name, String surname, String contactNumber, String email, String paymentDetails) {
        // Validation logic (optional)
        if (Helper.isNullOrEmpty(name) || Helper.isNullOrEmpty(surname) || Helper.isNullOrEmpty(paymentDetails)) {
            throw new IllegalArgumentException("Name, surname, and payment details cannot be empty.");
        }
        if (!Helper.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        if (!Helper.isValidContactNumber(contactNumber)) {  // Pass contactNumber as a string
            throw new IllegalArgumentException("Invalid contact number.");
        }

        // Using the GuestBuilder to build the Guest object
        return new Guest.GuestBuilder()
                .setGuestID(guestID)
                .setName(name)
                .setSurname(surname)
                .setContactNumber(contactNumber)
                .setEmail(email)
                .setPaymentDetails(paymentDetails)
                .build(); // Calling build to create the Guest
    }
}
