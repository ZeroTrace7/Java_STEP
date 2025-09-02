import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Arrays;

// Base Employee class
class Employee {
    // Instance variables
    protected String empId;
    protected String empName;
    protected String department;
    protected String designation;
    protected double baseSalary;
    protected String joinDate;
    protected boolean[] attendanceRecord;
    
    // Static variables
    protected static int totalEmployees = 0;
    protected static String companyName = "TechCorp Solutions";
    protected static double totalSalaryExpense = 0.0;
    protected static int workingDaysPerMonth = 22;
    
    // Constructor
    public Employee(String empId, String empName, String department, 
                   String designation, double baseSalary, String joinDate) {
        this.empId = empId;
        this.empName = empName;
        this.department = department;
        this.designation = designation;
        this.baseSalary = baseSalary;
        this.joinDate = joinDate;
        this.attendanceRecord = new boolean[30];
        totalEmployees++;
        totalSalaryExpense += baseSalary;
    }
    
    // Mark attendance for a specific day
    public void markAttendance(int day, boolean present) {
        if (day >= 1 && day <= 30) {
            attendanceRecord[day - 1] = present;
            System.out.println("Attendance marked for " + empName + 
                             " on day " + day + ": " + (present ? "Present" : "Absent"));
        } else {
            System.out.println("Invalid day. Please enter a day between 1-30.");
        }
    }
    
    // Calculate attendance percentage
    public double calculateAttendancePercentage() {
        int presentDays = 0;
        for (boolean present : attendanceRecord) {
            if (present) presentDays++;
        }
        return (double) presentDays / workingDaysPerMonth * 100;
    }
    
    // Calculate salary (to be overridden by subclasses)
    public double calculateSalary() {
        double attendancePercentage = calculateAttendancePercentage();
        double salary = baseSalary * (attendancePercentage / 100);
        return Math.round(salary * 100.0) / 100.0; // Round to 2 decimal places
    }
    
    // Calculate bonus based on attendance and performance
    public double calculateBonus() {
        double attendancePercentage = calculateAttendancePercentage();
        double bonus = 0.0;
        
        if (attendancePercentage >= 95) {
            bonus = baseSalary * 0.15; // 15% bonus for excellent attendance
        } else if (attendancePercentage >= 85) {
            bonus = baseSalary * 0.10; // 10% bonus for good attendance
        } else if (attendancePercentage >= 75) {
            bonus = baseSalary * 0.05; // 5% bonus for fair attendance
        }
        
        return Math.round(bonus * 100.0) / 100.0;
    }
    
    // Generate pay slip
    public void generatePaySlip() {
        double salary = calculateSalary();
        double bonus = calculateBonus();
        double totalPay = salary + bonus;
        double tax = totalPay * 0.10; // 10% tax
        double netPay = totalPay - tax;
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("PAY SLIP - " + companyName);
        System.out.println("=".repeat(50));
        System.out.println("Employee ID: " + empId);
        System.out.println("Name: " + empName);
        System.out.println("Department: " + department);
        System.out.println("Designation: " + designation);
        System.out.println("Join Date: " + joinDate);
        System.out.println("-".repeat(50));
        System.out.println("Base Salary: $" + String.format("%.2f", baseSalary));
        System.out.println("Attendance: " + String.format("%.1f", calculateAttendancePercentage()) + "%");
        System.out.println("Calculated Salary: $" + String.format("%.2f", salary));
        System.out.println("Bonus: $" + String.format("%.2f", bonus));
        System.out.println("Gross Pay: $" + String.format("%.2f", totalPay));
        System.out.println("Tax (10%): $" + String.format("%.2f", tax));
        System.out.println("NET PAY: $" + String.format("%.2f", netPay));
        System.out.println("=".repeat(50));
    }
    
    // Request leave
    public void requestLeave(int startDay, int endDay, String reason) {
        if (startDay >= 1 && startDay <= 30 && endDay >= startDay && endDay <= 30) {
            System.out.println("\nLeave Request for " + empName + ":");
            System.out.println("Days: " + startDay + " to " + endDay);
            System.out.println("Reason: " + reason);
            System.out.println("Status: Pending Approval");
            
            // Mark leave days as absent
            for (int i = startDay - 1; i < endDay; i++) {
                attendanceRecord[i] = false;
            }
        } else {
            System.out.println("Invalid leave dates.");
        }
    }
    
    // Getters
    public String getEmpId() { return empId; }
    public String getEmpName() { return empName; }
    public String getDepartment() { return department; }
    public String getDesignation() { return designation; }
    public double getBaseSalary() { return baseSalary; }
    public String getJoinDate() { return joinDate; }
    
    // Static getters
    public static int getTotalEmployees() { return totalEmployees; }
    public static String getCompanyName() { return companyName; }
    public static double getTotalSalaryExpense() { return totalSalaryExpense; }
    public static int getWorkingDaysPerMonth() { return workingDaysPerMonth; }
    
    @Override
    public String toString() {
        return "Employee{" +
                "empId='" + empId + '\'' +
                ", empName='" + empName + '\'' +
                ", department='" + department + '\'' +
                ", designation='" + designation + '\'' +
                ", baseSalary=" + baseSalary +
                ", joinDate='" + joinDate + '\'' +
                '}';
    }
}

// Full-time Employee class
class FullTimeEmployee extends Employee {
    private double healthInsurance;
    private double retirementContribution;
    
    public FullTimeEmployee(String empId, String empName, String department,
                           String designation, double baseSalary, String joinDate) {
        super(empId, empName, department, designation, baseSalary, joinDate);
        this.healthInsurance = 200.0; // Monthly health insurance
        this.retirementContribution = baseSalary * 0.05; // 5% retirement contribution
    }
    
    @Override
    public double calculateSalary() {
        double basePay = super.calculateSalary();
        return basePay + healthInsurance + retirementContribution;
    }
    
    @Override
    public void generatePaySlip() {
        double salary = super.calculateSalary();
        double bonus = calculateBonus();
        double totalPay = salary + bonus + healthInsurance + retirementContribution;
        double tax = totalPay * 0.12; // 12% tax for full-time
        double netPay = totalPay - tax;
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("PAY SLIP - " + getCompanyName() + " (FULL-TIME)");
        System.out.println("=".repeat(50));
        System.out.println("Employee ID: " + empId);
        System.out.println("Name: " + empName);
        System.out.println("Department: " + department);
        System.out.println("Designation: " + designation);
        System.out.println("-".repeat(50));
        System.out.println("Base Salary: $" + String.format("%.2f", baseSalary));
        System.out.println("Attendance Salary: $" + String.format("%.2f", salary));
        System.out.println("Health Insurance: $" + String.format("%.2f", healthInsurance));
        System.out.println("Retirement: $" + String.format("%.2f", retirementContribution));
        System.out.println("Bonus: $" + String.format("%.2f", bonus));
        System.out.println("Gross Pay: $" + String.format("%.2f", totalPay));
        System.out.println("Tax (12%): $" + String.format("%.2f", tax));
        System.out.println("NET PAY: $" + String.format("%.2f", netPay));
        System.out.println("=".repeat(50));
    }
}

// Part-time Employee class
class PartTimeEmployee extends Employee {
    private double hourlyRate;
    private int hoursWorked;
    
    public PartTimeEmployee(String empId, String empName, String department,
                           String designation, double hourlyRate, String joinDate) {
        super(empId, empName, department, designation, hourlyRate * 80, joinDate); // Assuming 80 hours per month
        this.hourlyRate = hourlyRate;
        this.hoursWorked = 0;
    }
    
    public void logHours(int hours) {
        this.hoursWorked += hours;
        System.out.println("Logged " + hours + " hours for " + empName + 
                         ". Total hours: " + hoursWorked);
    }
    
    @Override
    public double calculateSalary() {
        double attendancePercentage = calculateAttendancePercentage();
        return (hourlyRate * hoursWorked) * (attendancePercentage / 100);
    }
    
    @Override
    public void generatePaySlip() {
        double salary = calculateSalary();
        double bonus = calculateBonus() * 0.5; // Reduced bonus for part-time
        double totalPay = salary + bonus;
        double tax = totalPay * 0.08; // 8% tax for part-time
        double netPay = totalPay - tax;
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("PAY SLIP - " + getCompanyName() + " (PART-TIME)");
        System.out.println("=".repeat(50));
        System.out.println("Employee ID: " + empId);
        System.out.println("Name: " + empName);
        System.out.println("Department: " + department);
        System.out.println("Designation: " + designation);
        System.out.println("-".repeat(50));
        System.out.println("Hourly Rate: $" + String.format("%.2f", hourlyRate));
        System.out.println("Hours Worked: " + hoursWorked);
        System.out.println("Calculated Salary: $" + String.format("%.2f", salary));
        System.out.println("Bonus: $" + String.format("%.2f", bonus));
        System.out.println("Gross Pay: $" + String.format("%.2f", totalPay));
        System.out.println("Tax (8%): $" + String.format("%.2f", tax));
        System.out.println("NET PAY: $" + String.format("%.2f", netPay));
        System.out.println("=".repeat(50));
    }
}

// Contract Employee class
class ContractEmployee extends Employee {
    private String contractEndDate;
    private double contractBonus;
    
    public ContractEmployee(String empId, String empName, String department,
                           String designation, double baseSalary, String joinDate, 
                           String contractEndDate) {
        super(empId, empName, department, designation, baseSalary, joinDate);
        this.contractEndDate = contractEndDate;
        this.contractBonus = baseSalary * 0.20; // 20% contract completion bonus
    }
    
    @Override
    public double calculateBonus() {
        double regularBonus = super.calculateBonus();
        return regularBonus + contractBonus;
    }
    
    @Override
    public void generatePaySlip() {
        double salary = super.calculateSalary();
        double bonus = calculateBonus();
        double totalPay = salary + bonus;
        double tax = totalPay * 0.15; // 15% tax for contractors
        double netPay = totalPay - tax;
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("PAY SLIP - " + getCompanyName() + " (CONTRACT)");
        System.out.println("=".repeat(50));
        System.out.println("Employee ID: " + empId);
        System.out.println("Name: " + empName);
        System.out.println("Department: " + department);
        System.out.println("Designation: " + designation);
        System.out.println("Contract End: " + contractEndDate);
        System.out.println("-".repeat(50));
        System.out.println("Base Salary: $" + String.format("%.2f", baseSalary));
        System.out.println("Calculated Salary: $" + String.format("%.2f", salary));
        System.out.println("Performance Bonus: $" + String.format("%.2f", super.calculateBonus()));
        System.out.println("Contract Bonus: $" + String.format("%.2f", contractBonus));
        System.out.println("Total Bonus: $" + String.format("%.2f", bonus));
        System.out.println("Gross Pay: $" + String.format("%.2f", totalPay));
        System.out.println("Tax (15%): $" + String.format("%.2f", tax));
        System.out.println("NET PAY: $" + String.format("%.2f", netPay));
        System.out.println("=".repeat(50));
    }
}

// Department class
class Department {
    private String deptId;
    private String deptName;
    private Employee manager;
    private Employee[] employees;
    private double budget;
    private int employeeCount;
    
    public Department(String deptId, String deptName, double budget, int maxEmployees) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.budget = budget;
        this.employees = new Employee[maxEmployees];
        this.employeeCount = 0;
    }
    
    // Add employee to department
    public boolean addEmployee(Employee employee) {
        if (employeeCount < employees.length) {
            employees[employeeCount] = employee;
            employeeCount++;
            System.out.println("Employee " + employee.getEmpName() + 
                             " added to " + deptName + " department.");
            return true;
        } else {
            System.out.println("Cannot add employee. Department is at maximum capacity.");
            return false;
        }
    }
    
    // Set manager
    public void setManager(Employee manager) {
        this.manager = manager;
        System.out.println(manager.getEmpName() + " is now the manager of " + deptName);
    }
    
    // Calculate total department salary expense
    public double calculateTotalSalaryExpense() {
        double totalExpense = 0;
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i] != null) {
                totalExpense += employees[i].calculateSalary();
            }
        }
        return totalExpense;
    }
    
    // Generate department report
    public void generateDepartmentReport() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("DEPARTMENT REPORT - " + deptName.toUpperCase());
        System.out.println("=".repeat(60));
        System.out.println("Department ID: " + deptId);
        System.out.println("Budget: $" + String.format("%.2f", budget));
        System.out.println("Manager: " + (manager != null ? manager.getEmpName() : "Not assigned"));
        System.out.println("Total Employees: " + employeeCount);
        System.out.println("-".repeat(60));
        
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i] != null) {
                Employee emp = employees[i];
                System.out.println(emp.getEmpId() + " | " + emp.getEmpName() + 
                                 " | " + emp.getDesignation() + 
                                 " | Attendance: " + String.format("%.1f", emp.calculateAttendancePercentage()) + "%");
            }
        }
        
        System.out.println("-".repeat(60));
        System.out.println("Total Salary Expense: $" + String.format("%.2f", calculateTotalSalaryExpense()));
        System.out.println("Remaining Budget: $" + String.format("%.2f", budget - calculateTotalSalaryExpense()));
        System.out.println("=".repeat(60));
    }
    
    // Getters
    public String getDeptId() { return deptId; }
    public String getDeptName() { return deptName; }
    public Employee getManager() { return manager; }
    public Employee[] getEmployees() { return employees; }
    public double getBudget() { return budget; }
    public int getEmployeeCount() { return employeeCount; }
}

// Main class to demonstrate the system
public class PayrollSystem {
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("EMPLOYEE PAYROLL AND ATTENDANCE SYSTEM");
        System.out.println("Company: " + Employee.getCompanyName());
        System.out.println("=".repeat(60));
        
        // Create departments
        Department itDept = new Department("IT001", "Information Technology", 500000, 10);
        Department hrDept = new Department("HR001", "Human Resources", 200000, 5);
        
        // Create different types of employees
        FullTimeEmployee emp1 = new FullTimeEmployee("FT001", "John Smith", "IT", 
                                                    "Software Engineer", 8000, "2023-01-15");
        PartTimeEmployee emp2 = new PartTimeEmployee("PT001", "Sarah Johnson", "IT", 
                                                    "UI Designer", 50, "2023-03-01");
        ContractEmployee emp3 = new ContractEmployee("CT001", "Mike Wilson", "IT", 
                                                    "Project Manager", 10000, "2023-06-01", "2024-12-01");
        FullTimeEmployee emp4 = new FullTimeEmployee("FT002", "Emily Davis", "HR", 
                                                    "HR Specialist", 6000, "2023-02-01");
        
        // Add employees to departments
        itDept.addEmployee(emp1);
        itDept.addEmployee(emp2);
        itDept.addEmployee(emp3);
        itDept.setManager(emp3); // Project manager as department manager
        
        hrDept.addEmployee(emp4);
        hrDept.setManager(emp4);
        
        System.out.println("\nTotal Employees in Company: " + Employee.getTotalEmployees());
        
        // Simulate attendance for employees
        System.out.println("\n--- MARKING ATTENDANCE ---");
        
        // Mark attendance for John (excellent attendance)
        for (int i = 1; i <= 22; i++) {
            emp1.markAttendance(i, true);
        }
        
        // Mark attendance for Sarah (good attendance with some absences)
        for (int i = 1; i <= 22; i++) {
            emp2.markAttendance(i, i % 5 != 0); // Absent every 5th day
        }
        emp2.logHours(120); // Log working hours
        
        // Mark attendance for Mike (fair attendance)
        for (int i = 1; i <= 22; i++) {
            emp3.markAttendance(i, i % 3 != 0); // Absent every 3rd day
        }
        
        // Mark attendance for Emily (excellent attendance)
        for (int i = 1; i <= 22; i++) {
            emp4.markAttendance(i, true);
        }
        
        // Request leave
        System.out.println("\n--- LEAVE REQUESTS ---");
        emp1.requestLeave(25, 27, "Personal vacation");
        emp4.requestLeave(28, 30, "Medical leave");
        
        // Generate pay slips
        System.out.println("\n--- GENERATING PAY SLIPS ---");
        emp1.generatePaySlip();
        emp2.generatePaySlip();
        emp3.generatePaySlip();
        emp4.generatePaySlip();
        
        // Generate department reports
        System.out.println("\n--- DEPARTMENT REPORTS ---");
        itDept.generateDepartmentReport();
        hrDept.generateDepartmentReport();
        
        // Company summary
        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPANY SUMMARY");
        System.out.println("=".repeat(60));
        System.out.println("Company: " + Employee.getCompanyName());
        System.out.println("Total Employees: " + Employee.getTotalEmployees());
        System.out.println("Working Days per Month: " + Employee.getWorkingDaysPerMonth());
        System.out.println("Total Base Salary Expense: $" + 
                         String.format("%.2f", Employee.getTotalSalaryExpense()));
        System.out.println("=".repeat(60));
    }
}