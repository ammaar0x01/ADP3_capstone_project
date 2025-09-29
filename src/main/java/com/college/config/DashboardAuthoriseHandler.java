package com.college.config;

import com.college.MainFinal;
import com.college.controller.DashboardController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DashboardAuthoriseHandler {

//    @FXML
//    private Label usernameSlot;
//
//    @FXML
//    private Label roleSlot;
//
//    public void setUsername(String username) {
//        usernameSlot.setText(username);
//    }

//    public void setUsername(String username) {
//        // This will fail if usernameSlot is null
//        System.out.println("Setting username: " + username);
//        usernameSlot.setText(username); // <--- will work if FXML injection succeeded
//    }

//    public void redirectToDashboard(Stage stage, Authentication auth) {

//    public void redirectToDashboard(Stage stage, Authentication auth, String username) {
//        System.out.println("username: " + username);
//        this.setUsername(username);
//        usernameSlot.setText(username);
//        roleSlot.setText("temp");
//        System.out.println("***displayed name on home section");
//
//        String fxmlToLoad = "scenes/default-dashboard.fxml"; // default fallback
//
//        for (GrantedAuthority authority : auth.getAuthorities()) {
//            String role = authority.getAuthority();
//            if (role.equals("ROLE_ADMIN")) {
//                fxmlToLoad = "/scenes/dashboardAdmin.fxml";
//                break;
//            } else if (role.equals("ROLE_MANAGER")) {
//                fxmlToLoad = "/scenes/dashboard.fxml";
//                break;
//            }else if (role.equals("ROLE_USER")) {  // <-- add this
//                fxmlToLoad = "/scenes/dashboardUser.fxml";
//                break;
//            }
//            System.out.println("role: " + role);
//
//        }
//
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlToLoad));
//            loader.setControllerFactory(clz -> MainFinal.getSpringContext().getBean(clz));
//            Parent dashboardRoot = loader.load();
//
//            Scene scene = new Scene(dashboardRoot);
//            stage.setScene(scene);
//            stage.setWidth(1100);
//            stage.setHeight(600);
//            stage.show();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    public void redirectToDashboard(Stage stage, Authentication auth, String username) {
        String fxmlToLoad = "scenes/default-dashboard.fxml"; // fallback

        String userRole = "temp_role";
//        String userRole = null;
        for (GrantedAuthority authority : auth.getAuthorities()) {
            String role = authority.getAuthority();
            userRole = role;
            if (role.equals("ROLE_ADMIN")) {
                fxmlToLoad = "/scenes/dashboardAdmin.fxml";
                break;
            } else if (role.equals("ROLE_MANAGER")) {
                fxmlToLoad = "/scenes/dashboard.fxml";
                break;
            } else if (role.equals("ROLE_USER")) {
                fxmlToLoad = "/scenes/dashboardUser.fxml";
                break;
            }
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlToLoad));
            loader.setControllerFactory(clz -> MainFinal.getSpringContext().getBean(clz));
            Parent dashboardRoot = loader.load();

            // Inject username into controller
            Object controller = loader.getController();
            if (controller instanceof DashboardController dashController) {
                dashController.setUsername(username);
                dashController.setRole(userRole); // if you want to display role too
            } else {
                System.out.println("Warning: Unknown dashboard controller type");
            }

            Scene scene = new Scene(dashboardRoot);
            stage.setScene(scene);
            stage.setWidth(1100);
            stage.setHeight(600);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
