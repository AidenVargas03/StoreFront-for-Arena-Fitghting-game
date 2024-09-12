package app;

import java.util.ArrayList;
import java.util.List;

// This manages the shopping cart for the store
public class ShoppingCart {
    private List<SalableProduct> cartItems;

    // Constructor
    public ShoppingCart() {
        this.cartItems = new ArrayList<>();
    }

    // This adds a product to the cart
    public void addToCart(SalableProduct product, int quantity) {
        SalableProduct existingProduct = getProduct(product.getName());
        if (existingProduct != null) {
            existingProduct.setQuantity(existingProduct.getQuantity() + quantity);
        } else {
            SalableProduct cartProduct = new SalableProduct(product.getName(), product.getDescription(), product.getPrice(), quantity);
            cartItems.add(cartProduct);
        }
    }

    // This removes a product from the cart
    public void removeFromCart(String productName, int quantity) {
        SalableProduct productToRemove = null;
        for (SalableProduct product : cartItems) {
            if (product.getName().equals(productName)) {
                if (product.getQuantity() == quantity) {
                    productToRemove = product;
                    break;
                } else if (product.getQuantity() > quantity) {
                    product.setQuantity(product.getQuantity() - quantity);
                    return;
                } else {
                    // Handle scenario where quantity to remove is more than available
                    System.out.println("Error: Attempting to remove more quantity than available in cart.");
                    return;
                }
            }
        }
        if (productToRemove != null) {
            cartItems.remove(productToRemove);
        }
    }

    // This returns the contents of the cart
    public List<SalableProduct> getCartContents() {
        return new ArrayList<>(cartItems);
    }

    // This empties the cart
    public void emptyCart() {
        cartItems.clear();
    }

    // This retrieves a product from the cart by name
    public SalableProduct getProduct(String productName) {
        for (SalableProduct product : cartItems) {
            if (product.getName().equals(productName)) {
                return product;
            }
        }
        return null;
    }
}
