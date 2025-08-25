package com.college.views;

import com.college.controller.RoomController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class RoomView extends Application {

    private ConfigurableApplicationContext springContext;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        // Start Spring before JavaFX
        springContext = new SpringApplicationBuilder(com.college.Main.class)
                .run(); // your SpringBootApplication class
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainView.fxml"));

        // Give FXMLLoader access to Spring beans
        loader.setControllerFactory(springContext::getBean);

        Parent root = loader.load();
        Scene scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("/css/buttonStyle.css").toExternalForm());

        Image icon = new Image(getClass().getResourceAsStream("/icon.png"));
        stage.getIcons().add(icon);

        stage.setScene(scene);
        // stage.setWidth(1000); Ammar res
        // stage.setHeight(600);
        stage.show();
    }

    @Override
    public void stop() {
        // Gracefully shutdown Spring when JavaFX exits
        springContext.close();
    }
}
//1
//had to add  @Override
//    public void init() {
//        // Start Spring before JavaFX
//        springContext = new SpringApplicationBuilder(com.college.Main.class)
//                .run(); // your SpringBootApplication class
//    }

//2
//had to replace @restfulcontroller in room controller with @ component\\


//init() method starts Spring before FXMLLoader tries to fetch beans.
//
//loader.setControllerFactory(springContext::getBean); now works because Spring knows about RoomController.
//
//stop() closes Spring cleanly when the app exits.