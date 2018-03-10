package com.flowergarden.run;

import com.flowergarden.service.GetBouquetPriceService;

public class Run {

    public static void main(String[] args) {

        GetBouquetPriceService getBouquetPriceService = GetBouquetPriceService.getBouquetPriceService();
        float price = getBouquetPriceService.getPrice(1);
        System.out.println("The total price of married bouquet is :" + price);


    }

}
