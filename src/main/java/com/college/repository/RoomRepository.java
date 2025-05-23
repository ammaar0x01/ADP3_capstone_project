/* RoomRepository.java
RoomRepository Model class
Author: joshua twigg (222153881)
Date: 27 March 2025
*/

package com.college.repository;

import com.college.domain.Room;
import java.util.HashMap;

@Deprecated
public class RoomRepository implements IRepository<Room, Integer> {

    private static RoomRepository instance;  // Singleton instance
    private HashMap<String, Room> map;

    // Private constructor to prevent instantiation
    private RoomRepository() {
        map = new HashMap<>();
    }

    // Public method to get the instance of RoomRepository
    public static RoomRepository getInstance() {
        if (instance == null) {
            instance = new RoomRepository();
        }
        return instance;
    }

    public HashMap<String, Room> getAll() {
        if (map.isEmpty()) {
            System.out.println("The repository is empty.");
        } else {
            System.out.println("The repository contains " + map.size() + " rooms.");
            map.forEach((key, value) -> System.out.println(value + "\t" + "key: " + key));
        }
        return map;
    }


    @Override
    public Room create(Room obj) {

//        map.put(obj);
//        System.out.println("Room added to repository");
        return obj;

    }

    @Override
    public Room read(Integer integer) {

        Room room = map.get(integer);
        if (room == null) {
            System.out.println("Room does not exist");
            return null;
        }
        System.out.println("Room read from repository" + " " + room + "\n");
        return room;

    }

    @Override
    public Room update(Room obj) {

//        if (map.containsKey(obj)) {
//            map.put(obj, obj); // update the room
//            System.out.println("Room updated");
//        } else {
//            System.out.println("Room does not exist");
//        }

        return obj;
    }

    @Override
    public boolean delete(Integer integer) {

        if (map.remove(integer) != null) {
            System.out.println("Room deleted");
            return true;
        } else {
            return false;
        }

    }
}