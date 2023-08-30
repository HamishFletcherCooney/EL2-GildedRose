package com.gildedrose;

import java.util.ArrayList;
import java.util.List;


class GildedRose {
//    Item[] originalItems;
    List<Item> typedItems = new ArrayList<>();

    public GildedRose(Item[] items) {
        typedItems = new ListOfItems(items).getListOfItems();
//        this.originalItems = items;
//        itemFactory factory = new itemFactory();
//        for (Item item : items) {
//            typedItems.add(factory.create(item));
//        }
    }

    public void updateQuality() {
        for (Item item : typedItems) {
            item.updateQuality();
        }
    }

    }
