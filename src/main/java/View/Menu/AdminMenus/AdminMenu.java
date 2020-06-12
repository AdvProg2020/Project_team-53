package View.Menu.AdminMenus;

import Controller.AccountManager;
import Controller.Database;
import Model.Account.Account;
import Model.Account.AdminAccount;
import Model.Account.BuyerAccount;
import Model.Account.SellerAccount;
import Model.Product.Product;
import Model.Request.*;
import View.Menu.Menu;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class AdminMenu extends Menu {

    public AdminMenu(Menu parentMenu) {
        super("Admin Menu", parentMenu);
        //super.addToSubMenus(1, new PersonalInfoMenu(this));
        //super.addToSubMenus(2,new MangeUsersMenu(this));
        //super.addToSubMenus(3, new ManageAllProductsMenu(this));
        //super.addToSubMenus(4, getCreateDiscountCodeMenu());
        //super.addToSubMenus(5, new ViewDiscountCodeMenu(this));
        //super.addToSubMenus(6, new ManageRequestsMenu(this));
        //super.addToSubMenus(7, new ManageCategoriesMenu(this));
    }

    @Override
    public void show() {
        super.setPane();

        VBox allButtons = new VBox();
        allButtons.setSpacing(10);

        Button editInfoButton = new Button("Edit");
        editInfoButton.setOnAction(e -> handleEdit());

        Button logout = new Button("Logout");
        logout.setOnAction(e -> handleLogout());

        Button manageRequest = new Button("Manage Request");
        manageRequest.setOnAction(e -> handleManageRequest());

        Button manageProduct = new Button("Manage Product");
        manageProduct.setOnAction(e -> handleManageProduct());

        Button manageUser = new Button("Manage Users");
        manageUser.setOnAction(e -> handleManageUsers());

        Button back = new Button("Back");
        back.setOnAction(e -> parentMenu.show());

        allButtons.getChildren().addAll(editInfoButton, manageUser, manageRequest, manageProduct, logout, back);

        HBox hBox = new HBox(AccountManager.viewPersonalInfoInGraphic() , allButtons);
        hBox.setSpacing(20);

        Scene scene = new Scene(super.mainPane, super.width, super.height);
        super.mainPane.setCenter(hBox);

        window.setScene(scene);
    }

    public void handleEdit()
    {
        super.setPane();
        VBox vBox = new VBox();
        Scene scene = new Scene(super.mainPane, 1000, 600);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        Label status = new Label();
        ChoiceBox<String> field = new ChoiceBox<>();
        field.getItems().addAll("FirstName", "LastName", "password", "email", "PhoneNumber");
        field.setValue("first name");
        TextField changeTo = new TextField();
        changeTo.setPromptText("change to");
        Button edit = new Button("edit");
        edit.setOnAction(e -> {
            status.setText(AccountManager.edit(field.getValue(), changeTo.getText()));
        });
        Button back = new Button("back");
        back.setOnAction(e -> {
            show();
        });
        vBox.getChildren().addAll(field, changeTo, edit, back, status);
        super.mainPane.setCenter(vBox);

        Menu.window.setScene(scene);
    }

    public void handleLogout()
    {
        AccountManager.logOut();
        parentMenu.show();
    }

    public void handleManageRequest()
    {
        super.setPane();
        ArrayList<Request> allRequest = Database.getAllRequest();
        Scene scene = new Scene(super.mainPane, 1000, 600);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        Label info = new Label("All Requests");
        info.setFont(Font.font(25));
        GridPane.setConstraints(info, 1, 0);
        gridPane.getChildren().add(info);
        int i = 1;
        for (Request request : allRequest) {
            String text = "";
            Label label = new Label();
            text = text + request.getId() + ":";
            if(request instanceof NewSellerRequest)
            {
                text = text + "New Seller";
            }
            else if (request instanceof NewProductRequest)
            {
                text = text + "New Product";
            }
            else if (request instanceof AddNewOffRequest)
            {
                text = text + "New Off";
            }
            else if (request instanceof EditProductRequest)
            {
                text = text + "Edit Product";
            }
            else if (request instanceof EditOffRequest)
            {
                text = text + "Edit Off";
            }
            label.setText(text);
            label.setFont(Font.font(15));
            Button button = new Button("show");
            button.setAlignment(Pos.CENTER);
            GridPane.setConstraints(label, 0, i);
            GridPane.setConstraints(button, 2, i);
            gridPane.getChildren().addAll(label, button);
        }
        Button back = new Button("back");
        back.setOnAction(e -> show());
        GridPane.setConstraints(back,1, i);
        back.setAlignment(Pos.CENTER);
        gridPane.getChildren().add(back);

        super.mainPane.setCenter(gridPane);

        Menu.window.setScene(scene);
    }

    public void handleManageProduct()
    {
        super.setPane();
        ArrayList<Product> allProduct = Database.getAllProducts();
        Scene scene = new Scene(super.mainPane, 1000, 600);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        Label info = new Label("All Products");
        info.setFont(Font.font(25));
        GridPane.setConstraints(info, 1, 0);
        gridPane.getChildren().add(info);
        int i = 1;
        for (Product product : allProduct) {
            String text = "";
            Label label = new Label();
            text = text + product.getProductId() + ":" + product.getName();
            label.setText(text);
            label.setFont(Font.font(15));
            Button button = new Button("show");
            button.setAlignment(Pos.CENTER);
            GridPane.setConstraints(label, 0, i);
            GridPane.setConstraints(button, 2, i);
            gridPane.getChildren().addAll(label, button);
        }
        Button back = new Button("back");
        back.setOnAction(e -> show());
        GridPane.setConstraints(back,1, i);
        back.setAlignment(Pos.CENTER);
        gridPane.getChildren().add(back);

        super.mainPane.setCenter(gridPane);

        Menu.window.setScene(scene);
    }

    public void handleManageUsers()
    {
        super.setPane();
        ArrayList<Account> allAccount = Database.getAllAccounts();
        Scene scene = new Scene(super.mainPane, 1000, 600);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        Label info = new Label("All Users");
        info.setFont(Font.font(25));
        GridPane.setConstraints(info, 1, 0);
        gridPane.getChildren().add(info);
        int i = 1;
        for (Account account : allAccount) {
            String text = "";
            Label label = new Label();
            text = text + account.getUsername() + ":";
            if (account instanceof AdminAccount)
            {
                text = text + "Admin";
            }
            else if (account instanceof BuyerAccount)
            {
                text = text + "Buyer";
            }
            else if (account instanceof SellerAccount)
            {
                text = text + "Seller";
            }
            label.setText(text);
            label.setFont(Font.font(15));
            Button button = new Button("show");
            button.setAlignment(Pos.CENTER);
            GridPane.setConstraints(label, 0, i);
            GridPane.setConstraints(button, 2, i);
            gridPane.getChildren().addAll(label, button);
            i++;
        }
        Button back = new Button("back");
        back.setOnAction(e -> show());
        GridPane.setConstraints(back,1, i);
        back.setAlignment(Pos.CENTER);
        gridPane.getChildren().add(back);

        super.mainPane.setCenter(gridPane);

        Menu.window.setScene(scene);
    }
}
