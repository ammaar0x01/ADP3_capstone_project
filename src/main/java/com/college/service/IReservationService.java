package com.college.service;

import com.college.domain.reservationRelated.Reservation;
import com.college.domain.reservationRelated.Room;

import java.util.List;

public interface IReservationService extends IService<Reservation, Integer>{
    List<Room> getAll();
}
