/*
File name:  ReservationFactoryTest.java (TESTS for factory Class)
Author:     Ammaar
Started:    24.03.25
updated:    23.05.25
*/

package com.college.factory;

import com.college.domain.Reservation;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservationFactoryTest {

    @Test
    void createReservationT1(){
        System.out.println("\nTest1 (object creation; empty strings; id=0)");
        Reservation obj = ReservationFactory.createReservation(0, "", "");
        assertNotNull(obj, "\nObject is null");
    }

    @Test
    void createReservationT2(){
        System.out.println("\nTest2 (object creation; empty strings)");
        Reservation obj1 = ReservationFactory.createReservation(111, "", "");
        assertNotNull(obj1, "\nObject is null");
    }


    @Test
    void equalityT(){
        System.out.println("\nTest (testing equality)");
        Reservation obj1 = ReservationFactory.createReservation(12, "9am", "11am");
        Reservation obj2 = ReservationFactory.createReservation(12, "9am", "11am");

        System.out.println(obj1.hashCode());
        System.out.println(obj2.hashCode());
        assertNotEquals(obj1, obj2, "\nObjects are equal");
    }
}
