package com.college.repository;

import com.college.domain.Guest;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class GuestRepository implements iGuestRepository {

    @Override
    public Guest create(Guest entity) {
        return null;
    }

    @Override
    public Guest read(Integer integer) {
        return null;
    }

    @Override
    public Guest update(Guest entity) {
        return null;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public Map getAll() {
        return Map.of();
    }

    @Override
    public List<Guest> findByName(String name) {
        return List.of();
    }
}
