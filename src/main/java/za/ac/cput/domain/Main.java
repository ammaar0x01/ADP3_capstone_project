package za.ac.cput.domain;

import za.ac.cput.entity.Room;
import za.ac.cput.factory.RoomFactory;
import za.ac.cput.repository.RoomRepository;

public class Main {
    public static void main(String[] args) {
//        Room roomOne = new Room.RoomBuilder("a","b",124,true,"c").build();
//        Room roomTwo = new Room.RoomBuilder("x","y",124,true,"z").build();
//        System.out.println(roomOne.toString());

        Room roomThree = RoomFactory.createRoom("a","a",2,true,"axe");
        Room roomFour = RoomFactory.createRoom("b","b",3,false,"spoon");
        System.out.println(roomThree.toString());

        //crud roomThree with repo
        RoomRepository roomRepository = new RoomRepository();

        //add room to hashmap
        roomRepository.create(roomThree);

        //select room three from repo
       Room retrievedRoom = (roomRepository.read(roomThree.getRoomID()));
        System.out.println(retrievedRoom + " is the retrieved room");

        //update room three to room four, apparently this doesnt delete the old room from hashmap idk??!?!
        //read method anyway??? .readroomthree shoudlnt work but it does? idk
        roomRepository.update(roomThree.getRoomID(), roomFour);
        //selecting again
       Room newRoom = roomRepository.read(roomFour.getRoomID());
        System.out.println(newRoom + " is the new room");
        //delete from hashmap
        roomRepository.delete(roomFour.getRoomID());
        System.out.println(roomRepository.read(roomFour.getRoomID()));
    }
}
