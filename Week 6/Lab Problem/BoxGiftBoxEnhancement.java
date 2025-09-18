// Parent class - Box
class Box {
    protected String size;
    protected String material;
    protected boolean isPacked;
    
    public Box(String size, String material) {
        this.size = size;
        this.material = material;
        this.isPacked = false;
    }
    
    public void pack() {
        System.out.println("Packing items into " + size + " " + material + " box");
        System.out.println("Checking box integrity");
        System.out.println("Sealing the box");
        isPacked = true;
        System.out.println("Box packed successfully!");
    }
    
    public void unpack() {
        if (!isPacked) {
            System.out.println("Box is already unpacked");
            return;
        }
        System.out.println("Opening the " + size + " " + material + " box");
        System.out.println("Removing items from box");
        isPacked = false;
        System.out.println("Box unpacked successfully!");
    }
    
    public void displayInfo() {
        System.out.println("Box Size: " + size);
        System.out.println("Material: " + material);
        System.out.println("Status: " + (isPacked ? "Packed" : "Unpacked"));
    }
}

// Child class - GiftBox extends Box
class GiftBox extends Box {
    private String wrapperColor;
    private boolean hasRibbon;
    private String message;
    
    public GiftBox(String size, String material, String wrapperColor, boolean hasRibbon, String message) {
        super(size, material);
        this.wrapperColor = wrapperColor;
        this.hasRibbon = hasRibbon;
        this.message = message;
    }
    
    @Override
    public void pack() {
        // First do regular box packing
        super.pack();
        
        // Add gift-specific enhancements
        System.out.println("\n--- Gift Enhancement ---");
        System.out.println("Wrapping with " + wrapperColor + " paper");
        
        if (hasRibbon) {
            System.out.println("Adding decorative ribbon");
            System.out.println("Making a beautiful bow");
        }
        
        if (message != null && !message.isEmpty()) {
            System.out.println("Attaching gift card with message: \"" + message + "\"");
        }
        
        System.out.println("Gift box beautifully prepared!");
    }
    
    @Override
    public void unpack() {
        if (!isPacked) {
            System.out.println("Gift box is already unpacked");
            return;
        }
        
        // Gift-specific unwrapping first
        System.out.println("--- Gift Unwrapping ---");
        
        if (message != null && !message.isEmpty()) {
            System.out.println("Reading gift card: \"" + message + "\"");
        }
        
        if (hasRibbon) {
            System.out.println("Carefully removing the ribbon and bow");
        }
        
        System.out.println("Unwrapping " + wrapperColor + " gift paper");
        System.out.println();
        
        // Then do regular box unpacking
        super.unpack();
        
        System.out.println("Gift opened with excitement!");
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Wrapper Color: " + wrapperColor);
        System.out.println("Has Ribbon: " + (hasRibbon ? "Yes" : "No"));
        System.out.println("Message: " + (message.isEmpty() ? "None" : "\"" + message + "\""));
    }
    
    // Additional gift-specific method
    public void addPersonalization(String newMessage) {
        this.message = newMessage;
        System.out.println("Added personal message: \"" + newMessage + "\"");
    }
}

// Main class
public class BoxGiftBoxEnhancement {
    public static void main(String[] args) {
        System.out.println("Box and GiftBox Enhancement Demo\n");
        
        // Test regular Box
        System.out.println("1. Regular Box Operations:");
        Box regularBox = new Box("Medium", "Cardboard");
        regularBox.displayInfo();
        System.out.println();
        
        regularBox.pack();
        System.out.println();
        regularBox.unpack();
        
        System.out.println("\n-------------------------------\n");
        
        // Test GiftBox with enhanced functionality
        System.out.println("2. GiftBox Enhanced Operations:");
        GiftBox giftBox = new GiftBox("Large", "Premium Cardboard", "Red", true, "Happy Birthday!");
        giftBox.displayInfo();
        System.out.println();
        
        // Pack with enhancements
        System.out.println("Packing gift box:");
        giftBox.pack();
        
        System.out.println("\n-------------------------------\n");
        
        // Unpack with enhancements
        System.out.println("3. Unpacking gift box:");
        giftBox.unpack();
        
        System.out.println("\n-------------------------------\n");
        
        // Test super method calls with different gift
        System.out.println("4. Another GiftBox Example:");
        GiftBox surpriseBox = new GiftBox("Small", "Wooden", "Gold", false, "");
        surpriseBox.addPersonalization("Surprise for you!");
        System.out.println();
        
        surpriseBox.pack();
        System.out.println();
        surpriseBox.unpack();
        
        System.out.println("\n-------------------------------\n");
        
        // Demonstrate trying to unpack already unpacked box
        System.out.println("5. Trying to unpack already unpacked box:");
        giftBox.unpack();
    }
}