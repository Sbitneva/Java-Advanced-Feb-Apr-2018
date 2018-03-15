package com.flowergarden.flowers;

import com.flowergarden.dao.sqlite.SqliteBouquetDao;
import com.flowergarden.flowers.exceptions.ChamomileException;
import com.flowergarden.flowers.exceptions.FlowerFreshnessException;
import com.flowergarden.flowers.exceptions.FlowerLengthException;
import com.flowergarden.flowers.exceptions.FlowerPriceException;
import com.flowergarden.properties.FreshnessInteger;
import org.apache.log4j.Logger;

public class FlowersBuilder {

    private static Logger log = Logger.getLogger(SqliteBouquetDao.class.getName());

    private int flowerId;
    private int bouquetId;
    private String name;
    private int length;
    private FreshnessInteger freshness;
    private float price;
    private int petals;
    private boolean spike;

    public FlowersBuilder() {

    }

    public FlowersBuilder setFlowerId(int flowerId) {
        this.flowerId = flowerId;
        return this;
    }

    public FlowersBuilder setBouquetId(int bouquetId) {
        this.bouquetId = bouquetId;
        return this;
    }

    public FlowersBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public FlowersBuilder setLength(int length) throws FlowerLengthException {
        if (length < 1) {
            throw new FlowerLengthException("Flower length should has value greaater than 0");
        } else {
            this.length = length;
        }
        return this;
    }

    public FlowersBuilder setFreshness(FreshnessInteger freshness) throws FlowerFreshnessException {
        if (freshness.getFreshness() < 1) {
            throw new FlowerFreshnessException("Flower freshness value should be greater than 0");
        }
        this.freshness = freshness;
        return this;
    }

    public FlowersBuilder setPrice(float price) throws FlowerPriceException {
        if (price <= 0) {
            throw new FlowerPriceException("Flower price should be greater than 0");
        }
        this.price = price;
        return this;
    }

    public FlowersBuilder setPetals(int petals) throws ChamomileException {
        if (petals < 0) {
            throw new ChamomileException("Petals value should be greater or equal 0");
        }
        this.petals = petals;
        return this;
    }

    public FlowersBuilder setSpike(boolean spike) {
        this.spike = spike;
        return this;
    }

    private Chamomile createChamomile() {

        Chamomile chamomile = new Chamomile(this.petals, this.length, this.price, this.freshness);

        chamomile.setFlowerId(this.flowerId);
        chamomile.setBouquetId(this.bouquetId);

        return chamomile;
    }

    private Rose createRose() {

        Rose rose = new Rose(this.spike, this.length, this.price, this.freshness);

        rose.setFlowerId(this.flowerId);
        rose.setBouquetId(this.bouquetId);

        return rose;
    }

    private Tulip createTulip() {

        Tulip tulip = new Tulip();

        tulip.setLength(this.length);
        tulip.setFreshness(this.freshness);
        tulip.setPrice(this.price);
        tulip.setFlowerId(this.flowerId);
        tulip.setBouquetId(this.bouquetId);

        return tulip;
    }

    private GeneralFlower createGeneralFlower() {

        GeneralFlower generalFlower = new GeneralFlower();

        generalFlower.setBouquetId(this.bouquetId);
        generalFlower.setFlowerId(this.flowerId);
        generalFlower.setFreshness(this.freshness);
        generalFlower.setLength(this.length);
        generalFlower.setPrice(this.price);

        return generalFlower;
    }

    public Flower buildFlower() {
        switch (this.name) {
            case "chamomile":
                return this.createChamomile();
            case "rose":
                return this.createRose();
            case "tulip":
                return this.createTulip();
            default:
                return this.createGeneralFlower();
        }
    }
}
