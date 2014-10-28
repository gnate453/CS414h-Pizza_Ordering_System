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
		menu.addMenuItem("Pepperoni Pizza", 12.00, false);
		
		assertEquals(1, menu.getMenuItems().size());
		assertEquals(true, menu.containsMenuItem(new MenuItem("Pepperoni Pizza", 12.00)));
		
		menu.addMenuItem("Sausage Pizza", 12.00, false);
		menu.addMenuItem("Cheese Pizza", 10.00, false);
		
		assertEquals(3, menu.getMenuItems().size());
		assertEquals(true, menu.containsMenuItem(new MenuItem("Pepperoni Pizza")));
		assertEquals(true, menu.containsMenuItem(new MenuItem("Sausage Pizza")));
		assertEquals(true, menu.containsMenuItem(new MenuItem("Cheese Pizza")));
	}

	@Test
	public void testRemoveMenuItem() {
		menu = new Menu();

		menu.addMenuItem("Pepperoni Pizza", 12.00, false);
		menu.addMenuItem("Sausage Pizza", 12.00, false);
		menu.addMenuItem("Cheese Pizza", 10.00, false);
		
		assertEquals(3, menu.getMenuItems().size());
		
		
		menu.removeMenuItem(new MenuItem("Sausage Pizza"));
		assertEquals(2, menu.getMenuItems().size());
		assertEquals(true, menu.containsMenuItem(new MenuItem("Pepperoni Pizza", 12.00)));
		assertNotEquals(true, menu.containsMenuItem(new MenuItem("Sausage Pizza", 12.00)));
		assertEquals(true, menu.containsMenuItem(new MenuItem("Cheese Pizza", 10.00)));
		
		menu.removeMenuItem(new MenuItem("Cheese Pizza"));
		assertEquals(1, menu.getMenuItems().size());
		assertEquals(true, menu.containsMenuItem(new MenuItem("Pepperoni Pizza", 12.00)));
		assertNotEquals(true, menu.containsMenuItem(new MenuItem("Sausage Pizza", 12.00)));
		assertNotEquals(true, menu.containsMenuItem(new MenuItem("Cheese Pizza", 10.00)));
	}
	
	@Test
	public void testContainsMenuItem() {
		menu = new Menu();
		
		menu.addMenuItem("Pepperoni Pizza", 12.00, false);
		menu.addMenuItem("Sausage Pizza", 12.00, false);
		menu.addMenuItem("Cheese Pizza", 10.00, false);

		assertEquals(true, menu.containsMenuItem(new MenuItem("Pepperoni Pizza", 1844.00)));
		assertEquals(false, menu.containsMenuItem(new MenuItem("Sausagesss Pizza", 12.00)));
		assertEquals(true, menu.containsMenuItem(new MenuItem("Cheese Pizza", 10.00)));
		assertEquals(true, menu.containsMenuItem(new MenuItem("Pepperoni Pizza")));
		assertEquals(true, menu.containsMenuItem(new MenuItem("Sausage Pizza")));
		assertEquals(false, menu.containsMenuItem(new MenuItem("Cheeseswerwewr4 Pizza")));
	}

	@Test
	public void testGetMenuItems() {
		menu = new Menu();

		menu.addMenuItem("Pepperoni Pizza", 12.00, false);
		menu.addMenuItem("Sausage Pizza", 12.00, false);
		menu.addMenuItem("Cheese Pizza", 10.00, true);
		
		
		assertEquals(3, menu.getMenuItems().size());
		assertEquals("Pepperoni Pizza", menu.getMenuItems().get(0).getName());
		assertEquals(12.00, menu.getMenuItems().get(0).getPrice(), 0.0);
		assertEquals("Sausage Pizza", menu.getMenuItems().get(1).getName());
		assertEquals(12.00, menu.getMenuItems().get(1).getPrice(), 0.0);
		assertEquals("Cheese Pizza", menu.getMenuItems().get(2).getName());
		assertEquals(10.00, menu.getMenuItems().get(2).getPrice(), 0.0);
	}

}
