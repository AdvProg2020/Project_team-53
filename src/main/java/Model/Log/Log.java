package Model.Log;

import Controller.Database;
import Model.BuyerAccount;
import Model.SellerAccount;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Log {
    private static int allLogId = 1;
    String date;
    int price;
    String deliveryStatus;
    int logId;
    int productId;

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
}
