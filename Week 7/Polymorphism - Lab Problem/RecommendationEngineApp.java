class Product {
    protected String productId;
    protected String name;
    protected double price;
    protected String category;
    protected double rating;
    
    public Product(String productId, String name, double price, String category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.rating = 0.0;
    }
    
    public void recommend() {
        System.out.println("Basic recommendation: " + name + " - $" + price);
    }
    
    public void updateInfo(String newName) {
        this.name = newName;
        System.out.println("Product name updated to: " + newName);
    }
    
    public void updateInfo(double newPrice) {
        this.price = newPrice;
        System.out.println("Product price updated to: $" + newPrice);
    }
    
    public void updateInfo(double newPrice, double newRating) {
        this.price = newPrice;
        this.rating = newRating;
        System.out.println("Price and rating updated: $" + newPrice + " | Rating: " + newRating);
    }
    
    public void showDetails() {
        System.out.println("Product: " + name + " | ID: " + productId + " | Price: $" + price + " | Category: " + category);
    }
    
    public String getName() {
        return name;
    }
    
    public String getCategory() {
        return category;
    }
}

class Electronics extends Product {
    private int warrantyMonths;
    private String specs;
    private String brand;
    
    public Electronics(String productId, String name, double price, int warrantyMonths, String specs, String brand) {
        super(productId, name, price, "Electronics");
        this.warrantyMonths = warrantyMonths;
        this.specs = specs;
        this.brand = brand;
    }
    
    public void recommend() {
        System.out.println("Tech Recommendation: " + name + " by " + brand);
        System.out.println("Price: $" + price + " | Warranty: " + warrantyMonths + " months");
        System.out.println("Specs: " + specs);
        System.out.println("Perfect for tech enthusiasts!");
    }
    
    public void updateInfo(int newWarranty) {
        this.warrantyMonths = newWarranty;
        System.out.println("Warranty updated to: " + newWarranty + " months");
    }
    
    public void updateInfo(String newSpecs, String newBrand) {
        this.specs = newSpecs;
        this.brand = newBrand;
        System.out.println("Specs and brand updated: " + newBrand + " - " + newSpecs);
    }
    
    public void showDetails() {
        super.showDetails();
        System.out.println("Brand: " + brand + " | Warranty: " + warrantyMonths + " months | Specs: " + specs);
    }
}

class Clothing extends Product {
    private String sizes;
    private String material;
    private String style;
    
    public Clothing(String productId, String name, double price, String sizes, String material, String style) {
        super(productId, name, price, "Clothing");
        this.sizes = sizes;
        this.material = material;
        this.style = style;
    }
    
    public void recommend() {
        System.out.println("Fashion Recommendation: " + name);
        System.out.println("Price: $" + price + " | Style: " + style + " | Material: " + material);
        System.out.println("Available sizes: " + sizes);
        System.out.println("Trending fashion choice!");
    }
    
    public void updateInfo(String newSizes) {
        this.sizes = newSizes;
        System.out.println("Available sizes updated to: " + newSizes);
    }
    
    public void updateInfo(String newMaterial, String newStyle) {
        this.material = newMaterial;
        this.style = newStyle;
        System.out.println("Material and style updated: " + newMaterial + " - " + newStyle);
    }
    
    public void showDetails() {
        super.showDetails();
        System.out.println("Sizes: " + sizes + " | Material: " + material + " | Style: " + style);
    }
}

class Book extends Product {
    private String author;
    private String genre;
    private int pages;
    
    public Book(String productId, String name, double price, String author, String genre, int pages) {
        super(productId, name, price, "Books");
        this.author = author;
        this.genre = genre;
        this.pages = pages;
    }
    
    public void recommend() {
        System.out.println("Book Recommendation: " + name + " by " + author);
        System.out.println("Price: $" + price + " | Genre: " + genre + " | Pages: " + pages);
        System.out.println("Great read for " + genre + " lovers!");
    }
    
    public void updateInfo(String newAuthor) {
        this.author = newAuthor;
        System.out.println("Author updated to: " + newAuthor);
    }
    
    public void updateInfo(String newGenre, int newPages) {
        this.genre = newGenre;
        this.pages = newPages;
        System.out.println("Genre and pages updated: " + newGenre + " - " + newPages + " pages");
    }
    
    public void showDetails() {
        super.showDetails();
        System.out.println("Author: " + author + " | Genre: " + genre + " | Pages: " + pages);
    }
}

public class RecommendationEngineApp {
    
    public static void processProduct(Product product) {
        product.showDetails();
        product.recommend();
        System.out.println();
    }
    
    public static void updateProductSafely(Product product) {
        if (product instanceof Electronics) {
            Electronics electronics = (Electronics) product;
            electronics.updateInfo(24);
            electronics.updateInfo("8GB RAM, 256GB SSD", "TechBrand");
        } else if (product instanceof Clothing) {
            Clothing clothing = (Clothing) product;
            clothing.updateInfo("S, M, L, XL, XXL");
            clothing.updateInfo("Premium Cotton", "Casual");
        } else if (product instanceof Book) {
            Book book = (Book) product;
            book.updateInfo("Updated Author");
            book.updateInfo("Mystery", 350);
        } else {
            product.updateInfo("Updated Product");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        System.out.println("E-Commerce Recommendation Engine");
        System.out.println();
        
        Product[] catalog = new Product[6];
        
        catalog[0] = new Electronics("E001", "Gaming Laptop", 1299.99, 12, "16GB RAM, 512GB SSD", "GameTech");
        catalog[1] = new Clothing("C001", "Summer Dress", 79.99, "S, M, L", "Cotton", "Casual");
        catalog[2] = new Book("B001", "Java Programming", 49.99, "John Smith", "Education", 400);
        catalog[3] = new Electronics("E002", "Wireless Headphones", 199.99, 18, "Noise Canceling, 30hr Battery", "AudioMax");
        catalog[4] = new Clothing("C002", "Business Suit", 299.99, "38, 40, 42, 44", "Wool", "Formal");
        catalog[5] = new Book("B002", "Mystery Novel", 19.99, "Jane Doe", "Fiction", 320);
        
        System.out.println("Product Catalog with Dynamic Recommendations:");
        for (int i = 0; i < catalog.length; i++) {
            processProduct(catalog[i]);
        }
        
        System.out.println("Method Overloading - Different Update Operations:");
        catalog[0].updateInfo(1199.99);
        catalog[1].updateInfo("Premium Dress");
        catalog[2].updateInfo(44.99, 4.8);
        System.out.println();
        
        System.out.println("Safe Downcasting with Specific Updates:");
        for (int i = 0; i < 3; i++) {
            updateProductSafely(catalog[i]);
        }
        
        System.out.println("Updated Product Information:");
        for (int i = 0; i < 3; i++) {
            catalog[i].showDetails();
            System.out.println();
        }
        
        System.out.println("Multiple Polymorphism Integration Demonstrated:");
        System.out.println("- Method Overriding: Different recommend() behaviors");
        System.out.println("- Method Overloading: Multiple updateInfo() versions");
        System.out.println("- Dynamic Method Dispatch: Runtime method selection");
        System.out.println("- Upcasting: Mixed product array handling");
        System.out.println("- Safe Downcasting: Type-specific operations");
    }
}