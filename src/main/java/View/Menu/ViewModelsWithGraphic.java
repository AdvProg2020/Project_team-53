package View.Menu;

import Controller.AccountManager;
import Controller.Database;
import Model.Account.Account;
import Model.Account.BuyerAccount;
import Model.Product.DiscountAndOff.Discount;
import Model.Product.Product;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
        Label remainigTimes = new Label("Remaining Times Of Use : " +
                (discount.getNumberOfTimes() - ((BuyerAccount) AccountManager.getLoggedInAccount()).getNumberOfUse(discountId)));


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

}
