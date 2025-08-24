package com.college.controller;

import com.college.domain.Guest;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class AddEditGuestDialog extends Dialog<Guest> {

    private final TextField txtID = new TextField();
    private final TextField txtName = new TextField();
    private final TextField txtSurname = new TextField();
    private final TextField txtEmail = new TextField();
    private final TextField txtContact = new TextField();
    private final ComboBox<String> cbPayment = new ComboBox<>();

    public AddEditGuestDialog(Guest guest) {
        setTitle(guest == null ? "Add Guest" : "Edit Guest");

        // Custom header label
        // Custom header label - centered
        Label headerLabel = new Label(guest == null ? "Add Guest" : "Edit Guest");
        headerLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        HBox headerBox = new HBox(headerLabel);
        headerBox.setAlignment(Pos.CENTER); // center alignment
        headerBox.setPadding(new Insets(0, 0, 10, 0)); // optional bottom padding

        getDialogPane().setHeader(headerBox);

        // Buttons
        ButtonType submitButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        getDialogPane().getButtonTypes().addAll(submitButtonType, cancelButtonType);

        // Layout
        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(20));

        // Fields
        grid.add(new Label("Guest ID:"), 0, 0);
        grid.add(txtID, 1, 0);
        grid.add(new Label("Name:"), 0, 1);
        grid.add(txtName, 1, 1);
        grid.add(new Label("Surname:"), 0, 2);
        grid.add(txtSurname, 1, 2);
        grid.add(new Label("Email:"), 0, 3);
        grid.add(txtEmail, 1, 3);
        grid.add(new Label("Contact Number:"), 0, 4);
        grid.add(txtContact, 1, 4);
        grid.add(new Label("Payment Method:"), 0, 5);
        grid.add(cbPayment, 1, 5);

        // Style
        String textFieldStyle = "-fx-pref-width: 250px; -fx-padding: 6px;";
        txtID.setStyle(textFieldStyle);
        txtName.setStyle(textFieldStyle);
        txtSurname.setStyle(textFieldStyle);
        txtEmail.setStyle(textFieldStyle);
        txtContact.setStyle(textFieldStyle);
        cbPayment.setStyle(textFieldStyle);

        // Populate payment methods
        cbPayment.getItems().addAll("Cash", "Credit Card", "Debit Card", "EFT", "PayPal");
        cbPayment.setEditable(false); // Only allow selection from list

        // Pre-fill if editing
        if (guest != null) {
            txtID.setText(String.valueOf(guest.getGuestID()));
            txtID.setDisable(true);
            txtName.setText(guest.getName());
            txtSurname.setText(guest.getSurname());
            txtEmail.setText(guest.getEmail());
            txtContact.setText(guest.getContactNumber());
            cbPayment.setValue(guest.getPaymentDetails());
        }

        // Set content
        getDialogPane().setContent(grid);

        // Style dialog buttons
        getDialogPane().lookupButton(submitButtonType)
                .setStyle("-fx-background-color: #26AD00; -fx-text-fill: white;");
        getDialogPane().lookupButton(cancelButtonType)
                .setStyle("-fx-background-color: #C20010; -fx-text-fill: white;");

        // Result handling
        setResultConverter(dialogButton -> {
            if (dialogButton == submitButtonType) {
                return new Guest.GuestBuilder()
                        .setGuestID(Integer.parseInt(txtID.getText()))
                        .setName(txtName.getText())
                        .setSurname(txtSurname.getText())
                        .setContactNumber(txtContact.getText())
                        .setEmail(txtEmail.getText())
                        .setPaymentDetails(cbPayment.getValue()) // <-- Use selected payment method
                        .build();
            }
            return null;
        });
    }
}
