
package controller;

import model.Customer;
import model.CustomerContainer;
import model.CustomerStatus;

public class CustomerController {
	private CustomerContainer customerCont = CustomerContainer.getInstance();
	
	public CustomerController() {
	}
	
	public Customer createCustomer(String name, String address, String email, String phone, int customerId, double personalDiscount, CustomerStatus customerStatus) {
		Customer customer = new Customer(name, address, email, phone, customerId, personalDiscount, customerStatus);
		boolean success = customerCont.addCustomer(customer);
		if (!success) {
			customer = null;
		}
		return customer;
	}
	
	public Customer findCustomer(int customerId) {
		return customerCont.findCustomer(customerId);
	}
}