class Book {
    private String bookId;
    private String title;
    private String author;
    private boolean isAvailable;
    private static int totalBooks = 0;
    private static int availableBooks = 0;
    
    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
        totalBooks++;
        availableBooks++;
        
        System.out.println("New book added to library: " + title);
    }
    
    public boolean issueBook() {
        if (isAvailable) {
            isAvailable = false;
            availableBooks--;
            System.out.println("Book issued successfully: " + title);
            return true;
        } else {
            System.out.println("Sorry, this book is already issued: " + title);
            return false;
        }
    }
    
    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            availableBooks++;
            System.out.println("Book returned successfully: " + title);
        } else {
            System.out.println("This book was not issued: " + title);
        }
    }
    
    public void displayBookInfo() {
        System.out.println("--- Book Information ---");
        System.out.println("Book ID: " + bookId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Status: " + (isAvailable ? "Available" : "Issued"));
        System.out.println("-----------------------");
    }
    
    public String getBookId() {
        return bookId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public boolean getIsAvailable() {
        return isAvailable;
    }
    
    public static int getTotalBooks() {
        return totalBooks;
    }
    
    public static int getAvailableBooks() {
        return availableBooks;
    }
}

class Member {
    private String memberId;
    private String memberName;
    private String[] booksIssued;
    private int bookCount;
    private static final int MAX_BOOKS = 5;
    
    public Member(String memberId, String memberName) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.booksIssued = new String[MAX_BOOKS];
        this.bookCount = 0;
        
        System.out.println("Welcome new member: " + memberName);
    }
    
    public boolean borrowBook(Book book) {
        if (bookCount >= MAX_BOOKS) {
            System.out.println("Sorry " + memberName + ", you already have maximum books!");
            return false;
        }
        
        if (book.getIsAvailable()) {
            if (book.issueBook()) {
                booksIssued[bookCount] = book.getBookId();
                bookCount++;
                System.out.println(memberName + " successfully borrowed: " + book.getTitle());
                return true;
            }
        } else {
            System.out.println("Sorry " + memberName + ", book is not available: " + book.getTitle());
        }
        return false;
    }
    
    public boolean returnBook(String bookId, Book[] books) {
        boolean bookFound = false;
        int bookPosition = -1;
        
        for (int i = 0; i < bookCount; i++) {
            if (booksIssued[i].equals(bookId)) {
                bookFound = true;
                bookPosition = i;
                break;
            }
        }
        
        if (!bookFound) {
            System.out.println(memberName + " does not have this book: " + bookId);
            return false;
        }
        
        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                book.returnBook();
                
                for (int i = bookPosition; i < bookCount - 1; i++) {
                    booksIssued[i] = booksIssued[i + 1];
                }
                booksIssued[bookCount - 1] = null;
                bookCount--;
                
                System.out.println(memberName + " returned book successfully!");
                return true;
            }
        }
        
        System.out.println("Book not found in library system");
        return false;
    }
    
    public void displayMemberInfo() {
        System.out.println("--- Member Information ---");
        System.out.println("Member ID: " + memberId);
        System.out.println("Name: " + memberName);
        System.out.println("Books issued: " + bookCount + "/" + MAX_BOOKS);
        
        if (bookCount > 0) {
            System.out.println("Issued book IDs:");
            for (int i = 0; i < bookCount; i++) {
                System.out.println("  - " + booksIssued[i]);
            }
        } else {
            System.out.println("No books currently issued");
        }
        System.out.println("-------------------------");
    }
    
    public String getMemberName() {
        return memberName;
    }
    
    public int getBookCount() {
        return bookCount;
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        System.out.println("Welcome to City Library Management System!");
        System.out.println("Let's set up our library...");
        
        System.out.println("\nAdding books to our library:");
        Book[] books = {
            new Book("B001", "To Kill a Mockingbird", "Harper Lee"),
            new Book("B002", "1984", "George Orwell"),
            new Book("B003", "Pride and Prejudice", "Jane Austen"),
            new Book("B004", "The Great Gatsby", "F. Scott Fitzgerald"),
            new Book("B005", "Harry Potter", "J.K. Rowling")
        };
        
        System.out.println("\nRegistering library members:");
        Member alice = new Member("M001", "Alice Johnson");
        Member bob = new Member("M002", "Bob Smith");
        Member charlie = new Member("M003", "Charlie Brown");
        
        System.out.println("\nLet's start borrowing books:");
        alice.borrowBook(books[0]);
        alice.borrowBook(books[1]);
        bob.borrowBook(books[2]);
        charlie.borrowBook(books[0]);
        
        System.out.println("\nChecking member status:");
        alice.displayMemberInfo();
        bob.displayMemberInfo();
        charlie.displayMemberInfo();
        
        System.out.println("\nLet's see our book status:");
        for (Book book : books) {
            book.displayBookInfo();
        }
        
        System.out.println("\nTime to return some books:");
        alice.returnBook("B001", books);
        alice.returnBook("B999", books);
        charlie.returnBook("B001", books);
        
        System.out.println("\nNow Charlie can borrow the returned book:");
        charlie.borrowBook(books[0]);
        
        System.out.println("\nLet's test maximum book limit:");
        alice.borrowBook(books[2]);
        alice.borrowBook(books[3]);
        alice.borrowBook(books[4]);
        alice.borrowBook(books[0]);
        
        System.out.println("\nFinal library statistics:");
        System.out.println("Total books in library: " + Book.getTotalBooks());
        System.out.println("Available books: " + Book.getAvailableBooks());
        
        System.out.println("\nFinal member status:");
        alice.displayMemberInfo();
        bob.displayMemberInfo();
        charlie.displayMemberInfo();
        
        System.out.println("\nThanks for using our library system!");
    }
}