/*  HousekeeperController.java
    HousekeeperController
    Author: Muaath Slamong (230074138)
    Date: 22 May 2025
*/

package com.college.controller;


import com.college.domain.Housekeeper;
import com.college.service.HousekeeperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HousekeeperController {

    private final HousekeeperService service;

    @Autowired
    public HousekeeperController(HousekeeperService housekeeperService) {
        this.service = housekeeperService;
    }

    @GetMapping("/get/housekeeper/{id}")
    public Housekeeper read(@PathVariable int id) {
        return service.findById(id);
    }

    @GetMapping("/get/housekeepers")
    public List<Housekeeper> getAll() {
        return service.getAll();
    }

    @PostMapping("/create/housekeeper")
    public Housekeeper create(@RequestBody Housekeeper housekeeper) {
        return service.create(housekeeper);
    }

    @PutMapping("/update/housekeeper")
    public Housekeeper update(@RequestBody Housekeeper housekeeper) {
        return service.update(housekeeper);
    }

    @DeleteMapping("/delete/housekeeper")
    public void delete(@RequestBody int id) {
        service.delete(id);
    }

}
