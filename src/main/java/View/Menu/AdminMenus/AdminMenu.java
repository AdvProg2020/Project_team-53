package View.Menu.AdminMenus;

import Model.Account.Account;
import Model.Account.AdminAccount;
import Model.Account.BuyerAccount;
import Model.Account.SellerAccount;
import Model.Log.BuyLog;
import Model.Product.Category;
import Model.Product.DiscountAndOff.Discount;
import Model.Product.Product;
import Model.Request.*;
import View.Menu.Menu;
import View.Menu.UserMenu;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

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
        Scene scene = new Scene(super.mainPane, super.width, super.height);
        scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
        super.mainPane.getStyleClass().add("admin-background");

        GridPane allButtons = new GridPane();
        allButtons.setVgap(10);

        Button editInfoButton = new Button("Edit");
        editInfoButton.setOnAction(e -> handleEdit());
        editInfoButton.getStyleClass().add("dark-blue");
        editInfoButton.setMaxWidth(Double.MAX_VALUE);

        Button logout = new Button("Logout");
        logout.setOnAction(e -> handleLogout());
        logout.getStyleClass().add("dark-blue");
        logout.setMaxWidth(Double.MAX_VALUE);

        Button manageRequest = new Button("Manage Request");
        manageRequest.setOnAction(e -> handleManageRequest());
        manageRequest.getStyleClass().add("dark-blue");
        manageRequest.setMaxWidth(Double.MAX_VALUE);

        Button manageProduct = new Button("Manage Product");
        manageProduct.setOnAction(e -> handleManageProduct());
        manageProduct.getStyleClass().add("dark-blue");
        manageProduct.setMaxWidth(Double.MAX_VALUE);

        Button manageUser = new Button("Manage Users");
        manageUser.setOnAction(e -> handleManageUsers());
        manageUser.getStyleClass().add("dark-blue");
        manageUser.setMaxWidth(Double.MAX_VALUE);

        Button onlineUser = new Button("Online Users");
        onlineUser.setOnAction(e -> handleOnlineUsers());
        onlineUser.getStyleClass().add("dark-blue");
        onlineUser.setMaxWidth(Double.MAX_VALUE);

        Button manageCategories = new Button("Manage Categories");
        manageCategories.setOnAction(e -> handleManageCategories());
        manageCategories.getStyleClass().add("dark-blue");
        manageCategories.setMaxWidth(Double.MAX_VALUE);

        Button manageDiscounts = new Button("Manage Discounts");
        manageDiscounts.setOnAction(e -> handleManageDiscounts());
        manageDiscounts.getStyleClass().add("dark-blue");
        manageDiscounts.setMaxWidth(Double.MAX_VALUE);

        Button manageAddAdmin = new Button("Add Manager");
        manageAddAdmin.setOnAction(e -> handleAddAdmin());
        manageAddAdmin.getStyleClass().add("dark-blue");
        manageAddAdmin.setMaxWidth(Double.MAX_VALUE);


        GridPane.setConstraints(editInfoButton, 0, 0);
        GridPane.setConstraints(manageUser, 0, 1);
        GridPane.setConstraints(onlineUser, 0, 2);
        GridPane.setConstraints(manageRequest, 0, 3);
        GridPane.setConstraints(manageProduct, 0, 4);
        GridPane.setConstraints(manageCategories, 0, 5);
        GridPane.setConstraints(manageDiscounts, 0, 6);
        GridPane.setConstraints(manageAddAdmin, 0, 7);
        GridPane.setConstraints(logout, 0, 8);
        GridPane.setHalignment(editInfoButton, HPos.CENTER);
        GridPane.setHalignment(manageUser, HPos.CENTER);
        GridPane.setHalignment(onlineUser, HPos.CENTER);
        GridPane.setHalignment(manageRequest, HPos.CENTER);
        GridPane.setHalignment(manageProduct, HPos.CENTER);
        GridPane.setHalignment(manageCategories, HPos.CENTER);
        GridPane.setHalignment(manageDiscounts, HPos.CENTER);
        GridPane.setHalignment(manageAddAdmin, HPos.CENTER);
        GridPane.setHalignment(logout, HPos.CENTER);

        allButtons.getChildren().addAll(editInfoButton, manageUser, manageRequest, manageProduct, manageCategories, manageDiscounts, manageAddAdmin, logout, onlineUser);

        try {
            dataOutputStream.writeUTF("GetLoggedAccount");
            dataOutputStream.flush();
            String data = dataInputStream.readUTF();
            String role = data.split("_")[0];
            Type type;
            if (role.equalsIgnoreCase("Admin")){
                type = new TypeToken<AdminAccount>(){}.getType();
                Menu.account = new Gson().fromJson(data.split("_")[1], type);
            }
            else if (role.equalsIgnoreCase("Seller"))
            {
                type = new TypeToken<SellerAccount>(){}.getType();
                Menu.account = new Gson().fromJson(data.split("_")[1], type);
            }
            else if (role.equalsIgnoreCase("Buyer"))
            {
                type = new TypeToken<BuyerAccount>(){}.getType();
                Menu.account = new Gson().fromJson(data.split("_")[1], type);
            }
            else {
                Menu.account = null;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Pane pane = Objects.requireNonNull(account).viewPersonalInfoInGraphic();
        GridPane.setConstraints(pane, 0 , 0);
        GridPane.setConstraints(allButtons, 3 ,0);
        GridPane gridPane = new GridPane();
        gridPane.getChildren().addAll(pane, allButtons);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(25);

        super.mainPane.setCenter(gridPane);

        window.setScene(scene);
    }

    public void handleEdit()
    {
        super.setPane();
        GridPane gridPane = new GridPane();
        Scene scene = new Scene(super.mainPane, 1000, 600);
        scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
        super.mainPane.getStyleClass().add("admin-page");
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        Label status = new Label();
        ChoiceBox<String> field = new ChoiceBox<>();
        field.getItems().addAll("FirstName", "LastName", "password", "email", "PhoneNumber");
        field.setValue("FirstName");
        field.getStyleClass().add("choice-box");
        TextField changeTo = new TextField();
        changeTo.setPromptText("change to");
        changeTo.getStyleClass().add("textfield.css");
        Button edit = new Button("edit");
        edit.getStyleClass().add("dark-blue");
        edit.setMaxWidth(Double.MAX_VALUE);
        edit.setOnAction(e -> {
            try {
                dataOutputStream.writeUTF("edit " + field.getValue() + " " + changeTo.getText());
                dataOutputStream.flush();
                status.setText(dataInputStream.readUTF());
            }
            catch (Exception ex)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Process Fail");
                alert.setContentText("Wrong input");

                alert.showAndWait();
            }
        });
        Button back = new Button("back");
        back.getStyleClass().add("dark-blue");
        back.setMaxWidth(Double.MAX_VALUE);
        back.setOnAction(e -> show());
        GridPane.setConstraints(field, 0, 0 );
        GridPane.setConstraints(changeTo, 0, 1);
        GridPane.setConstraints(edit, 0, 2);
        GridPane.setConstraints(back, 0, 3);
        GridPane.setConstraints(status, 0, 4);
        GridPane.setHalignment(edit, HPos.CENTER);
        GridPane.setHalignment(back, HPos.CENTER);
        GridPane.setHalignment(field, HPos.CENTER);
        GridPane.setHalignment(status, HPos.CENTER);
        gridPane.getChildren().addAll(field, changeTo, edit, back, status);
        super.mainPane.setCenter(gridPane);

        Menu.window.setScene(scene);
    }

    public void handleLogout() {
        try {
            dataOutputStream.writeUTF("logout");
            dataOutputStream.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Menu.account = null;
        UserMenu userMenu = new UserMenu(this);
        userMenu.show();
    }

    public void handleManageRequest()
    {
        try {
            super.setPane();
            dataOutputStream.writeUTF("GetAllAddNewOffRequest");
            dataOutputStream.flush();
            ArrayList<AddNewOffRequest> addNewOffRequests = new Gson().fromJson(dataInputStream.readUTF(), new TypeToken<ArrayList<AddNewOffRequest>>(){}.getType());
            dataOutputStream.writeUTF("GetAllDeleteProductRequest");
            dataOutputStream.flush();
            ArrayList<DeleteProduct> deleteProducts = new Gson().fromJson(dataInputStream.readUTF(), new TypeToken<ArrayList<DeleteProduct>>(){}.getType());
            dataOutputStream.writeUTF("GetAllEditOffRequest");
            dataOutputStream.flush();
            ArrayList<EditOffRequest> editOffRequests = new Gson().fromJson(dataInputStream.readUTF(), new TypeToken<ArrayList<EditOffRequest>>(){}.getType());
            dataOutputStream.writeUTF("GetAllEditProductRequest");
            dataOutputStream.flush();
            ArrayList<EditProductRequest> editProductRequests = new Gson().fromJson(dataInputStream.readUTF(), new TypeToken<ArrayList<EditProductRequest>>(){}.getType());
            dataOutputStream.writeUTF("GetAllNewProductRequest");
            dataOutputStream.flush();
            ArrayList<NewProductRequest> newProductRequests = new Gson().fromJson(dataInputStream.readUTF(), new TypeToken<ArrayList<NewProductRequest>>(){}.getType());
            dataOutputStream.writeUTF("GetAllNewSellerRequest");
            dataOutputStream.flush();
            ArrayList<NewSellerRequest> newSellerRequests = new Gson().fromJson(dataInputStream.readUTF(), new TypeToken<ArrayList<NewSellerRequest>>(){}.getType());
            ArrayList<Request> allRequest = new ArrayList<>(addNewOffRequests);
            allRequest.addAll(deleteProducts);
            allRequest.addAll(editOffRequests);
            allRequest.addAll(editProductRequests);
            allRequest.addAll(newProductRequests);
            allRequest.addAll(newSellerRequests);
            Scene scene = new Scene(super.mainPane, 1000, 600);
            scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
            super.mainPane.getStyleClass().add("admin-page");
            GridPane gridPane = new GridPane();
            gridPane.setHgap(20);
            gridPane.setVgap(10);
            gridPane.setAlignment(Pos.CENTER);
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            scrollPane.setFitToHeight(true);
            scrollPane.setFitToWidth(true);
            scrollPane.getStyleClass().add("scroll-pane");
            Label info = new Label("All Requests");
            info.setFont(Font.font(25));
            GridPane.setHalignment(info, HPos.CENTER);
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
                else if (request instanceof DeleteProduct){
                    text = text + "Delete Product";
                }
                label.setText(text);
                label.setFont(Font.font(15));
                Button button = new Button("show");
                button.setAlignment(Pos.CENTER);
                button.setMaxWidth(Double.MAX_VALUE);
                button.getStyleClass().add("dark-blue");
                button.setOnAction(e -> handleShowRequest(request));
                GridPane.setConstraints(label, 0, i);
                GridPane.setConstraints(button, 2, i);
                gridPane.getChildren().addAll(label, button);
                i++;
            }
            Button back = new Button("back");
            back.setAlignment(Pos.CENTER);
            back.setOnAction(e -> show());
            back.setMaxWidth(Double.MAX_VALUE);
            back.getStyleClass().add("dark-blue");
            GridPane.setConstraints(back,1, i);
            GridPane.setHalignment(back, HPos.CENTER);
            back.setAlignment(Pos.CENTER);
            gridPane.getChildren().add(back);

            scrollPane.setContent(gridPane);
            super.mainPane.setCenter(scrollPane);

            Menu.window.setScene(scene);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void handleManageProduct()
    {
        try {
            super.setPane();
            ArrayList<Product> allProduct;
            dataOutputStream.writeUTF("AllProducts");
            dataOutputStream.flush();
            Gson gson = new Gson();
            allProduct = gson.fromJson(dataInputStream.readUTF(), new TypeToken<ArrayList<Product>>(){}.getType());
            Scene scene = new Scene(super.mainPane, 1000, 600);
            scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
            super.mainPane.getStyleClass().add("admin-page");
            GridPane gridPane = new GridPane();
            gridPane.setHgap(20);
            gridPane.setVgap(10);
            gridPane.setAlignment(Pos.CENTER);
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            scrollPane.setFitToHeight(true);
            scrollPane.setFitToWidth(true);
            scrollPane.getStyleClass().add("scroll-pane");
            Label info = new Label("All Products");
            info.setFont(Font.font(25));
            GridPane.setHalignment(info, HPos.CENTER);
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
                button.setOnAction(e -> handleShowProduct(product));
                button.setAlignment(Pos.CENTER);
                button.getStyleClass().add("dark-blue");
                button.setMaxWidth(Double.MAX_VALUE);
                GridPane.setConstraints(label, 0, i);
                GridPane.setConstraints(button, 2, i);
                gridPane.getChildren().addAll(label, button);
                i++;
            }
            Button back = new Button("back");
            back.setAlignment(Pos.CENTER);
            back.getStyleClass().add("dark-blue");
            back.setMaxWidth(Double.MAX_VALUE);
            back.setOnAction(e -> show());
            GridPane.setConstraints(back,1, i);
            GridPane.setHalignment(back, HPos.CENTER);
            back.setAlignment(Pos.CENTER);
            gridPane.getChildren().add(back);

            scrollPane.setContent(gridPane);
            super.mainPane.setCenter(scrollPane);

            Menu.window.setScene(scene);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void handleManageUsers()
    {
        try {
            super.setPane();
            ArrayList<BuyerAccount> allBuyerAccount;
            ArrayList<SellerAccount> allSellerAccount;
            ArrayList<AdminAccount> allAdminAccount;
            dataOutputStream.writeUTF("GetAllBuyerAccounts");
            dataOutputStream.flush();
            Gson gson = new Gson();
            allBuyerAccount = gson.fromJson(dataInputStream.readUTF(), new TypeToken<ArrayList<BuyerAccount>>(){}.getType());
            dataOutputStream.writeUTF("GetAllSellerAccounts");
            dataOutputStream.flush();
            allSellerAccount = new Gson().fromJson(dataInputStream.readUTF(), new TypeToken<ArrayList<SellerAccount>>(){}.getType());
            dataOutputStream.writeUTF("GetAllAdminAccounts");
            dataOutputStream.flush();
            allAdminAccount = new Gson().fromJson(dataInputStream.readUTF(), new TypeToken<ArrayList<AdminAccount>>(){}.getType());
            ArrayList<Account> allAccount = new ArrayList<>(allAdminAccount);
            allAccount.addAll(allSellerAccount);
            allAccount.addAll(allBuyerAccount);
            Scene scene = new Scene(super.mainPane, 1000, 600);
            scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
            super.mainPane.getStyleClass().add("admin-page");
            GridPane gridPane = new GridPane();
            gridPane.setHgap(20);
            gridPane.setVgap(10);
            gridPane.setAlignment(Pos.CENTER);
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            scrollPane.setFitToHeight(true);
            scrollPane.setFitToWidth(true);
            scrollPane.getStyleClass().add("scroll-pane");
            Label info = new Label("All Users");
            info.setFont(Font.font(25));
            GridPane.setHalignment(info, HPos.CENTER);
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
                button.setMaxWidth(Double.MAX_VALUE);
                button.getStyleClass().add("dark-blue");
                button.setOnAction(e -> {
                    handleShowUser(account);
                });
                GridPane.setConstraints(label, 0, i);
                GridPane.setConstraints(button, 2, i);
                gridPane.getChildren().addAll(label, button);
                i++;
            }
            Button back = new Button("back");
            back.setAlignment(Pos.CENTER);
            back.getStyleClass().add("dark-blue");
            back.setMaxWidth(Double.MAX_VALUE);
            GridPane.setHalignment(back, HPos.CENTER);
            back.setOnAction(e -> show());
            GridPane.setConstraints(back,1, i);
            back.setAlignment(Pos.CENTER);
            gridPane.getChildren().add(back);

            scrollPane.setContent(gridPane);
            super.mainPane.setCenter(scrollPane);

            Menu.window.setScene(scene);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void handleOnlineUsers()
    {
        try {
            super.setPane();
            dataOutputStream.writeUTF("GetOnlineAdmins");
            dataOutputStream.flush();
            ArrayList<Account> allOnlineAccounts = new ArrayList<>(new Gson().fromJson(dataInputStream.readUTF(), new TypeToken<ArrayList<AdminAccount>>() {}.getType()));
            dataOutputStream.writeUTF("GetOnlineBuyers");
            dataOutputStream.flush();
            allOnlineAccounts.addAll(new Gson().fromJson(dataInputStream.readUTF(), new TypeToken<ArrayList<BuyerAccount>>(){}.getType()));
            dataOutputStream.writeUTF("GetOnlineSellers");
            dataOutputStream.flush();
            allOnlineAccounts.addAll(new Gson().fromJson(dataInputStream.readUTF(), new TypeToken<ArrayList<SellerAccount>>(){}.getType()));
            Scene scene = new Scene(super.mainPane, 1000, 600);
            scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
            super.mainPane.getStyleClass().add("admin-page");
            GridPane gridPane = new GridPane();
            gridPane.setHgap(20);
            gridPane.setVgap(10);
            gridPane.setAlignment(Pos.CENTER);
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            scrollPane.setFitToHeight(true);
            scrollPane.setFitToWidth(true);
            scrollPane.getStyleClass().add("scroll-pane");
            Label info = new Label("All Users");
            info.setFont(Font.font(25));
            GridPane.setHalignment(info, HPos.CENTER);
            info.setAlignment(Pos.CENTER);
            GridPane.setConstraints(info, 1, 0);
            gridPane.getChildren().add(info);
            int i = 1;
            for (Account account : allOnlineAccounts) {
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
                button.setMaxWidth(Double.MAX_VALUE);
                button.getStyleClass().add("dark-blue");
                button.setOnAction(e -> {
                    handleShowUser(account);
                });
                GridPane.setConstraints(label, 0, i);
                GridPane.setConstraints(button, 2, i);
                gridPane.getChildren().addAll(label, button);
                i++;
            }
            Button back = new Button("back");
            back.setAlignment(Pos.CENTER);
            back.getStyleClass().add("dark-blue");
            back.setMaxWidth(Double.MAX_VALUE);
            GridPane.setHalignment(back, HPos.CENTER);
            back.setOnAction(e -> show());
            GridPane.setConstraints(back,1, i);
            back.setAlignment(Pos.CENTER);
            gridPane.getChildren().add(back);

            scrollPane.setContent(gridPane);
            super.mainPane.setCenter(scrollPane);

            Menu.window.setScene(scene);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void handleManageCategories()
    {
        try {
            super.setPane();
            ArrayList<Category> allCategories;
            dataOutputStream.writeUTF("AllCategories");
            dataOutputStream.flush();
            Gson gson = new Gson();
            allCategories = gson.fromJson(dataInputStream.readUTF(), new TypeToken<ArrayList<Category>>(){}.getType());
            Scene scene = new Scene(super.mainPane, 1000, 600);
            scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
            super.mainPane.getStyleClass().add("admin-page");
            GridPane gridPane = new GridPane();
            gridPane.setHgap(20);
            gridPane.setVgap(10);
            gridPane.setAlignment(Pos.CENTER);
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            scrollPane.setFitToHeight(true);
            scrollPane.setFitToWidth(true);
            scrollPane.getStyleClass().add("scroll-pane");
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
                button.setMaxWidth(Double.MAX_VALUE);
                button.getStyleClass().add("dark-blue");
                button.setOnAction(e -> {
                    Stage newWindow = new Stage();
                    newWindow.initModality(Modality.APPLICATION_MODAL);

                    handleShowCategory(category , newWindow);
                    newWindow.showAndWait();
                });
                button.setAlignment(Pos.CENTER);
                GridPane.setConstraints(label, 0, i);
                GridPane.setConstraints(button, 2, i);
                gridPane.getChildren().addAll(label, button);
                i++;
            }
            Button back = new Button("back");
            back.setAlignment(Pos.CENTER);
            back.getStyleClass().add("dark-blue");
            back.setMaxWidth(Double.MAX_VALUE);
            back.setOnAction(e -> show());

            Button addNewCategory = new Button("Add Category");
            addNewCategory.setMaxWidth(Double.MAX_VALUE);
            addNewCategory.getStyleClass().add("dark-blue");
            addNewCategory.setAlignment(Pos.CENTER);
            addNewCategory.setOnAction(e -> handleAddNewCategory());

            GridPane.setConstraints(addNewCategory, 1, i);
            GridPane.setConstraints(back,1, i+1);

            back.setAlignment(Pos.CENTER);
            gridPane.getChildren().addAll(back, addNewCategory);

            scrollPane.setContent(gridPane);
            super.mainPane.setCenter(scrollPane);

            Menu.window.setScene(scene);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void handleAddNewCategory() {

        super.setPane();
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(super.mainPane, 1000, 600);
        scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
        super.mainPane.getStyleClass().add("admin-page");

        Label status = new Label();
        status.setFont(Font.font(20));

        TextField name = new TextField();
        name.setPromptText("Name");
        name.getStyleClass().add("text-field");

        TextField feature = new TextField();
        feature.setPromptText("Feature");

        TextField parentName = new TextField();
        parentName.setPromptText("Parent");


        Button addButton = new Button("Add");
        addButton.getStyleClass().add("dark-blue");
        addButton.setMaxWidth(Double.MAX_VALUE);
        addButton.setOnAction(e -> {
            try {
                dataOutputStream.writeUTF("AddCategory " + name.getText() + " " + feature.getText() + " " + parentName.getText());
                dataOutputStream.flush();
                status.setText(dataInputStream.readUTF());
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        });

        Button back = new Button("back");
        back.getStyleClass().add("dark-blue");
        back.setMaxWidth(Double.MAX_VALUE);
        back.setOnAction(e -> {
            handleManageCategories();
        });

        GridPane.setConstraints(name, 0, 0);
        GridPane.setConstraints(feature, 0, 1);
        GridPane.setConstraints(parentName, 0, 2);
        GridPane.setConstraints(addButton, 0, 3);
        GridPane.setConstraints(back, 0, 4);
        GridPane.setConstraints(status, 0, 5);
        GridPane.setHalignment(addButton, HPos.CENTER);
        GridPane.setHalignment(back, HPos.CENTER);
        GridPane.setHalignment(status, HPos.CENTER);

        gridPane.getChildren().addAll(name, feature, parentName, addButton, back, status);
        super.mainPane.setCenter(gridPane);

        Menu.window.setScene(scene);
    }

    private void handleShowCategory(Category category , Stage newWindow){
        Pane pane = category.showCategoryGraphic();
        ((GridPane)pane).setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane, 600, 400);
        scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
        pane.getStyleClass().add("admin-popup");

        Button remove = new Button("Remove");
        remove.setAlignment(Pos.CENTER);
        remove.setMaxWidth(Double.MAX_VALUE);
        remove.getStyleClass().add("record-sales");
        remove.setOnAction(e -> {
            try {
                dataOutputStream.writeUTF("RemoveCategory " + category.getName());
                dataOutputStream.flush();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            handleManageCategories();
            newWindow.close();
        });

        Button edit = new Button("Edit");
        edit.setAlignment(Pos.CENTER);
        edit.setMaxWidth(Double.MAX_VALUE);
        edit.getStyleClass().add("dark-blue");
        edit.setOnAction(e -> handleEditCategory(category, newWindow));

        GridPane.setConstraints(remove, 4, 1);
        GridPane.setConstraints(edit, 4, 2);

        pane.getChildren().addAll(remove , edit);

        newWindow.setScene(scene);
        newWindow.setOnCloseRequest(e -> handleManageCategories());

    }

    private void handleEditCategory(Category category, Stage newWindow) {
        super.setPane();
        VBox vBox = new VBox();
        Scene scene = new Scene(vBox, 600, 400);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        Label status = new Label();
        ChoiceBox<String> field = new ChoiceBox<>();
        field.getItems().addAll( "feature", "parentName");

        TextField changeTo = new TextField();
        changeTo.setPromptText("change to");

        Button edit = new Button("edit");
        edit.setOnAction(e -> {
            try {
                dataOutputStream.writeUTF("EditCategory " + category.getName() + " " + field.getValue() + " " + changeTo.getText());
                dataOutputStream.flush();
                status.setText(dataInputStream.readUTF());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
        Button back = new Button("back");
        back.setOnAction(e -> handleShowCategory(category, newWindow));
        vBox.getChildren().addAll(field, changeTo, edit, back, status);

        newWindow.setScene(scene);
    }

    public void handleManageDiscounts()
    {
        try {
            super.setPane();
            ArrayList<Discount> allDiscounts;
            dataOutputStream.writeUTF("AllDiscounts");
            dataOutputStream.flush();
            Gson gson = new Gson();
            allDiscounts = gson.fromJson(dataInputStream.readUTF(), new TypeToken<ArrayList<Discount>>(){}.getType());
            Scene scene = new Scene(super.mainPane, 1000, 600);
            scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
            super.mainPane.getStyleClass().add("admin-page");
            GridPane gridPane = new GridPane();
            gridPane.setHgap(20);
            gridPane.setVgap(10);
            gridPane.setAlignment(Pos.CENTER);
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            scrollPane.setFitToHeight(true);
            scrollPane.setFitToWidth(true);
            scrollPane.getStyleClass().add("scroll-pane");
            Label info = new Label("All Discounts");
            info.setFont(Font.font(25));
            GridPane.setHalignment(info, HPos.CENTER);
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
                button.getStyleClass().add("dark-blue");
                button.setOnAction(e -> {
                    Stage newWindow = new Stage();
                    handleShowDiscount(discount,newWindow);
                    newWindow.initModality(Modality.APPLICATION_MODAL);
                    newWindow.showAndWait();
                });
                GridPane.setConstraints(label, 0, i);
                GridPane.setConstraints(button, 2, i);
                gridPane.getChildren().addAll(label, button);
                i++;
            }
            Button back = new Button("back");
            back.setAlignment(Pos.CENTER);
            back.setMaxWidth(Double.MAX_VALUE);
            back.getStyleClass().add("dark-blue");
            GridPane.setHalignment(back, HPos.CENTER);
            back.setOnAction(e -> show());

            Button addNewDiscount = new Button("Add New Discount");
            addNewDiscount.getStyleClass().add("dark-blue");
            addNewDiscount.setMaxWidth(Double.MAX_VALUE);
            addNewDiscount.setOnAction(e -> handleAddDiscount());

            GridPane.setConstraints(addNewDiscount, 1, i);
            GridPane.setConstraints(back,1, i+1);

            back.setAlignment(Pos.CENTER);
            gridPane.getChildren().addAll(back, addNewDiscount);

            scrollPane.setContent(gridPane);
            super.mainPane.setCenter(scrollPane);

            Menu.window.setScene(scene);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void handleShowUser(Account account)
    {
        try {
            Stage newWindow = new Stage();
            Pane pane = account.viewPersonalInfoInGraphic();
            ((GridPane)pane).setAlignment(Pos.CENTER);
            Scene scene = new Scene(pane, 600, 400);
            scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
            pane.getStyleClass().add("admin-popup");
            ChoiceBox<String> choiceBox = new ChoiceBox<>();
            choiceBox.getItems().addAll("Admin", "Seller", "Buyer");
            choiceBox.getStyleClass().add("choice-box");
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
            changeRole.setMaxWidth(Double.MAX_VALUE);
            changeRole.getStyleClass().add("dark-blue");
            changeRole.setOnAction(e -> {
                try {
                    dataOutputStream.writeUTF("ChangeRole " + account.getUsername() + " " + choiceBox.getValue());
                    dataOutputStream.flush();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            });
            Button remove = new Button("Remove");
            remove.setMaxWidth(Double.MAX_VALUE);
            remove.getStyleClass().add("record-sales");
            remove.setOnAction(e -> {
                try {
                    dataOutputStream.writeUTF("RemoveAccount " + account.getUsername());
                    dataOutputStream.flush();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
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
        catch (Exception e)
        {
            System.out.println(e.getMessage());;
        }
    }

    public void handleShowProduct(Product product)
    {
        try {
            Stage newWindow = new Stage();
            Pane pane = product.showProductFullInfoGraphic();
            ((GridPane)pane).setAlignment(Pos.CENTER);
            Scene scene = new Scene(pane, 600, 400);
            scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
            pane.getStyleClass().add("admin-popup");
            Button remove = new Button("Remove");
            remove.setMaxWidth(Double.MAX_VALUE);
            remove.getStyleClass().add("record-sales");
            remove.setOnAction(e -> {
                try {
                    dataOutputStream.writeUTF("RemoveProduct " + product.getProductId());
                    dataOutputStream.flush();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
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
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void handleShowRequest(Request request)
    {
        try {
            Stage newWindow = new Stage();
            Pane pane = request.showRequestGraphic();
            ((GridPane)pane).setAlignment(Pos.CENTER);
            Scene scene = new Scene(pane, 600, 400);
            scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
            pane.getStyleClass().add("admin-popup");

            Button accept = new Button("Accept");
            accept.setMaxWidth(Double.MAX_VALUE);
            accept.getStyleClass().add("dark-blue");

            accept.setOnAction(e -> {
                try {
                    dataOutputStream.writeUTF("ARRequest " + request.getId() + " " + "true");
                    dataOutputStream.flush();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                newWindow.close();
                handleManageRequest();
            });

            Button reject = new Button("Reject");
            reject.setMaxWidth(Double.MAX_VALUE);
            reject.getStyleClass().add("dark-blue");
            reject.setOnAction(e -> {
                try {
                    dataOutputStream.writeUTF("ARRequest " + request.getId() + " " + "false");
                    dataOutputStream.flush();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
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
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void handleShowDiscount(Discount discount, Stage newWindow)
    {
        try {
            Pane pane = discount.viewDiscountInGraphic(Menu.account);
            ((GridPane)pane).setAlignment(Pos.CENTER);
            Scene scene = new Scene(pane, 600, 400);
            scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
            pane.getStyleClass().add("admin-popup");
            Button edit = new Button("Edit");
            edit.setMaxWidth(Double.MAX_VALUE);
            edit.getStyleClass().add("dark-blue");
            edit.setOnAction(e -> {
                handleEditDiscount(discount , newWindow);
            });
            Button remove = new Button("Remove");
            remove.setMaxWidth(Double.MAX_VALUE);
            remove.getStyleClass().add("record-sales");
            remove.setOnAction(e -> {
                try {
                    dataOutputStream.writeUTF("RemoveDiscount " + discount.getDiscountId());
                    dataOutputStream.flush();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                newWindow.close();
                handleManageDiscounts();
            });
            GridPane.setConstraints(edit, 3, 2);
            GridPane.setConstraints(remove, 3, 3);

            pane.getChildren().addAll(edit, remove);

            newWindow.setScene(scene);
            newWindow.setOnCloseRequest(e -> handleManageDiscounts());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


    private void handleEditDiscount(Discount discount, Stage newWindow) {
        super.setPane();
        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane, 600, 400);
        scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
        gridPane.getStyleClass().add("admin-popup");
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        Label status = new Label();
        ChoiceBox<String> field = new ChoiceBox<>();
        field.getItems().addAll("MaxValue", "Percent", "StartDate", "EndDate", "NumberOfTime");
        field.getStyleClass().add("choice-box");

        TextField changeTo = new TextField();
        changeTo.setPromptText("change to");
        changeTo.getStyleClass().add("text-field");

        Button edit = new Button("edit");
        edit.setMaxWidth(Double.MAX_VALUE);
        edit.getStyleClass().add("dark-blue");
        edit.setOnAction(e -> {
            try {
                dataOutputStream.writeUTF("EditDiscount " + discount.getDiscountId() + " " + field.getValue() + " " + changeTo.getText());
                dataOutputStream.flush();
                status.setText(dataInputStream.readUTF());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
        Button back = new Button("back");
        back.getStyleClass().add("dark-blue");
        back.setMaxWidth(Double.MAX_VALUE);
        back.setOnAction(e -> handleShowDiscount(discount, newWindow));

        GridPane.setConstraints(field, 0, 0);
        GridPane.setConstraints(changeTo, 0, 1);
        GridPane.setConstraints(edit, 0, 2);
        GridPane.setConstraints(back, 0, 3);
        GridPane.setConstraints(status, 0, 4);
        GridPane.setHalignment(field, HPos.CENTER);
        GridPane.setHalignment(edit, HPos.CENTER);
        GridPane.setHalignment(back, HPos.CENTER);
        GridPane.setHalignment(status, HPos.CENTER);

        gridPane.getChildren().addAll(field, changeTo, edit, back, status);

        newWindow.setScene(scene);
    }


    public void handleAddDiscount()
    {
        try {
            super.setPane();
            Scene scene = new Scene(super.mainPane, 1000, 600);
            scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
            super.mainPane.getStyleClass().add("admin-page");
            GridPane gridPane = new GridPane();
            gridPane.setHgap(20);
            gridPane.setVgap(10);
            gridPane.setAlignment(Pos.CENTER);
            Label status = new Label();
            TextField maxValue = new TextField();
            maxValue.setPromptText("Max Value");
            maxValue.getStyleClass().add("text-field");
            TextField percent = new TextField();
            percent.setPromptText("Percent");
            TextField startDate = new TextField();
            startDate.setPromptText("Start Date");
            TextField endDate = new TextField();
            endDate.setPromptText("End Date");
            TextField numberOfTimes = new TextField();
            numberOfTimes.setPromptText("Number Of Times");

            ArrayList<CheckBox> checkBoxes = new ArrayList<>();
            ArrayList<Account> allAccounts;
            dataOutputStream.writeUTF("GetAllBuyerAccounts");
            dataOutputStream.flush();
            allAccounts = new Gson().fromJson(dataInputStream.readUTF(), new TypeToken<ArrayList<BuyerAccount>>(){}.getType());
            for (Account account : allAccounts) {
                CheckBox checkBox = new CheckBox(account.getUsername());
                checkBox.setId(account.getUsername());
                checkBoxes.add(checkBox);
            }

            Button add = new Button("Add");
            add.setOnAction(e -> {
                try {

                    ArrayList<String> unIAL = new ArrayList<>();
                    for (CheckBox checkBox : checkBoxes) {
                        if (checkBox.isSelected()){
                            unIAL.add(checkBox.getId());
                        }
                    }
                    dataOutputStream.writeUTF("AddDiscount\n" + Integer.parseInt(maxValue.getText()) + "\n" + Integer.parseInt(percent.getText()) + "\n" +
                            startDate.getText() + "\n" + endDate.getText() + "\n" + Integer.parseInt(numberOfTimes.getText()) + "\n" + new Gson().toJson(unIAL));
                    dataOutputStream.flush();
                    status.setText(dataInputStream.readUTF());
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
            add.getStyleClass().add("dark-blue");
            add.setMaxWidth(Double.MAX_VALUE);

            Button back = new Button("Back");
            back.setOnAction(e -> handleManageDiscounts());
            back.getStyleClass().add("dark-blue");
            back.setMaxWidth(Double.MAX_VALUE);

            GridPane.setConstraints(maxValue, 0, 0);
            GridPane.setConstraints(percent, 0, 1);
            GridPane.setConstraints(startDate, 0, 2);
            GridPane.setConstraints(endDate, 0, 3);
            GridPane.setConstraints(numberOfTimes, 0, 4);
            int i=5 ;
            for (CheckBox checkBox : checkBoxes) {
                GridPane.setConstraints(checkBox, 0, i);
                i++;
                gridPane.getChildren().add(checkBox);
            }
            GridPane.setConstraints(add, 0, i);
            GridPane.setConstraints(back, 0, i+1);
            GridPane.setConstraints(status, 0, i+2);
            GridPane.setHalignment(add, HPos.CENTER);
            GridPane.setHalignment(back, HPos.CENTER);
            GridPane.setHalignment(status, HPos.CENTER);

            gridPane.getChildren().addAll(maxValue, percent, startDate, endDate, numberOfTimes, add, back, status);

            super.mainPane.setCenter(gridPane);

            Menu.window.setScene(scene);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void handleAddAdmin()
    {
        super.setPane();
        Scene scene = new Scene(super.mainPane, 1000, 600);
        scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
        super.mainPane.getStyleClass().add("admin-page");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        Label status = new Label();
        TextField userName = new TextField();
        userName.getStyleClass().add("text-field");
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
        register.getStyleClass().add("dark-blue");
        register.setMaxWidth(Double.MAX_VALUE);
        register.setOnAction(e -> {
            try
            {
                dataOutputStream.writeUTF("AddAdmin " + userName.getText() + " " + firstName.getText() + " " + lastName.getText() + " " + email.getText() + " "
                        + phoneNumber.getText() + " " + password.getText() + " " + credit.getText());
                dataOutputStream.flush();
                status.setText(dataInputStream.readUTF());
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
        back.getStyleClass().add("dark-blue");
        back.setMaxWidth(Double.MAX_VALUE);
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
        GridPane.setHalignment(register, HPos.CENTER);
        GridPane.setHalignment(back, HPos.CENTER);
        GridPane.setHalignment(status, HPos.CENTER);

        gridPane.getChildren().addAll(userName, firstName, lastName, email, phoneNumber, password, credit, register, back, status);

        super.mainPane.setCenter(gridPane);

        Menu.window.setScene(scene);
    }
}
