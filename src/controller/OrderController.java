package controller;

import model.Customer;
import model.Order;
import model.Product;
import model.Staff;

public class OrderController {
	private Order order;
	private Product product;
	private Staff staff;
	private Customer customer;
	private CustomerController customerCtrl;
	private ProductController productCtrl;
	private InventoryController inventoryCtrl;
	private StaffController staffCtrl;
	private LocationController locationCtrl;
}
