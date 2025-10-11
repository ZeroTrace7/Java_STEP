class Address {
    String city;
    String state;

    Address(String city, String state) {
        this.city = city;
        this.state = state;
    }

    @Override
    public String toString() {
        return city + ", " + state;
    }
}

class Person implements Cloneable {
    String name;
    Address address;

    Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public Person clone() throws CloneNotSupportedException {
        return (Person) super.clone();
    }

    public Person deepClone() {
        Address newAddress = new Address(this.address.city, this.address.state);
        return new Person(this.name, newAddress);
    }

    @Override
    public String toString() {
        return "Person[name=" + name + ", address=" + address + "]";
    }
}

public class Cloning {
    public static void main(String[] args) throws CloneNotSupportedException {
        Address addr = new Address("Mumbai", "Maharashtra");
        Person p1 = new Person("Raj", addr);

        Person p2 = p1.clone();
        Person p3 = p1.deepClone();

        System.out.println("Original: " + p1);
        System.out.println("Shallow Copy: " + p2);
        System.out.println("Deep Copy: " + p3);
        System.out.println();

        p1.address.city = "Delhi";
        p1.address.state = "Delhi";

        System.out.println("After changing original address:");
        System.out.println("Original: " + p1);
        System.out.println("Shallow Copy: " + p2);
        System.out.println("Deep Copy: " + p3);
    }
}