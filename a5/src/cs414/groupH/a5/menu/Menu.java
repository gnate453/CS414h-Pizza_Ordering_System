package cs414.groupH.a5.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	private static List<MenuItem> menuItems = new ArrayList<MenuItem>();

	public static boolean addMenuItem(MenuItem item) {
		if (!containsMenuItem(item)) {
			return menuItems.add(item);
		}
		else {
			System.out.println("ERROR: Item with name '"+item.getName()+"' already exists.");
			return false;
		}
	}
	
	public static boolean editMenuItem(String oldName, MenuItem item) {
		MenuItem oldItem = new MenuItem(oldName);
		int index = menuItems.indexOf(oldItem);
		MenuItem curItem = menuItems.get(index);
		curItem.setName(item.getName());
		curItem.setPrice(item.getPrice());
		curItem.setDailySpecial(item.isDailySpecial());
		return true;
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
