package cs414.groupH.a4.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	private static List<MenuItem> menuItems = new ArrayList<MenuItem>();

	public static boolean addMenuItem(MenuItem item) {
		if (!containsMenuItem(item)) {
			menuItems.add(item);
			return true;
		}
		else {
			System.out.println("ERROR: Menu item with name '"+item.getName()+"' already exists.");
			return false;
		}
	}
	
	public static boolean containsMenuItem(MenuItem item) {
		return menuItems.contains(item);
	}
	
	public static MenuItem findMenuItem(String itemName) {
		for (MenuItem i : menuItems) {
			if (i.getName().equals(itemName)) {
				return i;
			}
		}
		
		return null;
	}
	
	public static boolean removeMenuItem(MenuItem item) {
		return menuItems.remove(item);
	}
	
	public static void clearMenu() {
		menuItems.clear();
	}

	public static List<MenuItem> getMenuItems() {
		return menuItems;
	}
	
}
