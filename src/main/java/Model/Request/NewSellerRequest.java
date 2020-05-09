package Model.Request;

import Controller.Database;
import Model.SellerAccount;

public class NewSellerRequest extends Request {

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String company;
    private int credit;
    private String phoneNumber;

    public NewSellerRequest(String username, String firstName, String lastName, String password, String email, String company, int credit, String phoneNumber) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.company = company;
        this.credit = credit;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String acceptRequest() {
        try {
            Database.addAllAccounts(new SellerAccount(username, firstName, lastName, password, email, phoneNumber, credit, company));
        } catch (Exception e){
            return e.getMessage();
        }
        return "New Seller account registered";
    }

    @Override
    public String show() {
        return "NewSellerRequest" + '{' + '\n' +
                "username='" + username + '\n' +
                "firstName='" + firstName + '\n' +
                "lastName='" + lastName + '\n' +
                "password='" + password + '\n' +
                "email='" + email + '\n' +
                "company='" + company + '\n' +
                "credit=" + credit + '\n' +
                "phoneNumber='" + phoneNumber + '\n' +
                '}';
    }

}
