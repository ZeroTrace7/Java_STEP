// Base class - Instrument
class Instrument {
    protected String name;
    protected String material;
    protected String type;
    
    public Instrument(String name, String material, String type) {
        this.name = name;
        this.material = material;
        this.type = type;
        System.out.println("Instrument created: " + name);
    }
    
    public void displayInfo() {
        System.out.println("Instrument: " + name);
        System.out.println("Material: " + material);
        System.out.println("Type: " + type);
    }
    
    public void play() {
        System.out.println("Playing the " + name);
    }
}

// Child class 1 - Piano extends Instrument
class Piano extends Instrument {
    private int keys;
    private String pianoType;
    
    public Piano(String name, String material, int keys, String pianoType) {
        super(name, material, "Keyboard Instrument");
        this.keys = keys;
        this.pianoType = pianoType;
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Number of keys: " + keys);
        System.out.println("Piano type: " + pianoType);
    }
    
    @Override
    public void play() {
        System.out.println("Playing beautiful melodies on " + keys + "-key " + name);
    }
    
    public void playChords() {
        System.out.println("Playing harmonious chords on the " + pianoType + " piano");
    }
}

// Child class 2 - Guitar extends Instrument
class Guitar extends Instrument {
    private int strings;
    private String guitarType;
    
    public Guitar(String name, String material, int strings, String guitarType) {
        super(name, material, "String Instrument");
        this.strings = strings;
        this.guitarType = guitarType;
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Number of strings: " + strings);
        System.out.println("Guitar type: " + guitarType);
    }
    
    @Override
    public void play() {
        System.out.println("Strumming " + strings + " strings on the " + guitarType + " guitar");
    }
    
    public void strum() {
        System.out.println("Strumming all " + strings + " strings together");
    }
}

// Child class 3 - Drum extends Instrument
class Drum extends Instrument {
    private String drumType;
    private int size; // in inches
    
    public Drum(String name, String material, String drumType, int size) {
        super(name, material, "Percussion Instrument");
        this.drumType = drumType;
        this.size = size;
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Drum type: " + drumType);
        System.out.println("Size: " + size + " inches");
    }
    
    @Override
    public void play() {
        System.out.println("Beating rhythms on the " + size + "-inch " + drumType);
    }
    
    public void beat() {
        System.out.println("Creating powerful beats with the " + drumType);
    }
}

// Main class
public class MusicalInstrumentFamily {
    public static void main(String[] args) {
        System.out.println("Musical Instrument Family Demo\n");
        
        // Create individual instruments
        Piano piano = new Piano("Grand Piano", "Wood", 88, "Grand");
        Guitar guitar = new Guitar("Acoustic Guitar", "Wood", 6, "Acoustic");
        Drum drum = new Drum("Snare Drum", "Metal", "Snare", 14);
        
        // Test individual instruments
        System.out.println("1. Individual Instruments:");
        piano.displayInfo();
        piano.play();
        piano.playChords();
        
        System.out.println("\n--------------------\n");
        
        guitar.displayInfo();
        guitar.play();
        guitar.strum();
        
        System.out.println("\n--------------------\n");
        
        drum.displayInfo();
        drum.play();
        drum.beat();
        
        System.out.println("\n--------------------------------------\n");
        
        // Test hierarchical inheritance with polymorphism
        System.out.println("2. Orchestra Array (Polymorphism):");
        Instrument[] orchestra = {
            new Piano("Upright Piano", "Wood", 76, "Upright"),
            new Guitar("Electric Guitar", "Wood", 6, "Electric"),
            new Drum("Bass Drum", "Wood", "Bass", 22),
            new Piano("Digital Piano", "Plastic", 61, "Digital"),
            new Guitar("Classical Guitar", "Wood", 6, "Classical")
        };
        
        System.out.println("Welcome to our orchestra performance!\n");
        
        for (int i = 0; i < orchestra.length; i++) {
            System.out.println("Instrument " + (i + 1) + ":");
            orchestra[i].displayInfo();
            orchestra[i].play();
            System.out.println();
        }
        
        System.out.println("All instruments playing together - Beautiful harmony!");
    }
}