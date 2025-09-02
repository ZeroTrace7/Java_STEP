import java.util.Scanner;
import java.util.Arrays;

class Subject {
    // Instance attributes
    private String subjectCode;
    private String subjectName;
    private int credits;
    private String instructor;
    
    // Constructor
    public Subject(String subjectCode, String subjectName, int credits, String instructor) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.credits = credits;
        this.instructor = instructor;
    }
    
    // Getter methods
    public String getSubjectCode() { return subjectCode; }
    public String getSubjectName() { return subjectName; }
    public int getCredits() { return credits; }
    public String getInstructor() { return instructor; }
    
    // Setter methods
    public void setInstructor(String instructor) { this.instructor = instructor; }
    public void setCredits(int credits) { this.credits = credits; }
    
    @Override
    public String toString() {
        return String.format("%-8s | %-20s | Credits: %-2d | Instructor: %s",
                subjectCode, subjectName, credits, instructor);
    }
}

class Student {
    // Instance attributes
    private String studentId;
    private String studentName;
    private String className;
    private String[] subjects;
    private double[][] marks; // [subject][test] - supports multiple tests per subject
    private double gpa;
    private int subjectCount;
    
    // Static variables
    private static int totalStudents = 0;
    private static String schoolName = "Excellence Academy";
    private static String[] gradingScale = {"A+", "A", "B+", "B", "C+", "C", "D", "F"};
    private static double passPercentage = 60.0;
    
    // Constants
    private static final int MAX_SUBJECTS = 10;
    private static final int MAX_TESTS_PER_SUBJECT = 5;
    
    // Constructor
    public Student(String studentId, String studentName, String className) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.className = className;
        this.subjects = new String[MAX_SUBJECTS];
        this.marks = new double[MAX_SUBJECTS][MAX_TESTS_PER_SUBJECT];
        this.gpa = 0.0;
        this.subjectCount = 0;
        
        // Initialize marks array with -1 (indicating no marks entered)
        for (int i = 0; i < MAX_SUBJECTS; i++) {
            Arrays.fill(marks[i], -1);
        }
        
        totalStudents++;
    }
    
    // Method to add a subject
    public boolean addSubject(String subject) {
        if (subjectCount < MAX_SUBJECTS) {
            subjects[subjectCount] = subject;
            subjectCount++;
            System.out.println("Subject '" + subject + "' added for " + studentName);
            return true;
        } else {
            System.out.println("Cannot add more subjects. Maximum limit reached.");
            return false;
        }
    }
    
    // Method to add marks for a subject
    public boolean addMarks(String subject, double marksValue) {
        if (marksValue < 0 || marksValue > 100) {
            System.out.println("Invalid marks! Marks should be between 0 and 100.");
            return false;
        }
        
        // Find subject index
        int subjectIndex = -1;
        for (int i = 0; i < subjectCount; i++) {
            if (subjects[i].equals(subject)) {
                subjectIndex = i;
                break;
            }
        }
        
        if (subjectIndex == -1) {
            System.out.println("Subject '" + subject + "' not found for student " + studentName);
            return false;
        }
        
        // Find next available slot for marks
        for (int i = 0; i < MAX_TESTS_PER_SUBJECT; i++) {
            if (marks[subjectIndex][i] == -1) {
                marks[subjectIndex][i] = marksValue;
                System.out.println("Marks " + marksValue + " added for " + subject + " (Test " + (i + 1) + ")");
                calculateGPA(); // Recalculate GPA after adding marks
                return true;
            }
        }
        
        System.out.println("Cannot add more marks for " + subject + ". Maximum tests limit reached.");
        return false;
    }
    
    // Method to calculate average marks for a subject
    private double getSubjectAverage(int subjectIndex) {
        double total = 0;
        int count = 0;
        
        for (int i = 0; i < MAX_TESTS_PER_SUBJECT; i++) {
            if (marks[subjectIndex][i] != -1) {
                total += marks[subjectIndex][i];
                count++;
            }
        }
        
        return count > 0 ? total / count : 0;
    }
    
    // Method to calculate GPA
    public void calculateGPA() {
        if (subjectCount == 0) {
            gpa = 0.0;
            return;
        }
        
        double totalGradePoints = 0;
        int validSubjects = 0;
        
        for (int i = 0; i < subjectCount; i++) {
            double average = getSubjectAverage(i);
            if (average > 0) { // Only count subjects with marks
                totalGradePoints += convertPercentageToGradePoints(average);
                validSubjects++;
            }
        }
        
        gpa = validSubjects > 0 ? totalGradePoints / validSubjects : 0.0;
    }
    
    // Helper method to convert percentage to grade points
    private double convertPercentageToGradePoints(double percentage) {
        if (percentage >= 97) return 4.0;      // A+
        else if (percentage >= 93) return 3.7; // A
        else if (percentage >= 90) return 3.3; // B+
        else if (percentage >= 87) return 3.0; // B
        else if (percentage >= 83) return 2.7; // C+
        else if (percentage >= 80) return 2.3; // C
        else if (percentage >= 70) return 2.0; // D
        else return 0.0; // F
    }
    
    // Method to get letter grade from percentage
    public static String getLetterGrade(double percentage) {
        if (percentage >= 97) return "A+";
        else if (percentage >= 93) return "A";
        else if (percentage >= 90) return "B+";
        else if (percentage >= 87) return "B";
        else if (percentage >= 83) return "C+";
        else if (percentage >= 80) return "C";
        else if (percentage >= 70) return "D";
        else return "F";
    }
    
    // Method to check promotion eligibility
    public boolean checkPromotionEligibility() {
        if (subjectCount == 0) {
            System.out.println("No subjects enrolled for " + studentName);
            return false;
        }
        
        int failedSubjects = 0;
        double totalAverage = 0;
        int validSubjects = 0;
        
        for (int i = 0; i < subjectCount; i++) {
            double average = getSubjectAverage(i);
            if (average > 0) {
                totalAverage += average;
                validSubjects++;
                if (average < passPercentage) {
                    failedSubjects++;
                }
            }
        }
        
        if (validSubjects == 0) {
            System.out.println("No marks available for " + studentName);
            return false;
        }
        
        double overallAverage = totalAverage / validSubjects;
        
        // Promotion criteria: Overall average >= pass percentage AND failed subjects <= 1
        boolean isEligible = overallAverage >= passPercentage && failedSubjects <= 1;
        
        System.out.println("\nPromotion Eligibility for " + studentName + ":");
        System.out.println("Overall Average: " + String.format("%.2f", overallAverage) + "%");
        System.out.println("Failed Subjects: " + failedSubjects);
        System.out.println("Pass Percentage Required: " + passPercentage + "%");
        System.out.println("Status: " + (isEligible ? "ELIGIBLE FOR PROMOTION" : "NOT ELIGIBLE FOR PROMOTION"));
        
        return isEligible;
    }
    
    // Method to generate report card
    public void generateReportCard() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("OFFICIAL REPORT CARD");
        System.out.println("=".repeat(80));
        System.out.println("School: " + schoolName);
        System.out.println("Student ID: " + studentId);
        System.out.println("Student Name: " + studentName);
        System.out.println("Class: " + className);
        System.out.println("Academic Year: 2024-2025");
        System.out.println("=".repeat(80));
        
        if (subjectCount == 0) {
            System.out.println("No subjects enrolled.");
            System.out.println("=".repeat(80));
            return;
        }
        
        System.out.printf("%-20s | %-12s | %-8s | %-8s | %-5s%n", 
                         "Subject", "Test Scores", "Average", "Grade", "Status");
        System.out.println("-".repeat(80));
        
        double totalAverage = 0;
        int validSubjects = 0;
        int passedSubjects = 0;
        
        for (int i = 0; i < subjectCount; i++) {
            String subjectName = subjects[i];
            StringBuilder testScores = new StringBuilder();
            
            // Collect test scores
            for (int j = 0; j < MAX_TESTS_PER_SUBJECT; j++) {
                if (marks[i][j] != -1) {
                    if (testScores.length() > 0) testScores.append(", ");
                    testScores.append(String.format("%.1f", marks[i][j]));
                }
            }
            
            double average = getSubjectAverage(i);
            String letterGrade = getLetterGrade(average);
            String status = average >= passPercentage ? "PASS" : "FAIL";
            
            if (average > 0) {
                totalAverage += average;
                validSubjects++;
                if (average >= passPercentage) {
                    passedSubjects++;
                }
            }
            
            System.out.printf("%-20s | %-12s | %-8.2f | %-8s | %-5s%n",
                             subjectName,
                             testScores.length() > 0 ? testScores.toString() : "No scores",
                             average,
                             letterGrade,
                             status);
        }
        
        System.out.println("-".repeat(80));
        
        if (validSubjects > 0) {
            double overallAverage = totalAverage / validSubjects;
            String overallGrade = getLetterGrade(overallAverage);
            
            System.out.println("SUMMARY:");
            System.out.println("Total Subjects: " + subjectCount);
            System.out.println("Subjects Passed: " + passedSubjects);
            System.out.println("Subjects Failed: " + (validSubjects - passedSubjects));
            System.out.println("Overall Average: " + String.format("%.2f", overallAverage) + "%");
            System.out.println("Overall Grade: " + overallGrade);
            System.out.println("GPA: " + String.format("%.2f", gpa) + "/4.0");
            
            boolean promoted = checkPromotionEligibility();
            System.out.println("Promotion Status: " + (promoted ? "PROMOTED" : "NOT PROMOTED"));
        }
        
        System.out.println("=".repeat(80));
        System.out.println("Report generated on: " + java.time.LocalDate.now());
        System.out.println("=".repeat(80));
    }
    
    // Method to display student summary
    public void displayStudentSummary() {
        System.out.println("\nStudent Summary:");
        System.out.println("ID: " + studentId + " | Name: " + studentName + " | Class: " + className);
        System.out.println("Subjects Enrolled: " + subjectCount + " | GPA: " + String.format("%.2f", gpa));
    }
    
    // Static methods
    public static void setSchoolName(String name) {
        schoolName = name;
        System.out.println("School name updated to: " + schoolName);
    }
    
    public static void setPassPercentage(double percentage) {
        if (percentage >= 0 && percentage <= 100) {
            passPercentage = percentage;
            System.out.println("Pass percentage updated to: " + passPercentage + "%");
        } else {
            System.out.println("Invalid pass percentage! Must be between 0 and 100.");
        }
    }
    
    public static void displayGradingScale() {
        System.out.println("\nGrading Scale:");
        System.out.println("A+ (97-100%) - Excellent");
        System.out.println("A  (93-96%)  - Outstanding");
        System.out.println("B+ (90-92%)  - Very Good");
        System.out.println("B  (87-89%)  - Good");
        System.out.println("C+ (83-86%)  - Above Average");
        System.out.println("C  (80-82%)  - Average");
        System.out.println("D  (70-79%)  - Below Average");
        System.out.println("F  (0-69%)   - Fail");
    }
    
    public static void displaySchoolStatistics(Student[] students, int studentCount) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("SCHOOL STATISTICS - " + schoolName);
        System.out.println("=".repeat(60));
        System.out.println("Total Students: " + totalStudents);
        System.out.println("Pass Percentage Requirement: " + passPercentage + "%");
        
        if (studentCount > 0) {
            double totalGPA = 0;
            int promotedStudents = 0;
            int[] gradeDistribution = new int[gradingScale.length];
            
            for (int i = 0; i < studentCount; i++) {
                totalGPA += students[i].gpa;
                if (students[i].checkPromotionEligibility()) {
                    promotedStudents++;
                }
                
                // Calculate overall average for grade distribution
                double overallAverage = 0;
                int validSubjects = 0;
                for (int j = 0; j < students[i].subjectCount; j++) {
                    double avg = students[i].getSubjectAverage(j);
                    if (avg > 0) {
                        overallAverage += avg;
                        validSubjects++;
                    }
                }
                if (validSubjects > 0) {
                    overallAverage /= validSubjects;
                    String grade = getLetterGrade(overallAverage);
                    for (int k = 0; k < gradingScale.length; k++) {
                        if (gradingScale[k].equals(grade)) {
                            gradeDistribution[k]++;
                            break;
                        }
                    }
                }
            }
            
            System.out.println("Average School GPA: " + String.format("%.2f", totalGPA / studentCount));
            System.out.println("Students Promoted: " + promotedStudents + "/" + studentCount);
            System.out.println("Promotion Rate: " + String.format("%.1f", (double)promotedStudents / studentCount * 100) + "%");
            
            System.out.println("\nGrade Distribution:");
            for (int i = 0; i < gradingScale.length; i++) {
                if (gradeDistribution[i] > 0) {
                    System.out.println(gradingScale[i] + ": " + gradeDistribution[i] + " students");
                }
            }
        }
        System.out.println("=".repeat(60));
    }
    
    // Getter methods
    public String getStudentId() { return studentId; }
    public String getStudentName() { return studentName; }
    public String getClassName() { return className; }
    public double getGpa() { return gpa; }
    public int getSubjectCount() { return subjectCount; }
    public static int getTotalStudents() { return totalStudents; }
    public static String getSchoolName() { return schoolName; }
    public static double getPassPercentage() { return passPercentage; }
}

public class StudentGradeManagementSystem {
    private static Student[] students;
    private static Subject[] subjects;
    private static Scanner scanner = new Scanner(System.in);
    private static int studentCount = 0;
    private static int subjectCount = 0;
    private static final int MAX_STUDENTS = 100;
    private static final int MAX_SUBJECTS = 20;
    
    public static void main(String[] args) {
        students = new Student[MAX_STUDENTS];
        subjects = new Subject[MAX_SUBJECTS];
        
        initializeSystem();
        
        System.out.println("Welcome to " + Student.getSchoolName() + " Grade Management System!");
        System.out.println("=".repeat(60));
        
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getChoice();
            
            switch (choice) {
                case 1:
                    registerStudent();
                    break;
                case 2:
                    addSubjectToStudent();
                    break;
                case 3:
                    addMarksToStudent();
                    break;
                case 4:
                    generateStudentReport();
                    break;
                case 5:
                    checkStudentPromotion();
                    break;
                case 6:
                    displayAllStudents();
                    break;
                case 7:
                    Student.displayGradingScale();
                    break;
                case 8:
                    Student.displaySchoolStatistics(students, studentCount);
                    break;
                case 9:
                    systemSettings();
                    break;
                case 10:
                    System.out.println("Thank you for using the Grade Management System!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        
        scanner.close();
    }
    
    private static void initializeSystem() {
        // Initialize some sample subjects
        subjects[subjectCount++] = new Subject("MATH101", "Mathematics", 4, "Dr. Smith");
        subjects[subjectCount++] = new Subject("ENG101", "English", 3, "Prof. Johnson");
        subjects[subjectCount++] = new Subject("SCI101", "Science", 4, "Dr. Brown");
        subjects[subjectCount++] = new Subject("HIS101", "History", 3, "Prof. Davis");
        subjects[subjectCount++] = new Subject("ART101", "Art", 2, "Ms. Wilson");
        
        System.out.println("System initialized with " + subjectCount + " subjects.");
    }
    
    private static void displayMainMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("STUDENT GRADE MANAGEMENT SYSTEM");
        System.out.println("=".repeat(50));
        System.out.println("1. Register New Student");
        System.out.println("2. Add Subject to Student");
        System.out.println("3. Add Marks for Student");
        System.out.println("4. Generate Student Report Card");
        System.out.println("5. Check Promotion Eligibility");
        System.out.println("6. Display All Students");
        System.out.println("7. View Grading Scale");
        System.out.println("8. School Statistics");
        System.out.println("9. System Settings");
        System.out.println("10. Exit");
        System.out.println("=".repeat(50));
        System.out.print("Enter your choice: ");
    }
    
    private static void registerStudent() {
        if (studentCount >= MAX_STUDENTS) {
            System.out.println("Cannot register more students. Maximum limit reached.");
            return;
        }
        
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        
        // Check if student ID already exists
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getStudentId().equals(studentId)) {
                System.out.println("Student ID already exists! Please use a different ID.");
                return;
            }
        }
        
        System.out.print("Enter Student Name: ");
        String studentName = scanner.nextLine();
        
        System.out.print("Enter Class: ");
        String className = scanner.nextLine();
        
        students[studentCount] = new Student(studentId, studentName, className);
        studentCount++;
        
        System.out.println("Student registered successfully!");
        students[studentCount - 1].displayStudentSummary();
    }
    
    private static void addSubjectToStudent() {
        Student student = findStudent();
        if (student == null) return;
        
        System.out.println("\nAvailable Subjects:");
        for (int i = 0; i < subjectCount; i++) {
            System.out.println((i + 1) + ". " + subjects[i]);
        }
        
        System.out.print("Enter subject code or name: ");
        String subjectInput = scanner.nextLine();
        
        // Find subject by code or name
        String subjectToAdd = null;
        for (int i = 0; i < subjectCount; i++) {
            if (subjects[i].getSubjectCode().equalsIgnoreCase(subjectInput) ||
                subjects[i].getSubjectName().equalsIgnoreCase(subjectInput)) {
                subjectToAdd = subjects[i].getSubjectName();
                break;
            }
        }
        
        if (subjectToAdd != null) {
            student.addSubject(subjectToAdd);
        } else {
            System.out.println("Subject not found! You can add a custom subject name:");
            System.out.print("Enter custom subject name: ");
            String customSubject = scanner.nextLine();
            student.addSubject(customSubject);
        }
    }
    
    private static void addMarksToStudent() {
        Student student = findStudent();
        if (student == null) return;
        
        if (student.getSubjectCount() == 0) {
            System.out.println("No subjects enrolled for this student. Please add subjects first.");
            return;
        }
        
        System.out.print("Enter subject name: ");
        String subject = scanner.nextLine();
        
        System.out.print("Enter marks (0-100): ");
        try {
            double marks = Double.parseDouble(scanner.nextLine());
            student.addMarks(subject, marks);
        } catch (NumberFormatException e) {
            System.out.println("Invalid marks format! Please enter a valid number.");
        }
    }
    
    private static void generateStudentReport() {
        Student student = findStudent();
        if (student == null) return;
        
        student.generateReportCard();
    }
    
    private static void checkStudentPromotion() {
        Student student = findStudent();
        if (student == null) return;
        
        student.checkPromotionEligibility();
    }
    
    private static void displayAllStudents() {
        if (studentCount == 0) {
            System.out.println("No students registered.");
            return;
        }
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ALL REGISTERED STUDENTS");
        System.out.println("=".repeat(80));
        
        for (int i = 0; i < studentCount; i++) {
            students[i].displayStudentSummary();
        }
        
        System.out.println("=".repeat(80));
        System.out.println("Total Students: " + studentCount);
    }
    
    private static void systemSettings() {
        System.out.println("\n=== SYSTEM SETTINGS ===");
        System.out.println("1. Change School Name");
        System.out.println("2. Change Pass Percentage");
        System.out.println("3. Back to Main Menu");
        
        System.out.print("Enter choice: ");
        int choice = getChoice();
        
        switch (choice) {
            case 1:
                System.out.print("Enter new school name: ");
                String newSchoolName = scanner.nextLine();
                Student.setSchoolName(newSchoolName);
                break;
            case 2:
                System.out.print("Enter new pass percentage (0-100): ");
                try {
                    double newPassPercentage = Double.parseDouble(scanner.nextLine());
                    Student.setPassPercentage(newPassPercentage);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid percentage format!");
                }
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice!");
        }
    }
    
    private static Student findStudent() {
        if (studentCount == 0) {
            System.out.println("No students registered.");
            return null;
        }
        
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getStudentId().equals(studentId)) {
                return students[i];
            }
        }
        
        System.out.println("Student not found!");
        return null;
    }
    
    private static int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}