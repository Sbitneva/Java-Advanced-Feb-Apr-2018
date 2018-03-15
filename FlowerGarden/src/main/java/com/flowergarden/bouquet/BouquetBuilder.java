package com.flowergarden.bouquet;

public class BouquetBuilder {

    private int id;
    private float assemblePrice;
    private String name;

    public BouquetBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public BouquetBuilder setAssemblePrice(float assemblePrice) {
        this.assemblePrice = assemblePrice;
        return this;
    }

    public Bouquet getBouquet(String typeName) {
        Bouquet bouquet = null;
        switch(typeName){
            case "married" :
                bouquet =  new MarriedBouquet();
                break;
            default:
                bouquet = new CommonBouquet();
                ((CommonBouquet) bouquet).setName(typeName);

        }
        bouquet.setId(this.id);
        bouquet.setAssemblePrice(this.assemblePrice);

        return bouquet;
    }
}
