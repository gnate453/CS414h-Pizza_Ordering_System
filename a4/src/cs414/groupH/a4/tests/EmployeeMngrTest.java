package cs414.groupH.a4.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import cs414.groupH.a4.employee.Employee;
import cs414.groupH.a4.employee.EmployeeType;
import cs414.groupH.a4.manager.EmployeeManager;

public class EmployeeMngrTest {
	
	@Test
	public void testAddEmployee() {
		EmployeeManager.clear();
		EmployeeManager.addEmployee(new Employee("001", "John Smith", "password", EmployeeType.cashier));
		EmployeeManager.addEmployee(new Employee("002", "Joh Smith", "password", EmployeeType.chef));
		EmployeeManager.addEmployee(new Employee("003", "Jo Smith", "password", EmployeeType.manager));
		EmployeeManager.addEmployee(new Employee("004", "J Smith", "password", EmployeeType.cashier));
		
		assertEquals(4, EmployeeManager.getEmployees().size());
		assertEquals(true, EmployeeManager.doesEmpExist(new Employee("004")));
		assertEquals(true, EmployeeManager.doesEmpExist(new Employee("003")));
		assertEquals(true, EmployeeManager.doesEmpExist(new Employee("002")));
		assertEquals(true, EmployeeManager.doesEmpExist(new Employee("001")));
	}

	@Test
	public void testRemoveEmployee() {
		EmployeeManager.clear();
		EmployeeManager.addEmployee(new Employee("001", "John Smith", "password", EmployeeType.cashier));
		EmployeeManager.addEmployee(new Employee("002", "Joh Smith", "password", EmployeeType.chef));
		EmployeeManager.addEmployee(new Employee("003", "Jo Smith", "password", EmployeeType.manager));
		EmployeeManager.addEmployee(new Employee("004", "J Smith", "password", EmployeeType.cashier));
		
		EmployeeManager.removeEmployee(new Employee("003"));
		
		assertEquals(3, EmployeeManager.getEmployees().size());
		assertEquals(true, EmployeeManager.doesEmpExist(new Employee("004")));
		assertEquals(false, EmployeeManager.doesEmpExist(new Employee("003")));
		assertEquals(true, EmployeeManager.doesEmpExist(new Employee("002")));
		assertEquals(true, EmployeeManager.doesEmpExist(new Employee("001")));
		
		EmployeeManager.removeEmployee(new Employee("001"));
		
		assertEquals(2, EmployeeManager.getEmployees().size());
		assertEquals(true, EmployeeManager.doesEmpExist(new Employee("004")));
		assertEquals(false, EmployeeManager.doesEmpExist(new Employee("003")));
		assertEquals(true, EmployeeManager.doesEmpExist(new Employee("002")));
		assertEquals(false, EmployeeManager.doesEmpExist(new Employee("001")));
	}

	@Test
	public void testGetEmployees() {
		EmployeeManager.clear();
		EmployeeManager.addEmployee(new Employee("001", "John Smith", "password", EmployeeType.cashier));
		EmployeeManager.addEmployee(new Employee("002", "Joh Smith", "password", EmployeeType.chef));
		EmployeeManager.addEmployee(new Employee("003", "Jo Smith", "password", EmployeeType.manager));
		EmployeeManager.addEmployee(new Employee("004", "J Smith", "password", EmployeeType.cashier));
		
		assertEquals(4, EmployeeManager.getEmployees().size());
		assertEquals("001", EmployeeManager.getEmployees().get(0).getEmployeeId());
		assertEquals("002", EmployeeManager.getEmployees().get(1).getEmployeeId());
		assertEquals("003", EmployeeManager.getEmployees().get(2).getEmployeeId());
		assertEquals("004", EmployeeManager.getEmployees().get(3).getEmployeeId());
	}
	
	@Test
	public void testDoesEmpExist() {
		EmployeeManager.clear();
		EmployeeManager.addEmployee(new Employee("001", "John Smith", "password", EmployeeType.cashier));
		EmployeeManager.addEmployee(new Employee("002", "Joh Smith", "password", EmployeeType.chef));
		EmployeeManager.addEmployee(new Employee("003", "Jo Smith", "password", EmployeeType.manager));
		EmployeeManager.addEmployee(new Employee("004", "J Smith", "password", EmployeeType.cashier));

		assertEquals(true, EmployeeManager.doesEmpExist(new Employee("004")));
		assertEquals(false, EmployeeManager.doesEmpExist(new Employee("0054f8")));
		assertEquals(true, EmployeeManager.doesEmpExist(new Employee("002")));
		assertEquals(false, EmployeeManager.doesEmpExist(new Employee("000")));
	}
	
	@Test
	public void testVerifyCreds() {
		EmployeeManager.clear();
		EmployeeManager.addEmployee(new Employee("001", "John Smith", "password23", EmployeeType.cashier));
		EmployeeManager.addEmployee(new Employee("002", "Joh Smith", "password45", EmployeeType.chef));
		EmployeeManager.addEmployee(new Employee("003", "Jo Smith", "password16", EmployeeType.manager));
		EmployeeManager.addEmployee(new Employee("004", "J Smith", "password61", EmployeeType.cashier));
		
		assertEquals(true, EmployeeManager.verifyCreds("002", "password45"));
		assertEquals(false, EmployeeManager.verifyCreds("002", "password23"));
		assertEquals(false, EmployeeManager.verifyCreds("04854", "password45"));
		assertEquals(true, EmployeeManager.verifyCreds("004", "password61"));
	}

}
