<<<<<<< HEAD
=======
/*
File name:  ReservationFactoryTest.java (TESTS for factory Class)
Author:     Ammaar
Started:    24.03.25
updated:    24.03.25
*/

>>>>>>> pre-master
package com.college.factory;

import com.college.domain.Reservation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
<<<<<<< HEAD
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
=======

import static org.junit.jupiter.api.Assertions.*;

>>>>>>> pre-master
class ReservationFactoryTest {
    @AfterEach
    void line(){
        System.out.println("----------------------------");
    }

    @Test
<<<<<<< HEAD
    void createReservationT1(){
        System.out.println("\nTest2 (object creation; empty strings; id=0)");
        Reservation obj = ReservationFactory.createReservation(0, "", "");
        System.out.println(obj);

        assertNotNull(obj, "\nObject is null");

=======
    void createReservationT1() {
        System.out.println("\nTest1 (object creation; no parameters)");
        Reservation obj = ReservationFactory.createReservation();
        assertNotNull(obj, "\nObject is null");
>>>>>>> pre-master
    }

    @Test
    void createReservationT2(){
<<<<<<< HEAD
        System.out.println("\nTest3 (object creation; empty strings)");
        Reservation obj = ReservationFactory.createReservation(111, "", "");
        System.out.println(obj);

=======
        System.out.println("\nTest2 (object creation; empty strings; id=0)");
        Reservation obj = ReservationFactory.createReservation(0, "", "");
>>>>>>> pre-master
        assertNotNull(obj, "\nObject is null");
    }

    @Test
    void createReservationT3(){
<<<<<<< HEAD
        System.out.println("\nTest4 (object creation)");
        Reservation obj = ReservationFactory.createReservation(1, "8am", "10am");
        System.out.println(obj);

=======
        System.out.println("\nTest3 (object creation; empty strings)");
        Reservation obj = ReservationFactory.createReservation(111, "", "");
>>>>>>> pre-master
        assertNotNull(obj, "\nObject is null");
    }

    @Test
<<<<<<< HEAD
    void equality(){
=======
    void createReservationT4(){
        System.out.println("\nTest4 (object creation)");
        Reservation obj = ReservationFactory.createReservation(1, "8am", "10am");
        assertNotNull(obj, "\nObject is null");
    }

    @Test
    void equalityT1(){
>>>>>>> pre-master
        System.out.println("\nTest5 (testing equality)");
        Reservation obj1 = ReservationFactory.createReservation(12, "9am", "11am");
        Reservation obj2 = ReservationFactory.createReservation(12, "9am", "11am");

        System.out.println(obj1.hashCode());
        System.out.println(obj2.hashCode());
        assertNotEquals(obj1, obj2, "\nObjects are equal");
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> pre-master
