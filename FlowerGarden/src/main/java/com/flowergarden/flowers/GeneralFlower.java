package com.flowergarden.flowers;

import com.flowergarden.properties.FreshnessInteger;

import javax.xml.bind.annotation.XmlElement;

public class GeneralFlower implements Flower<Integer>, Comparable<GeneralFlower> {

    private int flowerId;
    private int bouquetId;
    private String name;

    FreshnessInteger freshness;
    private Integer freshnessValue;
    @XmlElement
    float price;
    @XmlElement
    int length;

    GeneralFlower() {

    }

    public int getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(int flowerId) {
        this.flowerId = flowerId;
    }

    public int getBouquetId() {
        return bouquetId;
    }

    public void setBouquetId(int bouquetId) {
        this.bouquetId = bouquetId;
    }

    public Integer getFreshnessValue() {
        return this.freshnessValue.intValue();
    }

    public void setFreshnessValue(Integer freshnessValue) {
        this.freshnessValue = freshnessValue;
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

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
