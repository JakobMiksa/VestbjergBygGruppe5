package controller;

import java.util.ArrayList;

import model.Customer;
import model.DeliveryStatus;
import model.Order;
import model.OrderLine;
import model.OrderStatus;
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
	
	public OrderController() {
		
	}
	
	public Order createOrder(String orderId, Customer customer, Staff staff, ArrayList<OrderLine> orderLine, OrderStatus orderStatus, DeliveryStatus deliveryStatus, double total, String date, String expiryDate) {
		Order newOrder = new Order();
	}
}