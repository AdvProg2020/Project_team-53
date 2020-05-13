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

    public int getProductId() {
        return productId;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategoryName(String categoryName) {
        // TODO: 12-May-20
        Category category = Category.getCategoryByName(this.categoryName);
        category.removeProduct(this.productId);
        Category.getCategoryByName(categoryName).addProduct(this.productId);
        this.categoryName = categoryName;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }
}
