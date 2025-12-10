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
        this.orderId = generateSKU();
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


    private String generateSKU() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();

        // 3 bogstaver
        for (int i = 0; i < 3; i++) {
            int index = (int) (Math.random() * letters.length());
            sb.append(letters.charAt(index));
        }

        sb.append("-");

        // 5 tal
        for (int i = 0; i < 5; i++) {
            int digit = (int) (Math.random() * 10);
            sb.append(digit);
        }

        return sb.toString();
    }

    public boolean addOrderLine(OrderLine line) {
        if (line != null) {
            return orderLine.add(line);
        }
        return false;
    }

    public void recalculateTotal() {
        double total = 0.0;
        for (OrderLine line : orderLine) {
            total += line.getUnitPrice();
        }
        this.total = total;
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
