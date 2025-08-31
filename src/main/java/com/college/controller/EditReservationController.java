package com.college.controller;

import com.college.domain.Reservation;
import com.college.service.ReservationService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class EditReservationController implements Initializable {

    @FXML private TextField reservationIdField;
    // or
//    @FXML private Label reservationIdField;
    @FXML private TextField startTimeField;
    @FXML private TextField endTimeField;

    private final ReservationService reservationService;
    private Stage stage;
    private Reservation reservationToEdit;

    @Autowired
    public EditReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reservationIdField.setEditable(false);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setReservationToEdit(Reservation reservation) {
        this.reservationToEdit = reservation;
        if (reservationToEdit != null) {
            reservationIdField.setText(String.valueOf(reservationToEdit.getReservationId()));
            startTimeField.setText(reservationToEdit.getReservationDateTimeStart());
            endTimeField.setText(reservationToEdit.getReservationDateTimeEnd());
        }
    }

    @FXML
    private void saveReservation() {
        if (reservationToEdit != null) {
            String newStartTime = startTimeField.getText();
            String newEndTime = endTimeField.getText();

            if (!newStartTime.isEmpty() && !newEndTime.isEmpty()) {
                // use builder setters?
                reservationToEdit.setReservationDateTimeStart(newStartTime);
                reservationToEdit.setReservationDateTimeEnd(newEndTime);

                try {
                    reservationService.update(reservationToEdit);
                    System.out.println("Reservation updated: " + reservationToEdit);
                    stage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                     // ...
                    System.out.println("Error updating reservation: " + e.getMessage());
                }
            } else {
                // ...show a message that fields are empty
                System.out.println("Please fill in all fields.");
            }
        }
    }

    @FXML
    private void cancel() {
        stage.close();
    }
}
