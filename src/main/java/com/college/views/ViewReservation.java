package com.college.views;


import com.college.utilities.ApplicationContextProvider;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class ViewReservation extends Application {
    private ConfigurableApplicationContext springContext;

    @Override
    public void init() {
        springContext = new SpringApplicationBuilder(com.college.Main.class).run();
        ApplicationContextProvider.setApplicationContext(springContext);
    }

    @Override
    public void start(Stage stage) throws Exception {
//        String sceneName = "/scenes/dashboard.fxml";
        String sceneName = "/scenes/window-reservation.fxml";
//        String sceneName = "/scenes/reservation-view.fxml";

        System.out.println("\n>>> Loading scene from '" + sceneName + "'...");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(sceneName));

        // Give FXMLLoader access to Spring beans
        loader.setControllerFactory(springContext::getBean);

        Parent root = loader.load();
        Scene scene = new Scene(root);

        Image icon = new Image(getClass().getResourceAsStream("/icon.png"));
        stage.getIcons().add(icon);

        stage.setTitle("HMS - Reservations");

//        stage.initStyle(StageStyle.UNDECORATED);

        stage.setScene(scene);
        stage.setResizable(false);
//        stage.setWidth(1000);
//        stage.setHeight(600);

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
