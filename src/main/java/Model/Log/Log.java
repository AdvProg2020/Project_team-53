package Model.Log;

import Controller.Database;
import Model.Account.BuyerAccount;
import Model.Account.SellerAccount;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Log {
    private static int allLogId = 1;
    String date;
    int price;
    String deliveryStatus;
    int logId;
    int productId;

    public static int getAllLogId() {
        return allLogId;
    }

    public static void setAllLogId(int allLogId) {
        Log.allLogId = allLogId;
    }

    public Log(String date, int price, String deliveryStatus, int logId, int productId) {
        this.date = date;
        this.price = price;
        this.deliveryStatus = deliveryStatus;
        this.logId = logId;
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public String getDate() {
        return date;
    }

    public int getPrice() {
        return price;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public static void addLog(String buyerUsername , String sellerUsername , int price, int productId, int offValue, int discountValue){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));

        SellLog sellLog = new SellLog(dtf.format(now), price, "preparing...", allLogId, productId, offValue, buyerUsername);
        BuyLog buyLog = new BuyLog(dtf.format(now), price, "preparing...", allLogId, productId, discountValue, sellerUsername);

        BuyerAccount buyerAccount = (BuyerAccount)Database.getAccountByUsername(buyerUsername);
        SellerAccount sellerAccount = (SellerAccount)Database.getAccountByUsername(sellerUsername);

        buyerAccount.addBuyLog(buyLog);
        sellerAccount.addSellLog(sellLog);

        allLogId++;

    }

    public int getLogId() {
        return logId;
    }

    public Pane showLogWithGraphic() {
        Log log = this;
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
