
package controller;

import model.Customer;
import model.CustomerContainer;

public class CustomerController {
	private Customer customer;
	private CustomerContainer customerCont;
	private OrderController orderController;
	
	public CustomerController() {
		this.customerCont = new CustomerContainer();
		this.orderController = orderController;
	}
	
	public Customer createCustomer(int customerId, double personalDiscount) {
		Customer customer = new Customer(customerId, personalDiscount);
		customerCont.addCustomer(customer);
		return customer;
		 
	}
	
	public Customer findCustomer(int CustomerId) {
		return CustomerCont.getCustomerById(CustomerId);
		
	}
}