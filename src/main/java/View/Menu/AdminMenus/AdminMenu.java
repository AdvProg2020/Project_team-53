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
        VBox info = new VBox();
        info.setSpacing(10);
        for (Request request : allRequest) {
            String text = "";
            HBox hBox = new HBox();
            hBox.setSpacing(50);
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
            label.setFont(Font.font(20));
            Button button = new Button("show");
            hBox.getChildren().addAll(label, button);
            info.getChildren().add(hBox);
        }
        super.mainPane.setCenter(info);

        Menu.window.setScene(scene);
    }

    public void handleManageProduct()
    {
        super.setPane();
        ArrayList<Product> allProduct = Database.getAllProducts();
        Scene scene = new Scene(super.mainPane, 1000, 600);
        VBox info = new VBox();
        info.setSpacing(10);
        for (Product product : allProduct) {
            String text = "";
            HBox hBox = new HBox();
            hBox.setSpacing(50);
            Label label = new Label();
            text = text + product.getProductId() + ":" + product.getName();
            label.setText(text);
            label.setFont(Font.font(20));
            Button button = new Button("show");
            hBox.getChildren().addAll(label, button);
            info.getChildren().add(hBox);
        }
        super.mainPane.setCenter(info);

        Menu.window.setScene(scene);
    }

    public void handleManageUsers()
    {
        super.setPane();
        ArrayList<Account> allAccount = Database.getAllAccounts();
        Scene scene = new Scene(super.mainPane, 1000, 600);
        VBox info = new VBox();
        info.setSpacing(10);
        for (Account account : allAccount) {
            String text = "";
            HBox hBox = new HBox();
            hBox.setSpacing(50);
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
            label.setFont(Font.font(20));
            Button button = new Button("show");
            hBox.getChildren().addAll(label, button);
            info.getChildren().add(hBox);
        }
        super.mainPane.setCenter(info);

        Menu.window.setScene(scene);
    }


}
