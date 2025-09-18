// Level 1: Base class - Color
class Color {
    protected String name;
    
    public Color(String name) {
        System.out.println("🎨 Color constructor called: " + name);
        this.name = name;
    }
    
    public void displayColor() {
        System.out.println("Color: " + name);
    }
    
    public void mix() {
        System.out.println("Mixing " + name + " with other colors");
    }
}

// Level 2: PrimaryColor extends Color
class PrimaryColor extends Color {
    protected int intensity; // 1-10 scale
    
    public PrimaryColor(String name, int intensity) {
        super(name); // Call Color constructor
        System.out.println("🎨 PrimaryColor constructor called: intensity " + intensity);
        this.intensity = intensity;
    }
    
    @Override
    public void displayColor() {
        super.displayColor();
        System.out.println("Intensity: " + intensity + "/10");
    }
    
    public void blend() {
        System.out.println("Primary color " + name + " blends to create secondary colors");
    }
}

// Level 3: RedColor extends PrimaryColor
class RedColor extends PrimaryColor {
    protected String shade;
    
    public RedColor(String name, int intensity, String shade) {
        super(name, intensity); // Call PrimaryColor constructor
        System.out.println("🎨 RedColor constructor called: shade " + shade);
        this.shade = shade;
    }
    
    @Override
    public void displayColor() {
        super.displayColor();
        System.out.println("Shade: " + shade);
    }
    
    public void redProperties() {
        System.out.println(shade + " red represents warmth and energy");
    }
    
    @Override
    public void mix() {
        System.out.println(shade + " red mixes well with yellow to make orange");
    }
}

// Main class
public class ColorHierarchyChain {
    public static void main(String[] args) {
        System.out.println("=== Color Hierarchy Chain Demo ===\n");
        
        // Test Level 1: Color
        System.out.println("1. Creating basic Color:");
        Color basicColor = new Color("Blue");
        basicColor.displayColor();
        basicColor.mix();
        
        System.out.println("\n" + "=".repeat(40) + "\n");
        
        // Test Level 2: PrimaryColor
        System.out.println("2. Creating PrimaryColor:");
        PrimaryColor primary = new PrimaryColor("Yellow", 8);
        primary.displayColor();
        primary.blend();
        
        System.out.println("\n" + "=".repeat(40) + "\n");
        
        // Test Level 3: RedColor (shows full constructor chain)
        System.out.println("3. Creating RedColor (full chain):");
        RedColor redColor = new RedColor("Red", 9, "Crimson");
        redColor.displayColor();
        redColor.redProperties();
        redColor.mix();
        
        System.out.println("\n" + "=".repeat(40) + "\n");
        
        // Create different red shades
        System.out.println("4. Different Red shades:");
        
        RedColor brightRed = new RedColor("Red", 10, "Bright Red");
        System.out.println();
        brightRed.displayColor();
        
        System.out.println();
        RedColor darkRed = new RedColor("Red", 6, "Dark Red");
        System.out.println();
        darkRed.displayColor();
        darkRed.redProperties();
        
        System.out.println("\n" + "=".repeat(40) + "\n");
        
        // Polymorphism test
        System.out.println("5. Polymorphism with Color references:");
        Color[] colors = {
            new Color("Green"),
            new PrimaryColor("Blue", 7),
            new RedColor("Red", 8, "Cherry Red")
        };
        
        for (Color color : colors) {
            System.out.println();
            color.displayColor();
            color.mix();
        }
    }
}