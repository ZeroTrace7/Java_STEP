interface Animal {
    void eat();
}

interface Pet extends Animal {
    void play();
}

class Dog implements Pet {
    public void eat() {
        System.out.println("Dog is eating food");
    }
    
    public void play() {
        System.out.println("Dog is playing with ball");
    }
}

public class AnimalClass {
    public static void main(String[] args) {
        Dog myDog = new Dog();
        myDog.eat();
        myDog.play();
        
        System.out.println();
        
        Pet pet = new Dog();
        pet.eat();
        pet.play();
        
        System.out.println();
        
        Animal animal = new Dog();
        animal.eat();
    }
}