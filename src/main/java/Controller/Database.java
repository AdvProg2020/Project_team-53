package Controller;

import Model.Account;
import Model.AdminAccount;
import Model.BuyerAccount;
import Model.SellerAccount;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Database {
    private static ArrayList<Account> allAccounts = new ArrayList();


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
        ;
    }
}
