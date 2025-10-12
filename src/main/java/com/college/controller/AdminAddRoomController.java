package com.college.controller;

import com.college.domain.CustomRoom;
import com.college.domain.Room;
import com.college.factory.CustomRoomFactory;
import com.college.factory.RoomFactory;
import com.college.service.CustomRoomService;
import com.college.service.RoomService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.util.Optional;

@Component
public class AdminAddRoomController {

    @FXML private ComboBox<String> roomIdComboBox;
    @FXML private TextField roomTypeField;
    @FXML private TextField pricePerNightField;
    @FXML private ComboBox<String> availabilityComboBox;
    @FXML private TextField featuresField;

    @FXML
    private ImageView roomImageView;

    @Autowired
    CustomRoomService customRoomService;

    private File selectedImageFile;

    @FXML
    public void handleUploadImage(ActionEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Room Image");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
            );

            File file = fileChooser.showOpenDialog(null);

            if (file != null) {
                selectedImageFile = file;
                Image image = new Image(file.toURI().toString());
                roomImageView.setImage(image);
            } else {
                showAlert("No file selected", "Please choose an image file.");
            }
        } catch (Exception e) {
            showAlert("Error", "Unable to upload image: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void addRoomToDatabase() {
        try {
            // Validate all inputs first
            if (roomIdComboBox.getValue() == null || roomIdComboBox.getValue().isEmpty()) {
                showAlert("Validation Error", "Please select a Room ID.");
                return;
            }

            if (roomTypeField.getText().isEmpty()) {
                showAlert("Validation Error", "Please enter a Room Type.");
                return;
            }

            if (pricePerNightField.getText().isEmpty()) {
                showAlert("Validation Error", "Please enter a Price per Night.");
                return;
            }

            if (availabilityComboBox.getValue() == null || availabilityComboBox.getValue().isEmpty()) {
                showAlert("Validation Error", "Please select Availability.");
                return;
            }

            if (featuresField.getText().isEmpty()) {
                showAlert("Validation Error", "Please enter Room Features.");
                return;
            }

            // Parse the inputs
            int roomID = Integer.parseInt(roomIdComboBox.getValue());
            String roomType = roomTypeField.getText();
            float pricePerNight = Float.parseFloat(pricePerNightField.getText());
            boolean availability = "Available".equalsIgnoreCase(availabilityComboBox.getValue());
            String features = featuresField.getText();

            byte[] imageBytes = null;
            String imageStatus = "No image";
            if (selectedImageFile != null) {
                imageBytes = Files.readAllBytes(selectedImageFile.toPath());
                imageStatus = "Image uploaded (" + selectedImageFile.getName() + ")";
            }

            // Show confirmation as the LAST step with all room details
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirm Room Addition");
            confirmationAlert.setHeaderText("Please review the room details before adding:");
            confirmationAlert.setContentText(
                    "Are you sure you want to add this room to the database?\n\n" +
                            "Room Details:\n" +
                            "• Room ID: " + roomID + "\n" +
                            "• Room Type: " + roomType + "\n" +
                            "• Price per Night: $" + String.format("%.2f", pricePerNight) + "\n" +
                            "• Availability: " + (availability ? "Available" : "Not Available") + "\n" +
                            "• Features: " + features + "\n" +
                            "• Image: " + imageStatus + "\n\n" +
                            "Click OK to add this room to the database."
            );

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Create and save the room only after confirmation
                CustomRoom customRoom = CustomRoomFactory.createCustomRoom(
                        roomID, roomType, pricePerNight, availability, features, imageBytes
                );

                customRoomService.create(customRoom);
                showSuccessAlert("Room added successfully!");
                clearForm();
            } else {
                showAlert("Cancelled", "Room addition was cancelled. No changes were made.");
            }

        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter valid numeric values for Room ID and Price.");
        } catch (Exception e) {
            showAlert("Error", "Failed to add room: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void clearForm() {
        roomIdComboBox.setValue(null);
        roomTypeField.clear();
        pricePerNightField.clear();
        availabilityComboBox.setValue(null);
        featuresField.clear();
        roomImageView.setImage(null);
        selectedImageFile = null;
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}