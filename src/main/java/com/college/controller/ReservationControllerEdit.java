//package com.college.controller;
//
//import com.college.domain.Reservation;
//import com.college.service.ReservationService;
//import javafx.fxml.FXML;
//import javafx.scene.control.TextField;
//import javafx.stage.Stage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ReservationControllerEdit {
//
//    @FXML private TextField startTimeField;
//    @FXML private TextField endTimeField;
//
//    private final ReservationService reservationService;
//    private Stage stage;
//
//    @Autowired
//    public ReservationControllerEdit(ReservationService reservationService) {
//        this.reservationService = reservationService;
//    }
//
//    public void setStage(Stage stage) {
//        this.stage = stage;
//        this.startTimeField.setText("start");
//        this.endTimeField.setText("end");
//
//        Reservation r1 = reservationService.read(2);
//        System.out.println(r1);
//        System.out.println(r1.getReservationDateTimeEnd());
//        System.out.println(r1.getReservationDateTimeStart());
//
//    }
//
//    @FXML
//    private void editReservation() {
//        String startTime = startTimeField.getText();
//        String endTime = endTimeField.getText();
//
//        if (!startTime.isEmpty() && !endTime.isEmpty()) {
//            Reservation newReservation = new Reservation(startTime, endTime);
//            reservationService.create(newReservation);
//            System.out.println("New reservation saved: " + newReservation);
//
//            stage.close();
//        }
//        else {
//            System.out.println("Please fill in all fields.");
//        }
//    }
//
//    @FXML
//    private void cancel() {
//        stage.close();
//    }
//}
