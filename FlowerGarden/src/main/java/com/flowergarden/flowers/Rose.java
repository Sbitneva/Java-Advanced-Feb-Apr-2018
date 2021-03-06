package com.flowergarden.flowers;

import com.flowergarden.properties.FreshnessInteger;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Rose extends GeneralFlower {

    private boolean spike;

    Rose(boolean spike, int length, float price, FreshnessInteger fresh) {
        this.spike = spike;
        this.length = length;
        this.price = price;
        this.freshness = fresh;
    }

    public Rose() {

    }

    public boolean getSpike() {
        return spike;
    }

    public void setSpike(boolean spike) {
        this.spike = spike;
    }
}
