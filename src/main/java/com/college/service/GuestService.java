package com.college.service;

import com.college.domain.Guest;
import com.college.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService implements IGuestService {

    private GuestRepository repository;

    @Autowired GuestService(GuestRepository repository) {
        this.repository = repository;
    }

    @Override
    public Guest create(Guest guest) {
        return this.repository.save(guest);
    }

    @Override
    public Guest read(Integer id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public Guest update(Guest guest) {
        return this.repository.save(guest);
    }

    @Override
    public boolean delete(Integer id) {
        this.repository.deleteById(id);
        return true;
    }

    @Override
    public List<Guest> getAll() {
        return this.repository.findAll();
    }

}
