package com.flowergarden.dao.sqlite;

import com.flowergarden.dao.DaoFactory;
import com.flowergarden.dao.FlowerDao;
import com.flowergarden.flowers.Flower;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class SqliteFlowerTest {

    private FlowerDao flowerDao = DaoFactory.getSqliteFlowerDao();

    @Test
    public void getFlowersPricesForBouquetTest(){
        ArrayList<Float> flowerPrices = flowerDao.getFlowersPricesForBouquet(1);
        float price = 0;
        for(Float flowerPrice : flowerPrices) {
            price += flowerPrice;
        }

        assertEquals(6, flowerPrices.size());
        assertTrue(82.3f == price);
    }

    @Test
    public void getAllFlowerstest(){
        ArrayList<Flower> flowers = flowerDao.getAllFlowers();
        assertEquals(6, flowers.size());
    }
}
