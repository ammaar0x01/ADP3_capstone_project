/*  HousekeeperService.java
    HousekeeperService
    Author: Muaath Slamong (230074138)
    Date: 22 May 2025
*/

package com.college.service;

import com.college.domain.Housekeeper;
import com.college.repository.HousekeeperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HousekeeperService implements IHousekeeperService {

    private final HousekeeperRepository repository;

    @Autowired
    public HousekeeperService(HousekeeperRepository repository) {
        this.repository = repository;
    }

    @Override
    public Housekeeper create(Housekeeper housekeeper) {
        return this.repository.save(housekeeper);
    }

    @Override
    public Housekeeper read(Integer id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public Housekeeper update(Housekeeper housekeeper) {
        return this.repository.save(housekeeper);
    }

    @Override
    public boolean delete(Integer id) {
        this.repository.deleteById(id);
        return false;
    }

    @Override
    public Housekeeper findById(Integer id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public List<Housekeeper> getAll() {
        return this.repository.findAll();
    }
}
