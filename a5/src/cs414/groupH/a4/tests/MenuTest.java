package cs414.groupH.a4.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import cs414.groupH.a4.menu.Menu;
import cs414.groupH.a4.menu.MenuItem;

public class MenuTest {

	@Test
	public static void testAddMenuItem() {
		Menu.addMenuItem(new MenuItem("Pepperoni Pizza", 12.00, false));
		
		assertEquals(1, Menu.getMenuItems().size());
		assertEquals(true, Menu.containsMenuItem(new MenuItem("Pepperoni Pizza", 12.00)));
		
		Menu.addMenuItem(new MenuItem("Sausage Pizza", 12.00, false));
		Menu.addMenuItem(new MenuItem("Cheese Pizza", 10.00, false));
		
		assertEquals(3, Menu.getMenuItems().size());
		assertEquals(true, Menu.containsMenuItem(new MenuItem("Pepperoni Pizza")));
		assertEquals(true, Menu.containsMenuItem(new MenuItem("Sausage Pizza")));
		assertEquals(true, Menu.containsMenuItem(new MenuItem("Cheese Pizza")));
	}

	@Test
	public static void testRemoveMenuItem() {
		Menu.addMenuItem(new MenuItem("Pepperoni Pizza", 12.00, false));
		Menu.addMenuItem(new MenuItem("Sausage Pizza", 12.00, false));
		Menu.addMenuItem(new MenuItem("Cheese Pizza", 10.00, false));
		
		assertEquals(3, Menu.getMenuItems().size());
		
		
		Menu.removeMenuItem(new MenuItem("Sausage Pizza"));
		assertEquals(2, Menu.getMenuItems().size());
		assertEquals(true, Menu.containsMenuItem(new MenuItem("Pepperoni Pizza", 12.00)));
		assertNotEquals(true, Menu.containsMenuItem(new MenuItem("Sausage Pizza", 12.00)));
		assertEquals(true, Menu.containsMenuItem(new MenuItem("Cheese Pizza", 10.00)));
		
		Menu.removeMenuItem(new MenuItem("Cheese Pizza"));
		assertEquals(1, Menu.getMenuItems().size());
		assertEquals(true, Menu.containsMenuItem(new MenuItem("Pepperoni Pizza", 12.00)));
		assertNotEquals(true, Menu.containsMenuItem(new MenuItem("Sausage Pizza", 12.00)));
		assertNotEquals(true, Menu.containsMenuItem(new MenuItem("Cheese Pizza", 10.00)));
	}
	
	@Test
	public void testContainsMenuItem() {		
		Menu.addMenuItem(new MenuItem("Pepperoni Pizza", 12.00, false));
		Menu.addMenuItem(new MenuItem("Sausage Pizza", 12.00, false));
		Menu.addMenuItem(new MenuItem("Cheese Pizza", 10.00, false));

		assertEquals(true, Menu.containsMenuItem(new MenuItem("Pepperoni Pizza", 1844.00)));
		assertEquals(false, Menu.containsMenuItem(new MenuItem("Sausagesss Pizza", 12.00)));
		assertEquals(true, Menu.containsMenuItem(new MenuItem("Cheese Pizza", 10.00)));
		assertEquals(true, Menu.containsMenuItem(new MenuItem("Pepperoni Pizza")));
		assertEquals(true, Menu.containsMenuItem(new MenuItem("Sausage Pizza")));
		assertEquals(false, Menu.containsMenuItem(new MenuItem("Cheeseswerwewr4 Pizza")));
	}

	@Test
	public void testGetMenuItems() {
		Menu.addMenuItem(new MenuItem("Pepperoni Pizza", 12.00, false));
		Menu.addMenuItem(new MenuItem("Sausage Pizza", 12.00, false));
		Menu.addMenuItem(new MenuItem("Cheese Pizza", 10.00, true));
		
		
		assertEquals(3, Menu.getMenuItems().size());
		assertEquals("Pepperoni Pizza", Menu.getMenuItems().get(0).getName());
		assertEquals(12.00, Menu.getMenuItems().get(0).getPrice(), 0.0);
		assertEquals("Sausage Pizza", Menu.getMenuItems().get(1).getName());
		assertEquals(12.00, Menu.getMenuItems().get(1).getPrice(), 0.0);
		assertEquals("Cheese Pizza", Menu.getMenuItems().get(2).getName());
		assertEquals(10.00, Menu.getMenuItems().get(2).getPrice(), 0.0);
	}

}
