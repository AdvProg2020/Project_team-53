package Controller;

import Model.*;
import Model.Request.Request;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Database {
    private static ArrayList<Account> allAccounts = new ArrayList<>();
    private static ArrayList<Request> allRequest = new ArrayList<>();
    private static ArrayList<Product> allProducts= new ArrayList<>();

    public static ArrayList<Account> getAllAccounts() {
        return allAccounts;
    }

    public static ArrayList<Request> getAllRequest() {
        return allRequest;
    }

    public static ArrayList<Product> getAllProducts() {
        return allProducts;
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

    public static void addAllAccounts(Account account) {
        allAccounts.add(account);
    }

    public static void addRequest(Request request) {
        allRequest.add(request);
    }

    public static void addAllProduct(Product product) {
        allProducts.add(product);
    }

    public static void writeDataOnFile() {
        writeAccountsOnFile();
        writeProductsOnFile();
    }

    private static void writeProductsOnFile() {
        File file = new File("Data\\Products\\Products.json");
        file.getParentFile().mkdirs();
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(file);
            Gson gson = new Gson();
            String json = gson.toJson(allProducts);
            fileWriter.write(json);
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeAccountsOnFile(){
        ArrayList<Account> sellers = new ArrayList<>();
        ArrayList<Account> admins = new ArrayList<>();
        ArrayList<Account> buyers = new ArrayList<>();
        for (Account account : allAccounts) {
            if (account instanceof AdminAccount)
                admins.add(account);
            else if (account instanceof SellerAccount)
                sellers.add(account);
            else if (account instanceof BuyerAccount)
                buyers.add(account);
            else
                System.out.println("What the hell");
        }
        writeArrayAccountOnFile(admins, "Accounts\\Admins");
        writeArrayAccountOnFile(sellers, "Accounts\\Sellers");
        writeArrayAccountOnFile(buyers, "Accounts\\Buyers");
    }

    private static void writeArrayAccountOnFile(ArrayList<Account> arr, String name) {
        File file = new File("Data\\" + name + ".json");
        file.getParentFile().mkdirs();
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(file);
            Gson gson = new Gson();
            String json = gson.toJson(arr);
            fileWriter.write(json);
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public static void initialize() {
        readArrayOfAccountFromFile("Admins");
        readArrayOfAccountFromFile("Buyers");
        readArrayOfAccountFromFile("Sellers");

        readArrayOfProductFromFile("Products");
        for (Account account : allAccounts) {
            if (account instanceof AdminAccount && account.getUsername().equals("Admin"))
                return;
        }
        try {
            allAccounts.add(new AdminAccount("Admin", "Admin", "Admin", "Admin", "Admin@gmail.com", "00000000", 0));
        } catch (Exception e) {

        }
    }

    private static void readArrayOfProductFromFile(String place) {
        Gson gson = new Gson();
        File file = new File("Data\\Products\\"+place+".json");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Type type = new TypeToken<ArrayList<Product>>(){}.getType();
        allProducts = gson.fromJson(br, type);
    }

    private static void readArrayOfAccountFromFile(String place) {
        Gson gson = new Gson();
        File file = new File("Data\\Accounts\\"+place+".json");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Account> arr = new ArrayList<>();
        Type type ;
        if (place.equals("Admins")) {
             type = new TypeToken<ArrayList<AdminAccount>>(){}.getType();
        }
        else if (place.equals("Buyers")){
            type = new TypeToken<ArrayList<BuyerAccount>>(){}.getType();
        }
        else{
            type = new TypeToken<ArrayList<SellerAccount>>(){}.getType();
        }
        arr = gson.fromJson(br, type);

        allAccounts.addAll(arr);
    }

}
