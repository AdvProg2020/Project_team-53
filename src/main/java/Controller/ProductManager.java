package Controller;

import Model.Account.BuyerAccount;
import Model.Product.CommentAndScore.Comment;
import Model.Product.CommentAndScore.Score;
import Model.Product.Product;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ProductManager {
    private static Product product = null;

    public static void setProduct(Product setTo) {
        product = setTo;
    }

    public static Product getProduct() {
        return product;
    }

    public static String giveScore(int score){
        BuyerAccount account = (BuyerAccount) AccountManager.getLoggedInAccount();
        if (!account.buyedProduct(product.getProductId())){
            return "You have to buy product to give score.";
        }
        product.giveScore(new Score(score , account.getUsername()));
        return "Your Score submitted.";
    }

    public static double showAverageScore(){
        return product.getAverageScore();
    }

    public static String digest(){
        return product.digest();
    }

    public static String showFullInfo(){
        return product.showAllInfo();
    }

    public static Pane showFullInfoGraphic(int productId){
        Product product = Database.getProductByID(productId);

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Image image = null;
        try {
            image = new Image(new FileInputStream("src\\resource\\ProductImages\\" + product.getProductId() + ".png"));
        }catch (Exception e){
            try {
                image = new Image(new FileInputStream("src\\resource\\ProductImages\\notFound.png"));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        ImageView productImage = new ImageView(image);
        productImage.setFitHeight(100);
        productImage.setFitWidth(100);

        Label name = new Label("Name : " + product.getName());
        Label averageScore = new Label("Average Score : " + product.getAverageScore());
        Label price = new Label("Price : " + product.getPrice());
        Label category = new Label("Category : " + product.getCategoryName());
        Label status = new Label("Status : " + product.getStatus());
        Label sellerUsername = new Label("Seller : " + product.getSellerUsername());
        Label available = new Label("Available : " + product.isAvailable());
        Label number = new Label("Number : " + product.getNumber());
        Label descriptionTag = new Label("Description : ");
        Label description = new Label(product.getDescription());

        GridPane.setConstraints(productImage, 0 , 0 , 2 , 1);
        GridPane.setConstraints(name, 0, 1 , 2 , 1);
        GridPane.setConstraints(averageScore, 0, 2 , 2 , 1);
        GridPane.setConstraints(price, 0, 3 , 2 , 1);
        GridPane.setConstraints(category, 0, 4 , 2 , 1);
        GridPane.setConstraints(status, 0, 5 , 2 , 1);
        GridPane.setConstraints(sellerUsername, 0, 6 , 2 , 1);
        GridPane.setConstraints(available, 0, 7 , 2 , 1);
        GridPane.setConstraints(number, 0, 8 , 2 , 1);
        GridPane.setConstraints(descriptionTag, 0, 9 , 2 , 1);
        GridPane.setConstraints(description, 1, 10 , 2 , 3);

        gridPane.getChildren().addAll(productImage, name, averageScore, price, category, status,sellerUsername,available,number,descriptionTag,description);

        return gridPane;
    }

    public static String compare(int productId){
        Product secondPro = Database.getProductByID(productId);
        return product.compareWith(secondPro);
    }

    public static String giveComment(String title, String content){
        BuyerAccount buyerAccount = (BuyerAccount) AccountManager.getLoggedInAccount();
        product.addComment(new Comment(title, content, buyerAccount.getUsername(), buyerAccount.buyedProduct(product.getProductId())));
        return "Your comment registered.";
    }

    public static String viewAllComments(){
        if (product == null)
            return "You have to choose product first";
        return product.showComments();
    }
}
