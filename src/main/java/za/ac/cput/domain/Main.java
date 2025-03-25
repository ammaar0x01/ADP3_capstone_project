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
        RoomRepository roomRepository = RoomRepository.getInstance();

        //add room to hashmap
        roomRepository.create(roomThree);

        //select room three from repo
        roomRepository.read(roomThree.getRoomID());

        //updates values only
        roomRepository.update(roomThree.getRoomID(), roomFour);

        //selecting again to see new values
        roomRepository.read(roomThree.getRoomID());

        //delete from hashmap
        roomRepository.delete(roomThree.getRoomID());

        //print everything in hashmap
        roomRepository.getAll();
    }
}
