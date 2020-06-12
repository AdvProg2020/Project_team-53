package Controller;

import Model.Account.Account;
import Model.Account.BuyerAccount;
import Model.Account.SellerAccount;
import Model.Request.NewSellerRequest;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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

    public static String viewPersonalInfo(){
        return loggedInAccount.showInfo();
    }

    public static Pane viewPersonalInfoInGraphic() {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Image image = null;
        try {
            image = new Image(new FileInputStream("src\\resource\\ProfileImages\\" + loggedInAccount.getUsername() + ".png"));
        }catch (Exception e){
            try {
                image = new Image(new FileInputStream("src\\resource\\ProfileImages\\notFound.png"));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        ImageView profileImage = new ImageView(image);
        profileImage.setFitHeight(100);
        profileImage.setFitWidth(100);

        Label username = new Label("Username : " + loggedInAccount.getUsername());
        Label firstName = new Label("First Name : " + loggedInAccount.getFirstName());
        Label lastName = new Label("Last Name : " + loggedInAccount.getLastName());
        Label email = new Label("Email : " + loggedInAccount.getEmail());
        Label phoneNumber = new Label("Phone : " + loggedInAccount.getPhoneNumber());
        Label credit = new Label("Credit : " + loggedInAccount.getCredit());

        GridPane.setConstraints(profileImage, 0, 0, 2, 6);
        GridPane.setConstraints(username, 2, 0);
        GridPane.setConstraints(firstName, 2, 1);
        GridPane.setConstraints(lastName, 2,2);
        GridPane.setConstraints(email, 2, 3);
        GridPane.setConstraints(phoneNumber , 2, 4);
        GridPane.setConstraints(credit , 2, 5);

        gridPane.getChildren().addAll(profileImage, username, firstName, lastName, email, phoneNumber, credit);

        return gridPane;
    }

    public static String viewLogs(){
        if (loggedInAccount instanceof BuyerAccount)
            return ((BuyerAccount) loggedInAccount).showAllLog();
        else if (loggedInAccount instanceof SellerAccount)
            return ((SellerAccount) loggedInAccount).showAllLog();
        return "nothing to show.";
    }

    public static String viewLogsWithId(int logId){
        if (loggedInAccount instanceof BuyerAccount)
            return ((BuyerAccount) loggedInAccount).showLogById(logId);
        else if (loggedInAccount instanceof SellerAccount)
            return ((SellerAccount) loggedInAccount).showLogById(logId);
        return "nothing to show.";
    }

    public static String viewCredit(){
        return String.valueOf(loggedInAccount.getCredit());
    }
}
