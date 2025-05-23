package com.college.service;

import com.college.domain.Room;

import java.util.List;

public interface IRoomService extends IService< Room, Integer>{
    List<Room> getAll();


}
