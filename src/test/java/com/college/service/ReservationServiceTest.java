package com.college.service;

import com.college.domain.Reservation;
import com.college.repository.ReservationRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReservationServiceTest {
    @Autowired
    ReservationService service;

//    ReservationRepository repo;
//    @Autowired
//    public ReservationServiceTest(ReservationRepository reservationRepository){
//        this.repo = reservationRepository;
//    }
    // -------------------------------------

    static Reservation reservation1 = new Reservation.Builder(
            1, "12:00", "14:00")
            .build();

    // -------------------------------------

    @Test
    @Order(1)
    void create() {
//        Reservation savedObj = service.create(reservation1);
//        System.out.println(savedObj);
//        assertNotNull(savedObj);

        Reservation reservationX = new Reservation("09:00", "11:00");
        Reservation savedObj1 = service.create(reservationX);
        System.out.println(savedObj1);
        assertNotNull(savedObj1);
    }

    @Test
    @Order(2)
    void read() {
        System.out.println(reservation1);
        System.out.println(reservation1.getReservationId());
        Reservation readObj = service.read(reservation1.getReservationId());
        System.out.println(readObj);
        assertNull(readObj);
    }

    @Test
    @Order(3)
    void update() {
        Reservation reservation2 = new Reservation.Builder(
                2, "14:00", "16:00")
                .build();
        Reservation updateObj = service.update(reservation2);
        System.out.println(updateObj);
        assertNotNull(updateObj);
    }

    @Test
    @Order(4)
    void delete() {
        Boolean deleteObj = service.delete(reservation1.getReservationId());
        System.out.println(deleteObj);
        assertTrue(deleteObj);
    }

    @Test
    @Order(5)
    void getAll() {
        List<Reservation> reservations = service.getAll();
        System.out.println(reservations);
        assertNotNull(reservations);
    }

}