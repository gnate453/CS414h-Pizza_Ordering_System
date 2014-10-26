package cs414.groupH.a4.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import cs414.groupH.a4.menu.Menu;
import cs414.groupH.a4.menu.MenuItem;

public class MenuTest {
	
	Menu menu;

	@Test
	public void testAddMenuItem() {
		menu = new Menu();
		MenuItem item = new MenuItem("Pepperoni Pizza", 12.00);
		menu.addMenuItem(item);
		
		assertEquals(1, menu.getMenuItems().size());
		assertEquals(new MenuItem("Pepperoni Pizza", 12.00), menu.getMenuItems().get(0));
	}

	@Test
	public void testRemoveMenuItem() {
		
	}

	@Test
	public void testGetMenuItems() {
		
	}

}
