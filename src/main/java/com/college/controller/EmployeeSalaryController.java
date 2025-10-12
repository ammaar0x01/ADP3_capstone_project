package com.college.controller;

import com.college.MainFinal;
import com.college.domain.EmployeeSalary;
import com.college.service.EmployeeSalaryService;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class EmployeeSalaryController {

    @FXML private TableView<EmployeeSalary> employeeSalaryTable;
    @FXML private TableColumn<EmployeeSalary, Integer> colId;
    @FXML private TableColumn<EmployeeSalary, String> colEmployee;
    @FXML private TableColumn<EmployeeSalary, Double> colAmount;
    @FXML private TableColumn<EmployeeSalary, String> colMethod;
    @FXML private TableColumn<EmployeeSalary, LocalDate> colDate;

    private final ObservableList<EmployeeSalary> employeeSalaries = FXCollections.observableArrayList();

    @Autowired
    private EmployeeSalaryService employeeSalaryService;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("salaryId"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colMethod.setCellValueFactory(new PropertyValueFactory<>("method"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        colEmployee.setCellValueFactory(cellData -> {
            if (cellData.getValue().getEmployee() != null) {
                return new SimpleStringProperty(
                        cellData.getValue().getEmployee().getUser().getName() + " " +
                                cellData.getValue().getEmployee().getUser().getSurname()
                );
            } else {
                return new SimpleStringProperty("N/A");
            }
        });

        employeeSalaryTable.setItems(employeeSalaries);
        loadEmployeeSalaries();
    }

    private void loadEmployeeSalaries() {
        try {
            List<EmployeeSalary> list = employeeSalaryService.getAll();
            Platform.runLater(() -> {
                employeeSalaries.clear();
                employeeSalaries.addAll(list);
            });
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error loading employee salaries: " + e.getMessage());
        }
    }

    @FXML
    private void handleAddEmployeeSalary() {
        openForm(null); // Confirmation is inside the form
    }

    @FXML
    private void handleUpdateEmployeeSalary() {
        EmployeeSalary selected = employeeSalaryTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            openForm(selected); // Confirmation is inside the form
        } else {
            showAlert("Please select an employee salary to update");
        }
    }

    @FXML
    private void handleDeleteEmployeeSalary() {
        EmployeeSalary selected = employeeSalaryTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Please select an employee salary to delete");
            return;
        }

        // ✅ Confirmation before deleting
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Delete");
        confirm.setHeaderText("Are you sure you want to delete this employee salary?");
        confirm.setContentText("This action cannot be undone.");
        var result = confirm.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                employeeSalaryService.delete(selected.getSalaryId());
                showAlert(Alert.AlertType.INFORMATION, "Employee salary deleted successfully");
                loadEmployeeSalaries();
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error deleting employee salary: " + e.getMessage());
            }
        }
    }

    private void openForm(EmployeeSalary employeeSalary) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenes/Add_EmployeeSalary.fxml"));
            loader.setControllerFactory(MainFinal.getSpringContext()::getBean);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(employeeSalary == null ? "Add Employee Salary" : "Update Employee Salary");
            stage.setScene(new Scene(loader.load()));

            AddEmployeeSalaryController controller = loader.getController();
            controller.setEmployeeSalary(employeeSalary);

            stage.showAndWait();
            loadEmployeeSalaries();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error opening form: " + e.getMessage());
        }
    }

    private void showAlert(String message) {
        showAlert(Alert.AlertType.WARNING, message);
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type, message, ButtonType.OK);
        alert.showAndWait();
    }
}
