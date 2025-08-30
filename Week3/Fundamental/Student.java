public class Student {
    // Private instance variables (encapsulation - data hiding)
    private String studentId;
    private String name;
    private double grade;
    private String course;
    
    // Default constructor (no parameters)
    // This allows creating a Student object without initial values
    public Student() {
        this.studentId = "Unknown";
        this.name = "Unknown";
        this.grade = 0.0;
        this.course = "Unknown";
        System.out.println("📝 Default constructor called - Student created with default values");
    }
    
    // Parameterized constructor (accepts all attributes)
    // This allows creating a Student with specific initial values
    public Student(String studentId, String name, double grade, String course) {
        this.studentId = studentId;
        this.name = name;
        this.grade = grade;
        this.course = course;
        System.out.println("📝 Parameterized constructor called - Student created with provided values");
    }
    
    // Getter methods (accessors) - Allow reading private variables
    public String getStudentId() {
        return studentId;
    }
    
    public String getName() {
        return name;
    }
    
    public double getGrade() {
        return grade;
    }
    
    public String getCourse() {
        return course;
    }
    
    // Setter methods (mutators) - Allow modifying private variables with validation
    public void setStudentId(String studentId) {
        if (studentId != null && !studentId.trim().isEmpty()) {
            this.studentId = studentId;
        } else {
            System.out.println("⚠️ Warning: Student ID cannot be empty!");
        }
    }
    
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("⚠️ Warning: Name cannot be empty!");
        }
    }
    
    public void setGrade(double grade) {
        if (grade >= 0.0 && grade <= 100.0) {
            this.grade = grade;
        } else {
            System.out.println("⚠️ Warning: Grade must be between 0 and 100!");
        }
    }
    
    public void setCourse(String course) {
        if (course != null && !course.trim().isEmpty()) {
            this.course = course;
        } else {
            System.out.println("⚠️ Warning: Course cannot be empty!");
        }
    }
    
    // Method to calculate letter grade based on numerical grade
    public String calculateLetterGrade() {
        if (grade >= 90.0) {
            return "A";
        } else if (grade >= 80.0) {
            return "B";
        } else if (grade >= 70.0) {
            return "C";
        } else if (grade >= 60.0) {
            return "D";
        } else {
            return "F";
        }
    }
    
    // Method to display all student information
    public void displayStudent() {
        System.out.println("======= STUDENT INFORMATION =======");
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Course: " + course);
        System.out.printf("Numerical Grade: %.2f\n", grade);
        System.out.println("Letter Grade: " + calculateLetterGrade());
        
        // Add some visual feedback based on grade
        String performance;
        if (grade >= 90) {
            performance = "🌟 Excellent!";
        } else if (grade >= 80) {
            performance = "👍 Good job!";
        } else if (grade >= 70) {
            performance = "✅ Satisfactory";
        } else if (grade >= 60) {
            performance = "⚠️ Needs improvement";
        } else {
            performance = "❌ Failing - requires attention";
        }
        System.out.println("Performance: " + performance);
        System.out.println("===================================\n");
    }
    
    // Additional utility method to check if student is passing
    public boolean isPassing() {
        return grade >= 60.0;
    }
    
    public static void main(String[] args) {
        // TODO: Create one student using default constructor, then set values
        // TODO: Create another student using parameterized constructor
        // TODO: Demonstrate all getter/setter methods
        // TODO: Show both students' information and letter grades
    }
}