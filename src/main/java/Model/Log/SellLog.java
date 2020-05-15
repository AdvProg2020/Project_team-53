package Model.Log;

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
}
