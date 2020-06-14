package Model.Request;

import Controller.Database;
import Model.Product.DiscountAndOff.Off;
import Model.Product.Product;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class AddNewOffRequest extends Request {

    private int offId;

    public AddNewOffRequest(int offId) {
        this.offId = offId;
    }

    public int getOffId() {
        return offId;
    }

    @Override
    public String acceptRequest() {
        Off off = Database.getOffById(offId);
        if (off == null)
            return "no such off";

        for (Integer productId : off.getProductIds()) {
            Product product = Database.getProductByID(productId);
            if (product != null){
                product.setOffId(offId);
            }
        }

        off.setStatus("Accepted");

        return "Off request added";
    }

    @Override
    public String show() {
        Off off = Database.getOffById(offId);
        if (off == null)
            return "no such off";
        return "AddNewOffRequest{" + '\n' +
                "requestId=" + getId() + '\n' +
                off.showInfo() + '\n' +
                '}';
    }

    @Override
    public Pane showGraphical()
    {
        Off off = Database.getOffById(offId);
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Label reqID = new Label("RequestID : " + super.getId());
        Label offID = new Label("OffID : " + offId);
        Label maxValue = new Label("Off Max Value : " + off.getMaxValue());
        Label percent = new Label("Off Percent : " + off.getPercent());
        Label startDate = new Label("Start Date : " + off.getStartDate());
        Label endDate = new Label("End Date : " + off.getEndDate());
        Label productId = new Label("productID's : " + off.getProductIds());
        Label status = new Label("Status : " + off.getStatus());
        Label sellerUsername = new Label("Seller Username : " + off.getSellerUsername());

        GridPane.setConstraints(reqID, 0, 0);
        GridPane.setConstraints(offID, 0,1);
        GridPane.setConstraints(maxValue, 0, 2);
        GridPane.setConstraints(percent, 0, 3);
        GridPane.setConstraints(startDate, 0, 4);
        GridPane.setConstraints(endDate, 0, 5);
        GridPane.setConstraints(productId, 0, 6);
        GridPane.setConstraints(status, 0, 7);
        GridPane.setConstraints(sellerUsername, 0, 8);

        gridPane.getChildren().addAll(reqID, offID, maxValue, percent, startDate, endDate, productId, status, sellerUsername);

        return gridPane;
    }
}
