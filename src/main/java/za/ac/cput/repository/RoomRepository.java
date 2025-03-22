package za.ac.cput.repository;

import za.ac.cput.entity.Room;

import java.util.HashMap;
import java.util.Map;


public class RoomRepository implements _RepositoryInterface <Room, String>{
    public static HashMap<String, Room> map;

    public RoomRepository() {
        map = new HashMap<>();
    }

    @Override
    public Room create(Room obj) {
        map.put(obj.getRoomID(), obj);
        System.out.println("room added to repository");
        return obj;
    }

    @Override
    public Room read(String id) {
        Room room = map.get(id);
        if(room == null){
            System.out.println("room does not exist");
            return null;
        }
        return room;
    }

    @Override
    public Room update(String id, Room obj) {
            map.put(obj.getRoomID(), obj);
            return obj;
    }

    @Override
    public boolean delete(String id) {
        if(map.remove(id) != null){
            System.out.println("room deleted");
            return true;
        }else{
            return false;
        }
    }

    //main method
    public static void main(String[] args) {

    }
}
