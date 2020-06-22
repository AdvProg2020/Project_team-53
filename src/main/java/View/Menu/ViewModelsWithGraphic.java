package View.Menu;

import Controller.AccountManager;
import Controller.Database;
import Model.Account.Account;
import Model.Account.BuyerAccount;
import Model.Account.SellerAccount;
import Model.Product.CommentAndScore.Comment;
import Model.Log.BuyLog;
import Model.Log.Log;
import Model.Log.SellLog;
import Model.Product.Category;
import Model.Product.DiscountAndOff.Discount;
import Model.Product.DiscountAndOff.Off;
import Model.Product.Product;
import Model.Request.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;

public class ViewModelsWithGraphic {


    public static GridPane viewDiscount(Integer discountId) {
        Discount discount = Database.getDiscountById(discountId);

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Label disID = new Label("DiscountId : " + discount.getDiscountId());
        Label maxValue = new Label("Max Value : " + discount.getMaxValue());
        Label percent = new Label("Percent : " + discount.getPercent());
        Label startDate = new Label("Start Date : " + discount.getStartDate());
        Label endDate = new Label("End Date : " + discount.getEndDate());
        int x = discount.getNumberOfTimes();
        if (AccountManager.getLoggedInAccount() instanceof BuyerAccount)
            x -= ((BuyerAccount) AccountManager.getLoggedInAccount()).getNumberOfUse(discountId);
        Label remainigTimes = new Label("Times Of Use : " + x);


        GridPane.setConstraints(disID, 0 , 0);
        GridPane.setConstraints(maxValue, 0, 1);
        GridPane.setConstraints(percent, 0, 2);
        GridPane.setConstraints(startDate, 0, 3);
        GridPane.setConstraints(endDate, 0, 4);
        GridPane.setConstraints(remainigTimes, 0, 5);

        gridPane.getChildren().addAll(disID, maxValue, percent, startDate, endDate, remainigTimes);

        return gridPane;

    }

    public static Pane viewPersonalInfoInGraphic(String name) {
        Account account = Database.getAccountByUsername(name);

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Image image = null;
        try {
            image = new Image(new FileInputStream("src\\resource\\ProfileImages\\" + account.getUsername() + ".png"));
        }catch (Exception e){
            try {
                image = new Image(new FileInputStream("src\\resource\\ProfileImages\\notFound.png"));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        ImageView profileImage = new ImageView(image);
        profileImage.setFitHeight(100);
        profileImage.setFitWidth(100);

        Label username = new Label("Username : " + account.getUsername());
        Label firstName = new Label("First Name : " + account.getFirstName());
        Label lastName = new Label("Last Name : " + account.getLastName());
        Label email = new Label("Email : " + account.getEmail());
        Label phoneNumber = new Label("Phone : " + account.getPhoneNumber());
        Label credit = new Label("Credit : " + account.getCredit());

        GridPane.setConstraints(profileImage, 0, 0, 2, 6);
        GridPane.setConstraints(username, 2, 0);
        GridPane.setConstraints(firstName, 2, 1);
        GridPane.setConstraints(lastName, 2,2);
        GridPane.setConstraints(email, 2, 3);
        GridPane.setConstraints(phoneNumber , 2, 4);
        GridPane.setConstraints(credit , 2, 5);

        gridPane.getChildren().addAll(profileImage, username, firstName, lastName, email, phoneNumber, credit);


        if (account instanceof SellerAccount){
            Label companyLabel = new Label("Company : " + ((SellerAccount)account).getCompany());
            GridPane.setConstraints(companyLabel , 2 , 6);
            gridPane.getChildren().addAll(companyLabel);
        }

        return gridPane;
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

    public static Pane showOffFullInfoGraphic(int offId) {
        Off off = Database.getOffById(offId);

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);


        Label offIdLabel = new Label("OffID : " + off.getOffId());
        Label sellerUsername = new Label("Seller Username : " + off.getSellerUsername());
        Label maxValue = new Label("Max Value : " + off.getMaxValue());
        Label percent = new Label("Percent : " + off.getPercent());
        Label startDate = new Label("Start Date : " + off.getStartDate());
        Label endDate = new Label("End Date : " + off.getEndDate());
        Label status = new Label("Status : " + off.getStatus());
        Label productIdsTag = new Label("Products : ");
        Label productIds = new Label(off.getProductIds().toString());

        GridPane.setConstraints(offIdLabel, 0, 1 , 2 , 1);
        GridPane.setConstraints(sellerUsername, 0, 2 , 2 , 1);
        GridPane.setConstraints(maxValue, 0, 3 , 2 , 1);
        GridPane.setConstraints(percent, 0, 4 , 2 , 1);
        GridPane.setConstraints(startDate, 0, 5 , 2 , 1);
        GridPane.setConstraints(endDate, 0, 6 , 2 , 1);
        GridPane.setConstraints(status, 0, 7 , 2 , 1);
        GridPane.setConstraints(productIdsTag, 0, 9 , 2 , 1);
        GridPane.setConstraints(productIds, 1, 10 , 2 , 3);

        gridPane.getChildren().addAll(offIdLabel, sellerUsername, maxValue, percent, startDate,endDate,status,productIdsTag,productIds);

        return gridPane;
    }

    public static ScrollPane showCommentsOfProduct(int productId)
    {
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        ArrayList<Comment> comments = Objects.requireNonNull(Database.getProductByID(productId)).getComments();
        for (Comment comment : comments) {
            Label label = new Label(comment.showComment());
            vBox.getChildren().add(label);
        }
        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.setPannable(true);
        scrollPane.setPrefSize(200, 50);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        return scrollPane;
    }


}
