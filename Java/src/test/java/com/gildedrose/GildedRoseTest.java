package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void addMultipleItems(){
        Item[] items = new Item[] {
            new Item("+5 Dexterity Vest", 10, 20),
            new Item("Aged Brie", 2, 0),
            new Item("Elixir of the Mongoose", 5, 7),
            new Item("Sulfuras, Hand of Ragnaros", 0, 80),
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            // this conjured item does not work properly yet
//            new Item("Conjured Mana Cake", 3, 6)
        };

        GildedRose app = new GildedRose(items);

        assertEquals("+5 Dexterity Vest, 10, 20", ""+items[0]);
        assertEquals("Aged Brie, 2, 0", ""+items[1]);
        assertEquals("Elixir of the Mongoose, 5, 7", ""+items[2]);
        assertEquals("Sulfuras, Hand of Ragnaros, 0, 80", ""+items[3]);
        assertEquals("Sulfuras, Hand of Ragnaros, -1, 80", ""+items[4]);
        assertEquals("Backstage passes to a TAFKAL80ETC concert, 15, 20", ""+items[5]);
        assertEquals("Backstage passes to a TAFKAL80ETC concert, 10, 49", ""+items[6]);
        assertEquals("Backstage passes to a TAFKAL80ETC concert, 5, 49", ""+items[7]);
//            // this conjured item does not work properly yet
//            new Item("Conjured Mana Cake", 3, 6) };
    }

    @Test
    void normalItemDegrades1whilstInDate(){
        Item[] items = new Item[] {
            new Item("Normal Item", 2, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        assertEquals("Normal Item, 0, 8", ""+items[0]);
    }

    @Test
    void normalItemDegrades2PastDate(){
        Item[] items = new Item[] {
            new Item("Normal Item", 2, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        assertEquals("Normal Item, -2, 4", ""+items[0]);
    }
    @Test
    void brieIncreasesToSellBy(){
        Item[] items = new Item[] {
            new Item("Aged Brie", 2, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        assertEquals("Aged Brie, 0, 2", ""+items[0]);
    }

    @Test
    void briePastToSellBy(){
        Item[] items = new Item[] {
            new Item("Aged Brie", 2, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();

        assertEquals("Aged Brie, -2, 6", ""+items[0]);
    }

    @Test
    void brieNotPast50(){
        Item[] items = new Item[] {
            new Item("Aged Brie", 2, 48)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();

        assertEquals("Aged Brie, -2, 50", ""+items[0]);
    }

    @Test
    void sulfurasDoesntChangeQaulity() {
        Item[] items = new Item[]{
            new Item("Sulfuras, Hand of Ragnaros", 2, 80),
            new Item("Sulfuras, Hand of Ragnaros", 1, 80),
            new Item("Sulfuras, Hand of Ragnaros", -3, 80),
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();

        assertEquals("Sulfuras, Hand of Ragnaros, 2, 80", "" + items[0]);
        assertEquals("Sulfuras, Hand of Ragnaros, 1, 80", "" + items[1]);
        assertEquals("Sulfuras, Hand of Ragnaros, -3, 80", "" + items[2]);
    }

    @Test
    void backstageIncreases1over10DaysOut(){
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert, 13, 22", ""+items[0]);
    }

    @Test
    void backstageIncreases2when10DaysOutOrLess(){
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert, 8, 25", ""+items[0]);
    }

    @Test
    void backstageIncreases3when5DaysOutOrLess(){
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 6, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert, 2, 31", ""+items[0]);
    }

    @Test
    void backstageHasValueWhenSellIn0(){
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 2, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert, 0, 26", ""+items[0]);
    }

    @Test
    void backstageExpires(){
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 1, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert, -1, 0", ""+items[0]);
    }

    @Test
    void backstageNotOver50(){
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 12, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 7, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 2, 49)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();

        assertEquals("Backstage passes to a TAFKAL80ETC concert, 10, 50", ""+items[0]);
        assertEquals("Backstage passes to a TAFKAL80ETC concert, 5, 50", ""+items[1]);
        assertEquals("Backstage passes to a TAFKAL80ETC concert, 0, 50", ""+items[2]);
    }

}
