package com.flowergarden.service;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.dao.BouquetDao;
import com.flowergarden.dao.DaoFactory;
import com.flowergarden.dao.FlowerDao;
import com.flowergarden.dao.sqlite.SqliteBouquetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Configurable
public class GetAllBouquetsService implements Services {

    public GetAllBouquetsService(){

    }

    @Autowired
    private SqliteBouquetDao sqliteBouquetDao;

    public ArrayList<Bouquet> getBouquets() {
        ArrayList<Bouquet> bouquets;
        bouquets = sqliteBouquetDao.getAllBouquets();
        return bouquets;
    }


}
