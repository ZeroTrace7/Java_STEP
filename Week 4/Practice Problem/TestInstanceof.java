class Animal {
    public void makeSound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Dog barks: Woof!");
    }
    
    public void fetch() {
        System.out.println("Dog fetches the ball");
    }
}

class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Cat meows: Meow!");
    }
    
    public void climb() {
        System.out.println("Cat climbs the tree");
    }
}

public class TestInstanceof {
    public static void main(String[] args) {
        System.out.println("INSTANCEOF TYPE CHECKING DEMONSTRATION");
        System.out.println();
        
        Animal[] animals = { new Dog(), new Cat(), new Dog(), new Animal() };
        
        int dogCount = 0;
        int catCount = 0;
        int animalCount = 0;
        
        System.out.println("Array contents:");
        for (int i = 0; i < animals.length; i++) {
            System.out.print("Index " + i + ": ");
            
            if (animals[i] instanceof Dog) {
                dogCount++;
                System.out.println("Dog instance");
            } else if (animals[i] instanceof Cat) {
                catCount++;
                System.out.println("Cat instance");
            } else if (animals[i] instanceof Animal) {
                animalCount++;
                System.out.println("Animal instance");
            }
        }
        
        System.out.println();
        System.out.println("COUNT RESULTS:");
        System.out.println("Dogs: " + dogCount);
        System.out.println("Cats: " + catCount);
        System.out.println("Animals (base class only): " + animalCount);
        System.out.println("Total objects: " + animals.length);
        
        System.out.println();
        System.out.println("DETAILED TYPE CHECKING:");
        for (int i = 0; i < animals.length; i++) {
            System.out.println("Object " + i + ":");
            System.out.println("  instanceof Animal: " + (animals[i] instanceof Animal));
            System.out.println("  instanceof Dog: " + (animals[i] instanceof Dog));
            System.out.println("  instanceof Cat: " + (animals[i] instanceof Cat));
            
            animals[i].makeSound();
            
            if (animals[i] instanceof Dog) {
                Dog dog = (Dog) animals[i];
                dog.fetch();
            } else if (animals[i] instanceof Cat) {
                Cat cat = (Cat) animals[i];
                cat.climb();
            }
            System.out.println();
        }
        
        System.out.println("EXTENDED DEMONSTRATION:");
        System.out.println();
        
        Animal[] moreAnimals = {
            new Dog(), new Dog(), new Cat(), new Cat(), new Cat(),
            new Animal(), new Dog(), new Animal()
        };
        
        int extendedDogCount = 0;
        int extendedCatCount = 0;
        int extendedAnimalCount = 0;
        
        for (Animal animal : moreAnimals) {
            if (animal instanceof Dog) {
                extendedDogCount++;
            } else if (animal instanceof Cat) {
                extendedCatCount++;
            } else if (animal instanceof Animal) {
                extendedAnimalCount++;
            }
        }
        
        System.out.println("Extended array results:");
        System.out.println("Dogs: " + extendedDogCount);
        System.out.println("Cats: " + extendedCatCount);
        System.out.println("Animals: " + extendedAnimalCount);
        System.out.println("Total: " + moreAnimals.length);
        
        System.out.println();
        System.out.println("INHERITANCE HIERARCHY CHECKING:");
        Dog testDog = new Dog();
        Cat testCat = new Cat();
        Animal testAnimal = new Animal();
        
        System.out.println("Dog object inheritance check:");
        System.out.println("  Dog instanceof Dog: " + (testDog instanceof Dog));
        System.out.println("  Dog instanceof Animal: " + (testDog instanceof Animal));
        System.out.println("  Dog instanceof Object: " + (testDog instanceof Object));
        
        System.out.println();
        System.out.println("Cat object inheritance check:");
        System.out.println("  Cat instanceof Cat: " + (testCat instanceof Cat));
        System.out.println("  Cat instanceof Animal: " + (testCat instanceof Animal));
        System.out.println("  Cat instanceof Object: " + (testCat instanceof Object));
        
        System.out.println();
        System.out.println("Animal object inheritance check:");
        System.out.println("  Animal instanceof Animal: " + (testAnimal instanceof Animal));
        System.out.println("  Animal instanceof Dog: " + (testAnimal instanceof Dog));
        System.out.println("  Animal instanceof Cat: " + (testAnimal instanceof Cat));
        System.out.println("  Animal instanceof Object: " + (testAnimal instanceof Object));
        
        System.out.println();
        System.out.println("INSTANCEOF CONCEPTS DEMONSTRATED:");
        System.out.println("1. Type checking with instanceof operator");
        System.out.println("2. Inheritance hierarchy verification");
        System.out.println("3. Safe type casting after instanceof check");
        System.out.println("4. Counting specific object types in arrays");
        System.out.println("5. Polymorphic method calls");
        
        System.out.println();
        System.out.println("PRACTICAL USE CASE:");
        performAnimalActions(moreAnimals);
    }
    
    public static void performAnimalActions(Animal[] animals) {
        System.out.println("Performing type-specific actions:");
        
        for (Animal animal : animals) {
            if (animal instanceof Dog) {
                System.out.print("Dog action: ");
                ((Dog) animal).fetch();
            } else if (animal instanceof Cat) {
                System.out.print("Cat action: ");
                ((Cat) animal).climb();
            } else {
                System.out.print("General animal action: ");
                animal.makeSound();
            }
        }
    }
}