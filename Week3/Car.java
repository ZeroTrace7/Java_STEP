public class Car {
    // Instance variables (attributes) - These represent the properties of a car
    private String brand;
    private String model;
    private int year;
    private String color;
    private boolean isRunning;
    
    // Constructor - This is like the "blueprint" for creating a new car
    public Car(String brand, String model, int year, String color) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.isRunning = false; // New cars start with engine off
    }
    
    // Instance method - Start the car's engine
    public void startEngine() {
        if (!isRunning) {
            isRunning = true;
            System.out.println("🚗 " + brand + " " + model + " engine started! Vroom vroom!");
        } else {
            System.out.println("⚠️  " + brand + " " + model + " engine is already running!");
        }
    }
    
    // Instance method - Stop the car's engine
    public void stopEngine() {
        if (isRunning) {
            isRunning = false;
            System.out.println("🔴 " + brand + " " + model + " engine stopped.");
        } else {
            System.out.println("⚠️  " + brand + " " + model + " engine is already off!");
        }
    }
    
    // Instance method - Display all car information
    public void displayInfo() {
        System.out.println("=== CAR INFORMATION ===");
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Color: " + color);
        System.out.println("Engine Status: " + (isRunning ? "Running" : "Off"));
        System.out.println("Age: " + getAge() + " years old");
        System.out.println("========================\n");
    }
    
    // Instance method - Calculate car's age
    public int getAge() {
        int currentYear = 2024; // In a real app, you'd use Calendar.getInstance().get(Calendar.YEAR)
        return currentYear - year;
    }
    
    // Getter methods (optional but good practice)
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public boolean isEngineRunning() { return isRunning; }
    
    public static void main(String[] args) {
        System.out.println("=== CLASSES AND OBJECTS DEMONSTRATION ===\n");
        
        // Create 3 different Car objects with different attributes
        // Each object is a unique instance of the Car class
        Car car1 = new Car("Toyota", "Camry", 2020, "Silver");
        Car car2 = new Car("BMW", "X5", 2019, "Black");
        Car car3 = new Car("Honda", "Civic", 2022, "Red");
        
        System.out.println("🏭 Three cars have been manufactured (objects created)!\n");
        
        // Demonstrate calling methods on each object
        // Each car maintains its own independent state
        
        System.out.println("--- Car 1 Operations ---");
        car1.displayInfo();
        car1.startEngine();
        System.out.println("Car 1 engine running: " + car1.isEngineRunning() + "\n");
        
        System.out.println("--- Car 2 Operations ---");
        car2.displayInfo();
        car2.startEngine();
        car2.stopEngine();
        System.out.println("Car 2 engine running: " + car2.isEngineRunning() + "\n");
        
        System.out.println("--- Car 3 Operations ---");
        car3.displayInfo();
        car3.startEngine();
        car3.startEngine(); // Try to start again (should show warning)
        System.out.println("Car 3 engine running: " + car3.isEngineRunning() + "\n");
        
        // Show how each object maintains its own state
        System.out.println("=== INDEPENDENT STATE DEMONSTRATION ===");
        System.out.println("Car 1 (" + car1.getBrand() + " " + car1.getModel() + ") - Engine: " + 
                          (car1.isEngineRunning() ? "ON" : "OFF"));
        System.out.println("Car 2 (" + car2.getBrand() + " " + car2.getModel() + ") - Engine: " + 
                          (car2.isEngineRunning() ? "ON" : "OFF"));
        System.out.println("Car 3 (" + car3.getBrand() + " " + car3.getModel() + ") - Engine: " + 
                          (car3.isEngineRunning() ? "ON" : "OFF"));
        
        
        
        System.out.println("\n🎓 This demonstrates Object-Oriented Programming fundamentals:");
        System.out.println("   • Classes define the structure and behavior");
        System.out.println("   • Objects are instances with their own data");
        System.out.println("   • Each object maintains independent state");
        System.out.println("   • Methods operate on object-specific data");
    }
}