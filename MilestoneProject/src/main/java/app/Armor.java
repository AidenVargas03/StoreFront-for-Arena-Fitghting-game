package app;


// Represents an armor product that can be sold in the store.
public class Armor extends SalableProduct{
	private int defense;
	
	// constructor
	public Armor(String name, String description, double price, int quantity) {
		super(name, description, price, quantity);
		this.defense = defense;
	}
	
	// getter for defense
	public int getDefense() { 
		return defense;
	
	}
}
