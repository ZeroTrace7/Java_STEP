import java.util.Scanner;

// Employee class demonstrating OOP concepts
class Employee {
    // Static variables - shared across all Employee objects
    private static String companyName = "Default Company";
    private static int totalEmployees = 0;
    
    // Instance variables - unique to each Employee object
    private int empId;
    private String name;
    private String department;
    private double salary;
    
    // Default constructor
    public Employee() {
        this.empId = 0;
        this.name = "Unknown";
        this.department = "Unassigned";
        this.salary = 0.0;
        totalEmployees++;
    }
    
    // Parameterized constructor
    public Employee(int empId, String name, String department, double salary) {
        this.empId = empId;
        this.name = name;
        this.department = department;
        this.salary = salary;
        totalEmployees++;
    }
    
    // Static methods - can be called without creating objects
    public static void setCompanyName(String name) {
        companyName = name;
    }
    
    public static String getCompanyName() {
        return companyName;
    }
    
    public static int getTotalEmployees() {
        return totalEmployees;
    }
    
    // Instance methods - require object to be called
    public double calculateAnnualSalary() {
        return salary * 12;
    }
    
    public void displayEmployee() {
        System.out.println("Employee ID: " + empId);
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Monthly Salary: $" + String.format("%.2f", salary));
        System.out.println("Annual Salary: $" + String.format("%.2f", calculateAnnualSalary()));
        System.out.println("------------------------");
    }
    
    public void updateSalary(double newSalary) {
        this.salary = newSalary;
        System.out.println("Salary updated successfully for " + name);
    }
    
    // Getter methods for encapsulation
    public int getEmpId() { return empId; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    
    // Setter methods
    public void setEmpId(int empId) { this.empId = empId; }
    public void setName(String name) { this.name = name; }
    public void setDepartment(String department) { this.department = department; }
    public void setSalary(double salary) { this.salary = salary; }
}

// Department class to manage employees
class Department {
    private String deptName;
    private Employee[] employees;
    private int employeeCount;
    private final int MAX_EMPLOYEES = 50;
    
    // Constructor
    public Department(String deptName) {
        this.deptName = deptName;
        this.employees = new Employee[MAX_EMPLOYEES];
        this.employeeCount = 0;
    }
    
    // Method to add employee to department
    public boolean addEmployee(Employee emp) {
        if (employeeCount < MAX_EMPLOYEES) {
            employees[employeeCount] = emp;
            employeeCount++;
            System.out.println("Employee " + emp.getName() + " added to " + deptName + " department");
            return true;
        } else {
            System.out.println("Department is full! Cannot add more employees.");
            return false;
        }
    }
    
    // Method to find highest paid employee in department
    public Employee findHighestPaid() {
        if (employeeCount == 0) {
            return null;
        }
        
        Employee highest = employees[0];
        for (int i = 1; i < employeeCount; i++) {
            if (employees[i].getSalary() > highest.getSalary()) {
                highest = employees[i];
            }
        }
        return highest;
    }
    
    // Method to calculate total payroll for department
    public double calculateTotalPayroll() {
        double totalPayroll = 0;
        for (int i = 0; i < employeeCount; i++) {
            totalPayroll += employees[i].calculateAnnualSalary();
        }
        return totalPayroll;
    }
    
    // Method to display all employees in department
    public void displayDepartmentEmployees() {
        System.out.println("\n=== " + deptName + " Department ===");
        System.out.println("Total Employees: " + employeeCount);
        System.out.println("------------------------");
        
        if (employeeCount == 0) {
            System.out.println("No employees in this department.");
            return;
        }
        
        for (int i = 0; i < employeeCount; i++) {
            employees[i].displayEmployee();
        }
        
        System.out.println("Department Annual Payroll: $" + String.format("%.2f", calculateTotalPayroll()));
        
        Employee highestPaid = findHighestPaid();
        if (highestPaid != null) {
            System.out.println("Highest Paid Employee: " + highestPaid.getName() + 
                             " ($" + String.format("%.2f", highestPaid.getSalary()) + "/month)");
        }
        System.out.println("========================\n");
    }
    
    // Getter methods
    public String getDeptName() { return deptName; }
    public int getEmployeeCount() { return employeeCount; }
    public Employee[] getEmployees() { return employees; }
}

// Main application class
public class EmployeeManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Department[] departments = new Department[10];
    private static int departmentCount = 0;
    
    public static void main(String[] args) {
        System.out.println("=== Employee Management System ===");
        
        // Initialize the system
        initializeSystem();
        
        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    setCompanyName();
                    break;
                case 2:
                    createDepartment();
                    break;
                case 3:
                    addEmployee();
                    break;
                case 4:
                    displayAllDepartments();
                    break;
                case 5:
                    findHighestPaidCompanyWide();
                    break;
                case 6:
                    calculateCompanyPayroll();
                    break;
                case 7:
                    showCompanyStatistics();
                    break;
                case 8:
                    updateEmployeeSalary();
                    break;
                case 0:
                    System.out.println("Thank you for using Employee Management System!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 0);
        
        scanner.close();
    }
    
    private static void initializeSystem() {
        // Set default company name
        Employee.setCompanyName("TechCorp Solutions");
        
        // Create sample departments
        departments[departmentCount++] = new Department("IT");
        departments[departmentCount++] = new Department("HR");
        departments[departmentCount++] = new Department("Finance");
        
        // Add sample employees
        addSampleData();
    }
    
    private static void addSampleData() {
        // IT Department employees
        departments[0].addEmployee(new Employee(101, "John Doe", "IT", 5500.00));
        departments[0].addEmployee(new Employee(102, "Jane Smith", "IT", 6200.00));
        departments[0].addEmployee(new Employee(103, "Bob Johnson", "IT", 4800.00));
        
        // HR Department employees
        departments[1].addEmployee(new Employee(201, "Alice Wilson", "HR", 4500.00));
        departments[1].addEmployee(new Employee(202, "Charlie Brown", "HR", 5000.00));
        
        // Finance Department employees
        departments[2].addEmployee(new Employee(301, "Diana Prince", "Finance", 5800.00));
        departments[2].addEmployee(new Employee(302, "Bruce Wayne", "Finance", 7000.00));
    }
    
    private static void displayMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1. Set Company Name");
        System.out.println("2. Create Department");
        System.out.println("3. Add Employee");
        System.out.println("4. Display All Departments");
        System.out.println("5. Find Highest Paid Employee (Company-wide)");
        System.out.println("6. Calculate Company-wide Payroll");
        System.out.println("7. Show Company Statistics");
        System.out.println("8. Update Employee Salary");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }
    
    private static void setCompanyName() {
        System.out.print("Enter company name: ");
        String name = scanner.nextLine();
        Employee.setCompanyName(name);
        System.out.println("Company name set to: " + Employee.getCompanyName());
    }
    
    private static void createDepartment() {
        if (departmentCount >= departments.length) {
            System.out.println("Maximum departments limit reached!");
            return;
        }
        
        System.out.print("Enter department name: ");
        String deptName = scanner.nextLine();
        departments[departmentCount++] = new Department(deptName);
        System.out.println("Department '" + deptName + "' created successfully!");
    }
    
    private static void addEmployee() {
        if (departmentCount == 0) {
            System.out.println("No departments available! Create a department first.");
            return;
        }
        
        // Display available departments
        System.out.println("Available departments:");
        for (int i = 0; i < departmentCount; i++) {
            System.out.println((i + 1) + ". " + departments[i].getDeptName());
        }
        
        System.out.print("Select department (1-" + departmentCount + "): ");
        int deptChoice = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline
        
        if (deptChoice < 0 || deptChoice >= departmentCount) {
            System.out.println("Invalid department selection!");
            return;
        }
        
        // Get employee details
        System.out.print("Enter Employee ID: ");
        int empId = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Monthly Salary: ");
        double salary = scanner.nextDouble();
        
        Employee newEmp = new Employee(empId, name, departments[deptChoice].getDeptName(), salary);
        departments[deptChoice].addEmployee(newEmp);
    }
    
    private static void displayAllDepartments() {
        System.out.println("\n=== " + Employee.getCompanyName() + " - All Departments ===");
        
        if (departmentCount == 0) {
            System.out.println("No departments found!");
            return;
        }
        
        for (int i = 0; i < departmentCount; i++) {
            departments[i].displayDepartmentEmployees();
        }
    }
    
    private static void findHighestPaidCompanyWide() {
        Employee highestPaid = null;
        String deptName = "";
        
        for (int i = 0; i < departmentCount; i++) {
            Employee deptHighest = departments[i].findHighestPaid();
            if (deptHighest != null) {
                if (highestPaid == null || deptHighest.getSalary() > highestPaid.getSalary()) {
                    highestPaid = deptHighest;
                    deptName = departments[i].getDeptName();
                }
            }
        }
        
        System.out.println("\n=== Highest Paid Employee (Company-wide) ===");
        if (highestPaid != null) {
            System.out.println("Department: " + deptName);
            highestPaid.displayEmployee();
        } else {
            System.out.println("No employees found!");
        }
    }
    
    private static void calculateCompanyPayroll() {
        double totalPayroll = 0;
        
        System.out.println("\n=== Company-wide Payroll ===");
        for (int i = 0; i < departmentCount; i++) {
            double deptPayroll = departments[i].calculateTotalPayroll();
            totalPayroll += deptPayroll;
            System.out.println(departments[i].getDeptName() + " Department: $" + 
                             String.format("%.2f", deptPayroll));
        }
        
        System.out.println("------------------------");
        System.out.println("Total Company Payroll: $" + String.format("%.2f", totalPayroll));
    }
    
    private static void showCompanyStatistics() {
        System.out.println("\n=== Company Statistics ===");
        System.out.println("Company Name: " + Employee.getCompanyName());
        System.out.println("Total Employees: " + Employee.getTotalEmployees());
        System.out.println("Total Departments: " + departmentCount);
        
        System.out.println("\nDepartment-wise Employee Count:");
        for (int i = 0; i < departmentCount; i++) {
            System.out.println(departments[i].getDeptName() + ": " + 
                             departments[i].getEmployeeCount() + " employees");
        }
    }
    
    private static void updateEmployeeSalary() {
        System.out.print("Enter Employee ID to update salary: ");
        int empId = scanner.nextInt();
        
        Employee foundEmp = null;
        for (int i = 0; i < departmentCount; i++) {
            for (int j = 0; j < departments[i].getEmployeeCount(); j++) {
                if (departments[i].getEmployees()[j].getEmpId() == empId) {
                    foundEmp = departments[i].getEmployees()[j];
                    break;
                }
            }
            if (foundEmp != null) break;
        }
        
        if (foundEmp != null) {
            System.out.println("Current salary for " + foundEmp.getName() + 
                             ": $" + String.format("%.2f", foundEmp.getSalary()));
            System.out.print("Enter new salary: ");
            double newSalary = scanner.nextDouble();
            foundEmp.updateSalary(newSalary);
        } else {
            System.out.println("Employee with ID " + empId + " not found!");
        }
    }
}