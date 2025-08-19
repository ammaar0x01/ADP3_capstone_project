package com.college.service;

import com.college.domain.Reservation;
import com.college.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ReservationService {
    ReservationRepository repo;
    @Autowired
    public ReservationService(ReservationRepository reservationRepository){
        this.repo = reservationRepository;
    }
    // ---------------------------

    public Reservation create(Reservation reservation){
        return repo.save(reservation);
    }

    public Reservation read(int id){
        return repo.findById(id).orElse(null);
    }

    public Reservation update(Reservation reservation){
        return repo.save(reservation);
    }

    public boolean delete(int id){
        repo.deleteById(id);
        return true;
    }

    public List<Reservation> getAll(){
        return repo.findAll();
    }
}
