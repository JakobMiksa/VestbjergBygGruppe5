package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.*;

class ProductContainerTest {
	
	private ProductContainer container;
	private Product product1;
	private Product product2;

	@BeforeEach
	void setUp() {
		ProductContainer.instance = null;
		container = ProductContainer.getInstance();
		
		Location loc = new Location("Lager");
		Price price = new Price(100);
		
		product1 = new Product("SKU_001", price, "Hammer", null, loc);
		product2 = new Product("SKU_002", price, "Sav", null, loc);
	}

	@Test
	void testSingletonInstance() {
		ProductContainer c1 = ProductContainer.getInstance();
		ProductContainer c2 = ProductContainer.getInstance();
		
		assertSame(c1, c2);
	}

	@Test
	void testAddAndFindProduct() {
		container.addProduct(product1);
		
		Product found = container.findProduct("SKU_001");
		
		assertNotNull(found);
		assertEquals("Hammer", found.getInfo());
	}
	
	@Test
	void testFindNonExistentProductReturnsNull() {
		container.addProduct(product1);
		
		Product result = container.findProduct("SKU_999");
		
		assertNull(result);
	}

	@Test
	void testCannotAddDuplicateSKU() {
		container.addProduct(product1);
		
		Product duplicateProduct = new Product("SKU_001", new Price(200), "Anden Hammer", null, new Location("Butik"));
		
		boolean result = container.addProduct(duplicateProduct);
		
		assertFalse(result);
		assertEquals(1, container.getProducts().size());
	}
}