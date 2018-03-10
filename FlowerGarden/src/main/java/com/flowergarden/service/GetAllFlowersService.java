package com.flowergarden.service;

import com.flowergarden.bouquet.Bouquet;

public class GetAllFlowersService {

    private static GetAllFlowersService service = new GetAllFlowersService();

    private GetAllFlowersService() {

    }

    public static GetAllFlowersService getBouquetPriceService() {
        return service;
    }

    public Bouquet GetBouquetService(int bouquetId) {
        Bouquet bouquet = null;
        return bouquet;
    }
}
