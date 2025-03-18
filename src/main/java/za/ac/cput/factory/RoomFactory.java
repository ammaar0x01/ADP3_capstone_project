package za.ac.cput.factory;

import za.ac.cput.entity.Room;

public class RoomFactory {
    public static Room createRoom(String roomID, String roomType,float roomPrice, boolean availablity, String features) {

        return new Room.RoomBuilder(roomID,roomType,roomPrice,availablity,features).build();

    }

    /// overload method with null checks
    public static Room createRoomTesting(String roomID) {

        if(roomID.isEmpty() ||roomID ==null){
            return null;
        }

        return new Room.RoomBuilder("x","y",124,true,"z").build();
    }
}
