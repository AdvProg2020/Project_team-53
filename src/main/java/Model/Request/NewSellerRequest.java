package Model.Request;

import Controller.Database;
import Model.Account.SellerAccount;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

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
                "   requestId="+ getId()+'\n'+
                "   username=" + username + '\n' +
                "   firstName=" + firstName + '\n' +
                "   lastName=" + lastName + '\n' +
                "   password=" + password + '\n' +
                "   email=" + email + '\n' +
                "   company=" + company + '\n' +
                "   credit=" + credit + '\n' +
                "   phoneNumber=" + phoneNumber + '\n' +
                '}';
    }

    @Override
    public Pane showGraphical() {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Label reqID = new Label("RequestID : " + super.getId());
        Label userNameLabel = new Label("Username : " + username);
        Label firstNameLabel = new Label("First Name : " + firstName);
        Label lastNameLabel = new Label("Last Name : " + lastName);
        Label emailLabel = new Label("Email : " + email);
        Label phoneNumberLabel = new Label("Phone Number : " + phoneNumber);
        Label companyLabel = new Label("Company : " + company);
        Label creditLabel = new Label("Credit : " + credit);

        GridPane.setConstraints(reqID, 0 , 0);
        GridPane.setConstraints(userNameLabel, 0, 1);
        GridPane.setConstraints(firstNameLabel, 0, 2);
        GridPane.setConstraints(lastNameLabel, 0, 3);
        GridPane.setConstraints(emailLabel, 0, 4);
        GridPane.setConstraints(phoneNumberLabel, 0, 5);
        GridPane.setConstraints(companyLabel, 0, 6);
        GridPane.setConstraints(creditLabel, 0, 7);

        gridPane.getChildren().addAll(reqID, userNameLabel, firstNameLabel, lastNameLabel, emailLabel, phoneNumberLabel, companyLabel, creditLabel);

        return gridPane;
    }
}
