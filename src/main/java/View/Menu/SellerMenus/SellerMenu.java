package View.Menu.SellerMenus;

import Controller.AccountManager;
import Controller.Database;
import Controller.SellerManager;
import Model.Account.SellerAccount;
import Model.Log.SellLog;
import Model.Product.DiscountAndOff.Off;
import Model.Product.Product;
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

public class SellerMenu extends Menu {

    public SellerMenu(Menu parentMenu) {
        super("Seller Menu", parentMenu);
        //super.addToSubMenus(1, new PersonalInfoMenu(this));
        //super.addToSubMenus(2, this.getViewCompanyInformMenu());
        //super.addToSubMenus(3, this.getViewSalesHistoryMenu());
        //super.addToSubMenus(4, new MangeProductsMenu(this));
        //super.addToSubMenus(5, this.getAddProductMenu());
        //super.addToSubMenus(6, this.getRemoveProductMenu());
        //super.addToSubMenus(7, this.getShowAllCategoriesMenu());
        //super.addToSubMenus(8, new ViewOffsMenu(this));
    }

    public void show(){
        super.setPane();

        VBox allButtons = new VBox();
        allButtons.setSpacing(10);

        Button viewAllLogs = new Button("Logs");
        viewAllLogs.setOnAction(e -> handleAllLogs());

        Button manageProducts = new Button("Manage Products");
        manageProducts.setOnAction(e -> handleManageProducts());
        
        Button manageOffs = new Button("Manage Offs");
        manageOffs.setOnAction(e -> handleManageOffs());
        
        Button editInfoButton = new Button("Edit");
        editInfoButton.setOnAction(e -> handleEdit());

        Button logout = new Button("Logout");
        logout.setOnAction(e -> handleLogout());

        Button back = new Button("Back");
        back.setOnAction(e -> parentMenu.show());

        allButtons.getChildren().addAll(viewAllLogs, manageProducts, manageOffs, editInfoButton, logout, back);

        HBox hBox = new HBox(ViewModelsWithGraphic.viewPersonalInfoInGraphic(AccountManager.getLoggedInAccount().getUsername()) , allButtons);
        hBox.setSpacing(20);

        Scene scene = new Scene(super.mainPane, 1000, 600);
        super.mainPane.setCenter(hBox);

        window.setScene(scene);

    }

    private void handleManageOffs() {
        super.setPane();
        ArrayList<Off> allOff = Database.getAllOffs();
        ArrayList<Off> thisSellerOff = new ArrayList<>();
        for (Off off : allOff) {
            if (off.getSellerUsername().equalsIgnoreCase(AccountManager.getLoggedInAccount().getUsername())){
                thisSellerOff.add(off);
            }
        }
        Scene scene = new Scene(super.mainPane, 1000, 600);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        Label info = new Label("Your Offs");
        info.setFont(Font.font(25));
        info.setAlignment(Pos.CENTER);
        GridPane.setConstraints(info, 1, 0);
        gridPane.getChildren().add(info);
        int i = 1;

        for (Off off : thisSellerOff) {
            String text = "";
            Label label = new Label();
            text = text + "ID(" + off.getOffId() + "):" + off.getProductIds();
            label.setText(text);
            label.setFont(Font.font(15));
            Button button = new Button("show");
            button.setOnAction(e -> {
                Stage newWindow = new Stage();
                newWindow.initModality(Modality.APPLICATION_MODAL);

                handleShowOff(off.getOffId(), newWindow);

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
        back.setOnAction(e -> show());

        Button addNewOff = new Button("Add Off");
        addNewOff.setOnAction(e -> handleAddNewOff());

        GridPane.setConstraints(addNewOff, 1, i);
        GridPane.setConstraints(back,1, i+1);

        back.setAlignment(Pos.CENTER);
        gridPane.getChildren().addAll(back, addNewOff);

        super.mainPane.setCenter(gridPane);

        Menu.window.setScene(scene);
    }

    private void handleAddNewOff()  {

        super.setPane();
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        Scene scene = new Scene(super.mainPane, 1000, 600);

        Label status = new Label();
        status.setFont(Font.font(20));

        TextField maxValue = new TextField();
        maxValue.setPromptText("Max Value");

        TextField percent = new TextField();
        percent.setPromptText("Percent (1 - 100)");


        TextField startDate = new TextField();
        startDate.setPromptText("Start Date (YYYY-MM-DD_hh:mm");

        TextField endDate = new TextField();
        endDate.setPromptText("End Date (YYYY-MM-DD_hh:mm");
        ArrayList<CheckBox> checkBoxes = new ArrayList<>();
        ArrayList<Product> allProduct = Database.getAllProducts();
        for (Product product : allProduct) {
            if (product.getSellerUsername().equalsIgnoreCase(AccountManager.getLoggedInAccount().getUsername())){
                CheckBox checkBox = new CheckBox("ID(" + product.getProductId() +")" + product.getName());
                checkBox.setId(String.valueOf(product.getProductId()));
                checkBoxes.add(checkBox);
            }
        }
        ArrayList<Integer> selectedProducts = new ArrayList<>();

        Button sendRequest = new Button("Send Request");
        sendRequest.setOnAction(e -> {
            for (CheckBox checkBox : checkBoxes) {
                if (checkBox.isSelected()){
                    selectedProducts.add(Integer.parseInt(checkBox.getId()));
                }
            }
            status.setText(SellerManager.addNewOff(Integer.parseInt(maxValue.getText()), Integer.parseInt(percent.getText()),
                    startDate.getText(), endDate.getText(), selectedProducts));
            show();
        });

        Button back = new Button("back");
        back.setOnAction(e -> {
            show();
        });

        vBox.getChildren().addAll(maxValue, percent, startDate, endDate);
        for (CheckBox checkBox : checkBoxes) {
            vBox.getChildren().addAll(checkBox);
        }
        vBox.getChildren().addAll(sendRequest, back,status);
        super.mainPane.setCenter(vBox);

        Menu.window.setScene(scene);

    }

    private void handleShowOff(int offId, Stage newWindow) {
        Pane pane = ViewModelsWithGraphic.showOffFullInfoGraphic(offId);
        ((GridPane)pane).setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane, 600, 400);


        Button edit = new Button("Edit");
        edit.setOnAction(e -> handleEditOff(offId , newWindow));

        GridPane.setConstraints(edit, 4, 1);

        pane.getChildren().addAll(edit);

        newWindow.setScene(scene);
        newWindow.setOnCloseRequest(e -> handleManageOffs());

    }

    private void handleEditOff(int offId, Stage newWindow) {
        super.setPane();
        VBox vBox = new VBox();
        Scene scene = new Scene(vBox, 600, 400);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        Label status = new Label();
        ChoiceBox<String> field = new ChoiceBox<>();
        field.getItems().addAll("maxValue", "percent", "startDate", "endDate");
        field.setValue("maxValue");

        TextField changeTo = new TextField();
        changeTo.setPromptText("change to");

        Button edit = new Button("edit");
        edit.setOnAction(e -> {
            status.setText(SellerManager.editOff(offId, field.getValue(), changeTo.getText()));
        });
        Button back = new Button("back");
        back.setOnAction(e -> handleShowOff(offId, newWindow));
        vBox.getChildren().addAll(field, changeTo, edit, back, status);

        newWindow.setScene(scene);
    }


    public void handleManageProducts()
    {
        super.setPane();
        ArrayList<Product> allProduct = Database.getAllProducts();
        ArrayList<Product> thisSellerProducts = new ArrayList<>();
        for (Product product : allProduct) {
            if (product.getSellerUsername().equalsIgnoreCase(AccountManager.getLoggedInAccount().getUsername())){
                thisSellerProducts.add(product);
            }
        }
        Scene scene = new Scene(super.mainPane, 1000, 600);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        Label info = new Label("Your Products");
        info.setFont(Font.font(25));
        info.setAlignment(Pos.CENTER);
        GridPane.setConstraints(info, 1, 0);
        gridPane.getChildren().add(info);
        int i = 1;
        for (Product product : thisSellerProducts) {
            String text = "";
            Label label = new Label();
            text = text + "ID(" + product.getProductId() + "):" + product.getName();
            label.setText(text);
            label.setFont(Font.font(15));
            Button button = new Button("show");
            button.setOnAction(e -> {
                Stage newWindow = new Stage();
                newWindow.initModality(Modality.APPLICATION_MODAL);

                handleShowProduct(product.getProductId(), newWindow);

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
        back.setOnAction(e -> show());

        Button addNewProduct = new Button("Add Product");
        addNewProduct.setOnAction(e -> handleAddNewProduct());

        GridPane.setConstraints(addNewProduct, 1, i);
        GridPane.setConstraints(back,1, i+1);

        back.setAlignment(Pos.CENTER);
        gridPane.getChildren().addAll(back, addNewProduct);

        super.mainPane.setCenter(gridPane);

        Menu.window.setScene(scene);
    }

    private void handleAddNewProduct() {

        super.setPane();
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        Scene scene = new Scene(super.mainPane, 1000, 600);

        Label status = new Label();
        status.setFont(Font.font(20));

        TextField statusTextField = new TextField();
        statusTextField.setPromptText("Status");

        TextField name = new TextField();
        name.setPromptText("Name");

        CheckBox available = new CheckBox("Available");
        available.setSelected(true);

        TextField number = new TextField();
        number.setPromptText("Number");

        TextField description = new TextField();
        description.setPromptText("Description");

        TextField categoryName = new TextField();
        categoryName.setPromptText("Category Name");

        TextField price = new TextField();
        price.setPromptText("Price");

        Button sendRequest = new Button("Send Request");
        sendRequest.setOnAction(e -> {
            status.setText(SellerManager.sendAddProductRequest(statusTextField.getText(), name.getText(), available.isSelected(),
                    Integer.parseInt(number.getText()), description.getText(), categoryName.getText(),
                    Integer.parseInt(price.getText())));
            handleManageProducts();
        });

        Button back = new Button("back");
        back.setOnAction(e -> {
            handleManageProducts();
        });

        vBox.getChildren().addAll(statusTextField, name, available, number, description, categoryName, price, sendRequest, back, status);
        super.mainPane.setCenter(vBox);

        Menu.window.setScene(scene);

    }
    
    private void handleAllLogs() {
        super.setPane();
        ArrayList<SellLog> allLogs = ((SellerAccount)AccountManager.getLoggedInAccount()).getSellLogs();
        Scene scene = new Scene(super.mainPane, 1000, 600);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        Label info = new Label("All Sell Logs");
        info.setFont(Font.font(25));
        info.setAlignment(Pos.CENTER);
        GridPane.setConstraints(info, 1, 0);
        gridPane.getChildren().add(info);
        int i = 1;
        for (SellLog sellLog : allLogs) {
            Label label = new Label(String.valueOf(sellLog.getLogId()));
            label.setFont(Font.font(15));
            Button button = new Button("show");
            button.setAlignment(Pos.CENTER);
            button.setOnAction(e -> {
                handleShowLog(sellLog);
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

    private void handleShowLog(SellLog sellLog) {
        Stage newWindow = new Stage();
        Pane pane = ViewModelsWithGraphic.showLogWithGraphic(sellLog);
        ((GridPane)pane).setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane, 600, 400);

        newWindow.setScene(scene);
        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.setOnCloseRequest(e -> handleAllLogs());
        newWindow.showAndWait();
    }

    public void handleShowProduct(int productID , Stage newWindow)
    {
        Pane pane = ViewModelsWithGraphic.showFullInfoGraphic(productID);
        ((GridPane)pane).setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane, 600, 400);

        Button remove = new Button("Remove");
        remove.setOnAction(e -> {
            SellerManager.deleteProduct(productID);
            handleManageProducts();
            newWindow.close();
        });

        Button edit = new Button("Edit");
        edit.setOnAction(e -> handleEditProduct(productID, newWindow));

        GridPane.setConstraints(remove, 4, 1);
        GridPane.setConstraints(edit, 4, 2);

        pane.getChildren().addAll(remove , edit);

        newWindow.setScene(scene);
        newWindow.setOnCloseRequest(e -> handleManageProducts());
//        newWindow.showAndWait();

    }

    private void handleEditProduct(int productId, Stage newWindow) {
        super.setPane();
        VBox vBox = new VBox();
        Scene scene = new Scene(vBox, 600, 400);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        Label status = new Label();
        ChoiceBox<String> field = new ChoiceBox<>();
        field.getItems().addAll("status", "name", "available", "number", "description", "categoryName", "price");
        field.setValue("status");

        TextField changeTo = new TextField();
        changeTo.setPromptText("change to");

        Button edit = new Button("edit");
        edit.setOnAction(e -> {
            status.setText(SellerManager.sendEditProductRequest(productId, field.getValue(), changeTo.getText()));
        });
        Button back = new Button("back");
        back.setOnAction(e -> handleShowProduct(productId, newWindow));
        vBox.getChildren().addAll(field, changeTo, edit, back, status);

        newWindow.setScene(scene);
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

}
