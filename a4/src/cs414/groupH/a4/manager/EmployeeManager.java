package cs414.groupH.a4.manager;

import java.util.ArrayList;
import java.util.List;

import cs414.groupH.a4.employee.Employee;

public class EmployeeManager {
	private static List<Employee> employees = new ArrayList<Employee>();
	
	public static void addEmployee(Employee emp) {
		employees.add(emp);
	}
	
	public static void removeEmployee(Employee emp) {
		employees.remove(emp);
	}
	

	public static List<Employee> getEmployees() {
		return employees;
	}

}
