package com.gildedrose;

import static java.lang.Math.max;
import static java.lang.Math.min;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {

            if (item.name.equals("Sulfuras, Hand of Ragnaros")){
                continue;
            }

            if (item.name.contains("Backstage pass")) {
                backstagePassHandler(item);
                continue;
            }

            if (item.name.equals("Aged Brie")){
                agedBrieHandler(item);
                continue;
            }

            normalItemHandler(item);
        }
    }

    private void normalItemHandler(Item item) {
        item.sellIn = item.sellIn - 1;
        if (item.sellIn >= 0) {
            ChangeQuality(item, -1);
        }
        else {
            ChangeQuality(item, -2);
        }
    }

    private void agedBrieHandler(Item item) {
        item.sellIn -= 1;
        if (item.sellIn < 0 ){
            ChangeQuality(item,2);
        }
        else {
            ChangeQuality(item,1);
        }
    }

    private void backstagePassHandler(Item item) {
        item.sellIn -= 1;
        if (item.sellIn < 0){
            item.quality=0;
            return;
        }
        if (item.sellIn>=10){
            ChangeQuality(item,1);
        }
        else if (item.sellIn>=5){
            ChangeQuality(item,2);
        }
        else{
            ChangeQuality(item,3);
        }
    }

    private void ChangeQuality(Item item, int increment){
        item.quality = min(item.quality + increment,50);
        item.quality = max(item.quality,0);
    }
}
