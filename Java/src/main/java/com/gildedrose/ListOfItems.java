package com.gildedrose;

import java.util.ArrayList;
import java.util.List;

public class ListOfItems implements IListOfItems{

    Item[] originalItems;
    List<Item> typedItems = new ArrayList<>();

    public ListOfItems(Item[] originalItems) {
            this.originalItems = originalItems;
            itemFactory factory = new itemFactory();
            for (Item item : originalItems) {
                typedItems.add(factory.create(item));
            }
        }
        public List<Item>  getListOfItems(){
        return typedItems;
        }

}

