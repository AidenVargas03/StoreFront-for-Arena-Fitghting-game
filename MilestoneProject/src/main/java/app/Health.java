package app;

//Represents a health product that can be sold in the store.
public class Health extends SalableProduct{
	private int healthPoints;
	public Health(String name, String description, double price, int quantity) {
		super(name, description, price, quantity);
		this.healthPoints = healthPoints;
	}
	
	public int getHealthPoints() {
		return healthPoints;
		
	}
}
