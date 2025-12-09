package controller;

import model.InventoryContainer;
import model.Product;
import model.ProductContainer;

public class ProductController {
private Product product;
private ProductContainer productCont;
private InventoryController inventoryCtrl;
private InventoryContainer inventoryCont;

public ProductController(Product product, ProductContainer productCont, InventoryController inventoryCtrl, InventoryContainer inventoryCont) {
	
	this.product = product;
	this.productCont = productCont;
	this.inventoryCtrl = inventoryCtrl;
	this.inventoryCont = inventoryCont;
}
