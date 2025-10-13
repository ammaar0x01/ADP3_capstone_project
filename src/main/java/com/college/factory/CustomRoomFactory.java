

package com.college.factory;

import com.college.domain.reservationRelated.CustomRoom;

public class CustomRoomFactory {
    public static CustomRoom createCustomRoom(int roomID, String roomType, float roomPrice, boolean availability, String features, byte[] image) {
        return new CustomRoom.CustomRoomBuilder(roomID, roomType, roomPrice, availability, features, image).build();
    }
}
