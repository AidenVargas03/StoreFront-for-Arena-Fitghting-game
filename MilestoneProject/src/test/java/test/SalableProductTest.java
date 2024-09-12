package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import app.SalableProduct;

public class SalableProductTest {
	private SalableProduct product;
	
	@Before
	public void setUp() {
		product = new SalableProduct("TestProduct", "This is a test product", 10.99, 50);
	}
	
	@Test
	public void testGetName() {
		assertEquals("TestProduct", product.getName());
	}
	
	@Test 
	public void testGetDescription() {
		assertEquals("This is a test product", product.getDescription());
	}
	
	@Test
	public void testGetPrice() {
		assertEquals(10.99, product.getPrice(), 0.01);
	}
	
	@Test
	public void testGetQuantity() {
		assertEquals(50, product.getQuantity());
	}
	
	@Test
	public void testSetQuantity() {
		product.setQuantity(30);
		assertEquals(30, product.getQuantity());
	}
	
	
	
	
}
