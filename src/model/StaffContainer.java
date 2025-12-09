package model;

import java.util.ArrayList;

public class StaffContainer {
	private ArrayList<Staff> staffMembers;
	private static StaffContainer instance;

	private StaffContainer() {
		staffMembers = new ArrayList<>();
	}
	
	public static StaffContainer getInstance() {
		if (instance == null) {
			instance = new StaffContainer();
		} 
		return instance;
	}
	
	public ArrayList<Staff> getStaffMembers() {
		return staffMembers;
	}
	
	public boolean addStaff(Staff staff) {
		boolean res = false;
		if (!staffMembers.contains(staff)) {
			staffMembers.add(staff);
			res = true;
		}
		return res;
	}
	
	public Staff findStaff(String staffId) {
		for (Staff staff : staffMembers) {
			if (staff.getStaffId().equals(staffId)) {
				return staff;
			}
		}
		return null;
	}
}
