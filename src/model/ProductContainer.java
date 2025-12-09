package model;

import java.util.ArrayList;

public class ProductContainer {
	private ArrayList<Product> products;
	private static ProductContainer instance;
	
	private ProductContainer() {
		products = new ArrayList<>();
	}
	
	public static ProductContainer getInstance() {
		if (instance == null) {
			instance = new ProductContainer();
		} 
		return instance;
	}
	
	public ArrayList<Product> getProducts() {
		return products;
	}
	
	public boolean addProduct(Product product) {
		boolean res = false;
		if (products.size() == 0) {
			products.add(product);
			res = true;
		} else {
			for (Product existingProduct : products) {
				if (existingProduct.getSKU().equals(product.getSKU())) {
					System.out.println("Fejl: Vare med SKU: " + product.getSKU() + " findes allerede.");
					res = false;
					return res;
				}
			}
			products.add(product);
			res = true;
		}
		return res;
	}
	
	public Product findProduct(String SKU) {
		for (Product product : products) {
			if (product.getSKU().equals(SKU)) {
				return product;
			}
		}
		return null;
	}
}
