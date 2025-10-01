abstract class Animal {
    protected String name;
    protected String habitat;
    
    public Animal(String name, String habitat) {
        this.name = name;
        this.habitat = habitat;
    }
    
    public abstract void eat();
}

interface Soundable {
    void makeSound();
}

class Dog extends Animal implements Soundable {
    private String breed;
    
    public Dog(String name, String habitat, String breed) {
        super(name, habitat);
        this.breed = breed;
    }
    
    public void eat() {
        System.out.println(name + " eats meat and dog food");
    }
    
    public void makeSound() {
        System.out.println(name + " says: Bark Bark!");
    }
    
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Breed: " + breed);
        System.out.println("Habitat: " + habitat);
    }
}

public class AnimalTest {
    public static void main(String[] args) {
        Dog dog = new Dog("Buddy", "Domestic", "Labrador");
        dog.displayInfo();
        dog.eat();
        dog.makeSound();
    }
}