package com.flowergarden.service;

import com.flowergarden.dao.sqlite.SqliteFlowerDao;
import com.flowergarden.flowers.Flower;
import com.flowergarden.flowers.GeneralFlower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GetBouquetFlowersService implements Services{

    public GetBouquetFlowersService(){

    }
    @Autowired
    private SqliteFlowerDao sqliteFlowerDao;

    public ArrayList<GeneralFlower> getFlowers(int bouquetId) {
        ArrayList<GeneralFlower> flowers = sqliteFlowerDao.getBouquetFlowers(bouquetId);
        return flowers;
    }
}
