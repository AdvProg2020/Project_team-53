package Controller;

import Model.Account.Account;
import Model.Account.AdminAccount;
import Model.Account.BuyerAccount;
import Model.Account.SellerAccount;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;

public class ClientThread extends Thread {
    private Account account;
    private Socket clientSocket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private AccountManager accountManager;
    private AdminManager adminManager;
    private AllProductManager allProductManager;
    private BuyerManager buyerManager;
    private SellerManager sellerManager;
    private ProductManager productManager;

    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            dataInputStream = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
            dataOutputStream = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        accountManager = new AccountManager();
        adminManager = new AdminManager();
        allProductManager = new AllProductManager();
        buyerManager = new BuyerManager();
        sellerManager = new SellerManager();
        productManager = new ProductManager();
    }

    @Override
    public void run() {
        try
        {
            String input;
            String output;
            String[] details;
            while (true)
            {
                input = dataInputStream.readUTF();
                output = null;
                if (input.startsWith("login"))
                {
                    details = input.split(" ");
                    Account account = Database.getAccountByUsername(details[1]);
                    if (this.account != null){
                        output = "You have already loggedIn. You have to logout first.";
                    }
                    else if (account == null)
                        output = "No User with this name";
                    else if (!account.getPassword().equals(details[2]))
                        output = "Wrong Password";
                    else {
                        this.account = account;
                        output = "Welcome " + account.getUsername();
                    }
                }
                else if (input.startsWith("logout"))
                {
                    account = null;
                }
                else if (input.startsWith("register"))
                {
                    details = input.split(" ");
                    output = accountManager.register(details[1], details[2], details[3], details[4], details[5], details[6], details[7], Integer.parseInt(details[8]), details[9]);
                }
                else if (input.startsWith("edit"))
                {
                    details = input.split(" ");
                    output = accountManager.edit(details[1], details[2], account);
                }
                else if (input.startsWith("ViewAccount"))
                {
                    Gson gson = new Gson();
                    output = gson.toJson(account.viewPersonalInfoInGraphic());
                }
                else if (input.startsWith("AllAccounts"))
                {
                    Gson gson = new Gson();
                    output = gson.toJson(Database.getAllAccounts());
                }
                else if (input.startsWith("AllProducts"))
                {
                    Gson gson = new Gson();
                    output = gson.toJson(Database.getAllProducts());
                }
                else if (input.startsWith("AllDiscounts"))
                {
                    Gson gson = new Gson();
                    output = gson.toJson(Database.getAllDiscounts());
                }
                else if (input.startsWith("AllRequest"))
                {
                    Gson gson = new Gson();
                    output = gson.toJson(Database.getAllRequest());
                }
                else if (input.startsWith("AllCategories"))
                {
                    Gson gson = new Gson();
                    output = gson.toJson(Database.getAllCategories());
                }
                else if (input.startsWith("AllOffs"))
                {
                    Gson gson = new Gson();
                    output = gson.toJson(Database.getAllOffs());
                }
                else if (input.startsWith("ChangeRole"))
                {
                    details = input.split(" ");
                    adminManager.changeRole(details[1], details[2]);
                }
                else if (input.startsWith("RemoveAccount"))
                {
                    details = input.split(" ");
                    adminManager.deleteUsername(details[1]);
                }
                else if (input.startsWith("RemoveProduct"))
                {
                    details = input.split(" ");
                    adminManager.deleteProduct(Integer.parseInt(details[1]));
                }
                else if (input.startsWith("ARRequest"))
                {
                    details = input.split(" ");
                    adminManager.acceptOrRejectRequest(Integer.parseInt(details[1]), Boolean.parseBoolean(details[2]));
                }
                else if (input.startsWith("RemoveCategory"))
                {
                    details = input.split(" ");
                    adminManager.deleteCategory(details[1]);
                }
                else if (input.startsWith("AddCategory"))
                {
                    details = input.split(" ");
                    output = adminManager.addNewCategory(details[1], details[2], details[3]);
                }
                else if (input.startsWith("EditCategory"))
                {
                    details = input.split(" ");
                    output = adminManager.editCategory(details[1], details[2], details[3]);
                }
                else if (input.startsWith("RemoveDiscount"))
                {
                    details = input.split(" ");
                    adminManager.removeDiscount(Integer.parseInt(details[1]));
                }
                else if (input.startsWith("AddDiscount"))
                {
                    details = input.split(" ");
                    Gson gson = new Gson();
                    ArrayList<String> users = gson.fromJson(details[6], new TypeToken<ArrayList<String>>(){}.getType());
                    output = adminManager.addNewDiscount(Integer.parseInt(details[1]), Integer.parseInt(details[2]), details[3], details[4], Integer.parseInt(details[5]), users);
                }
                else if (input.startsWith("AddAdmin"))
                {
                    details = input.split(" ");
                    output = adminManager.addNewAdminAccount(details[1], details[2], details[3], details[4], details[5], details[6], Integer.parseInt(details[7]));
                }
                else if (input.startsWith("ViewUsername"))
                {
                    Account temp = Database.getAccountByUsername(input.split(" ")[1]);
                    Gson gson = new Gson();
                    output = gson.toJson(temp.viewPersonalInfoInGraphic());
                }
                else if (input.startsWith("GetAccount"))
                {
                    Gson gson = new Gson();
                    output = gson.toJson(Database.getAccountByUsername(input.split(" ")[1]));
                }
                else if (input.startsWith("GetRequest"))
                {
                    Gson gson = new Gson();
                    output = gson.toJson(Database.getRequestById(Integer.parseInt(input.split(" ")[1])));
                }
                else if (input.startsWith("ViewDiscount"))
                {
                    Gson gson = new Gson();
                    output = gson.toJson(Objects.requireNonNull(Database.getDiscountById(Integer.parseInt(input.split(" ")[1]))).viewDiscount());
                }
                else if (input.startsWith("EditDiscount"))
                {
                    details = input.split(" ");
                    output = adminManager.editDiscount(Integer.parseInt(details[1]), details[2], details[3]);
                }
                else if (input.startsWith("GetLoggedAccount"))
                {
                    String role = "";
                    if (account instanceof AdminAccount)
                        role = "Admin";
                    else if (account instanceof SellerAccount)
                        role = "Seller";
                    else if (account instanceof BuyerAccount)
                        role = "Buyer";
                    output = role + " " + new Gson().toJson(account);
                }
                else if (input.startsWith("GetProduct"))
                {
                    output = new Gson().toJson(Database.getProductByID(Integer.parseInt(input.split(" ")[1])));
                }
                else if (input.startsWith("ViewProduct"))
                {
                    output = new Gson().toJson(Database.getProductByID(Integer.parseInt(input.split(" ")[1])).showProductFullInfoGraphic());
                }
                else if (input.startsWith("ShowAllProduct"))
                {
                    output = new Gson().toJson(allProductManager.showProductArray());
                }
                else if (input.startsWith("GetSortedBy"))
                {
                    output = new Gson().toJson(allProductManager.getSortedBy());
                }
                else if (input.startsWith("SetSortedBy"))
                {
                    allProductManager.setSortedBy(input.split(" ")[1]);
                }
                else if (input.startsWith("SetProduct"))
                {
                    productManager.setProduct(Database.getProductByID(Integer.parseInt(input.split(" ")[1])));
                }
                else if (input.startsWith("GetFilterOption"))
                {
                    output = new Gson().toJson(allProductManager.getFilterOptions());
                    System.out.println("test" + output);
                }
                else if (input.startsWith("RemoveFilter"))
                {
                    allProductManager.removeFilterOption(input.split(" ")[1]);
                }
                else if (input.startsWith("AddFilter"))
                {
                    details = input.split(" ");
                    allProductManager.addFilterOption(details[1] + " " + details[2]);
                }
                else if (input.startsWith("GetMainProduct"))
                {
                    output = new Gson().toJson(productManager.getProduct());
                }
                else if (input.startsWith("AddToCart"))
                {
                    output = buyerManager.addProductToCart(productManager.getProduct(), account);
                }
                else if (input.startsWith("GiveComment"))
                {
                    details = input.split(" ");
                    output = productManager.giveComment(details[1], details[2], account);
                }
                else if (input.startsWith("GiveScore"))
                {
                    output = productManager.giveScore(Integer.parseInt(input.split(" ")[1]), account);
                }
                if (output != null)
                {
                    dataOutputStream.writeUTF(output);
                    dataOutputStream.flush();
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}

