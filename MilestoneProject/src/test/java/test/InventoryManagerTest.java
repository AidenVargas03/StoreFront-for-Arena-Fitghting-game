package test;
import org.junit.Before;
import org.junit.Test;

import app.InventoryManager;
import app.SalableProduct;

import static org.junit.Assert.*;
import java.util.List;

public class InventoryManagerTest {
    private InventoryManager inventoryManager;

    @Before
    public void setUp() {
        inventoryManager = new InventoryManager();
        SalableProduct product1 = new SalableProduct("Product1", "Description1", 5.99, 10);
        SalableProduct product2 = new SalableProduct("Product2", "Description2", 3.99, 20);
        inventoryManager.addProduct(product1);
        inventoryManager.addProduct(product2);
    }

    @Test
    public void testInitializeFromJsonString() {
        String jsonString = "[{\"name\":\"Product3\",\"description\":\"Description3\",\"price\":2.99,\"quantity\":15}]";
        inventoryManager.initializeFromJsonString(jsonString);
        assertEquals(1, inventoryManager.getAllProducts().size());
    }

    @Test
    public void testToJsonString() {
        String jsonString = inventoryManager.toJsonString();
        assertTrue(jsonString.contains("\"name\":\"Product1\""));
        assertTrue(jsonString.contains("\"name\":\"Product2\""));
    }

    @Test
    public void testAddProduct() {
        SalableProduct product = new SalableProduct("Product3", "Description3", 2.99, 15);
        inventoryManager.addProduct(product);
        assertEquals(3, inventoryManager.getAllProducts().size());
    }

    @Test
    public void testRemoveProduct() {
        inventoryManager.removeProduct("Product1", 5);
        SalableProduct product = inventoryManager.getProduct("Product1");
        assertEquals(5, product.getQuantity());
    }

    @Test
    public void testGetProduct() {
        SalableProduct product = inventoryManager.getProduct("Product1");
        assertNotNull(product);
    }

    @Test
    public void testGetAllProducts() {
        List<SalableProduct> products = inventoryManager.getAllProducts();
        assertEquals(2, products.size());
    }
}
