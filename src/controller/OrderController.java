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
	}

	public Order createOrder(Staff staff) {
		Order order = new Order();
		order.setStaff(staff);
		
		return order;
	}
	
    public boolean addProduct(Order order, Product product, int quantity) {
    	boolean res = false;
        if (product != null) {
       		OrderLine line = new OrderLine(quantity, product.getPrice().getPrice() * quantity, product); 
       		order.addOrderLine(line);
    			order.recalculateTotal();
    			res = true;
        }
        return res;
    }
    
	public void changeOrderStatus(Order order, OrderStatus newStatus) {
		if (order != null && newStatus != null) {
			order.setOrderStatus(newStatus);
        		}
	}
	
	public void changeDeliveryStatus(Order order, DeliveryStatus newStatus) {
		if (order != null && newStatus != null) {
			order.setDeliveryStatus(newStatus);
        		}
	}
	
	public boolean finishOrder(Order order) {
		if (order == null) {
			return false;
		}
		for (OrderLine line : order.getOrderLine()) {
			Product p = line.getProduct();
			
			if (p != null && p.getInventory() != null) {
				p.getInventory().decreaseStock(line.getQuantity());
			}
		}
		order.recalculateTotal();
		order.setOrderStatus(OrderStatus.finished);
		return orderCont.addOrder(order);
	}
}