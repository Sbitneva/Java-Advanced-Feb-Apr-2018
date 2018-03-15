package com.flowergarden.dao;

import com.flowergarden.bouquet.Bouquet;

import java.util.ArrayList;

public interface BouquetDao {

    void addBouquet(Bouquet bouquet);

    float getAssemblePrice(int bouquetId);

    Bouquet getBouquet(int id);

    void updateBouquetAssemblePrice(int bouquetId, float assemblePrice);

    ArrayList<Bouquet> getAllBouquets();

    void deleteBouquet(int id);
}
