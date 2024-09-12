package app;

//Represents a weapon product that can be sold in the store.
public class Weapon extends SalableProduct{
	private int damage;
	
	public Weapon(String name, String description, double price, int quantity) {
		super(name, description, price, quantity);
		this.damage = damage;
	}
	
	public int getDamage() { 
		return damage; }
}
	