import java.util.Random;

public class VirtualPet {
    private final String petId;
    private String petName;
    private String species;
    private int age;
    private int happiness;
    private int health;
    private int currentStage;
    
    private static final String[] EVOLUTION_STAGES = {"Egg", "Baby", "Child", "Teen", "Adult", "Elder"};
    private static final String[] SPECIES_OPTIONS = {"Dragon", "Phoenix", "Unicorn", "Griffin", "Pegasus"};
    private static int totalPetsCreated = 0;
    private static final Random random = new Random();
    
    public VirtualPet() {
        this("Mystery Pet", getRandomSpecies(), 0, 50, 80, 0);
    }
    
    public VirtualPet(String petName) {
        this(petName, getRandomSpecies(), 5, 60, 85, 1);
    }
    
    public VirtualPet(String petName, String species) {
        this(petName, species, 10, 70, 90, 2);
    }
    
    public VirtualPet(String petName, String species, int age, int happiness, int health, int currentStage) {
        this.petId = generatePetId();
        this.petName = petName;
        this.species = species;
        this.age = age;
        this.happiness = Math.max(0, Math.min(100, happiness));
        this.health = Math.max(0, Math.min(100, health));
        this.currentStage = Math.max(0, Math.min(EVOLUTION_STAGES.length - 1, currentStage));
        totalPetsCreated++;
        
        System.out.println("New virtual pet created: " + petName + " (ID: " + petId + ")");
        System.out.println("Starting as: " + EVOLUTION_STAGES[this.currentStage] + " " + species);
    }
    
    private final String generatePetId() {
        return "PET-" + System.currentTimeMillis() % 10000 + "-" + random.nextInt(1000);
    }
    
    private static String getRandomSpecies() {
        return SPECIES_OPTIONS[random.nextInt(SPECIES_OPTIONS.length)];
    }
    
    public void evolvePet() {
        int requiredAge = (currentStage + 1) * 15;
        int requiredCare = 60;
        
        if (age >= requiredAge && happiness >= requiredCare && health >= requiredCare) {
            if (currentStage < EVOLUTION_STAGES.length - 1) {
                currentStage++;
                System.out.println(petName + " has evolved to " + EVOLUTION_STAGES[currentStage] + " stage!");
                happiness += 10;
                health += 5;
            } else {
                System.out.println(petName + " is already at maximum evolution!");
            }
        } else {
            System.out.println(petName + " needs more care to evolve!");
            System.out.println("Requirements - Age: " + requiredAge + ", Care Level: " + requiredCare);
        }
    }
    
    public void feedPet() {
        health += 10;
        happiness += 5;
        age += 1;
        
        if (health > 100) health = 100;
        if (happiness > 100) happiness = 100;
        
        System.out.println(petName + " has been fed! Health: " + health + ", Happiness: " + happiness);
        checkEvolution();
    }
    
    public void playWithPet() {
        happiness += 15;
        health -= 2;
        age += 1;
        
        if (happiness > 100) happiness = 100;
        if (health < 0) health = 0;
        
        System.out.println(petName + " enjoyed playing! Happiness: " + happiness + ", Health: " + health);
        checkEvolution();
    }
    
    public void restPet() {
        health += 20;
        happiness += 5;
        age += 2;
        
        if (health > 100) health = 100;
        if (happiness > 100) happiness = 100;
        
        System.out.println(petName + " had a good rest! Health: " + health + ", Happiness: " + happiness);
        checkEvolution();
    }
    
    private void checkEvolution() {
        evolvePet();
    }
    
    public void displayPetStatus() {
        System.out.println();
        System.out.println("PET STATUS REPORT");
        System.out.println("Pet ID: " + petId);
        System.out.println("Name: " + petName);
        System.out.println("Species: " + species);
        System.out.println("Evolution Stage: " + EVOLUTION_STAGES[currentStage]);
        System.out.println("Age: " + age + " days");
        System.out.println("Happiness: " + happiness + "/100");
        System.out.println("Health: " + health + "/100");
        System.out.println("Care Level: " + ((happiness + health) / 2) + "/100");
        System.out.println();
    }
    
    public void agePet(int days) {
        age += days;
        happiness -= days * 2;
        health -= days;
        
        if (happiness < 0) happiness = 0;
        if (health < 0) health = 0;
        
        System.out.println(petName + " aged " + days + " days");
        if (happiness < 30 || health < 30) {
            System.out.println("Warning: " + petName + " needs attention!");
        }
    }
    
    public static int getTotalPetsCreated() {
        return totalPetsCreated;
    }
    
    public static void displayEvolutionChart() {
        System.out.println("EVOLUTION STAGES CHART:");
        for (int i = 0; i < EVOLUTION_STAGES.length; i++) {
            int requiredAge = i * 15;
            System.out.println("Stage " + i + ": " + EVOLUTION_STAGES[i] + " (Age: " + requiredAge + "+ days)");
        }
        System.out.println();
    }
    
    public final String getPetId() {
        return petId;
    }
    
    public String getPetName() {
        return petName;
    }
    
    public String getSpecies() {
        return species;
    }
    
    public int getAge() {
        return age;
    }
    
    public int getHappiness() {
        return happiness;
    }
    
    public int getHealth() {
        return health;
    }
    
    public String getCurrentStage() {
        return EVOLUTION_STAGES[currentStage];
    }
    
    public static void main(String[] args) {
        System.out.println("VIRTUAL PET EVOLUTION SIMULATOR");
        System.out.println();
        
        System.out.println("Creating pets with different constructors:");
        System.out.println();
        
        System.out.println("1. Default Constructor (Mystery Egg):");
        VirtualPet pet1 = new VirtualPet();
        pet1.displayPetStatus();
        
        System.out.println("2. Name Only Constructor (Baby Stage):");
        VirtualPet pet2 = new VirtualPet("Sparkles");
        pet2.displayPetStatus();
        
        System.out.println("3. Name and Species Constructor (Child Stage):");
        VirtualPet pet3 = new VirtualPet("Thunder", "Dragon");
        pet3.displayPetStatus();
        
        System.out.println("4. Full Constructor (Custom Stats):");
        VirtualPet pet4 = new VirtualPet("Celestia", "Phoenix", 25, 85, 95, 3);
        pet4.displayPetStatus();
        
        displayEvolutionChart();
        
        System.out.println("PET CARE SIMULATION:");
        System.out.println();
        
        System.out.println("Taking care of Sparkles:");
        for (int day = 1; day <= 3; day++) {
            System.out.println("Day " + day + ":");
            pet2.feedPet();
            pet2.playWithPet();
            pet2.restPet();
            System.out.println();
        }
        
        pet2.displayPetStatus();
        
        System.out.println("EVOLUTION DEMONSTRATION:");
        System.out.println();
        
        VirtualPet rapidPet = new VirtualPet("Speedy", "Unicorn");
        System.out.println("Accelerated growth simulation for " + rapidPet.getPetName() + ":");
        
        for (int stage = 0; stage < 4; stage++) {
            System.out.println("Growth Phase " + (stage + 1) + ":");
            
            for (int i = 0; i < 8; i++) {
                rapidPet.feedPet();
                rapidPet.playWithPet();
                rapidPet.restPet();
            }
            
            rapidPet.displayPetStatus();
        }
        
        System.out.println("MULTIPLE PETS MANAGEMENT:");
        System.out.println();
        
        VirtualPet[] petCollection = {
            new VirtualPet("Alpha", "Griffin"),
            new VirtualPet("Beta", "Pegasus"),
            new VirtualPet("Gamma"),
            new VirtualPet("Delta", "Dragon", 30, 90, 85, 4)
        };
        
        System.out.println("Pet Collection Status:");
        for (VirtualPet pet : petCollection) {
            pet.displayPetStatus();
        }
        
        System.out.println("Aging all pets by 10 days:");
        for (VirtualPet pet : petCollection) {
            pet.agePet(10);
        }
        
        System.out.println();
        System.out.println("FINAL STATISTICS:");
        System.out.println("Total pets created: " + VirtualPet.getTotalPetsCreated());
        
        System.out.println();
        System.out.println("CONCEPTS DEMONSTRATED:");
        System.out.println("1. Constructor Overloading with 4 different constructors");
        System.out.println("2. this() chaining - all constructors call main constructor");
        System.out.println("3. final keyword - petId cannot be changed after creation");
        System.out.println("4. static variables - totalPetsCreated shared across all instances");
        System.out.println("5. static final arrays - EVOLUTION_STAGES constant");
        System.out.println("6. Evolution mechanics based on age and care level");
        System.out.println("7. Unique pet ID generation for each instance");
    }
}