package com.college.controller;

import com.college.domain.Payment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PaymentControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    static final String BASE_URL = "http://localhost:8080/";

    static Payment payment1;

    @BeforeAll
    static void setup() {
        payment1 = new Payment.Builder()
                .setPaymentId(1)
                .setPaymentAmount("1000.00")
                .setPaymentMethod("Credit Card")
                .setPaymentStatus("Pending")
                .setPaymentDate("2023-10-01")
                .build();
    }

    @Test
    @Order(1)
    void create() {
        String url = BASE_URL + "payment/create";
        Payment created = restTemplate.postForObject(url, payment1, Payment.class);
        System.out.println(created);
        assertNotNull(created);
        assertEquals(payment1.getPaymentId(), created.getPaymentId());
    }

    @Test
    @Order(2)
    void read() {
        String url = BASE_URL + "payment/get/" + payment1.getPaymentId();
        ResponseEntity<Payment> res = restTemplate.getForEntity(url, Payment.class);
        System.out.println(res.getBody());
        assertEquals(payment1.getPaymentId(), res.getBody().getPaymentId());
    }

    @Test
    @Order(3)
    void update() {
        Payment update = new Payment.Builder()
                .setPaymentId(payment1.getPaymentId())
                .setPaymentAmount("1500.00")  // Changed amount
                .setPaymentMethod(payment1.getPaymentMethod())
                .setPaymentStatus("Completed")
                .setPaymentDate(payment1.getPaymentDate())
                .build();

        String url = BASE_URL + "payment/update";
        restTemplate.put(url, update);

        ResponseEntity<Payment> res = restTemplate.getForEntity(
                BASE_URL + "payment/get/" + update.getPaymentId(),
                Payment.class
        );

        System.out.println(res.getBody());
        assertNotNull(res.getBody());
        assertEquals("1500.00", res.getBody().getPaymentAmount());
        assertEquals("Completed", res.getBody().getPaymentStatus());
    }


    @Test
    @Order(4)
    void delete() {
        String url = BASE_URL + "/payment/delete/" + payment1.getPaymentId();
        restTemplate.delete(url);

        ResponseEntity<Payment> res = restTemplate.getForEntity(
                BASE_URL + "/payment/get/" + payment1.getPaymentId(),
                Payment.class
        );
        System.out.println(res.getBody());
        System.out.println("Deleted payment with ID: " + payment1.getPaymentId());
        assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());
    }

    @Test
    @Order(5)
    void getAll() {
        String url = BASE_URL + "payment/get/all";
        ResponseEntity<Payment[]> res = restTemplate.getForEntity(url, Payment[].class);
        assertNotNull(res.getBody());
        System.out.println("\nAll Payments:");
        for (Payment p : res.getBody()) {
            System.out.println(p);
        }
    }
}
