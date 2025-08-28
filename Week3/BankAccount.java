public class BankAccount {
    // STATIC VARIABLES (Class-level) - Shared by ALL instances
    // These belong to the class itself, not to any specific object
    private static String bankName = "Global Bank"; // Same for all accounts
    private static int totalAccounts = 0;           // Count of all accounts created
    private static double interestRate = 0.025;     // Same rate for all accounts (2.5%)
    
    // INSTANCE VARIABLES (Object-level) - Unique for EACH instance
    // Each BankAccount object has its own copy of these variables
    private String accountNumber;  // Unique for each account
    private String accountHolder;  // Unique for each account
    private double balance;        // Unique for each account
    
    // CONSTRUCTOR - Initializes instance variables and updates static counter
    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        // Initialize instance variables (each object gets its own values)
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        
        // Increment static variable (shared by all objects)
        totalAccounts++; // This affects the class-level counter
        
        System.out.println("🏦 New account created: " + accountNumber + 
                          " (Total accounts now: " + totalAccounts + ")");
    }
    
    // STATIC METHODS - Belong to the class, can be called without creating objects
    // Can only access static variables, NOT instance variables
    
    public static void setBankName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            bankName = name;
            System.out.println("🏛️ Bank name changed to: " + bankName);
        } else {
            System.out.println("⚠️ Invalid bank name!");
        }
    }
    
    public static void setInterestRate(double rate) {
        if (rate >= 0 && rate <= 1.0) {
            interestRate = rate;
            System.out.printf("📈 Interest rate changed to: %.2f%%\n", rate * 100);
        } else {
            System.out.println("⚠️ Interest rate must be between 0 and 1.0 (0% to 100%)!");
        }
    }
    
    public static int getTotalAccounts() {
        return totalAccounts;
    }
    
    public static void displayBankInfo() {
        System.out.println("========== BANK INFORMATION ==========");
        System.out.println("Bank Name: " + bankName);
        System.out.println("Total Accounts: " + totalAccounts);
        System.out.printf("Current Interest Rate: %.2f%%\n", interestRate * 100);
        System.out.println("======================================");
    }
    
    // Static getter methods for accessing static variables
    public static String getBankName() {
        return bankName;
    }
    
    public static double getInterestRate() {
        return interestRate;
    }
    
    // INSTANCE METHODS - Belong to specific objects, need an object to be called
    // Can access both instance variables AND static variables
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("💰 Deposited $%.2f to account %s. New balance: $%.2f\n", 
                            amount, accountNumber, balance);
        } else {
            System.out.println("⚠️ Deposit amount must be positive!");
        }
    }
    
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.printf("💸 Withdrew $%.2f from account %s. New balance: $%.2f\n", 
                            amount, accountNumber, balance);
        } else if (amount > balance) {
            System.out.println("⚠️ Insufficient funds! Current balance: $" + balance);
        } else {
            System.out.println("⚠️ Withdrawal amount must be positive!");
        }
    }
    
    public double calculateInterest() {
        // Instance method accessing static variable (interestRate)
        double interest = balance * interestRate;
        System.out.printf("📊 Annual interest for account %s: $%.2f (%.2f%% of $%.2f)\n", 
                         accountNumber, interest, interestRate * 100, balance);
        return interest;
    }
    
    public void displayAccountInfo() {
        System.out.println("======= ACCOUNT INFORMATION =======");
        System.out.println("Bank: " + bankName); // Accessing static variable
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.printf("Current Balance: $%.2f\n", balance);
        System.out.printf("Interest Rate: %.2f%%\n", interestRate * 100);
        System.out.printf("Potential Annual Interest: $%.2f\n", balance * interestRate);
        System.out.println("===================================\n");
    }
    
    // Instance getter methods
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getAccountHolder() {
        return accountHolder;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public static void main(String[] args) {
        System.out.println("=== INSTANCE vs STATIC MEMBERS DEMONSTRATION ===\n");
        
        // Demonstrate STATIC methods - called on the CLASS, not on objects
        System.out.println("--- STATIC METHODS (called on class) ---");
        System.out.println("Initial bank info:");
        BankAccount.displayBankInfo(); // Called on class name
        System.out.println("Total accounts before creating any: " + BankAccount.getTotalAccounts());
        System.out.println();
        
        // Create first account - notice how static totalAccounts increments
        System.out.println("--- Creating First Account ---");
        BankAccount account1 = new BankAccount("ACC001", "Alice Johnson", 1000.0);
        
        // Create second account - totalAccounts increments again
        System.out.println("\n--- Creating Second Account ---");
        BankAccount account2 = new BankAccount("ACC002", "Bob Smith", 1500.0);
        
        // Create third account
        System.out.println("\n--- Creating Third Account ---");
        BankAccount account3 = new BankAccount("ACC003", "Carol Davis", 2000.0);
        
        System.out.println("\n--- Bank Info After Creating Accounts ---");
        BankAccount.displayBankInfo(); // Shows updated totalAccounts
        
        // Demonstrate that static variables are SHARED by all instances
        System.out.println("\n=== STATIC VARIABLES ARE SHARED ===");
        System.out.println("Changing bank name using static method...");
        BankAccount.setBankName("Premium Banking Corp");
        
        System.out.println("Changing interest rate using static method...");
        BankAccount.setInterestRate(0.035); // 3.5%
        
        System.out.println("\nNow all accounts reflect the same changes:");
        account1.displayAccountInfo(); // Shows new bank name and interest rate
        account2.displayAccountInfo(); // Shows same bank name and interest rate
        
        // Demonstrate INSTANCE methods - called on specific objects
        System.out.println("=== INSTANCE METHODS (called on specific objects) ===");
        
        System.out.println("--- Account 1 Operations ---");
        account1.deposit(250.0);
        account1.withdraw(100.0);
        account1.calculateInterest();
        
        System.out.println("\n--- Account 2 Operations ---");
        account2.deposit(500.0);
        account2.withdraw(200.0);
        account2.calculateInterest();
        
        System.out.println("\n--- Account 3 Operations ---");
        account3.deposit(1000.0);
        account3.calculateInterest();
        
        // Show that each account maintains its own INSTANCE variables
        System.out.println("\n=== INSTANCE VARIABLES ARE UNIQUE PER OBJECT ===");
        System.out.println("Each account has different balances (instance variables):");
        System.out.println("Account 1 balance: $" + account1.getBalance());
        System.out.println("Account 2 balance: $" + account2.getBalance());
        System.out.println("Account 3 balance: $" + account3.getBalance());
        
        // End of demonstration
        System.out.println("\nThank you for using the BankAccount demo!\n");
    }
// End of BankAccount class
}