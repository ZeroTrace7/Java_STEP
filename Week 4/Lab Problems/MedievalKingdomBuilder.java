import java.util.Arrays;
import java.util.Random;

abstract class MagicalStructure {
    protected String structureName;
    protected int magicPower;
    protected String location;
    protected boolean isActive;
    protected static int totalStructuresBuilt = 0;
    protected static final Random random = new Random();
    
    public MagicalStructure() {
        this("Unknown Structure", 10, "Unknown Location");
    }
    
    public MagicalStructure(String structureName) {
        this(structureName, 25, "Central Kingdom");
    }
    
    public MagicalStructure(String structureName, int magicPower, String location) {
        this.structureName = structureName;
        this.magicPower = Math.max(0, magicPower);
        this.location = location;
        this.isActive = true;
        totalStructuresBuilt++;
        System.out.println("Magical structure built: " + structureName + " at " + location);
    }
    
    public abstract String castMagicSpell();
    
    public void displayStructureInfo() {
        System.out.println("Structure: " + structureName);
        System.out.println("Magic Power: " + magicPower);
        System.out.println("Location: " + location);
        System.out.println("Status: " + (isActive ? "Active" : "Dormant"));
    }
    
    public String getStructureName() { return structureName; }
    public int getMagicPower() { return magicPower; }
    public String getLocation() { return location; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { this.isActive = active; }
    public static int getTotalStructuresBuilt() { return totalStructuresBuilt; }
}

class WizardTower extends MagicalStructure {
    private int spellCapacity;
    private String[] knownSpells;
    private int currentSpells;
    
    public WizardTower() {
        this("Basic Tower", new String[]{"Fireball"});
    }
    
    public WizardTower(String towerName, String[] basicSpells) {
        this(towerName, 40, "Mystic Heights", basicSpells.length * 5, basicSpells);
    }
    
    public WizardTower(String towerName, int magicPower, String location, int spellCapacity, String[] knownSpells) {
        super(towerName, magicPower, location);
        this.spellCapacity = spellCapacity;
        this.knownSpells = Arrays.copyOf(knownSpells, spellCapacity);
        this.currentSpells = knownSpells.length;
    }
    
    @Override
    public String castMagicSpell() {
        if (currentSpells > 0 && isActive) {
            String spell = knownSpells[random.nextInt(currentSpells)];
            return structureName + " casts " + spell + "! (Power: " + magicPower + ")";
        }
        return structureName + " has no spells to cast!";
    }
    
    public void learnSpell(String newSpell) {
        if (currentSpells < spellCapacity) {
            knownSpells[currentSpells] = newSpell;
            currentSpells++;
            System.out.println(structureName + " learned new spell: " + newSpell);
        } else {
            System.out.println(structureName + " is at maximum spell capacity!");
        }
    }
    
    public void doubleSpellCapacity() {
        spellCapacity *= 2;
        String[] newSpells = new String[spellCapacity];
        System.arraycopy(knownSpells, 0, newSpells, 0, currentSpells);
        knownSpells = newSpells;
        System.out.println(structureName + " spell capacity doubled to " + spellCapacity + "!");
    }
    
    public int getSpellCapacity() { return spellCapacity; }
    public int getCurrentSpells() { return currentSpells; }
    public String[] getKnownSpells() { return Arrays.copyOf(knownSpells, currentSpells); }
    
    @Override
    public void displayStructureInfo() {
        super.displayStructureInfo();
        System.out.println("Spell Capacity: " + currentSpells + "/" + spellCapacity);
        System.out.print("Known Spells: ");
        for (int i = 0; i < currentSpells; i++) {
            System.out.print(knownSpells[i] + (i < currentSpells - 1 ? ", " : ""));
        }
        System.out.println();
    }
}

class EnchantedCastle extends MagicalStructure {
    private int defenseRating;
    private boolean hasDrawbridge;
    
    public EnchantedCastle() {
        this("Simple Fort");
    }
    
    public EnchantedCastle(String castleName) {
        this(castleName, 30, "Kingdom Center", 50, true);
    }
    
    public EnchantedCastle(String castleName, int magicPower, String location, int defenseRating, boolean hasDrawbridge) {
        super(castleName, magicPower, location);
        this.defenseRating = defenseRating;
        this.hasDrawbridge = hasDrawbridge;
    }
    
    @Override
    public String castMagicSpell() {
        if (isActive) {
            return structureName + " activates magical barriers! (Defense: " + defenseRating + ", Magic: " + magicPower + ")";
        }
        return structureName + " barriers are down!";
    }
    
    public void tripleDefense() {
        defenseRating *= 3;
        System.out.println(structureName + " defense tripled to " + defenseRating + " with dragon guard!");
    }
    
    public int getDefenseRating() { return defenseRating; }
    public boolean hasDrawbridge() { return hasDrawbridge; }
    
    @Override
    public void displayStructureInfo() {
        super.displayStructureInfo();
        System.out.println("Defense Rating: " + defenseRating);
        System.out.println("Drawbridge: " + (hasDrawbridge ? "Available" : "None"));
    }
}

class MysticLibrary extends MagicalStructure {
    private int bookCount;
    private String ancientLanguage;
    
    public MysticLibrary() {
        this("Small Library", 100, "Common");
    }
    
    public MysticLibrary(String libraryName, int bookCount, String ancientLanguage) {
        this(libraryName, bookCount * 2, "Scholar's District", bookCount, ancientLanguage);
    }
    
    public MysticLibrary(String libraryName, int magicPower, String location, int bookCount, String ancientLanguage) {
        super(libraryName, magicPower, location);
        this.bookCount = bookCount;
        this.ancientLanguage = ancientLanguage;
    }
    
    @Override
    public String castMagicSpell() {
        if (isActive) {
            return structureName + " reveals ancient knowledge in " + ancientLanguage + "! (Wisdom: " + bookCount + " books)";
        }
        return structureName + " knowledge is sealed!";
    }
    
    public int getBookCount() { return bookCount; }
    public String getAncientLanguage() { return ancientLanguage; }
    
    @Override
    public void displayStructureInfo() {
        super.displayStructureInfo();
        System.out.println("Book Collection: " + bookCount + " ancient texts");
        System.out.println("Ancient Language: " + ancientLanguage);
    }
}

class DragonLair extends MagicalStructure {
    private String dragonType;
    private int treasureValue;
    
    public DragonLair(String dragonType) {
        this("Dragon's Lair", dragonType, 1000);
    }
    
    public DragonLair(String lairName, String dragonType, int treasureValue) {
        this(lairName, treasureValue / 10, "Mountain Peak", dragonType, treasureValue);
    }
    
    public DragonLair(String lairName, int magicPower, String location, String dragonType, int treasureValue) {
        super(lairName, magicPower, location);
        this.dragonType = dragonType;
        this.treasureValue = treasureValue;
    }
    
    @Override
    public String castMagicSpell() {
        if (isActive) {
            return structureName + " unleashes " + dragonType + " dragon's fury! (Treasure Power: " + treasureValue + ")";
        }
        return dragonType + " dragon is sleeping!";
    }
    
    public String getDragonType() { return dragonType; }
    public int getTreasureValue() { return treasureValue; }
    
    @Override
    public void displayStructureInfo() {
        super.displayStructureInfo();
        System.out.println("Dragon Type: " + dragonType);
        System.out.println("Treasure Value: " + treasureValue + " gold");
    }
}

public class MedievalKingdomBuilder {
    
    public static boolean canStructuresInteract(MagicalStructure s1, MagicalStructure s2) {
        if (!s1.isActive() || !s2.isActive()) {
            return false;
        }
        
        int powerDifference = Math.abs(s1.getMagicPower() - s2.getMagicPower());
        return powerDifference <= 50;
    }
    
    public static String performMagicBattle(MagicalStructure attacker, MagicalStructure defender) {
        if (!canStructuresInteract(attacker, defender)) {
            return "Structures cannot interact - power difference too great or inactive!";
        }
        
        String battleResult = "MAGIC BATTLE: " + attacker.getStructureName() + " vs " + defender.getStructureName();
        battleResult += "\nAttacker: " + attacker.castMagicSpell();
        battleResult += "\nDefender: " + defender.castMagicSpell();
        
        if (attacker.getMagicPower() > defender.getMagicPower()) {
            battleResult += "\nResult: " + attacker.getStructureName() + " wins!";
        } else if (defender.getMagicPower() > attacker.getMagicPower()) {
            battleResult += "\nResult: " + defender.getStructureName() + " wins!";
        } else {
            battleResult += "\nResult: Draw!";
        }
        
        return battleResult;
    }
    
    public static int calculateKingdomMagicPower(MagicalStructure[] structures) {
        int totalPower = 0;
        int activeBonuses = 0;
        
        for (MagicalStructure structure : structures) {
            if (structure.isActive()) {
                totalPower += structure.getMagicPower();
                activeBonuses++;
            }
        }
        
        int synergy = (activeBonuses > 1) ? activeBonuses * 5 : 0;
        return totalPower + synergy;
    }
    
    public static void applySpecialEffects(MagicalStructure[] structures) {
        System.out.println("CHECKING FOR SPECIAL COMBINATIONS:");
        
        for (int i = 0; i < structures.length; i++) {
            for (int j = i + 1; j < structures.length; j++) {
                MagicalStructure s1 = structures[i];
                MagicalStructure s2 = structures[j];
                
                if (s1 instanceof WizardTower && s2 instanceof MysticLibrary) {
                    ((WizardTower) s1).doubleSpellCapacity();
                    System.out.println("Knowledge boost activated!");
                } else if (s1 instanceof MysticLibrary && s2 instanceof WizardTower) {
                    ((WizardTower) s2).doubleSpellCapacity();
                    System.out.println("Knowledge boost activated!");
                } else if (s1 instanceof EnchantedCastle && s2 instanceof DragonLair) {
                    ((EnchantedCastle) s1).tripleDefense();
                    System.out.println("Dragon guard activated!");
                } else if (s1 instanceof DragonLair && s2 instanceof EnchantedCastle) {
                    ((EnchantedCastle) s2).tripleDefense();
                    System.out.println("Dragon guard activated!");
                }
            }
        }
        
        int towerCount = 0;
        for (MagicalStructure structure : structures) {
            if (structure instanceof WizardTower) {
                towerCount++;
            }
        }
        
        if (towerCount >= 3) {
            System.out.println("Magic network activated with " + towerCount + " towers!");
            for (MagicalStructure structure : structures) {
                if (structure instanceof WizardTower) {
                    WizardTower tower = (WizardTower) structure;
                    tower.learnSpell("Network Spell");
                }
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println("MEDIEVAL KINGDOM BUILDER WITH MAGIC SYSTEM");
        System.out.println();
        
        System.out.println("BUILDING MAGICAL STRUCTURES:");
        System.out.println();
        
        System.out.println("1. Wizard Towers with different constructors:");
        WizardTower tower1 = new WizardTower();
        WizardTower tower2 = new WizardTower("Arcane Spire", new String[]{"Lightning Bolt", "Ice Shard", "Teleport"});
        WizardTower tower3 = new WizardTower("Master's Tower", 60, "Crystal Peak", 15, 
            new String[]{"Meteor", "Time Stop", "Dimension Door", "Wish"});
        
        System.out.println();
        System.out.println("2. Enchanted Castles:");
        EnchantedCastle castle1 = new EnchantedCastle();
        EnchantedCastle castle2 = new EnchantedCastle("Royal Fortress");
        EnchantedCastle castle3 = new EnchantedCastle("Impregnable Keep", 80, "Border March", 200, true);
        
        System.out.println();
        System.out.println("3. Mystic Libraries:");
        MysticLibrary library1 = new MysticLibrary();
        MysticLibrary library2 = new MysticLibrary("Great Archive", 500, "Draconic");
        MysticLibrary library3 = new MysticLibrary("Ancient Codex", 150, "Void Sanctuary", 1000, "Celestial");
        
        System.out.println();
        System.out.println("4. Dragon Lairs:");
        DragonLair lair1 = new DragonLair("Fire");
        DragonLair lair2 = new DragonLair("Shadow Depths", "Shadow", 5000);
        DragonLair lair3 = new DragonLair("Ancient Wyrm's Hoard", 200, "Volcano Core", "Ancient Gold", 10000);
        
        System.out.println();
        System.out.println("STRUCTURE INFORMATION:");
        MagicalStructure[] allStructures = {tower1, tower2, tower3, castle1, castle2, castle3, 
                                           library1, library2, library3, lair1, lair2, lair3};
        
        for (MagicalStructure structure : allStructures) {
            System.out.println();
            structure.displayStructureInfo();
            System.out.println("Spell Effect: " + structure.castMagicSpell());
        }
        
        System.out.println();
        System.out.println("INSTANCEOF TYPE CHECKING:");
        for (MagicalStructure structure : allStructures) {
            System.out.print(structure.getStructureName() + " is: ");
            if (structure instanceof WizardTower) {
                System.out.println("Wizard Tower");
            } else if (structure instanceof EnchantedCastle) {
                System.out.println("Enchanted Castle");
            } else if (structure instanceof MysticLibrary) {
                System.out.println("Mystic Library");
            } else if (structure instanceof DragonLair) {
                System.out.println("Dragon Lair");
            }
        }
        
        System.out.println();
        applySpecialEffects(allStructures);
        
        System.out.println();
        System.out.println("MAGIC BATTLES:");
        System.out.println(performMagicBattle(tower3, lair3));
        System.out.println();
        System.out.println(performMagicBattle(castle3, library3));
        
        System.out.println();
        System.out.println("KINGDOM STATISTICS:");
        System.out.println("Total Kingdom Magic Power: " + calculateKingdomMagicPower(allStructures));
        System.out.println("Total Structures Built: " + MagicalStructure.getTotalStructuresBuilt());
        
        System.out.println();
        System.out.println("CONCEPTS DEMONSTRATED:");
        System.out.println("1. Abstract base class with constructor chaining");
        System.out.println("2. Inheritance with four different magical structure types");
        System.out.println("3. instanceof checking for type identification and special effects");
        System.out.println("4. this keyword usage in constructor chaining");
        System.out.println("5. Constructor overloading for different building scenarios");
        System.out.println("6. Special combination effects based on structure types");
        System.out.println("7. Polymorphic method calls through abstract castMagicSpell()");
    }
}