class Employee {
    int id;
    String name;
    double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee[ID=" + id + ", Name=" + name + ", Salary=$" + salary + "]";
    }
}

public class EmployeeDetails {
    public static void main(String[] args) {
        Employee emp1 = new Employee(101, "John", 50000);
        Employee emp2 = new Employee(102, "Sarah", 60000);
        Employee emp3 = new Employee(103, "Mike", 55000);

        System.out.println(emp1);
        System.out.println("Class name: " + emp1.getClass().getName());
        System.out.println();

        System.out.println(emp2);
        System.out.println("Class name: " + emp2.getClass().getName());
        System.out.println();

        System.out.println(emp3);
        System.out.println("Class name: " + emp3.getClass().getName());
    }
}