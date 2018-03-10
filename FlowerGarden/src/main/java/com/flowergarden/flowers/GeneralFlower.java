package com.flowergarden.flowers;

import com.flowergarden.flowers.exceptions.FlowerFreshnessException;
import com.flowergarden.flowers.exceptions.FlowerLengthException;
import com.flowergarden.flowers.exceptions.FlowerPriceException;
import com.flowergarden.properties.FreshnessInteger;

import javax.xml.bind.annotation.XmlElement;

public class GeneralFlower implements Flower<Integer>, Comparable<GeneralFlower> {

    FreshnessInteger freshness;
    @XmlElement
    float price;
    @XmlElement
    int length;

    GeneralFlower() {

    }

    @Override
    public FreshnessInteger getFreshness() {
        return freshness;
    }

    public void setFreshness(FreshnessInteger fr) {
        this.freshness = fr;
    }

    @Override
    public float getPrice() {
        return price;
    }

    public void setPrice(float price){
            this.price = price;
    }

    @Override
    public int getLength() {
        return length;
    }

    public void setLength(int length){
        this.length = length;
    }

    @Override
    public int compareTo(GeneralFlower compareFlower) {
        int compareFresh = compareFlower.getFreshness().getFreshness();
        return this.getFreshness().getFreshness() - compareFresh;
    }


    @Override
    public String toString() {
        return "GeneralFlower{" +
                "freshness=" + freshness +
                ", price=" + price +
                ", length=" + length +
                '}';
    }
}
