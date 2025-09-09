import java.util.Random;

class BankAccount {
    private String accountHolder;
    private int accountNumber;
    private double balance;
    private static Random random = new Random();
    
    public BankAccount() {
        this.accountHolder = "Unknown";
        this.accountNumber = generateAccountNumber();
        this.balance = 0.0;
    }
    
    public BankAccount(String accountHolder) {
        this.accountHolder = accountHolder;
        this.accountNumber = generateAccountNumber();
        this.balance = 0.0;
    }
    
    public BankAccount(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.accountNumber = generateAccountNumber();
        this.balance = initialBalance >= 0 ? initialBalance : 0.0;
    }
    
    private int generateAccountNumber() {
        return 100000 + random.nextInt(900000);
    }
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited Rs." + amount);
            System.out.println("New balance: Rs." + balance);
        } else {
            System.out.println("Invalid deposit amount! Amount must be positive.");
        }
    }
    
    public void withdraw(double amount) {
        if (amount > 0) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Successfully withdrawn Rs." + amount);
                System.out.println("Remaining balance: Rs." + balance);
            } else {
                System.out.println("Insufficient funds! Available balance: Rs." + balance);
            }
        } else {
            System.out.println("Invalid withdrawal amount! Amount must be positive.");
        }
    }
    
    public void displayAccount() {
        System.out.println("ACCOUNT DETAILS");
        System.out.println("Account Holder : " + accountHolder);
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Current Balance: Rs." + String.format("%.2f", balance));
        System.out.println();
    }
    
    public String getAccountHolder() { 
        return accountHolder; 
    }
    
    public int getAccountNumber() { 
        return accountNumber; 
    }
    
    public double getBalance() { 
        return balance; 
    }
    
    public void setAccountHolder(String accountHolder) { 
        this.accountHolder = accountHolder; 
    }
}

public class BankAccountSystem {
    public static void main(String[] args) {
        System.out.println("BANK ACCOUNT MANAGEMENT SYSTEM");
        System.out.println();
        
        System.out.println("1. Account created with Default Constructor:");
        BankAccount account1 = new BankAccount();
        account1.displayAccount();
        
        System.out.println("2. Account created with Name only:");
        BankAccount account2 = new BankAccount("John Smith");
        account2.displayAccount();
        
        System.out.println("3. Account created with Name and Initial Balance:");
        BankAccount account3 = new BankAccount("Alice Johnson", 5000.0);
        account3.displayAccount();
        
        System.out.println("BANKING OPERATIONS ON ALICE'S ACCOUNT:");
        System.out.println();
        
        System.out.println("DEPOSIT OPERATIONS:");
        account3.deposit(1500.0);
        account3.deposit(750.25);
        account3.deposit(-100.0);
        
        System.out.println();
        System.out.println("WITHDRAWAL OPERATIONS:");
        account3.withdraw(2000.0);
        account3.withdraw(500.75);
        account3.withdraw(10000.0);
        account3.withdraw(-50.0);
        
        System.out.println();
        System.out.println("FINAL ACCOUNT STATUS:");
        account3.displayAccount();
        
        System.out.println("MULTIPLE ACCOUNTS DEMONSTRATION:");
        System.out.println();
        
        BankAccount[] accounts = {
            new BankAccount("Bob Wilson", 3000.0),
            new BankAccount("Carol Davis", 1200.50),
            new BankAccount("David Brown")
        };
        
        for (int i = 0; i < accounts.length; i++) {
            System.out.println("Account " + (i + 1) + ":");
            accounts[i].displayAccount();
        }
        
        System.out.println("QUICK TRANSACTIONS:");
        System.out.println();
        
        System.out.println("Bob's Transactions:");
        accounts[0].deposit(500.0);
        accounts[0].withdraw(800.0);
        
        System.out.println();
        System.out.println("Carol's Transactions:");
        accounts[1].deposit(300.0);
        accounts[1].withdraw(200.0);
        
        System.out.println();
        System.out.println("David's Transactions:");
        accounts[2].deposit(1000.0);
        accounts[2].withdraw(150.0);
        
        System.out.println();
        System.out.println("ALL ACCOUNTS FINAL STATUS:");
        for (int i = 0; i < accounts.length; i++) {
            System.out.println("Account " + (i + 1) + " Final Status:");
            accounts[i].displayAccount();
        }
        
        System.out.println("CONSTRUCTOR OVERLOADING DEMONSTRATION:");
        System.out.println("Default Constructor: BankAccount()");
        System.out.println("Single Parameter: BankAccount(String accountHolder)");
        System.out.println("Two Parameters: BankAccount(String accountHolder, double initialBalance)");
        System.out.println();
        System.out.println("All constructors successfully demonstrated!");
    }
}