
package controller;

import model.Customer;
import model.CustomerContainer;

public class CustomerController {
	private Customer customer;
	private CustomerContainer customerCont;
	private OrderController orderController;
	
	public CustomerController() {
		this.customerCont = customerCont;
		this.orderController = orderController;
	}
	
	public Customer createCustomer(String name, String address, String email, String phone, int customerId, double personalDiscount) {
		Customer customer = new Customer(name, address, email, phone, customerId, personalDiscount);
		customerCont.addCustomer(customer);
		return customer;
		 
	}
	
	public Customer findCustomer(int CustomerId) {
		return CustomerCont.getCustomerById(CustomerId);
		
	}
}