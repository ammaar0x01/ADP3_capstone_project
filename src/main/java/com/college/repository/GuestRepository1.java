package com.college.repository;

import com.college.domain.Guest;
import java.util.HashMap;
import java.util.Map;

public class GuestRepository1 {

    private final Map<Integer, Guest> guestDatabase = new HashMap<>();

    // Save a guest to the database
    public void save(Guest guest) {
        guestDatabase.put(guest.getGuestID(), guest);
    }

    // Find a guest by their ID
    public Guest findByGuestID(int guestID) {
        return guestDatabase.get(guestID);
    }

    // Delete a guest by their ID
    public boolean delete(int guestID) {
        if (guestDatabase.containsKey(guestID)) {
            guestDatabase.remove(guestID);
            return true;
        }
        return false;
    }

    // Get all guests
    public Map<Integer, Guest> getAll() {
        return guestDatabase;
    }
}
