abstract class Employee {
    protected String name;
    protected double salary;
    
    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
    
    public abstract void calculateBonus();
}

interface Payable {
    void generatePaySlip();
}

class Manager extends Employee implements Payable {
    private String department;
    private double bonus;
    
    public Manager(String name, double salary, String department) {
        super(name, salary);
        this.department = department;
    }
    
    public void calculateBonus() {
        bonus = salary * 0.20;
        System.out.println("Manager Bonus: " + bonus);
    }
    
    public void generatePaySlip() {
        System.out.println("---- Pay Slip ----");
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Salary: " + salary);
        System.out.println("Bonus: " + bonus);
        System.out.println("Total: " + (salary + bonus));
    }
}

public class EmployeeTest {
    public static void main(String[] args) {
        Manager manager = new Manager("Rahul", 50000, "IT");
        manager.calculateBonus();
        manager.generatePaySlip();
    }
}