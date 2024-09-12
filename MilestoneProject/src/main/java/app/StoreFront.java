package app;

import java.util.Scanner;

import admin.AdminService;

import java.util.List;

public class StoreFront {
    private Scanner scanner;
    private InventoryManager inventoryManager;
    private ShoppingCart shoppingCart;

    public StoreFront() {
        this.inventoryManager = new InventoryManager();
        this.shoppingCart = new ShoppingCart();
        this.scanner = new Scanner(System.in);
        initializeStore();  // Initialize store with products
        startAdminService(); 
    }

    private void initializeStore() {
        try {
            inventoryManager.initializeFromJson("inventory.json");
        } catch (CustomException e) {
            System.err.println(e.getMessage());
        }
    }
    private void startAdminService() {
        AdminService adminService = new AdminService(inventoryManager);
        adminService.startServer(12345);  // Start server on port 12345
    }

    
    public void run() {
        System.out.println("Welcome to the Store! Choose an option:");
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    viewAllProducts();
                    break;
                case 2:
                    addProductToCart();
                    break;
                case 3:
                    removeProductFromCart();
                    break;
                case 4:
                    viewCart();
                    break;
                case 5:
                    checkout();
                    break;
                case 6:
                    System.out.println("Thank you for shopping!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n1. View All Products");
        System.out.println("2. Add Product to Cart");
        System.out.println("3. Remove Product from Cart");
        System.out.println("4. View Cart");
        System.out.println("5. Checkout");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private void viewAllProducts() {
        List<SalableProduct> products = inventoryManager.getAllProducts();
        for (SalableProduct product : products) {
            System.out.println(product);
        }
    }

    private void addProductToCart() {
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();
        SalableProduct product = inventoryManager.getProduct(productName);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        if (quantity <= 0) {
            System.out.println("Invalid quantity.");
            return;
        }

        if (product.getQuantity() < quantity) {
            System.out.println("Insufficient stock for " + productName);
            return;
        }

        shoppingCart.addToCart(product, quantity);
        inventoryManager.removeProduct(productName, quantity);
        System.out.println(quantity + " of " + productName + " added to cart.");
    }

    private void removeProductFromCart() {
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();
        SalableProduct product = shoppingCart.getProduct(productName);
        if (product == null) {
            System.out.println("Product not found in cart.");
            return;
        }

        System.out.print("Enter quantity to remove: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        if (quantity <= 0) {
            System.out.println("Invalid quantity.");
            return;
        }

        shoppingCart.removeFromCart(productName, quantity);
        inventoryManager.addProduct(product);
        System.out.println(quantity + " of " + productName + " removed from cart.");
    }

    private void viewCart() {
        List<SalableProduct> cartItems = shoppingCart.getCartContents();
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Items in your cart:");
            for (SalableProduct item : cartItems) {
                System.out.println(item.getName() + " - Quantity: " + item.getQuantity());
            }
        }
    }

    private void checkout() {
        List<SalableProduct> cartItems = shoppingCart.getCartContents();
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty. Nothing to checkout.");
        } else {
            System.out.println("Items in your cart:");
            for (SalableProduct item : cartItems) {
                System.out.println(item.getName() + " - Quantity: " + item.getQuantity());
            }
            System.out.println("Total amount to pay: $" + calculateTotal(cartItems));
            shoppingCart.emptyCart();
            System.out.println("Thank you for your purchase!");
        }
    }

    private double calculateTotal(List<SalableProduct> cartItems) {
        double total = 0;
        for (SalableProduct item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

    public static void main(String[] args) {
        StoreFront store = new StoreFront();
        store.run();
    }
}
