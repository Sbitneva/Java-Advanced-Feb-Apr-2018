package com.flowergarden.service;

import com.flowergarden.dao.BouquetDao;
import com.flowergarden.dao.DaoFactory;
import com.flowergarden.dao.FlowerDao;

import java.util.ArrayList;

public class GetBouquetPriceService {

    private FlowerDao flowerDao = new DaoFactory().getSqliteFlowerDao();
    private BouquetDao bouquetDao = new DaoFactory().getSqliteBouquetDao();

    public GetBouquetPriceService() {

    }

    public float getPrice(int bouquetId) {

        float price = 0;

        ArrayList<Float> flowerPrices = flowerDao.getFlowersPricesForBouquet(bouquetId);

        float assemblePrice = bouquetDao.getAssemblePrice(bouquetId);

        for (Float flowerPrice : flowerPrices) {
            price += flowerPrice;
        }

        price += assemblePrice;

        return price;
    }
}
