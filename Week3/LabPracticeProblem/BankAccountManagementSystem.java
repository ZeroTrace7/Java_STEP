class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private static int totalAccounts = 0;
    
    public BankAccount(String accountHolderName, double initialDeposit) {
        if (initialDeposit < 0) {
            System.out.println("Sorry, initial deposit cannot be negative!");
            System.out.println("Account creation failed for: " + accountHolderName);
            return;
        }
        
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
        this.accountNumber = generateAccountNumber();
        totalAccounts++;
        
        System.out.println("Great! Account created successfully!");
        displayAccountInfo();
    }
    
    public static String generateAccountNumber() {
        return String.format("ACC%03d", totalAccounts + 1);
    }
    
    public static int getTotalAccounts() {
        return totalAccounts;
    }
    
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Oops! Deposit amount must be greater than 0!");
            return;
        }
        
        balance += amount;
        
        System.out.println("Money deposited successfully!");
        System.out.println("Amount added: $" + String.format("%.2f", amount));
        System.out.println("Your new balance: $" + String.format("%.2f", balance));
    }
    
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Sorry, withdrawal amount must be positive!");
            return;
        }
        
        if (amount > balance) {
            System.out.println("Insufficient balance! Cannot withdraw this amount.");
            System.out.println("Your current balance: $" + String.format("%.2f", balance));
            System.out.println("Amount you tried to withdraw: $" + String.format("%.2f", amount));
            return;
        }
        
        balance -= amount;
        
        System.out.println("Cash withdrawn successfully!");
        System.out.println("Amount withdrawn: $" + String.format("%.2f", amount));
        System.out.println("Remaining balance: $" + String.format("%.2f", balance));
    }
    
    public double checkBalance() {
        System.out.println("Account Balance Details:");
        System.out.println("Hello " + accountHolderName + "!");
        System.out.println("Your current balance is: $" + String.format("%.2f", balance));
        return balance;
    }
    
    public void displayAccountInfo() {
        System.out.println("------- Account Details -------");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Current Balance: $" + String.format("%.2f", balance));
        System.out.println("--------------------------------");
    }
}

public class BankAccountManagementSystem {
    public static void main(String[] args) {
        System.out.println("Welcome to Simple Bank Management System");
        System.out.println("Let's start banking with us!");
        
        System.out.println("\nCreating some bank accounts...");
        
        BankAccount johnAccount = new BankAccount("John Doe", 1500.0);
        BankAccount janeAccount = new BankAccount("Jane Smith", 2000.0);
        BankAccount bobAccount = new BankAccount("Bob Wilson", 500.0);
        
        System.out.println("\nTrying to create account with negative money...");
        BankAccount invalidAccount = new BankAccount("Invalid User", -100.0);
        
        System.out.println("\nLet's make some deposits:");
        johnAccount.deposit(300.0);
        janeAccount.deposit(150.0);
        
        System.out.println("\nTesting invalid deposits:");
        bobAccount.deposit(-50.0);
        bobAccount.deposit(0);
        
        System.out.println("\nTime for some withdrawals:");
        johnAccount.withdraw(200.0);
        janeAccount.withdraw(100.0);
        
        System.out.println("\nTesting withdrawal limits:");
        bobAccount.withdraw(1000.0);
        johnAccount.withdraw(-25.0);
        
        System.out.println("\nChecking everyone's balance:");
        johnAccount.checkBalance();
        janeAccount.checkBalance();
        bobAccount.checkBalance();
        
        System.out.println("\nHere are all account details:");
        johnAccount.displayAccountInfo();
        janeAccount.displayAccountInfo();
        bobAccount.displayAccountInfo();
        
        System.out.println("\nBank statistics:");
        System.out.println("Total accounts we have: " + BankAccount.getTotalAccounts());
        System.out.println("Next account will be: " + BankAccount.generateAccountNumber());
        
        System.out.println("\nDoing some more banking with John:");
        johnAccount.deposit(500.0);
        johnAccount.withdraw(100.0);
        johnAccount.checkBalance();
        
        System.out.println("\nThanks for using our banking system!");
    }
}