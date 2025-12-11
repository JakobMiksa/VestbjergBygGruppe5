package controller;

import java.util.ArrayList;

import model.Inventory;
import model.Location;
import model.Price;
import model.Product;
import model.ProductContainer;

public class ProductController {
	private ProductContainer productCont = ProductContainer.getInstance();

	public ProductController() {
	}

	public Product createProduct(String SKU, Price price, String info, Inventory inventory, Location location) {
		Product newProduct = new Product(SKU, price, info, inventory, location);
		boolean success = productCont.addProduct(newProduct);
		if (!success) {
			newProduct = null;
		}
		return newProduct;
	}

	public Product findProduct(String SKU) {
		return productCont.findProduct(SKU);
	}
	
	public ArrayList<Product> getAllProducts() {
		return productCont.getProducts();
	}
}