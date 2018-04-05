package com.flowergarden.bouquet;

import java.util.Collection;

public interface Bouquet<T> {

    int getId();

    void setId(int id);

    float getPrice();

    void setAssemblePrice(float assemblePrice);

    float getAssemblePrice();

    void addFlower(T flower);

    Collection<T> searchFlowersByLength(int start, int end);

    void sortByFreshness();

    Collection<T> getFlowers();

    void setName(String name);

    String getName();
}
