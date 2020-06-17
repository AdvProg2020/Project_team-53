package Model.Log;

public class SellLog extends Log {
    int OffValue;
    String buyerUsername;

    public SellLog(String date, int price, String deliveryStatus, int logId, int productId, int offValue, String buyerUsername) {
        super(date, price, deliveryStatus, logId, productId);
        OffValue = offValue;
        this.buyerUsername = buyerUsername;
    }

    public int getOffValue() {
        return OffValue;
    }

    public String getBuyerUsername() {
        return buyerUsername;
    }

    @Override
    public String toString() {
        return "SellLog{" +
                "   logId=" + this.getLogId() + '\n' +
                "   productId=" + this.getProductId()+ '\n' +
                "   OffValue=" + this.getOffValue() + '\n' +
                "   buyerUsername='" + this.getBuyerUsername() + '\n' +
                "   date='" + this.getDate() + '\n' +
                "   price=" + this.getPrice() + '\n' +
                "   deliveryStatus='" + this.getDeliveryStatus() + '\n' +
                '}';
    }

}
