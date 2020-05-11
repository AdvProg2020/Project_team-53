package Controller;

import Model.Account;
import Model.AdminAccount;
import Model.Request.Request;

import java.util.ArrayList;

public class AdminManager {

    public static String showAllAccount() {
        ArrayList<Account> accounts = Database.getAllAccounts();
        StringBuilder res = new StringBuilder();
        for (Account account : accounts) {
            res.append("\n ++++++++++++++++++++++++++++++ \n" + account.showInfo());
        }
        return res.toString();
    }

    public static String showAccountWithUsername(String username) {
        Account account = Database.getAccountByUsername(username);
        if (account == null)
            return "No user with this username.";
        return account.showInfo();
    }

    public static String addNewAdminAccount(String username, String firstName, String lastName, String email, String phoneNumber, String password, int credit) {
        if (Database.getAccountByUsername(username) != null)
            return "Exist account with this username.";
        try {
            Database.addAllAccounts(new AdminAccount(username, firstName, lastName, password, email, phoneNumber, credit));
        } catch (Exception e){
            return e.getMessage();
        }
        return "New admin account registered.";
    }

    public static String showRequestByiId(int id){
        Request request = Database.getRequestById(id);
        if (request == null)
            return "No request with this id";
        return request.show();
    }

    public static String showAllRequests(){
        ArrayList<Request> requests = Database.getAllRequest();
        StringBuilder res = new StringBuilder();
        for (Request request : requests) {
            res.append(request.show() + "\n+++++++++++++++++++++++++++++++++++++++\n");
        }
        return res.toString();
    }

    public static String acceptOrRejectRequest(int id, boolean accept){
        Request request = Database.getRequestById(id);
        if (request == null)
            return "No request with this id";

        if (accept){
            request.acceptRequest();
        }
        Database.removeRequest(request);
        return "done!";
    }

    public static String deleteUsername(String username){
        Account account = Database.getAccountByUsername(username);
        if (account == null){
            return "No user with this username";
        }
        Database.removeAccount(account);
        return "Account deleted";
    }
}
