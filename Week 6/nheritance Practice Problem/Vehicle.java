// File: Vehicle.java (Parent Class)
public class Vehicle {
    // Protected fields - accessible to subclasses
    protected String brand;
    protected String model;
    protected int year;
    protected String engineType;
    
    // Private fields - only accessible through methods
    private String registrationNumber;
    private boolean isRunning;
    
    // Default constructor
    public Vehicle() {
        System.out.println("Vehicle default constructor called");
        this.brand = "Unknown";
        this.model = "Unknown";
        this.year = 2020;
        this.engineType = "Petrol";
        this.registrationNumber = "REG" + (int)(Math.random() * 1000);
        this.isRunning = false;
    }
    
    // Parameterized constructor
    public Vehicle(String brand, String model, int year, String engineType) {
        System.out.println("Vehicle parameterized constructor called");
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.engineType = engineType;
        this.registrationNumber = "REG" + (int)(Math.random() * 1000);
        this.isRunning = false;
    }
    
    // Vehicle operations
    public void start() {
        this.isRunning = true;
        System.out.println("Vehicle started");
    }
    
    public void stop() {
        this.isRunning = false;
        System.out.println("Vehicle stopped");
    }
    
    public String getVehicleInfo() {
        return "Brand: " + brand + ", Model: " + model + 
               ", Year: " + year + ", Engine: " + engineType + 
               ", Registration: " + registrationNumber + 
               ", Running: " + isRunning;
    }
    
    // Getter for private field
    public String getRegistrationNumber() {
        return registrationNumber;
    }
    
    public boolean isRunning() {
        return isRunning;
    }
}

// File: Car.java (Child Class)
class Car extends Vehicle {
    // Car-specific fields
    private int numberOfDoors;
    private String fuelType;
    private String transmissionType;
    
    // Default constructor
    public Car() {
        super(); // Call parent's default constructor
        System.out.println("Car default constructor called");
        this.numberOfDoors = 4;
        this.fuelType = "Petrol";
        this.transmissionType = "Manual";
    }
    
    // Parameterized constructor
    public Car(String brand, String model, int year, String engineType,
               int numberOfDoors, String fuelType, String transmissionType) {
        super(brand, model, year, engineType); // Call parent's constructor
        System.out.println("Car parameterized constructor called");
        this.numberOfDoors = numberOfDoors;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
    }
    
    // Override parent method to add car-specific info
    @Override
    public String getVehicleInfo() {
        // Call parent method using super
        String parentInfo = super.getVehicleInfo();
        return parentInfo + ", Doors: " + numberOfDoors + 
               ", Fuel: " + fuelType + ", Transmission: " + transmissionType;
    }
    
    // Car-specific method
    public void honk() {
        System.out.println("Car is honking: Beep Beep!");
    }
    
    // Override start method to show car-specific behavior
    @Override
    public void start() {
        super.start(); // Call parent's start method
        System.out.println("Car engine is running smoothly");
    }
}

// Test class to demonstrate inheritance
class InheritanceDemo {
    public static void main(String[] args) {
        System.out.println("=== Creating Vehicle with default constructor ===");
        Vehicle v1 = new Vehicle();
        System.out.println(v1.getVehicleInfo());
        
        System.out.println("\n=== Creating Vehicle with parameters ===");
        Vehicle v2 = new Vehicle("Toyota", "Camry", 2022, "Hybrid");
        System.out.println(v2.getVehicleInfo());
        
        System.out.println("\n=== Creating Car with default constructor ===");
        Car c1 = new Car();
        System.out.println(c1.getVehicleInfo());
        
        System.out.println("\n=== Creating Car with parameters ===");
        Car c2 = new Car("Honda", "Civic", 2023, "Turbo", 4, "Petrol", "Automatic");
        System.out.println(c2.getVehicleInfo());
        
        System.out.println("\n=== Testing inheritance and method calls ===");
        // Car can access protected fields from parent
        System.out.println("Car brand: " + c2.brand); // Protected field access
        
        // Car inherits parent methods
        c2.start(); // Calls overridden method
        c2.honk();  // Car-specific method
        c2.stop();  // Inherited method
        
        System.out.println("\n=== Testing super keyword ===");
        System.out.println("Car info (uses super): " + c2.getVehicleInfo());
    }
}