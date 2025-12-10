package model;

import java.util.ArrayList;

public class Order {
	private String orderId;
	private Customer customer;
	private Staff staff;
	private ArrayList<OrderLine> orderLine;
	private OrderStatus orderStatus;
	private DeliveryStatus deliveryStatus;
	private double total;
	private String date;
	private String expiryDate;
	
	public Order() {
		this.orderLine = new ArrayList<>();
		this.orderId = "001";
	}
	
	public Order(String orderId, Customer customer, Staff staff, ArrayList<OrderLine> orderLine, OrderStatus orderStatus, DeliveryStatus deliveryStatus, double total, String date, String expiryDate) {
		this.staff = staff;
		this.customer = customer;
		this.orderId = orderId;
		this.orderStatus = orderStatus;
		this.deliveryStatus = deliveryStatus;
		this.total = total;
		this.date = date;
		this.expiryDate = expiryDate;
		this.orderLine = orderLine;
		
		orderLine = new ArrayList<>();
	}
	
	public boolean addOrderLine(OrderLine line) {
        if (line != null) {
            return orderLine.add(line);
        }
        return false;
    }
	
	public void recalculateTotal() {
        double calculatedTotal = 0.0;

        // 1. Sum of all products (existing logic)
        for (OrderLine line : orderLine) {
            calculatedTotal += line.getUnitPrice();
        }

        // 2. Delivery Fee Logic
        double deliveryCost = 500.0; // Set your price here (e.g., 100 kr)

        // Check if the status matches your Enum for delivered
        if (this.deliveryStatus == DeliveryStatus.delivered) {
            calculatedTotal += deliveryCost;
        }

        // 3. Update the final total
        this.total = calculatedTotal;
    }
    
	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ArrayList<OrderLine> getOrderLine() {
		return orderLine;
	}

	public void setOrderLine(ArrayList<OrderLine> orderLine) {
		this.orderLine = orderLine;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public DeliveryStatus getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
}