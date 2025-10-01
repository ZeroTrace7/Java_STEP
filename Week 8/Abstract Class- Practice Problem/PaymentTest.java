abstract class PaymentGateway {
    public abstract void pay(double amount);
    public abstract void refund(double amount);
}

class CreditCardPayment extends PaymentGateway {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via Credit Card");
    }
    
    public void refund(double amount) {
        System.out.println("Refund " + amount + " to Credit Card");
    }
}

class UPIPayment extends PaymentGateway {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via UPI");
    }
    
    public void refund(double amount) {
        System.out.println("Refund " + amount + " to UPI");
    }
}

public class PaymentTest {
    public static void main(String[] args) {
        PaymentGateway pg1 = new CreditCardPayment();
        pg1.pay(5000);
        pg1.refund(500);
        
        System.out.println();
        
        PaymentGateway pg2 = new UPIPayment();
        pg2.pay(3000);
        pg2.refund(300);
    }
}