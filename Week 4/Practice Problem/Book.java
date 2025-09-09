public class Book {
    String title;
    String author;
    double price;
    
    public Book() {
        this.title = "Unknown Title";
        this.author = "Unknown Author";
        this.price = 0.0;
    }
    
    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }
    
    public void display() {
        System.out.println("Book Details:");
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Price: Rs." + price);
        System.out.println();
    }
    
    public static void main(String[] args) {
        Book book1 = new Book();
        
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", 299.99);
        
        System.out.println("Book created using default constructor:");
        book1.display();
        
        System.out.println("Book created using parameterized constructor:");
        book2.display();
    }
}