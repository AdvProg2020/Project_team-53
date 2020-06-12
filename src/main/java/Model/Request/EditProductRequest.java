package Model.Request;

import Controller.Database;
import Model.Product.Product;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class EditProductRequest extends Request{
    String field;
    String changeTo;
    int productId;

    public EditProductRequest(String field, String changeTo, int productId) {
        this.field = field;
        this.changeTo = changeTo;
        this.productId = productId;
    }

    @Override
    public String acceptRequest() {
        Product product = Database.getProductByID(productId);
        if (product == null)
            return "Product not found!";
        if (field.equalsIgnoreCase("status")){
            product.setStatus(changeTo);
        }
        else if (field.equalsIgnoreCase("name")){
            product.setName(changeTo);
        }
        else if (field.equalsIgnoreCase("available")){
            product.setAvailable(Boolean.parseBoolean(changeTo));
        }
        else if (field.equalsIgnoreCase("number")){
            product.setNumber(Integer.parseInt(changeTo));
        }
        else if (field.equalsIgnoreCase("description")){
            product.setDescription(changeTo);
        }
        else if (field.equalsIgnoreCase("categoryName")){
            product.setCategoryNameAndChangeCategory(changeTo);
        }
        else if (field.equalsIgnoreCase("price")){
            product.setPrice(Integer.parseInt(changeTo));
        }
        return "Product changed successfully";
    }

    @Override
    public String show() {
        return "EditProductRequest{" + '\n' +
                "   requestId="+ getId()+'\n'+
                "   field=" + field + '\n' +
                "   changeTo=" + changeTo + '\n' +
                "   productId=" + productId +'\n' +
                '}';
    }

    @Override
    public Pane showGraphical() {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Label reqID = new Label("RequestID : " + super.getId());
        Label productID = new Label("ProductID : " + productId);
        Label changeField = new Label("Field : " + field);
        Label changeToLabel = new Label("Change To : " + changeTo);

        GridPane.setConstraints(reqID, 0, 0);
        GridPane.setConstraints(productID, 0,1);
        GridPane.setConstraints(changeField, 0, 2);
        GridPane.setConstraints(changeToLabel, 0, 3);

        gridPane.getChildren().addAll(reqID, productID, changeField, changeToLabel);

        return gridPane;
    }
}
