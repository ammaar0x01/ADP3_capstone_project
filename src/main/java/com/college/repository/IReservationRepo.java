/*
File name:  IReservationRepo.java
Author:     Ammaar
Started:    12.03.25
*/

package com.college.repository;

import com.college.domain.Reservation;

import java.util.ArrayList;

public interface IReservationRepo extends _RepositoryInterface<Reservation, Integer> {
    public ArrayList<Reservation> getAll();

}
