import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.HashMap;
import java.util.Map;

public class SmartDevice {
    
    // Read-only properties
    private final String deviceId;
    private final LocalDateTime manufacturingDate;
    private final String serialNumber;
    
    // Write-only properties (stored as hashes)
    private int hashedEncryptionKey;
    private int hashedAdminPassword;
    
    // Read-write properties
    private String deviceName;
    private boolean isEnabled;
    
    // Internal state
    private final LocalDateTime startupTime;
    
    public SmartDevice(String deviceName) {
        this.deviceId = "DEV-" + UUID.randomUUID().toString().substring(0, 8);
        this.manufacturingDate = LocalDateTime.now().minusMonths(6);
        this.serialNumber = "SN" + System.currentTimeMillis();
        this.startupTime = LocalDateTime.now();
        this.deviceName = deviceName;
        this.isEnabled = true;
        this.hashedEncryptionKey = 0;
        this.hashedAdminPassword = 0;
    }
    
    // Read-Only Property Methods
    public String getDeviceId() {
        return deviceId;
    }
    
    public LocalDateTime getManufacturingDate() {
        return manufacturingDate;
    }
    
    public String getSerialNumber() {
        return serialNumber;
    }
    
    public long getUptime() {
        return ChronoUnit.SECONDS.between(startupTime, LocalDateTime.now());
    }
    
    public int getDeviceAge() {
        return (int) ChronoUnit.DAYS.between(manufacturingDate, LocalDateTime.now());
    }
    
    // Write-Only Property Methods
    public void setEncryptionKey(String key) {
        if (key == null || key.length() < 8) {
            throw new IllegalArgumentException("Encryption key must be at least 8 characters");
        }
        if (!key.matches(".*[A-Z].*") || !key.matches(".*[0-9].*")) {
            throw new IllegalArgumentException("Encryption key must contain uppercase letters and numbers");
        }
        this.hashedEncryptionKey = key.hashCode();
        System.out.println("Encryption key set successfully");
    }
    
    public void setAdminPassword(String password) {
        if (password == null || password.length() < 10) {
            throw new IllegalArgumentException("Admin password must be at least 10 characters");
        }
        if (!password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*") || 
            !password.matches(".*[0-9].*") || !password.matches(".*[!@#$%^&*].*")) {
            throw new IllegalArgumentException("Password must contain uppercase, lowercase, numbers, and special characters");
        }
        this.hashedAdminPassword = password.hashCode();
        System.out.println("Admin password set successfully");
    }
    
    public boolean validateEncryptionKey(String key) {
        return key != null && key.hashCode() == this.hashedEncryptionKey;
    }
    
    public boolean validateAdminPassword(String password) {
        return password != null && password.hashCode() == this.hashedAdminPassword;
    }
    
    // Read-Write Property Methods
    public String getDeviceName() {
        return deviceName;
    }
    
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
    
    public boolean isEnabled() {
        return isEnabled;
    }
    
    public void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
    }
    
    // Utility Methods
    public Map<String, String> getPropertyInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("deviceId", "Read-Only");
        info.put("manufacturingDate", "Read-Only");
        info.put("serialNumber", "Read-Only");
        info.put("uptime", "Computed Read-Only");
        info.put("deviceAge", "Computed Read-Only");
        info.put("encryptionKey", "Write-Only");
        info.put("adminPassword", "Write-Only");
        info.put("deviceName", "Read-Write");
        info.put("isEnabled", "Read-Write");
        return info;
    }
    
    public void resetDevice() {
        this.hashedEncryptionKey = 0;
        this.hashedAdminPassword = 0;
        this.isEnabled = true;
        System.out.println("Device reset completed. Write-only properties cleared, read-only properties preserved.");
    }
    
    @Override
    public String toString() {
        return "SmartDevice{" +
                "deviceId='" + deviceId + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", manufacturingDate=" + manufacturingDate +
                ", isEnabled=" + isEnabled +
                ", uptime=" + getUptime() + " seconds" +
                ", deviceAge=" + getDeviceAge() + " days" +
                '}';
    }
    
    public static void main(String[] args) {
        System.out.println("=== Creating Smart Device ===");
        SmartDevice device1 = new SmartDevice("Living Room Thermostat");
        
        System.out.println("Device created: " + device1);
        
        System.out.println("\n=== Demonstrating Read-Only Properties ===");
        System.out.println("Device ID: " + device1.getDeviceId());
        System.out.println("Manufacturing Date: " + device1.getManufacturingDate());
        System.out.println("Serial Number: " + device1.getSerialNumber());
        System.out.println("Uptime: " + device1.getUptime() + " seconds");
        System.out.println("Device Age: " + device1.getDeviceAge() + " days");
        
        try {
            Thread.sleep(2000);
            System.out.println("Uptime after 2 seconds: " + device1.getUptime() + " seconds");
        } catch (InterruptedException e) {
            // Ignore
        }
        
        System.out.println("\n=== Demonstrating Write-Only Properties ===");
        try {
            device1.setEncryptionKey("SecureKey123");
            device1.setAdminPassword("AdminPass123!");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation error: " + e.getMessage());
        }
        
        System.out.println("Validating encryption key 'SecureKey123': " + 
                          device1.validateEncryptionKey("SecureKey123"));
        System.out.println("Validating wrong key 'WrongKey': " + 
                          device1.validateEncryptionKey("WrongKey"));
        System.out.println("Validating admin password 'AdminPass123!': " + 
                          device1.validateAdminPassword("AdminPass123!"));
        
        System.out.println("\n=== Testing Write-Only Property Validation ===");
        try {
            device1.setEncryptionKey("weak");
        } catch (IllegalArgumentException e) {
            System.out.println("Weak encryption key rejected: " + e.getMessage());
        }
        
        try {
            device1.setAdminPassword("simple");
        } catch (IllegalArgumentException e) {
            System.out.println("Simple password rejected: " + e.getMessage());
        }
        
        System.out.println("\n=== Demonstrating Read-Write Properties ===");
        System.out.println("Current device name: " + device1.getDeviceName());
        device1.setDeviceName("Updated Thermostat");
        System.out.println("Updated device name: " + device1.getDeviceName());
        
        System.out.println("Device enabled: " + device1.isEnabled());
        device1.setEnabled(false);
        System.out.println("Device enabled after change: " + device1.isEnabled());
        
        System.out.println("\n=== Property Access Information ===");
        Map<String, String> propertyInfo = device1.getPropertyInfo();
        for (Map.Entry<String, String> entry : propertyInfo.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        System.out.println("\n=== Creating Multiple Devices ===");
        SmartDevice device2 = new SmartDevice("Bedroom Light");
        SmartDevice device3 = new SmartDevice("Kitchen Speaker");
        
        System.out.println("Device 1 ID: " + device1.getDeviceId());
        System.out.println("Device 2 ID: " + device2.getDeviceId());
        System.out.println("Device 3 ID: " + device3.getDeviceId());
        
        device2.setEncryptionKey("Device2Key99");
        device3.setEncryptionKey("Device3Secure88");
        
        System.out.println("Device 1 validates Device2's key: " + 
                          device1.validateEncryptionKey("Device2Key99"));
        System.out.println("Device 2 validates its own key: " + 
                          device2.validateEncryptionKey("Device2Key99"));
        
        System.out.println("\n=== Testing Device Reset ===");
        device1.resetDevice();
        System.out.println("After reset - encryption key validation: " + 
                          device1.validateEncryptionKey("SecureKey123"));
        System.out.println("After reset - device ID preserved: " + device1.getDeviceId());
        System.out.println("After reset - device enabled: " + device1.isEnabled());
    }
}