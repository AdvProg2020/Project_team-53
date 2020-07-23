package Model.Log;

public class BuyLog extends Log{
    private int discountValue;
    private String sellerUsername;
    private String addressOfBuyer;
    private boolean wasProductHasFile;

    public BuyLog(String date, int price, String deliveryStatus, int logId, int productId, int discountValue, String sellerUsername, String addressOfBuyer, boolean wasProductHasFile) {
        super(date, price, deliveryStatus, logId, productId);
        this.discountValue = discountValue;
        this.sellerUsername = sellerUsername;
        this.addressOfBuyer = addressOfBuyer;
        this.wasProductHasFile = wasProductHasFile;
    }

    public int getDiscountValue() {
        return discountValue;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    @Override
    public String toString() {
        return "BuyLog{" +
                "   logId=" + this.getLogId() + '\n' +
                "   productId=" + this.getProductId() +'\n' +
                "   discountValue=" + this.getDiscountValue() + '\n' +
                "   sellerUsername='" + this.getSellerUsername() + '\n' +
                "   date='" + this.getDate() + '\n' +
                "   price=" + this.getPrice() + '\n' +
                "   deliveryStatus='" + this.getDeliveryStatus() + '\n' +
                '}';
    }

    public boolean isWasProductHasFile() {
        return wasProductHasFile;
    }

    public String getAddressOfBuyer() {
        return addressOfBuyer;
    }
}
