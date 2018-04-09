package com.flowergarden.run;


import com.flowergarden.service.GetBouquetPriceService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Run {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

        GetBouquetPriceService getBouquetPriceService = (GetBouquetPriceService) context.getBean("getBouquetPriceService");
        System.out.println(getBouquetPriceService.getPrice(1));

    }
}
