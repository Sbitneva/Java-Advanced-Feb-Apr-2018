package com.flowergarden.dao;

import com.flowergarden.flowers.Flower;

import java.util.ArrayList;

public interface FlowerDao {
    ArrayList<Flower> getAllFlowers();

    ArrayList<Float> getFlowersPricesForBouquet(int bouquetId);

    void addFlower(Flower flower);

    void deleteFlower(int flowerId);

    void addFlowerToBouquet(Flower flower);

}
