package com.college;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;

public class AppZ extends Application {

    private ConfigurableApplicationContext springContext;

    @Override
    public void init() {
        springContext = Main.getSpringContext();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/scenes/window-guest.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setTitle("HMS - Guest Management");
        Image icon = new Image(getClass().getResourceAsStream("/images/icons/app-icon.png"));
        stage.getIcons().add(icon);

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
