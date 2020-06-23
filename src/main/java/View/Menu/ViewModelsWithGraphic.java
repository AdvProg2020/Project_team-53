package View.Menu;

import Controller.AccountManager;
import Controller.Database;
import Model.Account.Account;
import Model.Account.BuyerAccount;
import Model.Account.SellerAccount;
import Model.Log.BuyLog;
import Model.Log.Log;
import Model.Log.SellLog;
import Model.Product.Category;
import Model.Product.CommentAndScore.Comment;
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


    public static Pane showRequestGraphic(Request request) {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        if (request instanceof AddNewOffRequest) {

            Off off = Database.getOffById(((AddNewOffRequest) request).getOffId());
            Label reqID = new Label("RequestID : " + request.getId());
            Label offID = new Label("OffID : " + ((AddNewOffRequest) request).getOffId());
            Label maxValue = new Label("Off Max Value : " + off.getMaxValue());
            Label percent = new Label("Off Percent : " + off.getPercent());
            Label startDate = new Label("Start Date : " + off.getStartDate());
            Label endDate = new Label("End Date : " + off.getEndDate());
            Label productId = new Label("productID's : " + off.getProductIds());
            Label status = new Label("Status : " + off.getStatus());
            Label sellerUsername = new Label("Seller Username : " + off.getSellerUsername());

            GridPane.setConstraints(reqID, 0, 0);
            GridPane.setConstraints(offID, 0, 1);
            GridPane.setConstraints(maxValue, 0, 2);
            GridPane.setConstraints(percent, 0, 3);
            GridPane.setConstraints(startDate, 0, 4);
            GridPane.setConstraints(endDate, 0, 5);
            GridPane.setConstraints(productId, 0, 6);
            GridPane.setConstraints(status, 0, 7);
            GridPane.setConstraints(sellerUsername, 0, 8);

            gridPane.getChildren().addAll(reqID, offID, maxValue, percent, startDate, endDate, productId, status, sellerUsername);
        }
        else if (request instanceof EditOffRequest){
            Label reqID = new Label("RequestID : " + request.getId());
            Label offID = new Label("OffID : " + ((EditOffRequest) request).getOffId());
            Label changeField = new Label("Field : " + ((EditOffRequest) request).getField());
            Label changeToLabel = new Label("Change To : " + ((EditOffRequest) request).getChangeTo());

            GridPane.setConstraints(reqID, 0, 0);
            GridPane.setConstraints(offID, 0,1);
            GridPane.setConstraints(changeField, 0, 2);
            GridPane.setConstraints(changeToLabel, 0, 3);

            gridPane.getChildren().addAll(reqID, offID, changeField, changeToLabel);

        }
        else if (request instanceof EditProductRequest){
            Label reqID = new Label("RequestID : " + request.getId());
            Label productID = new Label("ProductID : " + ((EditProductRequest) request).getProductId());
            Label changeField = new Label("Field : " + ((EditProductRequest) request).getField());
            Label changeToLabel = new Label("Change To : " + ((EditProductRequest) request).getChangeTo());

            GridPane.setConstraints(reqID, 0, 0);
            GridPane.setConstraints(productID, 0,1);
            GridPane.setConstraints(changeField, 0, 2);
            GridPane.setConstraints(changeToLabel, 0, 3);

            gridPane.getChildren().addAll(reqID, productID, changeField, changeToLabel);

        }
        else if (request instanceof NewProductRequest){
            Label reqID = new Label("RequestID : " + request.getId());
            Label statusLabel = new Label("Status : " + ((NewProductRequest) request).getStatus());
            Label nameLabel = new Label("Name : " + ((NewProductRequest) request).getName());
            Label sellUsernameLabel = new Label("Seller Username : " + ((NewProductRequest) request).getSellerUsername());
            Label availableLabel = new Label("Available : " + ((NewProductRequest) request).isAvailable());
            Label numberLabel = new Label("Number : " + ((NewProductRequest) request).getNumber());
            Label descriptionLabel = new Label("Description : " + ((NewProductRequest) request).getDescription());
            Label categoryNameLabel = new Label("Category Name : " + ((NewProductRequest) request).getCategoryName());

            GridPane.setConstraints(reqID, 0, 0);
            GridPane.setConstraints(statusLabel, 0, 1);
            GridPane.setConstraints(nameLabel, 0, 2);
            GridPane.setConstraints(sellUsernameLabel, 0, 3);
            GridPane.setConstraints(availableLabel, 0, 4);
            GridPane.setConstraints(numberLabel, 0, 5);
            GridPane.setConstraints(descriptionLabel, 0, 6);
            GridPane.setConstraints(categoryNameLabel, 0, 7);

            gridPane.getChildren().addAll(reqID, statusLabel, nameLabel, sellUsernameLabel, availableLabel, numberLabel, descriptionLabel, categoryNameLabel);

        }
        else if (request instanceof NewSellerRequest){
            Label reqID = new Label("RequestID : " + request.getId());
            Label userNameLabel = new Label("Username : " + ((NewSellerRequest) request).getUsername());
            Label firstNameLabel = new Label("First Name : " + ((NewSellerRequest) request).getFirstName());
            Label lastNameLabel = new Label("Last Name : " + ((NewSellerRequest) request).getLastName());
            Label emailLabel = new Label("Email : " + ((NewSellerRequest) request).getEmail());
            Label phoneNumberLabel = new Label("Phone Number : " + ((NewSellerRequest) request).getPhoneNumber());
            Label companyLabel = new Label("Company : " + ((NewSellerRequest) request).getCompany());
            Label creditLabel = new Label("Credit : " + ((NewSellerRequest) request).getCredit());

            GridPane.setConstraints(reqID, 0 , 0);
            GridPane.setConstraints(userNameLabel, 0, 1);
            GridPane.setConstraints(firstNameLabel, 0, 2);
            GridPane.setConstraints(lastNameLabel, 0, 3);
            GridPane.setConstraints(emailLabel, 0, 4);
            GridPane.setConstraints(phoneNumberLabel, 0, 5);
            GridPane.setConstraints(companyLabel, 0, 6);
            GridPane.setConstraints(creditLabel, 0, 7);

            gridPane.getChildren().addAll(reqID, userNameLabel, firstNameLabel, lastNameLabel, emailLabel, phoneNumberLabel, companyLabel, creditLabel);

        }
        return gridPane;
    }

    public static Pane showCategoryGraphic(String categoryName) {
        Category category = Database.getCategoryByName(categoryName);

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Label name = new Label("Name : " + category.getName());
        Label allSubCategory = new Label("SubCategory : " + category.getAllSubCategoryNames());
        Label feature = new Label("Feature : " + category.getFeature());
        Label productIds = new Label("All Products : " + category.getAllProductIds());
        Label parent = new Label("Parent : " + category.getParent());

        GridPane.setConstraints(name, 0, 1 , 2 , 1);
        GridPane.setConstraints(allSubCategory, 0, 2 , 2 , 1);
        GridPane.setConstraints(feature, 0, 3 , 2 , 1);
        GridPane.setConstraints(productIds, 0, 5 , 2 , 1);
        GridPane.setConstraints(parent, 0, 6 , 2 , 1);

        gridPane.getChildren().addAll(name, allSubCategory, feature, productIds,parent);

        return gridPane;
    }

    public static Pane showLogWithGraphic(Log log) {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        if (log instanceof BuyLog) {
            Label logId = new Label("Log ID : " + log.getLogId());
            Label dateLabel = new Label("Date : " + log.getDate());
            Label priceLabel = new Label("Price : " + log.getPrice());
            Label delivered = new Label("Delivery Status : " + log.getDeliveryStatus());
            Label productIdLabel = new Label("Product Id : " + log.getProductId());
            Label discountLabel = new Label("Discount Value : " + ((BuyLog) log).getDiscountValue());
            Label sellerUsernameLabel = new Label("Seller Username : " + ((BuyLog) log).getSellerUsername());


            GridPane.setConstraints(logId, 0, 0);
            GridPane.setConstraints(dateLabel, 0, 1);
            GridPane.setConstraints(priceLabel, 0, 2);
            GridPane.setConstraints(delivered, 0, 3);
            GridPane.setConstraints(productIdLabel, 0, 4);
            GridPane.setConstraints(discountLabel, 0, 5);
            GridPane.setConstraints(sellerUsernameLabel, 0, 6);

            gridPane.getChildren().addAll(logId, dateLabel, priceLabel, delivered, productIdLabel, discountLabel, sellerUsernameLabel);
        }
        else if (log instanceof SellLog){
            Label logId = new Label("Log ID : " + log.getLogId());
            Label dateLabel = new Label("Date : " + log.getDate());
            Label priceLabel = new Label("Price : " + log.getPrice());
            Label delivered = new Label("Delivery Status : " + log.getDeliveryStatus());
            Label productIdLabel = new Label("Product Id : " + log.getProductId());
            Label offLabel = new Label("Off Value : " + ((SellLog) log).getOffValue());
            Label buyerUsernameLabel = new Label("Buyer Username : " + ((SellLog) log).getBuyerUsername());


            GridPane.setConstraints(logId, 0 , 0);
            GridPane.setConstraints(dateLabel, 0, 1);
            GridPane.setConstraints(priceLabel, 0, 2);
            GridPane.setConstraints(delivered, 0, 3);
            GridPane.setConstraints(productIdLabel, 0, 4);
            GridPane.setConstraints(offLabel, 0, 5);
            GridPane.setConstraints(buyerUsernameLabel, 0, 6);

            gridPane.getChildren().addAll(logId, dateLabel, priceLabel, delivered, productIdLabel, offLabel, buyerUsernameLabel);

        }
        return gridPane;
    }
}
