package com.college.controller;

import com.college.domain.Employee;
import com.college.domain.subclasses.FoodWorker;
import com.college.repository.EmployeeRepository;
import com.college.service.IFoodWorkerService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class EmployeeControllerView {

    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, Integer> colEmployeeId;
    @FXML
    private TableColumn<Employee, String> colName;
    @FXML
    private TableColumn<Employee, String> colSurname;
    @FXML
    private TableColumn<Employee, Integer> colAge;
    @FXML
    private TableColumn<Employee, String> colJobType;
    @FXML
    private TableColumn<Employee, String> colGender;
    @FXML
    private TableColumn<Employee, LocalDate> colStartDate;
    @FXML
    private TableColumn<Employee, Integer> colUserId;

    @Autowired
    private EmployeeRepository repo;
//    private IFoodWorkerService foodWorkerService;

    private ObservableList<Employee> employees = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colJobType.setCellValueFactory(new PropertyValueFactory<>("jobType"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        colUserId.setCellValueFactory(cellData -> {
            Employee emp = cellData.getValue();
            return new javafx.beans.property.SimpleIntegerProperty(
                emp.getUser() != null ? emp.getUser().getId() : 0
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
        TextInputDialog dialog = new TextInputDialog();

        dialog.setTitle("Add Employee");
        dialog.setHeaderText("Name:");
        Optional<String> nameResult = dialog.showAndWait();
        if (nameResult.isEmpty()) return;

        dialog.setHeaderText("Surname:");
        Optional<String> surnameResult = dialog.showAndWait();
        if (surnameResult.isEmpty()) return;

        dialog.setHeaderText("Age:");
        Optional<String> ageResult = dialog.showAndWait();
        if (ageResult.isEmpty()) return;

        dialog.setHeaderText("Job Type:");
        Optional<String> jobTypeResult = dialog.showAndWait();
        if (jobTypeResult.isEmpty()) return;

        dialog.setHeaderText("Gender:");
        Optional<String> genderResult = dialog.showAndWait();
        if (genderResult.isEmpty()) return;

        dialog.setHeaderText("Start Date (yyyy-MM-dd):");
        Optional<String> startDateResult = dialog.showAndWait();
        if (startDateResult.isEmpty()) return;

        // For simplicity, user is set to null here. You can add user selection logic if needed.
        Employee employee = new Employee(
            nameResult.get(),
            surnameResult.get(),
            Integer.parseInt(ageResult.get()),
            jobTypeResult.get(),
            genderResult.get(),
            LocalDate.parse(startDateResult.get()),
            null
        );
        repo.save(employee);
        loadEmployees();
    }

    @FXML
    private void updateEmployee() {
        Employee selected = employeeTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Please select an employee to update.");
            return;
        }

        TextInputDialog dialog = new TextInputDialog(selected.getName());
        dialog.setTitle("Update Employee");
        dialog.setHeaderText("Update Name:");
        Optional<String> nameResult = dialog.showAndWait();
        if (nameResult.isEmpty()) return;

        dialog.setHeaderText("Update Surname:");
        Optional<String> surnameResult = dialog.showAndWait();
        if (surnameResult.isEmpty()) return;

        dialog.setHeaderText("Update Age:");
        Optional<String> ageResult = dialog.showAndWait();
        if (ageResult.isEmpty()) return;

        dialog.setHeaderText("Update Job Type:");
        Optional<String> jobTypeResult = dialog.showAndWait();
        if (jobTypeResult.isEmpty()) return;

        dialog.setHeaderText("Update Gender:");
        Optional<String> genderResult = dialog.showAndWait();
        if (genderResult.isEmpty()) return;

        dialog.setHeaderText("Update Start Date (yyyy-MM-dd):");
        Optional<String> startDateResult = dialog.showAndWait();
        if (startDateResult.isEmpty()) return;

        selected.setName(nameResult.get());
        selected.setSurname(surnameResult.get());
        selected.setAge(Integer.parseInt(ageResult.get()));
        selected.setJobType(jobTypeResult.get());
        selected.setGender(genderResult.get());
        selected.setStartDate(LocalDate.parse(startDateResult.get()));
        repo.save(selected);
        loadEmployees();
    }

    @FXML
    private void deleteEmployee() {
        Employee selected = employeeTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Please select an employee to delete.");
            return;
        }
        repo.deleteById(selected.getEmployeeId());
        loadEmployees();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
