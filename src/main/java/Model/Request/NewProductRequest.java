package Model.Request;

import Controller.Database;
import Model.Product.Product;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class NewProductRequest extends Request {

    String status;
    String name;
    String sellerUsername;
    boolean available;
    int number;
    String description;
    String categoryName;
    int price;

    public NewProductRequest(String status, String name, String sellerUsername, boolean available, int number, String description, String categoryName, int price) {
        this.status = status;
        this.name = name;
        this.sellerUsername = sellerUsername;
        this.available = available;
        this.number = number;
        this.description = description;
        this.categoryName = categoryName;
        this.price = price;
    }

    @Override
    public String acceptRequest() {
        Product product = new Product(status, name, sellerUsername, available, number, description , categoryName, price);
        Database.addAllProduct(product);
        Database.getCategoryByName(product.getCategoryName()).addProduct(product.getProductId());
        return "product added successfully.";
    }

    @Override
    public String show() {
        return "NewProductRequest{" + "\n" +
                "   requestId="+ getId()+'\n'+
                "   status=" + status + '\n' +
                "   name=" + name + '\n' +
                "   sellerUsername=" + sellerUsername + '\n' +
                "   available=" + available + '\n' +
                "   number=" + number + '\n' +
                "   description=" + description + '\n' +
                "   categoryName=" + categoryName + '\n' +
                '}';
    }

    @Override
    public Pane showGraphical() {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Label reqID = new Label("RequestID : " + super.getId());
        Label statusLabel = new Label("Status : " + status);
        Label nameLabel = new Label("Name : " + name);
        Label sellUsernameLabel = new Label("Seller Username : " + sellerUsername);
        Label availableLabel = new Label("Available : " + available);
        Label numberLabel = new Label("Number : " + number);
        Label descriptionLabel = new Label("Description : " + description);
        Label categoryNameLabel = new Label("Category Name : " + categoryName);

        GridPane.setConstraints(reqID, 0, 0);
        GridPane.setConstraints(statusLabel, 0, 1);
        GridPane.setConstraints(nameLabel, 0, 2);
        GridPane.setConstraints(sellUsernameLabel, 0, 3);
        GridPane.setConstraints(availableLabel, 0, 4);
        GridPane.setConstraints(numberLabel, 0, 5);
        GridPane.setConstraints(descriptionLabel, 0, 6);
        GridPane.setConstraints(categoryNameLabel, 0, 7);

        gridPane.getChildren().addAll(reqID, statusLabel, nameLabel, sellUsernameLabel, availableLabel, numberLabel, descriptionLabel, categoryNameLabel);

        return gridPane;
    }
}
