abstract class BankAccount {
    double balance;
    
    BankAccount(double balance) {
        this.balance = balance;
    }
    
    void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount);
        System.out.println("New Balance: " + balance);
    }
    
    abstract void calculateInterest();
}

class SavingsAccount extends BankAccount {
    SavingsAccount(double balance) {
        super(balance);
    }
    
    void calculateInterest() {
        double interest = balance * 0.04;
        balance += interest;
        System.out.println("Savings Account Interest (4%): " + interest);
        System.out.println("Balance after interest: " + balance);
    }
}

class CurrentAccount extends BankAccount {
    CurrentAccount(double balance) {
        super(balance);
    }
    
    void calculateInterest() {
        double interest = balance * 0.01;
        balance += interest;
        System.out.println("Current Account Interest (1%): " + interest);
        System.out.println("Balance after interest: " + balance);
    }
}

public class BankAccountClass {
    public static void main(String[] args) {
        SavingsAccount savings = new SavingsAccount(10000);
        System.out.println("Savings Account:");
        savings.deposit(2000);
        savings.calculateInterest();
        
        System.out.println();
        
        CurrentAccount current = new CurrentAccount(15000);
        System.out.println("Current Account:");
        current.deposit(3000);
        current.calculateInterest();
    }
}