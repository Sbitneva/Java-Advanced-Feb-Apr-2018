package com.flowergarden.flowers;

import com.flowergarden.properties.FreshnessInteger;

public class Chamomile extends GeneralFlower {

    private int petals;

    Chamomile(int petals, int length, float price, FreshnessInteger fresh) {
        this.petals = petals;
        this.length = length;
        this.price = price;
        this.freshness = fresh;
    }

    public boolean getPetal() {
        if (petals <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public int getPetals() {
        return petals;
    }

    public void setPetals(int petals) {
        this.petals = petals;
    }

    @Override
    public Integer getFreshnessValue() {
        return super.getFreshnessValue();
    }
}
