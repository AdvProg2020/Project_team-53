package Controller;

import Model.Account;

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
}
