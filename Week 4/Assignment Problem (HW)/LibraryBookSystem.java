class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;
    
    public Book() {
        this.title = "Unknown";
        this.author = "Unknown";
        this.isbn = "Unknown";
        this.isAvailable = true;
    }
    
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isbn = "Unknown";
        this.isAvailable = true;
    }
    
    public Book(String title, String author, String isbn, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = isAvailable;
    }
    
    public void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("Book borrowed successfully: " + title);
        } else {
            System.out.println("Book is already borrowed: " + title);
        }
    }
    
    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println("Book returned successfully: " + title);
        } else {
            System.out.println("Book is already available: " + title);
        }
    }
    
    public void displayBookInfo() {
        System.out.println("BOOK INFORMATION");
        System.out.println("Title      : " + title);
        System.out.println("Author     : " + author);
        System.out.println("ISBN       : " + isbn);
        System.out.println("Status     : " + (isAvailable ? "Available" : "Borrowed"));
        System.out.println();
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }
}

public class LibraryBookSystem {
    public static void main(String[] args) {
        System.out.println("LIBRARY BOOK MANAGEMENT SYSTEM");
        System.out.println();
        
        System.out.println("1. Book created with Default Constructor:");
        Book book1 = new Book();
        book1.displayBookInfo();
        
        System.out.println("2. Book created with Title and Author:");
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee");
        book2.displayBookInfo();
        
        System.out.println("3. Book created with All Details:");
        Book book3 = new Book("1984", "George Orwell", "978-0-452-28423-4", true);
        book3.displayBookInfo();
        
        System.out.println("BOOK BORROWING OPERATIONS:");
        System.out.println();
        
        System.out.println("Attempting to borrow books:");
        book1.borrowBook();
        book2.borrowBook();
        book3.borrowBook();
        
        System.out.println();
        System.out.println("Status after borrowing:");
        book1.displayBookInfo();
        book2.displayBookInfo();
        book3.displayBookInfo();
        
        System.out.println("Attempting to borrow already borrowed books:");
        book1.borrowBook();
        book2.borrowBook();
        
        System.out.println();
        System.out.println("BOOK RETURNING OPERATIONS:");
        System.out.println();
        
        System.out.println("Returning books:");
        book1.returnBook();
        book2.returnBook();
        
        System.out.println();
        System.out.println("Status after returning:");
        book1.displayBookInfo();
        book2.displayBookInfo();
        
        System.out.println("Attempting to return available book:");
        book1.returnBook();
        
        System.out.println();
        System.out.println("MULTIPLE BOOKS DEMONSTRATION:");
        System.out.println();
        
        Book[] libraryBooks = {
            new Book("The Great Gatsby", "F. Scott Fitzgerald", "978-0-7432-7356-5", true),
            new Book("Pride and Prejudice", "Jane Austen", "978-0-14-143951-8", true),
            new Book("The Catcher in the Rye", "J.D. Salinger"),
            new Book("Harry Potter", "J.K. Rowling", "978-0-439-70818-8", false),
            new Book()
        };
        
        System.out.println("Initial library status:");
        for (int i = 0; i < libraryBooks.length; i++) {
            System.out.println("Book " + (i + 1) + ":");
            libraryBooks[i].displayBookInfo();
        }
        
        System.out.println("Library operations:");
        System.out.println();
        
        libraryBooks[0].borrowBook();
        libraryBooks[1].borrowBook();
        libraryBooks[2].borrowBook();
        libraryBooks[3].returnBook();
        libraryBooks[4].borrowBook();
        
        System.out.println();
        System.out.println("Final library status:");
        for (int i = 0; i < libraryBooks.length; i++) {
            System.out.println("Book " + (i + 1) + ":");
            libraryBooks[i].displayBookInfo();
        }
        
        System.out.println("AVAILABLE BOOKS COUNT:");
        int availableCount = 0;
        int borrowedCount = 0;
        
        for (Book book : libraryBooks) {
            if (book.isAvailable()) {
                availableCount++;
            } else {
                borrowedCount++;
            }
        }
        
        System.out.println("Total books: " + libraryBooks.length);
        System.out.println("Available books: " + availableCount);
        System.out.println("Borrowed books: " + borrowedCount);
        
        System.out.println();
        System.out.println("CONSTRUCTOR OVERLOADING DEMONSTRATION:");
        System.out.println("Default Constructor: Book()");
        System.out.println("Two Parameters: Book(String title, String author)");
        System.out.println("Four Parameters: Book(String title, String author, String isbn, boolean isAvailable)");
        System.out.println();
        System.out.println("All constructors successfully demonstrated!");
    }
}