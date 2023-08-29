package com.gildedrose;

public class itemFactory {

    public Item create (Item item){
        if (item.type == "Aged") { return createAgedItem(item.name,item.sellIn,item.quality,item.type);
        }
        if (item.type == "Legendary") { return createLegendaryItem(item.name,item.sellIn,item.quality,item.type);
        }
        if (item.type == "Ticket") { return createTicketItem(item.name,item.sellIn,item.quality,item.type);
        }
        return createGenericItem(item.name,item.sellIn,item.quality,item.type);

    }

    private Item createAgedItem (String name, int sellIn, int quality, String type){
        return new AgedItem(name,sellIn,quality,type);
    }

    private Item createLegendaryItem (String name, int sellIn, int quality, String type){
        return new LegendaryItem(name,sellIn,quality,type);
    }

    private Item createTicketItem (String name, int sellIn, int quality, String type){
        return new TicketItem(name,sellIn,quality,type);
    }

    private Item createGenericItem (String name, int sellIn, int quality, String type){
        return new Item(name,sellIn,quality,type);
    }
}
