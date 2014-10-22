package cs414.groupH.a4.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	private List<MenuItem> menuItems;
	
	public Menu() {
		menuItems = new ArrayList<MenuItem>();
	}
	
	public void addMenuItem(MenuItem item) {
		menuItems.add(item);
	}
	
	public void removeMenuItem(MenuItem item) {
		menuItems.remove(item);
	}

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}
	
}
