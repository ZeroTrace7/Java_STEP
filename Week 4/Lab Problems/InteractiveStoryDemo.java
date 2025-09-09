import java.util.*;
import java.util.stream.Collectors;

// Base StoryCharacter class with mixed final and dynamic attributes
abstract class StoryCharacter {
    // Final attributes - these never change
    protected final String characterId;
    protected final String backstory;
    protected final PersonalityType corePersonality;
    protected final String creationOrigin; // How this character was created
    
    // Dynamic attributes - these evolve throughout the story
    protected String currentMood;
    protected Map<String, RelationshipType> relationshipMap;
    protected Set<String> skillSet;
    protected String currentLocation;
    protected int storyExperience;
    protected boolean isAware; // Meta-story feature
    
    // Static story constants
    protected static final String[] STORY_GENRES = {"Fantasy", "Sci-Fi", "Mystery", "Romance", "Adventure"};
    protected static final String[] LOCATIONS = {"Enchanted Forest", "Space Station", "Victorian Mansion", 
                                                "City Café", "Mountain Peak", "Underground Lab"};
    protected static final Random random = new Random();
    
    // Base constructor
    protected StoryCharacter(String characterId, String backstory, PersonalityType corePersonality, String creationOrigin) {
        this.characterId = characterId;
        this.backstory = backstory;
        this.corePersonality = corePersonality;
        this.creationOrigin = creationOrigin;
        
        // Initialize dynamic attributes
        this.currentMood = "Neutral";
        this.relationshipMap = new HashMap<>();
        this.skillSet = new HashSet<>();
        this.currentLocation = LOCATIONS[random.nextInt(LOCATIONS.length)];
        this.storyExperience = 0;
        this.isAware = false;
    }
    
    // Abstract methods that must be implemented by subclasses
    public abstract String performAction(String situation);
    public abstract void evolve();
    
    // Concrete methods available to all characters
    public void updateMood(String newMood) {
        this.currentMood = newMood;
        System.out.println(characterId + " is now feeling " + newMood);
    }
    
    public void addRelationship(String otherCharacterId, RelationshipType relationship) {
        relationshipMap.put(otherCharacterId, relationship);
        System.out.println(characterId + " now has a " + relationship + " relationship with " + otherCharacterId);
    }
    
    public void learnSkill(String skill) {
        if (skillSet.add(skill)) {
            System.out.println(characterId + " has learned: " + skill);
        }
    }
    
    public void moveTo(String location) {
        String oldLocation = this.currentLocation;
        this.currentLocation = location;
        System.out.println(characterId + " moved from " + oldLocation + " to " + location);
    }
    
    // Meta-story feature: Character becomes self-aware
    public void becomeAware() {
        if (!isAware) {
            isAware = true;
            System.out.println("💭 " + characterId + " suddenly realizes: 'Wait, I can't change my backstory! It's final!'");
            attemptToHackFinalAttributes();
        }
    }
    
    // Characters attempt to hack their final attributes (always fails humorously)
    protected void attemptToHackFinalAttributes() {
        System.out.println("🔧 " + characterId + " attempts to modify their final backstory...");
        System.out.println("💥 ERROR: Cannot modify final attribute 'backstory'!");
        System.out.println("😤 " + characterId + ": 'Curse these constructor limitations!'");
    }
    
    // Getters
    public String getCharacterId() { return characterId; }
    public String getBackstory() { return backstory; }
    public PersonalityType getCorePersonality() { return corePersonality; }
    public String getCurrentMood() { return currentMood; }
    public String getCurrentLocation() { return currentLocation; }
    public String getCreationOrigin() { return creationOrigin; }
    public Set<String> getSkillSet() { return new HashSet<>(skillSet); }
    public boolean isAware() { return isAware; }
}

// Hero class with different origin stories determining abilities
class Hero extends StoryCharacter {
    private final Set<String> heroicAbilities;
    private final String originStory;
    private int heroismLevel;
    
    // Constructor for chosen one heroes
    public Hero(String name, String originStory) {
        super(name, generateHeroBackstory(originStory), PersonalityType.NOBLE, "Chosen Origin");
        this.originStory = originStory;
        this.heroicAbilities = determineAbilitiesFromOrigin(originStory);
        this.heroismLevel = 1;
        initializeHeroSkills();
    }
    
    // Constructor for reluctant heroes
    public Hero(String name, String pastTragedy, boolean isReluctant) {
        super(name, "Once lived peacefully until: " + pastTragedy, 
              isReluctant ? PersonalityType.BROODING : PersonalityType.NOBLE, "Reluctant Origin");
        this.originStory = "Reluctant Hero";
        this.heroicAbilities = new HashSet<>(Arrays.asList("Determination", "Hidden Strength"));
        this.heroismLevel = isReluctant ? 0 : 1;
        if (isReluctant) {
            this.currentMood = "Reluctant";
        }
        initializeHeroSkills();
    }
    
    // Constructor for creating hero from story prompt
    public Hero(String storyPrompt, String genre) {
        super(parseNameFromPrompt(storyPrompt), 
              parseBackstoryFromPrompt(storyPrompt), 
              PersonalityType.NOBLE, "Story Prompt: " + genre);
        this.originStory = "Prompt-Generated";
        this.heroicAbilities = generateAbilitiesFromGenre(genre);
        this.heroismLevel = 1;
        initializeHeroSkills();
    }
    
    private static String generateHeroBackstory(String originStory) {
        switch (originStory.toLowerCase()) {
            case "chosen one": return "Born with a mysterious birthmark, destined for greatness";
            case "fallen noble": return "Once royalty, now fights to reclaim honor";
            case "common hero": return "An ordinary person who rose to extraordinary circumstances";
            default: return "A hero with a unique origin story: " + originStory;
        }
    }
    
    private Set<String> determineAbilitiesFromOrigin(String origin) {
        Set<String> abilities = new HashSet<>();
        switch (origin.toLowerCase()) {
            case "chosen one":
                abilities.addAll(Arrays.asList("Divine Protection", "Ancient Wisdom", "Prophecy Sense"));
                break;
            case "fallen noble":
                abilities.addAll(Arrays.asList("Royal Combat Training", "Leadership", "Strategic Mind"));
                break;
            case "common hero":
                abilities.addAll(Arrays.asList("Resourcefulness", "Common Sense", "Heart of Gold"));
                break;
            default:
                abilities.addAll(Arrays.asList("Courage", "Determination"));
        }
        return abilities;
    }
    
    private Set<String> generateAbilitiesFromGenre(String genre) {
        Set<String> abilities = new HashSet<>();
        switch (genre) {
            case "Fantasy": abilities.addAll(Arrays.asList("Magic Resistance", "Ancient Lore")); break;
            case "Sci-Fi": abilities.addAll(Arrays.asList("Tech Savvy", "Space Navigation")); break;
            case "Mystery": abilities.addAll(Arrays.asList("Deductive Reasoning", "Observation")); break;
            case "Romance": abilities.addAll(Arrays.asList("Emotional Intelligence", "Charm")); break;
            case "Adventure": abilities.addAll(Arrays.asList("Survival Skills", "Athletic Prowess")); break;
            default: abilities.add("Versatility");
        }
        return abilities;
    }
    
    private void initializeHeroSkills() {
        skillSet.addAll(Arrays.asList("Basic Combat", "Moral Compass"));
        skillSet.addAll(heroicAbilities);
    }
    
    private static String parseNameFromPrompt(String prompt) {
        return prompt.contains("hero") ? "Generated Hero" : "Prompt Hero";
    }
    
    private static String parseBackstoryFromPrompt(String prompt) {
        return "Generated from story prompt: " + prompt.substring(0, Math.min(50, prompt.length()));
    }
    
    @Override
    public String performAction(String situation) {
        heroismLevel++;
        String action;
        
        if (isAware) {
            action = "🎭 " + characterId + " heroically faces " + situation + 
                    " while muttering: 'At least my heroic abilities are final!'";
        } else {
            action = characterId + " bravely confronts " + situation + 
                    " using " + heroicAbilities.iterator().next();
        }
        
        if (heroismLevel > 5 && random.nextBoolean()) {
            becomeAware();
        }
        
        return action;
    }
    
    @Override
    public void evolve() {
        heroismLevel++;
        if (heroismLevel % 3 == 0) {
            learnSkill("Advanced " + heroicAbilities.iterator().next());
        }
        
        // Heroes become more confident over time
        if (heroismLevel > 3 && currentMood.equals("Reluctant")) {
            updateMood("Determined");
        }
    }
}

// Villain class with permanent evil motivation but evolving methods
class Villain extends StoryCharacter {
    private final String evilMotivation; // This is final - villains don't change their core evil
    private final EvilnessLevel evilnessLevel;
    private List<String> evilSchemes;
    private int schemesAttempted;
    
    // Constructor for classic villain
    public Villain(String name, String evilMotivation, EvilnessLevel level) {
        super(name, "Turned to darkness driven by: " + evilMotivation, 
              PersonalityType.EVIL, "Classic Villain");
        this.evilMotivation = evilMotivation;
        this.evilnessLevel = level;
        this.evilSchemes = new ArrayList<>();
        this.schemesAttempted = 0;
        initializeEvilSkills();
        generateInitialSchemes();
    }
    
    // Constructor for redeemable villain
    public Villain(String name, String evilMotivation, boolean isRedeemable) {
        super(name, "Misguided by: " + evilMotivation, 
              isRedeemable ? PersonalityType.MISUNDERSTOOD : PersonalityType.EVIL, 
              "Redeemable Villain");
        this.evilMotivation = evilMotivation;
        this.evilnessLevel = isRedeemable ? EvilnessLevel.MISGUIDED : EvilnessLevel.CORRUPT;
        this.evilSchemes = new ArrayList<>();
        this.schemesAttempted = 0;
        initializeEvilSkills();
        if (isRedeemable) {
            this.currentMood = "Conflicted";
        }
    }
    
    // Constructor from character fusion (combining two existing characters' evil traits)
    public Villain(StoryCharacter char1, StoryCharacter char2) {
        super("Fused-" + char1.getCharacterId() + "-" + char2.getCharacterId(),
              "Born from the fusion of " + char1.getCharacterId() + " and " + char2.getCharacterId(),
              PersonalityType.EVIL, "Character Fusion");
        this.evilMotivation = "Revenge for forced fusion";
        this.evilnessLevel = EvilnessLevel.CORRUPT;
        this.evilSchemes = new ArrayList<>();
        this.schemesAttempted = 0;
        
        // Inherit skills from both characters
        skillSet.addAll(char1.getSkillSet());
        skillSet.addAll(char2.getSkillSet());
        skillSet.add("Fusion Powers");
        
        System.out.println("⚡ " + characterId + " emerges from fusion, angry about being created!");
    }
    
    private void initializeEvilSkills() {
        skillSet.addAll(Arrays.asList("Scheming", "Monologuing", "Dramatic Exits"));
        switch (evilnessLevel) {
            case PETTY: skillSet.add("Petty Pranks"); break;
            case MISGUIDED: skillSet.add("Good Intentions Gone Wrong"); break;
            case CORRUPT: skillSet.addAll(Arrays.asList("Manipulation", "Dark Magic")); break;
            case PURE_EVIL: skillSet.addAll(Arrays.asList("Reality Warping", "Soul Corruption")); break;
        }
    }
    
    private void generateInitialSchemes() {
        switch (evilnessLevel) {
            case PETTY:
                evilSchemes.addAll(Arrays.asList("Steal all the cookies", "Make everyone's socks wet"));
                break;
            case MISGUIDED:
                evilSchemes.addAll(Arrays.asList("Force everyone to be happy", "Eliminate all sadness"));
                break;
            case CORRUPT:
                evilSchemes.addAll(Arrays.asList("Conquer the kingdom", "Steal magical artifacts"));
                break;
            case PURE_EVIL:
                evilSchemes.addAll(Arrays.asList("Destroy reality", "Corrupt all heroes"));
                break;
        }
    }
    
    @Override
    public String performAction(String situation) {
        schemesAttempted++;
        String currentScheme = evilSchemes.get(random.nextInt(evilSchemes.size()));
        
        if (isAware) {
            return "😈 " + characterId + " tries to " + currentScheme + " but pauses: " +
                   "'Wait, why can't I change my evil motivation? I'm stuck being evil forever!'";
        } else {
            return characterId + " responds to " + situation + " by implementing scheme: " + currentScheme;
        }
    }
    
    @Override
    public void evolve() {
        // Villains get more creative with their schemes but can't change their core motivation
        if (schemesAttempted % 2 == 0) {
            String newScheme = "Advanced " + evilSchemes.get(random.nextInt(evilSchemes.size()));
            evilSchemes.add(newScheme);
            System.out.println(characterId + " devises new scheme: " + newScheme);
        }
        
        if (schemesAttempted > 4 && random.nextBoolean()) {
            becomeAware();
        }
    }
    
    @Override
    protected void attemptToHackFinalAttributes() {
        super.attemptToHackFinalAttributes();
        System.out.println("🦹 " + characterId + ": 'I can't even change my evil motivation! " + 
                          "I'm forever driven by: " + evilMotivation + "'");
        System.out.println("😤 " + characterId + ": 'This is ironically making me more evil!'");
    }
    
    public String getEvilMotivation() { return evilMotivation; }
}

// Mysterious Stranger class with hidden attributes
class MysteriousStranger extends StoryCharacter {
    private final String hiddenIdentity; // Final but not revealed until story progresses
    private final String secretMission;
    private final Map<String, Object> hiddenAttributes;
    private int mysteryLevel;
    private boolean identityRevealed;
    
    // Constructor with hidden identity
    public MysteriousStranger(String apparentName, String hiddenIdentity, String secretMission) {
        super(apparentName, "A stranger with mysterious origins", PersonalityType.MYSTERIOUS, "Hidden Identity");
        this.hiddenIdentity = hiddenIdentity;
        this.secretMission = secretMission;
        this.hiddenAttributes = new HashMap<>();
        this.mysteryLevel = 10;
        this.identityRevealed = false;
        
        // Hide their true abilities
        initializeHiddenAttributes();
        skillSet.add("Cryptic Speech");
    }
    
    // Constructor for randomly generated mysterious character
    public MysteriousStranger(String genre) {
        super("The Stranger", "Origins unknown", PersonalityType.MYSTERIOUS, "Random Generation");
        this.hiddenIdentity = generateRandomIdentity(genre);
        this.secretMission = generateRandomMission(genre);
        this.hiddenAttributes = new HashMap<>();
        this.mysteryLevel = 10;
        this.identityRevealed = false;
        
        initializeHiddenAttributes();
        skillSet.add("Genre-Appropriate Mystery");
    }
    
    private void initializeHiddenAttributes() {
        hiddenAttributes.put("TruePower", "Reality Manipulation");
        hiddenAttributes.put("RealAge", random.nextInt(1000) + 100);
        hiddenAttributes.put("SecretWeakness", "Allergic to direct questions");
        hiddenAttributes.put("HiddenKnowledge", "Knows the ending of every story");
    }
    
    private String generateRandomIdentity(String genre) {
        switch (genre) {
            case "Fantasy": return random.nextBoolean() ? "Ancient Wizard" : "Fallen God";
            case "Sci-Fi": return random.nextBoolean() ? "Time Traveler" : "Alien Observer";
            case "Mystery": return random.nextBoolean() ? "Detective in Disguise" : "The Real Culprit";
            case "Romance": return random.nextBoolean() ? "Lost Love" : "Matchmaking Fairy";
            case "Adventure": return random.nextBoolean() ? "Treasure Map Keeper" : "Guardian Spirit";
            default: return "Interdimensional Being";
        }
    }
    
    private String generateRandomMission(String genre) {
        String[] missions = {"Protect the chosen one", "Collect mysterious artifacts", 
                           "Prevent the apocalypse", "Find their lost memories",
                           "Test the other characters"};
        return missions[random.nextInt(missions.length)];
    }
    
    @Override
    public String performAction(String situation) {
        mysteryLevel--;
        
        if (mysteryLevel <= 3 && !identityRevealed) {
            revealIdentity();
        }
        
        if (isAware) {
            return "🔮 " + characterId + " mysteriously handles " + situation + 
                   " while complaining: 'Even I can't change my final hidden identity!'";
        } else if (identityRevealed) {
            return hiddenIdentity + " (formerly " + characterId + ") deals with " + situation + 
                   " using their revealed power: " + hiddenAttributes.get("TruePower");
        } else {
            return characterId + " handles " + situation + " in a mysterious way, " +
                   "hinting at hidden knowledge";
        }
    }
    
    private void revealIdentity() {
        identityRevealed = true;
        System.out.println("🎭 IDENTITY REVEALED!");
        System.out.println("💫 " + characterId + " removes their hood: 'I am actually " + hiddenIdentity + "!'");
        System.out.println("🎯 Secret mission: " + secretMission);
        
        // Add hidden skills to visible skills
        skillSet.add((String) hiddenAttributes.get("TruePower"));
        learnSkill("Identity Revelation Drama");
        
        if (random.nextBoolean()) {
            becomeAware();
        }
    }
    
    @Override
    public void evolve() {
        mysteryLevel--;
        if (mysteryLevel % 2 == 0) {
            learnSkill("Deeper Mystery Level " + (10 - mysteryLevel));
        }
    }
    
    @Override
    protected void attemptToHackFinalAttributes() {
        super.attemptToHackFinalAttributes();
        System.out.println("🔮 " + characterId + ": 'Even with my mysterious powers, I can't change my " +
                          "final hidden identity: " + hiddenIdentity + "'");
        System.out.println("😑 " + characterId + ": 'The irony is not lost on me.'");
    }
    
    public boolean isIdentityRevealed() { return identityRevealed; }
    public String getHiddenIdentity() { return identityRevealed ? hiddenIdentity : "Still Hidden"; }
}

// Comic Relief class with final humor style but dynamic timing
class ComicRelief extends StoryCharacter {
    private final HumorStyle humorStyle; // This never changes
    private final String comedicBackstory;
    private List<String> jokes;
    private int comedyTiming;
    private boolean audienceAppreciation;
    
    // Constructor with specific humor style
    public ComicRelief(String name, HumorStyle humorStyle) {
        super(name, generateComedyBackstory(humorStyle), PersonalityType.CHEERFUL, "Comedy Specialist");
        this.humorStyle = humorStyle;
        this.comedicBackstory = backstory;
        this.jokes = new ArrayList<>();
        this.comedyTiming = 1;
        this.audienceAppreciation = false;
        
        initializeJokes();
        skillSet.addAll(Arrays.asList("Perfect Timing", "Comic Improvisation"));
    }
    
    // Constructor for accidental comic relief
    public ComicRelief(String name, boolean isAccidental) {
        super(name, isAccidental ? "Accidentally hilarious in everything they do" : 
              "Professional comedian seeking the perfect joke", PersonalityType.CHEERFUL, 
              isAccidental ? "Accidental Comedy" : "Intentional Comedy");
        this.humorStyle = isAccidental ? HumorStyle.SLAPSTICK : HumorStyle.WITTY;
        this.comedicBackstory = backstory;
        this.jokes = new ArrayList<>();
        this.comedyTiming = isAccidental ? 0 : 3;
        this.audienceAppreciation = false;
        
        initializeJokes();
        if (isAccidental) {
            skillSet.add("Unintentional Comedy");
        }
    }
    
    private static String generateComedyBackstory(HumorStyle style) {
        switch (style) {
            case WITTY: return "Former court jester with a sharp wit";
            case SLAPSTICK: return "Accident-prone but loveable fool";
            case SARCASTIC: return "Cynical observer of life's absurdities";
            case WHOLESOME: return "Eternally optimistic joke-teller";
            case DARK: return "Finds humor in the darkest situations";
            default: return "Comedy is their life's calling";
        }
    }
    
    private void initializeJokes() {
        switch (humorStyle) {
            case WITTY:
                jokes.addAll(Arrays.asList("That's what she said... to the dragon!",
                                         "I'd make a programming joke, but it would just compile errors"));
                break;
            case SLAPSTICK:
                jokes.addAll(Arrays.asList("*trips over own feet*", "*accidentally saves the day*"));
                break;
            case SARCASTIC:
                jokes.addAll(Arrays.asList("Oh great, another evil plot. How original.",
                                         "Let me guess, we need to save the world again?"));
                break;
            case WHOLESOME:
                jokes.addAll(Arrays.asList("Why don't scientists trust atoms? Because they make up everything!",
                                         "What do you call a bear with no teeth? A gummy bear!"));
                break;
            case DARK:
                jokes.addAll(Arrays.asList("At least the apocalypse has good timing",
                                         "Death is just life's way of saying 'The End'"));
                break;
        }
    }
    
    @Override
    public String performAction(String situation) {
        comedyTiming++;
        String joke = jokes.get(random.nextInt(jokes.size()));
        
        if (isAware) {
            return "🎭 " + characterId + " responds to " + situation + " with: '" + joke + 
                   "' then adds: 'Why can't I change my humor style? I'm stuck being " + 
                   humorStyle + " forever!'";
        } else {
            return characterId + " lightens the mood during " + situation + " with: '" + joke + "'";
        }
    }
    
    @Override
    public void evolve() {
        comedyTiming++;
        
        // Better timing leads to better jokes
        if (comedyTiming % 3 == 0) {
            String newJoke = "Advanced " + humorStyle + " joke #" + comedyTiming;
            jokes.add(newJoke);
            System.out.println(characterId + " develops new material: " + newJoke);
        }
        
        // Audience starts appreciating the humor
        if (comedyTiming > 5) {
            audienceAppreciation = true;
            updateMood("Appreciated");
        }
        
        if (comedyTiming > 7 && random.nextBoolean()) {
            becomeAware();
        }
    }
    
    @Override
    protected void attemptToHackFinalAttributes() {
        super.attemptToHackFinalAttributes();
        System.out.println("🎭 " + characterId + ": 'I can't even change my humor style! " +
                          "I'm forever " + humorStyle + "!'");
        System.out.println("😂 " + characterId + ": 'Well, at least that's ironically funny!'");
    }
    
    public HumorStyle getHumorStyle() { return humorStyle; }
}

// Story Engine class that orchestrates everything
class StoryEngine {
    private final String storyTitle;
    private final String genre;
    private final List<StoryCharacter> characters;
    private final Queue<String> storyEvents;
    private final Map<String, Object> storyState;
    private int chapterNumber;
    private boolean metaMode;
    
    // Static story rules
    private static final Map<String, List<String>> GENRE_EVENTS = new HashMap<>();
    private static final List<String> UNIVERSAL_EVENTS = Arrays.asList(
        "A mysterious door appears", "Someone's past catches up", "An unexpected alliance forms",
        "A secret is revealed", "The stakes are raised", "Characters question their purpose"
    );
    
    static {
        GENRE_EVENTS.put("Fantasy", Arrays.asList("Dragon attack", "Magic goes wrong", "Ancient prophecy unfolds"));
        GENRE_EVENTS.put("Sci-Fi", Arrays.asList("Alien contact", "Technology malfunction", "Time paradox"));
        GENRE_EVENTS.put("Mystery", Arrays.asList("New clue discovered", "Red herring revealed", "Suspect eliminated"));
        GENRE_EVENTS.put("Romance", Arrays.asList("Misunderstanding occurs", "Romantic moment", "Love triangle"));
        GENRE_EVENTS.put("Adventure", Arrays.asList("Treasure map found", "Dangerous trap", "Epic chase scene"));
    }
    
    // Constructor for new story
    public StoryEngine(String title, String genre) {
        this.storyTitle = title;
        this.genre = genre;
        this.characters = new ArrayList<>();
        this.storyEvents = new LinkedList<>();
        this.storyState = new HashMap<>();
        this.chapterNumber = 1;
        this.metaMode = false;
        
        initializeStoryEvents();
        System.out.println("📖 New story created: " + storyTitle + " (" + genre + ")");
    }
    
    // Static method for universal story rules
    public static String generateUniversalPlotTwist() {
        String[] twists = {"The hero was the villain all along", "It was all a dream... or was it?",
                          "The comic relief character is actually the most powerful",
                          "The story is being written by one of the characters"};
        return twists[new Random().nextInt(twists.length)];
    }
    
    // Add characters using different creation methods
    public void addCharacter(StoryCharacter character) {
        characters.add(character);
        System.out.println("✨ " + character.getCharacterId() + " joins the story! (" + character.getCreationOrigin() + ")");
    }
    
    // Create character from story prompt
    public void addCharacterFromPrompt(String prompt) {
        if (prompt.toLowerCase().contains("hero")) {
            addCharacter(new Hero(prompt, genre));
        } else if (prompt.toLowerCase().contains("villain")) {
            // Parse motivation from prompt (simplified)
            String motivation = prompt.contains("revenge") ? "revenge" : "power";
            addCharacter(new Villain("Prompt Villain", motivation, EvilnessLevel.CORRUPT));
        } else {
            addCharacter(new MysteriousStranger(genre));
        }
    }
    
    // Generate random character based on genre
    public void addRandomCharacter() {
        Random rand = new Random();
        switch (rand.nextInt(4)) {
            case 0: addCharacter(new Hero("Random Hero", "common hero")); break;
            case 1: addCharacter(new Villain("Random Villain", "chaos", EvilnessLevel.CORRUPT)); break;
            case 2: addCharacter(new MysteriousStranger(genre)); break;
            case 3: addCharacter(new ComicRelief("Random Comic", HumorStyle.WITTY)); break;
        }
    }
    
    // Character fusion method
    public void fuseCharacters(int index1, int index2) {
        if (index1 >= 0 && index1 < characters.size() && index2 >= 0 && index2 < characters.size()) {
            StoryCharacter char1 = characters.get(index1);
            StoryCharacter char2 = characters.get(index2);
            Villain fusedVillain = new Villain(char1, char2);
            addCharacter(fusedVillain);
        }
    }
    
    private void initializeStoryEvents() {
        // Add universal events
        storyEvents.addAll(UNIVERSAL_EVENTS);
        
        // Add genre-specific events
        if (GENRE_EVENTS.containsKey(genre)) {
            storyEvents.addAll(GENRE_EVENTS.get(genre));
        }
        
        // Shuffle for randomness
        List<String> eventList = new ArrayList<>(storyEvents);
        Collections.shuffle(eventList);
        storyEvents.clear();
        storyEvents.addAll(eventList);
    }
    
    // Main story progression method
    public void advanceStory() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("📚 Chapter " + chapterNumber + " of " + storyTitle);
        System.out.println("=".repeat(50));
        
        if (!storyEvents.isEmpty()) {
            String currentEvent = storyEvents.poll();
            System.out.println("🎭 Current situation: " + currentEvent);
            
            // All characters respond to the event
            for (StoryCharacter character : characters) {
                String action = character.performAction(currentEvent);
                System.out.println("    " + action);
                character.evolve();
            }
            
            // Create character interactions
            createCharacterInteractions();
            
            // Check for meta-story activation
            if (chapterNumber > 3 && !metaMode && Math.random() < 0.3) {
                activateMetaMode();
            }
            
            chapterNumber++;
        } else {
            concludeStory();
        }
    }
    
    private void createCharacterInteractions() {
        System.out.println("\n🤝 Character Interactions:");
        
        for (int i = 0; i < characters.size() - 1; i++) {
            StoryCharacter char1 = characters.get(i);
            StoryCharacter char2 = characters.get(i + 1);
            
            // Determine relationship based on character types
            RelationshipType relationship = determineRelationship(char1, char2);
            char1.addRelationship(char2.getCharacterId(), relationship);
            char2.addRelationship(char1.getCharacterId(), relationship);
            
            // Characters might learn from each other
            if (Math.random() < 0.5) {
                String sharedSkill = "Teamwork with " + char2.getCharacterId();
                char1.learnSkill(sharedSkill);
            }
        }
    }
    
    private RelationshipType determineRelationship(StoryCharacter char1, StoryCharacter char2) {
        // Heroes and Villains are rivals
        if ((char1 instanceof Hero && char2 instanceof Villain) || 
            (char1 instanceof Villain && char2 instanceof Hero)) {
            return RelationshipType.RIVAL;
        }
        // Comic Relief characters are friends with everyone
        else if (char1 instanceof ComicRelief || char2 instanceof ComicRelief) {
            return RelationshipType.FRIEND;
        }
        // Mysterious Strangers are allies
        else if (char1 instanceof MysteriousStranger || char2 instanceof MysteriousStranger) {
            return RelationshipType.ALLY;
        }
        else {
            return RelationshipType.ACQUAINTANCE;
        }
    }
    
    private void activateMetaMode() {
        metaMode = true;
        System.out.println("\n🎭💫 META-STORY MODE ACTIVATED! 💫🎭");
        System.out.println("The fourth wall begins to crack...");
        
        // All characters become aware
        for (StoryCharacter character : characters) {
            if (!character.isAware()) {
                character.becomeAware();
            }
        }
        
        System.out.println("\n📝 Characters begin to question the story itself:");
        metaStoryDialogue();
    }
    
    private void metaStoryDialogue() {
        System.out.println("💭 All characters suddenly realize they're in a story!");
        
        for (StoryCharacter character : characters) {
            if (character instanceof Hero) {
                System.out.println("🦸 " + character.getCharacterId() + 
                                 ": 'Wait, why do I always have to save everyone? Can't I retire?'");
            } else if (character instanceof Villain) {
                System.out.println("🦹 " + character.getCharacterId() + 
                                 ": 'I'm tired of being evil! But my final motivation won't let me change!'");
            } else if (character instanceof MysteriousStranger) {
                System.out.println("🔮 " + character.getCharacterId() + 
                                 ": 'I know everything about this story... except how to escape it!'");
            } else if (character instanceof ComicRelief) {
                System.out.println("🎭 " + character.getCharacterId() + 
                                 ": 'At least being comic relief is fun! Though I wish I could try drama sometimes!'");
            }
        }
        
        System.out.println("\n🎪 The characters attempt to rewrite their own story...");
        System.out.println("💥 But their final attributes prevent any major changes!");
        System.out.println("😅 They resign themselves to playing their roles... with more self-awareness now.");
    }
    
    private void concludeStory() {
        System.out.println("\n" + "🌟".repeat(25));
        System.out.println("📖 THE END OF " + storyTitle.toUpperCase() + " 📖");
        System.out.println("🌟".repeat(25));
        
        System.out.println("\n📊 Story Statistics:");
        System.out.println("📚 Total Chapters: " + (chapterNumber - 1));
        System.out.println("👥 Characters: " + characters.size());
        System.out.println("🎭 Meta-mode activated: " + (metaMode ? "Yes" : "No"));
        
        System.out.println("\n🎭 Final Character States:");
        for (StoryCharacter character : characters) {
            System.out.println("  " + character.getCharacterId() + ":");
            System.out.println("    📍 Location: " + character.getCurrentLocation());
            System.out.println("    😊 Mood: " + character.getCurrentMood());
            System.out.println("    🛠️ Skills: " + character.getSkillSet().size() + " total");
            System.out.println("    🧠 Self-aware: " + (character.isAware() ? "Yes" : "No"));
            System.out.println("    📜 Origin: " + character.getCreationOrigin());
        }
        
        // Final meta-story moment
        if (metaMode) {
            System.out.println("\n🎪 Final Meta-Story Moment:");
            System.out.println("🎭 All characters gather for a final bow...");
            System.out.println("💬 They thank the StoryEngine for the adventure, despite their constructor limitations!");
        }
        
        // Offer to save story state
        System.out.println("\n💾 Story state preserved for potential sequels!");
        System.out.println("🔄 Characters retain their final attributes but can continue evolving dynamically.");
    }
    
    // Method to save/load story (preserving final attributes)
    public Map<String, Object> saveStoryState() {
        Map<String, Object> saveData = new HashMap<>();
        saveData.put("title", storyTitle);
        saveData.put("genre", genre);
        saveData.put("chapter", chapterNumber);
        saveData.put("metaMode", metaMode);
        
        // Save character data (final attributes are preserved, dynamic ones can be restored)
        List<Map<String, Object>> characterData = new ArrayList<>();
        for (StoryCharacter character : characters) {
            Map<String, Object> charData = new HashMap<>();
            charData.put("id", character.getCharacterId());
            charData.put("type", character.getClass().getSimpleName());
            charData.put("backstory", character.getBackstory()); // Final attribute
            charData.put("personality", character.getCorePersonality()); // Final attribute
            charData.put("location", character.getCurrentLocation()); // Dynamic attribute
            charData.put("mood", character.getCurrentMood()); // Dynamic attribute
            charData.put("skills", new ArrayList<>(character.getSkillSet())); // Dynamic attribute
            charData.put("aware", character.isAware()); // Dynamic attribute
            characterData.add(charData);
        }
        saveData.put("characters", characterData);
        
        return saveData;
    }
    
    // Getters for story information
    public String getStoryTitle() { return storyTitle; }
    public String getGenre() { return genre; }
    public List<StoryCharacter> getCharacters() { return new ArrayList<>(characters); }
    public int getChapterNumber() { return chapterNumber; }
    public boolean isMetaMode() { return metaMode; }
}

// Enum definitions for character attributes
enum PersonalityType {
    NOBLE("Noble and heroic"),
    EVIL("Evil and scheming"),
    MYSTERIOUS("Mysterious and enigmatic"),
    CHEERFUL("Cheerful and funny"),
    BROODING("Dark and brooding"),
    MISUNDERSTOOD("Misunderstood but good-hearted");
    
    private final String description;
    
    PersonalityType(String description) {
        this.description = description;
    }
    
    public String getDescription() { return description; }
}

enum RelationshipType {
    FRIEND, RIVAL, ALLY, ENEMY, ACQUAINTANCE, MENTOR, LOVE_INTEREST
}

enum EvilnessLevel {
    PETTY("Petty annoyance"),
    MISGUIDED("Misguided but redeemable"),
    CORRUPT("Truly corrupt"),
    PURE_EVIL("Irredeemably evil");
    
    private final String description;
    
    EvilnessLevel(String description) {
        this.description = description;
    }
    
    public String getDescription() { return description; }
}

enum HumorStyle {
    WITTY("Quick wit and clever jokes"),
    SLAPSTICK("Physical comedy and pratfalls"),
    SARCASTIC("Sarcasm and dry humor"),
    WHOLESOME("Clean, family-friendly humor"),
    DARK("Dark and morbid humor");
    
    private final String description;
    
    HumorStyle(String description) {
        this.description = description;
    }
    
    public String getDescription() { return description; }
}

// Main demonstration class
public class InteractiveStoryDemo {
    public static void main(String[] args) {
        System.out.println("🎭✨ INTERACTIVE STORY GENERATOR DEMO ✨🎭");
        System.out.println("Showcasing all Java constructor concepts with dynamic storytelling!\n");
        
        // Create a new story
        StoryEngine story = new StoryEngine("The Constructor Chronicles", "Fantasy");
        
        System.out.println("\n🎪 Demonstrating different character creation methods:\n");
        
        // 1. Basic constructor chaining - Hero with different origins
        story.addCharacter(new Hero("Sir Constructor", "chosen one"));
        story.addCharacter(new Hero("Lady Override", "the death of her village", true)); // reluctant hero
        
        // 2. Villain with permanent evil motivation
        story.addCharacter(new Villain("Lord Final", "desire for immutable power", EvilnessLevel.CORRUPT));
        story.addCharacter(new Villain("The Redeemable", "misguided attempt to help", true));
        
        // 3. Mysterious character with hidden final attributes
        story.addCharacter(new MysteriousStranger("The Enigma", "Ancient Code Architect", "Protect the Final Keywords"));
        
        // 4. Comic relief with final humor style
        story.addCharacter(new ComicRelief("Jest Exception", HumorStyle.WITTY));
        story.addCharacter(new ComicRelief("Slip Stack", true)); // accidental comedy
        
        // 5. Character created from story prompt
        story.addCharacterFromPrompt("A hero emerges from the coding academy to fight the bugs of chaos");
        
        // 6. Random character generation
        story.addRandomCharacter();
        
        // 7. Character fusion demonstration
        System.out.println("\n⚡ Attempting character fusion...");
        story.fuseCharacters(0, 1); // Fuse the first two characters
        
        System.out.println("\n🎬 Beginning the story adventure...\n");
        
        // Run the story for several chapters
        for (int i = 0; i < 6; i++) {
            story.advanceStory();
            
            // Add some dramatic pauses
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        System.out.println("\n🎯 Demonstrating static story methods:");
        System.out.println("📚 Universal plot twist: " + StoryEngine.generateUniversalPlotTwist());
        
        System.out.println("\n💾 Saving story state...");
        Map<String, Object> savedState = story.saveStoryState();
        System.out.println("✅ Story saved! Final attributes preserved, dynamic attributes captured.");
        
        System.out.println("\n🎉 Demo completed! This showcase demonstrated:");
        System.out.println("✨ Constructor chaining with different parameter combinations");
        System.out.println("✨ Final attributes that cannot be changed after object creation");
        System.out.println("✨ Dynamic attributes that evolve throughout the story");
        System.out.println("✨ Static methods for universal story rules");
        System.out.println("✨ Abstract base classes with concrete implementations");
        System.out.println("✨ Meta-programming humor with characters aware of their limitations");
        System.out.println("✨ Complex object relationships and interactions");
        System.out.println("✨ State preservation while respecting final attribute immutability");
        
        System.out.println("\n🎭 The characters may be limited by their final attributes,");
        System.out.println("   but their stories are dynamically infinite! ✨");
    }
}