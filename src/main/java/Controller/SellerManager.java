package Controller;

import Model.Log.SellLog;
import Model.Product.DiscountAndOff.Off;
import Model.Product.Product;
import Model.Request.*;
import Model.Account.SellerAccount;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
        Database.addRequest(new DeleteProduct(productId));
        return "Request send";
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

    public static String getSellerOfProduct(int productID) {
        Set<String> buyerAccounts = new HashSet<>();
        String res = "" ;
        for (SellLog sellLog : ((SellerAccount) AccountManager.getLoggedInAccount()).getSellLogs()) {
            if (sellLog.getProductId() == productID && !buyerAccounts.contains(sellLog.getBuyerUsername())){
                res = res + "\n" + sellLog.getBuyerUsername();
                buyerAccounts.add(sellLog.getBuyerUsername());
            }
        }
        return res;
    }
}
