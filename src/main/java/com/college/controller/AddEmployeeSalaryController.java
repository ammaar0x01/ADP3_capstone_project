package com.college.controller;

import com.college.domain.Employee;
import com.college.domain.EmployeeSalary;
import com.college.service.EmployeeSalaryService;
import com.college.service.EmployeeService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AddEmployeeSalaryController {

    @FXML private TextField txtAmount;
    @FXML private ChoiceBox<String> choiceMethod;
    @FXML private DatePicker datePicker;
    @FXML private Button btnSave;
    @FXML private Button btnCancel;
    @FXML private ComboBox<Employee> employeeComboBox;

    private EmployeeSalary employeeSalary;

    @Autowired
    private EmployeeSalaryService employeeSalaryService;

    @Autowired
    private EmployeeService empService;

    @FXML
    public void initialize() {
        choiceMethod.getItems().addAll("Cash", "Card", "EFT");
        datePicker.setValue(LocalDate.now());

        try {
            var employees = empService.getAllEmployees();
            employeeComboBox.setItems(FXCollections.observableArrayList(employees));

            employeeComboBox.setCellFactory(cb -> new ListCell<>() {
                @Override
                protected void updateItem(Employee emp, boolean empty) {
                    super.updateItem(emp, empty);
                    setText(empty || emp == null ? "" : emp.getUser().getName() + " " + emp.getUser().getSurname());
                }
            });

            employeeComboBox.setButtonCell(new ListCell<>() {
                @Override
                protected void updateItem(Employee emp, boolean empty) {
                    super.updateItem(emp, empty);
                    setText(empty || emp == null ? "" : emp.getUser().getName() + " " + emp.getUser().getSurname());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error loading employees: " + e.getMessage());
        }
    }

    public void setEmployeeSalary(EmployeeSalary employeeSalary) {
        this.employeeSalary = employeeSalary;
        if (employeeSalary != null) {
            txtAmount.setText(String.valueOf(employeeSalary.getAmount()));
            choiceMethod.setValue(employeeSalary.getMethod());
            datePicker.setValue(employeeSalary.getDate());
        }
    }

    @FXML
    private void handleSave() {
        // ✅ Confirmation before saving
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Save");
        confirm.setHeaderText("Are you sure you want to save this employee salary?");
        confirm.setContentText("Click OK to confirm or Cancel to go back.");
        var result = confirm.showAndWait();

        if (result.isEmpty() || result.get() != ButtonType.OK) {
            return; // User cancelled the confirmation
        }

        try {
            if (txtAmount.getText().trim().isEmpty()) {
                showAlert("Please enter an amount");
                return;
            }
            if (choiceMethod.getValue() == null) {
                showAlert("Please select a payment method");
                return;
            }
            if (datePicker.getValue() == null) {
                showAlert("Please select a date");
                return;
            }

            double amount = Double.parseDouble(txtAmount.getText().trim());

            Employee selectedEmployee = employeeComboBox.getSelectionModel().getSelectedItem();
            if (selectedEmployee == null) {
                showAlert("Please select an employee");
                return;
            }

            if (selectedEmployee.getSalary() != null) {
                showAlert("This employee already has a salary assigned!");
                return;
            }

            if (employeeSalary == null) {
                EmployeeSalary newSalary = new EmployeeSalary.Builder()
                        .setAmount(amount)
                        .setMethod(choiceMethod.getValue())
                        .setDate(datePicker.getValue())
                        .build();

                newSalary.setEmployee(selectedEmployee);
                employeeSalaryService.create(newSalary);
                showAlert(Alert.AlertType.INFORMATION, "Employee salary created successfully");
            } else {
                employeeSalary.setAmount(amount);
                employeeSalary.setMethod(choiceMethod.getValue());
                employeeSalary.setDate(datePicker.getValue());
                employeeSalaryService.update(employeeSalary);
                showAlert(Alert.AlertType.INFORMATION, "Employee salary updated successfully");
            }

            closeWindow();
        } catch (NumberFormatException e) {
            showAlert("Invalid amount format");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error saving employee salary: " + e.getMessage());
        }
    }

    @FXML
    private void handleCancel() {
        // ✅ Confirmation before canceling
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Cancel");
        confirm.setHeaderText("Are you sure you want to cancel?");
        confirm.setContentText("All unsaved changes will be lost.");
        var result = confirm.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            closeWindow();
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) txtAmount.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String message) {
        showAlert(Alert.AlertType.WARNING, message);
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type, message, ButtonType.OK);
        alert.showAndWait();
    }
}
