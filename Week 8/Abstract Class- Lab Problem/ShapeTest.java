abstract class Shape {
    protected double area;
    protected double perimeter;
    
    public abstract void calculateArea();
    public abstract void calculatePerimeter();
}

interface Drawable {
    void draw();
}

class Circle extends Shape implements Drawable {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    public void calculateArea() {
        area = 3.14 * radius * radius;
        System.out.println("Circle Area: " + area);
    }
    
    public void calculatePerimeter() {
        perimeter = 2 * 3.14 * radius;
        System.out.println("Circle Perimeter: " + perimeter);
    }
    
    public void draw() {
        System.out.println("Drawing a Circle with radius " + radius);
    }
}

public class ShapeTest {
    public static void main(String[] args) {
        Circle circle = new Circle(5);
        circle.draw();
        circle.calculateArea();
        circle.calculatePerimeter();
    }
}