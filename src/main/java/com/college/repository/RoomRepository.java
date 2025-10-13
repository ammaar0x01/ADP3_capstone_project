package com.college.repository;

import com.college.domain.reservationRelated.Reservation;
import com.college.domain.reservationRelated.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    List<Room> findByReservation(Reservation reservation);
}
