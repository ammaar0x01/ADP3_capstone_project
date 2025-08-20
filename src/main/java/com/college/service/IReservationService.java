package com.college.service;

import com.college.domain.Reservation;
import com.college.domain.Room;

import java.util.List;

public interface IReservationService extends IService<Reservation, Integer>{
    List<Room> getAll();
}
