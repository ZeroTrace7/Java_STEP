import java.util.Scanner;

class Product {
    // Instance attributes
    private String productId;
    private String productName;
    private double price;
    private String category;
    private int stockQuantity;
    
    // Static variables
    private static int totalProducts = 0;
    private static String[] categories = {"Electronics", "Clothing", "Books", "Home & Garden", "Sports"};
    
    // Constructor
    public Product(String productId, String productName, double price, String category, int stockQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
        totalProducts++;
    }
    
    // Getter methods
    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public int getStockQuantity() { return stockQuantity; }
    
    // Setter methods
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    
    public void reduceStock(int quantity) {
        if (stockQuantity >= quantity) {
            stockQuantity -= quantity;
        }
    }
    
    public void increaseStock(int quantity) {
        stockQuantity += quantity;
    }
    
    // Static methods
    public static Product findProductById(Product[] products, String productId) {
        for (Product product : products) {
            if (product != null && product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }
    
    public static Product[] getProductsByCategory(Product[] products, String category) {
        // Count products in category first
        int count = 0;
        for (Product product : products) {
            if (product != null && product.getCategory().equalsIgnoreCase(category)) {
                count++;
            }
        }
        
        // Create array of exact size
        Product[] categoryProducts = new Product[count];
        int index = 0;
        for (Product product : products) {
            if (product != null && product.getCategory().equalsIgnoreCase(category)) {
                categoryProducts[index++] = product;
            }
        }
        return categoryProducts;
    }
    
    public static int getTotalProducts() {
        return totalProducts;
    }
    
    public static String[] getCategories() {
        return categories;
    }
    
    @Override
    public String toString() {
        return String.format("ID: %-8s | %-25s | $%-8.2f | %-15s | Stock: %d",
                productId, productName, price, category, stockQuantity);
    }
}

class ShoppingCart {
    // Instance attributes
    private String cartId;
    private String customerName;
    private Product[] products;
    private int[] quantities;
    private double cartTotal;
    private int itemCount;
    
    private static final int MAX_ITEMS = 50;
    
    // Constructor
    public ShoppingCart(String cartId, String customerName) {
        this.cartId = cartId;
        this.customerName = customerName;
        this.products = new Product[MAX_ITEMS];
        this.quantities = new int[MAX_ITEMS];
        this.cartTotal = 0.0;
        this.itemCount = 0;
    }
    
    // Method to add product to cart
    public boolean addProduct(Product product, int quantity) {
        if (product == null || quantity <= 0) {
            System.out.println("Invalid product or quantity!");
            return false;
        }
        
        if (product.getStockQuantity() < quantity) {
            System.out.println("Insufficient stock! Available: " + product.getStockQuantity());
            return false;
        }
        
        // Check if product already exists in cart
        for (int i = 0; i < itemCount; i++) {
            if (products[i].getProductId().equals(product.getProductId())) {
                if (product.getStockQuantity() < quantities[i] + quantity) {
                    System.out.println("Cannot add more items. Total would exceed available stock!");
                    return false;
                }
                quantities[i] += quantity;
                calculateTotal();
                System.out.println("Updated quantity for " + product.getProductName() + 
                                 ". New quantity: " + quantities[i]);
                return true;
            }
        }
        
        // Add new product to cart
        if (itemCount < MAX_ITEMS) {
            products[itemCount] = product;
            quantities[itemCount] = quantity;
            itemCount++;
            calculateTotal();
            System.out.println("Added " + quantity + " x " + product.getProductName() + " to cart.");
            return true;
        } else {
            System.out.println("Cart is full! Maximum " + MAX_ITEMS + " different items allowed.");
            return false;
        }
    }
    
    // Method to remove product from cart
    public boolean removeProduct(String productId) {
        for (int i = 0; i < itemCount; i++) {
            if (products[i].getProductId().equals(productId)) {
                System.out.println("Removed " + products[i].getProductName() + " from cart.");
                
                // Shift remaining items
                for (int j = i; j < itemCount - 1; j++) {
                    products[j] = products[j + 1];
                    quantities[j] = quantities[j + 1];
                }
                
                // Clear last position
                products[itemCount - 1] = null;
                quantities[itemCount - 1] = 0;
                itemCount--;
                
                calculateTotal();
                return true;
            }
        }
        System.out.println("Product not found in cart!");
        return false;
    }
    
    // Method to calculate total
    public void calculateTotal() {
        cartTotal = 0.0;
        for (int i = 0; i < itemCount; i++) {
            cartTotal += products[i].getPrice() * quantities[i];
        }
    }
    
    // Method to display cart contents
    public void displayCart() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("SHOPPING CART - " + customerName + " (Cart ID: " + cartId + ")");
        System.out.println("=".repeat(80));
        
        if (itemCount == 0) {
            System.out.println("Your cart is empty!");
        } else {
            System.out.printf("%-8s | %-25s | %-10s | %-8s | %-10s%n", 
                            "ID", "Product Name", "Price", "Qty", "Subtotal");
            System.out.println("-".repeat(80));
            
            for (int i = 0; i < itemCount; i++) {
                double subtotal = products[i].getPrice() * quantities[i];
                System.out.printf("%-8s | %-25s | $%-9.2f | %-8d | $%-9.2f%n",
                                products[i].getProductId(),
                                products[i].getProductName(),
                                products[i].getPrice(),
                                quantities[i],
                                subtotal);
            }
            System.out.println("-".repeat(80));
            System.out.printf("TOTAL AMOUNT: $%.2f%n", cartTotal);
        }
        System.out.println("=".repeat(80));
    }
    
    // Method to checkout
    public boolean checkout() {
        if (itemCount == 0) {
            System.out.println("Cannot checkout with empty cart!");
            return false;
        }
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("PROCESSING CHECKOUT...");
        System.out.println("=".repeat(50));
        
        // Update stock quantities
        for (int i = 0; i < itemCount; i++) {
            products[i].reduceStock(quantities[i]);
        }
        
        displayCart();
        System.out.println("\nCheckout successful!");
        System.out.println("Thank you for your purchase, " + customerName + "!");
        System.out.println("Order total: $" + String.format("%.2f", cartTotal));
        
        // Clear cart after checkout
        clearCart();
        return true;
    }
    
    // Helper method to clear cart
    private void clearCart() {
        for (int i = 0; i < MAX_ITEMS; i++) {
            products[i] = null;
            quantities[i] = 0;
        }
        itemCount = 0;
        cartTotal = 0.0;
    }
    
    // Getter methods
    public String getCartId() { return cartId; }
    public String getCustomerName() { return customerName; }
    public double getCartTotal() { return cartTotal; }
    public int getItemCount() { return itemCount; }
}

public class OnlineShoppingSystem {
    private static Product[] inventory;
    private static ShoppingCart cart;
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        initializeInventory();
        
        System.out.println("Welcome to Online Shopping System!");
        System.out.println("=".repeat(40));
        
        // Get customer information
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();
        
        String cartId = "CART" + System.currentTimeMillis() % 10000;
        cart = new ShoppingCart(cartId, customerName);
        
        System.out.println("Hello " + customerName + "! Your cart ID is: " + cartId);
        
        // Main menu loop
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getChoice();
            
            switch (choice) {
                case 1:
                    browseAllProducts();
                    break;
                case 2:
                    browseByCategoryMenu();
                    break;
                case 3:
                    addProductToCart();
                    break;
                case 4:
                    removeProductFromCart();
                    break;
                case 5:
                    cart.displayCart();
                    break;
                case 6:
                    if (cart.checkout()) {
                        running = false;
                    }
                    break;
                case 7:
                    System.out.println("Thank you for visiting! Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        
        scanner.close();
    }
    
    private static void initializeInventory() {
        inventory = new Product[20];
        
        // Electronics
        inventory[0] = new Product("EL001", "Smartphone", 699.99, "Electronics", 15);
        inventory[1] = new Product("EL002", "Laptop", 1299.99, "Electronics", 8);
        inventory[2] = new Product("EL003", "Headphones", 149.99, "Electronics", 25);
        inventory[3] = new Product("EL004", "Tablet", 399.99, "Electronics", 12);
        
        // Clothing
        inventory[4] = new Product("CL001", "T-Shirt", 19.99, "Clothing", 50);
        inventory[5] = new Product("CL002", "Jeans", 59.99, "Clothing", 30);
        inventory[6] = new Product("CL003", "Sneakers", 89.99, "Clothing", 20);
        inventory[7] = new Product("CL004", "Jacket", 129.99, "Clothing", 15);
        
        // Books
        inventory[8] = new Product("BK001", "Java Programming", 49.99, "Books", 40);
        inventory[9] = new Product("BK002", "Data Structures", 55.99, "Books", 35);
        inventory[10] = new Product("BK003", "Web Development", 39.99, "Books", 45);
        inventory[11] = new Product("BK004", "Machine Learning", 65.99, "Books", 25);
        
        // Home & Garden
        inventory[12] = new Product("HG001", "Coffee Maker", 79.99, "Home & Garden", 18);
        inventory[13] = new Product("HG002", "Plant Pot", 15.99, "Home & Garden", 60);
        inventory[14] = new Product("HG003", "Desk Lamp", 34.99, "Home & Garden", 22);
        inventory[15] = new Product("HG004", "Storage Box", 24.99, "Home & Garden", 35);
        
        // Sports
        inventory[16] = new Product("SP001", "Basketball", 29.99, "Sports", 40);
        inventory[17] = new Product("SP002", "Tennis Racket", 89.99, "Sports", 15);
        inventory[18] = new Product("SP003", "Yoga Mat", 25.99, "Sports", 30);
        inventory[19] = new Product("SP004", "Dumbbells", 45.99, "Sports", 20);
    }
    
    private static void displayMainMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("MAIN MENU");
        System.out.println("=".repeat(40));
        System.out.println("1. Browse All Products");
        System.out.println("2. Browse by Category");
        System.out.println("3. Add Product to Cart");
        System.out.println("4. Remove Product from Cart");
        System.out.println("5. View Shopping Cart");
        System.out.println("6. Checkout");
        System.out.println("7. Exit");
        System.out.println("=".repeat(40));
        System.out.print("Enter your choice: ");
    }
    
    private static void browseAllProducts() {
        System.out.println("\n" + "=".repeat(90));
        System.out.println("ALL PRODUCTS");
        System.out.println("=".repeat(90));
        
        for (Product product : inventory) {
            if (product != null) {
                System.out.println(product);
            }
        }
        System.out.println("=".repeat(90));
        System.out.println("Total Products: " + Product.getTotalProducts());
    }
    
    private static void browseByCategoryMenu() {
        System.out.println("\nSelect a category:");
        String[] categories = Product.getCategories();
        for (int i = 0; i < categories.length; i++) {
            System.out.println((i + 1) + ". " + categories[i]);
        }
        
        System.out.print("Enter category number: ");
        int categoryChoice = getChoice();
        
        if (categoryChoice >= 1 && categoryChoice <= categories.length) {
            String selectedCategory = categories[categoryChoice - 1];
            Product[] categoryProducts = Product.getProductsByCategory(inventory, selectedCategory);
            
            System.out.println("\n" + "=".repeat(90));
            System.out.println("PRODUCTS IN " + selectedCategory.toUpperCase());
            System.out.println("=".repeat(90));
            
            if (categoryProducts.length == 0) {
                System.out.println("No products found in this category.");
            } else {
                for (Product product : categoryProducts) {
                    System.out.println(product);
                }
            }
            System.out.println("=".repeat(90));
        } else {
            System.out.println("Invalid category selection!");
        }
    }
    
    private static void addProductToCart() {
        System.out.print("Enter Product ID to add to cart: ");
        String productId = scanner.nextLine().toUpperCase();
        
        Product product = Product.findProductById(inventory, productId);
        if (product == null) {
            System.out.println("Product not found!");
            return;
        }
        
        System.out.println("Selected: " + product);
        System.out.print("Enter quantity: ");
        int quantity = getChoice();
        
        cart.addProduct(product, quantity);
    }
    
    private static void removeProductFromCart() {
        if (cart.getItemCount() == 0) {
            System.out.println("Your cart is empty!");
            return;
        }
        
        cart.displayCart();
        System.out.print("Enter Product ID to remove: ");
        String productId = scanner.nextLine().toUpperCase();
        cart.removeProduct(productId);
    }
    
    private static int getChoice() {
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            return choice;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}