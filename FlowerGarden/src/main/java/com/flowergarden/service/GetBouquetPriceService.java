package com.flowergarden.service;

import com.flowergarden.dao.BouquetDao;
import com.flowergarden.dao.DaoFactory;
import com.flowergarden.dao.FlowerDao;

import java.util.ArrayList;

public class GetBouquetPriceService {

    private static GetBouquetPriceService service = new GetBouquetPriceService();

    private GetBouquetPriceService() {

    }

    public static GetBouquetPriceService getBouquetPriceService() {
        return service;
    }

    public float getPrice(int bouquetId) {

        float price = 0;

        FlowerDao flowerDao = DaoFactory.getSqliteFlowerDao();
        BouquetDao bouquetDao = DaoFactory.getSqliteBouquetDao();

        ArrayList<Float> flowerPrices = flowerDao.getFlowersPricesForBouquet(bouquetId);

        float assemblePrice = bouquetDao.getAssemblePrice(bouquetId);

        for (Float flowerPrice : flowerPrices) {
            price += flowerPrice;
        }

        price += assemblePrice;

        return price;
    }
}
