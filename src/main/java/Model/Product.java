package Model;

public class Product {
    static int numberOfAllProducts=1;
    int productId;
    String status;
    String name;
    String sellerUsername;
    boolean available;
    int number;
    String description;
    double averageScore;
    String categoryName;

    public Product(String status, String name, String sellerUsername, boolean available, int number, String description , String categoryName) {
        this.status = status;
        this.name = name;
        this.sellerUsername = sellerUsername;
        this.available = available;
        this.number = number;
        this.description = description;
        this.productId = numberOfAllProducts;
        this.categoryName = categoryName;
        numberOfAllProducts++;
        averageScore = 0;
    }


}
