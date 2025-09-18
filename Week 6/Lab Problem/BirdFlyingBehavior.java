// Parent class - Bird
class Bird {
    protected String name;
    
    public Bird(String name) {
        this.name = name;
    }
    
    // Method to be overridden by child classes
    public void fly() {
        System.out.println(name + " is flying in the sky");
    }
    
    public void displayInfo() {
        System.out.println("Bird: " + name);
    }
}

// Child class - Penguin
class Penguin extends Bird {
    public Penguin(String name) {
        super(name);
    }
    
    @Override
    public void fly() {
        System.out.println(name + " cannot fly, but can swim very well!");
    }
}

// Child class - Eagle
class Eagle extends Bird {
    public Eagle(String name) {
        super(name);
    }
    
    @Override
    public void fly() {
        System.out.println(name + " soars high with powerful wings!");
    }
}

// Main class
public class BirdFlyingBehavior {
    public static void main(String[] args) {
        System.out.println("=== Bird Flying Behavior Demo ===\n");
        
        // Create individual objects
        Bird genericBird = new Bird("Robin");
        Penguin penguin = new Penguin("Pingu");
        Eagle eagle = new Eagle("Golden Eagle");
        
        // Test individual flying behavior
        genericBird.fly();
        penguin.fly();
        eagle.fly();
        
        System.out.println("\n" + "=".repeat(40) + "\n");
        
        // Test polymorphism with array of Bird references
        System.out.println("Polymorphism Demo:");
        Bird[] birds = {
            new Bird("Sparrow"),
            new Penguin("Emperor Penguin"),
            new Eagle("Bald Eagle"),
            new Penguin("Adelie Penguin")
        };
        
        // Same method call, different behavior
        for (Bird bird : birds) {
            bird.displayInfo();
            bird.fly();
            System.out.println();
        }
    }
}