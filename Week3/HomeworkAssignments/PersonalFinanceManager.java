import java.util.ArrayList;
import java.util.List;

class PersonalAccount {
    // Private attributes
    private String accountHolderName;
    private String accountNumber;
    private double currentBalance;
    private double totalIncome;
    private double totalExpenses;
    
    // Lists to store transaction descriptions
    private List<String> incomeTransactions;
    private List<String> expenseTransactions;
    
    // Static variables
    private static int totalAccounts = 0;
    private static String bankName = "Personal Finance Bank";
    
    // Constructor
    public PersonalAccount(String accountHolderName) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = generateAccountNumber();
        this.currentBalance = 0.0;
        this.totalIncome = 0.0;
        this.totalExpenses = 0.0;
        this.incomeTransactions = new ArrayList<>();
        this.expenseTransactions = new ArrayList<>();
        totalAccounts++;
    }
    
    // Instance methods
    public void addIncome(double amount, String description) {
        if (amount > 0) {
            this.totalIncome += amount;
            this.currentBalance += amount;
            this.incomeTransactions.add(description + ": $" + String.format("%.2f", amount));
            System.out.println("Income added: " + description + " - $" + String.format("%.2f", amount));
        } else {
            System.out.println("Invalid income amount. Amount must be positive.");
        }
    }
    
    public void addExpense(double amount, String description) {
        if (amount > 0) {
            this.totalExpenses += amount;
            this.currentBalance -= amount;
            this.expenseTransactions.add(description + ": $" + String.format("%.2f", amount));
            System.out.println("Expense added: " + description + " - $" + String.format("%.2f", amount));
        } else {
            System.out.println("Invalid expense amount. Amount must be positive.");
        }
    }
    
    public double calculateSavings() {
        return totalIncome - totalExpenses;
    }
    
    public void displayAccountSummary() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ACCOUNT SUMMARY");
        System.out.println("=".repeat(50));
        System.out.println("Bank: " + bankName);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Current Balance: $" + String.format("%.2f", currentBalance));
        System.out.println("Total Income: $" + String.format("%.2f", totalIncome));
        System.out.println("Total Expenses: $" + String.format("%.2f", totalExpenses));
        System.out.println("Net Savings: $" + String.format("%.2f", calculateSavings()));
        
        if (!incomeTransactions.isEmpty()) {
            System.out.println("\nIncome Transactions:");
            for (String transaction : incomeTransactions) {
                System.out.println("  + " + transaction);
            }
        }
        
        if (!expenseTransactions.isEmpty()) {
            System.out.println("\nExpense Transactions:");
            for (String transaction : expenseTransactions) {
                System.out.println("  - " + transaction);
            }
        }
        System.out.println("=".repeat(50));
    }
    
    // Static methods
    public static void setBankName(String name) {
        bankName = name;
        System.out.println("Bank name updated to: " + bankName);
    }
    
    public static int getTotalAccounts() {
        return totalAccounts;
    }
    
    public static String generateAccountNumber() {
        return "ACC" + String.format("%06d", totalAccounts + 1);
    }
    
    // Getter methods for accessing private attributes (optional)
    public String getAccountHolderName() {
        return accountHolderName;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public double getCurrentBalance() {
        return currentBalance;
    }
}

public class PersonalFinanceManager {
    public static void main(String[] args) {
        System.out.println("Personal Finance Management System");
        System.out.println("=".repeat(40));
        
        // Set bank name
        PersonalAccount.setBankName("MyFinance Bank Ltd.");
        
        // Create personal accounts
        PersonalAccount account1 = new PersonalAccount("John Smith");
        PersonalAccount account2 = new PersonalAccount("Sarah Johnson");
        PersonalAccount account3 = new PersonalAccount("Michael Brown");
        
        System.out.println("\nTotal accounts created: " + PersonalAccount.getTotalAccounts());
        
        // Perform transactions for Account 1 (John Smith)
        System.out.println("\n--- Transactions for " + account1.getAccountHolderName() + " ---");
        account1.addIncome(5000.00, "Monthly Salary");
        account1.addIncome(500.00, "Freelance Project");
        account1.addIncome(200.00, "Investment Returns");
        account1.addExpense(1200.00, "Rent");
        account1.addExpense(300.00, "Groceries");
        account1.addExpense(150.00, "Utilities");
        account1.addExpense(100.00, "Transportation");
        
        // Perform transactions for Account 2 (Sarah Johnson)
        System.out.println("\n--- Transactions for " + account2.getAccountHolderName() + " ---");
        account2.addIncome(4500.00, "Monthly Salary");
        account2.addIncome(800.00, "Side Business");
        account2.addIncome(50.00, "Cashback Rewards");
        account2.addExpense(1000.00, "Rent");
        account2.addExpense(400.00, "Groceries");
        account2.addExpense(200.00, "Utilities");
        account2.addExpense(250.00, "Dining Out");
        account2.addExpense(150.00, "Entertainment");
        
        // Perform transactions for Account 3 (Michael Brown)
        System.out.println("\n--- Transactions for " + account3.getAccountHolderName() + " ---");
        account3.addIncome(6000.00, "Monthly Salary");
        account3.addIncome(300.00, "Rental Income");
        account3.addExpense(1500.00, "Mortgage");
        account3.addExpense(350.00, "Groceries");
        account3.addExpense(180.00, "Utilities");
        account3.addExpense(500.00, "Car Payment");
        account3.addExpense(200.00, "Insurance");
        
        // Display account summaries
        System.out.println("\n" + "=".repeat(60));
        System.out.println("DISPLAYING ALL ACCOUNT SUMMARIES");
        System.out.println("=".repeat(60));
        
        account1.displayAccountSummary();
        account2.displayAccountSummary();
        account3.displayAccountSummary();
        
        // Display overall statistics
        System.out.println("\n" + "=".repeat(50));
        System.out.println("OVERALL BANK STATISTICS");
        System.out.println("=".repeat(50));
        System.out.println("Bank Name: " + "MyFinance Bank Ltd.");
        System.out.println("Total Accounts: " + PersonalAccount.getTotalAccounts());
        
        double totalSavings = account1.calculateSavings() + 
                            account2.calculateSavings() + 
                            account3.calculateSavings();
        
        System.out.println("Total Savings Across All Accounts: $" + String.format("%.2f", totalSavings));
        System.out.println("=".repeat(50));
    }
}