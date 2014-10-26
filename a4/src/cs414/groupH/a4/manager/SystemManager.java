package cs414.groupH.a4.manager;

import java.util.List;

import cs414.groupH.a4.employee.Employee;
import cs414.groupH.a4.employee.EmployeeType;
import cs414.groupH.a4.menu.Menu;
import cs414.groupH.a4.menu.MenuItem;

public class SystemManager {
	private Menu menu = new Menu();

	public static void addEmployee(String employeeId, String name, String pwd, EmployeeType empType) {
		Employee emp = new Employee(employeeId, name, pwd, empType);
		EmployeeManager.addEmployee(emp);
	}
	
	public static void removeEmployee(String employeeId, String name, String pwd, EmployeeType empType) {
		Employee emp = new Employee(employeeId, name, pwd, empType);
		EmployeeManager.removeEmployee(emp);
	}
	
	public void addMenuItem(MenuItem item) {
		menu.addMenuItem(item);
	}
	
	public boolean containsMenuItem(String itemName, double price) {
		MenuItem item = new MenuItem(itemName, price);
		return menu.containsMenuItem(item);
	}
	
	public void removeMenuItem(MenuItem item) {
		menu.removeMenuItem(item);
	}
	
	public List<MenuItem> getMenuItems() {
		return menu.getMenuItems();
	}
	
}
