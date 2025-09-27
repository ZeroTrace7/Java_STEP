class BankAccount {
    protected String accountNumber;
    protected String customerName;
    protected double balance;
    protected String accountType;
    
    public BankAccount(String accountNumber, String customerName, double balance, String accountType) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.balance = balance;
        this.accountType = accountType;
    }
    
    public void processTransaction(double amount) {
        balance += amount;
        System.out.println("Basic transaction: $" + amount + " | New balance: $" + balance);
    }
    
    public void processTransaction(double amount, String description) {
        balance += amount;
        System.out.println("Transaction: " + description + " | Amount: $" + amount + " | Balance: $" + balance);
    }
    
    public void showAccountInfo() {
        System.out.println("Account: " + accountNumber + " | Customer: " + customerName + 
                          " | Type: " + accountType + " | Balance: $" + balance);
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getAccountType() {
        return accountType;
    }
}

class SavingsAccount extends BankAccount {
    private double interestRate;
    private double withdrawalLimit;
    private int withdrawalsThisMonth;
    
    public SavingsAccount(String accountNumber, String customerName, double balance, double interestRate) {
        super(accountNumber, customerName, balance, "Savings");
        this.interestRate = interestRate;
        this.withdrawalLimit = 500.0;
        this.withdrawalsThisMonth = 0;
    }
    
    public void processTransaction(double amount) {
        if (amount < 0 && Math.abs(amount) > withdrawalLimit) {
            System.out.println("Withdrawal exceeds limit of $" + withdrawalLimit);
            return;
        }
        if (amount < 0) {
            withdrawalsThisMonth++;
        }
        balance += amount;
        System.out.println("Savings transaction: $" + amount + " | Balance: $" + balance);
    }
    
    public void processTransaction(double amount, boolean applyInterest) {
        balance += amount;
        if (applyInterest) {
            double interest = balance * (interestRate / 100) / 12;
            balance += interest;
            System.out.println("Savings with interest: $" + amount + " + $" + interest + " interest | Balance: $" + balance);
        } else {
            System.out.println("Savings transaction: $" + amount + " | Balance: $" + balance);
        }
    }
    
    public void calculateInterest() {
        double interest = balance * (interestRate / 100) / 12;
        balance += interest;
        System.out.println("Monthly interest: $" + interest + " added | New balance: $" + balance);
    }
}

class CheckingAccount extends BankAccount {
    private double overdraftLimit;
    private double monthlyFee;
    private boolean hasOverdraftProtection;
    
    public CheckingAccount(String accountNumber, String customerName, double balance, double overdraftLimit) {
        super(accountNumber, customerName, balance, "Checking");
        this.overdraftLimit = overdraftLimit;
        this.monthlyFee = 12.0;
        this.hasOverdraftProtection = true;
    }
    
    public void processTransaction(double amount) {
        if (amount < 0 && (balance + amount) < -overdraftLimit) {
            System.out.println("Transaction denied: Exceeds overdraft limit of $" + overdraftLimit);
            return;
        }
        balance += amount;
        if (balance < 0) {
            System.out.println("Checking transaction: $" + amount + " | Balance: $" + balance + " (Overdraft)");
        } else {
            System.out.println("Checking transaction: $" + amount + " | Balance: $" + balance);
        }
    }
    
    public void processTransaction(double amount, double fee) {
        balance += amount;
        balance -= fee;
        System.out.println("Checking transaction: $" + amount + " | Fee: $" + fee + " | Balance: $" + balance);
    }
    
    public void chargeMonthlyFee() {
        balance -= monthlyFee;
        System.out.println("Monthly fee: $" + monthlyFee + " charged | Balance: $" + balance);
    }
}

class InvestmentAccount extends BankAccount {
    private String portfolioType;
    private double riskLevel;
    private double portfolioValue;
    
    public InvestmentAccount(String accountNumber, String customerName, double balance, String portfolioType) {
        super(accountNumber, customerName, balance, "Investment");
        this.portfolioType = portfolioType;
        this.riskLevel = portfolioType.equals("Conservative") ? 0.2 : 0.8;
        this.portfolioValue = balance;
    }
    
    public void processTransaction(double amount) {
        balance += amount;
        portfolioValue += amount;
        System.out.println("Investment transaction: $" + amount + " | Portfolio value: $" + portfolioValue);
    }
    
    public void processTransaction(double amount, String investmentType) {
        balance += amount;
        portfolioValue += amount;
        System.out.println("Investment in " + investmentType + ": $" + amount + " | Portfolio: $" + portfolioValue);
    }
    
    public void assessRisk() {
        System.out.println("Portfolio: " + portfolioType + " | Risk level: " + (riskLevel * 100) + "% | Value: $" + portfolioValue);
    }
    
    public void rebalancePortfolio() {
        System.out.println("Portfolio rebalanced for " + portfolioType + " strategy");
    }
}

class BusinessAccount extends BankAccount {
    private double merchantFeeRate;
    private int transactionsThisMonth;
    private boolean hasMerchantServices;
    
    public BusinessAccount(String accountNumber, String customerName, double balance) {
        super(accountNumber, customerName, balance, "Business");
        this.merchantFeeRate = 0.025;
        this.transactionsThisMonth = 0;
        this.hasMerchantServices = true;
    }
    
    public void processTransaction(double amount) {
        balance += amount;
        transactionsThisMonth++;
        System.out.println("Business transaction: $" + amount + " | Balance: $" + balance + 
                          " | Transactions: " + transactionsThisMonth);
    }
    
    public void processTransaction(double[] amounts) {
        double total = 0;
        for (int i = 0; i < amounts.length; i++) {
            total += amounts[i];
        }
        balance += total;
        transactionsThisMonth += amounts.length;
        System.out.println("Bulk transaction: " + amounts.length + " transactions | Total: $" + total + 
                          " | Balance: $" + balance);
    }
    
    public void processMerchantPayment(double amount) {
        double fee = amount * merchantFeeRate;
        balance += (amount - fee);
        System.out.println("Merchant payment: $" + amount + " | Fee: $" + fee + " | Net: $" + (amount - fee));
    }
}

public class BankingSystemApp {
    
    public static void processAccount(BankAccount account) {
        System.out.println("Processing account: " + account.getAccountNumber());
        account.showAccountInfo();
        account.processTransaction(100.0, "Standard deposit");
        System.out.println();
    }
    
    public static void performSpecializedOperations(BankAccount account) {
        if (account instanceof SavingsAccount) {
            SavingsAccount savings = (SavingsAccount) account;
            savings.calculateInterest();
            savings.processTransaction(200.0, true);
        }
        else if (account instanceof CheckingAccount) {
            CheckingAccount checking = (CheckingAccount) account;
            checking.chargeMonthlyFee();
            checking.processTransaction(-50.0, 2.5);
        }
        else if (account instanceof InvestmentAccount) {
            InvestmentAccount investment = (InvestmentAccount) account;
            investment.assessRisk();
            investment.processTransaction(500.0, "Tech Stocks");
        }
        else if (account instanceof BusinessAccount) {
            BusinessAccount business = (BusinessAccount) account;
            double[] bulkAmounts = {150.0, 200.0, 75.0};
            business.processTransaction(bulkAmounts);
            business.processMerchantPayment(300.0);
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        System.out.println("Banking Transaction System");
        System.out.println();
        
        BankAccount[] accounts = new BankAccount[4];
        
        accounts[0] = new SavingsAccount("SAV001", "John Smith", 5000, 2.5);
        accounts[1] = new CheckingAccount("CHK001", "Jane Doe", 1500, 500);
        accounts[2] = new InvestmentAccount("INV001", "Bob Johnson", 10000, "Conservative");
        accounts[3] = new BusinessAccount("BUS001", "ABC Corp", 25000);
        
        System.out.println("Account Processing (Upcasting):");
        for (int i = 0; i < accounts.length; i++) {
            processAccount(accounts[i]);
        }
        
        System.out.println("Specialized Operations (Safe Downcasting):");
        for (int i = 0; i < accounts.length; i++) {
            performSpecializedOperations(accounts[i]);
        }
        
        System.out.println("Multiple Polymorphism Integration:");
        System.out.println("- Method Overloading: Different transaction methods");
        System.out.println("- Method Overriding: Account-specific transaction handling");
        System.out.println("- Dynamic Dispatch: Runtime method selection");
        System.out.println("- Upcasting: Unified account processing");
        System.out.println("- Safe Downcasting: Specialized operations");
    }
}