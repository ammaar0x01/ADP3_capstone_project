package com.college.controller;

import com.college.domain.Role;
import com.college.domain.User;
import com.college.service.RoleService;
import com.college.service.UserService;
import javafx.beans.property.SimpleStringProperty;
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

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Component
public class UserTableController {

    @FXML
    private TableView<User> userTable;
    @FXML private TableColumn<User, Integer> userIdColumn;
    @FXML private TableColumn<User, String> nameColumn;
    @FXML private TableColumn<User, String> surnameColumn;
    @FXML private TableColumn<User, String> emailColumn;
    @FXML private TableColumn<User, String> roleColumn;
    @FXML private TableColumn<User, String> startDateColumn;
    @FXML private TableColumn<User, Integer> ageColumn;
    @FXML private TableColumn<User, String> genderColumn;

    @Autowired
    private UserService userService;

    @Autowired
    RoleService roleService;

    private final ObservableList<User> userList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Bind table columns
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

        // Format startDate as string
        startDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getStartDate() != null
                        ? cellData.getValue().getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                        : ""
        ));

        // Load data from DB
        loadUsers();
    }

    private void loadUsers() {
        try {
            List<User> users = userService.getAll();
            userList.setAll(users);
            userTable.setItems(userList);
            System.out.println("Loaded " + users.size() + " users.");
        } catch (Exception e) {
            System.out.println("Failed to load users: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void addUser() {
        System.out.println("Add User clicked - implement form here");
    }

    @FXML
    private void updateEmployee() {
        User selected = userTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Selection Required", "Please select a user to update.");
            return;
        }

        // Get all the updates first
        TextInputDialog dialog = new TextInputDialog(selected.getName());
        dialog.setTitle("Update Name");
        dialog.setHeaderText("Update Name:");
        Optional<String> name = dialog.showAndWait();
        if (name.isEmpty()) return;

        TextInputDialog dialog1 = new TextInputDialog(selected.getSurname());
        dialog1.setTitle("Update Surname");
        dialog1.setHeaderText("Update Surname:");
        Optional<String> surname = dialog1.showAndWait();
        if (surname.isEmpty()) return;

        TextInputDialog dialog2 = new TextInputDialog(selected.getEmail());
        dialog2.setTitle("Update Email");
        dialog2.setHeaderText("Update Email Address:");
        Optional<String> email = dialog2.showAndWait();
        if (email.isEmpty()) return;

        // Validate email format
        if (!isValidEmail(email.get())) {
            showAlert("Validation Error", "Please enter a valid email address.");
            return;
        }

        // Show confirmation as the LAST step with all changes summarized
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirm User Update");
        confirmationAlert.setHeaderText("Please confirm the following changes:");
        confirmationAlert.setContentText(
                "Are you sure you want to update this user?\n\n" +
                        "User ID: " + selected.getUserId() + "\n\n" +
                        "Changes:\n" +
                        "• Name: " + selected.getName() + " → " + name.get() + "\n" +
                        "• Surname: " + selected.getSurname() + " → " + surname.get() + "\n" +
                        "• Email: " + selected.getEmail() + " → " + email.get() + "\n\n" +
                        "Click OK to save these changes."
        );

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Apply the updates only after confirmation
            selected.setName(name.get());
            selected.setSurname(surname.get());
            selected.setEmail(email.get());

            try {
                userService.create(selected);
                loadUsers();
                showSuccessAlert("User updated successfully!");
            } catch (Exception e) {
                showAlert("Error", "Failed to update user: " + e.getMessage());
            }
        } else {
            showAlert("Update Cancelled", "User update was cancelled. No changes were made.");
        }
    }

    @FXML
    public void deleteUser() {
        User selected = userTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Selection Required", "Please select a user to delete.");
            return;
        }

        // Show confirmation as the LAST step with user details
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirm User Deletion");
        confirmationAlert.setHeaderText("Please confirm deletion:");
        confirmationAlert.setContentText(
                "Are you sure you want to delete this user?\n\n" +
                        "User Details:\n" +
                        "• User ID: " + selected.getUserId() + "\n" +
                        "• Name: " + selected.getName() + " " + selected.getSurname() + "\n" +
                        "• Email: " + selected.getEmail() + "\n" +
                        "• Role: " + (selected.getRole() != null ? selected.getRole() : "N/A") + "\n\n" +
                        "This action will:\n" +
                        "• Permanently delete the user\n" +
                        "• Clean up any unused roles\n" +
                        "• Cannot be undone!\n\n" +
                        "Click OK to proceed with deletion."
        );

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            proceedWithDeleteUser(selected);
        } else {
            showAlert("Deletion Cancelled", "User deletion was cancelled. No changes were made.");
        }
    }

    private void proceedWithDeleteUser(User selected) {
        try {
            boolean deleted = userService.delete(selected.getUserId());

            if (deleted) {
                // Clean up orphaned roles
                List<Role> allRoles = roleService.getAll();
                int rolesCleaned = 0;
                for (Role role : allRoles) {
                    boolean used = roleService.isRoleUsed(role.getId());
                    if (!used) {
                        roleService.delete(role.getId());
                        rolesCleaned++;
                    }
                }

                userList.remove(selected);
                showSuccessAlert("User deleted successfully!" +
                        (rolesCleaned > 0 ? " (" + rolesCleaned + " unused roles cleaned up)" : ""));
                System.out.println("Deleted user: " + selected.getName() + " and cleaned up unused roles.");
            } else {
                showAlert("Error", "Failed to delete user.");
            }

        } catch (Exception e) {
            showAlert("Error", "Error deleting user: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(emailRegex);
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