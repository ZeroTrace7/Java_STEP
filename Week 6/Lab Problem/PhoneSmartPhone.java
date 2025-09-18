// Parent class - Phone
class Phone {
    // Protected fields so child classes can access them
    protected String brand;
    protected String model;
    
    // Default constructor (no parameters)
    public Phone() {
        System.out.println("📱 Phone default constructor called");
        this.brand = "Unknown";
        this.model = "Basic Model";
    }
    
    // Constructor with brand only
    public Phone(String brand) {
        System.out.println("📱 Phone constructor with brand called: " + brand);
        this.brand = brand;
        this.model = "Standard Model";
    }
    
    // Constructor with both brand and model
    public Phone(String brand, String model) {
        System.out.println("📱 Phone constructor with brand and model called: " + brand + ", " + model);
        this.brand = brand;
        this.model = model;
    }
    
    // Method to display phone information
    public void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
    }
    
    // Method to make a call
    public void makeCall(String number) {
        System.out.println("Making a call to " + number + " from " + brand + " " + model);
    }
}

// Child class - SmartPhone extends Phone
class SmartPhone extends Phone {
    // Additional field specific to SmartPhone
    protected String operatingSystem;
    
    // Default constructor
    public SmartPhone() {
        // Calls Phone() default constructor automatically
        super(); // This line is optional - happens automatically if not specified
        System.out.println("📱 SmartPhone default constructor called");
        this.operatingSystem = "Unknown OS";
    }
    
    // Constructor with operating system only
    public SmartPhone(String operatingSystem) {
        // Calls Phone() default constructor
        super();
        System.out.println("📱 SmartPhone constructor with OS called: " + operatingSystem);
        this.operatingSystem = operatingSystem;
    }
    
    // Constructor with brand and operating system
    public SmartPhone(String brand, String operatingSystem) {
        // Calls Phone(String brand) constructor
        super(brand);
        System.out.println("📱 SmartPhone constructor with brand and OS called: " + brand + ", " + operatingSystem);
        this.operatingSystem = operatingSystem;
    }
    
    // Constructor with all three parameters
    public SmartPhone(String brand, String model, String operatingSystem) {
        // Calls Phone(String brand, String model) constructor
        super(brand, model);
        System.out.println("📱 SmartPhone constructor with all parameters called: " + brand + ", " + model + ", " + operatingSystem);
        this.operatingSystem = operatingSystem;
    }
    
    // Override displayInfo to include operating system
    @Override
    public void displayInfo() {
        super.displayInfo(); // Call parent method first
        System.out.println("Operating System: " + operatingSystem);
    }
    
    // SmartPhone specific methods
    public void installApp(String appName) {
        System.out.println("Installing " + appName + " on " + operatingSystem + " device");
    }
    
    public void browseInternet(String website) {
        System.out.println("Browsing " + website + " on " + brand + " " + model);
    }
}

// Main class to demonstrate constructor chaining
public class PhoneSmartPhone {
    public static void main(String[] args) {
        System.out.println("=== Phone and SmartPhone Constructor Chaining Demo ===\n");
        
        // Test 1: Default Phone constructor
        System.out.println("1. Creating Phone with default constructor:");
        Phone basicPhone = new Phone();
        basicPhone.displayInfo();
        System.out.println();
        
        System.out.println("=".repeat(50));
        
        // Test 2: Phone with brand only
        System.out.println("\n2. Creating Phone with brand only:");
        Phone nokiaPhone = new Phone("Nokia");
        nokiaPhone.displayInfo();
        nokiaPhone.makeCall("123-456-7890");
        System.out.println();
        
        System.out.println("=".repeat(50));
        
        // Test 3: Phone with brand and model
        System.out.println("\n3. Creating Phone with brand and model:");
        Phone samsungPhone = new Phone("Samsung", "Galaxy A50");
        samsungPhone.displayInfo();
        System.out.println();
        
        System.out.println("=".repeat(50));
        
        // Test 4: Default SmartPhone constructor
        System.out.println("\n4. Creating SmartPhone with default constructor:");
        SmartPhone defaultSmartPhone = new SmartPhone();
        defaultSmartPhone.displayInfo();
        System.out.println();
        
        System.out.println("=".repeat(50));
        
        // Test 5: SmartPhone with OS only
        System.out.println("\n5. Creating SmartPhone with OS only:");
        SmartPhone androidPhone = new SmartPhone("Android");
        androidPhone.displayInfo();
        androidPhone.installApp("WhatsApp");
        System.out.println();
        
        System.out.println("=".repeat(50));
        
        // Test 6: SmartPhone with brand and OS
        System.out.println("\n6. Creating SmartPhone with brand and OS:");
        SmartPhone applePhone = new SmartPhone("Apple", "iOS");
        applePhone.displayInfo();
        applePhone.browseInternet("www.apple.com");
        System.out.println();
        
        System.out.println("=".repeat(50));
        
        // Test 7: SmartPhone with all parameters
        System.out.println("\n7. Creating SmartPhone with all parameters:");
        SmartPhone flagshipPhone = new SmartPhone("Google", "Pixel 8", "Android 14");
        flagshipPhone.displayInfo();
        flagshipPhone.makeCall("555-0123");
        flagshipPhone.installApp("Google Photos");
        flagshipPhone.browseInternet("www.google.com");
        System.out.println();
        
        System.out.println("=".repeat(50));
        
        // Test 8: Demonstrate constructor execution order
        System.out.println("\n8. Constructor execution order demonstration:");
        System.out.println("Watch the order of constructor calls:");
        SmartPhone demoPhone = new SmartPhone("OnePlus", "11 Pro", "OxygenOS");
        System.out.println("Object created successfully!");
    }
}