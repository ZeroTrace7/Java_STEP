import java.util.HashSet;
import java.util.Objects;

class Student {
    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        
        Student s = (Student) obj;
        return id == s.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student[id=" + id + ", name=" + name + "]";
    }
}

public class StudentDetails {
    public static void main(String[] args) {
        HashSet<Student> students = new HashSet<>();

        Student s1 = new Student(101, "Alice");
        Student s2 = new Student(102, "Bob");
        Student s3 = new Student(101, "Alice Again");

        students.add(s1);
        students.add(s2);
        students.add(s3);

        System.out.println("HashSet size: " + students.size());
        System.out.println("Students in HashSet:");
        for (Student s : students) {
            System.out.println(s);
        }
        
        System.out.println();
        System.out.println("s1.equals(s3): " + s1.equals(s3));
        System.out.println("s1.hashCode(): " + s1.hashCode());
        System.out.println("s3.hashCode(): " + s3.hashCode());
    }
}