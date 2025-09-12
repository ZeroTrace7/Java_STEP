public class SecureBankAccount {
    
    private String accountNumber;
    private double balance;
    private int pin;
    private boolean isLocked;
    private int failedAttempts;
    
    private static final int MAX_FAILED_ATTEMPTS = 3;
    private static final double MIN_BALANCE = 0.0;
    
    public SecureBankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.pin = 0;
        this.isLocked = false;
        this.failedAttempts = 0;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public double getBalance() {
        if (isLocked) {
            System.out.println("Account is locked. Cannot check balance.");
            return -1;
        }
        return balance;
    }
    
    public boolean isAccountLocked() {
        return isLocked;
    }
    
    public boolean setPin(int oldPin, int newPin) {
        if (isLocked) {
            System.out.println("Account is locked. Cannot change PIN.");
            return false;
        }
        
        if (this.pin == oldPin) {
            this.pin = newPin;
            resetFailedAttempts();
            System.out.println("PIN changed successfully.");
            return true;
        } else {
            incrementFailedAttempts();
            System.out.println("Incorrect old PIN. PIN change failed.");
            return false;
        }
    }
    
    public boolean validatePin(int enteredPin) {
        if (isLocked) {
            System.out.println("Account is locked. Cannot validate PIN.");
            return false;
        }
        
        if (this.pin == enteredPin) {
            resetFailedAttempts();
            return true;
        } else {
            incrementFailedAttempts();
            return false;
        }
    }
    
    public boolean unlockAccount(int correctPin) {
        if (this.pin == correctPin) {
            this.isLocked = false;
            resetFailedAttempts();
            System.out.println("Account unlocked successfully.");
            return true;
        } else {
            System.out.println("Incorrect PIN. Account remains locked.");
            return false;
        }
    }
    
    public boolean deposit(double amount, int pin) {
        if (isLocked) {
            System.out.println("Account is locked. Cannot deposit.");
            return false;
        }
        
        if (!validatePin(pin)) {
            System.out.println("Invalid PIN. Deposit failed.");
            return false;
        }
        
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return false;
        }
        
        balance += amount;
        System.out.println("Deposited: $" + amount + ". New balance: $" + balance);
        return true;
    }
    
    public boolean withdraw(double amount, int pin) {
        if (isLocked) {
            System.out.println("Account is locked. Cannot withdraw.");
            return false;
        }
        
        if (!validatePin(pin)) {
            System.out.println("Invalid PIN. Withdrawal failed.");
            return false;
        }
        
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        }
        
        if (balance - amount < MIN_BALANCE) {
            System.out.println("Insufficient funds. Withdrawal failed.");
            return false;
        }
        
        balance -= amount;
        System.out.println("Withdrew: $" + amount + ". New balance: $" + balance);
        return true;
    }
    
    public boolean transfer(SecureBankAccount target, double amount, int pin) {
        if (this.withdraw(amount, pin)) {
            target.balance += amount;
            System.out.println("Transferred $" + amount + " to account " + target.getAccountNumber());
            return true;
        } else {
            System.out.println("Transfer failed.");
            return false;
        }
    }
    
    private void lockAccount() {
        this.isLocked = true;
        System.out.println("Account locked due to security breach.");
    }
    
    private void resetFailedAttempts() {
        this.failedAttempts = 0;
    }
    
    private void incrementFailedAttempts() {
        this.failedAttempts++;
        System.out.println("Failed attempts: " + failedAttempts);
        
        if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
            lockAccount();
        }
    }
    
    public static void main(String[] args) {
        SecureBankAccount account1 = new SecureBankAccount("ACC001", 1000.0);
        SecureBankAccount account2 = new SecureBankAccount("ACC002", 500.0);
        
        System.out.println("=== Testing Proper Usage ===");
        
        account1.setPin(0, 1234);
        account2.setPin(0, 5678);
        
        System.out.println("Account 1 Balance: $" + account1.getBalance());
        System.out.println("Account 2 Balance: $" + account2.getBalance());
        
        account1.deposit(200.0, 1234);
        account1.withdraw(100.0, 1234);
        
        System.out.println("\n=== Testing Transfer ===");
        account1.transfer(account2, 150.0, 1234);
        System.out.println("Account 2 Balance after transfer: $" + account2.getBalance());
        
        System.out.println("\n=== Testing Security Breaches ===");
        
        account1.withdraw(50.0, 9999);
        account1.withdraw(50.0, 8888);
        account1.withdraw(50.0, 7777);
        
        System.out.println("Account 1 locked: " + account1.isAccountLocked());
        
        System.out.println("\n=== Testing Locked Account Operations ===");
        account1.deposit(100.0, 1234);
        account1.getBalance();
        
        System.out.println("\n=== Testing Account Unlock ===");
        account1.unlockAccount(1234);
        System.out.println("Account 1 locked: " + account1.isAccountLocked());
        
        System.out.println("\n=== Testing Insufficient Funds ===");
        account2.withdraw(10000.0, 5678);
    }
}