package com.college.factory;

import com.college.domain.Payment;

public class PaymentFactory {

    public static Payment createPayment(int paymentId, String paymentAmount, String paymentMethod, String paymentStatus, String paymentDate) {
        return new Payment.Builder()
                .setPaymentId(paymentId)
                .setPaymentAmount(paymentAmount)
                .setPaymentMethod(paymentMethod)
                .setPaymentStatus(paymentStatus)
                .setPaymentDate(paymentDate)
                .build();
    }

    public static Payment createDefaultPayment(int paymentId, String paymentAmount, String paymentMethod, String paymentStatus, String paymentDate) {
        return createPayment(paymentId, paymentAmount, paymentMethod, paymentStatus, paymentDate);
    }

}
