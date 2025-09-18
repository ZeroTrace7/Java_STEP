// Base class Tool
public class Tool {
    private String name;          // Private field
    protected String brand;       // Protected field
    public String type;           // Public field

    // Constructor
    public Tool(String name, String brand, String type) {
        this.name = name;
        this.brand = brand;
        this.type = type;
    }

    // Getter for private field
    public String getName() {
        return name;
    }
}

// Derived class Hammer
public class Hammer extends Tool {

    public Hammer(String name, String brand, String type) {
        super(name, brand, type);
    }

    public void displayFields() {
        // Attempt to access each field
        // name // private - not accessible directly
        // brand // protected - accessible
        // type  // public - accessible

        System.out.println("Brand (protected): " + brand);
        System.out.println("Type (public): " + type);

        // Accessing private field via getter
        System.out.println("Name (private, via getter): " + getName());
    }
}

public class Main {
    public static void main(String[] args) {
        Hammer hammer = new Hammer("Claw Hammer", "ToolCorp", "Hammer");
        hammer.displayFields();

        // Accessing fields from outside
        // System.out.println(hammer.name); // Error: private
        // System.out.println(hammer.brand); // Error: protected, but accessible within package or subclass
        System.out.println("Accessing public field 'type': " + hammer.type); // OK
    }
}