class SmartDevice {
    protected String deviceId;
    protected String location;
    protected boolean isOnline;
    
    public SmartDevice(String deviceId, String location) {
        this.deviceId = deviceId;
        this.location = location;
        this.isOnline = true;
    }
    
    public void showStatus() {
        String status = isOnline ? "Online" : "Offline";
        System.out.println("Device: " + deviceId + " | Location: " + location + " | Status: " + status);
    }
    
    public void powerOff() {
        isOnline = false;
        System.out.println(deviceId + " powered off");
    }
    
    public String getDeviceId() {
        return deviceId;
    }
    
    public String getLocation() {
        return location;
    }
}

class SmartClassroom extends SmartDevice {
    private boolean lightsOn;
    private int temperature;
    private boolean projectorOn;
    
    public SmartClassroom(String deviceId, String location) {
        super(deviceId, location);
        this.lightsOn = false;
        this.temperature = 22;
        this.projectorOn = false;
    }
    
    public void controlLights(boolean turnOn) {
        lightsOn = turnOn;
        String action = turnOn ? "turned on" : "turned off";
        System.out.println("💡 Lights " + action + " in " + location);
    }
    
    public void setTemperature(int temp) {
        temperature = temp;
        System.out.println("🌡️ AC set to " + temp + "°C in " + location);
    }
    
    public void controlProjector(boolean turnOn) {
        projectorOn = turnOn;
        String action = turnOn ? "turned on" : "turned off";
        System.out.println("📽️ Projector " + action + " in " + location);
    }
    
    public void showStatus() {
        super.showStatus();
        System.out.println("  Lights: " + (lightsOn ? "On" : "Off") + 
                          " | Temperature: " + temperature + "°C" + 
                          " | Projector: " + (projectorOn ? "On" : "Off"));
    }
}

class SmartLab extends SmartDevice {
    private boolean equipmentActive;
    private boolean safetySystemOn;
    private int gasLevel;
    
    public SmartLab(String deviceId, String location) {
        super(deviceId, location);
        this.equipmentActive = false;
        this.safetySystemOn = true;
        this.gasLevel = 0;
    }
    
    public void controlEquipment(boolean activate) {
        equipmentActive = activate;
        String action = activate ? "activated" : "deactivated";
        System.out.println("🔬 Lab equipment " + action + " in " + location);
    }
    
    public void checkSafety() {
        System.out.println("🚨 Safety check in " + location + ":");
        System.out.println("  Safety system: " + (safetySystemOn ? "Active" : "Inactive"));
        System.out.println("  Gas level: " + gasLevel + " ppm");
        
        if (gasLevel > 50) {
            System.out.println("  ⚠️ WARNING: High gas levels detected!");
        }
    }
    
    public void emergencyShutdown() {
        equipmentActive = false;
        gasLevel = 0;
        System.out.println("🆘 EMERGENCY SHUTDOWN activated in " + location);
    }
    
    public void showStatus() {
        super.showStatus();
        System.out.println("  Equipment: " + (equipmentActive ? "Active" : "Inactive") + 
                          " | Safety: " + (safetySystemOn ? "On" : "Off") + 
                          " | Gas Level: " + gasLevel + " ppm");
    }
}

class SmartLibrary extends SmartDevice {
    private int occupancy;
    private int maxCapacity;
    private int availableBooks;
    
    public SmartLibrary(String deviceId, String location, int maxCapacity) {
        super(deviceId, location);
        this.occupancy = 0;
        this.maxCapacity = maxCapacity;
        this.availableBooks = 1000;
    }
    
    public void updateOccupancy(int count) {
        occupancy = count;
        System.out.println("👥 Occupancy updated: " + occupancy + "/" + maxCapacity + " in " + location);
        
        if (occupancy >= maxCapacity) {
            System.out.println("  ⚠️ Library at full capacity!");
        }
    }
    
    public void trackBooks(int borrowed) {
        availableBooks = availableBooks - borrowed;
        System.out.println("📚 Book tracking: " + borrowed + " books borrowed from " + location);
        System.out.println("  Available books: " + availableBooks);
    }
    
    public void showStatus() {
        super.showStatus();
        System.out.println("  Occupancy: " + occupancy + "/" + maxCapacity + 
                          " | Available books: " + availableBooks);
    }
}

public class SmartCampusApp {
    
    public static void manageDevice(SmartDevice device) {
        System.out.println("Managing device: " + device.getDeviceId());
        
        if (device instanceof SmartClassroom) {
            SmartClassroom classroom = (SmartClassroom) device;
            classroom.controlLights(true);
            classroom.setTemperature(24);
            classroom.controlProjector(true);
        } 
        else if (device instanceof SmartLab) {
            SmartLab lab = (SmartLab) device;
            lab.controlEquipment(true);
            lab.checkSafety();
        } 
        else if (device instanceof SmartLibrary) {
            SmartLibrary library = (SmartLibrary) device;
            library.updateOccupancy(45);
            library.trackBooks(12);
        } 
        else {
            System.out.println("Unknown device type - using basic controls only");
        }
        
        System.out.println();
    }
    
    public static void emergencyProtocol(SmartDevice[] devices) {
        System.out.println("🚨 EMERGENCY PROTOCOL ACTIVATED");
        System.out.println();
        
        for (int i = 0; i < devices.length; i++) {
            SmartDevice device = devices[i];
            
            if (device instanceof SmartLab) {
                SmartLab lab = (SmartLab) device;
                lab.emergencyShutdown();
            } 
            else if (device instanceof SmartClassroom) {
                SmartClassroom classroom = (SmartClassroom) device;
                classroom.controlLights(false);
                classroom.controlProjector(false);
            }
            
            device.powerOff();
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        System.out.println("Smart Campus IoT Management System");
        System.out.println();
        
        SmartDevice[] campusDevices = new SmartDevice[5];
        
        campusDevices[0] = new SmartClassroom("CR-101", "Room 101");
        campusDevices[1] = new SmartLab("LAB-A", "Chemistry Lab A");
        campusDevices[2] = new SmartLibrary("LIB-MAIN", "Main Library", 200);
        campusDevices[3] = new SmartClassroom("CR-205", "Room 205");
        campusDevices[4] = new SmartLab("LAB-B", "Physics Lab B");
        
        System.out.println("Device Status Report:");
        for (int i = 0; i < campusDevices.length; i++) {
            campusDevices[i].showStatus();
        }
        System.out.println();
        
        System.out.println("Smart Device Management (Safe Downcasting):");
        for (int i = 0; i < campusDevices.length; i++) {
            manageDevice(campusDevices[i]);
        }
        
        System.out.println("Updated Device Status:");
        for (int i = 0; i < campusDevices.length; i++) {
            campusDevices[i].showStatus();
        }
        System.out.println();
        
        emergencyProtocol(campusDevices);
        
        System.out.println("Safe Downcasting with instanceof:");
        System.out.println("✅ Check type first with 'instanceof'");
        System.out.println("✅ Cast safely only after verification");
        System.out.println("✅ Access specific methods without crashes");
        System.out.println("✅ Handle unknown types gracefully");
    }
}