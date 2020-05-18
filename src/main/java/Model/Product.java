package Model;

import Controller.Database;

import java.util.ArrayList;
import java.util.Date;

public class Product {
    static int numberOfAllProducts = 1;
    int productId;
    String status;
    String name;
    boolean available;
    int number;
    String description;
    double averageScore;
    String categoryName;
    int price;
    String sellerUsername;
    ArrayList<Score> scores = new ArrayList<>();
    ArrayList<Comment> comments = new ArrayList<>();
    int offId;

    public static int getNumberOfAllProducts() {
        return numberOfAllProducts;
    }

    public static void setNumberOfAllProducts(int numberOfAllProducts) {
        Product.numberOfAllProducts = numberOfAllProducts;
    }

    public Product(String status, String name, String sellerUsername, boolean available, int number, String description, String categoryName, int price) {
        this.status = status;
        this.name = name;
        this.sellerUsername = sellerUsername;
        this.available = available;
        this.number = number;
        this.description = description;
        this.productId = numberOfAllProducts;
        this.categoryName = categoryName;
        this.price = price;
        numberOfAllProducts++;
        averageScore = 0;
        this.offId = -1;
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

    public void setOffId(int offId) {
        this.offId = offId;
    }

    public String getStatus() {
        return status;
    }

    public void setCategoryNameAndChangeCategory(String categoryName) {
        if (Database.getCategoryByName(this.getCategoryName()) != null){
            Database.getCategoryByName(this.getCategoryName()).removeProduct(this.getProductId());
        }
        Category category = Database.getCategoryByName(categoryName);
        category.addProduct(this.getProductId());
    }

    public void setCategoryNameWithoutAddToCategory(String categoryName) {
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
        averageScore = sum / scores.size();
    }

    public double getAverageScore() {
        return averageScore;
    }

    public String digest() {
        return "   ID= " + productId + '\n' +
                "   name= " + name + '\n' +
                "   sellerUsername= " + sellerUsername + '\n' +
                "   description= " + description + '\n' +
                "   averageScore= " + averageScore + '\n' +
                "   price= " + price + '\n';

    }

    public String showAllInfo() {
        // TODO: 14-May-20 After adding comments
        return "productId=" + productId + '\n' +
                "status=" + status + '\n' +
                "name=" + name + '\n' +
                "sellerUsername=" + sellerUsername + '\n' +
                "available=" + available + '\n' +
                "number=" + number + '\n' +
                "description=" + description + '\n' +
                "averageScore=" + averageScore + '\n' +
                "categoryName=" + categoryName + '\n' +
                "price=" + price + '\n';

    }

    public String showComments(){
        StringBuilder res = new StringBuilder();

        for (Comment comment : comments) {
            res.append(comment.showComment() + "\n----------------\n");
        }
        return res.toString();
    }

    public String compareWith(Product secondPro) {
        return "name : " + this.getName() + " ---- " + secondPro.getName() + '\n' +
                "seller : " + this.getSellerUsername() + " ---- " + secondPro.getSellerUsername() + '\n' +
                "number : " + this.getNumber() + " ---- " + secondPro.getNumber() + '\n' +
                "available : " + this.isAvailable() + " ---- " + secondPro.isAvailable() + '\n' +
                "description : " + this.getDescription() + " ---- " + secondPro.getDescription() + '\n' +
                "averageScore : " + this.getAverageScore() + " ---- " + secondPro.getAverageScore() + '\n' +
                "categoryName : " + this.getCategoryName() + " ---- " + secondPro.getCategoryName() + '\n' +
                "price : " + this.getPrice() + " ---- " + secondPro.getPrice() + '\n';

    }

    public boolean isSeller(String username) {
        return sellerUsername.equalsIgnoreCase(username);
    }

    public void addComment(Comment comment) {
        comments.add(comment);

    }

    public boolean doesHaveOff(){
        Off off = Database.getOffById(offId);
        if (off == null)
            return false;
        Date date = new Date();
        if (date.compareTo(off.getEndDate()) > 0 && date.compareTo(off.getStartDate()) < 0)
            return false;
        if (!off.getStatus().equalsIgnoreCase("accepted"))
            return false;
        return true;
    }

    public Off getOff(){
        return Database.getOffById(offId);
    }
}
