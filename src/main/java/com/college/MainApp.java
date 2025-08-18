package com.college;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/guest-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Guest Management");
        stage.setScene(scene);
        stage.setWidth(800);
        stage.setHeight(600);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
