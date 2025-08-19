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

    @FXML private TextField reservationIdField; // To display ID, likely disabled for editing
    @FXML private TextField startTimeField;
    @FXML private TextField endTimeField;

    private final ReservationService reservationService;
    private Stage stage;
    private Reservation reservationToEdit; // The reservation object passed from the main controller

    @Autowired
    public EditReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // ID field is often non-editable
        reservationIdField.setEditable(false);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    // Method to set the reservation to be edited and populate fields
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
                // use builder setters
                reservationToEdit.setReservationDateTimeStart(newStartTime);
                reservationToEdit.setReservationDateTimeEnd(newEndTime);

                try {
                    reservationService.update(reservationToEdit);
                    System.out.println("Reservation updated: " + reservationToEdit);
                    stage.close(); // Close the modal window
                } catch (Exception e) {
                    e.printStackTrace();
                    // In a real app, show an error message to the user (e.g., using a Label or Alert)
                    System.out.println("Error updating reservation: " + e.getMessage());
                }
            } else {
                // In a real app, show a message that fields are empty
                System.out.println("Please fill in all fields.");
            }
        }
    }

    @FXML
    private void cancel() {
        stage.close(); // Close the modal window without saving
    }
}
