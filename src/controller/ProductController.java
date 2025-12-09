package controller;

import model.Inventory;
import model.Location;
import model.InventoryContainer;
import model.Price;
import model.Product;
import model.ProductContainer;

public class ProductController {
	private ProductContainer productCont;
	private InventoryController inventoryCtrl;
	private InventoryContainer inventoryCont;

	public ProductController(Product product, ProductContainer productCont, InventoryController inventoryCtrl, InventoryContainer inventoryCont) {
		this.productCont = productCont;
		this.inventoryCtrl = inventoryCtrl;
		this.inventoryCont = inventoryCont;
	}

	public Product createProduct(String SKU, Price price, String info, Inventory inventory, Location location) {
		Product newProduct = new Product(SKU, price, info, inventory, location);
		boolean success = productCont.addProduct(newProduct);
		if (!success) {
			newProduct = null;
		}
		return newProduct;
}

}