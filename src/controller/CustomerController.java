
package controller;

import model.Customer;
import model.CustomerContainer;

public class CustomerController {
	private CustomerContainer customerCont = CustomerContainer.getInstance();
	
	public CustomerController() {
	}
	
	public Customer createCustomer(String name, String address, String email, String phone, int customerId, double personalDiscount) {
		Customer customer = new Customer(name, address, email, phone, customerId, personalDiscount);
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