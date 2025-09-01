module com.college.sidebar {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.college.sidebar to javafx.fxml;
    exports com.college.sidebar;
}