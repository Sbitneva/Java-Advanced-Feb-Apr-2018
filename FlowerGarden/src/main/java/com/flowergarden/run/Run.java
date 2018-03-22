package com.flowergarden.run;

import com.flowergarden.config.ApplicationConfig;
import com.flowergarden.service.GetBouquetPriceService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Run {

    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        GetBouquetPriceService getBouquetPriceService = (GetBouquetPriceService) context.getBean("getBouquetPriceService");
        System.out.println(getBouquetPriceService.getPrice(1));

        context.close();
    }
}
