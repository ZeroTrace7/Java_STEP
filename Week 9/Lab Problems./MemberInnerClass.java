class Outer {
    private String message;

    Outer(String message) {
        this.message = message;
    }

    class Inner {
        void display() {
            System.out.println("Message from inner class: " + message);
        }
    }
}

public class MemberInnerClass {
    public static void main(String[] args) {
        Outer outer = new Outer("Hello from Outer class");
        
        Outer.Inner inner = outer.new Inner();
        
        inner.display();
    }
}