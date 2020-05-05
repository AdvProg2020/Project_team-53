package Controller;

import Model.Account;
import Model.AdminAccount;
import Model.BuyerAccount;
import Model.Request.Request;
import Model.SellerAccount;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Database {
    private static ArrayList<Account> allAccounts = new ArrayList<>();
    private static ArrayList<Request> allRequest = new ArrayList<>();
    public static ArrayList<Account> getAllAccounts() {
        return allAccounts;
    }

    public static ArrayList<Request> getAllRequest() {
        return allRequest;
    }

    public static Account getAccountByUsername(String username){
        for (Account account : allAccounts) {
            if (account.getUsername().equals(username))
                return account;
        }
        return null;
    }

    public static void addAllAccounts(Account account){
        allAccounts.add(account);
    }

    public static Request getRequestById(int id){
        for (Request request : allRequest) {
            if (request.getId() == id)
                return request;
        }
        return null;
    }

    public static void addRequest(Request request){
        allRequest.add(request);
    }


    public static void writeDataOnFile(){
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

        writeArrayOnFile(admins, "Admins");
        writeArrayOnFile(sellers, "Sellers");
        writeArrayOnFile(buyers, "Buyers");
    }

    private static void writeArrayOnFile(ArrayList<Account> arr, String name){
        File file = new File("Data\\"+ name + ".json");
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

    public static void initialize(){
        for (Account account : allAccounts) {
            if (account instanceof AdminAccount && account.getUsername().equals("Admin"))
                return;
        }
     allAccounts.add(new AdminAccount("Admin", "Admin", "Admin", "Admin", "Admin@gmail.com", "00000000", 0));
    }
}
