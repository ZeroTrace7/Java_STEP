public class GameController {
    private String controllerBrand;
    private String connectionType;
    private boolean hasVibration;
    private int batteryLevel;
    private double sensitivity;
    
    public GameController() {
        this.controllerBrand = "GenericPad";
        this.connectionType = "USB";
        this.hasVibration = true;
        this.batteryLevel = 100;
        this.sensitivity = 1.0;
    }
    
    public GameController(String controllerBrand, String connectionType,
                         boolean hasVibration, int batteryLevel,
                         double sensitivity) {
        this.controllerBrand = controllerBrand;
        this.connectionType = connectionType;
        this.hasVibration = hasVibration;
        
        if (batteryLevel < 0) {
            this.batteryLevel = 0;
        } else if (batteryLevel > 100) {
            this.batteryLevel = 100;
        } else {
            this.batteryLevel = batteryLevel;
        }
        
        if (sensitivity < 0.1) {
            this.sensitivity = 0.1;
        } else if (sensitivity > 3.0) {
            this.sensitivity = 3.0;
        } else {
            this.sensitivity = sensitivity;
        }
    }
    
    public void displayControllerInfo() {
        System.out.println("CONTROLLER CONFIGURATION");
        System.out.println("Brand: " + controllerBrand);
        System.out.println("Connection: " + connectionType);
        System.out.println("Vibration: " + (hasVibration ? "Enabled" : "Disabled"));
        System.out.println("Battery: " + batteryLevel + "%");
        System.out.println("Sensitivity: " + sensitivity + "x");
        System.out.println();
    }
    
    public void updateBattery(int newLevel) {
        if (newLevel >= 0 && newLevel <= 100) {
            this.batteryLevel = newLevel;
            System.out.println("Battery updated to " + newLevel + "%");
        } else {
            System.out.println("Invalid battery level. Must be between 0-100");
        }
    }
    
    public void changeSensitivity(double newSensitivity) {
        if (newSensitivity >= 0.1 && newSensitivity <= 3.0) {
            this.sensitivity = newSensitivity;
            System.out.println("Sensitivity updated to " + newSensitivity + "x");
        } else {
            System.out.println("Invalid sensitivity. Must be between 0.1-3.0");
        }
    }
    
    public void toggleVibration() {
        this.hasVibration = !this.hasVibration;
        System.out.println("Vibration " + (hasVibration ? "enabled" : "disabled"));
    }
    
    public String getControllerBrand() {
        return controllerBrand;
    }
    
    public String getConnectionType() {
        return connectionType;
    }
    
    public boolean hasVibration() {
        return hasVibration;
    }
    
    public int getBatteryLevel() {
        return batteryLevel;
    }
    
    public double getSensitivity() {
        return sensitivity;
    }
    
    public static void main(String[] args) {
        System.out.println("GAMING CONTROLLER CONFIGURATION SYSTEM");
        System.out.println();
        
        System.out.println("1. Default Controller Configuration:");
        GameController defaultController = new GameController();
        defaultController.displayControllerInfo();
        
        System.out.println("2. Custom Controller Configuration:");
        GameController customController = new GameController("Xbox Elite", "Wireless", 
                                                            true, 85, 1.5);
        customController.displayControllerInfo();
        
        System.out.println("3. Controller with Validation Testing:");
        GameController testController = new GameController("PlayStation Pro", "Bluetooth", 
                                                          false, 150, 5.0);
        testController.displayControllerInfo();
        
        System.out.println("4. Multiple Gaming Controllers:");
        GameController[] controllers = {
            new GameController("Nintendo Pro", "USB-C", true, 70, 0.8),
            new GameController("Razer Wolverine", "Wired", true, 100, 2.2),
            new GameController("SteelSeries", "Wireless", false, 45, 1.8),
            new GameController()
        };
        
        for (int i = 0; i < controllers.length; i++) {
            System.out.println("Controller " + (i + 1) + ":");
            controllers[i].displayControllerInfo();
        }
        
        System.out.println("5. Controller Configuration Updates:");
        System.out.println("Updating default controller settings:");
        defaultController.updateBattery(75);
        defaultController.changeSensitivity(2.0);
        defaultController.toggleVibration();
        System.out.println();
        
        System.out.println("Updated default controller:");
        defaultController.displayControllerInfo();
        
        System.out.println("6. Invalid Configuration Testing:");
        System.out.println("Testing invalid updates:");
        customController.updateBattery(120);
        customController.changeSensitivity(4.0);
        customController.updateBattery(-10);
        customController.changeSensitivity(0.05);
        System.out.println();
        
        System.out.println("7. Gaming Session Simulation:");
        System.out.println("Simulating gaming session with battery drain:");
        for (int session = 1; session <= 3; session++) {
            System.out.println("Gaming Session " + session + ":");
            int currentBattery = customController.getBatteryLevel();
            int newBattery = Math.max(0, currentBattery - 15);
            customController.updateBattery(newBattery);
            
            if (newBattery <= 20) {
                System.out.println("Low battery warning!");
            }
        }
        
        System.out.println();
        System.out.println("Final controller state:");
        customController.displayControllerInfo();
        
        System.out.println("CONSTRUCTOR DEMONSTRATION COMPLETE:");
        System.out.println("Default Constructor: GameController()");
        System.out.println("Parameterized Constructor: GameController(brand, connection, vibration, battery, sensitivity)");
        System.out.println("Validation applied for battery level (0-100) and sensitivity (0.1-3.0)");
    }
}