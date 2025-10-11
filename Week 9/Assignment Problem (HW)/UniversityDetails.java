class University {
    String universityName;
    String location;

    University(String universityName, String location) {
        this.universityName = universityName;
        this.location = location;
    }

    class Department {
        String deptName;

        Department(String deptName) {
            this.deptName = deptName;
        }

        void showDetails() {
            System.out.println("Department: " + deptName);
            System.out.println("University: " + universityName);
            System.out.println("Location: " + location);
        }
    }

    static class ExamCell {
        void conductExam(String examName) {
            System.out.println("Conducting exam: " + examName);
        }

        void publishResults() {
            System.out.println("Results published successfully");
        }
    }
}

public class UniversityDetails {
    public static void main(String[] args) {
        University uni = new University("MIT", "Cambridge");

        University.Department dept = uni.new Department("Computer Science");
        dept.showDetails();
        System.out.println();

        University.ExamCell examCell = new University.ExamCell();
        examCell.conductExam("Final Semester Exam");
        examCell.publishResults();
    }
}