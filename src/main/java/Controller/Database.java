package Controller;

import Model.Account.*;
import Model.Log.BuyLog;
import Model.Messaging.Chat;
import Model.Product.Auction;
import Model.Product.Category;
import Model.Product.DiscountAndOff.Discount;
import Model.Product.DiscountAndOff.Off;
import Model.Product.Product;
import Model.Request.*;

import java.util.ArrayList;

public class Database {
    static ArrayList<Account> allAccounts = new ArrayList<>();
    static ArrayList<Request> allRequest = new ArrayList<>();
    static ArrayList<Product> allProducts= new ArrayList<>();
    static ArrayList<Category> allCategories = new ArrayList<>();
    static ArrayList<Discount> allDiscounts = new ArrayList<>();
    static ArrayList<Off> allOffs = new ArrayList<>();
    static ArrayList<Auction> allAuction = new ArrayList<>();
    static ArrayList<Account> onlineChatUsers = new ArrayList<>();
    static ArrayList<Chat> allChats = new ArrayList<>();

    public synchronized static void addChat(Chat chat) {
        allChats.add(chat);
    }

    public static Chat getChatById(int id) {
        for (Chat chat : allChats) {
            if (chat.getId() == id)
                return chat;
        }
        return null;
    }



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

    public static ArrayList<Auction> getAllAuction() {
        return allAuction;
    }

    public static ArrayList<Chat> getAllChats() {
        return allChats;
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

    public static Auction getAuctionByID(int auctionId){
        for (Auction auction : allAuction) {
            if (auction.getAuctionID() == auctionId)
                return auction;
        }
        return null;
    }


    public static String getUsers() {
        String res = "";
        for (Account account : allAccounts) {
            if (account instanceof BuyerAccount || account instanceof SupporterAccount)
                res += account.getUsername() + "\\n";
        }
        return res;
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

    public static void addAllAuction(Auction auction){
        allAuction.add(auction);
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

    public static void removeAuction(Auction auction){
        allAuction.remove(auction);
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
        allCategories = new ArrayList<>();
        allOffs = new ArrayList<>();
        allDiscounts = new ArrayList<>();
        allProducts = new ArrayList<>();
        allAccounts = new ArrayList<>();
        allRequest = new ArrayList<>();
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

    public static ArrayList<BuyerAccount> getAllBuyerAccounts(){
        ArrayList<BuyerAccount> res = new ArrayList<>();
        for (Account account : allAccounts) {
            if (account instanceof BuyerAccount)
                res.add((BuyerAccount) account);
        }
        return res;
    }

    public static ArrayList<SellerAccount> getAllSellerAccounts(){
        ArrayList<SellerAccount> res = new ArrayList<>();
        for (Account account : allAccounts) {
            if (account instanceof SellerAccount)
                res.add((SellerAccount) account);
        }
        return res;
    }

    public static ArrayList<AdminAccount> getAllAdminAccounts(){
        ArrayList<AdminAccount> res = new ArrayList<>();
        for (Account account : allAccounts) {
            if (account instanceof AdminAccount)
                res.add((AdminAccount) account);
        }
        return res;
    }

    public static ArrayList<AddNewOffRequest> getAllAddNewOffRequests(){
        ArrayList<AddNewOffRequest> res = new ArrayList<>();
        for (Request request : allRequest) {
            if (request instanceof AddNewOffRequest){
                res.add((AddNewOffRequest) request);
            }
        }
        return res;
    }

    public static ArrayList<DeleteProduct> getAllDeleteProductRequests(){
        ArrayList<DeleteProduct> res = new ArrayList<>();
        for (Request request : allRequest) {
            if (request instanceof DeleteProduct){
                res.add((DeleteProduct) request);
            }
        }
        return res;
    }

    public static ArrayList<EditOffRequest> getAllEditOffRequests(){
        ArrayList<EditOffRequest> res = new ArrayList<>();
        for (Request request : allRequest) {
            if (request instanceof EditOffRequest){
                res.add((EditOffRequest) request);
            }
        }
        return res;
    }


    public static ArrayList<EditProductRequest> getAllEditProductRequests(){
        ArrayList<EditProductRequest> res = new ArrayList<>();
        for (Request request : allRequest) {
            if (request instanceof EditProductRequest){
                res.add((EditProductRequest) request);
            }
        }
        return res;
    }


    public static ArrayList<NewProductRequest> getAllNewProductRequests(){
        ArrayList<NewProductRequest> res = new ArrayList<>();
        for (Request request : allRequest) {
            if (request instanceof NewProductRequest){
                res.add((NewProductRequest) request);
            }
        }
        return res;
    }


    public static ArrayList<NewSellerRequest> getAllNewSellerRequests(){
        ArrayList<NewSellerRequest> res = new ArrayList<>();
        for (Request request : allRequest) {
            if (request instanceof NewSellerRequest){
                res.add((NewSellerRequest) request);
            }
        }
        return res;
    }

    public static BuyLog getBuyLogByID(int id)
    {
        ArrayList<BuyerAccount> allBuyers = getAllBuyerAccounts();
        ArrayList<BuyLog> allBuyLogs = new ArrayList<>();
        for (BuyerAccount buyer : allBuyers) {
            allBuyLogs.addAll(buyer.getBuyLogs());
        }
        for (BuyLog buyLog : allBuyLogs) {
            if (buyLog.getLogId() == id)
            {
                return buyLog;
            }
        }
        return null;
    }
}
