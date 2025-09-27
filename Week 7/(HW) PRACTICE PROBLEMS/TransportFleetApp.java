class Vehicle {
    protected String vehicleId;
    protected String currentLocation;
    protected boolean isActive;
    
    public Vehicle(String vehicleId, String currentLocation) {
        this.vehicleId = vehicleId;
        this.currentLocation = currentLocation;
        this.isActive = true;
    }
    
    public void dispatch() {
        System.out.println("Vehicle " + vehicleId + " dispatched from " + currentLocation);
    }
    
    public void showStatus() {
        String status = isActive ? "Active" : "Inactive";
        System.out.println("Vehicle: " + vehicleId + " | Location: " + currentLocation + " | Status: " + status);
    }
    
    public String getVehicleId() {
        return vehicleId;
    }
}

class Bus extends Vehicle {
    private String route;
    private int passengerCapacity;
    private int currentPassengers;
    
    public Bus(String vehicleId, String currentLocation, String route, int capacity) {
        super(vehicleId, currentLocation);
        this.route = route;
        this.passengerCapacity = capacity;
        this.currentPassengers = 0;
    }
    
    public void dispatch() {
        System.out.println("Bus Dispatch: " + vehicleId + " on Route " + route);
        System.out.println("Following fixed route with " + currentPassengers + "/" + passengerCapacity + " passengers");
        System.out.println("Next stops announced automatically");
    }
    
    public void boardPassengers(int passengers) {
        if (currentPassengers + passengers <= passengerCapacity) {
            currentPassengers += passengers;
            System.out.println(passengers + " passengers boarded. Total: " + currentPassengers);
        } else {
            System.out.println("Bus full! Cannot board " + passengers + " passengers");
        }
    }
}

class Taxi extends Vehicle {
    private double fareRate;
    private boolean isOccupied;
    private String destination;
    
    public Taxi(String vehicleId, String currentLocation) {
        super(vehicleId, currentLocation);
        this.fareRate = 2.50;
        this.isOccupied = false;
    }
    
    public void dispatch() {
        if (!isOccupied) {
            System.out.println("Taxi Dispatch: " + vehicleId + " from " + currentLocation);
            System.out.println("Available for door-to-door service");
            System.out.println("Fare rate: $" + fareRate + " per km");
        } else {
            System.out.println("Taxi " + vehicleId + " is occupied, going to " + destination);
        }
    }
    
    public void pickupPassenger(String dest, double distance) {
        isOccupied = true;
        destination = dest;
        double fare = distance * fareRate;
        System.out.println("Passenger picked up. Destination: " + dest);
        System.out.println("Distance: " + distance + " km | Fare: $" + fare);
    }
    
    public void dropOffPassenger() {
        isOccupied = false;
        currentLocation = destination;
        destination = null;
        System.out.println("Passenger dropped off at " + currentLocation);
    }
}

class Train extends Vehicle {
    private String schedule;
    private int totalCars;
    private int passengersPerCar;
    private int totalPassengers;
    
    public Train(String vehicleId, String currentLocation, String schedule, int cars) {
        super(vehicleId, currentLocation);
        this.schedule = schedule;
        this.totalCars = cars;
        this.passengersPerCar = 150;
        this.totalPassengers = 0;
    }
    
    public void dispatch() {
        int maxCapacity = totalCars * passengersPerCar;
        System.out.println("Train Dispatch: " + vehicleId + " from " + currentLocation);
        System.out.println("Schedule: " + schedule);
        System.out.println("Cars: " + totalCars + " | Capacity: " + totalPassengers + "/" + maxCapacity);
        System.out.println("Following railway schedule");
    }
    
    public void loadPassengers(int passengers) {
        int maxCapacity = totalCars * passengersPerCar;
        if (totalPassengers + passengers <= maxCapacity) {
            totalPassengers += passengers;
            System.out.println(passengers + " passengers loaded across " + totalCars + " cars");
        }
    }
}

class Bike extends Vehicle {
    private boolean isRented;
    private double hourlyRate;
    
    public Bike(String vehicleId, String currentLocation) {
        super(vehicleId, currentLocation);
        this.isRented = false;
        this.hourlyRate = 5.00;
    }
    
    public void dispatch() {
        if (!isRented) {
            System.out.println("Bike Dispatch: " + vehicleId + " at " + currentLocation);
            System.out.println("Available for eco-friendly short trips");
            System.out.println("Rate: $" + hourlyRate + " per hour");
        } else {
            System.out.println("Bike " + vehicleId + " is currently rented");
        }
    }
    
    public void rentBike(int hours) {
        if (!isRented) {
            isRented = true;
            double cost = hours * hourlyRate;
            System.out.println("Bike rented for " + hours + " hours. Cost: $" + cost);
        }
    }
    
    public void returnBike(String location) {
        isRented = false;
        currentLocation = location;
        System.out.println("Bike returned at " + location);
    }
}

public class TransportFleetApp {
    
    public static void dispatchVehicle(Vehicle vehicle) {
        System.out.println("Dispatching vehicle: " + vehicle.getVehicleId());
        vehicle.dispatch();
        System.out.println();
    }
    
    public static void main(String[] args) {
        System.out.println("City Transportation Fleet Management");
        System.out.println();
        
        Vehicle[] fleet = new Vehicle[5];
        
        fleet[0] = new Bus("BUS-101", "Central Station", "Route A", 50);
        fleet[1] = new Taxi("TAXI-205", "Airport");
        fleet[2] = new Train("TRAIN-301", "North Terminal", "Express Line", 8);
        fleet[3] = new Bike("BIKE-007", "Park Avenue");
        fleet[4] = new Bus("BUS-102", "Mall", "Route B", 40);
        
        System.out.println("Fleet Status:");
        for (int i = 0; i < fleet.length; i++) {
            fleet[i].showStatus();
        }
        System.out.println();
        
        System.out.println("Dynamic Dispatch System:");
        for (int i = 0; i < fleet.length; i++) {
            dispatchVehicle(fleet[i]);
        }
        
        System.out.println("Specific Operations:");
        Bus bus = (Bus) fleet[0];
        bus.boardPassengers(25);
        
        Taxi taxi = (Taxi) fleet[1];
        taxi.pickupPassenger("Downtown", 12.5);
        
        Train train = (Train) fleet[2];
        train.loadPassengers(500);
        
        Bike bike = (Bike) fleet[3];
        bike.rentBike(3);
        
        System.out.println();
        System.out.println("Dynamic Method Dispatch in action:");
        System.out.println("Same 'dispatch()' call produces different behavior");
        System.out.println("Runtime decides which method to execute");
    }
}