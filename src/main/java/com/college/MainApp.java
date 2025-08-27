package com.college;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;

public class MainApp extends Application {

    private ConfigurableApplicationContext springContext;

    @Override
    public void init() {
        springContext = Main.getSpringContext();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/guest-view.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean); // Allow Spring to inject dependencies
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setTitle("Guest Management");
        stage.setScene(scene);
        stage.setWidth(1000);
        stage.setHeight(600);
        stage.show();
    }

    @Override
    public void stop() {
        springContext.close();
    }
}
