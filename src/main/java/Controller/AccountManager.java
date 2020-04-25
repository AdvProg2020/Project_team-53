package Controller;

import Model.Account;
import Model.SellerAccount;

public class AccountManager {
    private static Account loggedInAccount = null ;

    public static String logIn(String username , String password)  {
        Account account = Database.getAccountByUsername(username);
        if (account == null)
            return "No User with this name";
        if (!account.getPassword().equals(password))
            return "Wrong Password";
        loggedInAccount = account;
        return "Welcome" + account.getUsername();
    }

    public static Account getLoggedInAccount() {
        return loggedInAccount;
    }

    public static String edit(String field , String changeTo){
        if (field.equals("username")){
            if (Database.getAccountByUsername(changeTo) != null){
                return "Exist a user with this username";
            }
            try {
                loggedInAccount.setUsername(changeTo);
                return "changed successfully";
            } catch (Exception e) {
                return e.getMessage();
            }
        }

        if(field.equals("lastName")){
            try {
                loggedInAccount.setLastName(changeTo);
                return "changed successfully";
            } catch (Exception e) {
                return e.getMessage();
            }
        }

        if (field.equals("password")){
            try {
                loggedInAccount.setPassword(changeTo);
                return "changed successfully";
            } catch (Exception e) {
                return e.getMessage();
            }
        }

        if(field.equals("email")){
            try {
                loggedInAccount.setEmail(changeTo);
                return "changed successfully";
            } catch (Exception e) {
                return e.getMessage();
            }
        }

        if(field.equals("phoneNumber")){
            try {
                loggedInAccount.setPhoneNumber(changeTo);
                return "changed successfully";
            } catch (Exception e) {
                return e.getMessage();
            }
        }

        return "Invalid field";
    }

    public static String logOut(){
        if (loggedInAccount == null)
            return "You haven't logged in";
        loggedInAccount = null ;
        return "logged out successfully";
    }
}
