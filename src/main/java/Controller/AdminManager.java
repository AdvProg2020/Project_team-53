package Controller;

import Model.Account.Account;
import Model.Account.AdminAccount;
import Model.Account.BuyerAccount;
import Model.Product.Category;
import Model.Product.DiscountAndOff.Discount;
import Model.Product.Product;
import Model.Request.Request;

import java.util.ArrayList;

public class AdminManager {

    public static String showAllAccount() {
        ArrayList<Account> accounts = Database.getAllAccounts();
        StringBuilder res = new StringBuilder();
        for (Account account : accounts) {
            res.append("\n ++++++++++++++++++++++++++++++ \n").append(account.showInfo());
        }
        return res.toString();
    }

    public static String showAccountWithUsername(String username) {
        Account account = Database.getAccountByUsername(username);
        if (account == null)
            return "No user with this username.";
        return account.showInfo();
    }

    public static String addNewAdminAccount(String username, String firstName, String lastName, String email, String phoneNumber, String password, int credit) {
        if (Database.getAccountByUsername(username) != null)
            return "Exist account with this username.";
        try {
            Database.addAllAccounts(new AdminAccount(username, firstName, lastName, password, email, phoneNumber, credit));
        } catch (Exception e){
            return e.getMessage();
        }
        return "New admin account registered.";
    }

    public static String showRequestByiId(int id){
        Request request = Database.getRequestById(id);
        if (request == null)
            return "No request with this id";
        return request.show();
    }

    public static String showAllRequests(){
        ArrayList<Request> requests = Database.getAllRequest();
        StringBuilder res = new StringBuilder();
        for (Request request : requests) {
            res.append(request.show() + "\n+++++++++++++++++++++++++++++++++++++++\n");
        }
        return res.toString();
    }

    public static String acceptOrRejectRequest(int id, boolean accept){
        Request request = Database.getRequestById(id);
        if (request == null)
            return "No request with this id";

        if (accept){
            Database.removeRequest(request);
            return request.acceptRequest();
        }
        Database.removeRequest(request);
        return "done!";
    }

    public static String deleteUsername(String username){
        Account account = Database.getAccountByUsername(username);
        if (account == null){
            return "No user with this username";
        }
        Database.removeAccount(account);
        return "Account deleted";
    }

    public static String addNewCategory(String name, String feature, String parentName){
        Database.addAllCategory(new Category(name, feature, parentName));
        return "New category added";
    }

    public static String editCategory(String categoryName, String field, String changeTo){
        Category category = Database.getCategoryByName(categoryName);
        if (category == null)
            return "no such category";
        if (field.equalsIgnoreCase("parentName")){
            category.setParent(changeTo);
        }
        else if (field.equalsIgnoreCase("name")){
            category.setName(changeTo);
        }
        else if (field.equalsIgnoreCase("feature")){
            category.setFeature(changeTo);
        }
        else if (field.equalsIgnoreCase("addNewSubCategory")){
            category.addSubCategory(changeTo);
        }
        else if (field.equalsIgnoreCase("addNewProduct")){
            Product product = Database.getProductByID(Integer.parseInt(changeTo));
            product.setCategoryNameAndChangeCategory(category.getName());
        }
        else
        {
            return "no such field";
        }
        return "changed successfully";
    }

    public static String deleteCategory(String categoryName){
        Category category = Database.getCategoryByName(categoryName);
        if (category == null)
            return "no such category.";
        Database.removeCategory(category);
        return "category deleted";
    }

    public static String deleteProduct(int productId){
        Product product = Database.getProductByID(productId);
        Database.removeProduct(product);
        return "Product removed successfully";
    }

    public static String addNewDiscount(int maxValue, int percent, String startDate, String endDate, int numberOfTimes, ArrayList<String> usernames){
        for (String username : usernames) {
            if (Database.getAccountByUsername(username) ==null || !(Database.getAccountByUsername(username) instanceof BuyerAccount))
                return "No buyer user with this username";
        }
        Discount discount = new Discount(maxValue , percent, startDate, endDate, numberOfTimes);
        Database.addAllDiscount(discount);
        for (String username : usernames) {
            BuyerAccount buyer = (BuyerAccount) Database.getAccountByUsername(username);
            buyer.addNewDiscount(discount.getDiscountId());
        }
        return "New discount added";
    }

    public static String removeDiscount(int discountId){
        Database.removeDiscount(Database.getDiscountById(discountId));
        return "discount deleted";
    }

    public static String showAllDiscount(){
        ArrayList<Discount> allDiscounts = Database.getAllDiscounts();
        StringBuilder res = new StringBuilder();
        for (Discount discount : allDiscounts) {
            res.append(discount.showInfo() + "\n------------------------\n");
        }
        return res.toString();
    }

    public static String showDiscountWithId(int discountId){
        Discount discount = Database.getDiscountById(discountId);
        if (discount == null)
            return "no such discount";
        return discount.showInfo();
    }

    public static String editDiscount(int discountId, String field , String changTo){
        Discount discount = Database.getDiscountById(discountId);
        if (field.equalsIgnoreCase("maxValue")){
            discount.setMaxValue(Integer.parseInt(changTo));
        }
        else if (field.equalsIgnoreCase("percent")){
            discount.setPercent(Integer.parseInt(changTo));
        }
        else if (field.equalsIgnoreCase("startDate")){
            discount.setStartDate(changTo);
        }
        else if (field.equalsIgnoreCase("endDate")){
            discount.setEndDate(changTo);
        }
        else if (field.equalsIgnoreCase("numberOfTime")){
            discount.setNumberOfTimes(Integer.parseInt(changTo));
        }
        else
        {
            return "no such field";
        }
        return "changed successfully";
    }
}
