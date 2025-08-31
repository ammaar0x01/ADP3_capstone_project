/*
File name:  ReservationFactory.java (Factory class)
Author:     Ammaar
Started:    12.03.25
*/

package com.college.factory;

import com.college.domain.Reservation;
import com.college.utilities.Helper;

public class ReservationFactory {
    public static Reservation createReservation(
            String rTimeStart,
            String rTimeEnd
    ){
        if (Helper.isNullOrEmpty(rTimeStart) || Helper.isNullOrEmpty(rTimeEnd)) {
            return null;
        }

        return new Reservation(rTimeStart, rTimeEnd);
    }
    // overload
    public static Reservation createReservation(
            int rId,
            String rTimeStart,
            String rTimeEnd
    ){
        if (Helper.isNullOrEmpty(rTimeStart) || Helper.isNullOrEmpty(rTimeEnd)) {
            return null;
        }

        return new Reservation.Builder()
                .setReservationId(rId)
                .setReservationDateTimeStart(rTimeStart)
                .setReservationDateTimeEnd(rTimeEnd)
                .build();
    }
}
