package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.OrderController;
import model.*;

class OrderControllerTest {
	
	private OrderController orderCtrl;
	private Staff staff;
	private Product product;
	private Inventory inventory;

	@BeforeEach
	void setUp() {
		orderCtrl = new OrderController();
		
		staff = new Staff("Test Medarbejder", "Testvej 1", "test@mail.dk", "12345678", "staff1", "Trælast");
		
		Location loc = new Location("Lager 1");
		Price price = new Price(100);
		
		product = new Product("SKU123", price, "Test Produkt", null, loc);
		
		inventory = new Inventory(loc, product, 2, 20, 10);
		
		product.setInventory(inventory);
	}

	@Test
	void testCreateOrder() {
		Order order = orderCtrl.createOrder(staff);
		
		assertNotNull(order);
		assertEquals(staff, order.getStaff());
	}

	@Test
	void testAddProduct() {
		Order order = orderCtrl.createOrder(staff);
		int antal = 5;
		
		orderCtrl.addProduct(order, product, antal);
		
		assertEquals(1, order.getOrderLine().size());
		
		assertEquals(500.0, order.getTotal());
	}
	
	@Test
	void testFinishOrderUpdatesStock() {
		Order order = orderCtrl.createOrder(staff);
		int købsAntal = 3;
		
		orderCtrl.addProduct(order, product, købsAntal);
		
		boolean success = orderCtrl.finishOrder(order);
		
		assertTrue(success);
		
		int forventetLager = 7;
		int faktiskLager = product.getInventory().getCurrStock();
		
		assertEquals(forventetLager, faktiskLager);
	}
}