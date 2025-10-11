class Product {
    int productId;
    String productName;

    Product(int productId, String productName) {
        this.productId = productId;
        this.productName = productName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        
        Product p = (Product) obj;
        return productId == p.productId;
    }

    @Override
    public String toString() {
        return "Product[ID=" + productId + ", Name=" + productName + "]";
    }
}

public class ProductComparisonDemo {
    public static void main(String[] args) {
        Product p1 = new Product(101, "Laptop");
        Product p2 = new Product(101, "Laptop");
        Product p3 = p1;
        Product p4 = new Product(102, "Mouse");

        System.out.println("p1: " + p1);
        System.out.println("p2: " + p2);
        System.out.println("p1 == p2: " + (p1 == p2));
        System.out.println("p1.equals(p2): " + p1.equals(p2));
        System.out.println();

        System.out.println("p1: " + p1);
        System.out.println("p3: " + p3);
        System.out.println("p1 == p3: " + (p1 == p3));
        System.out.println("p1.equals(p3): " + p1.equals(p3));
        System.out.println();

        System.out.println("p1: " + p1);
        System.out.println("p4: " + p4);
        System.out.println("p1 == p4: " + (p1 == p4));
        System.out.println("p1.equals(p4): " + p1.equals(p4));
    }
}