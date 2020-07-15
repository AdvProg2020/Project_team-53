package Model.Account;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class Account {
    protected String username;
    protected String firstName;
    protected String lastName;
    protected String password;
    protected String email;
    protected String phoneNumber;
    protected int credit;


    public Account(String username, String firstName, String lastName, String password, String email, String phoneNumber, int credit) throws Exception {
        if (!username.matches("[A-Za-z_0-9]+")){
            throw new Exception("Invalid Username");
        }
        this.username = username;
        if (!firstName.matches("[A-Za-z]+")){
            throw new Exception("Invalid FirstName");
        }
        this.firstName = firstName;
        if (!lastName.matches("[A-Za-z]+")){
            throw new Exception("Invalid LastName");
        }
        this.lastName = lastName;
        if (password.length() < 5 || !password.matches("\\S+")){
            throw new Exception("Weak or Invalid Password");
        }
        this.password = password;
        if (!email.matches("\\S+@\\S+\\.\\S+")){
            throw new Exception("Invalid Email");
        }
        this.email = email;
        if (!phoneNumber.matches("\\d+")) {
            throw new Exception("Invalid PhoneNumber");
        }
        this.phoneNumber = phoneNumber;
        this.credit = credit;
    }

    public String getUsername() {
        return username;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getCredit() {
        return credit;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setUsername(String username) throws Exception{
        if (!username.matches("[A-Za-z_0-9]+")){
            throw new Exception("Invalid Username");
        }
        this.username = username;
    }

    public void setFirstName(String firstName) throws Exception{
        System.out.println(firstName);
        if (!firstName.matches("[A-Za-z]+")){
            throw new Exception("Invalid FirstName");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) throws Exception{
        if (!lastName.matches("[A-Za-z]+")){
            throw new Exception("Invalid LastName");
        }
        this.lastName = lastName;
    }

    public void setPassword(String password) throws Exception{
        if (password.length() < 5 || !password.matches("\\S+")){
            throw new Exception("Weak or Invalid Password");
        }
        this.password = password;
    }

    public void setEmail(String email) throws Exception {
        if (!email.matches("\\S+@\\S+\\.\\S+")){
            throw new Exception("Invalid Email");
        }
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) throws Exception {
        if (!phoneNumber.matches("\\d+"))
            throw new Exception("Invalid PhoneNumber");
        this.phoneNumber = phoneNumber;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String showInfo(){
        return "username='" + username + "'\n" +
                "firstName='" + firstName + "'\n" +
                "lastName='" + lastName + "'\n" +
                "email='" + email + "'\n" +
                "phoneNumber='" + phoneNumber + "'\n" +
                "credit='" + credit + "'"
                ;
    }

    public Pane viewPersonalInfoInGraphic() {
        Account account = this;

        GridPane gridPane = new GridPane();
        gridPane.setVgap(20);
        gridPane.setHgap(20);

        Image image = null;
        try {
            image = new Image(new FileInputStream("src\\resource\\ProfileImages\\" + account.getUsername() + ".png"));
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

        FileInputStream fileInputStream = null;
        try {
            File file = new File("Data" + File.separator + "Styles" + File.separator + "Fonts" + File.separator + "KenneyFutureNarrow.ttf");
            Label role = new Label();
            role.setFont(Font.loadFont(new FileInputStream(file), 20));
            if (account instanceof AdminAccount)
            {
                role.setText("Role : Admin");
            }
            else if (account instanceof BuyerAccount)
            {
                role.setText("Role : Buyer");
            }
            else if (account instanceof SellerAccount)
            {
                role.setText("Role : Seller");
            }
            Label username = new Label("Username : " + account.getUsername());
            username.setFont(Font.loadFont(new FileInputStream(file), 20));
            Label firstName = new Label("First Name : " + account.getFirstName());
            firstName.setFont(Font.loadFont(new FileInputStream(file), 20));
            Label lastName = new Label("Last Name : " + account.getLastName());
            lastName.setFont(Font.loadFont(new FileInputStream(file), 20));
            Label email = new Label("Email : " + account.getEmail());
            email.setFont(Font.loadFont(new FileInputStream(file), 20));
            Label phoneNumber = new Label("Phone : " + account.getPhoneNumber());
            phoneNumber.setFont(Font.loadFont(new FileInputStream(file), 20));
            Label credit = new Label("Credit : " + account.getCredit());
            credit.setFont(Font.loadFont(new FileInputStream(file), 20));

            GridPane.setConstraints(profileImage, 0, 0, 2, 6);
            GridPane.setConstraints(role, 2, 0);
            GridPane.setConstraints(username, 2, 1);
            GridPane.setConstraints(firstName, 2, 2);
            GridPane.setConstraints(lastName, 2,3);
            GridPane.setConstraints(email, 2, 4);
            GridPane.setConstraints(phoneNumber , 2, 5);
            GridPane.setConstraints(credit , 2, 6);

            gridPane.getChildren().addAll(profileImage, role, username, firstName, lastName, email, phoneNumber, credit);


            if (account instanceof SellerAccount){
                Label companyLabel = new Label("Company : " + ((SellerAccount)account).getCompany());
                companyLabel.setFont(Font.loadFont(new FileInputStream(file), 20));
                GridPane.setConstraints(companyLabel , 2 , 7);
                gridPane.getChildren().addAll(companyLabel);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return gridPane;
    }

}
