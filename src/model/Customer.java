package model;

public class Customer extends Person {
	private int customerId;
	private CustomerStatus customerStatus;
	private double personalDiscount; 

	public Customer(String name, String address, String email, String phone, int customerId, double personalDiscount, CustomerStatus customerStatus) {
		super(name, address, email, phone);
		
		this.customerId = customerId;
		this.personalDiscount = personalDiscount;
		this.customerStatus = customerStatus.Private;
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

	public CustomerStatus getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(CustomerStatus customerStatus) {
		this.customerStatus = customerStatus;
	}
}
