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
    // =============================================

    @Override
    public void init() {
        springContext = new SpringApplicationBuilder(com.college.Main.class).run();
    }

    @Override
    public void start(Stage stage) throws Exception {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainView.fxml"));

//        String sceneName = "/MainView.fxml";
//        String sceneName = "/scenes/reservation-view.fxml";
        String sceneName = "/scenes/window-room-page1.fxml";

        System.out.println("\n>>> Loading scene from '" + sceneName + "'...");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(sceneName));

        // Give FXMLLoader access to Spring beans
        loader.setControllerFactory(springContext::getBean);

        Parent root = loader.load();
        Scene scene = new Scene(root);

//        scene.getStylesheets().add(getClass().getResource("/css/buttonStyle.css").toExternalForm());

        Image icon = new Image(getClass().getResourceAsStream("/icon.png"));
        stage.getIcons().add(icon);

        stage.setScene(scene);
        stage.setTitle("HMS - Rooms");
        stage.setResizable(false);

        stage.setWidth(1000);
        stage.setHeight(600);

        System.out.println("\nRendering UI...");
        stage.show();
    }

    @Override
    public void stop() {
        springContext.close();
    }

    // =============================================
    // MAIN //
    public static void main(String[] args) {
        launch(args);
    }
    // =============================================
}
