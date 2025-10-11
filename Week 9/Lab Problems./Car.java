class CarDetails {
    String brand;
    String model;
    double price;

    CarDetails(String brand, String model, double price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car: " + brand + " " + model + ", Price: $" + price;
    }
}
    
public class Car {
    public static void main(String[] args) {
        CarDetails car1 = new CarDetails("Toyota", "Camry", 25000);
        CarDetails car2 = new CarDetails("Honda", "Civic", 22000);

        System.out.println(car1);
        System.out.println("Class name: " + car1.getClass().getName());
        System.out.println();

        System.out.println(car2);
        System.out.println("Class name: " + car2.getClass().getName());
    }
}