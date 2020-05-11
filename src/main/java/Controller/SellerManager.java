package Controller;

import Model.Request.NewProductRequest;

public class SellerManager {

    public String sendAddProductRequest(String status, String name, boolean avialable, int number, String description, String categoryName){
        Database.addRequest(new NewProductRequest(status, name, AccountManager.getLoggedInAccount().getUsername(), avialable, number, description, categoryName ));
        return "Your request registered";
    }
}
