// Parent class - Fruit
class Fruit {
    // Protected fields can be accessed by child classes
    protected String color;
    protected String taste;
    
    // Constructor for Fruit class
    public Fruit(String color, String taste) {
        this.color = color;
        this.taste = taste;
    }
    
    // Method to display fruit information
    public void displayInfo() {
        System.out.println("Color: " + color);
        System.out.println("Taste: " + taste);
    }
}

// Child class - Apple extends Fruit
class Apple extends Fruit {
    // Additional field specific to Apple
    protected String variety;
    
    // Constructor for Apple class
    public Apple(String color, String taste, String variety) {
        // Call parent class constructor using super()
        super(color, taste);
        this.variety = variety;
    }
    
    // Override the displayInfo method to include variety
    @Override
    public void displayInfo() {
        // Call parent class method first
        super.displayInfo();
        System.out.println("Variety: " + variety);
    }
    
    // Additional method specific to Apple
    public void appleInfo() {
        System.out.println("This is a " + variety + " apple with " + color + " color and " + taste + " taste.");
    }
}

// Main class to test the inheritance
public class FruitAppleTest {
    public static void main(String[] args) {
        System.out.println("=== Testing Fruit and Apple Classes ===\n");
        
        // Create a Fruit object
        System.out.println("1. Creating a basic Fruit:");
        Fruit fruit = new Fruit("Yellow", "Sweet");
        fruit.displayInfo();
        
        System.out.println("\n" + "=".repeat(40) + "\n");
        
        // Create an Apple object
        System.out.println("2. Creating an Apple object:");
        Apple apple = new Apple("Red", "Sweet and Crispy", "Fuji");
        apple.displayInfo();
        
        System.out.println("\n" + "=".repeat(40) + "\n");
        
        // Test accessing inherited fields directly
        System.out.println("3. Accessing inherited fields directly:");
        System.out.println("Apple color: " + apple.color);
        System.out.println("Apple taste: " + apple.taste);
        System.out.println("Apple variety: " + apple.variety);
        
        System.out.println("\n" + "=".repeat(40) + "\n");
        
        // Test Apple-specific method
        System.out.println("4. Using Apple-specific method:");
        apple.appleInfo();
        
        System.out.println("\n" + "=".repeat(40) + "\n");
        
        // Create different types of apples
        System.out.println("5. Creating different Apple varieties:");
        Apple greenApple = new Apple("Green", "Tart", "Granny Smith");
        Apple redApple = new Apple("Dark Red", "Sweet", "Red Delicious");
        
        System.out.println("Green Apple:");
        greenApple.displayInfo();
        
        System.out.println("\nRed Apple:");
        redApple.displayInfo();
    }
}