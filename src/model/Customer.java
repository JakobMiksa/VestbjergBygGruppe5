package model;

public class Customer extends Person {
	private int customerId;
	private double personalDiscount; 

	public Customer(String name, String address, String email, String phone, int customerId, double personalDiscount) {
		super(name, address, email, phone);
		
		this.customerId = customerId;
		this.personalDiscount = personalDiscount;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public double getPersonalDiscount() {
		return personalDiscount;
	}

	public void setPersonalDiscount(double personalDiscount) {
		this.personalDiscount = personalDiscount;
	}
}
