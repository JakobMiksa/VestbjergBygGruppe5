package controller;

import model.Staff;
import model.StaffContainer;

import java.util.ArrayList;

public class StaffController {
	private StaffContainer staffContainer = StaffContainer.getInstance();
	
	public StaffController() {
	}
	
	public boolean createStaff(String name, String address, String email, String phone, String staffId, String department) {
		Staff newStaff = new Staff(name, address, email, phone, staffId, department);
		return staffContainer.addStaff(newStaff);
	}
	
	public Staff findStaff(String staffId) {
		return staffContainer.findStaff(staffId);
	}
} 
