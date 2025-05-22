package com.college.service;

import com.college.domain.Payment;
import java.util.List;
import java.util.Optional;

public interface IPaymentService {
    Payment create(Payment payment);
    Payment update(Payment payment);
    void delete(Integer id);
    Optional<Payment> findById(Integer id);
    List<Payment> getAll();
}

