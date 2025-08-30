class Product {
    private String productId;
    private String productName;
    private double price;
    private int quantity;
    private String supplierName;
    private String category;
    
    private static int totalProducts = 0;
    private static double totalInventoryValue = 0.0;
    private static int lowStockCount = 0;
    private static String[] categories = new String[50];
    private static int categoryCount = 0;
    
    public Product(String productName, double price, int quantity, String supplierName, String category) {
        totalProducts++;
        this.productId = generateProductId();
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.supplierName = supplierName;
        this.category = category;
        
        addCategoryIfNew(category);
        updateTotalInventoryValue();
        
        System.out.println("New product added: " + productName);
    }
    
    private String generateProductId() {
        return String.format("PRD%03d", totalProducts);
    }
    
    private void addCategoryIfNew(String newCategory) {
        boolean categoryExists = false;
        for (int i = 0; i < categoryCount; i++) {
            if (categories[i].equals(newCategory)) {
                categoryExists = true;
                break;
            }
        }
        if (!categoryExists && categoryCount < categories.length) {
            categories[categoryCount] = newCategory;
            categoryCount++;
        }
    }
    
    public void addStock(int additionalQuantity) {
        if (additionalQuantity <= 0) {
            System.out.println("Cannot add negative or zero stock for: " + productName);
            return;
        }
        
        quantity += additionalQuantity;
        updateTotalInventoryValue();
        System.out.println("Added " + additionalQuantity + " units to " + productName);
        System.out.println("New stock level: " + quantity);
    }
    
    public void reduceStock(int reduceQuantity) {
        if (reduceQuantity <= 0) {
            System.out.println("Cannot reduce negative or zero stock for: " + productName);
            return;
        }
        
        if (reduceQuantity > quantity) {
            System.out.println("Cannot reduce stock! Requested: " + reduceQuantity + 
                             ", Available: " + quantity + " for " + productName);
            return;
        }
        
        quantity -= reduceQuantity;
        updateTotalInventoryValue();
        System.out.println("Reduced " + reduceQuantity + " units from " + productName);
        System.out.println("Remaining stock: " + quantity);
        
        if (isLowStock()) {
            System.out.println("WARNING: " + productName + " is now low on stock!");
        }
    }
    
    public boolean isLowStock() {
        return quantity < 10;
    }
    
    public double calculateProductValue() {
        return price * quantity;
    }
    
    public void updatePrice(double newPrice) {
        if (newPrice <= 0) {
            System.out.println("Price must be positive for: " + productName);
            return;
        }
        
        double oldPrice = this.price;
        this.price = newPrice;
        updateTotalInventoryValue();
        
        System.out.println("Price updated for " + productName);
        System.out.println("Old price: $" + String.format("%.2f", oldPrice));
        System.out.println("New price: $" + String.format("%.2f", newPrice));
    }
    
    private void updateTotalInventoryValue() {
        totalInventoryValue = 0.0;
        lowStockCount = 0;
    }
    
    public void displayProductInfo() {
        System.out.println("--- Product Information ---");
        System.out.println("Product ID: " + productId);
        System.out.println("Name: " + productName);
        System.out.println("Price: $" + String.format("%.2f", price));
        System.out.println("Quantity: " + quantity);
        System.out.println("Supplier: " + supplierName);
        System.out.println("Category: " + category);
        System.out.println("Total Value: $" + String.format("%.2f", calculateProductValue()));
        System.out.println("Stock Status: " + (isLowStock() ? "LOW STOCK!" : "Good Stock"));
        System.out.println("--------------------------");
    }
    
    public static double calculateTotalInventoryValue(Product[] products) {
        double total = 0.0;
        for (Product product : products) {
            if (product != null) {
                total += product.calculateProductValue();
            }
        }
        totalInventoryValue = total;
        return total;
    }
    
    public static Product[] findLowStockProducts(Product[] products) {
        Product[] lowStockProducts = new Product[products.length];
        int count = 0;
        
        for (Product product : products) {
            if (product != null && product.isLowStock()) {
                lowStockProducts[count] = product;
                count++;
            }
        }
        
        lowStockCount = count;
        return lowStockProducts;
    }
    
    public static void generateInventoryReport(Product[] products) {
        System.out.println("\n========== INVENTORY REPORT ==========");
        System.out.println("Total Products: " + totalProducts);
        
        double totalValue = calculateTotalInventoryValue(products);
        System.out.println("Total Inventory Value: $" + String.format("%.2f", totalValue));
        
        Product[] lowStockProducts = findLowStockProducts(products);
        System.out.println("Low Stock Products: " + lowStockCount);
        
        System.out.println("\nCategories in Inventory:");
        for (int i = 0; i < categoryCount; i++) {
            System.out.println("- " + categories[i]);
        }
        
        if (lowStockCount > 0) {
            System.out.println("\nLow Stock Alert - Products needing restock:");
            for (int i = 0; i < lowStockCount; i++) {
                System.out.println("- " + lowStockProducts[i].getProductName() + 
                                 " (Stock: " + lowStockProducts[i].getQuantity() + ")");
            }
        }
        
        System.out.println("\nProducts by Supplier:");
        String[] suppliers = new String[products.length];
        int supplierCount = 0;
        
        for (Product product : products) {
            if (product != null) {
                boolean supplierExists = false;
                for (int i = 0; i < supplierCount; i++) {
                    if (suppliers[i].equals(product.getSupplierName())) {
                        supplierExists = true;
                        break;
                    }
                }
                if (!supplierExists) {
                    suppliers[supplierCount] = product.getSupplierName();
                    supplierCount++;
                }
            }
        }
        
        for (int i = 0; i < supplierCount; i++) {
            System.out.println("\nSupplier: " + suppliers[i]);
            for (Product product : products) {
                if (product != null && product.getSupplierName().equals(suppliers[i])) {
                    System.out.println("  - " + product.getProductName() + 
                                     " (Qty: " + product.getQuantity() + 
                                     ", Value: $" + String.format("%.2f", product.calculateProductValue()) + ")");
                }
            }
        }
        
        System.out.println("=====================================");
    }
    
    public String getProductName() {
        return productName;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public String getSupplierName() {
        return supplierName;
    }
    
    public String getCategory() {
        return category;
    }
    
    public double getPrice() {
        return price;
    }
    
    public static int getTotalProducts() {
        return totalProducts;
    }
    
    public static int getLowStockCount() {
        return lowStockCount;
    }
}

public class ProductInventorySystem {
    public static void main(String[] args) {
        System.out.println("Welcome to Product Inventory Management System!");
        System.out.println("Setting up inventory...");
        
        Product[] inventory = new Product[20];
        
        System.out.println("\nAdding products to inventory:");
        inventory[0] = new Product("Laptop Dell XPS", 1200.0, 15, "Dell Corp", "Electronics");
        inventory[1] = new Product("Office Chair", 250.0, 8, "Furniture Plus", "Furniture");
        inventory[2] = new Product("Smartphone Samsung", 800.0, 25, "Samsung Ltd", "Electronics");
        inventory[3] = new Product("Desk Lamp", 45.0, 5, "Light World", "Furniture");
        inventory[4] = new Product("Wireless Mouse", 35.0, 50, "Tech Supplies", "Electronics");
        inventory[5] = new Product("Coffee Maker", 120.0, 12, "Kitchen Pro", "Appliances");
        inventory[6] = new Product("Notebook Set", 15.0, 3, "Paper Works", "Stationery");
        inventory[7] = new Product("Monitor 24inch", 300.0, 20, "Display Tech", "Electronics");
        
        System.out.println("\nDisplaying all product information:");
        for (int i = 0; i < 8; i++) {
            inventory[i].displayProductInfo();
        }
        
        System.out.println("\nTesting stock operations:");
        
        System.out.println("\nAdding stock to some products:");
        inventory[1].addStock(15);
        inventory[3].addStock(20);
        
        System.out.println("\nReducing stock from products:");
        inventory[0].reduceStock(5);
        inventory[4].reduceStock(30);
        inventory[6].reduceStock(2);
        
        System.out.println("\nTesting invalid operations:");
        inventory[0].addStock(-5);
        inventory[1].reduceStock(100);
        
        System.out.println("\nUpdating prices:");
        inventory[2].updatePrice(750.0);
        inventory[5].updatePrice(135.0);
        inventory[0].updatePrice(-100.0);
        
        System.out.println("\nChecking individual product values:");
        for (int i = 0; i < 8; i++) {
            System.out.println(inventory[i].getProductName() + 
                             " - Total Value: $" + String.format("%.2f", inventory[i].calculateProductValue()));
        }
        
        System.out.println("\nTesting low stock detection:");
        for (int i = 0; i < 8; i++) {
            if (inventory[i].isLowStock()) {
                System.out.println("LOW STOCK: " + inventory[i].getProductName() + 
                                 " (Only " + inventory[i].getQuantity() + " left)");
            }
        }
        
        System.out.println("\nGenerating comprehensive inventory report:");
        Product.generateInventoryReport(inventory);
        
        System.out.println("\nTesting more stock operations:");
        System.out.println("\nSelling products (reducing stock):");
        inventory[0].reduceStock(3);
        inventory[2].reduceStock(10);
        inventory[7].reduceStock(5);
        
        System.out.println("\nRestocking low inventory items:");
        inventory[3].addStock(25);
        inventory[6].addStock(50);
        
        System.out.println("\nFinal inventory report after transactions:");
        Product.generateInventoryReport(inventory);
        
        System.out.println("\nInventory management complete!");
        System.out.println("Total products managed: " + Product.getTotalProducts());
    }
}