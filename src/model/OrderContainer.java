package model;

import java.util.ArrayList;

public class OrderContainer {
	private ArrayList<Order> orders;
	private static OrderContainer instance;
	
	private OrderContainer() {
		orders = new ArrayList<>();
	}
	
	public static OrderContainer getInstance() {
		if (instance == null) {
			instance = new OrderContainer();
		} 
		return instance;
	}
	
	public ArrayList<Order> getOrders() {
		return orders;
	}
	
	public boolean addOrder(Order newOrder) {
		boolean res = false;
		if (orders.size() == 0) {
			orders.add(newOrder);
			res = true;
		} else {
			for (Order existingOrder : orders) {
				if (existingOrder.getOrderId() == newOrder.getOrderId()) {
					System.out.println("Fejl: Order med orderId: " + newOrder.getOrderId() + " findes allerede.);");
					res = false;
					return res;
				}
			}
			orders.add(newOrder);
			res = true;
		}
		return res;
	}
	
	public Order findOrder(String orderId) {
		for (Order order : orders) {
			if (order.getOrderId() == orderId) {
				return order;
			}
		}
		return null;
	}
}
