package Controller;

import Model.Account.*;
import Model.Messaging.Chat;
import Model.Product.Auction;
import Model.Product.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;

public class ClientThread extends Thread {
    private Account account;
    private Server server;
    private Socket clientSocket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private AccountManager accountManager;
    private AdminManager adminManager;
    private AllProductManager allProductManager;
    private BuyerManager buyerManager;
    private SellerManager sellerManager;
    private ProductManager productManager;
    private ChatManager chatManager;
    public ClientThread(Socket clientSocket, Server server) {
        this.server = server;
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
        chatManager = new ChatManager();
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
                String[] split = input.split(" ");
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
                    if (this.account instanceof AdminAccount)
                        server.addOnlineAdmin((AdminAccount) account);
                    else if (this.account instanceof BuyerAccount)
                        server.addOnlineBuyer((BuyerAccount) account);
                    else if (this.account instanceof SellerAccount)
                        server.addOnlineSeller((SellerAccount) account);
                    else if (this.account instanceof SupporterAccount)
                        server.addOnlineSupporter((SupporterAccount) account);
                }
                else if (input.startsWith("logout"))
                {
                    if (this.account instanceof AdminAccount)
                        server.removeOnlineAdmin((AdminAccount) account);
                    else if (this.account instanceof BuyerAccount)
                        server.removeOnlineBuyer((BuyerAccount) account);
                    else if (this.account instanceof SellerAccount)
                        server.removeOnlineSeller((SellerAccount) account);
                    else if (this.account instanceof SupporterAccount)
                        server.removeOnlineSupporter((SupporterAccount) account);
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
                else if (input.startsWith("AllAuction"))
                {
                    ArrayList<Auction> allAuctions = Database.getAllAuction();
                    ArrayList<String> name = new ArrayList<>();
                    for (Auction auction : allAuctions) {
                        name.add("ID:" + auction.getAuctionID() + "_Product:" + auction.getProduct().getName());
                    }
                    output = new Gson().toJson(name);
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
                    details = input.split("\n");
                    Gson gson = new Gson();
                    String buyers = details[6];
                    ArrayList<String> users = gson.fromJson(details[6], new TypeToken<ArrayList<String>>(){}.getType());
                    output = adminManager.addNewDiscount(Integer.parseInt(details[1]), Integer.parseInt(details[2]), details[3], details[4], Integer.parseInt(details[5]), users);
                }
                else if (input.startsWith("AddAdmin"))
                {
                    details = input.split(" ");
                    output = adminManager.addNewAdminAccount(details[1], details[2], details[3], details[4], details[5], details[6], Integer.parseInt(details[7]));
                }
                else if (input.startsWith("AddSupporter")) {
                    output = adminManager.addNewSupporterAccount(split[1], split[2], split[3], split[4], split[5], split[6], Integer.parseInt(split[7]));
                }
                else if (input.startsWith("ViewUsername"))
                {
                    Account temp = Database.getAccountByUsername(input.split(" ")[1]);
                    Gson gson = new Gson();
                    output = gson.toJson(Objects.requireNonNull(temp).viewPersonalInfoInGraphic());
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
                else if (input.startsWith("EditDiscount"))
                {
                    details = input.split(" ");
                    output = adminManager.editDiscount(Integer.parseInt(details[1]), details[2], details[3]);
                }
                else if (input.startsWith("GetLoggedAccount"))
                {
                    if (account instanceof AdminAccount)
                        output = "Admin_" + new Gson().toJson(account);
                    else if (account instanceof SellerAccount)
                        output = "Seller_" + new Gson().toJson(account);
                    else if (account instanceof BuyerAccount)
                        output = "Buyer_" + new Gson().toJson(account);
                    else if (account instanceof SupporterAccount)
                        output = "Supp_" + new Gson().toJson(account);
                }
                else if (input.startsWith("GetProduct"))
                {
                    output = new Gson().toJson(Database.getProductByID(Integer.parseInt(input.split(" ")[1])));
                }
                else if (input.startsWith("ViewProduct"))
                {
                    output = new Gson().toJson(Objects.requireNonNull(Database.getProductByID(Integer.parseInt(input.split(" ")[1]))).showProductFullInfoGraphic());
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
                else if (input.startsWith("EditOff"))
                {
                    details = input.split(" ");
                    output = sellerManager.editOff(Integer.parseInt(details[1]), details[2], details[3], account);
                }
                else if (input.startsWith("DeleteProduct"))
                {
                    sellerManager.deleteProduct(Integer.parseInt(input.split(" ")[1]));
                }
                else if (input.startsWith("EditProduct"))
                {
                    details = input.split(" ");
                    output = sellerManager.sendEditProductRequest(Integer.parseInt(details[1]), details[2], details[3], account);
                }
                else if (input.startsWith("AddNewOff"))
                {
                    details = input.split(" ");
                    ArrayList<Integer> productsID = new Gson().fromJson(details[5], new TypeToken<ArrayList<Integer>>(){}.getType());
                    output = sellerManager.addNewOff(Integer.parseInt(details[1]), Integer.parseInt(details[2]), details[3], details[4], productsID, account);
                }
                else if (input.startsWith("AddNewProduct"))
                {
                    details = input.split(" ");
                    output = sellerManager.sendAddProductRequest(details[1], details[2], Boolean.parseBoolean(details[3]),
                            Integer.parseInt(details[4]), details[5], details[6], Integer.parseInt(details[7]), account, Boolean.parseBoolean(details[8]), details[9]);
                }
                else  if (input.startsWith("GetBuyerOfProduct"))
                {
                    output = sellerManager.getBuyerOfProduct(Integer.parseInt(input.split(" ")[1]), account);
                }
                else if (input.startsWith("GetDiscountWithID"))
                {
                    output = new Gson().toJson(Database.getDiscountById(Integer.parseInt(input.split(" ")[1])));
                }
                else if (input.startsWith("DecreaseProduct"))
                {
                    output = buyerManager.decreaseProduct(Integer.parseInt(input.split(" ")[1]), account);
                }
                else if (input.startsWith("IncreaseProduct"))
                {
                    output = buyerManager.addProductToCart(Objects.requireNonNull(Database.getProductByID(Integer.parseInt(input.split(" ")[1]))), account);
                }
                else if (input.startsWith("Pay"))
                {
                    details = input.split(" ");
                    output = buyerManager.pay(Integer.parseInt(details[1]), account, details[2]);
                }
                else if (input.startsWith("AddAuction"))
                {
                    details = input.split(" ");
                    output = sellerManager.addAuction(Integer.parseInt(details[1]), details[2], account);
                }
                else if (input.startsWith("GetOnlineAdmins"))
                {
                    output = new Gson().toJson(server.getAllOnlineAdmins());
                }
                else if (input.startsWith("GetOnlineBuyers"))
                {
                    output = new Gson().toJson(server.getAllOnlineBuyers());
                }
                else if (input.startsWith("GetOnlineSellers"))
                {
                    output = new Gson().toJson(server.getAllOnlineSellers());
                }
                else if (input.startsWith("GetOnlineSupporters"))
                {
                    output = new Gson().toJson(server.getAllOnlineSupporters());
                }
                else if (input.startsWith("JoinAuction"))
                {
                    Objects.requireNonNull(Database.getAuctionByID(Integer.parseInt(input.split(" ")[1]))).joinAuction((BuyerAccount) account);
                }
                else if (input.startsWith("GetAuctionOfAccount"))
                {
                    ArrayList<Auction> allAuctions = Database.getAllAuction();
                    ArrayList<String> myAuctions = new ArrayList<>();
                    for (Auction auction : allAuctions) {
                        if (auction.containBuyer((BuyerAccount) account))
                        {
                            myAuctions.add("ID:" + auction.getAuctionID() + "_Product:" + auction.getProduct().getName());
                        }
                    }
                    output = new Gson().toJson(myAuctions);
                }
                else if (input.startsWith("GetCostOfAccountCart"))
                {
                    output = Long.toString(((BuyerAccount)account).getCart().getCost());

                }
                else if (input.startsWith("GetCartOfAccount"))
                {
                    output = new Gson().toJson(((BuyerAccount)account).getCart());
                }
                else if (input.startsWith("SetPortOfSeller"))
                {
                    server.setPortForSeller((SellerAccount) account, Integer.parseInt(input.split(" ")[1]));
                }
                else if (input.startsWith("GetPortOfSeller"))
                {
                    output = Integer.toString(server.getPortOfSeller(((SellerAccount)Database.getAccountByUsername(input.split(" ")[1]))));
                }
                else if (input.startsWith("GetAllBuyerAccounts"))
                {
                    output = new Gson().toJson(Database.getAllBuyerAccounts());
                }
                else if (input.startsWith("GetAllSellerAccounts"))
                {
                    output = new Gson().toJson(Database.getAllSellerAccounts());
                }
                else if (input.startsWith("GetAllAdminAccounts"))
                {
                    output = new Gson().toJson(Database.getAllAdminAccounts());
                }
                else if (input.startsWith("GetAllAddNewOffRequest"))
                {
                    output = new Gson().toJson(Database.getAllAddNewOffRequests());
                }
                else if (input.startsWith("GetAllDeleteProductRequest"))
                {
                    output = new Gson().toJson(Database.getAllDeleteProductRequests());
                }
                else if (input.startsWith("GetAllEditOffRequest"))
                {
                    output = new Gson().toJson(Database.getAllEditOffRequests());
                }
                else if (input.startsWith("GetAllEditProductRequest"))
                {
                    output = new Gson().toJson(Database.getAllEditProductRequests());
                }
                else if (input.startsWith("GetAllNewProductRequest"))
                {
                    output = new Gson().toJson(Database.getAllNewProductRequests());
                }
                else if (input.startsWith("GetAllNewSellerRequest"))
                {
                    output = new Gson().toJson(Database.getAllNewSellerRequests());
                }
                else if (input.startsWith("GetDiscountOfAccount"))
                {
                    output = new Gson().toJson(((BuyerAccount)account).getDiscountIds());
                }
                else if (input.startsWith("PrOfCart"))
                {
                    ArrayList<Integer> productsID = ((BuyerAccount)account).getCart().getProductsID();
                    ArrayList<Product> allProducts = new ArrayList<>();
                    for (Integer integer : productsID) {
                        allProducts.add(Database.getProductByID(integer));
                    }
                    output = new Gson().toJson(allProducts);
                }
                else if (input.startsWith("changeCredit")){
                    details = input.split(" ");
                    output = accountManager.changeCredit(account , Integer.parseInt(details[1]), details[2] , details[3], details[4]);
                }
                else if (input.startsWith("changeFinancialSetting")){
                    details = input.split(" ");
                    output = adminManager.changeFinancialSetting(details[1] , details[2]);
                }
                else if (input.startsWith("ReceivedBuyLog"))
                {
                    Objects.requireNonNull(Database.getBuyLogByID(Integer.parseInt(input.split(" ")[1]))).setDeliveryStatus("Received");
                }
                else if (input.startsWith("getAllSupporters")){
                    output = Database.getUsers();
                }
                else if (input.startsWith("addMessage")) {
                    chatManager.addMessage(Integer.parseInt(split[1]), split[2], account);
                } else if (input.startsWith("getChatById")) {
                    output = new Gson().toJson(Database.getChatById(Integer.parseInt(split[1])));
                } else if (input.startsWith("chatWith")) {
                    BuyerAccount buyerAccount = (BuyerAccount) account;
                    if (buyerAccount.hasChatWith(Database.getAccountByUsername(split[1])) != null)
                        output = String.valueOf(buyerAccount.hasChatWith(Database.getAccountByUsername(split[1])).getId());
                    else {
                        ArrayList<Account> arrayList = new ArrayList();
                        arrayList.add(account);
                        arrayList.add((Database.getAccountByUsername(split[1])));
                        Chat chat = new Chat(arrayList);
                        //buyerAccount.addChat(chat);
                        output = String.valueOf(chat.getId());
                    }
                }
                else if (input.startsWith("GetMPBOfAuction"))
                {
                    try {
                        output = new Gson().toJson(Objects.requireNonNull(Database.getAuctionByID(Integer.parseInt(input.split(" ")[1]))).getBuyerWithMostPrice().getUsername());
                    }
                    catch (Exception e)
                    {
                        output = "NoOne";
                    }
                }
                else if (input.startsWith("GetMPOfAuction"))
                {
                    output = new Gson().toJson(Objects.requireNonNull(Database.getAuctionByID(Integer.parseInt(input.split(" ")[1]))).getMostPrice());
                }
                else if (input.startsWith("SetMostPriceOfAuction"))
                {
                    details = input.split(" ");
                    output = buyerManager.setPriceForAuction(Integer.parseInt(details[1]), Integer.parseInt(details[2]), account);
                }
                else if (input.startsWith("BankPay ")){
                    details = input.split(" ");
                    output = buyerManager.bankPay(account , Integer.parseInt(details[1]), details[2] , details[3], details[4] ,details[5]);
                }
                else if (input.startsWith("Exit"))
                {
                    clientSocket.close();
                    server.writeDataOnFile();
                    if (account != null)
                    {
                        if (this.account instanceof AdminAccount)
                            server.removeOnlineAdmin((AdminAccount) account);
                        else if (this.account instanceof BuyerAccount)
                            server.removeOnlineBuyer((BuyerAccount) account);
                        else if (this.account instanceof SellerAccount)
                            server.removeOnlineSeller((SellerAccount) account);
                    }
                    break;
                }
                if (output != null)
                {
                    dataOutputStream.writeUTF(output);
                    dataOutputStream.flush();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    }

