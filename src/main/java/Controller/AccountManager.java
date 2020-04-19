package Controller;

import Model.Account;

public class AccountManager {
    Account loggedInAccount = null ;

    void logIn(String username , String password) throws Exception {
        Account account = Database.getAccountByUsername(username);
        if (account == null)
            throw new Exception("No User with this name");
        if (!account.getPassword().equals(password))
            throw new Exception("Wrong Password");
        loggedInAccount = account;
    }



}
