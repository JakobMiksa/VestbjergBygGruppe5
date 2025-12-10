package model;

import java.util.ArrayList;

public class CustomerContainer {
	private ArrayList<Customer> customers;
	private static CustomerContainer instance;
	
	private CustomerContainer() {
		customers = new ArrayList<>();
	}
	
	public static CustomerContainer getInstance() {
		if (instance == null) {
			instance = new CustomerContainer();
		} 
		return instance;
	}
	
	public ArrayList<Customer> getCustomers() {
		return customers;
	}
	
	public boolean addCustomer(Customer customer) {
		boolean res = false;
		if (customers.size() == 0) {
			customers.add(customer);
			res = true;
		} else {
			for (Customer existingCustomer : customers) {
				if (existingCustomer.getCustomerId() == customer.getCustomerId()) {
					res = false;
					return res;
				}
			}
			customers.add(customer);
			res = true;
		}
		return res;
	}
	
	public Customer findCustomer(int customerId) {
		for (Customer customer : customers) {
			if (customer.getCustomerId() == customerId) {
				return customer;
			}
		}
		return null;
	}
}
