// Base Food class with template method
public abstract class Food {
    // Protected fields for inheritance
    protected String name;
    protected int prepTimeMinutes;
    protected String difficulty;
    
    // Constructor
    public Food(String name, int prepTimeMinutes, String difficulty) {
        this.name = name;
        this.prepTimeMinutes = prepTimeMinutes;
        this.difficulty = difficulty;
    }
    
    // Template Method - defines the sequence of steps
    public final void prepare() {
        System.out.println("Starting preparation of " + name);
        System.out.println("Estimated time: " + prepTimeMinutes + " minutes");
        System.out.println("Difficulty: " + difficulty);
        System.out.println("------------------------");
        
        // Call methods in specific order
        wash();
        cook();
        serve();
        
        System.out.println("------------------------");
        System.out.println(name + " is ready to eat!");
        System.out.println();
    }
    
    // Abstract methods - must be implemented by child classes
    protected abstract void wash();
    protected abstract void cook();
    protected abstract void serve();
    
    // Common method for all foods
    protected void cleanup() {
        System.out.println("Cleaning up kitchen after preparing " + name);
    }
    
    // Getter methods
    public String getName() {
        return name;
    }
    
    public int getPrepTimeMinutes() {
        return prepTimeMinutes;
    }
}

// Pizza class extends Food
class Pizza extends Food {
    private String[] toppings;
    private String crustType;
    private int ovenTemperature;
    
    // Constructor
    public Pizza(String name, String[] toppings, String crustType) {
        super(name, 45, "Medium"); // Pizza takes 45 minutes, medium difficulty
        this.toppings = toppings;
        this.crustType = crustType;
        this.ovenTemperature = 450; // Fahrenheit
    }
    
    // Override wash method for pizza
    @Override
    protected void wash() {
        System.out.println("Step 1 - Washing:");
        System.out.println("- Washing vegetables and toppings");
        System.out.println("- Cleaning pizza stone");
        System.out.println("- Sanitizing work surface");
    }
    
    // Override cook method for pizza
    @Override
    protected void cook() {
        System.out.println("Step 2 - Cooking:");
        System.out.println("- Rolling out " + crustType + " dough");
        System.out.println("- Spreading sauce on dough");
        System.out.print("- Adding toppings: ");
        for (int i = 0; i < toppings.length; i++) {
            System.out.print(toppings[i]);
            if (i < toppings.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
        System.out.println("- Preheating oven to " + ovenTemperature + "°F");
        System.out.println("- Baking pizza for 15-20 minutes");
        System.out.println("- Checking for golden crust");
    }
    
    // Override serve method for pizza
    @Override
    protected void serve() {
        System.out.println("Step 3 - Serving:");
        System.out.println("- Letting pizza cool for 5 minutes");
        System.out.println("- Cutting into 8 slices");
        System.out.println("- Serving on plates with napkins");
        System.out.println("- Adding parmesan and red pepper flakes");
    }
    
    // Pizza-specific method
    public void addExtraCheese() {
        System.out.println("Adding extra cheese to " + name);
    }
}

// Soup class extends Food
class Soup extends Food {
    private String[] ingredients;
    private String soupType; // Broth, Cream, etc.
    private boolean needsBlending;
    
    // Constructor
    public Soup(String name, String[] ingredients, String soupType, boolean needsBlending) {
        super(name, 30, "Easy"); // Soup takes 30 minutes, easy difficulty
        this.ingredients = ingredients;
        this.soupType = soupType;
        this.needsBlending = needsBlending;
    }
    
    // Override wash method for soup
    @Override
    protected void wash() {
        System.out.println("Step 1 - Washing:");
        System.out.println("- Washing all vegetables thoroughly");
        System.out.println("- Cleaning soup pot and utensils");
        System.out.println("- Rinsing herbs and spices");
    }
    
    // Override cook method for soup
    @Override
    protected void cook() {
        System.out.println("Step 2 - Cooking:");
        System.out.println("- Chopping vegetables into small pieces");
        System.out.print("- Adding ingredients: ");
        for (int i = 0; i < ingredients.length; i++) {
            System.out.print(ingredients[i]);
            if (i < ingredients.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
        System.out.println("- Adding " + soupType.toLowerCase() + " base");
        System.out.println("- Simmering on medium heat for 20 minutes");
        System.out.println("- Stirring occasionally");
        
        if (needsBlending) {
            System.out.println("- Blending soup until smooth");
        }
        
        System.out.println("- Seasoning with salt and pepper");
    }
    
    // Override serve method for soup
    @Override
    protected void serve() {
        System.out.println("Step 3 - Serving:");
        System.out.println("- Ladling soup into warm bowls");
        System.out.println("- Garnishing with herbs");
        System.out.println("- Serving with crackers or bread");
        System.out.println("- Providing soup spoons");
    }
    
    // Soup-specific method
    public void addCroutons() {
        System.out.println("Adding homemade croutons to " + name);
    }
}

// Salad class extends Food (another example)
class Salad extends Food {
    private String[] vegetables;
    private String dressingType;
    private boolean hasCroutons;
    
    // Constructor
    public Salad(String name, String[] vegetables, String dressingType, boolean hasCroutons) {
        super(name, 15, "Easy"); // Salad takes 15 minutes, easy difficulty
        this.vegetables = vegetables;
        this.dressingType = dressingType;
        this.hasCroutons = hasCroutons;
    }
    
    // Override wash method for salad
    @Override
    protected void wash() {
        System.out.println("Step 1 - Washing:");
        System.out.println("- Washing all leafy greens thoroughly");
        System.out.println("- Cleaning vegetables under cold water");
        System.out.println("- Drying vegetables with paper towels");
    }
    
    // Override cook method for salad (no actual cooking)
    @Override
    protected void cook() {
        System.out.println("Step 2 - Preparing (No cooking needed):");
        System.out.println("- Chopping vegetables into bite-sized pieces");
        System.out.print("- Mixing vegetables: ");
        for (int i = 0; i < vegetables.length; i++) {
            System.out.print(vegetables[i]);
            if (i < vegetables.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
        System.out.println("- Preparing " + dressingType + " dressing");
        
        if (hasCroutons) {
            System.out.println("- Adding croutons for crunch");
        }
    }
    
    // Override serve method for salad
    @Override
    protected void serve() {
        System.out.println("Step 3 - Serving:");
        System.out.println("- Tossing salad with dressing");
        System.out.println("- Serving in chilled bowls");
        System.out.println("- Providing salad forks");
        System.out.println("- Offering extra dressing on the side");
    }
}

// Demo class to test template method pattern
class FoodTemplateDemo {
    public static void main(String[] args) {
        System.out.println("Food Preparation Template Method Demo");
        System.out.println();
        
        // Create different food objects
        String[] pizzaToppings = {"Cheese", "Pepperoni", "Mushrooms", "Bell Peppers"};
        Pizza margherita = new Pizza("Margherita Pizza", pizzaToppings, "Thin Crust");
        
        String[] soupIngredients = {"Tomatoes", "Onions", "Garlic", "Basil"};
        Soup tomatoSoup = new Soup("Tomato Soup", soupIngredients, "Cream", true);
        
        String[] saladVeggies = {"Lettuce", "Tomatoes", "Cucumbers", "Carrots"};
        Salad caesarSalad = new Salad("Caesar Salad", saladVeggies, "Caesar", true);
        
        // Test template method on different food types
        System.out.println("PREPARING PIZZA:");
        margherita.prepare(); // Calls template method
        
        System.out.println("PREPARING SOUP:");
        tomatoSoup.prepare(); // Calls template method
        
        System.out.println("PREPARING SALAD:");
        caesarSalad.prepare(); // Calls template method
        
        // Test food-specific methods
        System.out.println("Testing food-specific methods:");
        margherita.addExtraCheese();
        tomatoSoup.addCroutons();
        
        // Show that template method sequence cannot be changed
        System.out.println("Template method ensures same sequence for all foods:");
        System.out.println("1. Wash -> 2. Cook -> 3. Serve (always in this order)");
    }
}