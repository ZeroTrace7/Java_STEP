interface Discount {
    double applyDiscount(double amount);
}

class Payment {
    
    void processTransaction(double amount) {
        
        class Validator {
            boolean isValid(double amt) {
                return amt > 0 && amt <= 100000;
            }
        }
        
        Validator validator = new Validator();
        
        if (validator.isValid(amount)) {
            System.out.println("Payment amount is valid: $" + amount);
            
            Discount discount = new Discount() {
                @Override
                public double applyDiscount(double amt) {
                    return amt * 0.9;
                }
            };
            
            double finalAmount = discount.applyDiscount(amount);
            System.out.println("After 10% discount: $" + finalAmount);
            System.out.println("Transaction processed successfully");
        } else {
            System.out.println("Invalid payment amount: $" + amount);
        }
    }
}

public class PaymentOut {
    public static void main(String[] args) {
        Payment payment = new Payment();
        
        System.out.println("Transaction 1:");
        payment.processTransaction(5000);
        System.out.println();
        
        System.out.println("Transaction 2:");
        payment.processTransaction(-100);
        System.out.println();
        
        System.out.println("Transaction 3:");
        payment.processTransaction(150000);
    }
}