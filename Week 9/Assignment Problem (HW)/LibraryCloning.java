import java.util.ArrayList;

class Book {
    String title;
    String author;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return title + " by " + author;
    }
}

class Library implements Cloneable {
    ArrayList<Book> books;

    Library() {
        books = new ArrayList<>();
    }

    void addBook(Book book) {
        books.add(book);
    }

    @Override
    public Library clone() throws CloneNotSupportedException {
        return (Library) super.clone();
    }

    public Library deepClone() {
        Library newLibrary = new Library();
        for (Book book : this.books) {
            newLibrary.addBook(new Book(book.title, book.author));
        }
        return newLibrary;
    }

    void displayBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }
}

public class LibraryCloning {
    public static void main(String[] args) throws CloneNotSupportedException {
        Library lib1 = new Library();
        lib1.addBook(new Book("Java Basics", "John"));
        lib1.addBook(new Book("Python Guide", "Sarah"));

        Library lib2 = lib1.clone();
        Library lib3 = lib1.deepClone();

        System.out.println("Original Library:");
        lib1.displayBooks();
        System.out.println();

        lib1.books.get(0).title = "Advanced Java";

        System.out.println("After modifying original:");
        System.out.println("Original Library:");
        lib1.displayBooks();
        System.out.println();

        System.out.println("Shallow Copy Library:");
        lib2.displayBooks();
        System.out.println();

        System.out.println("Deep Copy Library:");
        lib3.displayBooks();
    }
}