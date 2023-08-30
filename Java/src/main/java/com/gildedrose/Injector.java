package com.gildedrose;

public class Injector {
    GildedRose GR;
    Item[] items;

    public Injector(Item[] inputItems){
        this.items = inputItems;
        GR = new GildedRose(new ListOfItems(inputItems));
    }

    public void updateQuality() {GR.updateQuality();}
}
