package com.college.sidebar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;


// help from
// https://www.youtube.com/watch?v=VOiFmZyGAps&list=PLlGZc17KPrVAKj3Tl1im5HN8Lh5nYTXyB&index=5

public class Main extends Application {

    private double x, y = 0;
//    private double x = 0;
//    private double y = 0;

    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));


        String windowName = "main-d1.fxml";
//        String windowName = "main.fxml";
        System.out.println("\nLoading...");
        System.out.println("Getting '" + windowName + "'...");

        Parent root =  FXMLLoader.load(Objects.requireNonNull(getClass().getResource(windowName)));
        Scene scene = new Scene(root);


//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("The Jazzy house, HMS");
        stage.setWidth(1000);
        stage.setHeight(600);
//        stage.setResizable(false);

        // styles

        scene.getStylesheets().add(getClass().getResource("/stylesheets/main.css").toExternalForm());
//        scene.getStylesheets().add(getClass().getResource("/css/main.css").toExternalForm());

//        Image icon = new Image(getClass().getResourceAsStream("/images/icons/bed.png"));
//        stage.getIcons().add(icon);
//

        // business name?
        // jazzy joint
        //

        // ---------------------------------
        // undecorated window
//        stage.initStyle(StageStyle.UNDECORATED);
//        /// to move window
//        root.setOnMousePressed(event -> {
//            x = event.getSceneX();
//            y = event.getSceneY();
//        });
//
//        root.setOnMouseDragged(event -> {
//            stage.setX(event.getSceneX() - x);
//            stage.setY(event.getSceneX() - y);
//        });
        // ---------------------------------

        // custom logo
        // custom fonts
        // less solid colors; gradient colors?

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}