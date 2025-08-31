package com.college.controller;

import com.college.domain.Guest;
import com.college.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guest")
public class GuestController {

    private GuestService guestService;

    @Autowired
    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @PostMapping("/create")
    public Guest create(@RequestBody Guest guest) {
        return guestService.create(guest);
    }

    @GetMapping("/read/{guestID}")
    public Guest read(@PathVariable int guestID) {
        return guestService.read(guestID);
    }

    @PutMapping("/update")
    public Guest update(@RequestBody Guest guest) {
        return guestService.update(guest);
    }

    @DeleteMapping("/delete/{guestID}")
    public boolean delete(@PathVariable int guestID) {
        return guestService.delete(guestID);
    }

    @GetMapping("/getall")
    public List<Guest> getAllGuest() {
        return guestService.getAll();
    }
}
