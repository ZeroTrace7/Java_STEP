import java.util.Random;
import java.util.Arrays;
import java.util.Objects;

// Immutable SecurityClearance class
public final class SecurityClearance {
    // Time constants for clarity
    private static final long ONE_DAY_MILLIS = 86_400_000L;
    private static final long ONE_YEAR_MILLIS = 31_536_000_000L;
    
    private final String clearanceId;
    private final String level;
    private final String[] authorizedSections;
    private final long expirationDate;

    public SecurityClearance(String clearanceId, String level, String[] authorizedSections, long expirationDate) {
        if (clearanceId == null || clearanceId.trim().isEmpty()) {
            throw new IllegalArgumentException("Clearance ID cannot be null or empty");
        }
        if (level == null || level.trim().isEmpty()) {
            throw new IllegalArgumentException("Level cannot be null or empty");
        }
        if (authorizedSections == null || authorizedSections.length == 0) {
            throw new IllegalArgumentException("Authorized sections cannot be null or empty");
        }
        if (expirationDate < System.currentTimeMillis()) {
            throw new IllegalArgumentException("Expiration date cannot be in the past");
        }
        
        this.clearanceId = clearanceId.trim();
        this.level = level.trim();
        this.authorizedSections = authorizedSections.clone(); // Defensive copy
        this.expirationDate = expirationDate;
    }

    public final boolean canAccess(String section) {
        if (section == null) return false;
        if (isExpired()) return false;
        
        for (String s : authorizedSections) {
            if (s != null && s.equals(section)) {
                return true;
            }
        }
        return false;
    }

    public final boolean isExpired() {
        return System.currentTimeMillis() > expirationDate;
    }

    public final int getAccessHash() {
        return Objects.hash(clearanceId, level);
    }

    // Getters with defensive copying
    public String getClearanceId() {
        return clearanceId;
    }

    public String getLevel() {
        return level;
    }

    public String[] getAuthorizedSections() {
        return authorizedSections.clone(); // Defensive copy
    }

    public long getExpirationDate() {
        return expirationDate;
    }
    
    @Override
    public String toString() {
        return String.format("SecurityClearance{id='%s', level='%s', sections=%s, expired=%s}", 
                           clearanceId, level, Arrays.toString(authorizedSections), isExpired());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SecurityClearance that = (SecurityClearance) obj;
        return expirationDate == that.expirationDate &&
               Objects.equals(clearanceId, that.clearanceId) &&
               Objects.equals(level, that.level) &&
               Arrays.equals(authorizedSections, that.authorizedSections);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(clearanceId, level, Arrays.hashCode(authorizedSections), expirationDate);
    }
}

// Immutable CrewRank class with factory methods
public final class CrewRank {
    private final String rankName;
    private final int level;
    private final String[] permissions;

    private CrewRank(String rankName, int level, String[] permissions) {
        if (rankName == null || rankName.trim().isEmpty()) {
            throw new IllegalArgumentException("Rank name cannot be null or empty");
        }
        if (level < 1) {
            throw new IllegalArgumentException("Level must be positive");
        }
        if (permissions == null || permissions.length == 0) {
            throw new IllegalArgumentException("Permissions cannot be null or empty");
        }
        
        this.rankName = rankName.trim();
        this.level = level;
        this.permissions = permissions.clone(); // Defensive copy
    }

    // Static factory methods
    public static CrewRank createCadet() {
        return new CrewRank("Cadet", 1, new String[]{"Basic Access"});
    }

    public static CrewRank createOfficer() {
        return new CrewRank("Officer", 2, new String[]{"Basic Access", "Command Access"});
    }

    public static CrewRank createCommander() {
        return new CrewRank("Commander", 3, new String[]{"Basic Access", "Command Access", "Tactical Access"});
    }

    public static CrewRank createCaptain() {
        return new CrewRank("Captain", 4, new String[]{"Full Access"});
    }

    public static CrewRank createAdmiral() {
        return new CrewRank("Admiral", 5, new String[]{"Full Access", "Strategic Access"});
    }

    // Getters only
    public String getRankName() {
        return rankName;
    }

    public int getLevel() {
        return level;
    }

    public String[] getPermissions() {
        return permissions.clone(); // Defensive copy
    }
    
    @Override
    public String toString() {
        return String.format("CrewRank{name='%s', level=%d, permissions=%s}", 
                           rankName, level, Arrays.toString(permissions));
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CrewRank crewRank = (CrewRank) obj;
        return level == crewRank.level &&
               Objects.equals(rankName, crewRank.rankName) &&
               Arrays.equals(permissions, crewRank.permissions);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(rankName, level, Arrays.hashCode(permissions));
    }
}

// SpaceCrew class with mixed final/mutable attributes
public class SpaceCrew {
    // Class constants
    public static final String STATION_NAME = "Stellar Odyssey";
    public static final int MAX_CREW_CAPACITY = 50;
    
    // Time constants
    private static final long ONE_DAY_MILLIS = 86_400_000L;
    private static final long ONE_YEAR_MILLIS = 31_536_000_000L;
    
    // Immutable attributes (final)
    private final String crewId;
    private final String homePlanet;
    private final SecurityClearance clearance;
    private final CrewRank initialRank;

    // Mutable attributes
    private CrewRank currentRank;
    private int missionCount;
    private double spaceHours;
    
    // Static random for planet generation
    private static final Random random = new Random();
    private static final String[] RANDOM_PLANETS = {
        "Kepler-442b", "Proxima Centauri b", "TRAPPIST-1e", "K2-18b", "TOI-715b"
    };

    // Constructor 1: Emergency recruitment (minimal info, generates random homeplanet)
    public SpaceCrew(String crewId) {
        this(crewId, generateRandomPlanet(), CrewRank.createCadet());
    }

    // Constructor 2: Standard recruitment (name, homeplanet, initialRank)
    public SpaceCrew(String crewId, String homePlanet, CrewRank initialRank) {
        this(crewId, homePlanet, initialRank, 
             createStandardClearance(crewId), 0, 0.0);
    }

    // Constructor 3: Experienced transfer (includes mission count and skills)
    public SpaceCrew(String crewId, String homePlanet, CrewRank initialRank, 
                    int missionCount, double spaceHours) {
        this(crewId, homePlanet, initialRank, 
             createExperiencedClearance(crewId), missionCount, spaceHours);
    }

    // Constructor 4: Full detailed constructor with security clearance
    public SpaceCrew(String crewId, String homePlanet, CrewRank initialRank, 
                    SecurityClearance clearance, int missionCount, double spaceHours) {
        // Validation
        if (crewId == null || crewId.trim().isEmpty()) {
            throw new IllegalArgumentException("Crew ID cannot be null or empty");
        }
        if (homePlanet == null || homePlanet.trim().isEmpty()) {
            throw new IllegalArgumentException("Home planet cannot be null or empty");
        }
        if (initialRank == null) {
            throw new IllegalArgumentException("Initial rank cannot be null");
        }
        if (clearance == null) {
            throw new IllegalArgumentException("Security clearance cannot be null");
        }
        if (missionCount < 0) {
            throw new IllegalArgumentException("Mission count cannot be negative");
        }
        if (spaceHours < 0) {
            throw new IllegalArgumentException("Space hours cannot be negative");
        }
        
        // Initialize final attributes
        this.crewId = crewId.trim();
        this.homePlanet = homePlanet.trim();
        this.initialRank = initialRank;
        this.clearance = clearance;
        
        // Initialize mutable attributes
        this.currentRank = initialRank;
        this.missionCount = missionCount;
        this.spaceHours = spaceHours;
    }

    // Helper methods for constructor chaining
    private static String generateRandomPlanet() {
        return RANDOM_PLANETS[random.nextInt(RANDOM_PLANETS.length)];
    }
    
    private static SecurityClearance createStandardClearance(String crewId) {
        return new SecurityClearance(
            "STD-" + crewId,
            "Standard",
            new String[]{"Basic Section", "Command Section"},
            System.currentTimeMillis() + ONE_YEAR_MILLIS
        );
    }
    
    private static SecurityClearance createExperiencedClearance(String crewId) {
        return new SecurityClearance(
            "EXP-" + crewId,
            "Experienced",
            new String[]{"Basic Section", "Command Section", "Advanced Section"},
            System.currentTimeMillis() + ONE_YEAR_MILLIS
        );
    }

    // Access control method using final clearance
    public boolean canAccessSection(String section) {
        return clearance.canAccess(section);
    }

    // Getters for final attributes
    public String getCrewId() {
        return crewId;
    }

    public String getHomePlanet() {
        return homePlanet;
    }

    public SecurityClearance getClearance() {
        return clearance;
    }

    public CrewRank getInitialRank() {
        return initialRank;
    }

    // Getters and setters for mutable attributes with validation
    public CrewRank getCurrentRank() {
        return currentRank;
    }

    public void setCurrentRank(CrewRank currentRank) {
        if (currentRank == null) {
            throw new IllegalArgumentException("Current rank cannot be null");
        }
        this.currentRank = currentRank;
    }

    public int getMissionCount() {
        return missionCount;
    }

    public void setMissionCount(int missionCount) {
        if (missionCount < 0) {
            throw new IllegalArgumentException("Mission count cannot be negative");
        }
        this.missionCount = missionCount;
    }

    public double getSpaceHours() {
        return spaceHours;
    }

    public void setSpaceHours(double spaceHours) {
        if (spaceHours < 0) {
            throw new IllegalArgumentException("Space hours cannot be negative");
        }
        this.spaceHours = spaceHours;
    }
    
    @Override
    public String toString() {
        return String.format("SpaceCrew{id='%s', planet='%s', initialRank=%s, currentRank=%s, missions=%d, hours=%.1f, clearanceExpired=%s}", 
                           crewId, homePlanet, initialRank.getRankName(), 
                           currentRank.getRankName(), missionCount, spaceHours, clearance.isExpired());
    }
}

// Example usage and testing
class SpaceStationDemo {
    public static void main(String[] args) {
        System.out.println("=== Space Station " + SpaceCrew.STATION_NAME + " Security System ===\n");
        
        // Test different constructor patterns
        
        // Emergency recruitment
        SpaceCrew emergency = new SpaceCrew("EMG001");
        System.out.println("Emergency Recruit: " + emergency);
        System.out.println("Can access Basic Section: " + emergency.canAccessSection("Basic Section"));
        System.out.println();
        
        // Standard recruitment
        SpaceCrew standard = new SpaceCrew("STD001", "Earth", CrewRank.createOfficer());
        System.out.println("Standard Recruit: " + standard);
        System.out.println("Can access Command Section: " + standard.canAccessSection("Command Section"));
        System.out.println();
        
        // Experienced transfer
        SpaceCrew experienced = new SpaceCrew("EXP001", "Mars", CrewRank.createCommander(), 15, 2500.5);
        System.out.println("Experienced Transfer: " + experienced);
        System.out.println("Can access Advanced Section: " + experienced.canAccessSection("Advanced Section"));
        System.out.println();
        
        // Demonstrate rank progression
        System.out.println("=== Rank Progression ===");
        standard.setCurrentRank(CrewRank.createCommander());
        standard.setMissionCount(10);
        standard.setSpaceHours(1000.0);
        System.out.println("After promotion: " + standard);
        
        // Show immutability
        System.out.println("\n=== Immutability Test ===");
        System.out.println("Initial rank never changes: " + standard.getInitialRank().getRankName());
        System.out.println("Current rank can change: " + standard.getCurrentRank().getRankName());
    }
}