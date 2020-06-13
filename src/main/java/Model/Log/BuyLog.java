package Model.Log;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class BuyLog extends Log{
    int discountValue;
    String sellerUsername;

    public BuyLog(String date, int price, String deliveryStatus, int logId, int productId, int discountValue, String sellerUsername) {
        super(date, price, deliveryStatus, logId, productId);
        this.discountValue = discountValue;
        this.sellerUsername = sellerUsername;
    }

    @Override
    public String toString() {
        return "BuyLog{" +
                "   logId=" + logId + '\n' +
                "   productId=" + productId +'\n' +
                "   discountValue=" + discountValue + '\n' +
                "   sellerUsername='" + sellerUsername + '\n' +
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
        Label discountLabel = new Label("Discount Value : " + discountValue);
        Label sellerUsernameLabel = new Label("Seller Username : " + sellerUsername);


        GridPane.setConstraints(logId, 0 , 0);
        GridPane.setConstraints(dateLabel, 0, 1);
        GridPane.setConstraints(priceLabel, 0, 2);
        GridPane.setConstraints(delivered, 0, 3);
        GridPane.setConstraints(productIdLabel, 0, 4);
        GridPane.setConstraints(discountLabel, 0, 5);
        GridPane.setConstraints(sellerUsernameLabel, 0, 6);

        gridPane.getChildren().addAll(logId, dateLabel, priceLabel, delivered, productIdLabel, discountLabel, sellerUsernameLabel);

        return gridPane;
    }
}
