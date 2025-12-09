package controller;

import java.util.ArrayList;

import model.Customer;
import model.DeliveryStatus;
import model.Order;
import model.OrderContainer;
import model.OrderLine;
import model.OrderStatus;
import model.Product;
import model.Staff;

public class OrderController {
	private OrderContainer orderCont = OrderContainer.getInstance();
	
	public OrderController() {
		super();
	}
	
	public Order createOrder(String orderId, Customer customer, Staff staff, ArrayList<OrderLine> orderLine, OrderStatus orderStatus, DeliveryStatus deliveryStatus, double total, String date, String expiryDate) {
		Order newOrder = new Order(orderId, customer, staff, orderLine, orderStatus, deliveryStatus, total, expiryDate, expiryDate);
		boolean success = orderCont.addOrder(newOrder);
		if (!success) {
			newOrder = null;
		}
		return newOrder;
	}
	
    public boolean addProduct(Order order, Product product, int quantity) {
    	boolean res = false;
        if (product == null) {
        	res = true;
        	return res;
        }
        
        OrderLine line = new OrderLine(quantity, product.getPrice().getPrice() * quantity, product);
        order.addOrderLine(line);
        order.recalculateTotal();
        return res;
    }
    
	//public Product findProduct(String SKU) {
		
	//}
}