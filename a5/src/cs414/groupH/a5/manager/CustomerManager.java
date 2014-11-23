package cs414.groupH.a5.manager;

import java.util.ArrayList;
import java.util.List;

import cs414.groupH.a5.address.Address;
import cs414.groupH.a5.customer.Customer;
import cs414.groupH.a5.employee.Employee;

public class CustomerManager {
	private static List<Customer> employees = new ArrayList<Customer>();
	
	public static boolean addEmployee(Customer emp) {
		if (!doesEmpExist(emp)) {
			employees.add(emp);
			return true;
		}
		else {
			System.out.println("ERROR: Customer with ID '"+emp.getUsername()+"' already exists.");
			return false;
		}
	}
	
	public static boolean doesEmpExist(Customer emp) {
		return employees.contains(emp);
	}
	
	public static Customer findCustomer(String uname) {
		for (Customer e : employees) {
			if (e.getUsername().equals(uname)) {
				return e;
			}
		}
		return null;
	}
	
	public static boolean verifyCreds(String uname, String pwd) {
		Customer emp = new Customer(uname, new Address());
		if (employees.contains(emp)) {
			return employees.get(employees.indexOf(emp)).verifyPassword(pwd);
		}
		else {
			return false;
		}
	}
	
	public static boolean removeEmployee(Employee emp) {
		return employees.remove(emp);
	}
	
	public static void clear() {
		employees.clear();
	}

	public static List<Customer> getCustomer() {
		return employees;
	}

}
