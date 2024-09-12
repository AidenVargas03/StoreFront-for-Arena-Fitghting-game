package app;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    private List<SalableProduct> products;

    public InventoryManager() {
        this.products = new ArrayList<>();
    }

    public void initializeFromJson(String filePath) throws CustomException {
        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            Type productListType = new TypeToken<ArrayList<SalableProduct>>() {}.getType();
            this.products = gson.fromJson(reader, productListType);
        } catch (IOException e) {
            throw new CustomException("Failed to initialize inventory from JSON file: " + e.getMessage());
        }
    }

    // New method to initialize from JSON string
    public void initializeFromJsonString(String jsonString) {
        Gson gson = new Gson();
        Type productListType = new TypeToken<ArrayList<SalableProduct>>() {}.getType();
        this.products = gson.fromJson(jsonString, productListType);
    }

    // New method to convert products to JSON string
    public String toJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this.products);
    }

    public void addProduct(SalableProduct product) {
        this.products.add(product);
    }

    public void removeProduct(String productName, int quantity) {
        SalableProduct product = getProduct(productName);
        if (product != null && product.getQuantity() >= quantity) {
            product.setQuantity(product.getQuantity() - quantity);
        }
    }

    public SalableProduct getProduct(String productName) {
        for (SalableProduct product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }

    public List<SalableProduct> getAllProducts() {
        return new ArrayList<>(this.products);
    }
}
