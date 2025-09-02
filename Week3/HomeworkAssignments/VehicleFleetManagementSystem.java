import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// Base Vehicle class
abstract class Vehicle {
    // Instance variables
    protected String vehicleId;
    protected String brand;
    protected String model;
    protected int year;
    protected double mileage;
    protected String fuelType;
    protected String currentStatus;
    protected LocalDate lastServiceDate;
    protected Driver assignedDriver;
    
    // Static variables
    protected static int totalVehicles = 0;
    protected static double fleetValue = 0.0;
    protected static String companyName = "TransFleet Solutions";
    protected static double totalFuelConsumption = 0.0;
    
    // Constructor
    public Vehicle(String vehicleId, String brand, String model, int year, 
                   double mileage, String fuelType) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.fuelType = fuelType;
        this.currentStatus = "Available";
        this.lastServiceDate = LocalDate.now().minusMonths(3); // Default last service 3 months ago
        totalVehicles++;
        fleetValue += getVehicleValue();
    }
    
    // Abstract methods to be implemented by subclasses
    public abstract double calculateRunningCost();
    public abstract double getVehicleValue();
    public abstract String getVehicleType();
    
    // Common methods
    public void assignDriver(Driver driver) {
        if (this.assignedDriver != null) {
            System.out.println("Vehicle " + vehicleId + " already has a driver assigned.");
            return;
        }
        
        if (driver.getAssignedVehicle() != null) {
            System.out.println("Driver " + driver.getDriverName() + " is already assigned to another vehicle.");
            return;
        }
        
        this.assignedDriver = driver;
        driver.assignVehicle(this);
        this.currentStatus = "Assigned";
        System.out.println("Driver " + driver.getDriverName() + " assigned to vehicle " + vehicleId);
    }
    
    public void scheduleMaintenance() {
        if (currentStatus.equals("In Maintenance")) {
            System.out.println("Vehicle " + vehicleId + " is already in maintenance.");
            return;
        }
        
        currentStatus = "In Maintenance";
        lastServiceDate = LocalDate.now();
        System.out.println("Maintenance scheduled for vehicle " + vehicleId + " on " + lastServiceDate);
    }
    
    public void updateMileage(double newMileage) {
        if (newMileage < this.mileage) {
            System.out.println("Error: New mileage cannot be less than current mileage.");
            return;
        }
        
        double mileageDiff = newMileage - this.mileage;
        this.mileage = newMileage;
        
        // Update fuel consumption based on mileage increase
        double fuelConsumed = mileageDiff / getFuelEfficiency();
        totalFuelConsumption += fuelConsumed;
        
        System.out.println("Mileage updated for vehicle " + vehicleId + ". New mileage: " + mileage);
        
        // Check if service is due after mileage update
        checkServiceDue();
    }
    
    public boolean checkServiceDue() {
        long daysSinceService = ChronoUnit.DAYS.between(lastServiceDate, LocalDate.now());
        boolean serviceDue = daysSinceService > 90 || (mileage % 10000) < 500; // Service every 90 days or 10,000 miles
        
        if (serviceDue) {
            System.out.println("⚠️  Service due for vehicle " + vehicleId + 
                             " (Days since service: " + daysSinceService + ")");
        }
        
        return serviceDue;
    }
    
    // Abstract method for fuel efficiency
    protected abstract double getFuelEfficiency(); // Miles per gallon
    
    // Getters and Setters
    public String getVehicleId() { return vehicleId; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public double getMileage() { return mileage; }
    public String getFuelType() { return fuelType; }
    public String getCurrentStatus() { return currentStatus; }
    public Driver getAssignedDriver() { return assignedDriver; }
    public LocalDate getLastServiceDate() { return lastServiceDate; }
    
    // Static methods
    public static int getTotalVehicles() { return totalVehicles; }
    public static double getFleetValue() { return fleetValue; }
    public static String getCompanyName() { return companyName; }
    public static double getTotalFuelConsumption() { return totalFuelConsumption; }
    
    public void setCurrentStatus(String status) {
        this.currentStatus = status;
    }
    
    @Override
    public String toString() {
        return String.format("%s [ID: %s, %s %s %d, Mileage: %.1f, Status: %s, Driver: %s]",
                getVehicleType(), vehicleId, brand, model, year, mileage, currentStatus,
                assignedDriver != null ? assignedDriver.getDriverName() : "Unassigned");
    }
}

// Car class
class Car extends Vehicle {
    private int seatingCapacity;
    private String bodyType;
    private boolean hasAirConditioning;
    
    public Car(String vehicleId, String brand, String model, int year, 
               double mileage, String fuelType, int seatingCapacity, String bodyType) {
        super(vehicleId, brand, model, year, mileage, fuelType);
        this.seatingCapacity = seatingCapacity;
        this.bodyType = bodyType;
        this.hasAirConditioning = true; // Default to true for cars
    }
    
    @Override
    public double calculateRunningCost() {
        double baseCost = 0.15; // Base cost per mile
        double fuelCost = getFuelCostPerMile();
        double maintenanceCost = 0.05; // Maintenance cost per mile
        return (baseCost + fuelCost + maintenanceCost) * mileage;
    }
    
    @Override
    public double getVehicleValue() {
        int currentYear = LocalDate.now().getYear();
        int age = currentYear - year;
        double baseValue = 25000; // Base value for a car
        double depreciation = Math.max(0, baseValue * (1 - 0.15 * age)); // 15% depreciation per year
        return depreciation;
    }
    
    @Override
    protected double getFuelEfficiency() {
        return fuelType.equals("Electric") ? 100 : 30; // 30 MPG for regular cars, 100 for electric
    }
    
    private double getFuelCostPerMile() {
        double fuelPrice = fuelType.equals("Electric") ? 0.12 : 3.50; // Per gallon or kWh
        return fuelPrice / getFuelEfficiency();
    }
    
    @Override
    public String getVehicleType() {
        return "Car";
    }
    
    // Getters for car-specific attributes
    public int getSeatingCapacity() { return seatingCapacity; }
    public String getBodyType() { return bodyType; }
    public boolean hasAirConditioning() { return hasAirConditioning; }
}

// Bus class
class Bus extends Vehicle {
    private int seatingCapacity;
    private boolean isDoubleDecker;
    private String routeType; // City, Intercity, School
    private boolean hasWheelchairAccess;
    
    public Bus(String vehicleId, String brand, String model, int year, 
               double mileage, String fuelType, int seatingCapacity, String routeType) {
        super(vehicleId, brand, model, year, mileage, fuelType);
        this.seatingCapacity = seatingCapacity;
        this.routeType = routeType;
        this.isDoubleDecker = seatingCapacity > 60;
        this.hasWheelchairAccess = true; // Modern buses typically have this
    }
    
    @Override
    public double calculateRunningCost() {
        double baseCost = 0.45; // Higher base cost for buses
        double fuelCost = getFuelCostPerMile();
        double maintenanceCost = 0.12; // Higher maintenance cost
        double driverCost = 0.08; // Driver cost per mile
        return (baseCost + fuelCost + maintenanceCost + driverCost) * mileage;
    }
    
    @Override
    public double getVehicleValue() {
        int currentYear = LocalDate.now().getYear();
        int age = currentYear - year;
        double baseValue = 150000; // Base value for a bus
        double depreciation = Math.max(0, baseValue * (1 - 0.10 * age)); // 10% depreciation per year
        return depreciation;
    }
    
    @Override
    protected double getFuelEfficiency() {
        return fuelType.equals("Electric") ? 25 : 8; // 8 MPG for diesel buses
    }
    
    private double getFuelCostPerMile() {
        double fuelPrice = fuelType.equals("Electric") ? 0.12 : 3.20; // Diesel price
        return fuelPrice / getFuelEfficiency();
    }
    
    @Override
    public String getVehicleType() {
        return "Bus";
    }
    
    // Getters for bus-specific attributes
    public int getSeatingCapacity() { return seatingCapacity; }
    public boolean isDoubleDecker() { return isDoubleDecker; }
    public String getRouteType() { return routeType; }
    public boolean hasWheelchairAccess() { return hasWheelchairAccess; }
}

// Truck class
class Truck extends Vehicle {
    private double loadCapacity; // in tons
    private String truckType; // Delivery, Heavy-duty, Pickup
    private boolean hasTrailer;
    private int numberOfAxles;
    
    public Truck(String vehicleId, String brand, String model, int year, 
                 double mileage, String fuelType, double loadCapacity, String truckType) {
        super(vehicleId, brand, model, year, mileage, fuelType);
        this.loadCapacity = loadCapacity;
        this.truckType = truckType;
        this.hasTrailer = loadCapacity > 5.0; // Trucks with >5 ton capacity typically have trailers
        this.numberOfAxles = loadCapacity > 10.0 ? 3 : 2;
    }
    
    @Override
    public double calculateRunningCost() {
        double baseCost = 0.35; // Base cost for trucks
        double fuelCost = getFuelCostPerMile();
        double maintenanceCost = 0.10 + (loadCapacity * 0.02); // Maintenance increases with capacity
        double driverCost = 0.06; // Driver cost per mile
        return (baseCost + fuelCost + maintenanceCost + driverCost) * mileage;
    }
    
    @Override
    public double getVehicleValue() {
        int currentYear = LocalDate.now().getYear();
        int age = currentYear - year;
        double baseValue = 80000 + (loadCapacity * 5000); // Value increases with capacity
        double depreciation = Math.max(0, baseValue * (1 - 0.12 * age)); // 12% depreciation per year
        return depreciation;
    }
    
    @Override
    protected double getFuelEfficiency() {
        double efficiency = fuelType.equals("Electric") ? 15 : 6; // 6 MPG for diesel trucks
        return efficiency - (loadCapacity * 0.1); // Efficiency decreases with capacity
    }
    
    private double getFuelCostPerMile() {
        double fuelPrice = fuelType.equals("Electric") ? 0.12 : 3.20; // Diesel price
        return fuelPrice / getFuelEfficiency();
    }
    
    @Override
    public String getVehicleType() {
        return "Truck";
    }
    
    // Getters for truck-specific attributes
    public double getLoadCapacity() { return loadCapacity; }
    public String getTruckType() { return truckType; }
    public boolean hasTrailer() { return hasTrailer; }
    public int getNumberOfAxles() { return numberOfAxles; }
}

// Driver class
class Driver {
    private String driverId;
    private String driverName;
    private String licenseType;
    private Vehicle assignedVehicle;
    private int totalTrips;
    private LocalDate licenseExpiryDate;
    private double totalDistanceDriven;
    
    public Driver(String driverId, String driverName, String licenseType) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.licenseType = licenseType;
        this.totalTrips = 0;
        this.totalDistanceDriven = 0.0;
        this.licenseExpiryDate = LocalDate.now().plusYears(5); // License expires in 5 years
    }
    
    public void assignVehicle(Vehicle vehicle) {
        if (this.assignedVehicle != null) {
            System.out.println("Driver " + driverName + " is already assigned to vehicle " + 
                             assignedVehicle.getVehicleId());
            return;
        }
        this.assignedVehicle = vehicle;
    }
    
    public void completeTrip(double distance) {
        if (assignedVehicle == null) {
            System.out.println("Driver " + driverName + " is not assigned to any vehicle.");
            return;
        }
        
        totalTrips++;
        totalDistanceDriven += distance;
        
        // Update vehicle mileage
        double currentMileage = assignedVehicle.getMileage();
        assignedVehicle.updateMileage(currentMileage + distance);
        
        System.out.println("Trip completed by " + driverName + ". Distance: " + distance + " miles");
    }
    
    public boolean isLicenseValid() {
        return LocalDate.now().isBefore(licenseExpiryDate);
    }
    
    public void unassignVehicle() {
        if (assignedVehicle != null) {
            assignedVehicle.setCurrentStatus("Available");
            assignedVehicle = null;
            System.out.println("Driver " + driverName + " has been unassigned from vehicle.");
        }
    }
    
    // Getters
    public String getDriverId() { return driverId; }
    public String getDriverName() { return driverName; }
    public String getLicenseType() { return licenseType; }
    public Vehicle getAssignedVehicle() { return assignedVehicle; }
    public int getTotalTrips() { return totalTrips; }
    public double getTotalDistanceDriven() { return totalDistanceDriven; }
    public LocalDate getLicenseExpiryDate() { return licenseExpiryDate; }
    
    @Override
    public String toString() {
        return String.format("Driver [ID: %s, Name: %s, License: %s, Trips: %d, Vehicle: %s]",
                driverId, driverName, licenseType, totalTrips,
                assignedVehicle != null ? assignedVehicle.getVehicleId() : "None");
    }
}

// Fleet Management System
class FleetManager {
    private List<Vehicle> vehicles;
    private List<Driver> drivers;
    
    public FleetManager() {
        vehicles = new ArrayList<>();
        drivers = new ArrayList<>();
    }
    
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        System.out.println("Added vehicle: " + vehicle.getVehicleId());
    }
    
    public void addDriver(Driver driver) {
        drivers.add(driver);
        System.out.println("Added driver: " + driver.getDriverName());
    }
    
    public void displayFleetStatus() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("           FLEET STATUS REPORT");
        System.out.println("=".repeat(60));
        System.out.println("Company: " + Vehicle.getCompanyName());
        System.out.println("Total Vehicles: " + Vehicle.getTotalVehicles());
        System.out.printf("Fleet Value: $%.2f%n", Vehicle.getFleetValue());
        System.out.printf("Total Fuel Consumption: %.2f gallons%n", Vehicle.getTotalFuelConsumption());
        System.out.println("=".repeat(60));
        
        System.out.println("\nVEHICLES:");
        for (Vehicle vehicle : vehicles) {
            System.out.println("• " + vehicle);
            if (vehicle.checkServiceDue()) {
                System.out.println("  ⚠️  Service required!");
            }
        }
        
        System.out.println("\nDRIVERS:");
        for (Driver driver : drivers) {
            System.out.println("• " + driver);
            if (!driver.isLicenseValid()) {
                System.out.println("  ⚠️  License expired!");
            }
        }
    }
    
    public void displayFinancialReport() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("          FINANCIAL REPORT");
        System.out.println("=".repeat(60));
        
        double totalRunningCost = 0;
        for (Vehicle vehicle : vehicles) {
            double cost = vehicle.calculateRunningCost();
            totalRunningCost += cost;
            System.out.printf("%-15s %-10s: $%,.2f%n", 
                            vehicle.getVehicleType(), vehicle.getVehicleId(), cost);
        }
        
        System.out.println("-".repeat(40));
        System.out.printf("Total Running Costs: $%,.2f%n", totalRunningCost);
        System.out.printf("Fleet Value: $%,.2f%n", Vehicle.getFleetValue());
        System.out.println("=".repeat(60));
    }
    
    public List<Vehicle> getVehiclesDueForService() {
        List<Vehicle> serviceRequired = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.checkServiceDue()) {
                serviceRequired.add(vehicle);
            }
        }
        return serviceRequired;
    }
}

// Main class demonstrating the system
public class VehicleFleetManagementSystem {
    public static void main(String[] args) {
        FleetManager fleetManager = new FleetManager();
        
        // Create vehicles
        Car car1 = new Car("CAR001", "Toyota", "Camry", 2022, 15000, "Gasoline", 5, "Sedan");
        Car car2 = new Car("CAR002", "Tesla", "Model 3", 2023, 8000, "Electric", 5, "Sedan");
        
        Bus bus1 = new Bus("BUS001", "Mercedes", "Citaro", 2021, 45000, "Diesel", 50, "City");
        Bus bus2 = new Bus("BUS002", "Volvo", "7900E", 2023, 12000, "Electric", 75, "Intercity");
        
        Truck truck1 = new Truck("TRK001", "Ford", "F-150", 2022, 35000, "Gasoline", 3.5, "Pickup");
        Truck truck2 = new Truck("TRK002", "Peterbilt", "579", 2020, 125000, "Diesel", 25.0, "Heavy-duty");
        
        // Add vehicles to fleet
        fleetManager.addVehicle(car1);
        fleetManager.addVehicle(car2);
        fleetManager.addVehicle(bus1);
        fleetManager.addVehicle(bus2);
        fleetManager.addVehicle(truck1);
        fleetManager.addVehicle(truck2);
        
        // Create drivers
        Driver driver1 = new Driver("DRV001", "John Smith", "Class C");
        Driver driver2 = new Driver("DRV002", "Maria Garcia", "Class C");
        Driver driver3 = new Driver("DRV003", "Robert Johnson", "CDL Class B");
        Driver driver4 = new Driver("DRV004", "Sarah Wilson", "CDL Class A");
        
        // Add drivers to fleet
        fleetManager.addDriver(driver1);
        fleetManager.addDriver(driver2);
        fleetManager.addDriver(driver3);
        fleetManager.addDriver(driver4);
        
        System.out.println("🚗 Vehicle Fleet Management System Initialized 🚗\n");
        
        // Assign drivers to vehicles
        car1.assignDriver(driver1);
        bus1.assignDriver(driver3);
        truck2.assignDriver(driver4);
        
        // Simulate some operations
        System.out.println("\n--- Simulating Operations ---");
        driver1.completeTrip(150); // John drives the Toyota Camry 150 miles
        driver3.completeTrip(75);  // Robert drives the bus 75 miles
        
        // Schedule maintenance
        truck2.scheduleMaintenance();
        
        // Display reports
        fleetManager.displayFleetStatus();
        fleetManager.displayFinancialReport();
        
        // Check vehicles due for service
        System.out.println("\n--- Vehicles Due for Service ---");
        List<Vehicle> serviceDue = fleetManager.getVehiclesDueForService();
        if (serviceDue.isEmpty()) {
            System.out.println("No vehicles currently due for service.");
        } else {
            for (Vehicle vehicle : serviceDue) {
                System.out.println("Service needed: " + vehicle.getVehicleId());
            }
        }
        
        System.out.println("\n🏁 Fleet Management System Demo Complete! 🏁");
    }
}