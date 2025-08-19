package com.college.views;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URI;

// help
// exit functionality
// https://www.youtube.com/watch?v=exIQqcQ0lzI


public class ControllerD1 {

    @FXML
    private StackPane mainFrame;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label output_message;

    @FXML
    private Pane pane1;

    @FXML
    private Pane pane2;
    // --------------------------------------------

    //
//
//    ControllerD1(){
//        pane1.setVisible(true);
//    }
//
    @FXML
    protected void initialise() {
        pane1.setVisible(true);
        pane2.setVisible(false);
        System.out.println("Initial settings complete.");
    }

    @FXML
    private void openLink() {
//    private void openLink(ActionEvent event) {
        System.out.println("Attempting to open URL...");

        try {
            // Replace with the actual URL you want to open
            String url = "https://www.google.com";
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions, e.g., show an alert if the browser can't be opened
        }
    }

    @FXML
//    private void closeWindow(){
//        private void handleCloseButtonAction(ActionEvent event) {
    private void handleCloseButtonAction() {


        // Get the Stage from the button's scene
////            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        Stage stage = (Stage) ((Node) event.getSource().getWindow();
//
//            // Close the stage
//            stage.close();


        // Get the Stage from the event source
//        Node source = (Node) event.getSource();
//        Stage stage = (Stage) source.getNodeName();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Exit");
        alert.setHeaderText("You are about to close the app");
        alert.setContentText("Are you sure you want to close the app?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            Stage stage = (Stage) mainFrame.getScene().getWindow();
            System.out.println("Attempting to close the app...");
            stage.close();
        }

    }


    @FXML
    private void function1() {
        System.out.println("\nUsername: " + username.getText());
        System.out.println("Password: " + password.getText());

        output_message.setText("Username: " + username.getText());

        System.out.println("\nUsername length -> " + username.getText().length());
        pane1.setVisible(false);
        pane2.setVisible(true);

        // or
//        if (pane1.isVisible()) {
//            pane1.setVisible(false);
//            pane2.setVisible(true);
//        } else {
//            pane1.setVisible(true);
//            pane2.setVisible(false);
//        }

        // maybe put button outside of stacked pane
    }


    @FXML
    private void goback() {
        if (pane1.isVisible()) {
            pane1.setVisible(false);
            pane2.setVisible(true);
        } else {
            pane1.setVisible(true);
            pane2.setVisible(false);
        }

        // maybe put button outside of stacked pane
    }

    // or
//    @FXML
//    private void handleSwitchPane() {
//        if (pane1.isVisible()) { // Or check if pane1 is currently on top
//            pane2.toFront();
//        } else {
//            pane1.toFront();
//        }
//    }
}
