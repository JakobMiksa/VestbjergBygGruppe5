package model;

import java.util.ArrayList;

public class Order {
	private Staff staff;
	private Customer customer;
	private ArrayList<OrderLine> orderLine;
	private String orderId;
	private OrderStatus orderStatus;
	private DeliveryStatus deliveryStatus;
	private double total;
	private String date;
	private String expiryDate;
	
	public Order(Staff staff, Customer customer, ArrayList<OrderLine> orderLine, String orderId, OrderStatus orderStatus, DeliveryStatus deliveryStatus, double total, String date, String expiryDate) {
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