/*

Project:    Hotel-management system
Started:    11.03.25
Updated:    18.03.25
---

Members
-------
Name and surname    | Student id    | GitHub repo                                                       |
---------------------------------------------------------------------------------------------------------
Ammaar Swartland    | 230159478     | (original) https://github.com/ammaar0x01/ADP3_capstone_project    |
Zaid Theunissen     | 221084142     | https://github.com/zaid-xt/ADP3_capstone_project                  |
Joshua Twigg        | 222153881     | https://github.com/JoshuaTwigg/ADP3_capstone_project              |
Talia Theresa Smuts | 221126082     | https://github.com/Taliasmuts28/ADP3_capstone_project             |
Muaath Slamong      | 230074138     | https://github.com/MuaathSlamong-alt/ADP3_capstone_project        |
---------------------------------------------------------------------------------------------------------
*/

package com.college;

import com.college.domain.Reservation;
import com.college.factory.*;
import com.college.repository.IReservationRepo;
import com.college.repository.ReservationRepo;

public class Main {
    /**
     * Code related to the Reservation-entity
     * */
    public static void entityReservation(){
        System.out.println("\n---Reservation---");
        Reservation r0 = new Reservation.Builder()
                .build();
        Reservation r1 = new Reservation.Builder()
                .setReservationId(1)
                .build();
        System.out.println(r0);
        System.out.println(r1);

        System.out.println("\n---Reservation factory---");
        Reservation r11 = ReservationFactory.createReservation();
        Reservation r12 = ReservationFactory.createReservation(100, "start", "end");
        System.out.println(r11);
        System.out.println(r12);

        System.out.println("\n---Reservation repository---");
        IReservationRepo repoReservation = ReservationRepo.getRepo();
        repoReservation.create(r0);
        repoReservation.create(r1);
        repoReservation.create(r12);

        repoReservation.read(100);

        repoReservation.delete(1);

        /// added again
        repoReservation.create(r1);

        System.out.println();
        repoReservation.update(r1);

        // System.out.println(repoReservation.getAll());

        System.out.println();
        int index = 0;
//        repoReservation.getAll().forEach((obj) -> System.out.println(obj));
        // or
        for(Reservation r : repoReservation.getAll()){
            System.out.println(index);
            System.out.println(r);
            System.out.println();
            index++;
        }
    }
    // -----------------------------------

    public static void main(String[] args) {
        System.out.println("\nTesting");
        System.out.println("=======");

        entityReservation();
    }
}
