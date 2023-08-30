package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0, "generic") };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.typedItems.get(0).name);
    }

    @Test
    void addMultipleItems(){
        Item[] items = new Item[] {
            new Item("+5 Dexterity Vest", 10, 20, "generic"),
            new Item("Aged Brie", 2, 0, "Aged"),
            new Item("Elixir of the Mongoose", 5, 7,"generic"),
            new Item("Sulfuras, Hand of Ragnaros", 0, 80,"Legendary"),
            new Item("Sulfuras, Hand of Ragnaros", -1, 80,"Legendary"),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20,"Ticket"),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49,"Ticket"),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49,"Ticket"),
            // this conjured item does not work properly yet
//            new Item("Conjured Mana Cake", 3, 6)
        };

        GildedRose app = new GildedRose(items);

        assertEquals("+5 Dexterity Vest, 10, 20", ""+app.typedItems.get(0));
        assertEquals("Aged Brie, 2, 0", ""+app.typedItems.get(1));
        assertEquals("Elixir of the Mongoose, 5, 7", ""+app.typedItems.get(2));
        assertEquals("Sulfuras, Hand of Ragnaros, 0, 80", ""+app.typedItems.get(3));
        assertEquals("Sulfuras, Hand of Ragnaros, -1, 80", ""+app.typedItems.get(4));
        assertEquals("Backstage passes to a TAFKAL80ETC concert, 15, 20", ""+app.typedItems.get(5));
        assertEquals("Backstage passes to a TAFKAL80ETC concert, 10, 49", ""+app.typedItems.get(6));
        assertEquals("Backstage passes to a TAFKAL80ETC concert, 5, 49", ""+app.typedItems.get(7));
//            // this conjured item does not work properly yet
//            new Item("Conjured Mana Cake", 3, 6) };
    }
//
    @Test
    void genericItemDegrades1whilstInDate(){
        Item[] items = new Item[] {
            new Item("Normal Item", 2, 10,"generic")};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        assertEquals("Normal Item, 0, 8", ""+ app.typedItems.get(0));
    }

    @Test
    void genericItemDegrades2PastDate(){
        Item[] items = new Item[] {
            new Item("Normal Item", 2, 10,"generic")};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        assertEquals("Normal Item, -2, 4", ""+ app.typedItems.get(0));
    }
    @Test
    void brieIncreasesToSellBy(){
        Item[] items = new Item[] {
            new Item("Aged Brie", 2, 0,"Aged")};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        assertEquals("Aged Brie, 0, 2", ""+ app.typedItems.get(0));
    }

    @Test
    void briePastToSellBy(){
        Item[] items = new Item[] {
            new Item("Aged Brie", 2, 0, "Aged")};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();

        assertEquals("Aged Brie, -2, 6", ""+app.typedItems.get(0));
    }

    @Test
    void brieNotPast50(){
        Item[] items = new Item[] {
            new Item("Aged Brie", 2, 48, "Aged")};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();

        assertEquals("Aged Brie, -2, 50", ""+app.typedItems.get(0));
    }

    @Test
    void sulfurasDoesntChangeQaulity() {
        Item[] items = new Item[]{
            new Item("Sulfuras, Hand of Ragnaros", 2, 80,"Legendary"),
            new Item("Sulfuras, Hand of Ragnaros", 1, 80,"Legendary"),
            new Item("Sulfuras, Hand of Ragnaros", -3, 80,"Legendary"),
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();

        assertEquals("Sulfuras, Hand of Ragnaros, 2, 80", "" +app.typedItems.get(0));
        assertEquals("Sulfuras, Hand of Ragnaros, 1, 80", "" +app.typedItems.get(1));
        assertEquals("Sulfuras, Hand of Ragnaros, -3, 80", "" +app.typedItems.get(2));
    }

    @Test
    void backstageIncreases1over10DaysOut(){
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20,"Ticket")};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert, 13, 22", ""+app.typedItems.get(0));
    }

    @Test
    void backstageIncreases2when10DaysOutOrLess(){
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20,"Ticket")};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert, 8, 25", ""+app.typedItems.get(0));
    }

    @Test
    void backstageIncreases3when5DaysOutOrLess(){
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 6, 20,"Ticket")};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert, 2, 31", ""+app.typedItems.get(0));
    }

    @Test
    void backstageHasValueWhenSellIn0(){
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 2, 20,"Ticket")};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert, 0, 26", ""+app.typedItems.get(0));
    }

    @Test
    void backstageExpires(){
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 1, 20,"Ticket")};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert, -1, 0", ""+app.typedItems.get(0));
    }

    @Test
    void backstageNotOver50(){
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 12, 49,"Ticket"),
            new Item("Backstage passes to a TAFKAL80ETC concert", 7, 49,"Ticket"),
            new Item("Backstage passes to a TAFKAL80ETC concert", 2, 49,"Ticket")
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();

        assertEquals("Backstage passes to a TAFKAL80ETC concert, 10, 50", ""+app.typedItems.get(0));
        assertEquals("Backstage passes to a TAFKAL80ETC concert, 5, 50", ""+app.typedItems.get(1));
        assertEquals("Backstage passes to a TAFKAL80ETC concert, 0, 50", ""+app.typedItems.get(2));
    }

}
