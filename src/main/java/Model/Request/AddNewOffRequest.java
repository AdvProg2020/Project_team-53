package Model.Request;

import Controller.AdminManager;
import Controller.Database;
import Model.Product.DiscountAndOff.Off;
import Model.Product.Product;

public class AddNewOffRequest extends Request {

    private int offId;

    public AddNewOffRequest(int offId) {
        this.offId = offId;
    }

    public int getOffId() {
        return offId;
    }

    @Override
    public String acceptRequest(AdminManager adminManager) {
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

}
