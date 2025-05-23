package com.college.controller;

import com.college.domain.Reservation;
import com.college.repository.ReservationRepository;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.apache.coyote.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReservationControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    static final String BASE_URL = "http://localhost:8080/";

    static Reservation reservation1;
    @BeforeAll
    static void setup(){
        reservation1 = new Reservation("10:00", "12:00");
    }
    // ---------------------------

    @Test
    @Order(1)
    void a_create() {
        String url = BASE_URL + "/reservation/create";
        Reservation created = restTemplate.postForObject(url, reservation1, Reservation.class);
        System.out.println(created);
        assertNotNull(created);
        assertEquals(reservation1.getReservationId(), created.getReservationId());
    }

    @Test
    @Order(2)
    void b_read() {
        String url = BASE_URL + "/read/" + reservation1.getReservationId();
        ResponseEntity<Reservation> res = restTemplate.getForEntity(url, Reservation.class);
        System.out.println(res.getBody());
        assertEquals(reservation1.getReservationId(), res.getBody().getReservationId());
    }

    @Test
    @Order(3)
    void c_update() {
        Reservation update = new Reservation.Builder()
                .copy(reservation1)
                .setReservationDateTimeStart("15:00")
                .setReservationDateTimeEnd("17:00")
                .build();

        String url = BASE_URL + "/reservation/update";
        restTemplate.put(url, update);

        ResponseEntity<Reservation> res = restTemplate.getForEntity(
                BASE_URL + "/read" + update.getReservationId(),
                Reservation.class
        );

        System.out.println(res.getBody());
        assertNotNull(res.getBody());
//        assertEquals(res.getBody(), HttpStatus.OK);
    }

    @Test
    @Order(4)
    void d_delete() {
        String url = BASE_URL + "/delete/" + reservation1.getReservationId();
        restTemplate.delete(url);

        ResponseEntity<Reservation> res = restTemplate.getForEntity(
                BASE_URL + "/read/" + reservation1.getReservationId(),
                Reservation.class
        );
        System.out.println(res.getBody());
        System.out.println("Deleted: " + reservation1.getReservationId());
//        System.out.println("Deleted: true");
        assertNull(res.getBody());
        assertEquals(res.getStatusCode(), HttpStatus.NOT_FOUND);

    }

    @Test
    @Order(5)
    void e_getAll() {
        String url = BASE_URL + "/get/reservations";
        ResponseEntity<Reservation[]> res = restTemplate.getForEntity(url, Reservation[].class);
        assertNotNull(res.getBody());
        System.out.println("\nAll Reservations");
        for (Reservation r : res.getBody()){
            System.out.println(r);
        }

    }

}