import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

// Book class with all required attributes
class Book {
    private String bookId;
    private String title;
    private String author;
    private String isbn;
    private String category;
    private boolean isIssued;
    private String issueDate;
    private String dueDate;
    private String issuedToMemberId; // To track which member has the book
    
    // Constructor
    public Book(String bookId, String title, String author, String isbn, String category) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.isIssued = false;
        this.issueDate = "";
        this.dueDate = "";
        this.issuedToMemberId = "";
    }
    
    // Getters and Setters
    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public String getCategory() { return category; }
    public boolean isIssued() { return isIssued; }
    public String getIssueDate() { return issueDate; }
    public String getDueDate() { return dueDate; }
    public String getIssuedToMemberId() { return issuedToMemberId; }
    
    public void setIssued(boolean issued) { this.isIssued = issued; }
    public void setIssueDate(String issueDate) { this.issueDate = issueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }
    public void setIssuedToMemberId(String memberId) { this.issuedToMemberId = memberId; }
    
    @Override
    public String toString() {
        return String.format("Book[ID: %s, Title: %s, Author: %s, ISBN: %s, Category: %s, Issued: %s]",
                bookId, title, author, isbn, category, isIssued ? "Yes" : "No");
    }
}

// Member class with all required attributes
class Member {
    private String memberId;
    private String memberName;
    private String memberType;
    private ArrayList<Book> booksIssued;
    private double totalFines;
    private String membershipDate;
    
    // Constructor
    public Member(String memberId, String memberName, String memberType, String membershipDate) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberType = memberType;
        this.booksIssued = new ArrayList<>();
        this.totalFines = 0.0;
        this.membershipDate = membershipDate;
    }
    
    // Getters and Setters
    public String getMemberId() { return memberId; }
    public String getMemberName() { return memberName; }
    public String getMemberType() { return memberType; }
    public ArrayList<Book> getBooksIssued() { return booksIssued; }
    public double getTotalFines() { return totalFines; }
    public String getMembershipDate() { return membershipDate; }
    
    public void setTotalFines(double totalFines) { this.totalFines = totalFines; }
    public void addFine(double fine) { this.totalFines += fine; }
    
    public void addBook(Book book) { this.booksIssued.add(book); }
    public void removeBook(Book book) { this.booksIssued.remove(book); }
    
    @Override
    public String toString() {
        return String.format("Member[ID: %s, Name: %s, Type: %s, Books Issued: %d, Total Fines: $%.2f]",
                memberId, memberName, memberType, booksIssued.size(), totalFines);
    }
}

// Main Library Management System class
public class LibraryManagementSystem {
    // Static variables as required
    private static int totalBooks = 0;
    private static int totalMembers = 0;
    private static String libraryName = "Central City Library";
    private static double finePerDay = 2.0; // $2 per day
    private static int maxBooksAllowed = 3;
    
    // Instance variables
    private HashMap<String, Book> books;
    private HashMap<String, Member> members;
    private HashMap<String, ArrayList<String>> reservations; // bookId -> list of memberIds
    private DateTimeFormatter dateFormatter;
    
    // Constructor
    public LibraryManagementSystem() {
        this.books = new HashMap<>();
        this.members = new HashMap<>();
        this.reservations = new HashMap<>();
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }
    
    // Add a new book to the library
    public boolean addBook(String bookId, String title, String author, String isbn, String category) {
        if (books.containsKey(bookId)) {
            System.out.println("Book with ID " + bookId + " already exists!");
            return false;
        }
        
        Book newBook = new Book(bookId, title, author, isbn, category);
        books.put(bookId, newBook);
        totalBooks++;
        System.out.println("Book added successfully: " + newBook.getTitle());
        return true;
    }
    
    // Add a new member to the library
    public boolean addMember(String memberId, String memberName, String memberType) {
        if (members.containsKey(memberId)) {
            System.out.println("Member with ID " + memberId + " already exists!");
            return false;
        }
        
        String currentDate = LocalDate.now().format(dateFormatter);
        Member newMember = new Member(memberId, memberName, memberType, currentDate);
        members.put(memberId, newMember);
        totalMembers++;
        System.out.println("Member added successfully: " + newMember.getMemberName());
        return true;
    }
    
    // Issue a book to a member
    public boolean issueBook(String bookId, String memberId) {
        Book book = books.get(bookId);
        Member member = members.get(memberId);
        
        // Validation checks
        if (book == null) {
            System.out.println("Book not found!");
            return false;
        }
        if (member == null) {
            System.out.println("Member not found!");
            return false;
        }
        if (book.isIssued()) {
            System.out.println("Book is already issued!");
            return false;
        }
        if (member.getBooksIssued().size() >= maxBooksAllowed) {
            System.out.println("Member has reached maximum book limit!");
            return false;
        }
        
        // Issue the book
        LocalDate currentDate = LocalDate.now();
        LocalDate dueDate = currentDate.plusDays(14); // 2 weeks lending period
        
        book.setIssued(true);
        book.setIssueDate(currentDate.format(dateFormatter));
        book.setDueDate(dueDate.format(dateFormatter));
        book.setIssuedToMemberId(memberId);
        
        member.addBook(book);
        
        System.out.println("Book issued successfully!");
        System.out.println("Book: " + book.getTitle() + " issued to " + member.getMemberName());
        System.out.println("Due date: " + book.getDueDate());
        
        return true;
    }
    
    // Return a book and calculate fine if overdue
    public boolean returnBook(String bookId) {
        Book book = books.get(bookId);
        
        if (book == null) {
            System.out.println("Book not found!");
            return false;
        }
        if (!book.isIssued()) {
            System.out.println("Book is not currently issued!");
            return false;
        }
        
        Member member = members.get(book.getIssuedToMemberId());
        double fine = calculateFine(book);
        
        // Update book status
        book.setIssued(false);
        book.setIssueDate("");
        book.setDueDate("");
        String returnedFromMember = book.getIssuedToMemberId();
        book.setIssuedToMemberId("");
        
        // Update member's issued books
        member.removeBook(book);
        
        // Add fine if applicable
        if (fine > 0) {
            member.addFine(fine);
            System.out.println("Book returned with fine of $" + String.format("%.2f", fine));
        } else {
            System.out.println("Book returned successfully with no fine!");
        }
        
        System.out.println("Book: " + book.getTitle() + " returned by " + member.getMemberName());
        
        // Check if anyone has reserved this book
        processReservations(bookId);
        
        return true;
    }
    
    // Calculate fine for overdue book
    public double calculateFine(Book book) {
        if (!book.isIssued() || book.getDueDate().isEmpty()) {
            return 0.0;
        }
        
        LocalDate dueDate = LocalDate.parse(book.getDueDate(), dateFormatter);
        LocalDate currentDate = LocalDate.now();
        
        if (currentDate.isAfter(dueDate)) {
            long overdueDays = ChronoUnit.DAYS.between(dueDate, currentDate);
            return overdueDays * finePerDay;
        }
        
        return 0.0;
    }
    
    // Renew a book (extend due date by 14 days)
    public boolean renewBook(String bookId) {
        Book book = books.get(bookId);
        
        if (book == null) {
            System.out.println("Book not found!");
            return false;
        }
        if (!book.isIssued()) {
            System.out.println("Book is not currently issued!");
            return false;
        }
        
        // Check if book has reservations
        if (reservations.containsKey(bookId) && !reservations.get(bookId).isEmpty()) {
            System.out.println("Cannot renew book - it has pending reservations!");
            return false;
        }
        
        // Check for existing fines
        Member member = members.get(book.getIssuedToMemberId());
        double currentFine = calculateFine(book);
        if (currentFine > 0) {
            System.out.println("Cannot renew book - please pay outstanding fine of $" + 
                             String.format("%.2f", currentFine) + " first!");
            return false;
        }
        
        // Renew the book
        LocalDate newDueDate = LocalDate.parse(book.getDueDate(), dateFormatter).plusDays(14);
        book.setDueDate(newDueDate.format(dateFormatter));
        
        System.out.println("Book renewed successfully! New due date: " + book.getDueDate());
        return true;
    }
    
    // Search books by title, author, or category
    public ArrayList<Book> searchBooks(String searchTerm, String searchType) {
        ArrayList<Book> results = new ArrayList<>();
        
        for (Book book : books.values()) {
            boolean matches = false;
            
            switch (searchType.toLowerCase()) {
                case "title":
                    matches = book.getTitle().toLowerCase().contains(searchTerm.toLowerCase());
                    break;
                case "author":
                    matches = book.getAuthor().toLowerCase().contains(searchTerm.toLowerCase());
                    break;
                case "category":
                    matches = book.getCategory().toLowerCase().contains(searchTerm.toLowerCase());
                    break;
                case "isbn":
                    matches = book.getIsbn().equals(searchTerm);
                    break;
                default:
                    // Search all fields
                    matches = book.getTitle().toLowerCase().contains(searchTerm.toLowerCase()) ||
                             book.getAuthor().toLowerCase().contains(searchTerm.toLowerCase()) ||
                             book.getCategory().toLowerCase().contains(searchTerm.toLowerCase());
            }
            
            if (matches) {
                results.add(book);
            }
        }
        
        return results;
    }
    
    // Reserve a book
    public boolean reserveBook(String bookId, String memberId) {
        Book book = books.get(bookId);
        Member member = members.get(memberId);
        
        if (book == null) {
            System.out.println("Book not found!");
            return false;
        }
        if (member == null) {
            System.out.println("Member not found!");
            return false;
        }
        if (!book.isIssued()) {
            System.out.println("Book is available - no need to reserve!");
            return false;
        }
        
        // Initialize reservation list if doesn't exist
        reservations.putIfAbsent(bookId, new ArrayList<>());
        
        // Check if member already has reservation for this book
        if (reservations.get(bookId).contains(memberId)) {
            System.out.println("You already have a reservation for this book!");
            return false;
        }
        
        // Add reservation
        reservations.get(bookId).add(memberId);
        System.out.println("Book reserved successfully! You are #" + 
                          reservations.get(bookId).size() + " in the queue.");
        
        return true;
    }
    
    // Process reservations when a book is returned
    private void processReservations(String bookId) {
        if (reservations.containsKey(bookId) && !reservations.get(bookId).isEmpty()) {
            String nextMemberId = reservations.get(bookId).remove(0);
            Member nextMember = members.get(nextMemberId);
            
            System.out.println("\nNotification: Book " + books.get(bookId).getTitle() + 
                             " is now available for " + nextMember.getMemberName() + 
                             " (Reservation ID: " + nextMemberId + ")");
        }
    }
    
    // Display all overdue books with fines
    public void displayOverdueBooks() {
        System.out.println("\n=== OVERDUE BOOKS ===");
        boolean hasOverdueBooks = false;
        
        for (Book book : books.values()) {
            if (book.isIssued()) {
                double fine = calculateFine(book);
                if (fine > 0) {
                    hasOverdueBooks = true;
                    Member member = members.get(book.getIssuedToMemberId());
                    System.out.println("Book: " + book.getTitle());
                    System.out.println("Issued to: " + member.getMemberName());
                    System.out.println("Due Date: " + book.getDueDate());
                    System.out.println("Fine: $" + String.format("%.2f", fine));
                    System.out.println("---");
                }
            }
        }
        
        if (!hasOverdueBooks) {
            System.out.println("No overdue books!");
        }
    }
    
    // Display library statistics
    public void displayLibraryStats() {
        System.out.println("\n=== LIBRARY STATISTICS ===");
        System.out.println("Library Name: " + libraryName);
        System.out.println("Total Books: " + totalBooks);
        System.out.println("Total Members: " + totalMembers);
        System.out.println("Books Available: " + (totalBooks - getCurrentlyIssuedBooksCount()));
        System.out.println("Books Issued: " + getCurrentlyIssuedBooksCount());
        System.out.println("Fine per Day: $" + finePerDay);
        System.out.println("Max Books Allowed per Member: " + maxBooksAllowed);
    }
    
    private int getCurrentlyIssuedBooksCount() {
        int count = 0;
        for (Book book : books.values()) {
            if (book.isIssued()) count++;
        }
        return count;
    }
    
    // Display member details with current books and fines
    public void displayMemberDetails(String memberId) {
        Member member = members.get(memberId);
        if (member == null) {
            System.out.println("Member not found!");
            return;
        }
        
        System.out.println("\n=== MEMBER DETAILS ===");
        System.out.println(member);
        System.out.println("Membership Date: " + member.getMembershipDate());
        System.out.println("\nCurrently Issued Books:");
        
        if (member.getBooksIssued().isEmpty()) {
            System.out.println("No books currently issued.");
        } else {
            for (Book book : member.getBooksIssued()) {
                double fine = calculateFine(book);
                System.out.println("- " + book.getTitle() + " (Due: " + book.getDueDate() + 
                                 (fine > 0 ? ", Fine: $" + String.format("%.2f", fine) : "") + ")");
            }
        }
    }
    
    // Main method with demo
    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();
        
        System.out.println("=== LIBRARY MANAGEMENT SYSTEM DEMO ===\n");
        
        // Add some books
        library.addBook("B001", "The Great Gatsby", "F. Scott Fitzgerald", "978-0-7432-7356-5", "Fiction");
        library.addBook("B002", "To Kill a Mockingbird", "Harper Lee", "978-0-06-112008-4", "Fiction");
        library.addBook("B003", "1984", "George Orwell", "978-0-452-28423-4", "Dystopian");
        library.addBook("B004", "Java Programming", "James Gosling", "978-0-13-148398-0", "Technology");
        
        // Add some members
        library.addMember("M001", "John Doe", "Student");
        library.addMember("M002", "Jane Smith", "Faculty");
        library.addMember("M003", "Bob Johnson", "Staff");
        
        // Display initial stats
        library.displayLibraryStats();
        
        // Issue some books
        System.out.println("\n=== ISSUING BOOKS ===");
        library.issueBook("B001", "M001");
        library.issueBook("B002", "M001");
        library.issueBook("B003", "M002");
        
        // Try to issue more than allowed
        library.issueBook("B004", "M001"); // Should work (3rd book)
        
        // Search for books
        System.out.println("\n=== SEARCHING BOOKS ===");
        ArrayList<Book> searchResults = library.searchBooks("Fiction", "category");
        System.out.println("Books in Fiction category:");
        for (Book book : searchResults) {
            System.out.println(book);
        }
        
        // Try to reserve a book
        System.out.println("\n=== RESERVING BOOKS ===");
        library.reserveBook("B001", "M002");
        library.reserveBook("B001", "M003");
        
        // Display member details
        library.displayMemberDetails("M001");
        
        // Renew a book
        System.out.println("\n=== RENEWING BOOK ===");
        library.renewBook("B001");
        
        // Return a book
        System.out.println("\n=== RETURNING BOOK ===");
        library.returnBook("B001");
        
        // Display overdue books (none in this demo as books were just issued)
        library.displayOverdueBooks();
        
        // Final stats
        library.displayLibraryStats();
        
        System.out.println("\n=== DEMO COMPLETED ===");
    }
}