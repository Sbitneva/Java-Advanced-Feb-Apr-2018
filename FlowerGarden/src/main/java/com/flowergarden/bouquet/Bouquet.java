package com.flowergarden.bouquet;

import java.util.Collection;

public interface Bouquet<T> {

    int getId();

    void setId(int id);

    float getPrice();

    void setAssemblePrice(float assemblePrice);

    void addFlower(T flower);

    Collection<T> searchFlowersByLength(int start, int end);

    void sortByFreshness();

    Collection<T> getFlowers();
}
