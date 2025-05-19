package com.college.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {
    @GetMapping("/reservation")
    public String reservation(){
        return "<h1>Reservation</h1>";
    }

    @GetMapping("/reservation/get")
    public String reservationGetAll(){
        return "{ data: Data from the database }";
    }
}
