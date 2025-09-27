class LibraryUser {
    protected String name;
    protected String userId;
    protected String userType;
    
    public LibraryUser(String name, String userId, String userType) {
        this.name = name;
        this.userId = userId;
        this.userType = userType;
    }
    
    public void logEntry() {
        System.out.println("Entry logged: " + name + " (" + userType + ") entered the library");
    }
    
    public void showBasicInfo() {
        System.out.println("Name: " + name);
        System.out.println("ID: " + userId);
        System.out.println("Type: " + userType);
    }
    
    public void browseBooks() {
        System.out.println(name + " is browsing books");
    }
    
    public String getName() {
        return name;
    }
    
    public String getUserType() {
        return userType;
    }
}

class Student extends LibraryUser {
    private String major;
    private int booksBorrowed;
    
    public Student(String name, String userId, String major) {
        super(name, userId, "Student");
        this.major = major;
        this.booksBorrowed = 0;
    }
    
    public void borrowBook(String bookTitle) {
        if (booksBorrowed < 5) {
            booksBorrowed = booksBorrowed + 1;
            System.out.println("📚 " + name + " borrowed: " + bookTitle);
            System.out.println("   Books borrowed: " + booksBorrowed + "/5");
        } else {
            System.out.println("❌ " + name + " has reached the borrowing limit!");
        }
    }
    
    public void accessComputer() {
        System.out.println("💻 " + name + " is using a library computer");
    }
    
    public void showBasicInfo() {
        super.showBasicInfo();
        System.out.println("Major: " + major);
        System.out.println("Books borrowed: " + booksBorrowed);
    }
}

class Faculty extends LibraryUser {
    private String department;
    private int booksReserved;
    
    public Faculty(String name, String userId, String department) {
        super(name, userId, "Faculty");
        this.department = department;
        this.booksReserved = 0;
    }
    
    public void reserveBook(String bookTitle) {
        booksReserved = booksReserved + 1;
        System.out.println("📖 " + name + " reserved: " + bookTitle);
        System.out.println("   Books reserved: " + booksReserved);
    }
    
    public void accessResearchDatabase() {
        System.out.println("🔬 " + name + " is accessing research databases");
    }
    
    public void showBasicInfo() {
        super.showBasicInfo();
        System.out.println("Department: " + department);
        System.out.println("Books reserved: " + booksReserved);
    }
}

class Guest extends LibraryUser {
    private String visitPurpose;
    
    public Guest(String name, String userId, String visitPurpose) {
        super(name, userId, "Guest");
        this.visitPurpose = visitPurpose;
    }
    
    public void showBasicInfo() {
        super.showBasicInfo();
        System.out.println("Visit purpose: " + visitPurpose);
        System.out.println("Access level: Browse only");
    }
}

public class LibrarySystemApp {
    
    public static void processUser(LibraryUser user) {
        System.out.println("Processing library user:");
        user.logEntry();
        user.showBasicInfo();
        user.browseBooks();
        System.out.println();
    }
    
    public static void main(String[] args) {
        System.out.println("University Library System");
        System.out.println();
        
        Student student1 = new Student("Alice Johnson", "S001", "Computer Science");
        Faculty faculty1 = new Faculty("Dr. Smith", "F001", "Mathematics");
        Guest guest1 = new Guest("John Visitor", "G001", "Research");
        Student student2 = new Student("Bob Wilson", "S002", "Physics");
        
        LibraryUser[] allUsers = new LibraryUser[4];
        allUsers[0] = student1;
        allUsers[1] = faculty1;
        allUsers[2] = guest1;
        allUsers[3] = student2;
        
        System.out.println("Daily Entry Log (Upcasting in action):");
        System.out.println();
        
        for (int i = 0; i < allUsers.length; i++) {
            processUser(allUsers[i]);
        }
        
        System.out.println("Specific user activities:");
        System.out.println();
        
        student1.borrowBook("Java Programming");
        student1.borrowBook("Data Structures");
        student1.accessComputer();
        System.out.println();
        
        faculty1.reserveBook("Advanced Mathematics");
        faculty1.accessResearchDatabase();
        System.out.println();
        
        System.out.println("Guest activities:");
        guest1.browseBooks();
        System.out.println();
        
        System.out.println("Upcasting allows us to:");
        System.out.println("- Store different user types in the same array");
        System.out.println("- Process all users with the same method");
        System.out.println("- Access common features safely");
        System.out.println("- Treat specialists as generalists when needed");
    }
}