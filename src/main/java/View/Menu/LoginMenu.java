package View.Menu;

import Controller.AccountManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
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
        Button back = new Button("back");
        back.setOnAction(e -> {
            parentMenu.show();
        });
        vBox.getChildren().addAll(loginButton, registerButton, back);
        super.mainPane.setCenter(vBox);

        Menu.window.setScene(scene);
    }

    public void handleLoginChild()
    {
        super.setPane();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        Scene scene = new Scene(super.mainPane, 1000, 600);
        Label status = new Label();
        status.setFont(Font.font(20));
        TextField userName = new TextField();
        userName.setPromptText("username");
        userName.setAlignment(Pos.CENTER);
        PasswordField password = new PasswordField();
        password.setPromptText("password");
        password.setAlignment(Pos.CENTER);
        Button login = new Button("login");
        login.setAlignment(Pos.CENTER);
        login.setOnAction(e -> {
            status.setText(AccountManager.logIn(userName.getText(), password.getText()));
        });
        Button back = new Button("back");
        back.setAlignment(Pos.CENTER);
        back.setOnAction(e -> {
            UserMenu userMenu = new UserMenu(this);
            userMenu.show();
        });
        GridPane.setConstraints(userName, 0, 0);
        GridPane.setConstraints(password, 0, 1);
        GridPane.setConstraints(login, 0, 2);
        GridPane.setConstraints(back, 0, 3);
        GridPane.setConstraints(status, 0, 4);
        gridPane.getChildren().addAll(userName, password, login, back, status);
        super.mainPane.setCenter(gridPane);

        Menu.window.setScene(scene);
    }

    public void handleRegister()
    {
        super.setPane();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        Scene scene = new Scene(super.mainPane, 1000, 600);
        Label status = new Label();
        status.setFont(Font.font(20));
        ChoiceBox<String> role = new ChoiceBox<>();
        role.getItems().addAll("Buyer", "Seller");
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
        company.setPromptText("company");
        role.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if (newValue.equalsIgnoreCase("Buyer"))
            {
                company.setDisable(true);
            }
            else if (newValue.equalsIgnoreCase("Seller"))
            {
                company.setDisable(false);
            }
        });
        Button register = new Button("register");
        register.setOnAction(e -> {
            try {
                if (role.getValue().equalsIgnoreCase("Buyer"))
                {
                    status.setText(AccountManager.register(role.getValue(), userName.getText(), firstName.getText(), lastName.getText(), email.getText(),
                            phoneNumber.getText(), password.getText(), Integer.parseInt(credit.getText()), ""));
                }
                else if (role.getValue().equalsIgnoreCase("Seller"))
                {
                    status.setText(AccountManager.register(role.getValue(), userName.getText(), firstName.getText(), lastName.getText(), email.getText(),
                            phoneNumber.getText(), password.getText(), Integer.parseInt(credit.getText()), company.getText()));
                }
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
            UserMenu userMenu = new UserMenu(this);
            userMenu.show();
        });
        GridPane.setConstraints(role, 0, 0);
        GridPane.setConstraints(userName, 0, 1);
        GridPane.setConstraints(firstName, 0, 2);
        GridPane.setConstraints(lastName, 0, 3);
        GridPane.setConstraints(email, 0, 4);
        GridPane.setConstraints(phoneNumber, 0, 5);
        GridPane.setConstraints(password, 0, 6);
        GridPane.setConstraints(credit, 0, 7);
        GridPane.setConstraints(company,0, 8);
        GridPane.setConstraints(register, 0, 9);
        GridPane.setConstraints(back, 0, 10);
        GridPane.setConstraints(status, 0, 11);

        gridPane.getChildren().addAll(role, userName, firstName, lastName, email, phoneNumber, password, credit, company, register, back, status);
        super.mainPane.setCenter(gridPane);

        Menu.window.setScene(scene);
    }
}
