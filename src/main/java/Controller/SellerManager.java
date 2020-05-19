package Controller;

import Model.Off;
import Model.Product;
import Model.Request.AddNewOffRequest;
import Model.Request.EditOffRequest;
import Model.Request.EditProductRequest;
import Model.Request.NewProductRequest;
import Model.Account.SellerAccount;

import java.util.ArrayList;

public class SellerManager {

    public static String sendAddProductRequest(String status, String name, boolean available, int number, String description, String categoryName, int price){
        Database.addRequest(new NewProductRequest(status, name, AccountManager.getLoggedInAccount().getUsername(), available, number, description, categoryName, price ));
        return "Your request registered.";
    }

    public static String sendEditProductRequest(int productId, String field, String changeTo){
        Product product = Database.getProductByID(productId);
        if (product == null){
            return "no such product";
        }
        if (!product.isSeller(AccountManager.getLoggedInAccount().getUsername())){
            return "You can't edit this product.";
        }
        Database.addRequest(new EditProductRequest(field, changeTo, productId));
        return "Your request registered.";
    }

    public static String deleteProduct(int productId){
        Product product = Database.getProductByID(productId);
        if (!product.isSeller(AccountManager.getLoggedInAccount().getUsername())){
            return "You can't remove this product.";
        }
        Database.removeProduct(product);
        return "Product removed successfully";
    }

    public static String addNewOff(int maxValue, int percent, String startDate, String endDate , ArrayList<Integer>productIds){
        for (Integer productId : productIds) {
            Product product = Database.getProductByID(productId);
            if (product == null)
                return "no such product";
            if (!product.getSellerUsername().equalsIgnoreCase(AccountManager.getLoggedInAccount().getUsername()))
                return "You can add off only on your products";
        }
        Off off = new Off(maxValue, percent, startDate, endDate, AccountManager.getLoggedInAccount().getUsername(), productIds);
        Database.addAllOff(off);

        Database.addRequest(new AddNewOffRequest(off.getOffId()));

        return "Your request registered";
    }

    public static String editOff(int offId, String field, String changeTo){
        Off off = Database.getOffById(offId);
        if (off == null)
            return "no such off";
        if (!off.getSellerUsername().equalsIgnoreCase(AccountManager.getLoggedInAccount().getUsername())){
            return "You can't edit this off";
        }
        Database.addRequest(new EditOffRequest(field, changeTo, offId));
        return "Your request registered.";
    }

    public static String viewCompany(){
        return ((SellerAccount) AccountManager.getLoggedInAccount()).getCompany();
    }
}
