package com.flowergarden.dao;

import com.flowergarden.flowers.Flower;
import com.flowergarden.flowers.GeneralFlower;

import java.util.ArrayList;

public interface FlowerDao {

    ArrayList<GeneralFlower> getAllFlowers();

    ArrayList<Float> getFlowersPricesForBouquet(int bouquetId);

    void addFlower(GeneralFlower flower);

    void deleteFlower(int flowerId);

    void updateFlower(GeneralFlower flower);

    void addFlowerToBouquet(int flowerId, int bouquetId);

    ArrayList<GeneralFlower> getBouquetFlowers(int bouquetId);

}
