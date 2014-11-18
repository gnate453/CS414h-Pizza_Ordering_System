package cs414.groupH.a5.manager;

import java.util.ArrayList;
import java.util.List;

import cs414.groupH.a5.employee.Employee;

public class EmployeeManager {
	private static List<Employee> employees = new ArrayList<Employee>();
	
	public static boolean addEmployee(Employee emp) {
		if (!doesEmpExist(emp)) {
			employees.add(emp);
			return true;
		}
		else {
			System.out.println("ERROR: Employee with ID '"+emp.getEmployeeId()+"' already exists.");
			return false;
		}
	}
	
	public static boolean doesEmpExist(Employee emp) {
		return employees.contains(emp);
	}
	
	public static Employee findEmployee(String empId) {
		for (Employee e : employees) {
			if (e.getEmployeeId().equals(empId)) {
				return e;
			}
		}
		return null;
	}
	
	public static boolean verifyCreds(String empId, String pwd) {
		Employee emp = new Employee(empId);
		if (employees.contains(emp)) {
			return employees.get(employees.indexOf(emp)).verifyCreds(pwd);
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

	public static List<Employee> getEmployees() {
		return employees;
	}

}
