import java.util.Date;

// Base Employee class
public class Employee {
    // Protected fields for inheritance
    protected String employeeId;
    protected String name;
    protected double baseSalary;
    protected String department;
    protected Date joiningDate;
    
    // Constructor
    public Employee(String employeeId, String name, double baseSalary, String department) {
        if (employeeId == null || name == null || baseSalary < 0) {
            throw new IllegalArgumentException("Invalid employee data");
        }
        this.employeeId = employeeId;
        this.name = name;
        this.baseSalary = baseSalary;
        this.department = department;
        this.joiningDate = new Date();
        System.out.println("Employee " + name + " created in " + department);
    }
    
    // Methods to be overridden by subclasses
    public double calculateSalary() {
        return baseSalary;
    }
    
    public String getJobDescription() {
        return "General Employee";
    }
    
    public void performWork() {
        System.out.println("Employee is working");
    }
    
    public void attendMeeting() {
        System.out.println("Employee attending meeting");
    }
    
    // Final methods - cannot be overridden
    public final String getEmployeeId() {
        return employeeId;
    }
    
    public final void printEmployeeDetails() {
        System.out.println("ID: " + employeeId + ", Name: " + name + 
                          ", Department: " + department + 
                          ", Job: " + getJobDescription() + 
                          ", Salary: $" + calculateSalary());
    }
    
    // Default behavior methods
    public void takeBreak() {
        System.out.println(name + " is taking a break");
    }
    
    public void clockIn() {
        System.out.println(name + " clocked in at " + new Date());
    }
    
    public void clockOut() {
        System.out.println(name + " clocked out at " + new Date());
    }
}

// Developer class extends Employee
class Developer extends Employee {
    // Developer-specific fields
    private String[] programmingLanguages;
    private String experienceLevel; // Junior/Mid/Senior
    private int projectsCompleted;
    
    // Constructor
    public Developer(String employeeId, String name, double baseSalary, 
                    String department, String[] languages, 
                    String experienceLevel, int projectsCompleted) {
        super(employeeId, name, baseSalary, department);
        this.programmingLanguages = languages;
        this.experienceLevel = experienceLevel;
        this.projectsCompleted = projectsCompleted;
        System.out.println("Developer profile created");
    }
    
    // Override parent methods
    @Override
    public double calculateSalary() {
        double salary = baseSalary;
        
        // Experience bonus
        if (experienceLevel.equals("Senior")) {
            salary += 20000;
        } else if (experienceLevel.equals("Mid")) {
            salary += 10000;
        }
        
        // Project bonus
        salary += projectsCompleted * 1000;
        
        return salary;
    }
    
    @Override
    public String getJobDescription() {
        return "Software Developer";
    }
    
    @Override
    public void performWork() {
        System.out.println("Developer is coding and debugging");
    }
    
    @Override
    public void attendMeeting() {
        System.out.println("Developer in technical meeting");
    }
    
    // Developer-specific methods
    public void writeCode() {
        String language = programmingLanguages.length > 0 ? programmingLanguages[0] : "Java";
        System.out.println("Writing code in " + language);
    }
    
    public void reviewCode() {
        System.out.println("Reviewing team's code");
    }
    
    public void deployApplication() {
        System.out.println("Deploying application to production");
    }
}

// Manager class extends Employee
class Manager extends Employee {
    // Manager-specific fields
    private int teamSize;
    private String managementLevel; // Team Lead/Manager/Director
    private double budgetResponsibility;
    
    // Constructor
    public Manager(String employeeId, String name, double baseSalary, 
                  String department, int teamSize, String managementLevel, 
                  double budgetResponsibility) {
        super(employeeId, name, baseSalary, department);
        this.teamSize = teamSize;
        this.managementLevel = managementLevel;
        this.budgetResponsibility = budgetResponsibility;
        System.out.println("Manager profile created");
    }
    
    // Override parent methods differently than Developer
    @Override
    public double calculateSalary() {
        double salary = baseSalary;
        
        // Team size bonus
        salary += teamSize * 2000;
        
        // Management level bonus
        if (managementLevel.equals("Director")) {
            salary += 30000;
        } else if (managementLevel.equals("Manager")) {
            salary += 15000;
        } else if (managementLevel.equals("Team Lead")) {
            salary += 8000;
        }
        
        return salary;
    }
    
    @Override
    public String getJobDescription() {
        return "Team Manager";
    }
    
    @Override
    public void performWork() {
        System.out.println("Manager is coordinating team activities");
    }
    
    @Override
    public void attendMeeting() {
        System.out.println("Manager leading strategic meeting");
    }
    
    // Manager-specific methods
    public void conductPerformanceReview() {
        System.out.println("Conducting team performance review");
    }
    
    public void assignTasks() {
        System.out.println("Assigning tasks to team members");
    }
    
    public void manageBudget() {
        System.out.println("Managing department budget");
    }
}

// Intern class extends Employee
class Intern extends Employee {
    // Intern-specific fields
    private String university;
    private int internshipDuration; // weeks
    private String mentor;
    private boolean isFullTime;
    
    // Constructor
    public Intern(String employeeId, String name, String department, 
                 String university, int duration, String mentor, boolean isFullTime) {
        super(employeeId, name, 2000.0, department); // Lower base salary for intern
        this.university = university;
        this.internshipDuration = duration;
        this.mentor = mentor;
        this.isFullTime = isFullTime;
        System.out.println("Intern onboarded");
    }
    
    // Override methods with intern-specific behavior
    @Override
    public double calculateSalary() {
        // Return stipend amount (much lower)
        return isFullTime ? 1500.0 : 800.0;
    }
    
    @Override
    public String getJobDescription() {
        return "Intern";
    }
    
    @Override
    public void performWork() {
        System.out.println("Intern is learning and assisting");
    }
    
    // DON'T override attendMeeting() - uses parent implementation
    
    // Intern-specific methods
    public void submitWeeklyReport() {
        System.out.println("Intern submitting weekly progress report");
    }
    
    public void attendTraining() {
        System.out.println("Intern attending training session");
    }
}

// Demo class to test hierarchical inheritance
class HierarchicalInheritanceDemo {
    public static void main(String[] args) {
        System.out.println("=== Creating Different Employee Types ===\n");
        
        // Create Developer
        String[] languages = {"Java", "Python", "JavaScript"};
        Developer dev = new Developer("DEV001", "Alice Johnson", 60000, 
                                     "Engineering", languages, "Senior", 15);
        
        // Create Manager
        Manager mgr = new Manager("MGR001", "Bob Smith", 80000, 
                                 "Engineering", 8, "Manager", 500000);
        
        // Create Intern
        Intern intern = new Intern("INT001", "Carol Davis", "Engineering", 
                                  "MIT", 12, "DEV001", true);
        
        System.out.println("\n=== Employee Details ===");
        dev.printEmployeeDetails();
        mgr.printEmployeeDetails();
        intern.printEmployeeDetails();
        
        System.out.println("\n=== Different Work Behaviors ===");
        dev.performWork();
        mgr.performWork();
        intern.performWork();
        
        System.out.println("\n=== Meeting Behaviors ===");
        dev.attendMeeting();
        mgr.attendMeeting();
        intern.attendMeeting(); // Uses parent implementation
        
        System.out.println("\n=== Role-Specific Methods ===");
        dev.writeCode();
        mgr.assignTasks();
        intern.submitWeeklyReport();
        
        System.out.println("\n=== Common Inherited Methods ===");
        dev.takeBreak();
        mgr.clockIn();
        intern.clockOut();
        
        System.out.println("\n=== Final Method Access ===");
        System.out.println("Developer ID: " + dev.getEmployeeId());
        System.out.println("Manager ID: " + mgr.getEmployeeId());
        System.out.println("Intern ID: " + intern.getEmployeeId());
    }
}