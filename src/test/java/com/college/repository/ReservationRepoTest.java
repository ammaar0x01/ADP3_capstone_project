package com.college.repository;

import com.college.domain.Reservation;
import com.college.factory.ReservationFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReservationRepoTest {

    static IReservationRepo testReservationRepo;

    // using for the CRUD tests
//    Reservation reservation1 = ReservationFactory.createReservation(100, "start", "end");
    Reservation reservation1 = ReservationFactory.createReservation(100, "10am", "12pm");

    @BeforeAll
    static void init(){
        System.out.println("\nTesting 'ReservationRepo.java'");
        System.out.println("------------------------------");
        testReservationRepo = ReservationRepo.getRepo();

    }

    @AfterEach
    void line(){
        System.out.println("----------------------------");
    }

    @Test
    void addT1(){
        System.out.println("\nTest1 (Creating and adding to repo)");

        Reservation created = testReservationRepo.create(reservation1);
        assertEquals(reservation1.getReservationId(), created.getReservationId(), "Ids do not match");


        System.out.println(testReservationRepo.getAll());
        System.out.println(testReservationRepo.getAll().size());
   }

    @Test
    void readT1(){
        System.out.println("\nTest2 (Reading)");

        Reservation read = testReservationRepo.read(reservation1.getReservationId());
        //System.out.println(reservation1.getReservationId());
        assertNull(read, "\nObject not is null");


    }

    @Test
    void deleteT1(){
        System.out.println("\nTest3 (Deletion)");

        boolean successfulDeletion = testReservationRepo.delete(reservation1.getReservationId());
        assertTrue(successfulDeletion, "\nDeletion unsuccessful");
    }

    @Test
    void updateT1(){
        System.out.println("\nTest4 (Updating)");

        Reservation reservation2 = ReservationFactory.createReservation(101, "12pm", "2pm");

        var successfulUpdate = testReservationRepo.update(reservation1);
        assertNull(successfulUpdate, "\nUpdate unsuccessful");
    }

    @Test
    void getAllT1(){
        System.out.println("\nTest5 (Get all objects; empty)");

        System.out.println(testReservationRepo.getAll());
        assertNotNull(testReservationRepo.getAll(), "\nArrayList is null");
    }

    @Test
    void getAllT2(){
        System.out.println("\nTest6 (Get all objects)");

        Reservation created = testReservationRepo.create(reservation1);
        assertEquals(reservation1.getReservationId(), created.getReservationId(), "Ids do not match");

        System.out.println();
        System.out.println(testReservationRepo.getAll());
        System.out.println(testReservationRepo.getAll().size());
        assertNotNull(testReservationRepo.getAll(), "\nArrayList is null");
    }
}