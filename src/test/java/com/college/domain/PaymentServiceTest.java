/*
File name: PaymentServiceTest.java
Author: Talia Smuts
Student Number: 221126082
*/

package com.college.domain;

import com.college.service.IPaymentService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PaymentServiceTest {

    @Autowired
    private IPaymentService paymentService;

    static Payment payment = new Payment.Builder()
            .setPaymentId(1)
            .setPaymentAmount("1000")
            .setPaymentMethod("Credit Card")
            .setPaymentStatus("Completed")
            .setPaymentDate("2023-10-01")
            .build();

    @Test
    @Order(1)
    public void CreatePayment() {
        Payment createdPayment = paymentService.create(payment);
        assertNotNull(createdPayment);
        assertEquals(payment.getPaymentId(), createdPayment.getPaymentId());
        System.out.println("Created Payment: " + createdPayment);
    }

    @Test
    @Order(2)
    public void UpdatePayment() {
        Payment updatedPayment = new Payment.Builder()
                .setPaymentId(payment.getPaymentId())
                .setPaymentAmount("2000")
                .setPaymentMethod(payment.getPaymentMethod())
                .setPaymentStatus(payment.getPaymentStatus())
                .setPaymentDate(payment.getPaymentDate())
                .build();

        Payment result = paymentService.update(updatedPayment);
        assertEquals("2000", result.getPaymentAmount());
    }

    @Test
    @Order(3)
    public void ReadPayment() {
        Optional<Payment> foundPayment = paymentService.findById(payment.getPaymentId());
        assertTrue(foundPayment.isPresent());
        assertEquals(payment.getPaymentId(), foundPayment.get().getPaymentId());
    }

    @Test
    @Order(4)
    public void DeletePayment() {
        paymentService.delete(payment.getPaymentId());
        Optional<Payment> deleted = paymentService.findById(payment.getPaymentId());
        assertFalse(deleted.isPresent());
    }

    @Test
    @Order(5)
    public void FindAllPayments() {
        List<Payment> payments = paymentService.getAll(); // Adjusted to your service method
        assertNotNull(payments);
        System.out.println("All Payments: " + payments);
    }


}
