package com.college.controller;

import com.college.MainFinal;
import com.college.domain.Guest;
import com.college.domain.Reservation;
import com.college.domain.Room;
import com.college.service.*;
import com.college.utilities.ApplicationContextProvider;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
public class ReservationUIController implements Initializable {

    @FXML private TableView<Reservation> reservationTable;
    @FXML private TableColumn<Reservation, Integer> reservationIdColumn;
    @FXML private TableColumn<Reservation, Integer> guestIdColumn;

    @FXML private TableColumn<Reservation, String> startTimeColumn;
    @FXML private TableColumn<Reservation, String> endTimeColumn;

    @FXML private TableColumn<Reservation, Integer> roomIdColumn;
    @FXML private TableColumn<Reservation, Integer> employeeIdColumn;

    @FXML private Button btnAdd;
    @FXML private Button btnEdit;
    @FXML private Button btnDelete;
    @FXML private Button btnSearch;
    @FXML private Button btnGoBack;
    @FXML private Button btnRefresh;

    @FXML private TextField searchbar;
    @FXML private Label labelFeedback;

    private Stage stage;
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Autowired
    PaymentService paymentService;

    @Autowired
    GuestService guestService;

    @Autowired
    EventUIServiceNaked eventService;

    @Autowired
    RoomService roomService;

    private final ReservationService reservationService;
    private ObservableList<Reservation> reservationList;

    @Autowired
    public ReservationUIController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    //FK GUEST SET HERE
    private Guest guest; // field to hold the actual Guest object

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reservationIdColumn.setCellValueFactory(new PropertyValueFactory<>("reservationId"));

        reservationIdColumn.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        guestIdColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getGuest().getGuestId()).asObject()
        );

        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("reservationDateTimeStart"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("reservationDateTimeEnd"));

        // Room ID column
        roomIdColumn.setCellValueFactory(cellData -> {
            Reservation r = cellData.getValue();
            Room room = r.getRoom();
            return new SimpleIntegerProperty(
                    room != null ? room.getRoomID() : 0
            ).asObject();
        });

        // Employee ID column
        employeeIdColumn.setCellValueFactory(cellData -> {
            Reservation r = cellData.getValue();
            Room room = r.getRoom();
            int empId = (room != null && room.getEmployee() != null) ? room.getEmployee().getEmployeeId() : 0;
            return new SimpleIntegerProperty(empId).asObject();
        });

        reservationList = FXCollections.observableArrayList();
        reservationTable.setItems(reservationList);

        loadReservationData();
    }

    @FXML
    private void loadReservationData() {
        labelFeedback.setText("");
        List<Reservation> reservations = reservationService.getAllWithRoomAndEmployee();
        reservationList.clear();
        reservationList.addAll(reservations);
        System.out.println("Data loaded into TableView. Total items: " + reservationList.size());
    }

    @FXML
    private void search(){
        labelFeedback.setText("");
        String searchText = searchbar.getText().trim();

        if (searchText.isEmpty()) {
            labelFeedback.setText("Please enter an ID to search.");
            loadReservationData();
            return;
        }

        try {
            int id = Integer.parseInt(searchText);
            Reservation reservation = reservationService.read(id);

            if (reservation == null){
                System.out.println("No record found for ID: " + id);
                labelFeedback.setText("No record found for ID: " + id);
                reservationList.clear();
            } else {
                reservationList.clear();
                reservationList.add(reservation);
                labelFeedback.setText("Record found for ID: " + id);
            }
        } catch (NumberFormatException e) {
            labelFeedback.setText("Invalid ID. Please enter a number.");
            System.out.println("Invalid search input: " + searchText);
        } finally {
            searchbar.setText("");
        }
    }

    @FXML
    private void getAll() {
        loadReservationData();
    }

    @FXML
    private void add() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dialog_boxes/add-reservation.fxml"));
            loader.setControllerFactory(MainFinal.getSpringContext()::getBean);

            Parent root = loader.load();
            AddReservationController addController = loader.getController();

            addController.setGuest(this.guest); // pass guest to modal

            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setTitle("Add New Reservation");
            modalStage.setScene(new Scene(root));
            addController.setStage(modalStage); // set modal stage

            if (stage != null) {
                stage.close();
            }

            modalStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            labelFeedback.setText("Error opening Add Reservation form.");
        }
    }

    @FXML
    private void delete() {
        Reservation selectedReservation = reservationTable.getSelectionModel().getSelectedItem();

        if (selectedReservation == null) {
            labelFeedback.setText("Please select a reservation to delete.");
            return;
        }

        // Show confirmation as the LAST step with reservation details
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirm Reservation Deletion");
        confirmationAlert.setHeaderText("Please confirm deletion:");
        confirmationAlert.setContentText(
                "Are you sure you want to delete this reservation?\n\n" +
                        "Reservation Details:\n" +
                        "• Reservation ID: " + selectedReservation.getReservationId() + "\n" +
                        "• Guest ID: " + selectedReservation.getGuest().getGuestId() + "\n" +
                        "• Room ID: " + (selectedReservation.getRoom() != null ? selectedReservation.getRoom().getRoomID() : "Not assigned") + "\n" +
                        "• Start Time: " + selectedReservation.getReservationDateTimeStart() + "\n" +
                        "• End Time: " + selectedReservation.getReservationDateTimeEnd() + "\n\n" +
                        "This action will also:\n" +
                        "• Delete associated payments\n" +
                        "• Delete associated guest record\n" +
                        "• Remove employee assignment from room\n" +
                        "• Delete associated events\n" +
                        "• Cannot be undone!\n\n" +
                        "Click OK to proceed with deletion."
        );

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            proceedWithDeleteReservation(selectedReservation);
        } else {
            labelFeedback.setText("Deletion cancelled.");
        }
    }

    private void proceedWithDeleteReservation(Reservation selectedReservation) {
        try {
            //get fk from parent and child methods
            int guestId = selectedReservation.getGuest().getGuestID();

            if (selectedReservation.getEvent() != null) {
                eventService.deleteByReservationId(selectedReservation.getReservationId());
            }

            // Nullify employee FK in the room linked to this reservation
            if (selectedReservation.getRoom() != null) {
                selectedReservation.getRoom().setEmployee(null);
                roomService.update(selectedReservation.getRoom()); // persist change
            }

            boolean deleted = reservationService.delete(selectedReservation.getReservationId());
            //delete from other tables at the same time too
            paymentService.deleteByGuestId(guestId);

            if (deleted) {
                labelFeedback.setText("Reservation ID: " + selectedReservation.getReservationId() + " deleted successfully.");
                //delete from other tables at the same time too
                guestService.delete(guestId);
                loadReservationData();

                // Show success message
                showSuccessAlert("Reservation deleted successfully!");
            } else {
                labelFeedback.setText("Failed to delete reservation ID: " + selectedReservation.getReservationId() + ".");
            }
        } catch (Exception e) {
            e.printStackTrace();
            labelFeedback.setText("Error deleting reservation: " + e.getMessage());
        }
    }

    @FXML
    private void update() {
        Reservation selectedReservation = reservationTable.getSelectionModel().getSelectedItem();

        if (selectedReservation == null) {
            labelFeedback.setText("Please select a reservation to edit.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dialog_boxes/edit-reservation.fxml")); // Use a dedicated FXML for editing
            loader.setControllerFactory(MainFinal.getSpringContext()::getBean);

            Parent root = loader.load();
            EditReservationController editController = loader.getController();

            editController.setReservationToEdit(selectedReservation);

            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setTitle("Edit Reservation (ID: " + selectedReservation.getReservationId() + ")");
            modalStage.setScene(new Scene(root));
            editController.setStage(modalStage);

            modalStage.showAndWait();

            // Show confirmation after the edit modal closes
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirm Reservation Update");
            confirmationAlert.setHeaderText("Update Completed");
            confirmationAlert.setContentText(
                    "Reservation ID: " + selectedReservation.getReservationId() + " has been updated.\n\n" +
                            "Click OK to refresh the data and view the changes."
            );

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                loadReservationData();
                showSuccessAlert("Reservation updated successfully!");
            }

        } catch (IOException e) {
            e.printStackTrace();
            labelFeedback.setText("Error opening Edit Reservation form.");
        }
    }

    private void showSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}