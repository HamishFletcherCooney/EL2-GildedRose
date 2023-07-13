package com.gildedrose;

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


            if (!item.name.equals("Aged Brie")) {
                if (item.quality > 0) {
                    ChangeQuality(item,-1);
                }
            } else {
                ChangeQuality(item,1);
            }

            item.sellIn = item.sellIn - 1;

            if (item.sellIn < 0) {
                if (!item.name.equals("Aged Brie")) {
                    if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (item.quality > 0) {
                            item.quality = item.quality - 1;
                        }
                    } else {
                        item.quality = 0;
                    }
                } else {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                }
            }
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
            return;
        }
        else if (item.sellIn>=5){
            ChangeQuality(item,2);
            return;
        }
        else{
            ChangeQuality(item,3);
            return;
        }
    }

    private void ChangeQuality(Item item, int increment){
        item.quality = min(item.quality + increment,50);
    }
}
