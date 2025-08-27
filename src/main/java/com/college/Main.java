package com.college;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {

    private static ConfigurableApplicationContext springContext;

    public static void main(String[] args) {
        // Start Spring Boot and block until it's ready
        springContext = SpringApplication.run(Main.class, args);

        // Once Spring Boot is ready, start JavaFX
        Application.launch(MainApp.class, args);
    }

    public static ConfigurableApplicationContext getSpringContext() {
        return springContext;
    }
}
