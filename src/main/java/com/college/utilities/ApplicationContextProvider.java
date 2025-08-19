package com.college.utilities;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider {

    private static ApplicationContext context;

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }
}
