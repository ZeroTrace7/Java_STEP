import java.util.Random;

enum CrewRank {
    CADET(1), OFFICER(2), COMMANDER(3), CAPTAIN(4), ADMIRAL(5);
    
    private final int level;
    
    CrewRank(int level) {
        this.level = level;
    }
    
    public final int getLevel() {
        return level;
    }
    
    public final boolean canPromoteTo(CrewRank targetRank) {
        return targetRank.level == this.level + 1;
    }
    
    public final CrewRank getNextRank() {
        switch (this) {
            case CADET: return OFFICER;
            case OFFICER: return COMMANDER;
            case COMMANDER: return CAPTAIN;
            case CAPTAIN: return ADMIRAL;
            default: return ADMIRAL;
        }
    }
}

public class SpaceCrew {
    private final String crewId;
    private final String crewName;
    private final String homeplanet;
    private final CrewRank initialRank;
    
    private CrewRank currentRank;
    private int skillLevel;
    private int missionCount;
    private double spaceHours;
    
    private static final String STATION_NAME = "Stellar Odyssey";
    private static final int MAX_CREW_CAPACITY = 50;
    private static final String[] PLANETS = {"Earth", "Mars", "Europa", "Titan", "Proxima B", "Kepler-442b", "Gliese 667Cc"};
    private static final Random random = new Random();
    private static int nextCrewNumber = 1001;
    private static int currentCrewCount = 0;
    
    public SpaceCrew(String crewName) {
        this(crewName, getRandomPlanet(), CrewRank.CADET, 1, 0, 0.0);
        System.out.println("Emergency recruitment: " + crewName + " assigned random homeplanet: " + homeplanet);
    }
    
    public SpaceCrew(String crewName, String homeplanet, CrewRank initialRank) {
        this(crewName, homeplanet, initialRank, 1, 0, 0.0);
    }
    
    public SpaceCrew(String crewName, String homeplanet, CrewRank initialRank, int skillLevel, int missionCount, double spaceHours) {
        this.crewId = generateCrewId();
        this.crewName = crewName;
        this.homeplanet = homeplanet;
        this.initialRank = initialRank;
        this.currentRank = initialRank;
        this.skillLevel = Math.max(1, Math.min(10, skillLevel));
        this.missionCount = Math.max(0, missionCount);
        this.spaceHours = Math.max(0.0, spaceHours);
        
        if (currentCrewCount < MAX_CREW_CAPACITY) {
            currentCrewCount++;
            System.out.println("New crew member registered: " + crewName + " (ID: " + crewId + ")");
        } else {
            System.out.println("Warning: Station at maximum capacity!");
        }
    }
    
    private static String generateCrewId() {
        return "SC-" + STATION_NAME.substring(0, 2).toUpperCase() + "-" + (nextCrewNumber++);
    }
    
    private static String getRandomPlanet() {
        return PLANETS[random.nextInt(PLANETS.length)];
    }
    
    public final String getCrewIdentification() {
        return "Crew ID: " + crewId + " | Name: " + crewName + " | Origin: " + homeplanet + 
               " | Initial Rank: " + initialRank + " | Station: " + STATION_NAME;
    }
    
    public final boolean canBePromoted() {
        if (currentRank == CrewRank.ADMIRAL) {
            return false;
        }
        
        int experienceRequired = currentRank.getLevel() * 5;
        int hoursRequired = currentRank.getLevel() * 100;
        
        return skillLevel >= experienceRequired && 
               missionCount >= currentRank.getLevel() * 2 && 
               spaceHours >= hoursRequired;
    }
    
    public final int calculateSpaceExperience() {
        int baseExperience = skillLevel * 10;
        int missionBonus = missionCount * 15;
        int hoursBonus = (int)(spaceHours / 10);
        int rankMultiplier = currentRank.getLevel();
        int initialRankBonus = initialRank.getLevel() * 5;
        
        return (baseExperience + missionBonus + hoursBonus) * rankMultiplier + initialRankBonus;
    }
    
    public void promoteIfEligible() {
        if (canBePromoted()) {
            CrewRank oldRank = currentRank;
            currentRank = currentRank.getNextRank();
            System.out.println(crewName + " promoted from " + oldRank + " to " + currentRank + "!");
        } else {
            System.out.println(crewName + " not eligible for promotion yet.");
            displayPromotionRequirements();
        }
    }
    
    public void completeMission(int difficultyLevel, double hoursSpent) {
        missionCount++;
        spaceHours += hoursSpent;
        skillLevel += Math.min(2, difficultyLevel);
        if (skillLevel > 10) skillLevel = 10;
        
        System.out.println(crewName + " completed mission! Skill level: " + skillLevel + 
                          ", Total missions: " + missionCount + ", Space hours: " + String.format("%.1f", spaceHours));
        
        if (canBePromoted()) {
            System.out.println(crewName + " is now eligible for promotion!");
        }
    }
    
    public void trainSkills(int hoursSpent) {
        double skillGain = hoursSpent * 0.1;
        if (skillLevel + skillGain <= 10) {
            skillLevel += skillGain;
        } else {
            skillLevel = 10;
        }
        spaceHours += hoursSpent;
        
        System.out.println(crewName + " completed " + hoursSpent + " hours of training. Skill level: " + 
                          String.format("%.1f", skillLevel));
    }
    
    private void displayPromotionRequirements() {
        if (currentRank != CrewRank.ADMIRAL) {
            int skillRequired = currentRank.getLevel() * 5;
            int missionsRequired = currentRank.getLevel() * 2;
            int hoursRequired = currentRank.getLevel() * 100;
            
            System.out.println("Promotion requirements for " + currentRank.getNextRank() + ":");
            System.out.println("  Skill Level: " + skillLevel + "/" + skillRequired);
            System.out.println("  Missions: " + missionCount + "/" + missionsRequired);
            System.out.println("  Space Hours: " + String.format("%.1f", spaceHours) + "/" + hoursRequired);
        }
    }
    
    public void displayCrewProfile() {
        System.out.println();
        System.out.println("CREW PROFILE");
        System.out.println(getCrewIdentification());
        System.out.println("Current Rank: " + currentRank + " (Level " + currentRank.getLevel() + ")");
        System.out.println("Skill Level: " + String.format("%.1f", skillLevel) + "/10.0");
        System.out.println("Missions Completed: " + missionCount);
        System.out.println("Space Hours: " + String.format("%.1f", spaceHours));
        System.out.println("Space Experience Score: " + calculateSpaceExperience());
        System.out.println("Promotion Eligible: " + (canBePromoted() ? "Yes" : "No"));
        System.out.println();
    }
    
    public static void displayStationInfo() {
        System.out.println("STATION INFORMATION");
        System.out.println("Station Name: " + STATION_NAME);
        System.out.println("Current Crew: " + currentCrewCount + "/" + MAX_CREW_CAPACITY);
        System.out.println("Available Capacity: " + (MAX_CREW_CAPACITY - currentCrewCount));
        System.out.println();
    }
    
    public static int getCurrentCrewCount() {
        return currentCrewCount;
    }
    
    public static final String getStationName() {
        return STATION_NAME;
    }
    
    public static final int getMaxCapacity() {
        return MAX_CREW_CAPACITY;
    }
    
    public final String getCrewId() { return crewId; }
    public String getCrewName() { return crewName; }
    public final String getHomeplanet() { return homeplanet; }
    public final CrewRank getInitialRank() { return initialRank; }
    public CrewRank getCurrentRank() { return currentRank; }
    public double getSkillLevel() { return skillLevel; }
    public int getMissionCount() { return missionCount; }
    public double getSpaceHours() { return spaceHours; }
    
    public static void main(String[] args) {
        System.out.println("SPACE STATION CREW MANAGEMENT SYSTEM");
        System.out.println();
        
        displayStationInfo();
        
        System.out.println("CREW RECRUITMENT:");
        System.out.println();
        
        System.out.println("1. Emergency Recruitment (minimal info):");
        SpaceCrew crew1 = new SpaceCrew("Alex Nova");
        crew1.displayCrewProfile();
        
        System.out.println("2. Standard Recruitment:");
        SpaceCrew crew2 = new SpaceCrew("Luna Chen", "Mars", CrewRank.OFFICER);
        crew2.displayCrewProfile();
        
        System.out.println("3. Experienced Transfer:");
        SpaceCrew crew3 = new SpaceCrew("Commander Rodriguez", "Europa", CrewRank.COMMANDER, 7, 12, 850.5);
        crew3.displayCrewProfile();
        
        System.out.println("4. Full Detailed Profile:");
        SpaceCrew crew4 = new SpaceCrew("Captain Stellar", "Proxima B", CrewRank.CAPTAIN, 9, 25, 1500.0);
        crew4.displayCrewProfile();
        
        System.out.println("CREW DEVELOPMENT SIMULATION:");
        System.out.println();
        
        System.out.println("Training Alex Nova:");
        for (int i = 0; i < 5; i++) {
            crew1.trainSkills(20);
        }
        
        System.out.println();
        System.out.println("Alex Nova mission assignments:");
        crew1.completeMission(2, 45.5);
        crew1.completeMission(3, 62.0);
        crew1.completeMission(1, 30.0);
        
        crew1.displayCrewProfile();
        crew1.promoteIfEligible();
        
        System.out.println();
        System.out.println("Luna Chen career progression:");
        for (int mission = 1; mission <= 8; mission++) {
            crew2.completeMission(2 + (mission % 3), 50.0 + (mission * 10));
            if (mission % 3 == 0) {
                crew2.promoteIfEligible();
            }
        }
        
        crew2.displayCrewProfile();
        
        System.out.println("FINAL KEYWORD DEMONSTRATIONS:");
        System.out.println();
        
        SpaceCrew[] allCrew = {crew1, crew2, crew3, crew4};
        
        for (SpaceCrew crew : allCrew) {
            System.out.println("PERMANENT IDENTIFICATION:");
            System.out.println(crew.getCrewIdentification());
            System.out.println("Can be promoted: " + crew.canBePromoted());
            System.out.println("Space Experience: " + crew.calculateSpaceExperience());
            System.out.println();
        }
        
        System.out.println("STATION CAPACITY TEST:");
        System.out.println("Creating multiple crew members to test capacity...");
        for (int i = 5; i <= 52; i++) {
            SpaceCrew testCrew = new SpaceCrew("Crew Member " + i, "Earth", CrewRank.CADET);
            if (i == 51) {
                System.out.println("Approaching maximum capacity...");
            }
        }
        
        displayStationInfo();
        
        System.out.println("RANK SYSTEM DEMONSTRATION:");
        System.out.println("Available ranks and levels:");
        for (CrewRank rank : CrewRank.values()) {
            System.out.println(rank + " (Level " + rank.getLevel() + ")");
        }
        
        System.out.println();
        System.out.println("FINAL KEYWORD CONCEPTS DEMONSTRATED:");
        System.out.println("1. final fields - crewId, homeplanet, initialRank cannot be changed");
        System.out.println("2. final methods - getCrewIdentification(), canBePromoted(), calculateSpaceExperience()");
        System.out.println("3. final static fields - STATION_NAME, MAX_CREW_CAPACITY are constants");
        System.out.println("4. final enum - CrewRank with fixed values and final methods");
        System.out.println("5. Constructor overloading with this() chaining");
        System.out.println("6. Static usage for station-wide information and utilities");
        System.out.println();
        System.out.println("Total crew registered: " + SpaceCrew.getCurrentCrewCount());
    }
}