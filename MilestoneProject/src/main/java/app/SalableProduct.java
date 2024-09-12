package app;



public class SalableProduct implements Comparable<SalableProduct> {
	private String name;
	private String description;
	private double price;
	private int quantity;
	
	// Represents a product that can be sold in the store
	public SalableProduct(String name, String description, double price, int quantity) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		
	}
	//generated getters and setter for Quantity
	public String getName() {
		return name;
	}


	public String getDescription() {
		return description;
	}


	public double getPrice() {
		return price;
	}


	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public int compareTo(SalableProduct other) {
		int nameComparison = this.name.compareToIgnoreCase(other.name);
		if (nameComparison != 0) {
			return nameComparison;
		}
		return Double.compare(this.price,  other.price);
	}
	
	@Override
	public String toString() {
		return "Product Name: " + name + ", Description: " + description + ", Price: $" + price + ", Quantity: " + quantity;
	}
	

	}
	
	

