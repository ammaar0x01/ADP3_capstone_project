package com.college.controller;

import com.college.domain.Guest;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class AddEditGuestDialog extends Dialog<Guest> {

    private final TextField txtID = new TextField();
    private final TextField txtName = new TextField();
    private final TextField txtSurname = new TextField();
    private final TextField txtEmail = new TextField();
    private final TextField txtContact = new TextField();
    private final TextField txtPayment = new TextField();

    public AddEditGuestDialog(Guest guest) {
        setTitle(guest == null ? "Add Guest" : "Edit Guest");

        ButtonType submitButtonType = new ButtonType("Submit", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(submitButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

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
        grid.add(new Label("Payment Details:"), 0, 5);
        grid.add(txtPayment, 1, 5);

        if (guest != null) {
            txtID.setText(String.valueOf(guest.getGuestID()));
            txtID.setDisable(true);
            txtName.setText(guest.getName());
            txtSurname.setText(guest.getSurname());
            txtEmail.setText(guest.getEmail());
            txtContact.setText(guest.getContactNumber());
            txtPayment.setText(guest.getPaymentDetails());
        }

        getDialogPane().setContent(grid);

        setResultConverter(dialogButton -> {
            if (dialogButton == submitButtonType) {
                return new Guest.GuestBuilder()
                        .setGuestID(Integer.parseInt(txtID.getText()))
                        .setName(txtName.getText())
                        .setSurname(txtSurname.getText())
                        .setContactNumber(txtContact.getText())
                        .setEmail(txtEmail.getText())
                        .setPaymentDetails(txtPayment.getText())
                        .build();
            }
            return null;
    });
    }
}
