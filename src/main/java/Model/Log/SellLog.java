package Model.Log;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class SellLog extends Log {
    int OffValue;
    String buyerUsername;

    public SellLog(String date, int price, String deliveryStatus, int logId, int productId, int offValue, String buyerUsername) {
        super(date, price, deliveryStatus, logId, productId);
        OffValue = offValue;
        this.buyerUsername = buyerUsername;
    }

    @Override
    public String toString() {
        return "SellLog{" +
                "   logId=" + logId + '\n' +
                "   productId=" + productId + '\n' +
                "   OffValue=" + OffValue + '\n' +
                "   buyerUsername='" + buyerUsername + '\n' +
                "   date='" + date + '\n' +
                "   price=" + price + '\n' +
                "   deliveryStatus='" + deliveryStatus + '\n' +
                '}';
    }

    @Override
    public Pane showWithGraphic() {

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Label logId = new Label("Log ID : " + super.getLogId());
        Label dateLabel = new Label("Date : " + date);
        Label priceLabel = new Label("Price : " + price);
        Label delivered = new Label("Delivery Status : " + deliveryStatus);
        Label productIdLabel = new Label("Product Id : " + productId);
        Label offLabel = new Label("Off Value : " + OffValue);
        Label buyerUsernameLabel = new Label("Buyer Username : " + buyerUsername);


        GridPane.setConstraints(logId, 0 , 0);
        GridPane.setConstraints(dateLabel, 0, 1);
        GridPane.setConstraints(priceLabel, 0, 2);
        GridPane.setConstraints(delivered, 0, 3);
        GridPane.setConstraints(productIdLabel, 0, 4);
        GridPane.setConstraints(offLabel, 0, 5);
        GridPane.setConstraints(buyerUsernameLabel, 0, 6);

        gridPane.getChildren().addAll(logId, dateLabel, priceLabel, delivered, productIdLabel, offLabel, buyerUsernameLabel);

        return gridPane;
    }
}
