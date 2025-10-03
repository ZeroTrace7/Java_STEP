abstract class Shape {
    abstract double area();
    abstract double perimeter();
    
    void displayInfo() {
        System.out.println("Area: " + area());
        System.out.println("Perimeter: " + perimeter());
    }
}

class Circle extends Shape {
    double radius;
    
    Circle(double radius) {
        this.radius = radius;
    }
    
    double area() {
        return 3.14 * radius * radius;
    }
    
    double perimeter() {
        return 2 * 3.14 * radius;
    }
}

class Rectangle extends Shape {
    double length;
    double width;
    
    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    
    double area() {
        return length * width;
    }
    
    double perimeter() {
        return 2 * (length + width);
    }
}

public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle(5);
        System.out.println("Circle Information:");
        circle.displayInfo();
        
        System.out.println();
        
        Rectangle rectangle = new Rectangle(4, 6);
        System.out.println("Rectangle Information:");
        rectangle.displayInfo();
    }
}