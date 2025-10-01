abstract class Device {
    protected String brand;
    protected String model;
    
    public Device(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }
    
    public abstract void powerOn();
}

interface Connectable {
    void connect();
}

class Smartphone extends Device implements Connectable {
    private String os;
    
    public Smartphone(String brand, String model, String os) {
        super(brand, model);
        this.os = os;
    }
    
    public void powerOn() {
        System.out.println("Smartphone powering on...");
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("OS: " + os);
    }
    
    public void connect() {
        System.out.println("Connecting to WiFi and Bluetooth");
    }
}

public class DeviceTest {
    public static void main(String[] args) {
        Smartphone phone = new Smartphone("Samsung", "Galaxy S23", "Android");
        phone.powerOn();
        phone.connect();
    }
}