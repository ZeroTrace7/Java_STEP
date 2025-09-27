public class FoodDeliveryApp {
    
    public double calculateDeliveryCharges(double distance) {
        double cost = distance * 2.50;
        System.out.println("Basic delivery:");
        System.out.println("Distance: " + distance + " km");
        System.out.println("Cost: $" + cost);
        return cost;
    }
    
    public double calculateDeliveryCharges(double distance, boolean isPremium) {
        double cost = distance * 2.50;
        
        System.out.println("Premium delivery:");
        System.out.println("Distance: " + distance + " km");
        System.out.println("Base cost: $" + cost);
        
        if (isPremium) {
            cost = cost + 5.00;
            System.out.println("Premium fee: $5.00");
        }
        
        System.out.println("Total cost: $" + cost);
        return cost;
    }
    
    public double calculateDeliveryCharges(double distance, int numberOfOrders) {
        double baseCost = distance * 2.50;
        double discount = numberOfOrders * 1.00;
        double finalCost = baseCost - discount;
        
        if (finalCost < 2.00) {
            finalCost = 2.00;
        }
        
        System.out.println("Group delivery:");
        System.out.println("Distance: " + distance + " km");
        System.out.println("Base cost: $" + baseCost);
        System.out.println("Orders: " + numberOfOrders);
        System.out.println("Discount: $" + discount);
        System.out.println("Final cost: $" + finalCost);
        return finalCost;
    }
    
    public double calculateDeliveryCharges(double distance, double discountPercent, double orderAmount) {
        double baseCost = distance * 2.50;
        double discount = baseCost * discountPercent / 100;
        double finalCost = baseCost - discount;
        
        System.out.println("Festival special:");
        System.out.println("Distance: " + distance + " km");
        System.out.println("Order amount: $" + orderAmount);
        System.out.println("Base cost: $" + baseCost);
        System.out.println("Discount: $" + discount);
        
        if (orderAmount >= 50.00) {
            finalCost = 0.0;
            System.out.println("Free delivery! Order over $50");
        }
        
        System.out.println("Final cost: $" + finalCost);
        return finalCost;
    }
    
    public static void main(String[] args) {
        FoodDeliveryApp app = new FoodDeliveryApp();
        
        System.out.println("Food Delivery Calculator");
        System.out.println();
        
        app.calculateDeliveryCharges(5.0);
        System.out.println();
        
        app.calculateDeliveryCharges(8.0, true);
        System.out.println();
        
        app.calculateDeliveryCharges(6.0, 3);
        System.out.println();
        
        app.calculateDeliveryCharges(7.0, 20.0, 30.00);
        System.out.println();
        
        app.calculateDeliveryCharges(10.0, 15.0, 60.00);
        System.out.println();
        
        System.out.println("Same method name with different parameters!");
    }
}