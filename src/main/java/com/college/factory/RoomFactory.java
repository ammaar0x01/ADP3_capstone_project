/* RoomFactory.java
RoomFactory Model class
Author: joshua twigg (222153881)
Date: 27 March 2025
*/

package com.college.factory;

import com.college.domain.Room;

public class RoomFactory {

    public static Room createRoom() {

        return new Room.RoomBuilder("factoryTest","y",124,true,"z").build();

    }

    /// overload method with null checks
    public static Room createRoomTesting(String roomID) {

        if(roomID.isEmpty() ||roomID ==null){
            return null;
        }

        return new Room.RoomBuilder("x","y",124,true,"z").build();
    }
}
