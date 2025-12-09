package model;

public class Staff extends Person {
	private String staffId;
	private String department;
	
	public Staff(String department, String staffId) {
		
		this.staffId = staffId;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
}
}