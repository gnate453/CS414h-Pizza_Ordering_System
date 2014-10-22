package cs414.groupH.a4.employee;


public class Employee {
	private String employeeId;
	private String name;
	private EmployeeType empType;
	
	public Employee(String employeeId, String name, EmployeeType empType) {
		this.employeeId = employeeId;
		this.name = name;
		this.empType = empType;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public String getName() {
		return name;
	}

	public EmployeeType getEmpType() {
		return empType;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		
		Employee emp = (Employee) obj;
		
		if (!this.employeeId.equals(emp.getEmployeeId())) {
			return false;
		}
		else if (!this.name.equals(emp.getName())) {
			return false;
		}
		else if (this.empType != emp.getEmpType()) {
			return false;
		}
		else {
			return true;
		}
	}

}
