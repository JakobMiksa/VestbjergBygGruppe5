package model;

public class Staff extends Person {
	private String staffId;
	
	public Staff(String name, String address, String email, String phone, String staffId) {
		super(name, address, email, phone);
		
		this.staffId = staffId;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
}
