package com.college.service;

import com.college.domain.reservationRelated.Room;
import com.college.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService implements IRoomService {

    //auto-inject an instance of a class
    //bean = object managed by springboot
    @Autowired
    private RoomRepository roomRepository;


    @Override
    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room create(Room room) {
        roomRepository.save(room);
        return room;
    }

    @Override
    public Room read(Integer integer) {
        return roomRepository.findById(integer).orElse(null);
    }

    @Override
    public Room update(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public boolean delete(Integer integer) {
        roomRepository.deleteById(integer);
        return true;
    }


}
