package com.college.domain;

import com.college.factory.PaymentFactory;

public class Main {
    public static void main(String[] args) {
        // Create a Payment object using the Builder pattern
        Payment defaultPayment = PaymentFactory.createDefaultPayment(1, "1500.00", "Credit Card", "Completed", "2023-10-01");
        System.out.println("Default Payment: " + defaultPayment);

        // Create a custom payment using the factory
        Payment customPayment = PaymentFactory.createPayment(2, "750.00", "Mastercard", "Completed", "2023-10-05");
        System.out.println("Custom Payment: " + customPayment);
    }
}
