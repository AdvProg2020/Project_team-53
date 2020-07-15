package Controller;

import Model.Account.Account;
import Model.Account.AdminAccount;
import Model.Account.BuyerAccount;
import Model.Account.SellerAccount;
import Model.Product.Category;
import Model.Product.DiscountAndOff.Discount;
import Model.Product.DiscountAndOff.Off;
import Model.Product.Product;
import Model.Request.AddNewOffRequest;
import Model.Request.EditOffRequest;
import Model.Request.Request;

import java.util.ArrayList;

public class AdminManager {

    public String showAccountWithUsername(String username) {
        Account account = Database.getAccountByUsername(username);
        if (account == null)
            return "No user with this username.";
        return account.showInfo();
    }

    public String addNewAdminAccount(String username, String firstName, String lastName, String email, String phoneNumber, String password, int credit) {
        if (Database.getAccountByUsername(username) != null)
            return "Exist account with this username.";
        try {
            Database.addAllAccounts(new AdminAccount(username, firstName, lastName, password, email, phoneNumber, credit));
        } catch (Exception e){
            return e.getMessage();
        }
        return "New admin account registered.";
    }

    public String showRequestByiId(int id){
        Request request = Database.getRequestById(id);
        if (request == null)
            return "No request with this id";
        return request.show();
    }

    public String showAllRequests(){
        ArrayList<Request> requests = Database.getAllRequest();
        StringBuilder res = new StringBuilder();
        for (Request request : requests) {
            res.append(request.show() + "\n+++++++++++++++++++++++++++++++++++++++\n");
        }
        return res.toString();
    }

    public String acceptOrRejectRequest(int id, boolean accept){
        Request request = Database.getRequestById(id);
        if (request == null)
            return "No request with this id";

        if (accept){
            Database.removeRequest(request);
            return request.acceptRequest();
        }
        if (!accept && request instanceof AddNewOffRequest){
            Off off = Database.getOffById(((AddNewOffRequest) request).getOffId());
            Database.removeOff(off);
        }
        if (!accept && request instanceof EditOffRequest){
            Off off = Database.getOffById(((EditOffRequest) request).getOffId());
            off.setStatus("Accepted");
        }
        Database.removeRequest(request);
        return "done!";
    }

    public String deleteUsername(String username){
        Account account = Database.getAccountByUsername(username);
        if (account == null){
            return "No user with this username";
        }
        Database.removeAccount(account);
        return "Account deleted";
    }

    public String addNewCategory(String name, String feature, String parentName){
        Category category = new Category(name, feature, parentName);
        Database.addAllCategory(category);
        Category parentCategory = Database.getCategoryByName(parentName);
        if (parentCategory != null)
            parentCategory.addSubCategory(name);
        return "New category added";
    }

    public String editCategory(String categoryName, String field, String changeTo){
        Category category = Database.getCategoryByName(categoryName);
        if (category == null)
            return "no such category";
        if (field.equalsIgnoreCase("parentName")){
            Category parentCategory = Database.getCategoryByName(category.getParent());
            parentCategory.removeSubCategory(categoryName);
            Category newParent = Database.getCategoryByName(changeTo);
            if (newParent == null){
                category.setParent(null);
                return "changed successfully";
            }
            category.setParent(changeTo);
            newParent.addSubCategory(category.getName());
        }
        else if (field.equalsIgnoreCase("feature")){
            category.setFeature(changeTo);
        }
        else
        {
            return "no such field";
        }
        return "changed successfully";
    }

    public String deleteCategory(String categoryName){
        Category category = Database.getCategoryByName(categoryName);
        if (category == null)
            return "no such category.";
        ArrayList<Integer> temp = category.getAllProductIds();
        for (Integer productId : temp) {
            deleteProduct(productId);
        }
        ArrayList<String> newOne = new ArrayList<>();
        newOne.addAll(category.getAllSubCategoryNames());
        for (String subCategoryName : newOne) {
            deleteCategory(subCategoryName);
        }
        String parent = category.getParent();
        Category parentCategory = Database.getCategoryByName(parent);
        if (parentCategory != null){
            parentCategory.removeSubCategory(categoryName);
        }
        Database.removeCategory(category);
        return "category deleted";
    }

    public String deleteProduct(int productId){
        Product product = Database.getProductByID(productId);
        Database.removeProduct(product);
        Category category = Database.getCategoryByName(product.getCategoryName());
        if (category != null){
            category.removeProduct(productId);
        }
        return "Product removed successfully";
    }

    public String addNewDiscount(int maxValue, int percent, String startDate, String endDate, int numberOfTimes, ArrayList<String> usernames){
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

    public String removeDiscount(int discountId){
        Database.removeDiscount(Database.getDiscountById(discountId));
        return "discount deleted";
    }

    public String showAllDiscount(){
        ArrayList<Discount> allDiscounts = Database.getAllDiscounts();
        StringBuilder res = new StringBuilder();
        for (Discount discount : allDiscounts) {
            res.append(discount.showInfo() + "\n------------------------\n");
        }
        return res.toString();
    }

    public String editDiscount(int discountId, String field , String changTo){
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

    public String changeRole(String username , String changeTo){
        Account account = Database.getAccountByUsername(username);
        Database.removeAccount(account);
        if (changeTo.equalsIgnoreCase("Buyer")){
            try {
                Database.addAllAccounts(new BuyerAccount(account.getUsername(), account.getFirstName(),
                        account.getLastName(), account.getPassword(), account.getEmail(), account.getPhoneNumber(),
                        account.getCredit()));
            } catch (Exception e) {
                return e.getMessage();
            }
        }

        if (changeTo.equalsIgnoreCase("Admin")){
            try {
                Database.addAllAccounts(new AdminAccount(account.getUsername(), account.getFirstName(),
                        account.getLastName(), account.getPassword(), account.getEmail(), account.getPhoneNumber(),
                        account.getCredit()));
            } catch (Exception e) {
                return e.getMessage();
            }
        }


        if (changeTo.equalsIgnoreCase("Seller")){
            try {
                Database.addAllAccounts(new SellerAccount(account.getUsername(), account.getFirstName(),
                        account.getLastName(), account.getPassword(), account.getEmail(), account.getPhoneNumber(),
                        account.getCredit(), "No Company"));
            } catch (Exception e) {
                return e.getMessage();
            }
        }

        return "Changed successfully";
    }
}
