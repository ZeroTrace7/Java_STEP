import java.io.Serializable;
import java.util.Date;
import java.util.Calendar;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.lang.reflect.Method;

public class EmployeeBean implements Serializable {
    
    private String employeeId;
    private String firstName;
    private String lastName;
    private double salary;
    private String department;
    private Date hireDate;
    private boolean isActive;
    
    public EmployeeBean() {
    }
    
    public EmployeeBean(String employeeId, String firstName, String lastName, 
                       double salary, String department, Date hireDate, boolean isActive) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.department = department;
        this.hireDate = hireDate;
        this.isActive = isActive;
    }
    
    public String getEmployeeId() {
        return employeeId;
    }
    
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public double getSalary() {
        return salary;
    }
    
    public void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
        this.salary = salary;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public Date getHireDate() {
        return hireDate;
    }
    
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    public int getYearsOfService() {
        if (hireDate == null) return 0;
        
        Calendar hire = Calendar.getInstance();
        hire.setTime(hireDate);
        Calendar now = Calendar.getInstance();
        
        return now.get(Calendar.YEAR) - hire.get(Calendar.YEAR);
    }
    
    public String getFormattedSalary() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(salary);
    }
    
    public void setFullName(String fullName) {
        if (fullName != null && fullName.contains(" ")) {
            String[] parts = fullName.split(" ", 2);
            this.firstName = parts[0];
            this.lastName = parts[1];
        }
    }
    
    @Override
    public String toString() {
        return "EmployeeBean{" +
                "employeeId='" + employeeId + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", salary=" + getFormattedSalary() +
                ", department='" + department + '\'' +
                ", hireDate=" + hireDate +
                ", isActive=" + isActive +
                ", yearsOfService=" + getYearsOfService() +
                '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        EmployeeBean that = (EmployeeBean) obj;
        return employeeId != null ? employeeId.equals(that.employeeId) : that.employeeId == null;
    }
    
    @Override
    public int hashCode() {
        return employeeId != null ? employeeId.hashCode() : 0;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Creating Employees using Default Constructor ===");
        EmployeeBean emp1 = new EmployeeBean();
        emp1.setEmployeeId("E001");
        emp1.setFirstName("John");
        emp1.setLastName("Smith");
        emp1.setSalary(75000.0);
        emp1.setDepartment("Engineering");
    emp1.setHireDate(createDate(2020, 1, 15));
        emp1.setActive(true);
        
        System.out.println("=== Creating Employee using Parameterized Constructor ===");
    EmployeeBean emp2 = new EmployeeBean("E002", "Jane", "Doe", 85000.0, 
                       "Marketing", createDate(2019, 6, 10), true);
        
        System.out.println("=== Demonstrating Getter Methods ===");
        System.out.println("Employee 1: " + emp1.getFullName());
        System.out.println("Salary: " + emp1.getFormattedSalary());
        System.out.println("Years of Service: " + emp1.getYearsOfService());
        System.out.println("Is Active: " + emp1.isActive());
        
        System.out.println("=== Testing Computed Properties ===");
        emp2.setFullName("Jane Johnson");
        System.out.println("After setFullName: " + emp2.getFirstName() + " " + emp2.getLastName());
        
        System.out.println("=== Testing Validation ===");
        try {
            emp1.setSalary(-1000);
        } catch (IllegalArgumentException e) {
            System.out.println("Validation worked: " + e.getMessage());
        }
        
        System.out.println("=== JavaBean Array Operations ===");
        EmployeeBean[] employees = {
            new EmployeeBean("E003", "Alice", "Wilson", 90000.0, "Engineering", createDate(2018, 3, 1), true),
            new EmployeeBean("E004", "Bob", "Brown", 65000.0, "HR", createDate(2021, 9, 15), false),
            emp1, emp2
        };
        
        System.out.println("Original order:");
        for (EmployeeBean emp : employees) {
            System.out.println(emp.getFullName() + " - " + emp.getFormattedSalary());
        }
        
    Arrays.sort(employees, Comparator.comparing(EmployeeBean::getSalary).reversed());
        System.out.println("\nSorted by salary (highest first):");
        for (EmployeeBean emp : employees) {
            System.out.println(emp.getFullName() + " - " + emp.getFormattedSalary());
        }
        
        System.out.println("\nActive employees only:");
        for (EmployeeBean emp : employees) {
            if (emp.isActive()) {
                System.out.println(emp.getFullName() + " - " + emp.getDepartment());
            }
        }
        System.out.println("\n=== Using JavaBean Processor ===");
        JavaBeanProcessor.printAllProperties(emp1);
        
        System.out.println("\n=== Testing Property Copying ===");
        EmployeeBean empCopy = new EmployeeBean();
        JavaBeanProcessor.copyProperties(emp1, empCopy);
        System.out.println("Copied employee: " + empCopy);
    }

    // Utility method to create Date using Calendar (avoids deprecated constructor)
    private static Date createDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}

class JavaBeanProcessor {
    
    public static void printAllProperties(EmployeeBean emp) {
        System.out.println("=== Employee Properties via Reflection ===");
        Class<?> clazz = emp.getClass();
        Method[] methods = clazz.getMethods();
        
        for (Method method : methods) {
            String methodName = method.getName();
            if ((methodName.startsWith("get") || methodName.startsWith("is")) && 
                method.getParameterCount() == 0 && 
                !methodName.equals("getClass")) {
                
                try {
                    String propertyName = methodName.startsWith("get") ? 
                        methodName.substring(3) : methodName.substring(2);
                    Object value = method.invoke(emp);
                    System.out.println(propertyName + ": " + value);
                } catch (Exception e) {
                    System.out.println("Error accessing " + methodName + ": " + e.getMessage());
                }
            }
        }
    }
    
    public static void copyProperties(EmployeeBean source, EmployeeBean target) {
        Class<?> clazz = source.getClass();
        Method[] methods = clazz.getMethods();
        
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.startsWith("get") && method.getParameterCount() == 0) {
                String propertyName = methodName.substring(3);
                String setterName = "set" + propertyName;
                
                try {
                    Method setter = clazz.getMethod(setterName, method.getReturnType());
                    Object value = method.invoke(source);
                    setter.invoke(target, value);
                } catch (Exception e) {
                    // Skip properties that don't have setters or cause errors
                }
            }
        }
    }
}