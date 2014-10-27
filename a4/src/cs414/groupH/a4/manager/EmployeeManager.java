package cs414.groupH.a4.manager;

import java.util.ArrayList;
import java.util.List;

import cs414.groupH.a4.employee.Employee;
import cs414.groupH.a4.gui.LoginDialog;

public class EmployeeManager {
	private static List<Employee> employees = new ArrayList<Employee>();
	
	public static void main(String [ ] args)
	{
		new LoginDialog();
	}
	
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
	
	public static boolean verifyCreds(String empId, String pwd) {
		Employee emp = new Employee(empId);
		if (employees.contains(emp)) {
			return employees.get(employees.indexOf(emp)).verifyCreds(pwd);
		}
		else {
			return false;
		}
	}
	
	public static void removeEmployee(Employee emp) {
		employees.remove(emp);
	}
	
	public static void clear() {
		employees.clear();
	}

	public static List<Employee> getEmployees() {
		return employees;
	}

}
