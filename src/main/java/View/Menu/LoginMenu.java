package View.Menu;

import Controller.AccountManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class LoginMenu extends Menu{

    public LoginMenu(Menu parentMenu) {
        super("Login Menu", parentMenu);
    }

    @Override
    public void show() {
        super.setPane();
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        Scene scene = new Scene(super.mainPane, 1000, 600);
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            handleLoginChild();
        });
        Button registerButton = new Button("Register");
        registerButton.setOnAction(e -> {
            handleRegister();
        });
        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> {
            handleLogout();
        });
        Button back = new Button("back");
        back.setOnAction(e -> {
            parentMenu.show();
        });
        vBox.getChildren().addAll(loginButton, registerButton, logoutButton, back);
        super.mainPane.setCenter(vBox);

        Menu.window.setScene(scene);
    }

    public void handleLoginChild()
    {
        super.setPane();
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        Scene scene = new Scene(super.mainPane, 1000, 600);
        Label status = new Label();
        status.setFont(Font.font(20));
        TextField userName = new TextField();
        userName.setPromptText("username");
        PasswordField password = new PasswordField();
        password.setPromptText("password");
        Button login = new Button("login");
        login.setOnAction(e -> {
            status.setText(AccountManager.logIn(userName.getText(), password.getText()));
        });
        Button back = new Button("back");
        back.setOnAction(e -> {
            show();
        });
        vBox.getChildren().addAll(userName, password, login, back, status);
        super.mainPane.setCenter(vBox);

        Menu.window.setScene(scene);
    }

    public void handleRegister()
    {
        super.setPane();
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        Scene scene = new Scene(super.mainPane, 1000, 600);
        Label status = new Label();
        status.setFont(Font.font(20));
        TextField role = new TextField();
        role.setPromptText("role");
        TextField userName = new TextField();
        userName.setPromptText("username");
        TextField firstName = new TextField();
        firstName.setPromptText("first name");
        TextField lastName = new TextField();
        lastName.setPromptText("last name");
        TextField email = new TextField();
        email.setPromptText("email");
        TextField phoneNumber = new TextField();
        phoneNumber.setPromptText("phone number");
        TextField password = new TextField();
        password.setPromptText("password");
        TextField credit = new TextField();
        credit.setPromptText("credit");
        TextField company = new TextField();
        company.setPromptText("company(only for seller)");
        Button register = new Button("register");
        register.setOnAction(e -> {
            try {
                status.setText(AccountManager.register(role.getText(), userName.getText(), firstName.getText(), lastName.getText(), email.getText(),
                        phoneNumber.getText(), password.getText(), Integer.parseInt(credit.getText()), company.getText()));
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Register Fail");
                alert.setContentText(ex.getMessage());

                alert.showAndWait();
            }
        });
        Button back = new Button("back");
        back.setOnAction(e -> {
            show();
        });
        vBox.getChildren().addAll(role, userName, firstName, lastName, email, phoneNumber, password, credit, company, register, back, status);
        super.mainPane.setCenter(vBox);

        Menu.window.setScene(scene);
    }

    public void handleLogout()
    {
        AccountManager.logOut();
    }
}
