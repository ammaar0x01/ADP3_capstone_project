package com.college.utilities;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class Tools {

    // --------------------------------------------
    // FOR JFX
    // MouseEvent
//    public void handleHoverEnter(MouseEvent event) {
    public static void handleHoverEnter(MouseEvent event) {
        System.out.println("\nhover in");
        Button hoveredButton = (Button) event.getSource();
        hoveredButton.setStyle(
                "-fx-background-color: black; " +
                "-fx-text-fill: white;"
        );
    }

    public void handleHoverExit(MouseEvent event) {
        System.out.println("hover out");
        Button hoveredButton = (Button) event.getSource();
        hoveredButton.setStyle(
                "-fx-background-color:  rgba(69, 150, 255, 1);" +
                "-fx-text-fill: white;"
        );
    }
    // --------------------------------------------

}
