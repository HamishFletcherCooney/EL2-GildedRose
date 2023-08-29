package com.gildedrose;

public class TicketItem extends Item{
    public TicketItem(String name, int sellIn, int quality, String type) {
        super(name, sellIn, quality, type);
    }

    @Override
    public void updateQuality(){
        this.sellIn -= 1;
        if (this.sellIn < 0){
            this.quality=0;
            return;
        }
        if (this.sellIn>=10){
            ChangeQuality(this,1);
        }
        else if (this.sellIn>=5){
            ChangeQuality(this,2);
        }
        else{
            ChangeQuality(this,3);
        }
    }

}
