package model;

public class Customer extends Person {
	private int customerId;
	
	public Customer(String name, String address, String email, String phone, int customerId) {
		super(name, address, email, phone);
		
		this.customerId = customerId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
}
