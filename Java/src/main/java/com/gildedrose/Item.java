package com.gildedrose;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Item implements ItemInterface{

    public String name;

    public int sellIn;

    public int quality;

    public String type;

    public Item(String name, int sellIn, int quality, String type) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        this.type = type;
    }

   @Override
   public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

    @Override
    public void updateQuality() {
        this.sellIn = this.sellIn - 1;
        if (this.sellIn >= 0) {
            ChangeQuality(this, -1);
        }
        else {
            ChangeQuality(this, -2);
        }
    }

    void ChangeQuality(Item item, int increment){
        item.quality = min(item.quality + increment,50);
        item.quality = max(item.quality,0);
    }
}
