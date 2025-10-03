abstract class Vehicle {
    abstract void start();
    
    void stop() {
        System.out.println("Vehicle stopped");
    }
}

interface Fuel {
    void refuel();
}

class Car extends Vehicle implements Fuel {
    public void start() {
        System.out.println("Car engine started");
    }
    
    public void refuel() {
        System.out.println("Car is refueling with petrol");
    }
}

public class Fuelclass {
    public static void main(String[] args) {
        Car myCar = new Car();
        
        myCar.start();
        myCar.stop();
        myCar.refuel();
        
        System.out.println();
        
        Vehicle vehicle = new Car();
        vehicle.start();
        vehicle.stop();
        
        System.out.println();
        
        Fuel fuelVehicle = new Car();
        fuelVehicle.refuel();
    }
}