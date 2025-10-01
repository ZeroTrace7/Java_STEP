abstract class Fruit {
    protected String color;
    protected String taste;
    
    public Fruit(String color, String taste) {
        this.color = color;
        this.taste = taste;
    }
    
    public abstract void showDetails();
}

interface Edible {
    void nutrientsInfo();
}

class Apple extends Fruit implements Edible {
    private String variety;
    
    public Apple(String color, String taste, String variety) {
        super(color, taste);
        this.variety = variety;
    }
    
    public void showDetails() {
        System.out.println("Apple Details:");
        System.out.println("Color: " + color);
        System.out.println("Taste: " + taste);
        System.out.println("Variety: " + variety);
    }
    
    public void nutrientsInfo() {
        System.out.println("Nutrients: Vitamin C, Fiber, Potassium");
    }
}

public class FruitTest {
    public static void main(String[] args) {
        Apple apple = new Apple("Red", "Sweet", "Fuji");
        apple.showDetails();
        apple.nutrientsInfo();
    }
}