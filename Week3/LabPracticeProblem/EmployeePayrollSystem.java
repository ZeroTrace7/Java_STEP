class Employee {
    private String empId;
    private String empName;
    private String department;
    private double baseSalary;
    private String empType;
    private static int totalEmployees = 0;
    
    private double hourlyRate;
    private int hoursWorked;
    private double bonus;
    private double contractAmount;
    
    public Employee(String empName, String department, double baseSalary, double bonus) {
        totalEmployees++;
        this.empId = generateEmpId();
        this.empName = empName;
        this.department = department;
        this.baseSalary = baseSalary;
        this.bonus = bonus;
        this.empType = "Full-Time";
        
        System.out.println("Full-time employee added: " + empName);
    }
    
    public Employee(String empName, String department, double hourlyRate, int hoursWorked) {
        totalEmployees++;
        this.empId = generateEmpId();
        this.empName = empName;
        this.department = department;
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
        this.empType = "Part-Time";
        
        System.out.println("Part-time employee added: " + empName);
    }
    
    public Employee(String empName, String department, double contractAmount) {
        totalEmployees++;
        this.empId = generateEmpId();
        this.empName = empName;
        this.department = department;
        this.contractAmount = contractAmount;
        this.empType = "Contract";
        
        System.out.println("Contract employee added: " + empName);
    }
    
    private String generateEmpId() {
        return String.format("EMP%03d", totalEmployees);
    }
    
    public double calculateSalary() {
        if (empType.equals("Full-Time")) {
            return baseSalary + bonus;
        } else if (empType.equals("Part-Time")) {
            return hourlyRate * hoursWorked;
        } else {
            return contractAmount;
        }
    }
    
    public double calculateSalary(double extraBonus) {
        if (empType.equals("Full-Time")) {
            return baseSalary + bonus + extraBonus;
        } else if (empType.equals("Part-Time")) {
            return hourlyRate * hoursWorked + extraBonus;
        } else {
            return contractAmount + extraBonus;
        }
    }
    
    public double calculateSalary(int overtimeHours, double overtimeRate) {
        if (empType.equals("Full-Time")) {
            return baseSalary + bonus + (overtimeHours * overtimeRate);
        } else if (empType.equals("Part-Time")) {
            return hourlyRate * hoursWorked + (overtimeHours * overtimeRate);
        } else {
            return contractAmount + (overtimeHours * overtimeRate);
        }
    }
    
    public double calculateTax() {
        double salary = calculateSalary();
        if (empType.equals("Full-Time")) {
            return salary * 0.15;
        } else if (empType.equals("Part-Time")) {
            return salary * 0.10;
        } else {
            return salary * 0.12;
        }
    }
    
    public double calculateTax(double customTaxRate) {
        double salary = calculateSalary();
        return salary * customTaxRate;
    }
    
    public double calculateTax(boolean isHighEarner) {
        double salary = calculateSalary();
        if (isHighEarner) {
            if (empType.equals("Full-Time")) {
                return salary * 0.20;
            } else if (empType.equals("Part-Time")) {
                return salary * 0.15;
            } else {
                return salary * 0.18;
            }
        } else {
            return calculateTax();
        }
    }
    
    public void generatePaySlip() {
        double grossSalary = calculateSalary();
        double tax = calculateTax();
        double netSalary = grossSalary - tax;
        
        System.out.println("\n========== PAY SLIP ==========");
        System.out.println("Employee ID: " + empId);
        System.out.println("Name: " + empName);
        System.out.println("Department: " + department);
        System.out.println("Employee Type: " + empType);
        System.out.println("------------------------------");
        System.out.println("Gross Salary: $" + String.format("%.2f", grossSalary));
        System.out.println("Tax Deducted: $" + String.format("%.2f", tax));
        System.out.println("Net Salary: $" + String.format("%.2f", netSalary));
        System.out.println("==============================");
    }
    
    public void generatePaySlip(double extraBonus) {
        double grossSalary = calculateSalary(extraBonus);
        double tax = calculateTax();
        double netSalary = grossSalary - tax;
        
        System.out.println("\n========== PAY SLIP ==========");
        System.out.println("Employee ID: " + empId);
        System.out.println("Name: " + empName);
        System.out.println("Department: " + department);
        System.out.println("Employee Type: " + empType);
        System.out.println("------------------------------");
        System.out.println("Gross Salary: $" + String.format("%.2f", grossSalary));
        System.out.println("Extra Bonus: $" + String.format("%.2f", extraBonus));
        System.out.println("Tax Deducted: $" + String.format("%.2f", tax));
        System.out.println("Net Salary: $" + String.format("%.2f", netSalary));
        System.out.println("==============================");
    }
    
    public void displayEmployeeInfo() {
        System.out.println("\n--- Employee Information ---");
        System.out.println("Employee ID: " + empId);
        System.out.println("Name: " + empName);
        System.out.println("Department: " + department);
        System.out.println("Type: " + empType);
        
        if (empType.equals("Full-Time")) {
            System.out.println("Base Salary: $" + String.format("%.2f", baseSalary));
            System.out.println("Bonus: $" + String.format("%.2f", bonus));
        } else if (empType.equals("Part-Time")) {
            System.out.println("Hourly Rate: $" + String.format("%.2f", hourlyRate));
            System.out.println("Hours Worked: " + hoursWorked);
        } else {
            System.out.println("Contract Amount: $" + String.format("%.2f", contractAmount));
        }
        
        System.out.println("Total Salary: $" + String.format("%.2f", calculateSalary()));
        System.out.println("---------------------------");
    }
    
    public static int getTotalEmployees() {
        return totalEmployees;
    }
    
    public String getEmpType() {
        return empType;
    }
    
    public String getEmpName() {
        return empName;
    }
}

public class EmployeePayrollSystem {
    public static void main(String[] args) {
        System.out.println("Welcome to Company Payroll Management System!");
        System.out.println("Let's add some employees...");
        
        System.out.println("\nCreating different types of employees:");
        
        Employee john = new Employee("John Smith", "Engineering", 5000.0, 1000.0);
        Employee sarah = new Employee("Sarah Johnson", "Marketing", 25.0, 80);
        Employee mike = new Employee("Mike Brown", "IT Consulting", 8000.0);
        Employee lisa = new Employee("Lisa Wilson", "HR", 4500.0, 500.0);
        Employee alex = new Employee("Alex Davis", "Design", 30.0, 60);
        
        System.out.println("\nDisplaying all employee information:");
        john.displayEmployeeInfo();
        sarah.displayEmployeeInfo();
        mike.displayEmployeeInfo();
        lisa.displayEmployeeInfo();
        alex.displayEmployeeInfo();
        
        System.out.println("\nGenerating regular pay slips:");
        john.generatePaySlip();
        sarah.generatePaySlip();
        mike.generatePaySlip();
        
        System.out.println("\nTesting salary calculation with extra bonus:");
        System.out.println("John's salary with $500 extra bonus: $" + 
                          String.format("%.2f", john.calculateSalary(500.0)));
        System.out.println("Sarah's salary with $200 extra bonus: $" + 
                          String.format("%.2f", sarah.calculateSalary(200.0)));
        
        System.out.println("\nTesting salary calculation with overtime:");
        System.out.println("John's salary with 10 overtime hours at $50/hour: $" + 
                          String.format("%.2f", john.calculateSalary(10, 50.0)));
        System.out.println("Alex's salary with 5 overtime hours at $40/hour: $" + 
                          String.format("%.2f", alex.calculateSalary(5, 40.0)));
        
        System.out.println("\nTesting different tax calculations:");
        System.out.println("Lisa's regular tax: $" + String.format("%.2f", lisa.calculateTax()));
        System.out.println("Lisa's tax with custom 18% rate: $" + 
                          String.format("%.2f", lisa.calculateTax(0.18)));
        System.out.println("Lisa's tax as high earner: $" + 
                          String.format("%.2f", lisa.calculateTax(true)));
        
        System.out.println("\nGenerating pay slip with extra bonus:");
        lisa.generatePaySlip(750.0);
        
        System.out.println("\nTesting all employee types with different scenarios:");
        
        System.out.println("\nFull-time employee with overtime:");
        Employee fullTimer = new Employee("David Lee", "Sales", 4000.0, 800.0);
        System.out.println("Regular salary: $" + String.format("%.2f", fullTimer.calculateSalary()));
        System.out.println("With overtime: $" + String.format("%.2f", fullTimer.calculateSalary(15, 45.0)));
        
        System.out.println("\nPart-time employee scenarios:");
        Employee partTimer = new Employee("Emma White", "Customer Service", 22.0, 75);
        System.out.println("Regular salary: $" + String.format("%.2f", partTimer.calculateSalary()));
        System.out.println("With bonus: $" + String.format("%.2f", partTimer.calculateSalary(300.0)));
        
        System.out.println("\nContract employee scenarios:");
        Employee contractor = new Employee("Robert Green", "Consulting", 12000.0);
        System.out.println("Contract amount: $" + String.format("%.2f", contractor.calculateSalary()));
        System.out.println("With additional payment: $" + String.format("%.2f", contractor.calculateSalary(2000.0)));
        
        System.out.println("\nFinal company statistics:");
        System.out.println("Total employees in system: " + Employee.getTotalEmployees());
        
        System.out.println("\nPayroll processing complete!");
    }
}