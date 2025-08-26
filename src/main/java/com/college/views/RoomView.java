package com.college.views;

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
        String sceneName = "/_trash/window-room-page1.fxml";
//        String sceneName = "window-room-page1.fxml";

        System.out.println("\n>>> Loading scene from '" + sceneName + "'...");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(sceneName));
        loader.setControllerFactory(springContext::getBean);

        Parent root = loader.load();
        Scene scene = new Scene(root);

//        scene.getStylesheets().add(getClass().getResource("/css/buttonStyle.css").toExternalForm());
//        scene.getStylesheets().add(getClass().getResource("/css/main.css").toExternalForm());

        Image icon = new Image(getClass().getResourceAsStream("/images/icons/app-icon.png"));
        stage.getIcons().add(icon);

        stage.setScene(scene);
        stage.setTitle("HMS - Room Management");
        stage.setWidth(1000);
        stage.setHeight(600);
        stage.setResizable(false);

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
