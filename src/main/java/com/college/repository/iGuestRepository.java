package com.college.repository;

import com.college.domain.Guest;

import java.util.List;

public interface iGuestRepository extends iRepository <Guest, Integer> {

    List<Guest> findByName(String name);
}
