package controller;

import java.util.ArrayList;

import model.Inventory;
import model.InventoryContainer;
import model.Location;
import model.Product;

public class InventoryController {
	 private InventoryContainer inventoryCont = InventoryContainer.getInstance();
	 
	 	public InventoryController() {
		 
	 	}
	 	public Inventory createInventory(Location location, Product product, int minStock, int maxStock, int currStock) {
	 	Inventory newInventory = new Inventory(location, product, minStock, maxStock, currStock); 
	 	boolean success = inventoryCont.addInventory(newInventory); 
	 	if (!success) {
	 		newInventory = null;
	 	}
	 	return newInventory; 
	 	}
	
}
