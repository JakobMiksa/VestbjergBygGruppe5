package model;

public class Product {
	private String SKU;
	private Price price;
	private String info;
	
	private Inventory inventory;
	private Location location;
	
	public Product(String SKU, Price price, String info, Inventory inventory, Location location) {
		this.SKU = SKU;
		this.price = price;
		this.info = info;
		this.inventory = inventory;
		this.location = location;
	}

	public String getSKU() {
		return SKU;
	}

	public void setSKU(String sKU) {
		SKU = sKU;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}
