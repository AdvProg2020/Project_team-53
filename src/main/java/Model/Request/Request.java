package Model.Request;

import Controller.Database;
import Model.Product.DiscountAndOff.Off;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public abstract class Request {
    private static int allRequestId=1;
    private int id;

    public static int getAllRequestId() {
        return allRequestId;
    }

    public static void setAllRequestId(int allRequestId) {
        Request.allRequestId = allRequestId;
    }

    public int getId() {
        return id;
    }

    public Request() {
        this.id = allRequestId;
        allRequestId++;
    }

    public Pane showRequestGraphic() {
        Request request = this;
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
        else if (request instanceof DeleteProduct){
            Label reqID = new Label("RequestID : " + request.getId());
            Label productId = new Label("Product Id : " + ((DeleteProduct) request).getProductId());

            GridPane.setConstraints(reqID, 0 , 0);
            GridPane.setConstraints(productId, 0, 1);
            gridPane.getChildren().addAll(reqID, productId);

        }
        return gridPane;
    }


    public abstract String acceptRequest();
    public abstract String show();

}
