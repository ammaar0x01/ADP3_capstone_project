package com.college.domain;

public class Main {
    public static void main(String[] args) {
        // Create a Payment object using the Builder pattern
        Payment payment = new Payment.Builder()
                .setPaymentId(1)
                .setPaymentAmount("100.00")
                .setPaymentMethod("Credit Card")
                .setPaymentStatus("Completed")
                .setPaymentDate("2023-10-01")
                .build();

        // Print the Payment object
        System.out.println(payment);
    }
}
