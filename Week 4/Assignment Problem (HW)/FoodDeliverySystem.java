class FoodOrder {
    private String customerName;
    private String foodItem;
    private int quantity;
    private double price;
    private static final double FIXED_RATE = 150.0;
    
    public FoodOrder() {
        this.customerName = "Unknown";
        this.foodItem = "Unknown";
        this.quantity = 0;
        this.price = 0.0;
    }
    
    public FoodOrder(String foodItem) {
        this.customerName = "Unknown";
        this.foodItem = foodItem;
        this.quantity = 1;
        this.price = FIXED_RATE;
    }
    
    public FoodOrder(String foodItem, int quantity) {
        this.customerName = "Unknown";
        this.foodItem = foodItem;
        this.quantity = quantity;
        this.price = quantity * FIXED_RATE;
    }
    
    public FoodOrder(String customerName, String foodItem, int quantity, double price) {
        this.customerName = customerName;
        this.foodItem = foodItem;
        this.quantity = quantity;
        this.price = price;
    }
    
    public void printBill() {
        System.out.println("FOOD DELIVERY ORDER BILL");
        System.out.println("Customer Name : " + customerName);
        System.out.println("Food Item     : " + foodItem);
        System.out.println("Quantity      : " + quantity);
        System.out.println("Unit Price    : Rs." + String.format("%.2f", (quantity > 0 ? price / quantity : 0)));
        System.out.println("Total Price   : Rs." + String.format("%.2f", price));
        System.out.println();
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public String getFoodItem() {
        return foodItem;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public void setFoodItem(String foodItem) {
        this.foodItem = foodItem;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
}

public class FoodDeliverySystem {
    public static void main(String[] args) {
        System.out.println("FOOD DELIVERY SYSTEM");
        System.out.println();
        
        System.out.println("1. Order created with Default Constructor:");
        FoodOrder order1 = new FoodOrder();
        order1.printBill();
        
        System.out.println("2. Order created with Food Item only:");
        FoodOrder order2 = new FoodOrder("Burger");
        order2.printBill();
        
        System.out.println("3. Order created with Food Item and Quantity:");
        FoodOrder order3 = new FoodOrder("Pizza", 2);
        order3.printBill();
        
        System.out.println("4. Order created with full constructor:");
        FoodOrder order4 = new FoodOrder("John Smith", "Pasta", 1, 200.0);
        order4.printBill();
        
        System.out.println("UPDATING CUSTOMER NAMES:");
        System.out.println();
        
        order2.setCustomerName("Alice Johnson");
        order3.setCustomerName("Bob Wilson");
        
        System.out.println("Updated Order 2:");
        order2.printBill();
        
        System.out.println("Updated Order 3:");
        order3.printBill();
        
        System.out.println("MULTIPLE FOOD ORDERS:");
        System.out.println();
        
        FoodOrder[] orders = {
            new FoodOrder("Biryani"),
            new FoodOrder("Dosa", 3),
            new FoodOrder("Sandwich", 2),
            new FoodOrder("Mary Brown", "Ice Cream", 1, 80.0),
            new FoodOrder("Tom Davis", "Fried Rice", 2, 320.0)
        };
        
        String[] customers = {"Peter Parker", "Diana Prince", "Clark Kent", "", ""};
        
        for (int i = 0; i < orders.length; i++) {
            if (i < customers.length && !customers[i].isEmpty()) {
                orders[i].setCustomerName(customers[i]);
            }
            
            System.out.println("Order " + (i + 1) + ":");
            orders[i].printBill();
        }
        
        System.out.println("ORDER SUMMARY:");
        System.out.println();
        
        double totalRevenue = 0;
        int totalItems = 0;
        
        for (FoodOrder order : orders) {
            totalRevenue += order.getPrice();
            totalItems += order.getQuantity();
        }
        
        System.out.println("Total Orders: " + orders.length);
        System.out.println("Total Items: " + totalItems);
        System.out.println("Total Revenue: Rs." + String.format("%.2f", totalRevenue));
        
        System.out.println();
        System.out.println("POPULAR FOOD ITEMS:");
        System.out.println("Burger - Rs.150.0 per item");
        System.out.println("Pizza - Rs.150.0 per item");
        System.out.println("Biryani - Rs.150.0 per item");
        System.out.println("Dosa - Rs.150.0 per item");
        System.out.println("Sandwich - Rs.150.0 per item");
        
        System.out.println();
        System.out.println("CONSTRUCTOR OVERLOADING DEMONSTRATION:");
        System.out.println("Default Constructor: FoodOrder()");
        System.out.println("Single Parameter: FoodOrder(String foodItem)");
        System.out.println("Two Parameters: FoodOrder(String foodItem, int quantity)");
        System.out.println("Four Parameters: FoodOrder(String customerName, String foodItem, int quantity, double price)");
        System.out.println();
        System.out.println("All constructors successfully demonstrated!");
    }
}