package fi.oulu.tol.sqat.tests;


import static org.junit.Assert.*;
import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

import java.util.ArrayList;
import java.util.List;

public class GildedRoseTest {

	
	
	@Test
	public void testTheTruth() {
		assertTrue(true);
	}
	
	@Test
	public void testUpdateQualityForAddingNewItem() {
		
		GildedRose.items = new ArrayList<Item>();
		GildedRose.items.add(new Item("A new Item", 3, 6));
		GildedRose.updateQuality();
		
		int quality = GildedRose.items.get(0).getQuality();
		
		assertEquals("The test fails for A new Item", 5, quality);
	}
	
	@Test
	public void testUpdateQualityForAgedBrie() {
		
		GildedRose.items = new ArrayList<Item>();
		GildedRose.items.add(new Item("Aged Grie", 2, 0));
		GildedRose.updateQuality();
		
		int sellIn = GildedRose.items.get(0).getSellIn();
		
		assertEquals(3, sellIn);
		
	}
	
	@Test
	public void testUpdateQualityForSulfuras() {
		
		/*
		 * "Sulfuras", being a legendary item, never has to be sold or decreases in Quality 
		 */
		
		GildedRose.items = new ArrayList<Item>();
		GildedRose.items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
		GildedRose.updateQuality();
		
		int sellIn = GildedRose.items.get(0).getSellIn();
		
		assertEquals(0, sellIn);
		
	}
	
	@Test
	public void testUpdateQualityForHigher50Quality() {
		
		/*
		 * The Quality of an item is never more than 50
		 */
		
		GildedRose.items = new ArrayList<Item>();
		GildedRose.items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
		GildedRose.updateQuality();
		
		int qulity = GildedRose.items.get(0).getQuality();
		
		boolean isQualityLessThan50 = false;
		
		if(qulity < 50) {
			
			isQualityLessThan50 = true;
		}
		
		assertTrue("Quality value is biger > 50 ",isQualityLessThan50);
		
	}
	
	@Test
	public void testUpdataQualityForBackstagePassesQualitySellInLess10days() {
		
		/*
		 * "Backstage  passes",  like  aged  brie,  increases  in  Quality  as  it's  SellIn  value 
			approaches; Quality increases by 2 when there are 10 days or less and by 3 when 
			there are 5 days or less but Quality drops to 0 after the concert	
		 */
		
		GildedRose.items = new ArrayList<Item>();
		GildedRose.items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20));
		GildedRose.updateQuality();
		
		int qulity = GildedRose.items.get(0).getQuality();
		
		assertEquals("Quality should be increased by 2 when there are 10 days or less", 22, qulity);
		
		
	}
	
	@Test
	public void testUpdataQualityForBackstagePassesQualitySellInLess5days() {
		
		/*
		 * "Backstage  passes",  like  aged  brie,  increases  in  Quality  as  it's  SellIn  value 
			approaches; Quality increases by 2 when there are 10 days or less and by 3 when 
			there are 5 days or less but Quality drops to 0 after the concert	
		 */
		
		GildedRose.items = new ArrayList<Item>();
		GildedRose.items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20));
		GildedRose.updateQuality();
		
		int qulity = GildedRose.items.get(0).getQuality();
		
		assertEquals("Quality should be increased by 2 when there are 10 days or less", 23, qulity);
		
		
	}
	
	@Test 
	public void testUpdataQualityForlessThanZero() {
		
		/*
		 * The Quality of an item is never negative 
		 */
		
		GildedRose.items = new ArrayList<Item>();
		GildedRose.items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 10, -5));
		GildedRose.updateQuality();
		
		int qulity = GildedRose.items.get(0).getQuality();
		
		
		boolean isQualityGreaterThanZero = true;
		
		if(qulity < 0) {
			isQualityGreaterThanZero = false;
		}
		assertTrue("The Quality of an item is never negative", isQualityGreaterThanZero);
	}
}
