package com.college.views;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControllerNew {
    @FXML
    private TextField input_name;

    @FXML
    private TextField input_surname;

    @FXML
    private TextField input_age;

    @FXML
    private Label temp;
//    ---------------------------------------------

    @FXML
    protected void func1() {
//    protected void func1(ActionEvent event) {
//        temp.setText("Your name is: " + input_name.getText());

        System.out.println(" " + input_name.getText());
        System.out.println(" " + input_surname.getText());
//        System.out.println(" " + input_age.getText());
        System.out.println(" " + input_age.getText(0, 2));
        System.out.println();

        
    }
}
