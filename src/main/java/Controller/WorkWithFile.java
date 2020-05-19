package Controller;

import Model.*;
import Model.Account.Account;
import Model.Account.AdminAccount;
import Model.Account.BuyerAccount;
import Model.Account.SellerAccount;
import Model.Log.Log;
import Model.Request.*;
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
        readArrayOfAllThingFromFile("DisAndOff\\Offs");

        readArrayOfRequestFromFile("AddNewOffRequests");
        readArrayOfRequestFromFile("EditOffRequests");
        readArrayOfRequestFromFile("EditProductRequests");
        readArrayOfRequestFromFile("NewProductRequests");
        readArrayOfRequestFromFile("NewSellerRequests");

        readIdsFromFile();
    }

    private static void readIdsFromFile() {
        Gson gson = new Gson();
        File file = new File("Data\\IDs\\Id.json");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Type type = new TypeToken<ArrayList<Integer>>(){}.getType();
        ArrayList<Integer> arr = gson.fromJson(br, type);
        Log.setAllLogId(arr.get(0));
        Request.setAllRequestId(arr.get(1));
        Discount.setAllDiscountId(arr.get(2));
        Off.setAllOffIds(arr.get(3));
        Product.setNumberOfAllProducts(arr.get(4));
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
        else if (place.endsWith("Offs")){
            Type type = new TypeToken<ArrayList<Off>>(){}.getType();
            allOffs = gson.fromJson(br, type);
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

        ArrayList<Account> arr;
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

    public static void readArrayOfRequestFromFile(String place){
        Gson gson = new Gson();
        File file = new File("Data\\Requests\\"+place+".json");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Request> arr;
        Type type ;
        if (place.equals("AddNewOffRequests")) {
            type = new TypeToken<ArrayList<AddNewOffRequest>>(){}.getType();
        }
        else if (place.equals("EditOffRequests")){
            type = new TypeToken<ArrayList<EditOffRequest>>(){}.getType();
        }
        else if (place.equals("EditProductRequests")){
            type = new TypeToken<ArrayList<EditProductRequest>>(){}.getType();
        }
        else if (place.equals("NewProductRequests")){
            type = new TypeToken<ArrayList<NewProductRequest>>(){}.getType();
        }
        else {
            type = new TypeToken<ArrayList<NewSellerRequest>>(){}.getType();
        }

        arr = gson.fromJson(br, type);

        allRequest.addAll(arr);
    }

    public static void writeProductsOnFile() {
        ArrayList<Object> arr = new ArrayList<>(allProducts);
        writeArrayOnFile(arr, "Products\\Products");
    }

    public static void writeAccountsOnFile(){
        ArrayList<Object> sellers = new ArrayList<>();
        ArrayList<Object> admins = new ArrayList<>();
        ArrayList<Object> buyers = new ArrayList<>();
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
        writeArrayOnFile(admins, "Accounts\\Admins");
        writeArrayOnFile(sellers, "Accounts\\Sellers");
        writeArrayOnFile(buyers, "Accounts\\Buyers");
    }

    public static void writeCategoriesOnFile() {
        ArrayList<Object> arr = new ArrayList<>(allCategories);
        writeArrayOnFile(arr, "Products\\Categories");
    }

    public static void writeDiscountsOnFile() {
        ArrayList<Object> arr = new ArrayList<>(allDiscounts);
        writeArrayOnFile(arr, "DisAndOff\\Discounts");
    }

    public static void writeOffsOnFile() {
        ArrayList<Object> arr = new ArrayList<>(allOffs);
        writeArrayOnFile(arr, "DisAndOff\\Offs");
    }

    public static void writeRequestsOnFile(){
        ArrayList<Object> addNewOff = new ArrayList<>();
        ArrayList<Object> editOff = new ArrayList<>();
        ArrayList<Object> editProduct = new ArrayList<>();
        ArrayList<Object> newProduct = new ArrayList<>();
        ArrayList<Object> newSeller = new ArrayList<>();

        for (Request request : allRequest) {
            if (request instanceof AddNewOffRequest)
                addNewOff.add(request);
            else if (request instanceof EditOffRequest)
                editOff.add(request);
            else if (request instanceof EditProductRequest)
                editProduct.add(request);
            else if (request instanceof NewProductRequest)
                newProduct.add(request);
            else if (request instanceof NewSellerRequest)
                newSeller.add(request);
        }

        writeArrayOnFile(addNewOff, "Requests\\AddNewOffRequests");
        writeArrayOnFile(editOff, "Requests\\EditOffRequests");
        writeArrayOnFile(editProduct, "Requests\\EditProductRequests");
        writeArrayOnFile(newProduct, "Requests\\NewProductRequests");
        writeArrayOnFile(newSeller, "Requests\\NewSellerRequests");

    }

    private static void writeArrayOnFile(ArrayList<Object> arr, String name) {
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

    public static void writeIDOnFile(){
        ArrayList<Object> arr = new ArrayList<>();
        arr.add(Log.getAllLogId());
        arr.add(Request.getAllRequestId());
        arr.add(Discount.getAllDiscountId());
        arr.add(Off.getAllOffIds());
        arr.add(Product.getNumberOfAllProducts());
        writeArrayOnFile(arr, "IDs\\Id");
    }
}
