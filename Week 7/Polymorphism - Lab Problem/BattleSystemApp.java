class GameCharacter {
    protected String name;
    protected int health;
    protected int attackPower;
    
    public GameCharacter(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }
    
    public void attack() {
        System.out.println(name + " performs a basic attack for " + attackPower + " damage");
    }
    
    public void showStats() {
        System.out.println(name + " - Health: " + health + " | Attack: " + attackPower);
    }
    
    public String getName() {
        return name;
    }
}

class Warrior extends GameCharacter {
    private String weapon;
    private int defense;
    
    public Warrior(String name, int health, int attackPower, String weapon, int defense) {
        super(name, health, attackPower);
        this.weapon = weapon;
        this.defense = defense;
    }
    
    public void attack() {
        System.out.println("⚔️ " + name + " swings " + weapon + " dealing " + attackPower + " damage!");
        System.out.println("   Defense remains strong at " + defense + " points");
    }
    
    public void showStats() {
        System.out.println("🛡️ Warrior " + name + " - Health: " + health + " | Attack: " + attackPower + " | Defense: " + defense + " | Weapon: " + weapon);
    }
}

class Mage extends GameCharacter {
    private int mana;
    private String spellType;
    
    public Mage(String name, int health, int attackPower, int mana, String spellType) {
        super(name, health, attackPower);
        this.mana = mana;
        this.spellType = spellType;
    }
    
    public void attack() {
        if (mana >= 20) {
            mana = mana - 20;
            int spellDamage = attackPower + 15;
            System.out.println("🔮 " + name + " casts " + spellType + " spell dealing " + spellDamage + " magical damage!");
            System.out.println("   Mana remaining: " + mana);
        } else {
            System.out.println("💫 " + name + " is out of mana! Uses basic staff attack for " + attackPower + " damage");
        }
    }
    
    public void showStats() {
        System.out.println("🧙 Mage " + name + " - Health: " + health + " | Attack: " + attackPower + " | Mana: " + mana + " | Spell: " + spellType);
    }
}

class Archer extends GameCharacter {
    private int arrows;
    private int range;
    
    public Archer(String name, int health, int attackPower, int arrows, int range) {
        super(name, health, attackPower);
        this.arrows = arrows;
        this.range = range;
    }
    
    public void attack() {
        if (arrows > 0) {
            arrows = arrows - 1;
            int rangeDamage = attackPower + (range / 10);
            System.out.println("🏹 " + name + " shoots an arrow from " + range + "m dealing " + rangeDamage + " damage!");
            System.out.println("   Arrows remaining: " + arrows);
        } else {
            System.out.println("🪃 " + name + " is out of arrows! Uses melee attack for " + (attackPower - 10) + " damage");
        }
    }
    
    public void showStats() {
        System.out.println("🎯 Archer " + name + " - Health: " + health + " | Attack: " + attackPower + " | Arrows: " + arrows + " | Range: " + range + "m");
    }
}

public class BattleSystemApp {
    public static void main(String[] args) {
        System.out.println("Gaming Character Battle System");
        System.out.println();
        
        GameCharacter[] army = new GameCharacter[5];
        
        army[0] = new Warrior("Sir Lancelot", 120, 25, "Excalibur", 40);
        army[1] = new Mage("Merlin", 80, 20, 100, "Fireball");
        army[2] = new Archer("Robin Hood", 90, 22, 30, 150);
        army[3] = new Warrior("Thor", 150, 30, "Mjolnir", 50);
        army[4] = new Mage("Gandalf", 75, 18, 80, "Lightning");
        
        System.out.println("Army Stats:");
        for (int i = 0; i < army.length; i++) {
            army[i].showStats();
        }
        System.out.println();
        
        System.out.println("Battle Begins! Each character attacks:");
        System.out.println();
        
        for (int i = 0; i < army.length; i++) {
            army[i].attack();
            System.out.println();
        }
        
        System.out.println("Second Round of Attacks:");
        System.out.println();
        
        for (int i = 0; i < army.length; i++) {
            army[i].attack();
            System.out.println();
        }
        
        System.out.println("Dynamic Method Dispatch in action!");
        System.out.println("Same method call 'attack()' but different behavior for each character type!");
        System.out.println("Java decides which method to run based on the actual object type at runtime!");
    }
}