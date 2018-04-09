package com.flowergarden;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class ApplicationContextWrapper {

    private static AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
    private ApplicationContextWrapper(){
    }
    public static AbstractApplicationContext getContext(){
        return context;
    }
}
