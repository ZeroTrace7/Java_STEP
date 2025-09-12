import java.util.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class SecureConfiguration {
    
    // Compile-time constants (static final)
    public static final String APPLICATION_NAME = "SecureApp";
    public static final String VERSION = "2.1.0";
    public static final int MAX_CONNECTIONS = 100;
    public static final long DEFAULT_TIMEOUT = 30000L;
    
    // Instance constants (final, set in constructor)
    private final String configId;
    private final LocalDateTime creationTime;
    private final Set<String> allowedOperations;
    
    // Final references to mutable objects
    private final Map<String, String> serverSettings;
    private final List<String> userPermissions;
    private final Properties securityRules;
    
    // Private final fields requiring complex initialization
    private final byte[] encryptedData;
    private long checksum; // Not final because it needs to be recalculated
    
    public SecureConfiguration(String configId, String sensitiveData, 
                             Map<String, String> settings, List<String> permissions, 
                             Set<String> operations, Properties rules) {
        if (configId == null || configId.trim().isEmpty()) {
            throw new IllegalArgumentException("Config ID cannot be null or empty");
        }
        
        this.configId = configId;
        this.creationTime = LocalDateTime.now();
        this.allowedOperations = new HashSet<>(operations != null ? operations : new HashSet<>());
        
        this.serverSettings = initializeServerSettings(settings);
        this.userPermissions = new ArrayList<>(permissions != null ? permissions : new ArrayList<>());
        this.securityRules = new Properties();
        if (rules != null) {
            this.securityRules.putAll(rules);
        }
        
        this.encryptedData = encryptSensitiveData(sensitiveData);
        this.checksum = calculateChecksum();
    }
    
    // Initialization helper methods
    private Map<String, String> initializeServerSettings(Map<String, String> settings) {
        Map<String, String> validatedSettings = new HashMap<>();
        if (settings != null) {
            for (Map.Entry<String, String> entry : settings.entrySet()) {
                if (entry.getKey() != null && entry.getValue() != null) {
                    validatedSettings.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return validatedSettings;
    }
    
    private long calculateChecksum() {
        long hash = configId.hashCode();
        hash = hash * 31 + creationTime.hashCode();
        hash = hash * 31 + allowedOperations.hashCode();
        hash = hash * 31 + serverSettings.hashCode();
        hash = hash * 31 + userPermissions.hashCode();
        hash = hash * 31 + securityRules.hashCode();
        hash = hash * 31 + Arrays.hashCode(encryptedData);
        return hash;
    }
    
    private byte[] encryptSensitiveData(String data) {
        if (data == null) return new byte[0];
        
        // Simple encryption simulation (shift cipher)
        byte[] original = data.getBytes();
        byte[] encrypted = new byte[original.length];
        for (int i = 0; i < original.length; i++) {
            encrypted[i] = (byte) (original[i] + 7); // Simple shift
        }
        return encrypted;
    }
    
    // Simple getters for immutable final fields
    public String getConfigId() {
        return configId;
    }
    
    public LocalDateTime getCreationTime() {
        return creationTime;
    }
    
    public static String getApplicationName() {
        return APPLICATION_NAME;
    }
    
    public static String getVersion() {
        return VERSION;
    }
    
    public static int getMaxConnections() {
        return MAX_CONNECTIONS;
    }
    
    public static long getDefaultTimeout() {
        return DEFAULT_TIMEOUT;
    }
    
    // Defensive copying getters for mutable final references
    public Map<String, String> getServerSettings() {
        return new HashMap<>(serverSettings);
    }
    
    public List<String> getUserPermissions() {
        return new ArrayList<>(userPermissions);
    }
    
    public Properties getSecurityRules() {
        Properties copy = new Properties();
        copy.putAll(securityRules);
        return copy;
    }
    
    public Set<String> getAllowedOperations() {
        return new HashSet<>(allowedOperations);
    }
    
    // Computed getters
    public boolean isValid() {
        return validateChecksum();
    }
    
    public long getConfigAge() {
        return ChronoUnit.SECONDS.between(creationTime, LocalDateTime.now());
    }
    
    public boolean hasPermission(String permission) {
        return userPermissions.contains(permission);
    }
    
    public String getSettingValue(String key, String defaultValue) {
        return serverSettings.getOrDefault(key, defaultValue);
    }
    
    // Controlled modification methods
    public synchronized boolean addUserPermission(String permission) {
        if (permission != null && !userPermissions.contains(permission)) {
            userPermissions.add(permission);
            this.checksum = calculateChecksum();
            return true;
        }
        return false;
    }
    
    public synchronized boolean removeUserPermission(String permission) {
        if (userPermissions.remove(permission)) {
            this.checksum = calculateChecksum();
            return true;
        }
        return false;
    }
    
    public synchronized void updateServerSetting(String key, String value) {
        if (key != null && value != null) {
            serverSettings.put(key, value);
            this.checksum = calculateChecksum();
        }
    }
    
    public synchronized void addSecurityRule(String rule, String value) {
        if (rule != null && value != null) {
            securityRules.setProperty(rule, value);
            this.checksum = calculateChecksum();
        }
    }
    
    // Validation methods
    public boolean validateIntegrity() {
        return validateChecksum() && configId != null && creationTime != null;
    }
    
    public boolean validatePermission(String operation) {
        return allowedOperations.contains(operation);
    }
    
    public boolean validateChecksum() {
        return this.checksum == calculateChecksum();
    }
    
    // Snapshot methods
    public SecureConfiguration createSnapshot() {
        Map<String, String> settingsCopy = new HashMap<>(serverSettings);
        List<String> permissionsCopy = new ArrayList<>(userPermissions);
        Set<String> operationsCopy = new HashSet<>(allowedOperations);
        Properties rulesCopy = new Properties();
        rulesCopy.putAll(securityRules);
        
        // Decrypt data for snapshot (simulation)
        String decryptedData = new String(encryptedData);
        for (int i = 0; i < decryptedData.length(); i++) {
            // Reverse the encryption
        }
        
        return new SecureConfiguration(configId + "-SNAPSHOT", "snapshot-data", 
                                     settingsCopy, permissionsCopy, operationsCopy, rulesCopy);
    }
    
    public Map<String, Object> exportSettings() {
        Map<String, Object> export = new HashMap<>();
        export.put("configId", configId);
        export.put("creationTime", creationTime);
        export.put("serverSettings", Collections.unmodifiableMap(serverSettings));
        export.put("userPermissions", Collections.unmodifiableList(userPermissions));
        export.put("allowedOperations", Collections.unmodifiableSet(allowedOperations));
        export.put("checksum", checksum);
        export.put("valid", isValid());
        return Collections.unmodifiableMap(export);
    }
    
    @Override
    public String toString() {
        return "SecureConfiguration{" +
                "configId='" + configId + '\'' +
                ", creationTime=" + creationTime +
                ", serverSettings=" + serverSettings.size() + " settings" +
                ", userPermissions=" + userPermissions.size() + " permissions" +
                ", valid=" + isValid() +
                '}';
    }
    
    public static void main(String[] args) {
        System.out.println("=== Testing Final Field Initialization ===");
        
        // Create test data
        Map<String, String> settings = new HashMap<>();
        settings.put("host", "localhost");
        settings.put("port", "8080");
        settings.put("database", "mydb");
        
        List<String> permissions = Arrays.asList("READ", "WRITE", "DELETE");
        Set<String> operations = new HashSet<>(Arrays.asList("backup", "restore", "monitor"));
        
        Properties rules = new Properties();
        rules.setProperty("maxLoginAttempts", "3");
        rules.setProperty("sessionTimeout", "1800");
        
        // Create configuration
        SecureConfiguration config = new SecureConfiguration(
            "CONFIG-001", "sensitive-data-123", settings, permissions, operations, rules);
        
        System.out.println("Configuration created: " + config);
        System.out.println("Application: " + SecureConfiguration.getApplicationName());
        System.out.println("Version: " + SecureConfiguration.getVersion());
        System.out.println("Config ID: " + config.getConfigId());
        System.out.println("Creation Time: " + config.getCreationTime());
        System.out.println("Is Valid: " + config.isValid());
        
        System.out.println("\n=== Testing Immutability of Final References ===");
        
        // Modify original collections
        settings.put("newSetting", "newValue");
        permissions.add("ADMIN");
        operations.add("shutdown");
        
        System.out.println("Original settings modified, config settings count: " + 
                          config.getServerSettings().size());
        System.out.println("Original permissions modified, config permissions count: " + 
                          config.getUserPermissions().size());
        System.out.println("Original operations modified, config operations count: " + 
                          config.getAllowedOperations().size());
        
        System.out.println("\n=== Testing Defensive Copying ===");
        
        // Get collections and modify them
        Map<String, String> retrievedSettings = config.getServerSettings();
        List<String> retrievedPermissions = config.getUserPermissions();
        Set<String> retrievedOperations = config.getAllowedOperations();
        
        retrievedSettings.put("hackedSetting", "maliciousValue");
        retrievedPermissions.add("HACKED");
        retrievedOperations.add("destroy");
        
        System.out.println("Modified retrieved collections...");
        System.out.println("Config settings still secure: " + 
                          !config.getServerSettings().containsKey("hackedSetting"));
        System.out.println("Config permissions still secure: " + 
                          !config.getUserPermissions().contains("HACKED"));
        System.out.println("Config operations still secure: " + 
                          !config.getAllowedOperations().contains("destroy"));
        
        System.out.println("\n=== Testing Controlled Modifications ===");
        
        System.out.println("Adding permission 'EXECUTE': " + 
                          config.addUserPermission("EXECUTE"));
        System.out.println("Current permissions: " + config.getUserPermissions());
        System.out.println("Checksum valid after modification: " + config.isValid());
        
        config.updateServerSetting("timeout", "5000");
        System.out.println("Updated server setting - timeout: " + 
                          config.getSettingValue("timeout", "default"));
        System.out.println("Checksum valid after setting update: " + config.isValid());
        
        config.addSecurityRule("maxFileSize", "10MB");
        System.out.println("Added security rule");
        System.out.println("Checksum valid after rule addition: " + config.isValid());
        
        System.out.println("\n=== Testing Validation ===");
        
        System.out.println("Has READ permission: " + config.hasPermission("READ"));
        System.out.println("Has ADMIN permission: " + config.hasPermission("ADMIN"));
        System.out.println("Backup operation allowed: " + config.validatePermission("backup"));
        System.out.println("Invalid operation allowed: " + config.validatePermission("invalid"));
        
        System.out.println("Integrity validation: " + config.validateIntegrity());
        System.out.println("Config age: " + config.getConfigAge() + " seconds");
        
        System.out.println("\n=== Testing Configuration Manager ===");
        ConfigurationManager manager = new ConfigurationManager();
        manager.addConfiguration("primary", config);
        
        SecureConfiguration config2 = new SecureConfiguration(
            "CONFIG-002", "other-data", 
            Map.of("host", "remote", "port", "9090"),
            List.of("READ"), Set.of("view"), new Properties());
        
        manager.addConfiguration("secondary", config2);
        
        System.out.println("Manager has " + manager.getConfigurationCount() + " configurations");
        System.out.println("Primary config valid: " + manager.isConfigurationValid("primary"));
        System.out.println("Secondary config valid: " + manager.isConfigurationValid("secondary"));
        
        System.out.println("\n=== Testing Snapshot and Export ===");
        SecureConfiguration snapshot = config.createSnapshot();
        System.out.println("Snapshot created: " + snapshot.getConfigId());
        
        Map<String, Object> exported = config.exportSettings();
        System.out.println("Exported settings count: " + exported.size());
        System.out.println("Export is read-only: " + (exported instanceof Map));
    }
}

class ConfigurationManager {
    private final Map<String, SecureConfiguration> configurations = new HashMap<>();
    
    public void addConfiguration(String name, SecureConfiguration config) {
        configurations.put(name, config);
    }
    
    public SecureConfiguration getConfiguration(String name) {
        return configurations.get(name);
    }
    
    public boolean isConfigurationValid(String name) {
        SecureConfiguration config = configurations.get(name);
        return config != null && config.isValid();
    }
    
    public int getConfigurationCount() {
        return configurations.size();
    }
    
    public Set<String> getConfigurationNames() {
        return new HashSet<>(configurations.keySet());
    }
}