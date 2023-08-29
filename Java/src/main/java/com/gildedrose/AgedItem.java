package com.gildedrose;

public class AgedItem extends Item{

    private Item item;

    public AgedItem(String name, int sellIn, int quality, String type) {
        super(name, sellIn, quality, type);
    }

    @Override
    public void updateQuality(){
        this.sellIn -= 1;
        if (this.sellIn < 0 ){
            ChangeQuality(item,2);
        }
        else {
            ChangeQuality(item,1);
        }
    }
}
