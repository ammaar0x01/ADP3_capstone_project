/*
File name:  PaymentFactory.java
Author:     Talia Smuts
Student Number:    221126082
*/

package com.college.factory;

import com.college.domain.Payment;
import java.time.LocalDate;

public class PaymentFactory {

    public static Payment createPayment(int paymentId, double amount, String method, String status, LocalDate date) {
        return new Payment.Builder()
                .setPaymentId(paymentId)
                .setPaymentAmount(amount)
                .setPaymentMethod(method)
                .setPaymentStatus(status)
                .setPaymentDate(date)
                .build();
    }
}
