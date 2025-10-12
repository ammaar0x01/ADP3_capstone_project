package com.college.controller;

import com.college.domain.Employee;
import com.college.repository.EmployeeRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class EmployeeControllerView {

    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, Integer> colEmployeeId;
    @FXML
    private TableColumn<Employee, String> colJobType;
    @FXML
    private TableColumn<Employee, LocalDate> colStartDate;
    @FXML
    private TableColumn<Employee, Integer> colUserId;

    @Autowired
    private EmployeeRepository repo;

    private ObservableList<Employee> employees = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colJobType.setCellValueFactory(new PropertyValueFactory<>("jobType"));
        colStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));

        colUserId.setCellValueFactory(cellData -> {
            Employee emp = cellData.getValue();
            return new javafx.beans.property.SimpleIntegerProperty(
                    emp.getUser() != null ? emp.getUser().getUserId() : 0
            ).asObject();
        });

        loadEmployees();
    }

    private void loadEmployees() {
        employees.clear();
        employees.addAll(repo.findAll());
        employeeTable.setItems(employees);
    }

    @FXML
    private void addEmployee() {
        // Get all the input first
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Employee");
        dialog.setHeaderText("Enter Job Type:");
        Optional<String> jobTypeResult = dialog.showAndWait();
        if (jobTypeResult.isEmpty()) return;

        dialog = new TextInputDialog();
        dialog.setTitle("Add Employee");
        dialog.setHeaderText("Enter Start Date (yyyy-MM-dd):");
        Optional<String> startDateResult = dialog.showAndWait();
        if (startDateResult.isEmpty()) return;

        try {
            // Validate date format
            LocalDate startDate = LocalDate.parse(startDateResult.get());

            // Show confirmation as the LAST step with all details
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirm New Employee");
            confirmationAlert.setHeaderText("Please confirm the new employee details:");
            confirmationAlert.setContentText(
                    "Are you sure you want to add this new employee?\n\n" +
                            "Employee Details:\n" +
                            "• Job Type: " + jobTypeResult.get() + "\n" +
                            "• Start Date: " + startDate + "\n" +
                            "• User: " + "Not assigned" + "\n\n" +
                            "Click OK to create this employee."
            );

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Create employee only after confirmation
                Employee employee = new Employee(
                        jobTypeResult.get(),
                        startDate,
                        null
                );
                repo.save(employee);
                loadEmployees();
                showSuccessAlert("Employee added successfully!");
            } else {
                showAlert("Add Cancelled", "Employee creation was cancelled. No changes were made.");
            }

        } catch (Exception e) {
            showAlert("Error", "Invalid date format. Please use yyyy-MM-dd format.");
        }
    }

    @FXML
    private void updateEmployee() {
        Employee selected = employeeTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Selection Required", "Please select an employee to update.");
            return;
        }

        // Get all the updates first
        TextInputDialog dialog = new TextInputDialog(selected.getJobType());
        dialog.setTitle("Update Employee");
        dialog.setHeaderText("Update Job Type:");
        Optional<String> jobTypeResult = dialog.showAndWait();
        if (jobTypeResult.isEmpty()) return;

        dialog = new TextInputDialog(selected.getStartDate().toString());
        dialog.setTitle("Update Employee");
        dialog.setHeaderText("Update Start Date (yyyy-MM-dd):");
        Optional<String> startDateResult = dialog.showAndWait();
        if (startDateResult.isEmpty()) return;

        try {
            // Validate date format
            LocalDate startDate = LocalDate.parse(startDateResult.get());

            // Show confirmation as the LAST step with all changes summarized
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirm Employee Update");
            confirmationAlert.setHeaderText("Please confirm the following changes:");
            confirmationAlert.setContentText(
                    "Are you sure you want to update this employee?\n\n" +
                            "Employee ID: " + selected.getEmployeeId() + "\n\n" +
                            "Changes:\n" +
                            "• Job Type: " + selected.getJobType() + " → " + jobTypeResult.get() + "\n" +
                            "• Start Date: " + selected.getStartDate() + " → " + startDate + "\n\n" +
                            "Click OK to save these changes."
            );

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Apply the updates only after confirmation
                selected.setJobType(jobTypeResult.get());
                selected.setStartDate(startDate);
                repo.save(selected);
                loadEmployees();
                showSuccessAlert("Employee updated successfully!");
            } else {
                showAlert("Update Cancelled", "Employee update was cancelled. No changes were made.");
            }

        } catch (Exception e) {
            showAlert("Error", "Invalid date format. Please use yyyy-MM-dd format.");
        }
    }

    @FXML
    private void deleteEmployee() {
        Employee selected = employeeTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Selection Required", "Please select an employee to delete.");
            return;
        }

        // Show confirmation as the LAST step with employee details
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirm Employee Deletion");
        confirmationAlert.setHeaderText("Please confirm deletion:");
        confirmationAlert.setContentText(
                "Are you sure you want to delete this employee?\n\n" +
                        "Employee Details:\n" +
                        "• Employee ID: " + selected.getEmployeeId() + "\n" +
                        "• Job Type: " + selected.getJobType() + "\n" +
                        "• Start Date: " + selected.getStartDate() + "\n" +
                        "• User ID: " + (selected.getUser() != null ? selected.getUser().getUserId() : "Not assigned") + "\n\n" +
                        "This action will:\n" +
                        "• Permanently delete the employee record\n" +
                        "• Cannot be undone!\n\n" +
                        "Click OK to proceed with deletion."
        );

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            proceedWithDeleteEmployee(selected);
        } else {
            showAlert("Deletion Cancelled", "Employee deletion was cancelled. No changes were made.");
        }
    }

    private void proceedWithDeleteEmployee(Employee selected) {
        try {
            repo.deleteById(selected.getEmployeeId());
            loadEmployees();
            showSuccessAlert("Employee deleted successfully!");
        } catch (Exception e) {
            showAlert("Error", "Failed to delete employee: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Overloaded method for backward compatibility
    private void showAlert(String message) {
        showAlert("Information", message);
    }
}