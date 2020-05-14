package Model;

import java.util.ArrayList;

public class Product {
    static int numberOfAllProducts=1;
    int productId;
    String status;
    String name;
    boolean available;
    int number;
    String description;
    double averageScore;
    String categoryName;
    int price;
    ArrayList<Score> scores = new ArrayList<>();
    ArrayList<String> sellerUsernames = new ArrayList<>();

    public Product(String status, String name, String sellerUsername, boolean available, int number, String description , String categoryName , int price) {
        this.status = status;
        this.name = name;
        this.sellerUsernames.add(sellerUsername);
        this.available = available;
        this.number = number;
        this.description = description;
        this.productId = numberOfAllProducts;
        this.categoryName = categoryName;
        this.price = price;
        numberOfAllProducts++;
        averageScore = 0;
    }

    public int getProductId() {
        return productId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ArrayList<String> getSellerUsername() {
        return sellerUsernames;
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

    public boolean isAvailable() {
        return available;
    }

    public String getDescription() {
        return description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void giveScore(Score score) {
        double sum = averageScore * scores.size();
        sum += score.getScore();
        scores.add(score);
        averageScore = sum/scores.size();
    }

    public double getAverageScore() {
        return averageScore;
    }

    public String digest() {
        return  "   ID= " + productId + '\n' +
                "   name= " + name + '\n' +
                "   sellerUsernames= " + sellerUsernames + '\n' +
                "   description= " + description + '\n' +
                "   averageScore= " + averageScore + '\n' +
                "   price= " + price + '\n';

    }

    public String showAllInfo() {
        // TODO: 14-May-20 After adding comments
        return "productId=" + productId + '\n' +
                "status=" + status + '\n' +
                "name=" + name + '\n' +
                "sellerUsernames=" + sellerUsernames + '\n' +
                "available=" + available + '\n'+
                "number=" + number + '\n' +
                "description=" + description + '\n' +
                "averageScore=" + averageScore + '\n' +
                "categoryName=" + categoryName + '\n' +
                "price=" + price + '\n' ;

    }

    public String compareWith(Product secondPro) {
        return  "name : " + this.getName() + " ---- " + secondPro.getName() +'\n' +
                "sellers : " + this.getSellerUsername() + " ---- " + secondPro.getSellerUsername() +'\n' +
                "number : " + this.getNumber() + " ---- " + secondPro.getNumber() +'\n' +
                "available : " + this.isAvailable() + " ---- " + secondPro.isAvailable() +'\n' +
                "description : " + this.getDescription() + " ---- " + secondPro.getDescription() +'\n' +
                "averageScore : " + this.getAverageScore() + " ---- " + secondPro.getAverageScore() +'\n' +
                "categoryName : " + this.getCategoryName() + " ---- " + secondPro.getCategoryName() +'\n' +
                "price : " + this.getPrice() + " ---- " + secondPro.getPrice() +'\n' ;

    }

    public void addSeller(String changeTo) {
        sellerUsernames.add(changeTo);
    }

    public boolean isSeller(String username) {
        for (String seller : sellerUsernames) {
            if (username.equalsIgnoreCase(seller))
                return true;
        }
        return false;
    }
}
