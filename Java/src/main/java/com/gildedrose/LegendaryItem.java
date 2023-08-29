package com.gildedrose;

public class LegendaryItem extends Item{
    public LegendaryItem(String name, int sellIn, int quality, String type) {
        super(name, sellIn, quality, type);
    }

    @Override
    public void updateQuality(){
    }
}
