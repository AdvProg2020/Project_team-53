package View.Menu;

import Controller.AccountManager;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;

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
        scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
        super.mainPane.getStyleClass().add("register-background");
        Label name = new Label("Login");
        name.setFont(Font.font(40));
        name.setTextFill(Color.BLACK);
        Label status = new Label();
        status.setFont(Font.font(20));
        TextField userName = new TextField();
        userName.setPromptText("username");
        userName.setAlignment(Pos.CENTER);
        userName.getStyleClass().add("text-field");
        PasswordField password = new PasswordField();
        password.setPromptText("password");
        password.setAlignment(Pos.CENTER);
        password.getStyleClass().add("text-field");
        Button login = new Button("login");
        login.setAlignment(Pos.CENTER);
        login.setOnAction(e -> {
            status.setText(AccountManager.logIn(userName.getText(), password.getText()));
        });
        login.getStyleClass().add("dark-blue");
        Button back = new Button("back");
        back.setAlignment(Pos.CENTER);
        back.setOnAction(e -> {
            UserMenu userMenu = new UserMenu(this);
            userMenu.show();
        });
        back.getStyleClass().add("dark-blue");

        GridPane.setConstraints(name, 0, 0);
        GridPane.setConstraints(userName, 0, 1);
        GridPane.setConstraints(password, 0, 2);
        GridPane.setConstraints(login, 0, 3);
        GridPane.setConstraints(back, 0, 4);
        GridPane.setConstraints(status, 0, 5);

        GridPane.setHalignment(login, HPos.CENTER);
        GridPane.setHalignment(back, HPos.CENTER);
        GridPane.setHalignment(name, HPos.CENTER);
        gridPane.getChildren().addAll(name, userName, password, login, back, status);
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
        scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
        super.mainPane.getStyleClass().add("register-background");
        Label name = new Label("Register");
        name.setFont(Font.font(40));
        name.setTextFill(Color.BLACK);
        Label status = new Label();
        status.setFont(Font.font(20));
        ChoiceBox<String> role = new ChoiceBox<>();
        role.getItems().addAll("Buyer", "Seller");
        role.setValue("Buyer");
        TextField userName = new TextField();
        userName.setPromptText("username");
        userName.getStyleClass().add("text-field");
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
        register.getStyleClass().add("dark-blue");
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
        back.getStyleClass().add("dark-blue");
        back.setOnAction(e -> {
            UserMenu userMenu = new UserMenu(this);
            userMenu.show();
        });
        GridPane.setConstraints(name, 0, 0);
        GridPane.setConstraints(role, 0, 1);
        GridPane.setConstraints(userName, 0, 2);
        GridPane.setConstraints(firstName, 0, 3);
        GridPane.setConstraints(lastName, 0, 4);
        GridPane.setConstraints(email, 0, 5);
        GridPane.setConstraints(phoneNumber, 0, 6);
        GridPane.setConstraints(password, 0, 7);
        GridPane.setConstraints(credit, 0, 8);
        GridPane.setConstraints(company,0, 9);
        GridPane.setConstraints(register, 0, 10);
        GridPane.setConstraints(back, 0, 11);
        GridPane.setConstraints(status, 0, 12);
        GridPane.setHalignment(register, HPos.CENTER);
        GridPane.setHalignment(back, HPos.CENTER);
        GridPane.setHalignment(role, HPos.CENTER);
        GridPane.setHalignment(name, HPos.CENTER);

        gridPane.getChildren().addAll(name, role, userName, firstName, lastName, email, phoneNumber, password, credit, company, register, back, status);
        super.mainPane.setCenter(gridPane);

        Menu.window.setScene(scene);
    }
}
