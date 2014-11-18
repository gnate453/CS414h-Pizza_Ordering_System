package cs414.groupH.a5.employee;


public class Employee {
	private String employeeId;
	private String name;
	private String password;
	private EmployeeType empType;
	
	public Employee(String employeeId) {
		this.employeeId = employeeId;
		this.name = "";
		this.password = "";
		this.empType = null;
	}
	public Employee(String employeeId, String name, String password, EmployeeType empType) {
		this.employeeId = employeeId;
		this.name = name;
		this.password = password;
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
	
	public boolean verifyCreds(String givenPwd) {
		if (givenPwd.equals(password)) {
			return true;
		}
		else {
			return false;
		}
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
		else {
			return true;
		}
	}

}
