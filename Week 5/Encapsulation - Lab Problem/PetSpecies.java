final class PetSpecies {
    private final String name;
    private final String[] stages;
    private final int maxAge;
    
    public PetSpecies(String name, String[] stages, int maxAge) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (stages == null || stages.length < 1) {
            throw new IllegalArgumentException("Must have evolution stages");
        }
        if (maxAge <= 0) {
            throw new IllegalArgumentException("Max age must be positive");
        }
        
        this.name = name;
        this.stages = stages.clone();
        this.maxAge = maxAge;
    }
    
    public String getName() {
        return name;
    }
    
    public String[] getStages() {
        return stages.clone();
    }
    
    public int getMaxAge() {
        return maxAge;
    }
    
    @Override
    public String toString() {
        return "Species: " + name + " (max age: " + maxAge + ")";
    }
}

class VirtualPet {
    private final String id;
    private final PetSpecies species;
    private final long birthTime;
    
    private String name;
    private int age;
    private int happiness;
    private int health;
    
    protected static final String[] DEFAULT_STAGES = {
        "Baby", "Child", "Adult", "Elder"
    };
    
    static final int MAX_HAPPINESS = 100;
    static final int MAX_HEALTH = 100;
    
    public static final String VERSION = "2.0";
    
    private static final PetSpecies DEFAULT_SPECIES = 
        new PetSpecies("Generic Pet", DEFAULT_STAGES, 50);
    
    public VirtualPet() {
        this("Random Pet", DEFAULT_SPECIES, 50, 75);
    }
    
    public VirtualPet(String name) {
        this(name, DEFAULT_SPECIES, 60, 80);
    }
    
    public VirtualPet(String name, PetSpecies species) {
        this(name, species, 65, 85);
    }
    
    public VirtualPet(String name, PetSpecies species, int happiness, int health) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Pet needs a name");
        }
        if (species == null) {
            throw new IllegalArgumentException("Pet needs a species");
        }
        
        this.id = "PET_" + System.currentTimeMillis();
        this.name = name.trim();
        this.species = species;
        this.birthTime = System.currentTimeMillis();
        this.age = 0;
        this.happiness = checkStat(happiness);
        this.health = checkStat(health);
    }
    
    public void feed(String food) {
        if (food == null || food.isEmpty()) {
            System.out.println("What food?");
            return;
        }
        
        int healthGain = getFoodValue(food);
        int happyGain = healthGain / 2;
        
        addHealth(healthGain);
        addHappiness(happyGain);
        
        System.out.println(name + " ate " + food + "! (+" + healthGain + " health)");
    }
    
    public void play(String game) {
        if (game == null || game.isEmpty()) {
            System.out.println("What game?");
            return;
        }
        
        int happyGain = getGameValue(game);
        int healthCost = happyGain / 3;
        
        addHappiness(happyGain);
        addHealth(-healthCost);
        
        System.out.println(name + " played " + game + "! (+" + happyGain + " happiness)");
    }
    
    protected int getFoodValue(String food) {
        switch (food.toLowerCase()) {
            case "premium": return 25;
            case "treats": return 15;
            case "regular": return 10;
            default: return 8;
        }
    }
    
    protected int getGameValue(String game) {
        switch (game.toLowerCase()) {
            case "fetch": return 20;
            case "puzzle": return 15;
            case "training": return 25;
            default: return 12;
        }
    }
    
    private int checkStat(int value) {
        if (value < 0) return 0;
        if (value > 100) return 100;
        return value;
    }
    
    private void addHappiness(int amount) {
        happiness = checkStat(happiness + amount);
    }
    
    private void addHealth(int amount) {
        health = checkStat(health + amount);
    }
    
    String getDebugInfo() {
        return "ID: " + id + ", Age: " + age + ", H:" + health + ", Ha:" + happiness;
    }
    
    public String getId() { return id; }
    
    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name.trim();
    }
    
    public PetSpecies getSpecies() { return species; }
    public long getBirthTime() { return birthTime; }
    
    public int getAge() { return age; }
    public void setAge(int age) {
        if (age < 0) throw new IllegalArgumentException("Age cannot be negative");
        this.age = age;
    }
    
    public int getHappiness() { return happiness; }
    public void setHappiness(int happiness) { this.happiness = checkStat(happiness); }
    
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = checkStat(health); }
    
    @Override
    public String toString() {
        return name + " (" + species.getName() + ") - Health:" + health + " Happiness:" + happiness;
    }
}

class DragonPet {
    private final String dragonType;
    private final String breathWeapon;
    
    private final VirtualPet basePet;
    
    private int firepower;
    private boolean flying;
    
    public DragonPet(String name, String dragonType, String breathWeapon) {
        if (dragonType == null || dragonType.isEmpty()) {
            throw new IllegalArgumentException("Dragon needs a type");
        }
        if (breathWeapon == null || breathWeapon.isEmpty()) {
            throw new IllegalArgumentException("Dragon needs a breath weapon");
        }
        
        this.dragonType = dragonType;
        this.breathWeapon = breathWeapon;
        this.firepower = 50;
        this.flying = false;
        
        PetSpecies dragonSpecies = new PetSpecies("Dragon", 
            new String[]{"Hatchling", "Young Dragon", "Adult Dragon"}, 200);
        this.basePet = new VirtualPet(name, dragonSpecies, 70, 90);
    }
    
    public void breatheFire() {
        if (basePet.getHealth() < 20) {
            System.out.println(basePet.getName() + " is too tired to breathe fire!");
            return;
        }
        
        System.out.println(basePet.getName() + " breathes " + breathWeapon + "!");
        basePet.setHealth(basePet.getHealth() - 10);
        firepower = Math.min(100, firepower + 5);
    }
    
    public void fly() {
        flying = !flying;
        String action = flying ? "takes off" : "lands";
        System.out.println(basePet.getName() + " " + action + "!");
        
        if (flying) {
            basePet.setHappiness(basePet.getHappiness() + 15);
        }
    }
    
    public void feed(String food) {
        basePet.feed(food);
        if (food.toLowerCase().contains("meat")) {
            firepower = Math.min(100, firepower + 10);
            System.out.println("Dragon power increased!");
        }
    }
    
    public void play(String game) {
        basePet.play(game);
    }
    
    public String getDragonType() { return dragonType; }
    public String getBreathWeapon() { return breathWeapon; }
    public int getFirepower() { return firepower; }
    public boolean isFlying() { return flying; }
    
    public String getName() { return basePet.getName(); }
    public int getHealth() { return basePet.getHealth(); }
    public int getHappiness() { return basePet.getHappiness(); }
    
    @Override
    public String toString() {
        return basePet.getName() + " (" + dragonType + ") - Power:" + firepower + 
               " Flying:" + flying + " Health:" + getHealth();
    }
}

class RobotPet {
    private final VirtualPet basePet;
    
    private boolean needsCharging;
    private int batteryLevel;
    
    public RobotPet(String name, int startBattery) {
        if (startBattery < 0 || startBattery > 100) {
            throw new IllegalArgumentException("Battery must be 0-100");
        }
        
        this.batteryLevel = startBattery;
        this.needsCharging = (startBattery < 20);
        
        PetSpecies robotSpecies = new PetSpecies("Robot", 
            new String[]{"Basic", "Advanced", "Elite"}, 500);
        this.basePet = new VirtualPet(name, robotSpecies, 80, 95);
    }
    
    public void charge() {
        System.out.println(basePet.getName() + " is charging...");
        batteryLevel = 100;
        needsCharging = false;
        basePet.setHappiness(basePet.getHappiness() + 20);
        System.out.println("Fully charged!");
    }
    
    public void maintenance() {
        System.out.println("Running maintenance on " + basePet.getName());
        basePet.setHealth(100);
        batteryLevel = Math.min(100, batteryLevel + 30);
        checkBattery();
        System.out.println("Maintenance complete!");
    }
    
    public void feed(String food) {
        if (batteryLevel < 5) {
            System.out.println(basePet.getName() + " needs charging first!");
            return;
        }
        
        System.out.println(basePet.getName() + " processes " + food);
        basePet.setHappiness(basePet.getHappiness() + 10);
        batteryLevel -= 3;
        checkBattery();
    }
    
    public void play(String game) {
        if (batteryLevel < 10) {
            System.out.println(basePet.getName() + " needs charging first!");
            return;
        }
        
        basePet.play(game);
        batteryLevel -= 8;
        checkBattery();
    }
    
    private void checkBattery() {
        needsCharging = (batteryLevel < 20);
        if (needsCharging) {
            System.out.println("WARNING: Low battery (" + batteryLevel + "%)");
        }
    }
    
    public boolean needsCharging() { return needsCharging; }
    public int getBatteryLevel() { return batteryLevel; }
    
    public String getName() { return basePet.getName(); }
    public int getHealth() { return basePet.getHealth(); }
    public int getHappiness() { return basePet.getHappiness(); }
    
    @Override
    public String toString() {
        return basePet.getName() + " (Robot) - Battery:" + batteryLevel + "% Health:" + getHealth();
    }
}

class PetDemo {
    public static void main(String[] args) {
        System.out.println("Virtual Pet System " + VirtualPet.VERSION + "\n");
        
        System.out.println("1. Regular Pet:");
        VirtualPet pet = new VirtualPet("Buddy");
        System.out.println(pet);
        pet.feed("treats");
        pet.play("fetch");
        System.out.println("After activities: " + pet);
        System.out.println();
        
        System.out.println("2. Dragon Pet:");
        DragonPet dragon = new DragonPet("Flame", "Fire Dragon", "Fire Blast");
        System.out.println(dragon);
        dragon.feed("meat");
        dragon.breatheFire();
        dragon.fly();
        System.out.println("After activities: " + dragon);
        System.out.println();
        
        System.out.println("3. Robot Pet:");
        RobotPet robot = new RobotPet("R2D2", 60);
        System.out.println(robot);
        robot.play("puzzle");
        robot.play("training");
        robot.charge();
        robot.maintenance();
        System.out.println("After activities: " + robot);
        System.out.println();
        
        System.out.println("4. Debug info: " + pet.getDebugInfo());
        
        System.out.println("\nDemo Complete");
    }
}