package Controller;

import Model.Account.AdminAccount;
import Model.Account.BuyerAccount;
import Model.Account.SellerAccount;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ServerSocket serverSocket;
    private ArrayList<AdminAccount> allOnlineAdmins;
    private ArrayList<BuyerAccount> allOnlineBuyers;
    private ArrayList<SellerAccount> allOnlineSellers;

    public static void main(String[] args) {
        Server server = new Server();
        Database.initialize();
        server.waitForClient();
    }

    public Server() {
        try {
            this.serverSocket = new ServerSocket(8585);
            allOnlineAdmins = new ArrayList<>();
            allOnlineBuyers = new ArrayList<>();
            allOnlineSellers = new ArrayList<>();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addOnlineAdmin(AdminAccount adminAccount) {
        allOnlineAdmins.add(adminAccount);
    }

    public void addOnlineBuyer(BuyerAccount buyerAccount){
        allOnlineBuyers.add(buyerAccount);
    }

    public void addOnlineSeller(SellerAccount sellerAccount){
        allOnlineSellers.add(sellerAccount);
    }

    public void removeOnlineAdmin(AdminAccount adminAccount){
        allOnlineAdmins.remove(adminAccount);
    }

    public void removeOnlineBuyer(BuyerAccount buyerAccount){
        allOnlineBuyers.remove(buyerAccount);
    }

    public void removeOnlineSeller(SellerAccount sellerAccount){
        allOnlineSellers.remove(sellerAccount);
    }

    public ArrayList<AdminAccount> getAllOnlineAdmins() {
        return allOnlineAdmins;
    }

    public ArrayList<BuyerAccount> getAllOnlineBuyers() {
        return allOnlineBuyers;
    }

    public ArrayList<SellerAccount> getAllOnlineSellers() {
        return allOnlineSellers;
    }

    private void waitForClient()
    {
        while (true)
        {
            try {
                System.out.println("Waiting for client ...");
                Socket clientSocket = serverSocket.accept();
                new ClientThread(clientSocket, this).start();
                System.out.println("Client connected");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
