package com.gildedrose;

import java.util.ArrayList;
import java.util.List;


class GildedRose {
//    Item[] originalItems;
    IListOfItems realNames;
//    List<Item> typedItems = new ArrayList<>();

    public GildedRose(IListOfItems aRandomName) {
        this.realNames = aRandomName;
//        typedItems = aRandomName.getListOfItems();
//        typedItems = new ListOfItems(items).getListOfItems();
//        this.originalItems = items;
//        itemFactory factory = new itemFactory();
//        for (Item item : items) {
//            typedItems.add(factory.create(item));
//        }
    }

    public List<Item> getListMacro(){
        return realNames.getListOfItems();
    }

    public void updateQuality() {

        List<Item> typedItems = this.getListMacro();
        for (Item item : typedItems) {
            item.updateQuality();
        }
    }

    }
