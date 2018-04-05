package com.flowergarden.config;

import com.flowergarden.service.GetAllBouquetsService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class ApplicationContextSingleton {

    private static AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
    private ApplicationContextSingleton(){
    }
    public static AbstractApplicationContext getContext(){
        return context;
    }
}
