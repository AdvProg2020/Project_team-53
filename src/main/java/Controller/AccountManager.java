package Controller;

import Model.Account.Account;
import Model.Account.BuyerAccount;
import Model.Account.SellerAccount;
import Model.Request.NewSellerRequest;

public class AccountManager {
    private  Account loggedInAccount = null ;

    public  String logIn(String username , String password)  {
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

    public  Account getLoggedInAccount() {
        return loggedInAccount;
    }

    public  String edit(String field , String changeTo, Account account){

        if (field.equalsIgnoreCase("firstName")) {
            System.out.println(changeTo);
            try {
                account.setFirstName(changeTo);
                return "changed successfully";
            }
            catch (Exception e) {
                return e.getMessage();
            }
        }

        if(field.equalsIgnoreCase("lastName")){
            try {
                account.setLastName(changeTo);
                return "changed successfully";
            } catch (Exception e) {
                return e.getMessage();
            }
        }

        if (field.equalsIgnoreCase("password")){
            try {
                account.setPassword(changeTo);
                return "changed successfully";
            } catch (Exception e) {
                return e.getMessage();
            }
        }

        if(field.equalsIgnoreCase("email")){
            try {
                account.setEmail(changeTo);
                return "changed successfully";
            } catch (Exception e) {
                return e.getMessage();
            }
        }

        if(field.equalsIgnoreCase("phoneNumber")){
            try {
                account.setPhoneNumber(changeTo);
                return "changed successfully";
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        if (account instanceof SellerAccount && field.equalsIgnoreCase("company")){
            try {
                ((SellerAccount) account).setCompany(changeTo);
                return "changed successfully";
            } catch (Exception e){
                return e.getMessage();
            }
        }
        return "Invalid field";
    }

    public  String logOut(){
        if (loggedInAccount == null)
            return "You haven't logged in";
        loggedInAccount = null ;
        return "logged out successfully";
    }

    public String register(String role,String username, String firstName, String lastName, String email, String phoneNumber, String password, int credit, String company) throws Exception {
        if (Database.getAccountByUsername(username) != null)
            return "Exist account with this username.";
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

    public String viewLogs(){
        if (loggedInAccount instanceof BuyerAccount)
            return ((BuyerAccount) loggedInAccount).showAllLog();
        else if (loggedInAccount instanceof SellerAccount)
            return ((SellerAccount) loggedInAccount).showAllLog();
        return "nothing to show.";
    }

    public String changeCredit(Account account , int much , String bankUsername , String bankPassword , String bankId){
        String res = "not available";
        if (much > 0){
            res = WorkWithBank.increaseCredit(much , bankUsername, bankPassword, bankId);
            if (res.equalsIgnoreCase("done successfully")){
                account.setCredit(account.getCredit() + much);
            }
        }
        else if (account instanceof SellerAccount){
//            doish
        }
        return res;
    }

}
