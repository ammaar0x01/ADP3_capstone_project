package com.college.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import com.college.domain.Payment;
import com.college.service.PaymentService;

@Controller
public class PaymentUpdateController {

    @FXML private TextField amountField;
    @FXML private ChoiceBox<String> methodChoice;
    @FXML private ChoiceBox<String> statusChoice;
    @FXML private DatePicker datePicker;
    @FXML private Label paymentIdLabel;
    @FXML private Button updateButton;
    @FXML private Button cancelButton;

    @Autowired
    private PaymentService paymentService;
    private Payment payment;

    @FXML
    public void initialize() {
        methodChoice.getItems().addAll("Cash", "Card", "EFT");
        statusChoice.getItems().addAll("Pending", "Completed", "Failed");
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
        if (payment != null) {
            paymentIdLabel.setText("Payment #" + payment.getPaymentId());
            amountField.setText(String.valueOf(payment.getPaymentAmount()));
            methodChoice.setValue(payment.getPaymentMethod());
            statusChoice.setValue(payment.getPaymentStatus());
            datePicker.setValue(payment.getPaymentDate());
        }
    }

    @FXML
    private void updatePayment() {
        if (payment != null) {
            try {
                double amount = Double.parseDouble(amountField.getText().trim());
                String method = methodChoice.getValue();
                String status = statusChoice.getValue();
                LocalDate date = datePicker.getValue();

                if (method == null || status == null || date == null) {
                    showAlert("All fields are required!");
                    return;
                }

                payment.setPaymentAmount(amount);
                payment.setPaymentMethod(method);
                payment.setPaymentStatus(status);
                payment.setPaymentDate(date);

                paymentService.update(payment);
                showAlert(Alert.AlertType.INFORMATION, "Payment updated successfully");
                closeWindow();

            } catch (Exception ex) {
                showAlert("Error: " + ex.getMessage());
            }
        }
    }

    @FXML
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
}