// Parent class - Light
class Light {
    protected String brand;
    protected int wattage;
    protected String color;
    protected boolean isOn;
    
    // Default constructor
    public Light() {
        this("Generic", 60, "White");
        System.out.println("Light default constructor called");
    }
    
    // Constructor with brand only
    public Light(String brand) {
        this(brand, 60, "White");
        System.out.println("Light constructor with brand called: " + brand);
    }
    
    // Constructor with brand and wattage
    public Light(String brand, int wattage) {
        this(brand, wattage, "White");
        System.out.println("Light constructor with brand and wattage called: " + brand + ", " + wattage + "W");
    }
    
    // Main constructor (all parameters)
    public Light(String brand, int wattage, String color) {
        System.out.println("Light main constructor called: " + brand + ", " + wattage + "W, " + color);
        this.brand = brand;
        this.wattage = wattage;
        this.color = color;
        this.isOn = false;
    }
    
    public void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Wattage: " + wattage + "W");
        System.out.println("Color: " + color);
        System.out.println("Status: " + (isOn ? "On" : "Off"));
    }
    
    public void turnOn() {
        isOn = true;
        System.out.println(brand + " light is now ON");
    }
    
    public void turnOff() {
        isOn = false;
        System.out.println(brand + " light is now OFF");
    }
}

// Child class - LED extends Light
class LED extends Light {
    protected String ledType;
    protected int lifespan; // in hours
    
    // Default constructor
    public LED() {
        this("Generic", 10, "White", "Standard", 25000);
        System.out.println("LED default constructor called");
    }
    
    // Constructor with brand only
    public LED(String brand) {
        this(brand, 10, "White", "Standard", 25000);
        System.out.println("LED constructor with brand called: " + brand);
    }
    
    // Constructor with brand and wattage
    public LED(String brand, int wattage) {
        this(brand, wattage, "White", "Standard", 25000);
        System.out.println("LED constructor with brand and wattage called: " + brand + ", " + wattage + "W");
    }
    
    // Constructor with brand, wattage, and color
    public LED(String brand, int wattage, String color) {
        this(brand, wattage, color, "Standard", 25000);
        System.out.println("LED constructor with brand, wattage, color called: " + brand + ", " + wattage + "W, " + color);
    }
    
    // Main constructor (all parameters)
    public LED(String brand, int wattage, String color, String ledType, int lifespan) {
        super(brand, wattage, color); // Call parent constructor
        System.out.println("LED main constructor called: " + ledType + " type, " + lifespan + " hours lifespan");
        this.ledType = ledType;
        this.lifespan = lifespan;
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("LED Type: " + ledType);
        System.out.println("Lifespan: " + lifespan + " hours");
    }
    
    public void dimLight(int percentage) {
        if (isOn) {
            System.out.println("Dimming " + brand + " LED to " + percentage + "%");
        } else {
            System.out.println("Cannot dim - LED is off");
        }
    }
    
    public void changeColor(String newColor) {
        this.color = newColor;
        System.out.println("LED color changed to " + newColor);
    }
}

// Main class
public class LightLEDConstructors {
    public static void main(String[] args) {
        System.out.println("Light and LED Constructor Chaining Demo\n");
        
        // Test Light constructors with this() chaining
        System.out.println("1. Light Constructor Chaining:");
        
        System.out.println("Default Light:");
        Light defaultLight = new Light();
        System.out.println();
        
        System.out.println("Light with brand only:");
        Light brandLight = new Light("Philips");
        System.out.println();
        
        System.out.println("Light with brand and wattage:");
        Light brandWattageLight = new Light("GE", 100);
        System.out.println();
        
        System.out.println("Light with all parameters:");
        Light fullLight = new Light("Osram", 75, "Warm White");
        
        System.out.println("\n-------------------------------\n");
        
        // Test LED constructors with this() and super() chaining
        System.out.println("2. LED Constructor Chaining:");
        
        System.out.println("Default LED:");
        LED defaultLED = new LED();
        System.out.println();
        
        System.out.println("LED with brand only:");
        LED brandLED = new LED("Cree");
        System.out.println();
        
        System.out.println("LED with brand and wattage:");
        LED brandWattageLED = new LED("Samsung", 15);
        System.out.println();
        
        System.out.println("LED with brand, wattage, color:");
        LED partialLED = new LED("LG", 12, "Cool White");
        System.out.println();
        
        System.out.println("LED with all parameters:");
        LED fullLED = new LED("Nichia", 8, "RGB", "Smart", 50000);
        
        System.out.println("\n-------------------------------\n");
        
        // Test functionality
        System.out.println("3. Testing LED Functionality:");
        fullLED.displayInfo();
        System.out.println();
        
        fullLED.turnOn();
        fullLED.dimLight(70);
        fullLED.changeColor("Blue");
        fullLED.turnOff();
        
        System.out.println("\n-------------------------------\n");
        
        // Show constructor call tracing
        System.out.println("4. Constructor Call Tracing Example:");
        System.out.println("Creating LED with brand and wattage shows full chain:");
        LED traceLED = new LED("Trace Brand", 20);
    }
}