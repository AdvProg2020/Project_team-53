package Model.Log;

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
}
