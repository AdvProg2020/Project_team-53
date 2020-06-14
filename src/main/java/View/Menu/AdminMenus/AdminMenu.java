package View.Menu.AdminMenus;

import Controller.AccountManager;
import Controller.AdminManager;
import Controller.Database;
import Model.Account.Account;
import Model.Account.AdminAccount;
import Model.Account.BuyerAccount;
import Model.Account.SellerAccount;
import Model.Product.Category;
import Model.Product.DiscountAndOff.Discount;
import Model.Product.Product;
import Model.Request.*;
import View.Menu.Menu;
import View.Menu.ViewModelsWithGraphic;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

public class AdminMenu extends Menu {

    public AdminMenu(Menu parentMenu) {
        super("Admin Menu", parentMenu);
        //super.addToSubMenus(1, new PersonalInfoMenu(this));check
        //super.addToSubMenus(2,new MangeUsersMenu(this));
        //super.addToSubMenus(3, new ManageAllProductsMenu(this));check
        //super.addToSubMenus(4, getCreateDiscountCodeMenu());
        //super.addToSubMenus(5, new ViewDiscountCodeMenu(this));
        //super.addToSubMenus(6, new ManageRequestsMenu(this));check
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

        Button manageCategories = new Button("Manage Categories");
        manageCategories.setOnAction(e -> handleManageCategories());

        Button manageDiscounts = new Button("Manage Discounts");
        manageDiscounts.setOnAction(e -> handleManageDiscounts());

        Button manageAddDiscount = new Button("Add Discount");
        manageAddDiscount.setOnAction(e -> handleAddDiscount());

        Button manageAddAdmin = new Button("Add Manager");
        manageAddAdmin.setOnAction(e -> handleAddAdmin());

        Button back = new Button("Back");
        back.setOnAction(e -> parentMenu.show());

        allButtons.getChildren().addAll(editInfoButton, manageUser, manageRequest, manageProduct, manageAddDiscount, manageAddAdmin, logout, back);

        HBox hBox = new HBox(ViewModelsWithGraphic.viewPersonalInfoInGraphic(AccountManager.getLoggedInAccount().getUsername()) , allButtons);
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
        info.setAlignment(Pos.CENTER);
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
            button.setOnAction(e -> handleShowRequest(request.getId()));
            GridPane.setConstraints(label, 0, i);
            GridPane.setConstraints(button, 2, i);
            gridPane.getChildren().addAll(label, button);
            i++;
        }
        Button back = new Button("back");
        back.setAlignment(Pos.CENTER);
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
        info.setAlignment(Pos.CENTER);
        GridPane.setConstraints(info, 1, 0);
        gridPane.getChildren().add(info);
        int i = 1;
        for (Product product : allProduct) {
            String text = "";
            Label label = new Label();
            text = text + "ID(" + product.getProductId() + "):" + product.getName();
            label.setText(text);
            label.setFont(Font.font(15));
            Button button = new Button("show");
            button.setOnAction(e -> handleShowProduct(product.getProductId()));
            button.setAlignment(Pos.CENTER);
            GridPane.setConstraints(label, 0, i);
            GridPane.setConstraints(button, 2, i);
            gridPane.getChildren().addAll(label, button);
            i++;
        }
        Button back = new Button("back");
        back.setAlignment(Pos.CENTER);
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
        info.setAlignment(Pos.CENTER);
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
            button.setOnAction(e -> {
                handleShowUser(account.getUsername());
            });
            GridPane.setConstraints(label, 0, i);
            GridPane.setConstraints(button, 2, i);
            gridPane.getChildren().addAll(label, button);
            i++;
        }
        Button back = new Button("back");
        back.setAlignment(Pos.CENTER);
        back.setOnAction(e -> show());
        GridPane.setConstraints(back,1, i);
        back.setAlignment(Pos.CENTER);
        gridPane.getChildren().add(back);

        super.mainPane.setCenter(gridPane);

        Menu.window.setScene(scene);
    }

    public void handleManageCategories()
    {
        super.setPane();
        ArrayList<Category> allCategories = Database.getAllCategories();
        Scene scene = new Scene(super.mainPane, 1000, 600);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        Label info = new Label("All Categories");
        info.setFont(Font.font(25));
        info.setAlignment(Pos.CENTER);
        GridPane.setConstraints(info, 1, 0);
        gridPane.getChildren().add(info);
        int i = 1;
        for (Category category : allCategories) {
            String text = "";
            Label label = new Label();
            text = text + category.getName();
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
        back.setAlignment(Pos.CENTER);
        back.setOnAction(e -> show());
        GridPane.setConstraints(back,1, i);
        back.setAlignment(Pos.CENTER);
        gridPane.getChildren().add(back);

        super.mainPane.setCenter(gridPane);

        Menu.window.setScene(scene);
    }

    public void handleManageDiscounts()
    {
        super.setPane();
        ArrayList<Discount> allDiscounts = new ArrayList<>();
        Scene scene = new Scene(super.mainPane, 1000, 600);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        Label info = new Label("All Discounts");
        info.setFont(Font.font(25));
        info.setAlignment(Pos.CENTER);
        GridPane.setConstraints(info, 1, 0);
        gridPane.getChildren().add(info);
        int i = 1;
        for (Discount discount : allDiscounts) {
            String text = "DiscountID : ";
            Label label = new Label();
            text = text + discount.getDiscountId();
            label.setText(text);
            label.setFont(Font.font(15));
            Button button = new Button("show");
            button.setAlignment(Pos.CENTER);
            button.setOnAction(e -> handleShowDiscount(discount.getDiscountId()));
            GridPane.setConstraints(label, 0, i);
            GridPane.setConstraints(button, 2, i);
            gridPane.getChildren().addAll(label, button);
            i++;
        }
        Button back = new Button("back");
        back.setAlignment(Pos.CENTER);
        back.setOnAction(e -> show());
        GridPane.setConstraints(back,1, i);
        back.setAlignment(Pos.CENTER);
        gridPane.getChildren().add(back);

        super.mainPane.setCenter(gridPane);

        Menu.window.setScene(scene);
    }

    public void handleShowUser(String username)
    {
        Stage newWindow = new Stage();
        Pane pane = ViewModelsWithGraphic.viewPersonalInfoInGraphic(username);
        ((GridPane)pane).setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane, 600, 400);
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Admin", "Seller", "Buyer");
        Account account = Database.getAccountByUsername(username);
        if (account instanceof AdminAccount)
        {
            choiceBox.setValue("Admin");
        }
        else if (account instanceof BuyerAccount)
        {
            choiceBox.setValue("Buyer");
        }
        else if (account instanceof SellerAccount)
        {
            choiceBox.setValue("Seller");
        }
        Button changeRole = new Button("Change Role");
        changeRole.setOnAction(e -> {
            AdminManager.changeRole(username, choiceBox.getValue());
        });
        Button remove = new Button("Remove");
        remove.setOnAction(e -> {
            AdminManager.deleteUsername(username);
            handleManageUsers();
            newWindow.close();
        });
        GridPane.setConstraints(choiceBox, 4, 0);
        GridPane.setConstraints(changeRole, 4, 1);
        GridPane.setConstraints(remove, 4, 2);
        pane.getChildren().addAll(choiceBox, changeRole, remove);

        newWindow.setScene(scene);
        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.setOnCloseRequest(e -> handleManageUsers());
        newWindow.showAndWait();
    }

    public void handleShowProduct(int productID)
    {
        Stage newWindow = new Stage();
        Pane pane = ViewModelsWithGraphic.showFullInfoGraphic(productID);
        ((GridPane)pane).setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane, 600, 400);
        Button remove = new Button("Remove");
        remove.setOnAction(e -> {
            AdminManager.deleteProduct(productID);
            handleManageProduct();
            newWindow.close();
        });
        GridPane.setConstraints(remove, 4, 0);
        pane.getChildren().add(remove);

        newWindow.setScene(scene);
        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.setOnCloseRequest(e -> handleManageProduct());
        newWindow.showAndWait();

    }

    public void handleShowRequest(int id)
    {
        Stage newWindow = new Stage();
        Pane pane = AdminManager.showRequestByIdInGraphic(id);
        ((GridPane)pane).setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane, 600, 400);

        Button accept = new Button("Accept");

        accept.setOnAction(e -> {
            AdminManager.acceptOrRejectRequest(id, true);
            newWindow.close();
            handleManageRequest();
        });

        Button reject = new Button("Reject");
        reject.setOnAction(e -> {
            AdminManager.acceptOrRejectRequest(id, false);
            newWindow.close();
            handleManageRequest();
        });

        GridPane.setConstraints(accept, 3, 0);
        GridPane.setConstraints(reject, 3, 1);

        pane.getChildren().addAll(accept, reject);

        newWindow.setScene(scene);
        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.showAndWait();
    }

    public void handleShowDiscount(int id)
    {
        Stage newWindow = new Stage();
        Pane pane = ViewModelsWithGraphic.viewDiscount(id);
        ((GridPane)pane).setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane, 600, 400);
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("MaxValue", "Percent", "StartDate", "EndDate", "NumberOfTime");
        TextField changeTo = new TextField();
        changeTo.setPromptText("Change To");
        Button edit = new Button("Edit");
        edit.setOnAction(e -> {
            AdminManager.editDiscount(id, choiceBox.getValue(), changeTo.getText());
        });
        Button remove = new Button("Remove");
        remove.setOnAction(e -> {
            AdminManager.removeDiscount(id);
            handleManageDiscounts();
            newWindow.close();
        });
        GridPane.setConstraints(choiceBox, 3, 0);
        GridPane.setConstraints(changeTo, 3, 1);
        GridPane.setConstraints(edit, 3, 2);
        GridPane.setConstraints(remove, 3, 3);

        pane.getChildren().addAll(choiceBox, changeTo, edit, remove);

        newWindow.setScene(scene);
        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.showAndWait();
    }

    public void handleAddDiscount()
    {
        super.setPane();
        Scene scene = new Scene(super.mainPane, 1000, 600);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        Label status = new Label();
        TextField maxValue = new TextField();
        maxValue.setPromptText("Max Value");
        TextField percent = new TextField();
        percent.setPromptText("Percent");
        TextField startDate = new TextField();
        startDate.setPromptText("Start Date");
        TextField endDate = new TextField();
        endDate.setPromptText("End Date");
        TextField numberOfTimes = new TextField("Number Of Times");
        TextField username = new TextField();
        username.setPromptText("Username(separate with /)");
        Button add = new Button("Add");
        add.setOnAction(e -> {
            try {
                String[] usernameInArray = username.getText().split("/");
                ArrayList<String> unIAL = new ArrayList<>(Arrays.asList(usernameInArray));
                status.setText(AdminManager.addNewDiscount(Integer.parseInt(maxValue.getText()), Integer.parseInt(percent.getText()),
                        startDate.getText(), endDate.getText(), Integer.parseInt(numberOfTimes.getText()), unIAL));
            }
            catch (Exception ex)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Add Discount Fail");
                alert.setContentText(ex.getMessage());

                alert.showAndWait();
            }
        });
        Button back = new Button("Back");
        back.setOnAction(e -> show());

        GridPane.setConstraints(maxValue, 0, 0);
        GridPane.setConstraints(percent, 0, 1);
        GridPane.setConstraints(startDate, 0, 2);
        GridPane.setConstraints(endDate, 0, 3);
        GridPane.setConstraints(numberOfTimes, 0, 4);
        GridPane.setConstraints(username, 0, 5);
        GridPane.setConstraints(add, 0, 6);
        GridPane.setConstraints(back, 0, 7);
        GridPane.setConstraints(status, 0, 8);

        gridPane.getChildren().addAll(maxValue, percent, startDate, endDate, numberOfTimes, username, add, back, status);

        super.mainPane.setCenter(gridPane);

        Menu.window.setScene(scene);
    }

    public void handleAddAdmin()
    {
        super.setPane();
        Scene scene = new Scene(super.mainPane, 1000, 600);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        Label status = new Label();
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

        Button register = new Button("Register");
        register.setOnAction(e -> {
            try
            {
                status.setText(AccountManager.register("Admin", userName.getText(), firstName.getText(), lastName.getText(), email.getText(),
                        phoneNumber.getText(), password.getText(), Integer.parseInt(credit.getText()), ""));
            }
            catch (Exception ex)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Register Fail");
                alert.setContentText(ex.getMessage());

                alert.showAndWait();
            }
        });
        Button back = new Button("Back");
        back.setOnAction(e -> show());

        GridPane.setConstraints(userName, 0, 0);
        GridPane.setConstraints(firstName, 0, 1);
        GridPane.setConstraints(lastName, 0, 2);
        GridPane.setConstraints(email, 0, 3);
        GridPane.setConstraints(phoneNumber, 0, 4);
        GridPane.setConstraints(password, 0, 5);
        GridPane.setConstraints(credit, 0, 6);
        GridPane.setConstraints(register, 0, 7);
        GridPane.setConstraints(back, 0, 8);
        GridPane.setConstraints(status, 0, 9);

        gridPane.getChildren().addAll(userName, firstName, lastName, email, phoneNumber, password, credit, register, back, status);

        super.mainPane.setCenter(gridPane);

        Menu.window.setScene(scene);
    }

}
