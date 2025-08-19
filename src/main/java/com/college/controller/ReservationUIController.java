//package com.college.controller;
//
//import com.college.domain.Reservation;
//import com.college.factory.ReservationFactory;
//import com.college.service.ReservationService;
//import com.college.utilities.ApplicationContextProvider;
//import com.college.utilities.Helper;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RestController;
//
//import org.springframework.context.ApplicationContext;
//
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.List;
//import java.util.ResourceBundle;
//
////@RestController/
//@Component
//public class ReservationUIController implements Initializable {
//
////    private ReservationService service;
////
////    @Autowired
////    public ReservationUIController(ReservationService reservationService){
////        service = reservationService;
////    }
//
//
//    @FXML private TableView<Reservation> reservationTable;
//    @FXML private TableColumn<Reservation, Integer> reservationIdColumn;
//    @FXML private TableColumn<Reservation, String> startTimeColumn;
//    @FXML private TableColumn<Reservation, String> endTimeColumn;
//
//    @FXML private Button btnAdd;
//    @FXML private Button btnEdit;
//    @FXML private Button btnDelete;
//    @FXML private Button btnSearch;
//    @FXML private Button btnGoBack;
//    @FXML private Button btnRefresh; // maybe remove this
//
//    @FXML private TextField searchbar;
//
//    @FXML private Label labelFeedback;
//    // ==============================================
//
//    private final ReservationService reservationService;
//    private ObservableList<Reservation> reservationList;
//
//    @Autowired
//    public ReservationUIController(ReservationService reservationService) {
//        this.reservationService = reservationService;
//    }
//    // ==============================================
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        // Step 2: Map columns to properties of the Reservation class
//        reservationIdColumn.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
////        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
////        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
//
//        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("reservationDateTimeStart"));
//        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("reservationDateTimeEnd"));
//
//
//        // Step 3: Initialize the ObservableList and bind it to the TableView
//        reservationList = FXCollections.observableArrayList();
//        reservationTable.setItems(reservationList);
//
//        // Fetch data from the database and populate the table
//        loadReservationData();
//    }
//
//    @FXML
//    private void loadReservationData() {
//        // Step 4: Call the service to get data
//        List<Reservation> reservations = reservationService.getAll();
//        reservationList.clear(); // Clear existing data
//        reservationList.addAll(reservations); // Add the new data to the list
//        System.out.println("Data loaded into TableView. Total items: " + reservationList.size());
//    }
//
//
//    @FXML
//    private void search(){
//        // search by id? search/filter by time/time-frame?
//        // remove white spaces
//        labelFeedback.setText("");
//
//        System.out.println("Search query=" +  searchbar.getText());
//        int id = Integer.parseInt(searchbar.getText().replace(" ", ""));
//
//
//        System.out.println("\nRecord");
//        System.out.println(reservationService.read(id));
//
//        Reservation reservation = reservationService.read(id);
////        if (Helper.isNullOrEmpty()) // maybe modify for objects
//
//        // make more complex? ex, detect if numbers were entered, etc...
//        if (reservation == null){
//            System.out.println("No record found");
//            labelFeedback.setText("No record found");
//        }
//        else{
////            labelFeedback.setText("");
//
//            reservationList.clear(); // Clear existing data
//            reservationList.add(reservation);
//        }
//
//
//        searchbar.setText("");
//
////        reservationList.addAll(reservations); // Add the new data to the list
//        System.out.println("Data loaded into TableView. Total items: " + reservationList.size());
//    }
//
//    @FXML
////    private List<Reservation> getAll() {
//    private void getAll() {
////        System.out.println("Button clicked!");
////        System.out.println("Search query=" +  searchbar.getText());
////        searchbar.setText("");
////
////        btnSearch.setText("Getting...");
//
//        System.out.println("\nAll data:");
//
//        for (Reservation r : reservationService.getAll()){
//            System.out.println(" - " + r);
//        }
////        System.out.println(service.getAll());
////        return reservationService.getAll();
//    }
//
////    @FXML
////    private void handleVipClick() {
////    private Reservation add() {
////        // reset other buttons
//////        btnEdit.setText("");
//////        btnDelete.setText("");
//////        btnSearch.setText("");
////
////        // temp
////        btnAdd.setText("Added data");
////
//////        Reservation reservation = new Reservation("10:00", "12:00");
////        Reservation reservation = ReservationFactory.createReservation(
////                "10:00",
////                "12:00"
////        );
////        System.out.println("\nAdding...");
////        System.out.println(reservation);
////        System.out.println(reservation.toString());
////
////        System.out.println(reservationService.create(reservation));
////        return reservationService.create(reservation);
////    }
//
//
//    @FXML
//    private void add() {
//        try {
//            // Load the FXML for the modal window
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/add-reservation.fxml"));
////            loader.setControllerFactory(ApplicationContext.getInstance()::getBean);
//            loader.setControllerFactory(ApplicationContextProvider.getApplicationContext()::getBean);
//
//            Parent root = loader.load();
//
//            // Get the controller for the new scene
//            AddReservationController addController = loader.getController();
//
//            // Create a new stage for the modal window
//            Stage modalStage = new Stage();
//            modalStage.initModality(Modality.APPLICATION_MODAL); // Make it a modal window
//            modalStage.setTitle("Add New Reservation");
//            modalStage.setScene(new Scene(root));
//            addController.setStage(modalStage); // Pass the stage to the modal controller
//
//            // Show the modal window and wait for it to be closed
//            modalStage.showAndWait();
//
//            // Refresh the table view after the modal is closed
//            loadReservationData();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @FXML
//    private void update() {
//        try {
//            // Load the FXML for the modal window
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenes/add-reservation.fxml"));
////            loader.setControllerFactory(ApplicationContext.getInstance()::getBean);
//            loader.setControllerFactory(ApplicationContextProvider.getApplicationContext()::getBean);
//
//            Parent root = loader.load();
//
//            // Get the controller for the new scene
//            ReservationControllerEdit editController = loader.getController();
//
//            // Create a new stage for the modal window
//            Stage modalStage = new Stage();
//            modalStage.initModality(Modality.APPLICATION_MODAL); // Make it a modal window
//            modalStage.setTitle("Edit a reservation");
//            modalStage.setScene(new Scene(root));
//            editController.setStage(modalStage); // Pass the stage to the modal controller
//
//            // Show the modal window and wait for it to be closed
//            modalStage.showAndWait();
//
//            // Refresh the table view after the modal is closed
//            loadReservationData();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @FXML
//    private void delete() {
//        try {
//            // Load the FXML for the modal window
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/modal-delete.fxml"));
////            loader.setControllerFactory(ApplicationContext.getInstance()::getBean);
//            loader.setControllerFactory(ApplicationContextProvider.getApplicationContext()::getBean);
//
//            Parent root = loader.load();
//
//            // Get the controller for the new scene
//            AddReservationController addController = loader.getController();
//
//            // Create a new stage for the modal window
//            Stage modalStage = new Stage();
//            modalStage.initModality(Modality.APPLICATION_MODAL); // Make it a modal window
//            modalStage.setTitle("Delete a reservation");
//            modalStage.setScene(new Scene(root));
//            addController.setStage(modalStage); // Pass the stage to the modal controller
//
//            // Show the modal window and wait for it to be closed
//            modalStage.showAndWait();
//
//            // Refresh the table view after the modal is closed
//            loadReservationData();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//}


// newer //

package com.college.controller;

import com.college.domain.Reservation;
import com.college.factory.ReservationFactory;
import com.college.service.ReservationService;
import com.college.utilities.ApplicationContextProvider;
import com.college.utilities.Helper; // Assuming this is for utility methods
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
// import org.springframework.web.bind.annotation.RestController; // This annotation is for REST controllers, remove if not needed for UI controller

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional; // Needed for Alert
import java.util.ResourceBundle;

@Component
public class ReservationUIController implements Initializable {

    @FXML private TableView<Reservation> reservationTable;
    @FXML private TableColumn<Reservation, Integer> reservationIdColumn;
    @FXML private TableColumn<Reservation, String> startTimeColumn;
    @FXML private TableColumn<Reservation, String> endTimeColumn;

    @FXML private Button btnAdd;
    @FXML private Button btnEdit;
    @FXML private Button btnDelete;
    @FXML private Button btnSearch;
    @FXML private Button btnGoBack;
    @FXML private Button btnRefresh;

    @FXML private TextField searchbar;
    @FXML private Label labelFeedback;

    private final ReservationService reservationService;
    private ObservableList<Reservation> reservationList;

    @Autowired
    public ReservationUIController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reservationIdColumn.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("reservationDateTimeStart"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("reservationDateTimeEnd"));

        reservationList = FXCollections.observableArrayList();
        reservationTable.setItems(reservationList);

        loadReservationData();
    }

    @FXML
    private void loadReservationData() {
        labelFeedback.setText(""); // Clear feedback on refresh
        List<Reservation> reservations = reservationService.getAll();
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
            loadReservationData(); // Show all data if search is empty
            return;
        }

        try {
            int id = Integer.parseInt(searchText);
            Reservation reservation = reservationService.read(id);

            if (reservation == null){
                System.out.println("No record found for ID: " + id);
                labelFeedback.setText("No record found for ID: " + id);
                reservationList.clear(); // Clear table if no record found for search
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
    private void getAll() { // Renamed from commented out getAll() to avoid confusion, assuming this is connected to btnRefresh
        loadReservationData(); // Re-use the data loading method
    }

    @FXML
    private void add() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/add-reservation.fxml"));
            loader.setControllerFactory(ApplicationContextProvider.getApplicationContext()::getBean);

            Parent root = loader.load();
            AddReservationController addController = loader.getController();

            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setTitle("Add New Reservation");
            modalStage.setScene(new Scene(root));
            addController.setStage(modalStage);

            modalStage.showAndWait();
            loadReservationData(); // Refresh data after modal closes

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

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("Delete Reservation?");
        alert.setContentText("Are you sure you want to delete reservation ID: " + selectedReservation.getReservationId() + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                boolean deleted = reservationService.delete(selectedReservation.getReservationId());
                if (deleted) {
                    labelFeedback.setText("Reservation ID: " + selectedReservation.getReservationId() + " deleted successfully.");
                    loadReservationData(); // Refresh the table
                } else {
                    labelFeedback.setText("Failed to delete reservation ID: " + selectedReservation.getReservationId() + ".");
                }
            } catch (Exception e) {
                e.printStackTrace();
                labelFeedback.setText("Error deleting reservation: " + e.getMessage());
            }
        } else {
            labelFeedback.setText("Deletion cancelled.");
        }
    }

    // Existing update method, will be modified for editing
    @FXML
    private void update() { // This method will be used for editing
        Reservation selectedReservation = reservationTable.getSelectionModel().getSelectedItem();

        if (selectedReservation == null) {
            labelFeedback.setText("Please select a reservation to edit.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edit-reservation.fxml")); // Use a dedicated FXML for editing
            loader.setControllerFactory(ApplicationContextProvider.getApplicationContext()::getBean);

            Parent root = loader.load();
            EditReservationController editController = loader.getController(); // Use the new EditReservationController

            // Pass the selected reservation to the edit controller
            editController.setReservationToEdit(selectedReservation);

            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setTitle("Edit Reservation (ID: " + selectedReservation.getReservationId() + ")");
            modalStage.setScene(new Scene(root));
            editController.setStage(modalStage);

            modalStage.showAndWait();
            loadReservationData(); // Refresh data after modal closes

        } catch (IOException e) {
            e.printStackTrace();
            labelFeedback.setText("Error opening Edit Reservation form.");
        }
    }
}

