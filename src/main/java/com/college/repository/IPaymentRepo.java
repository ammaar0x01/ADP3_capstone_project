/*
File name:  IPaymentRepo.java
Author:     Talia Smuts
Student Number:    221126082
*/


package com.college.repository;

import com.college.domain.Payment;
import java.util.ArrayList;

public interface IPaymentRepo extends _RepositoryInterface<Payment, Integer> {
    public default ArrayList<Payment> getAll() {
        return new ArrayList<>();

    }
}