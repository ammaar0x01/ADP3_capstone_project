/*
File name:  PaymentFactoryTest.java
Author:     Talia Smuts
Student Number: 221126082
*/

package com.college.factory;

import com.college.domain.Payment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PaymentFactoryTest {

    @AfterEach
    void line() {
        System.out.println("----------------------------");
    }

    @Test
    void createPaymentEmptyFields() {
        System.out.println("\nTest1 (object creation; empty strings; id=0)");
        Payment payment = PaymentFactory.createPayment(0, "", "", "", "");
        System.out.println(payment);

        assertNotNull(payment, "\nObject is null");
        assertEquals(0, payment.getPaymentId());
        assertEquals("", payment.getPaymentAmount());
        assertEquals("", payment.getPaymentMethod());
        assertEquals("", payment.getPaymentStatus());
        assertEquals("", payment.getPaymentDate());
    }

    @Test
    void createPaymentDefaultValues() {
        System.out.println("\nTest2 (object creation with default values)");
        Payment payment = PaymentFactory.createDefaultPayment(1, "1000.00");
        System.out.println(payment);

        assertNotNull(payment, "\nObject is null");
        assertEquals(1, payment.getPaymentId());
        assertEquals("1000.00", payment.getPaymentAmount());
        assertEquals("Credit Card", payment.getPaymentMethod());
        assertEquals("Pending", payment.getPaymentStatus());
        assertEquals("2023-10-01", payment.getPaymentDate());
    }

    @Test
    void createPaymentCustomValues() {
        System.out.println("\nTest3 (object creation with custom values)");
        Payment payment = PaymentFactory.createPayment(2, "2000.00", "Debit Card", "Completed", "2023-11-15");
        System.out.println(payment);

        assertNotNull(payment, "\nObject is null");
        assertEquals(2, payment.getPaymentId());
        assertEquals("2000.00", payment.getPaymentAmount());
        assertEquals("Debit Card", payment.getPaymentMethod());
        assertEquals("Completed", payment.getPaymentStatus());
        assertEquals("2023-11-15", payment.getPaymentDate());
    }

    @Test
    void equalityTest() {
        System.out.println("\nTest4 (testing equality of two different objects with same data)");
        Payment p1 = PaymentFactory.createPayment(3, "1500.00", "Paypal", "Pending", "2023-12-01");
        Payment p2 = PaymentFactory.createPayment(3, "1500.00", "Paypal", "Pending", "2023-12-01");

        System.out.println(p1.hashCode());
        System.out.println(p2.hashCode());


        assertNotEquals(p1, p2, "\nObjects are equal");
    }
}
