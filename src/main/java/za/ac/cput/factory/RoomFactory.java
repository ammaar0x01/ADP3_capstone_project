package za.ac.cput.factory;

import za.ac.cput.entity.Room;

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
