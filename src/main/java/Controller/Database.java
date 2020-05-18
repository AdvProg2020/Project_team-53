package Controller;

import Model.*;
import Model.Request.Request;

import java.util.ArrayList;

public class Database {
    static ArrayList<Account> allAccounts = new ArrayList<>();
    static ArrayList<Request> allRequest = new ArrayList<>();
    static ArrayList<Product> allProducts= new ArrayList<>();
    static ArrayList<Category> allCategories = new ArrayList<>();
    static ArrayList<Discount> allDiscounts = new ArrayList<>();
    static ArrayList<Off> allOffs = new ArrayList<>();

    public static ArrayList<Account> getAllAccounts() {
        return allAccounts;
    }

    public static ArrayList<Request> getAllRequest() {
        return allRequest;
    }

    public static ArrayList<Product> getAllProducts() {
        return allProducts;
    }

    public static ArrayList<Category> getAllCategories() {
        return allCategories;
    }

    public static ArrayList<Discount> getAllDiscounts() {
        return allDiscounts;
    }

    public static ArrayList<Off> getAllOffs() {
        return allOffs;
    }


    public static Account getAccountByUsername(String username) {
        for (Account account : allAccounts) {
            if (account.getUsername().equals(username))
                return account;
        }
        return null;
    }

    public static Product getProductByID(int productId){
        for (Product product : allProducts) {
            if (product.getProductId() == productId)
                return product;
        }
        return null;
    }

    public static Request getRequestById(int id) {
        for (Request request : allRequest) {
            if (request.getId() == id)
                return request;
        }
        return null;
    }

    public static Category getCategoryByName(String categoryName){
        for (Category category : allCategories) {
            if (category.getName().equals(categoryName))
                return category;
        }
        return null;
    }

    public static Discount getDiscountById(int discountId){
        for (Discount discount : allDiscounts) {
            if (discount.getDiscountId() == discountId)
                return discount;
        }
        return null;
    }

    public static Off getOffById(int offId){
        for (Off off : allOffs) {
            if (off.getOffId() == offId){
                return off;
            }
        }
        return null;
    }

    public static void addAllAccounts(Account account) {
        allAccounts.add(account);
    }

    public static void addRequest(Request request) {
        allRequest.add(request);
    }

    public static void addAllProduct(Product product) {
        allProducts.add(product);
    }

    public static void addAllCategory(Category category){
        allCategories.add(category);
    }

    public static void addAllDiscount(Discount discount){
        allDiscounts.add(discount);
    }

    public static void addAllOff(Off off){
        allOffs.add(off);
    }

    public static void removeRequest(Request request) {
        allRequest.remove(request);
    }

    public static void removeAccount(Account account) {
        allAccounts.remove(account);
    }

    public static void removeProduct(Product product) {
        allProducts.remove(product);
    }

    public static void removeCategory(Category category){
        allCategories.remove(category);
        for (Integer productId : category.getAllProductIds()) {
            Product product = getProductByID(productId);
            if (product != null){
                product.setCategoryNameWithoutAddToCategory("no category");
            }
        }
    }

    public static void removeDiscount(Discount discount){
        allDiscounts.remove(discount);
    }

    public static void removeOff(Off off){
        allOffs.remove(off);
    }

    public static void writeDataOnFile() {
        WorkWithFile.writeAccountsOnFile();
        WorkWithFile.writeProductsOnFile();
        WorkWithFile.writeCategoriesOnFile();
        WorkWithFile.writeDiscountsOnFile();
        WorkWithFile.writeOffsOnFile();
        WorkWithFile.writeRequestsOnFile();
        WorkWithFile.writeIDOnFile();
    }

    public static void initialize() {
        WorkWithFile.initialize();

        for (Account account : allAccounts) {
            if (account instanceof AdminAccount && account.getUsername().equals("Admin"))
                return;
        }
        try {
            allAccounts.add(new AdminAccount("Admin", "Admin", "Admin", "Admin", "Admin@gmail.com", "00000000", 0));
        } catch (Exception e) {

        }
    }

}
