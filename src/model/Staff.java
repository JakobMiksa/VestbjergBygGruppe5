package model;

public class Staff extends Person {
	private String staffId;
	private String department;
	
	public Staff(String name, String address, String email, String phone, String staffId, String department) {
		super(name, address, email, phone);
		this.department = department;
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