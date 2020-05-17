package Controller;

import Model.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static Controller.Database.*;

public class WorkWithFile {
    public static void initialize(){

        readArrayOfAccountFromFile("Admins");
        readArrayOfAccountFromFile("Buyers");
        readArrayOfAccountFromFile("Sellers");

        readArrayOfAllThingFromFile("Products\\Products");
        readArrayOfAllThingFromFile("Products\\Categories");
        readArrayOfAllThingFromFile("DisAndOff\\Discounts");
    }

    public static void readArrayOfAllThingFromFile(String place) {
        Gson gson = new Gson();
        File file = new File("Data\\"+place+".json");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (place.endsWith("Products")) {
            Type type = new TypeToken<ArrayList<Product>>(){}.getType();
            allProducts = gson.fromJson(br, type);
        }
        else if (place.endsWith("Categories")){
            Type type = new TypeToken<ArrayList<Category>>(){}.getType();
            allCategories = gson.fromJson(br, type);
        }
        else if (place.endsWith("Discounts")){
            Type type = new TypeToken<ArrayList<Discount>>(){}.getType();
            allDiscounts = gson.fromJson(br, type);
        }
    }


    public static void readArrayOfAccountFromFile(String place) {
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


    public static void writeProductsOnFile() {
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

    public static void writeAccountsOnFile(){
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

    public static void writeCategoriesOnFile() {
        File file = new File("Data\\Products\\Categories.json");
        file.getParentFile().mkdirs();
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(file);
            Gson gson = new Gson();
            String json = gson.toJson(allCategories);
            fileWriter.write(json);
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeDiscountsOnFile() {
        File file = new File("Data\\DisAndOff\\Discounts.json");
        file.getParentFile().mkdirs();
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(file);
            Gson gson = new Gson();
            String json = gson.toJson(allDiscounts);
            fileWriter.write(json);
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
