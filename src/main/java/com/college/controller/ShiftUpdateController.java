package com.college.controller;

import com.college.domain.Shift;
import com.college.service.ShiftService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Controller
public class ShiftUpdateController {

    @FXML
    private DatePicker datePicker;
    @FXML
    private Spinner<Integer> spinnerStartHour;
    @FXML
    private Spinner<Integer> spinnerStartMinute;
    @FXML
    private Spinner<Integer> spinnerEndHour;
    @FXML
    private Spinner<Integer> spinnerEndMinute;
    @FXML
    private CheckBox chkOvertime;
    @FXML
    private Button cancelButton;

    @Autowired
    private ShiftService shiftService;

    private Shift shift;

    @FXML
    public void initialize() {
        // Default date = today
        datePicker.setValue(LocalDate.now());

        // Configure spinners for 24-hour format
        spinnerStartHour.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 8));
        spinnerStartMinute.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
        spinnerEndHour.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 17));
        spinnerEndMinute.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));

        // Debug logs
        System.out.println("ShiftUpdateController initialized");
        System.out.println("ShiftService injected: " + (shiftService != null));
    }

    public void setShift(Shift shift) {
        this.shift = shift;
        if (shift != null) {
            datePicker.setValue(shift.getShiftDay());
            spinnerStartHour.getValueFactory().setValue(shift.getShiftStartTime().getHour());
            spinnerStartMinute.getValueFactory().setValue(shift.getShiftStartTime().getMinute());
            spinnerEndHour.getValueFactory().setValue(shift.getShiftEndTime().getHour());
            spinnerEndMinute.getValueFactory().setValue(shift.getShiftEndTime().getMinute());
            chkOvertime.setSelected(shift.getShiftOvertime());
        }
    }

    @FXML
    private void updateShift() {
        if (!showConfirmation("Confirm Update", "Are you sure you want to save changes to this shift?")) {
            return; // User cancelled
        }

        try {
            if (datePicker.getValue() == null) {
                showAlert("Please select a shift date");
                return;
            }

            LocalDate date = datePicker.getValue();
            LocalTime start = LocalTime.of(spinnerStartHour.getValue(), spinnerStartMinute.getValue());
            LocalTime end = LocalTime.of(spinnerEndHour.getValue(), spinnerEndMinute.getValue());
            boolean overtime = chkOvertime.isSelected();

            // Update shift entity
            shift.setShiftDay(date);
            shift.setShiftStartTime(start);
            shift.setShiftEndTime(end);
            shift.setShiftOvertime(overtime);

            shiftService.update(shift);
            showAlert(Alert.AlertType.INFORMATION, "Shift updated successfully!");

            closeWindow();

        } catch (Exception ex) {
            showAlert("Error: " + ex.getMessage());
        }
    }

    @FXML
    private void handleCancel() {
        if (showConfirmation("Confirm Cancel", "Are you sure you want to cancel? All unsaved changes will be lost.")) {
            closeWindow();
        }
    }

    private void closeWindow() {
        ((Stage) cancelButton.getScene().getWindow()).close();
    }

    private void showAlert(String message) {
        showAlert(Alert.AlertType.WARNING, message);
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type, message, ButtonType.OK);
        alert.showAndWait();
    }

    private boolean showConfirmation(String title, String message) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle(title);
        confirm.setHeaderText(message);
        confirm.setContentText("Click OK to confirm or Cancel to go back.");
        Optional<ButtonType> result = confirm.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}
