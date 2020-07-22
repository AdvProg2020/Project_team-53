package Controller;

import Model.Account.Account;
import Model.Account.SellerAccount;
import Model.Log.SellLog;
import Model.Product.Auction;
import Model.Product.DiscountAndOff.Off;
import Model.Product.Product;
import Model.Request.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SellerManager {

    public String sendAddProductRequest(String status, String name, boolean available, int number, String description, String categoryName, int price, Account account, boolean hasFile, String address) {
        Database.addRequest(new NewProductRequest(status, name, account.getUsername(), available, number, description, categoryName, price, hasFile, address));
        return "Your request registered.";
    }

    public String sendEditProductRequest(int productId, String field, String changeTo, Account account) {
        Product product = Database.getProductByID(productId);
        if (product == null) {
            return "no such product";
        }
        if (!product.isSeller(account.getUsername())) {
            return "You can't edit this product.";
        }
        Database.addRequest(new EditProductRequest(field, changeTo, productId));
        return "Your request registered.";
    }

    public String deleteProduct(int productId) {
        Database.addRequest(new DeleteProduct(productId));
        return "Request send";
    }

    public String addNewOff(int maxValue, int percent, String startDate, String endDate, ArrayList<Integer> productIds, Account account) {
        for (Integer productId : productIds) {
            Product product = Database.getProductByID(productId);
            if (product == null)
                return "no such product";
            if (!product.getSellerUsername().equalsIgnoreCase(account.getUsername()))
                return "You can add off only on your products";
        }
        Off off = new Off(maxValue, percent, startDate, endDate, account.getUsername(), productIds);
        Database.addAllOff(off);

        Database.addRequest(new AddNewOffRequest(off.getOffId()));

        return "Your request registered";
    }

    public String editOff(int offId, String field, String changeTo, Account account) {
        Off off = Database.getOffById(offId);
        if (off == null)
            return "no such off";
        if (!off.getSellerUsername().equalsIgnoreCase(account.getUsername())) {
            return "You can't edit this off";
        }
        Database.addRequest(new EditOffRequest(field, changeTo, offId));
        return "Your request registered.";
    }

    public String viewCompany(Account account) {
        return ((SellerAccount) account).getCompany();
    }

    public String getBuyerOfProduct(int productID, Account account) {
        Set<String> buyerAccounts = new HashSet<>();
        String res = "";
        for (SellLog sellLog : ((SellerAccount) account).getSellLogs()) {
            if (sellLog.getProductId() == productID && !buyerAccounts.contains(sellLog.getBuyerUsername())) {
                res = res + "\n" + sellLog.getBuyerUsername();
                buyerAccounts.add(sellLog.getBuyerUsername());
            }
        }
        return res;
    }

    public String addAuction(int productID, String endDate, Account account) {
        try {

            Date endDateAsDate = new SimpleDateFormat("yyyy-MM-dd_HH:mm").parse(endDate);
            Product product = Database.getProductByID(productID);
            if (product == null)
                return "There is no product with this ID";
            if (!product.getSellerUsername().equals(account.getUsername()))
                return "You can create auction only for your products";
            Auction auction = new Auction(product, endDateAsDate);
            Database.addAllAuction(auction);
            auction.start();
            return "New Auction created";
        } catch (ParseException e) {
            return e.getMessage();
        }
    }
}
