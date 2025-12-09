package model;

public class Inventory {
	private int minStock;
	private int maxStock;
	private int currStock;
	
	private Product product;
	private Location location;
	
	public Inventory(Location location, Product product, int minStock, int maxStock, int currStock) {
		this.location = location;
		this.minStock = minStock;
		this.maxStock = maxStock;
		this.currStock = currStock;
		this.product = product;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getMinStock() {
		return minStock;
	}

	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}

	public int getMaxStock() {
		return maxStock;
	}

	public void setMaxStock(int maxStock) {
		this.maxStock = maxStock;
	}

	public int getCurrStock() {
		return currStock;
	}

	public void setCurrStock(int currStock) {
		this.currStock = currStock;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
