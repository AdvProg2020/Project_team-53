package Controller;

import Model.Account;
import Model.BuyerAccount;
import Model.Request.NewSellerRequest;
import Model.SellerAccount;

public class AccountManager {
    private static Account loggedInAccount = null ;

    public static String logIn(String username , String password)  {
        if (loggedInAccount != null){
            return "You have already loggedIn. You have to logout first.";
        }
        Account account = Database.getAccountByUsername(username);
        if (account == null)
            return "No User with this name";
        if (!account.getPassword().equals(password))
            return "Wrong Password";
        loggedInAccount = account;
        return "Welcome " + account.getUsername();
    }

    public static Account getLoggedInAccount() {
        return loggedInAccount;
    }

    public static String edit(String field , String changeTo){

        if (field.equalsIgnoreCase("firstName")) {
            try {
                loggedInAccount.setFirstName(changeTo);
                return "changed successfully";
            }
            catch (Exception e) {
                return e.getMessage();
            }
        }

        if(field.equalsIgnoreCase("lastName")){
            try {
                loggedInAccount.setLastName(changeTo);
                return "changed successfully";
            } catch (Exception e) {
                return e.getMessage();
            }
        }

        if (field.equalsIgnoreCase("password")){
            try {
                loggedInAccount.setPassword(changeTo);
                return "changed successfully";
            } catch (Exception e) {
                return e.getMessage();
            }
        }

        if(field.equalsIgnoreCase("email")){
            try {
                loggedInAccount.setEmail(changeTo);
                return "changed successfully";
            } catch (Exception e) {
                return e.getMessage();
            }
        }

        if(field.equalsIgnoreCase("phoneNumber")){
            try {
                loggedInAccount.setPhoneNumber(changeTo);
                return "changed successfully";
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        if (loggedInAccount instanceof SellerAccount && field.equalsIgnoreCase("company")){
            try {
                ((SellerAccount) loggedInAccount).setCompany(changeTo);
                return "changed successfully";
            } catch (Exception e){
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

    public static String register(String role,String username, String firstName, String lastName, String email, String phoneNumber, String password, int credit, String company) throws Exception {
        if (role.equalsIgnoreCase("Buyer")) {
            Database.addAllAccounts(new BuyerAccount(username, firstName, lastName, password, email, phoneNumber, credit));
            return "New buyer account registered.";
        }
        else if (role.equalsIgnoreCase("Seller")) {
            Database.addRequest(new NewSellerRequest(username, firstName, lastName, password, email, company, credit, phoneNumber));
            return "Your Request registered";
        }
        else
            return "Role is invalid!";
    }

    public static String viewPersonalInfo(){
        return loggedInAccount.showInfo();
    }

    public static String viewLogs(){
        if (loggedInAccount instanceof BuyerAccount)
            return ((BuyerAccount) loggedInAccount).showAllLog();
        else if (loggedInAccount instanceof SellerAccount)
            return ((SellerAccount) loggedInAccount).showAllLog();
        return "nothing to show.";
    }
}
