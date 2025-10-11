class BookDetails {
    String title;
    String author;

    BookDetails(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof BookDetails)) return false;

        BookDetails b = (BookDetails) obj;
        return (title == null ? b.title == null : title.equals(b.title))
                && (author == null ? b.author == null : author.equals(b.author));
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (title == null ? 0 : title.hashCode());
        result = 31 * result + (author == null ? 0 : author.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "BookDetails{title='" + title + "', author='" + author + "'}";
    }
}

public class Main {
    public static void main(String[] args) {
        BookDetails b1 = new BookDetails("Java", "John");
        BookDetails b2 = new BookDetails("Java", "John");
        BookDetails b3 = b1;
        BookDetails b4 = new BookDetails    ("Python", "Jane");

        System.out.println("b1 == b2: " + (b1 == b2));
        System.out.println("b1.equals(b2): " + b1.equals(b2));
        System.out.println();

        System.out.println("b1 == b3: " + (b1 == b3));
        System.out.println("b1.equals(b3): " + b1.equals(b3));
        System.out.println();

        System.out.println("b1 == b4: " + (b1 == b4));
        System.out.println("b1.equals(b4): " + b1.equals(b4));
        System.out.println();

        System.out.println("b1 == null: " + (b1 == null));
        System.out.println("b1.equals(null): " + b1.equals(null));
    }
}