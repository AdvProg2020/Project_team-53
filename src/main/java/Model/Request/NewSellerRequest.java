package Model.Request;

import Controller.Database;
import Model.Account.SellerAccount;

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

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getCompany() {
        return company;
    }

    public int getCredit() {
        return credit;
    }

    public String getPhoneNumber() {
        return phoneNumber;
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
                "   requestId="+ getId()+'\n'+
                "   username=" + this.getUsername() + '\n' +
                "   firstName=" + this.getFirstName() + '\n' +
                "   lastName=" + this.getLastName() + '\n' +
                "   password=" + this.getPassword() + '\n' +
                "   email=" + this.getEmail() + '\n' +
                "   company=" + this.getCompany() + '\n' +
                "   credit=" + this.getCredit() + '\n' +
                "   phoneNumber=" + this.getPhoneNumber() + '\n' +
                '}';
    }

}
