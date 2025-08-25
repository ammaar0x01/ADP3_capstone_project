package com.college.controller;

import com.college.domain.Room;
import com.college.factory.RoomFactory;
import com.college.service.RoomService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

//@RestController idk if this wil break old code.
// cause nowit needs to be @component to work with javafx ui
@Component
public class RoomController {

    @Autowired
    private RoomService roomService;

    private Room room;

    public RoomController(){}
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
        room = RoomFactory.createRoom(1, "Single", 100.0f, true, "WiFi, TV");
    }

    // Buttons
    @FXML private Button buttonVip;
    @FXML private Button buttonSuite;
    @FXML private Button buttonStandard;
    @FXML private Button buttonEconomy;

    // Editable Labels and TextFields for Price
    @FXML private Label priceLabel;
    @FXML private TextField priceTextField;

    // Editable Labels and TextFields for Room Type
    @FXML private Label typeLabel;
    @FXML private TextField typeTextField;

    // Editable Labels and TextFields for Availability
    @FXML private Label availabilityLabel;
    @FXML private TextField availabilityTextField;

    // Editable Labels and TextFields for Features
    @FXML private Label featuresLabel;
    @FXML private TextField featuresTextField;

    @FXML
    private void handleVipClick() {
        System.out.println("Button clicked!");
        buttonVip.setText("Clicked!");

        //get values from label, still needs specific label idk how yet event src?
        String priceText = priceLabel.getText();
        String typeText = typeLabel.getText();
        String availabilityText = availabilityLabel.getText();
        String featuresText = featuresLabel.getText();

        // Extract the actual values
        String roomType = typeText.replace("Room Type:", "").trim();
        float price = Float.parseFloat(priceText.replaceAll("[^\\d.]", "")); // removes "Price Per Night: R"
        boolean available = availabilityText.toLowerCase().contains("available");
        String features = featuresText.replace("Features:", "").trim();


        Room roomOne = RoomFactory.createRoom(
                51, roomType, price, available, features);

        System.out.println(roomOne.toString());
        roomService.create(roomOne);

    }

    @FXML
    private void handleSuiteClick() {
        System.out.println("Button clicked!");
        buttonSuite.setText("Clicked!");
    }

    @FXML
    private void handleStandardClick() {
        System.out.println("Button clicked!");
        buttonStandard.setText("Clicked!");
    }

    @FXML
    private void handleEconomyClick() {
        System.out.println("Button clicked!");
        buttonEconomy.setText("Clicked!");
    }

    // --- Toggle helper for editing Labels/TextFields ---
    private void toggleEdit(Label label, TextField textField) {
        boolean editing = textField.isVisible();
        label.setVisible(editing);
        label.setManaged(editing);
        textField.setVisible(!editing);
        textField.setManaged(!editing);
        if (!editing) {
            textField.requestFocus();
            textField.selectAll();
        }
    }

    // --- Edit Price ---
    @FXML
    private void editPrice(MouseEvent event) {
        ImageView clickedIcon = (ImageView) event.getSource();
        // The parent HBox of clicked icon
        HBox hbox = (HBox) clickedIcon.getParent();

        // Find the Label and TextField inside this HBox
        Label priceLabel = null;
        TextField priceTextField = null;
        for (Node node : hbox.getChildren()) {
            if (node instanceof Label) {
                priceLabel = (Label) node;
            } else if (node instanceof TextField) {
                priceTextField = (TextField) node;
            }
        }

        if (priceLabel != null && priceTextField != null) {
            // Extract current price (numbers only) from label text
            String currentPrice = priceLabel.getText().replaceAll("[^0-9]", "").trim();
            priceTextField.setText(currentPrice);

            // Toggle visibility: hide label, show textfield for editing
            priceLabel.setVisible(false);
            priceLabel.setManaged(false);
            priceTextField.setVisible(true);
            priceTextField.setManaged(true);

            priceTextField.requestFocus();
        }
    }

    @FXML
    private void priceTextFieldOnEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            TextField textField = (TextField) event.getSource();
            HBox hbox = (HBox) textField.getParent();

            Label priceLabel = null;
            for (Node node : hbox.getChildren()) {
                if (node instanceof Label) {
                    priceLabel = (Label) node;
                    break;
                }
            }

            if (priceLabel != null) {
                String newPrice = textField.getText().trim();
                priceLabel.setText("Price Per Night: R" + newPrice);

                // Toggle visibility back
                priceLabel.setVisible(true);
                priceLabel.setManaged(true);
                textField.setVisible(false);
                textField.setManaged(false);
            }
        }
    }

    @FXML
    private void editType(MouseEvent event) {
        ImageView clickedIcon = (ImageView) event.getSource();
        HBox hbox = (HBox) clickedIcon.getParent();

        Label typeLabel = null;
        TextField typeTextField = null;
        for (Node node : hbox.getChildren()) {
            if (node instanceof Label) {
                typeLabel = (Label) node;
            } else if (node instanceof TextField) {
                typeTextField = (TextField) node;
            }
        }

        if (typeLabel != null && typeTextField != null) {
            // Extract current type text (after colon)
            String currentType = typeLabel.getText().split(":")[1].trim();
            typeTextField.setText(currentType);

            typeLabel.setVisible(false);
            typeLabel.setManaged(false);
            typeTextField.setVisible(true);
            typeTextField.setManaged(true);

            typeTextField.requestFocus();
        }
    }

    @FXML
    private void editAvailability(MouseEvent event) {
        ImageView clickedIcon = (ImageView) event.getSource();
        HBox hbox = (HBox) clickedIcon.getParent();

        Label availabilityLabel = null;
        TextField availabilityTextField = null;
        for (Node node : hbox.getChildren()) {
            if (node instanceof Label) {
                availabilityLabel = (Label) node;
            } else if (node instanceof TextField) {
                availabilityTextField = (TextField) node;
            }
        }

        if (availabilityLabel != null && availabilityTextField != null) {
            // Extract current availability (after colon)
            String currentAvailability = availabilityLabel.getText().split(":")[1].trim();
            availabilityTextField.setText(currentAvailability);

            availabilityLabel.setVisible(false);
            availabilityLabel.setManaged(false);
            availabilityTextField.setVisible(true);
            availabilityTextField.setManaged(true);

            availabilityTextField.requestFocus();
        }
    }

    @FXML
    private void editFeatures(MouseEvent event) {
        ImageView clickedIcon = (ImageView) event.getSource();
        HBox hbox = (HBox) clickedIcon.getParent();

        Label featuresLabel = null;
        TextField featuresTextField = null;
        for (Node node : hbox.getChildren()) {
            if (node instanceof Label) {
                featuresLabel = (Label) node;
            } else if (node instanceof TextField) {
                featuresTextField = (TextField) node;
            }
        }

        if (featuresLabel != null && featuresTextField != null) {
            // Extract current features (after colon)
            String currentFeatures = featuresLabel.getText().split(":")[1].trim();
            featuresTextField.setText(currentFeatures);

            featuresLabel.setVisible(false);
            featuresLabel.setManaged(false);
            featuresTextField.setVisible(true);
            featuresTextField.setManaged(true);

            featuresTextField.requestFocus();
        }
    }



    @FXML
    private void typeTextFieldOnEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            TextField textField = (TextField) event.getSource();
            HBox hbox = (HBox) textField.getParent();

            Label typeLabel = null;
            for (Node node : hbox.getChildren()) {
                if (node instanceof Label) {
                    typeLabel = (Label) node;
                    break;
                }
            }

            if (typeLabel != null) {
                String newType = textField.getText().trim();
                if (!newType.isEmpty()) {
                    typeLabel.setText("Room Type: " + newType + " ");
                }

                // Toggle visibility
                typeLabel.setVisible(true);
                typeLabel.setManaged(true);
                textField.setVisible(false);
                textField.setManaged(false);
            }
        }
    }


    @FXML
    private void availabilityTextFieldOnEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            TextField textField = (TextField) event.getSource();
            HBox hbox = (HBox) textField.getParent();

            Label availabilityLabel = null;
            for (Node node : hbox.getChildren()) {
                if (node instanceof Label) {
                    availabilityLabel = (Label) node;
                    break;
                }
            }

            if (availabilityLabel != null) {
                String newAvailability = textField.getText().trim();
                if (!newAvailability.isEmpty()) {
                    availabilityLabel.setText("Availability: " + newAvailability);
                }

                availabilityLabel.setVisible(true);
                availabilityLabel.setManaged(true);
                textField.setVisible(false);
                textField.setManaged(false);
            }
        }
    }

    @FXML
    private void featuresTextFieldOnEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            TextField textField = (TextField) event.getSource();
            HBox hbox = (HBox) textField.getParent();

            Label featuresLabel = null;
            for (Node node : hbox.getChildren()) {
                if (node instanceof Label) {
                    featuresLabel = (Label) node;
                    break;
                }
            }

            if (featuresLabel != null) {
                String newFeatures = textField.getText().trim();
                if (!newFeatures.isEmpty()) {
                    featuresLabel.setText("Features: " + newFeatures + " ");
                }

                featuresLabel.setVisible(true);
                featuresLabel.setManaged(true);
                textField.setVisible(false);
                textField.setManaged(false);
            }
        }
    }


    @FXML
    private void nextPage(ActionEvent event) {
        System.out.println("Button clicked!");
        try {
            Parent page2Root = FXMLLoader.load(getClass().getResource("/_trash/pageTwo.fxml"));
            Scene currentScene = ((Node) event.getSource()).getScene();
            currentScene.setRoot(page2Root);
            System.out.println("Moving to new page");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        try {
            Parent mainViewRoot = FXMLLoader.load(getClass().getResource("/_trash/MainView.fxml"));
            Scene currentScene = ((Node) event.getSource()).getScene();
            currentScene.setRoot(mainViewRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // --- REST API Endpoints (Spring Boot) ---
//    @RequestMapping("/test")
//    Room save(Room room) {
//        return roomService.create(room);
//    }
//
//    @RequestMapping("/read")
//    String read(int id) {
//        roomService.read(id);
//        return "welcome";
//    }
//
//    @RequestMapping("/update")
//    String update(Room room) {
//        roomService.update(room);
//        return "welcome";
//    }
//
//    @RequestMapping("/delete")
//    String delete(int id) {
//        roomService.delete(id);
//        return "welcome";
//    }
}