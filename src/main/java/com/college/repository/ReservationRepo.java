/*
File name:  ReservationRepo.java
Author:     Ammaar
Started:    12.03.25
updated:    18.03.25
*/

package com.college.repository;

import com.college.domain.Reservation;

import java.util.ArrayList;

public class ReservationRepo implements IReservationRepo {
//    private final HashMap<Integer, Reservation> Reservations = new HashMap<>();
//    private final ArrayList<Reservation> reservations = new ArrayList<>();
    // -----------------------------


    // for singleton pattern
    // checking if another instance of the class was already created
    private static IReservationRepo repo = null;
    private static int counter;
    public static IReservationRepo getRepo(){
        counter++;
        System.out.println("\ninstances: " + counter);


        if(repo == null){
            System.out.println("Creating object...");
            repo = new ReservationRepo();
            return repo;
        }
        System.out.println("Object already exists");
        return repo;
    }

    private final ArrayList<Reservation> reservations;
    private ReservationRepo(){
        this.reservations = new ArrayList<>();
    }
    // -----------------------------

    @Override
//    public Reservation add(Reservation obj) {
//    public Reservation save(Reservation obj) {
    public Reservation create(Reservation obj) {
        if (this.reservations.add(obj)){
            System.out.println(obj);
            System.out.println(" * added to arraylist");
            return obj;
        }
        return null;
    }

    /**
     * Returns an object at a specific index
     * */
    @Override
    // find()
//    public Reservation get(Integer reservationId) {
//    public Reservation getObject(Integer reservationId) {
    public Reservation read(Integer reservationId) {

//        System.out.println(this.reservations.get(integer));
//        return this.reservations.get(integer);
//        return null;


//        System.out.println("sssssss");
//        System.out.println(reservations.get(0).getReservationId());

        // or use loop
        for (int a=0; a<this.reservations.size(); a++){

            if (reservationId == reservations.get(a).getReservationId()){
                System.out.println(" * object found");
                return reservations.get(a);
            }
        }
        System.out.println(" * object not found");
        return null;
    }

    @Override
    public boolean delete(Integer reservationId) {
        Reservation reservationToDel = this.read(reservationId);
        if (reservationToDel == null){
            return false;
        }
        System.out.println(" * removed from arraylist");
        return this.reservations.remove(reservationToDel);

        // return this.read(integer) ? this.reservations.remove(reservationToDel) : null;

    }

    @Override
    public Reservation update(Reservation obj) {
        int reservationId = obj.getReservationId();
        Reservation reservationObj = this.read(reservationId);

        if (reservationObj == null){
            return null;
        }

        boolean success = this.delete(reservationId);
        if (success){
            // adding new object
            System.out.println(" * updated");
            if (this.reservations.add(reservationObj)){
                return reservationObj;
            }
        }

        return null;
    }
    // -----------------------------

    @Override
    public ArrayList<Reservation> getAll() {
        return this.reservations;
    }
}
