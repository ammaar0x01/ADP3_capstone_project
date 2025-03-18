package za.ac.cput.domain;

import za.ac.cput.entity.Room;
import za.ac.cput.factory.RoomFactory;

public class Main {
    public static void main(String[] args) {
//        Room roomOne = new Room.RoomBuilder("a","b",124,true,"c").build();
//        Room roomTwo = new Room.RoomBuilder("x","y",124,true,"z").build();
//        System.out.println(roomOne.toString());

        Room roomThree = RoomFactory.createRoom("a","b",2,true,"ax");
        System.out.println(roomThree.toString());

    }
}
