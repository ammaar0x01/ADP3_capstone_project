package com.college.controller;

import com.college.domain.Reservation;
import com.college.factory.ReservationFactory;
import com.college.service.ReservationService;
import com.college.utilities.ApplicationContextProvider;
import com.college.utilities.Helper;
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
import org.springframework.web.bind.annotation.RestController;

import org.springframework.context.ApplicationContext;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

//@RestController/
@Component
public class ReservationUIController implements Initializable {

//    private ReservationService service;
//
//    @Autowired
//    public ReservationUIController(ReservationService reservationService){
//        service = reservationService;
//    }


    @FXML private TableView<Reservation> reservationTable;
    @FXML private TableColumn<Reservation, Integer> reservationIdColumn;
    @FXML private TableColumn<Reservation, String> startTimeColumn;
    @FXML private TableColumn<Reservation, String> endTimeColumn;

    @FXML private Button btnAdd;
    @FXML private Button btnEdit;
    @FXML private Button btnDelete;
    @FXML private Button btnSearch;
    @FXML private Button btnGoBack;
    @FXML private Button btnRefresh; // maybe remove this

    @FXML private TextField searchbar;

    @FXML private Label labelFeedback;
    // ==============================================

    private final ReservationService reservationService;
    private ObservableList<Reservation> reservationList;

    @Autowired
    public ReservationUIController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    // ==============================================

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Step 2: Map columns to properties of the Reservation class
        reservationIdColumn.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
//        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
//        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));

        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("reservationDateTimeStart"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("reservationDateTimeEnd"));


        // Step 3: Initialize the ObservableList and bind it to the TableView
        reservationList = FXCollections.observableArrayList();
        reservationTable.setItems(reservationList);

        // Fetch data from the database and populate the table
        loadReservationData();
    }

    @FXML
    private void loadReservationData() {
        // Step 4: Call the service to get data
        List<Reservation> reservations = reservationService.getAll();
        reservationList.clear(); // Clear existing data
        reservationList.addAll(reservations); // Add the new data to the list
        System.out.println("Data loaded into TableView. Total items: " + reservationList.size());
    }


    @FXML
    private void search(){
        // search by id? search/filter by time/time-frame?
        // remove white spaces
        labelFeedback.setText("");

        System.out.println("Search query=" +  searchbar.getText());
        int id = Integer.parseInt(searchbar.getText().replace(" ", ""));


        System.out.println("\nRecord");
        System.out.println(reservationService.read(id));

        Reservation reservation = reservationService.read(id);
//        if (Helper.isNullOrEmpty()) // maybe modify for objects

        // make more complex? ex, detect if numbers were entered, etc...
        if (reservation == null){
            System.out.println("No record found");
            labelFeedback.setText("No record found");
        }
        else{
//            labelFeedback.setText("");

            reservationList.clear(); // Clear existing data
            reservationList.add(reservation);
        }


        searchbar.setText("");

//        reservationList.addAll(reservations); // Add the new data to the list
        System.out.println("Data loaded into TableView. Total items: " + reservationList.size());
    }

    @FXML
//    private List<Reservation> getAll() {
    private void getAll() {
//        System.out.println("Button clicked!");
//        System.out.println("Search query=" +  searchbar.getText());
//        searchbar.setText("");
//
//        btnSearch.setText("Getting...");

        System.out.println("\nAll data:");

        for (Reservation r : reservationService.getAll()){
            System.out.println(" - " + r);
        }
//        System.out.println(service.getAll());
//        return reservationService.getAll();
    }

//    @FXML
//    private void handleVipClick() {
//    private Reservation add() {
//        // reset other buttons
////        btnEdit.setText("");
////        btnDelete.setText("");
////        btnSearch.setText("");
//
//        // temp
//        btnAdd.setText("Added data");
//
////        Reservation reservation = new Reservation("10:00", "12:00");
//        Reservation reservation = ReservationFactory.createReservation(
//                "10:00",
//                "12:00"
//        );
//        System.out.println("\nAdding...");
//        System.out.println(reservation);
//        System.out.println(reservation.toString());
//
//        System.out.println(reservationService.create(reservation));
//        return reservationService.create(reservation);
//    }


    @FXML
    private void add() {
        try {
            // Load the FXML for the modal window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/add-reservation.fxml"));
//            loader.setControllerFactory(ApplicationContext.getInstance()::getBean);
            loader.setControllerFactory(ApplicationContextProvider.getApplicationContext()::getBean);

            Parent root = loader.load();

            // Get the controller for the new scene
            AddReservationController addController = loader.getController();

            // Create a new stage for the modal window
            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL); // Make it a modal window
            modalStage.setTitle("Add New Reservation");
            modalStage.setScene(new Scene(root));
            addController.setStage(modalStage); // Pass the stage to the modal controller

            // Show the modal window and wait for it to be closed
            modalStage.showAndWait();

            // Refresh the table view after the modal is closed
            loadReservationData();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void update() {
        try {
            // Load the FXML for the modal window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenes/add-reservation.fxml"));
//            loader.setControllerFactory(ApplicationContext.getInstance()::getBean);
            loader.setControllerFactory(ApplicationContextProvider.getApplicationContext()::getBean);

            Parent root = loader.load();

            // Get the controller for the new scene
            AddReservationController addController = loader.getController();

            // Create a new stage for the modal window
            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL); // Make it a modal window
            modalStage.setTitle("Edit a reservation");
            modalStage.setScene(new Scene(root));
            addController.setStage(modalStage); // Pass the stage to the modal controller

            // Show the modal window and wait for it to be closed
            modalStage.showAndWait();

            // Refresh the table view after the modal is closed
            loadReservationData();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void delete() {
        try {
            // Load the FXML for the modal window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/modal-delete.fxml"));
//            loader.setControllerFactory(ApplicationContext.getInstance()::getBean);
            loader.setControllerFactory(ApplicationContextProvider.getApplicationContext()::getBean);

            Parent root = loader.load();

            // Get the controller for the new scene
            AddReservationController addController = loader.getController();

            // Create a new stage for the modal window
            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL); // Make it a modal window
            modalStage.setTitle("Delete a reservation");
            modalStage.setScene(new Scene(root));
            addController.setStage(modalStage); // Pass the stage to the modal controller

            // Show the modal window and wait for it to be closed
            modalStage.showAndWait();

            // Refresh the table view after the modal is closed
            loadReservationData();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
