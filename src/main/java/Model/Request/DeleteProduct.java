package Model.Request;

import Controller.AdminManager;

public class DeleteProduct extends Request{
    private int productId;

    public DeleteProduct(int productId) {
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    @Override
    public String acceptRequest() {
        return AdminManager.deleteProduct(productId);
    }

    @Override
    public String show() {
        return null;
    }
}
