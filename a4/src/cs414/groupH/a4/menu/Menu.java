package cs414.groupH.a4.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	private List<MenuItem> menuItems;
	
	public Menu() {
		menuItems = new ArrayList<MenuItem>();
	}
	
	public boolean addMenuItem(String name, double price, boolean isDailySpecial) {
		MenuItem item = new MenuItem(name, price, isDailySpecial);
		if (!containsMenuItem(item)) {
			menuItems.add(item);
			return true;
		}
		else {
			System.out.println("ERROR: Menu item with name '"+item.getName()+"' already exists.");
			return false;
		}
	}
	
	public boolean containsMenuItem(MenuItem item) {
		return menuItems.contains(item);
	}
	
	public boolean removeMenuItem(MenuItem item) {
		return menuItems.remove(item);
	}

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}
	
}
