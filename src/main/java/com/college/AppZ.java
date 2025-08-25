package com.college;

import com.college.utilities.ApplicationContextProvider;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

// NOTE: Run the main method in Main.java first //
// GuestUIController problem
// only had start, and main methods

@SpringBootApplication
public class AppZ extends Application {

//    private ConfigurableApplicationContext springContext;

//    @Override
////    public void init() {
//    public void init() throws Exception{
////        springContext = new SpringApplicationBuilder(com.college.Main.class).run();
////        ApplicationContextProvider.setApplicationContext(springContext);
//
//        springContext = SpringApplication.run(AppZ.class);
//    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/scenes/window-guest.fxml"));
//        fxmlLoader.setControllerFactory(springContext::getBean);

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        stage.setTitle("HMS - Guest Management");
        stage.setScene(scene);
        stage.setWidth(1000);
        stage.setHeight(600);
        stage.setResizable(false);
        stage.show();
    }

//    @Override
////    public void stop() {
//    public void stop() throws Exception {
//
//        springContext.close();
//        Platform.exit();
//    }
    // =============================================

    public static void main(String[] args) {
        launch();

//         Disable Spring Boot's web server since we're using JavaFX
//        System.setProperty("java.awt.headless", "false");
//        System.setProperty("spring.main.web-application-type", "none");
//
//        // Launch JavaFX application
//        Application.launch(AppZ.class, args);
    }
}
