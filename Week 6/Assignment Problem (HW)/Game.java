import java.util.Objects;

// Base Game class
public class Game {
    // Protected fields for inheritance
    protected String name;
    protected int minPlayers;
    protected int maxPlayers;
    protected int estimatedTimeMinutes;
    protected String difficulty; // Easy, Medium, Hard
    
    // Constructor
    public Game(String name, int minPlayers, int maxPlayers, 
                int estimatedTimeMinutes, String difficulty) {
        this.name = name;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.estimatedTimeMinutes = estimatedTimeMinutes;
        this.difficulty = difficulty;
    }
    
    // Override toString() method
    @Override
    public String toString() {
        return "Game{" +
               "name='" + name + '\'' +
               ", players=" + minPlayers + "-" + maxPlayers +
               ", time=" + estimatedTimeMinutes + " min" +
               ", difficulty='" + difficulty + '\'' +
               '}';
    }
    
    // Override equals() method
    @Override
    public boolean equals(Object obj) {
        // Step 1: Check if same object reference
        if (this == obj) {
            return true;
        }
        
        // Step 2: Check if obj is null
        if (obj == null) {
            return false;
        }
        
        // Step 3: Check if same class type
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        // Step 4: Cast and compare fields
        Game game = (Game) obj;
        return minPlayers == game.minPlayers &&
               maxPlayers == game.maxPlayers &&
               estimatedTimeMinutes == game.estimatedTimeMinutes &&
               Objects.equals(name, game.name) &&
               Objects.equals(difficulty, game.difficulty);
    }
    
    // Override hashCode() method
    @Override
    public int hashCode() {
        return Objects.hash(name, minPlayers, maxPlayers, 
                          estimatedTimeMinutes, difficulty);
    }
    
    // Regular methods
    public String getName() {
        return name;
    }
    
    public void startGame() {
        System.out.println("Starting game: " + name);
    }
    
    public void endGame() {
        System.out.println("Game " + name + " has ended");
    }
}

// CardGame extends Game
class CardGame extends Game {
    // CardGame-specific fields
    private int numberOfDecks;
    private String cardType; // Standard, Tarot, UNO, etc.
    private boolean shuffleRequired;
    private String gameType; // Trick-taking, Shedding, Matching, etc.
    
    // Constructor
    public CardGame(String name, int minPlayers, int maxPlayers, 
                   int estimatedTimeMinutes, String difficulty,
                   int numberOfDecks, String cardType, 
                   boolean shuffleRequired, String gameType) {
        // Call parent constructor
        super(name, minPlayers, maxPlayers, estimatedTimeMinutes, difficulty);
        this.numberOfDecks = numberOfDecks;
        this.cardType = cardType;
        this.shuffleRequired = shuffleRequired;
        this.gameType = gameType;
    }
    
    // Override toString() method - uses super.toString()
    @Override
    public String toString() {
        // Call parent's toString and add card-specific info
        String parentInfo = super.toString();
        // Remove the closing brace from parent and add card info
        String baseInfo = parentInfo.substring(0, parentInfo.length() - 1);
        
        return baseInfo + 
               ", decks=" + numberOfDecks +
               ", cardType='" + cardType + '\'' +
               ", shuffle=" + shuffleRequired +
               ", gameType='" + gameType + '\'' +
               '}';
    }
    
    // Override equals() method
    @Override
    public boolean equals(Object obj) {
        // Step 1: Check parent equality first
        if (!super.equals(obj)) {
            return false;
        }
        
        // Step 2: Check if same class (already done by super.equals)
        // Step 3: Cast and compare card-specific fields
        CardGame cardGame = (CardGame) obj;
        return numberOfDecks == cardGame.numberOfDecks &&
               shuffleRequired == cardGame.shuffleRequired &&
               Objects.equals(cardType, cardGame.cardType) &&
               Objects.equals(gameType, cardGame.gameType);
    }
    
    // Override hashCode() method
    @Override
    public int hashCode() {
        // Include parent's hash and add card-specific fields
        return Objects.hash(super.hashCode(), numberOfDecks, 
                          cardType, shuffleRequired, gameType);
    }
    
    // CardGame-specific methods
    public void shuffleCards() {
        if (shuffleRequired) {
            System.out.println("Shuffling " + numberOfDecks + " deck(s) of " + cardType + " cards");
        } else {
            System.out.println("No shuffling required for " + name);
        }
    }
    
    public void dealCards() {
        System.out.println("Dealing cards for " + name + " (" + gameType + " game)");
    }
    
    // Override startGame to include card setup
    @Override
    public void startGame() {
        super.startGame(); // Call parent method
        shuffleCards();
        dealCards();
        System.out.println("Card game setup complete!");
    }
}

// Demo class to test object method overriding
class GameOverrideDemo {
    public static void main(String[] args) {
        System.out.println("Creating Game Objects");
        
        // Create Game objects
        Game game1 = new Game("Chess", 2, 2, 60, "Hard");
        Game game2 = new Game("Chess", 2, 2, 60, "Hard");
        Game game3 = new Game("Checkers", 2, 2, 30, "Medium");
        
        // Create CardGame objects
        CardGame poker = new CardGame("Poker", 2, 8, 120, "Hard", 
                                    1, "Standard", true, "Betting");
        CardGame poker2 = new CardGame("Poker", 2, 8, 120, "Hard", 
                                     1, "Standard", true, "Betting");
        CardGame uno = new CardGame("UNO", 2, 10, 30, "Easy", 
                                  1, "UNO", true, "Shedding");
        
        System.out.println("\nTesting toString() Override");
        System.out.println("Game: " + game1.toString());
        System.out.println("CardGame: " + poker.toString());
        System.out.println("UNO: " + uno.toString());
        
        System.out.println("\nTesting equals() Override");
        System.out.println("game1.equals(game2): " + game1.equals(game2)); // true
        System.out.println("game1.equals(game3): " + game1.equals(game3)); // false
        System.out.println("poker.equals(poker2): " + poker.equals(poker2)); // true
        System.out.println("poker.equals(uno): " + poker.equals(uno)); // false
        
        // Test equals with different types
        System.out.println("game1.equals(poker): " + game1.equals(poker)); // false
        
        System.out.println("\nTesting hashCode() Override");
        System.out.println("game1.hashCode(): " + game1.hashCode());
        System.out.println("game2.hashCode(): " + game2.hashCode());
        System.out.println("Equal objects same hash? " + (game1.hashCode() == game2.hashCode()));
        
        System.out.println("poker.hashCode(): " + poker.hashCode());
        System.out.println("poker2.hashCode(): " + poker2.hashCode());
        System.out.println("Equal CardGames same hash? " + (poker.hashCode() == poker2.hashCode()));
        
        System.out.println("\nTesting Reference vs Content Equality");
        Game game4 = game1; // Same reference
        System.out.println("game1 == game4 (reference): " + (game1 == game4)); // true
        System.out.println("game1.equals(game4): " + game1.equals(game4)); // true
        System.out.println("game1 == game2 (reference): " + (game1 == game2)); // false
        System.out.println("game1.equals(game2): " + game1.equals(game2)); // true
        
        System.out.println("\nTesting Method Inheritance");
        poker.startGame(); // Overridden method with super call
        uno.shuffleCards(); // CardGame-specific method
        
        System.out.println("\nTesting Null and Different Type Equality");
        System.out.println("game1.equals(null): " + game1.equals(null)); // false
        System.out.println("game1.equals(\"String\"): " + game1.equals("String")); // false
        
        System.out.println("\nDemonstrating toString in Collections");
        java.util.List<Game> games = java.util.Arrays.asList(game1, poker, uno);
        System.out.println("Games list: " + games);
    }
}