/*
File name: PaymentController.java
Author: Talia Smuts
Student Number: 221126082
*/

package com.college.controller;

import com.college.domain.Payment;
import com.college.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final IPaymentService service;

    @Autowired
    public PaymentController(IPaymentService paymentService) {
        this.service = paymentService;
    }

    @GetMapping("/get/{id}")
    public Payment read(@PathVariable int id) {
        return service.findById(id).orElse(null);
    }

    @GetMapping("/get/all")
    public List<Payment> getAll() {
        return service.getAll();
    }

    @PostMapping("/create")
    public Payment create(@RequestBody Payment payment) {
        return service.create(payment);
    }

    @PutMapping("/update")
    public Payment update(@RequestBody Payment payment) {
        return service.update(payment);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable int id) {
        service.delete(id);
        return true;
    }

}
