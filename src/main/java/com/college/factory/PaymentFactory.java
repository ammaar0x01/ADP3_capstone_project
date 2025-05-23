/*
File name:  PaymentFactory.java
Author:     Talia Smuts
Student Number:    221126082
*/

package com.college.factory;

import com.college.domain.Payment;

public class PaymentFactory {

        public static Payment createPayment(int paymentId, String amount, String method, String status, String date) {
            return new Payment.Builder()
                    .setPaymentId(paymentId)
                    .setPaymentAmount(amount)
                    .setPaymentMethod(method)
                    .setPaymentStatus(status)
                    .setPaymentDate(date)
                    .build();
        }

    }
