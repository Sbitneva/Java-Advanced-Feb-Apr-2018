package com.flowergarden.dao;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.flowers.Flower;

import java.util.ArrayList;

public interface BouquetDao {
    void addBouquet(String bouquetName);

    float getAssemblePrice(int bouquetId);

    Bouquet getBouquet(int id);

    ArrayList<Bouquet> getAllBouquets();

    ArrayList<Flower> getBouquetFlowers(int bouquetId);
}
