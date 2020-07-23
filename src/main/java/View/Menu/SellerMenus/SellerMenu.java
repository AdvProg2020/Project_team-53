package View.Menu.SellerMenus;

import Model.Account.AdminAccount;
import Model.Account.BuyerAccount;
import Model.Account.SellerAccount;
import Model.Log.SellLog;
import Model.Product.DiscountAndOff.Off;
import Model.Product.Product;
import View.Menu.Menu;
import View.Menu.UserMenu;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;

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
        new Thread(new Runnable() {
            @Override
            public void run() {
                handleBuyer();
            }
        }).start();

        super.setPane();

        Scene scene = new Scene(super.mainPane, 1000, 600);
        scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
        super.mainPane.getStyleClass().add("seller-background");

        GridPane allButtons = new GridPane();
        allButtons.setVgap(10);

        Button viewAllLogs = new Button("Logs");
        viewAllLogs.getStyleClass().add("dark-blue");
        viewAllLogs.setMaxWidth(Double.MAX_VALUE);
        viewAllLogs.setOnAction(e -> handleAllLogs());

        Button manageProducts = new Button("Manage Products");
        manageProducts.getStyleClass().add("dark-blue");
        manageProducts.setMaxWidth(Double.MAX_VALUE);
        manageProducts.setOnAction(e -> handleManageProducts());
        
        Button manageOffs = new Button("Manage Offs");
        manageOffs.getStyleClass().add("dark-blue");
        manageOffs.setMaxWidth(Double.MAX_VALUE);
        manageOffs.setOnAction(e -> handleManageOffs());

        Button addAuction = new Button("Add Auction");
        addAuction.getStyleClass().add("dark-blue");
        addAuction.setMaxWidth(Double.MAX_VALUE);
        addAuction.setOnAction(e -> handleAddAuction());
        
        Button editInfoButton = new Button("Edit");
        editInfoButton.getStyleClass().add("dark-blue");
        editInfoButton.setMaxWidth(Double.MAX_VALUE);
        editInfoButton.setOnAction(e -> handleEdit());

        Button logout = new Button("Logout");
        logout.getStyleClass().add("dark-blue");
        logout.setMaxWidth(Double.MAX_VALUE);
        logout.setOnAction(e -> handleLogout());

        GridPane.setConstraints(viewAllLogs, 0, 0);
        GridPane.setConstraints(manageProducts, 0, 1);
        GridPane.setConstraints(manageOffs, 0, 2);
        GridPane.setConstraints(editInfoButton, 0, 3);
        GridPane.setConstraints(addAuction, 0, 4);
        GridPane.setConstraints(logout, 0, 5);

        GridPane.setHalignment(viewAllLogs, HPos.CENTER);
        GridPane.setHalignment(manageProducts, HPos.CENTER);
        GridPane.setHalignment(manageOffs, HPos.CENTER);
        GridPane.setHalignment(editInfoButton, HPos.CENTER);
        GridPane.setHalignment(logout, HPos.CENTER);
        GridPane.setHalignment(addAuction, HPos.CENTER);

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
        Pane pane = Objects.requireNonNull(Menu.account).viewPersonalInfoInGraphic();
        allButtons.getChildren().addAll(viewAllLogs, manageProducts, manageOffs, editInfoButton, logout, addAuction);

        GridPane.setConstraints(pane, 0, 0);
        GridPane.setConstraints(allButtons, 3, 0);

        GridPane gridPane = new GridPane();
        gridPane.getChildren().addAll(pane, allButtons);
        gridPane.setHgap(25);
        gridPane.setAlignment(Pos.CENTER);

        super.mainPane.setCenter(gridPane);

        window.setScene(scene);

    }

    private void handleBuyer()
    {
        try {
            ServerSocket sellerServerSocket = new ServerSocket(0);
            dataOutputStream.writeUTF("SetPortOfSeller " + sellerServerSocket.getLocalPort());
            dataOutputStream.flush();
            while (true)
            {
                Socket buyerSocket = sellerServerSocket.accept();
                DataInputStream sellerDataInputStream = new DataInputStream(new BufferedInputStream(buyerSocket.getInputStream()));
                DataOutputStream sellerDataOutputStream = new DataOutputStream(new BufferedOutputStream(buyerSocket.getOutputStream()));
                String input = sellerDataInputStream.readUTF();
                if (input.startsWith("exit"))
                {
                    sellerDataInputStream.close();
                    sellerDataOutputStream.close();
                    buyerSocket.close();
                    break;
                }
                else if (input.startsWith("GetProduct"))
                {
                    dataOutputStream.writeUTF("GetProduct " + input.split(" ")[1]);
                    dataOutputStream.flush();
                    Product product = new Gson().fromJson(dataInputStream.readUTF(), new TypeToken<Product>(){}.getType());
                    String address = product.getAddressOfProduct();
                    try {
                        File file = new File(address);
                        BufferedInputStream fileBufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                        byte[] fileBytes = new byte[(int) file.length()];
                        fileBufferedInputStream.read(fileBytes, 0, fileBytes.length);
                        sellerDataOutputStream.write(fileBytes, 0, fileBytes.length);
                        sellerDataOutputStream.flush();
                        fileBufferedInputStream.close();
                    }
                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                    sellerDataInputStream.close();
                    sellerDataOutputStream.close();
                    buyerSocket.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleAddAuction(){
        super.setPane();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);

        Scene scene = new Scene(super.mainPane, 1000, 600);
        scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
        super.mainPane.getStyleClass().add("admin-page");

        Label status = new Label();
        status.setFont(Font.font(20));

        TextField productID = new TextField();
        status.getStyleClass().add("text-field");
        productID.setPromptText("ProductID");

        TextField endDate = new TextField();
        endDate.getStyleClass().add("text-field");
        endDate.setPromptText("EndDate(yyyy-MM-dd_HH:mm)");

        Button create = new Button("Create");
        create.getStyleClass().add("dark-blue");
        create.setMaxWidth(Double.MAX_VALUE);
        create.setAlignment(Pos.CENTER);
        create.setOnAction(e ->{
            try {
                dataOutputStream.writeUTF("AddAuction " + productID.getText() + " " + endDate.getText());
                dataOutputStream.flush();
                status.setText(dataInputStream.readUTF());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });

        Button back = new Button("back");
        back.getStyleClass().add("dark-blue");
        back.setMaxWidth(Double.MAX_VALUE);
        back.setAlignment(Pos.CENTER);
        back.setOnAction(e -> show());

        GridPane.setConstraints(productID, 0, 0);
        GridPane.setConstraints(endDate, 0, 1);
        GridPane.setConstraints(create, 0, 2);
        GridPane.setConstraints(back, 0, 3);
        GridPane.setConstraints(status, 0, 4);

        GridPane.setHalignment(productID, HPos.CENTER);
        GridPane.setHalignment(endDate, HPos.CENTER);
        GridPane.setHalignment(create, HPos.CENTER);
        GridPane.setHalignment(back, HPos.CENTER);
        GridPane.setHalignment(status, HPos.CENTER);

        gridPane.getChildren().addAll(productID, endDate, create, back, status);
        super.mainPane.setCenter(gridPane);

        Menu.window.setScene(scene);
    }

    private void handleManageOffs() {
        super.setPane();
        ArrayList<Off> allOff = null;
        try {
            dataOutputStream.writeUTF("AllOffs");
            dataOutputStream.flush();
            allOff = new Gson().fromJson(dataInputStream.readUTF(), new TypeToken<ArrayList<Off>>(){}.getType());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        ArrayList<Off> thisSellerOff = new ArrayList<>();
        for (Off off : Objects.requireNonNull(allOff)) {
            if (off.getSellerUsername().equalsIgnoreCase(Menu.account.getUsername())){
                thisSellerOff.add(off);
            }
        }
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
        Label info = new Label("Your Offs");
        info.setFont(Font.font(25));
        GridPane.setHalignment(info, HPos.CENTER);
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
            button.setMaxWidth(Double.MAX_VALUE);
            button.getStyleClass().add("dark-blue");
            button.setOnAction(e -> {
                Stage newWindow = new Stage();
                newWindow.initModality(Modality.APPLICATION_MODAL);

                handleShowOff(off, newWindow);

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
        GridPane.setHalignment(back, HPos.CENTER);
        back.setOnAction(e -> show());

        Button addNewOff = new Button("Add Off");
        addNewOff.setAlignment(Pos.CENTER);
        addNewOff.getStyleClass().addAll("dark-blue");
        GridPane.setHalignment(addNewOff, HPos.CENTER);
        addNewOff.setMaxWidth(Double.MAX_VALUE);
        addNewOff.setOnAction(e -> handleAddNewOff());

        GridPane.setConstraints(addNewOff, 1, i);
        GridPane.setConstraints(back,1, i+1);

        gridPane.getChildren().addAll(back, addNewOff);

        scrollPane.setContent(gridPane);
        super.mainPane.setCenter(scrollPane);

        Menu.window.setScene(scene);
    }

    private void handleAddNewOff()  {

        super.setPane();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Scene scene = new Scene(super.mainPane, 1000, 600);
        scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
        super.mainPane.getStyleClass().add("admin-page");

        Label status = new Label();
        status.setFont(Font.font(20));

        TextField maxValue = new TextField();
        maxValue.getStyleClass().add("text-field");
        maxValue.setPromptText("Max Value");

        TextField percent = new TextField();
        percent.getStyleClass().add("text-field");
        percent.setPromptText("Percent (1 - 100)");

        TextField startDate = new TextField();
        startDate.getStyleClass().add("text-field");
        startDate.setPromptText("Start Date (YYYY-MM-DD_hh:mm");

        TextField endDate = new TextField();
        endDate.setPromptText("End Date (YYYY-MM-DD_hh:mm");
        endDate.getStyleClass().add("text-field");
        ArrayList<CheckBox> checkBoxes = new ArrayList<>();
        ArrayList<Product> allProduct = null;
        try {
            dataOutputStream.writeUTF("AllProducts");
            dataOutputStream.flush();
            allProduct = new Gson().fromJson(dataInputStream.readUTF(), new TypeToken<ArrayList<Product>>(){}.getType());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        for (Product product : Objects.requireNonNull(allProduct)) {
            if (product.getSellerUsername().equalsIgnoreCase(Menu.account.getUsername())){
                CheckBox checkBox = new CheckBox("ID(" + product.getProductId() +")" + product.getName());
                checkBox.setId(String.valueOf(product.getProductId()));
                checkBoxes.add(checkBox);
            }
        }
        ArrayList<Integer> selectedProducts = new ArrayList<>();

        Button sendRequest = new Button("Send Request");
        sendRequest.getStyleClass().add("dark-blue");
        sendRequest.setMaxWidth(Double.MAX_VALUE);
        sendRequest.setAlignment(Pos.CENTER);
        GridPane.setHalignment(sendRequest, HPos.CENTER);
        sendRequest.setOnAction(e -> {
            for (CheckBox checkBox : checkBoxes) {
                if (checkBox.isSelected()){
                    selectedProducts.add(Integer.parseInt(checkBox.getId()));
                }
            }
            try {
                dataOutputStream.writeUTF("AddNewOff " + maxValue.getText() + " " + percent.getText() + " " +
                        startDate.getText() + " " + endDate.getText() + " " + new Gson().toJson(selectedProducts));
                dataOutputStream.flush();
                status.setText(dataInputStream.readUTF());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

            show();
        });

        Button back = new Button("back");
        back.setAlignment(Pos.CENTER);
        back.getStyleClass().add("dark-blue");
        back.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHalignment(back, HPos.CENTER);
        back.setOnAction(e -> {
            show();
        });

        GridPane.setConstraints(maxValue, 0, 0);
        GridPane.setConstraints(percent, 0, 1);
        GridPane.setConstraints(startDate, 0, 2);
        GridPane.setConstraints(endDate, 0, 3);
        int i = 4;
        gridPane.getChildren().addAll(maxValue, percent, startDate, endDate);
        for (CheckBox checkBox : checkBoxes) {
            GridPane.setConstraints(checkBox, 0, i);
            GridPane.setHalignment(checkBox, HPos.CENTER);
            gridPane.getChildren().addAll(checkBox);
            i++;
        }
        GridPane.setConstraints(sendRequest, 0, i + 1);
        GridPane.setConstraints(back, 0, i + 2);
        GridPane.setConstraints(status, 0, i + 3);
        gridPane.getChildren().addAll(sendRequest, back,status);
        super.mainPane.setCenter(gridPane);

        Menu.window.setScene(scene);

    }

    private void handleShowOff(Off off, Stage newWindow) {
        Pane pane = off.showOffFullInfoGraphic();
        ((GridPane)pane).setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane, 600, 400);
        scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
        pane.getStyleClass().add("admin-popup");


        Button edit = new Button("Edit");
        edit.setAlignment(Pos.CENTER);
        edit.getStyleClass().add("dark-blue");
        edit.setOnAction(e -> handleEditOff(off , newWindow));

        GridPane.setConstraints(edit, 4, 1);

        pane.getChildren().addAll(edit);

        newWindow.setScene(scene);
        newWindow.setOnCloseRequest(e -> handleManageOffs());

    }

    private void handleEditOff(Off off, Stage newWindow) {
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
        field.getStyleClass().add("choice-box");
        field.getItems().addAll("maxValue", "percent", "startDate", "endDate");
        field.setValue("maxValue");

        TextField changeTo = new TextField();
        changeTo.getStyleClass().add("text-field");
        changeTo.setPromptText("change to");

        Button edit = new Button("edit");
        edit.setAlignment(Pos.CENTER);
        edit.setMaxWidth(Double.MAX_VALUE);
        edit.getStyleClass().add("dark-blue");
        edit.setOnAction(e -> {
            try {
                dataOutputStream.writeUTF("EditOff " + off.getOffId() + " " + field.getValue() + " " + changeTo.getText());
                dataOutputStream.flush();
                status.setText(dataInputStream.readUTF());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
        Button back = new Button("back");
        back.setMaxWidth(Double.MAX_VALUE);
        back.setAlignment(Pos.CENTER);
        back.getStyleClass().add("dark-blue");
        back.setOnAction(e -> handleShowOff(off, newWindow));

        GridPane.setConstraints(field, 0, 0);
        GridPane.setConstraints(changeTo, 0, 1);
        GridPane.setConstraints(edit, 0, 2);
        GridPane.setConstraints(back, 0, 3);
        GridPane.setConstraints(status, 0, 5);
        GridPane.setHalignment(field, HPos.CENTER);
        GridPane.setHalignment(changeTo, HPos.CENTER);
        GridPane.setHalignment(edit, HPos.CENTER);
        GridPane.setHalignment(back, HPos.CENTER);
        GridPane.setHalignment(status, HPos.CENTER);

        gridPane.getChildren().addAll(field, changeTo, edit, back, status);

        newWindow.setScene(scene);
    }

    private void handleManageProducts()
    {
        super.setPane();
        ArrayList<Product> allProduct = null;
        try {
            dataOutputStream.writeUTF("AllProducts");
            dataOutputStream.flush();
            allProduct = new Gson().fromJson(dataInputStream.readUTF(), new TypeToken<ArrayList<Product>>(){}.getType());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        ArrayList<Product> thisSellerProducts = new ArrayList<>();
        for (Product product : Objects.requireNonNull(allProduct)) {
            if (product.getSellerUsername().equalsIgnoreCase(Menu.account.getUsername())){
                thisSellerProducts.add(product);
            }
        }
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
        Label info = new Label("Your Products");
        info.setFont(Font.font(25));
        GridPane.setHalignment(info, HPos.CENTER);
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
            button.setMaxWidth(Double.MAX_VALUE);
            button.getStyleClass().add("dark-blue");
            button.setOnAction(e -> {
                Stage newWindow = new Stage();
                newWindow.initModality(Modality.APPLICATION_MODAL);

                handleShowProduct(product, newWindow);

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
        GridPane.setHalignment(back, HPos.CENTER);
        back.setOnAction(e -> show());

        Button addNewProduct = new Button("Add Product");
        addNewProduct.getStyleClass().addAll("dark-blue");
        GridPane.setHalignment(addNewProduct, HPos.CENTER);
        addNewProduct.setMaxWidth(Double.MAX_VALUE);
        addNewProduct.setAlignment(Pos.CENTER);
        addNewProduct.setOnAction(e -> handleAddNewProduct());

        GridPane.setConstraints(addNewProduct, 1, i);
        GridPane.setConstraints(back,1, i+1);

        back.setAlignment(Pos.CENTER);
        gridPane.getChildren().addAll(back, addNewProduct);

        scrollPane.setContent(gridPane);
        super.mainPane.setCenter(scrollPane);

        Menu.window.setScene(scene);
    }

    private void handleAddNewProduct() {

        super.setPane();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);

        Scene scene = new Scene(super.mainPane, 1000, 600);
        scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
        super.mainPane.getStyleClass().add("admin-page");

        Label status = new Label();
        status.setFont(Font.font(20));

        TextField statusTextField = new TextField();
        status.getStyleClass().add("text-field");
        statusTextField.setPromptText("Status");

        TextField name = new TextField();
        name.getStyleClass().add("text-field");
        name.setPromptText("Name");

        CheckBox available = new CheckBox("Available");
        available.setSelected(true);

        TextField number = new TextField();
        number.getStyleClass().add("text-field");
        number.setPromptText("Number");

        TextField description = new TextField();
        description.getStyleClass().add("text-field");
        description.setPromptText("Description");

        TextField categoryName = new TextField();
        categoryName.getStyleClass().add("text-field");
        categoryName.setPromptText("Category Name");

        TextField price = new TextField();
        price.getStyleClass().add("text-field");
        price.setPromptText("Price");

        TextField address = new TextField();
        address.getStyleClass().add("text-field");
        address.setPromptText("AddressOfFile");
        address.setDisable(true);

        CheckBox doesProductHasFile = new CheckBox("DoesProductHasFile");
        doesProductHasFile.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (doesProductHasFile.isSelected())
                    address.setDisable(false);
                else if (!doesProductHasFile.isSelected())
                    address.setDisable(true);
            }
        });

        Button sendRequest = new Button("Send Request");
        sendRequest.getStyleClass().add("dark-blue");
        sendRequest.setMaxWidth(Double.MAX_VALUE);
        sendRequest.setAlignment(Pos.CENTER);
        sendRequest.setOnAction(e -> {
            try {
                if (doesProductHasFile.isSelected())
                {
                    dataOutputStream.writeUTF("AddNewProduct " + statusTextField.getText() +  " " + name.getText() +  " "
                            + available.isSelected() + " " + number.getText() + " " + description.getText() + " " + categoryName.getText() + " "
                            + price.getText() + " " + doesProductHasFile.isSelected() + " " + address.getText());
                    dataOutputStream.flush();
                }
                else if (!doesProductHasFile.isSelected())
                {
                    dataOutputStream.writeUTF("AddNewProduct " + statusTextField.getText() +  " " + name.getText() +  " "
                            + available.isSelected() + " " + number.getText() + " " + description.getText() + " " + categoryName.getText() + " "
                            + price.getText() + " " + doesProductHasFile.isSelected() + " " + "DoesNotHaveFile");
                    dataOutputStream.flush();
                }
                status.setText(dataInputStream.readUTF());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            handleManageProducts();
        });

        Button back = new Button("back");
        back.getStyleClass().add("dark-blue");
        back.setMaxWidth(Double.MAX_VALUE);
        back.setAlignment(Pos.CENTER);
        back.setOnAction(e -> {
            handleManageProducts();
        });

        GridPane.setConstraints(statusTextField, 0, 0);
        GridPane.setConstraints(name, 0, 1);
        GridPane.setConstraints(number, 0, 2);
        GridPane.setConstraints(description, 0, 3);
        GridPane.setConstraints(categoryName, 0, 4);
        GridPane.setConstraints(price, 0, 5);
        GridPane.setConstraints(available, 0, 6);
        GridPane.setConstraints(doesProductHasFile, 0, 7);
        GridPane.setConstraints(address, 0, 8);
        GridPane.setConstraints(sendRequest, 0, 9);
        GridPane.setConstraints(back, 0, 10);
        GridPane.setConstraints(status, 0, 11);

        GridPane.setHalignment(status, HPos.CENTER);
        GridPane.setHalignment(name, HPos.CENTER);
        GridPane.setHalignment(available, HPos.CENTER);
        GridPane.setHalignment(number, HPos.CENTER);
        GridPane.setHalignment(description, HPos.CENTER);
        GridPane.setHalignment(categoryName, HPos.CENTER);
        GridPane.setHalignment(price, HPos.CENTER);
        GridPane.setHalignment(sendRequest, HPos.CENTER);
        GridPane.setHalignment(doesProductHasFile, HPos.CENTER);
        GridPane.setHalignment(address, HPos.CENTER);
        GridPane.setHalignment(back, HPos.CENTER);
        GridPane.setHalignment(status, HPos.CENTER);

        gridPane.getChildren().addAll(statusTextField, name, available, number, description, categoryName, price, sendRequest, back, status, doesProductHasFile, address);
        super.mainPane.setCenter(gridPane);

        Menu.window.setScene(scene);
    }
    
    private void handleAllLogs() {
        super.setPane();
        ArrayList<SellLog> allLogs = ((SellerAccount)Menu.account).getSellLogs();
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
        Label info = new Label("All Sell Logs");
        info.setFont(Font.font(25));
        GridPane.setHalignment(info, HPos.CENTER);
        info.setAlignment(Pos.CENTER);
        GridPane.setConstraints(info, 1, 0);
        gridPane.getChildren().add(info);
        int i = 1;
        for (SellLog sellLog : allLogs) {
            Label label = new Label(String.valueOf(sellLog.getLogId()));
            label.setFont(Font.font(15));
            Button button = new Button("show");
            button.setAlignment(Pos.CENTER);
            button.setMaxWidth(Double.MAX_VALUE);
            button.getStyleClass().add("dark-blue");
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

    private void handleShowLog(SellLog sellLog) {
        Stage newWindow = new Stage();
        Pane pane = sellLog.showLogWithGraphic();
        ((GridPane)pane).setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane, 600, 400);
        scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
        pane.getStyleClass().add("admin-popup");

        newWindow.setScene(scene);
        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.setOnCloseRequest(e -> handleAllLogs());
        newWindow.showAndWait();
    }

    private void handleShowProduct(Product product, Stage newWindow)
    {
        Pane pane = product.showProductFullInfoGraphic();
        ((GridPane)pane).setAlignment(Pos.CENTER);
        GridPane gridPane = new GridPane();
        GridPane.setConstraints(pane, 0, 0 );
        Scene scene = new Scene(gridPane, 700, 500);
        scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
        gridPane.getStyleClass().add("admin-popup");

        Button remove = new Button("Remove");
        remove.getStyleClass().add("record-sales");
        remove.setAlignment(Pos.CENTER);
        remove.setMaxWidth(Double.MAX_VALUE);
        remove.setOnAction(e -> {
            try {
                dataOutputStream.writeUTF("DeleteProduct " + product.getProductId());
                dataOutputStream.flush();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            handleManageProducts();
            newWindow.close();
        });

        Button edit = new Button("Edit");
        edit.getStyleClass().add("dark-blue");
        edit.setMaxWidth(Double.MAX_VALUE);
        edit.setAlignment(Pos.CENTER);
        edit.setOnAction(e -> handleEditProduct(product, newWindow));
        Label buyers = null;
        try {
            dataOutputStream.writeUTF("GetBuyerOfProduct " + product.getProductId());
            dataOutputStream.flush();
            buyers = new Label("Buyers : " + dataInputStream.readUTF());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        GridPane.setConstraints(remove, 0, 3);
        GridPane.setConstraints(edit, 0, 4);
        GridPane.setConstraints(buyers, 0 , 7, 1 , 10);

        GridPane buttons = new GridPane();
        buttons.setVgap(10);
        buttons.getChildren().addAll(remove, edit, buyers);
        GridPane.setConstraints(buttons, 2, 0);

        gridPane.setAlignment(Pos.CENTER);
        GridPane.setHalignment(pane, HPos.CENTER);
        gridPane.getChildren().addAll(pane, buttons);
        gridPane.setHgap(25);


        newWindow.setScene(scene);
        newWindow.setOnCloseRequest(e -> handleManageProducts());
//        newWindow.showAndWait();

    }

    private void handleEditProduct(Product product, Stage newWindow) {
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
        field.getStyleClass().add("choice-box");
        field.getItems().addAll("status", "name", "available", "number", "description", "categoryName", "price");
        field.setValue("status");

        TextField changeTo = new TextField();
        changeTo.getStyleClass().add("text-field");
        changeTo.setPromptText("change to");

        Button edit = new Button("edit");
        edit.setMaxWidth(Double.MAX_VALUE);
        edit.setAlignment(Pos.CENTER);
        edit.getStyleClass().add("dark-blue");
        edit.setOnAction(e -> {
            try {
                dataOutputStream.writeUTF("EditProduct " + product.getProductId() + " " + field.getValue() + " " + changeTo.getText());
                dataOutputStream.flush();
                status.setText(dataInputStream.readUTF());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
        Button back = new Button("back");
        back.setAlignment(Pos.CENTER);
        back.setMaxWidth(Double.MAX_VALUE);
        back.getStyleClass().add("dark-blue");
        back.setOnAction(e -> handleShowProduct(product, newWindow));

        GridPane.setConstraints(field, 0, 0);
        GridPane.setConstraints(changeTo, 0, 1);
        GridPane.setConstraints(edit, 0, 2);
        GridPane.setConstraints(back, 0, 3);
        GridPane.setConstraints(status, 0, 4);
        GridPane.setHalignment(field, HPos.CENTER);
        GridPane.setHalignment(changeTo, HPos.CENTER);
        GridPane.setHalignment(edit, HPos.CENTER);
        GridPane.setHalignment(back, HPos.CENTER);
        GridPane.setHalignment(status, HPos.CENTER);

        gridPane.getChildren().addAll(field, changeTo, edit, back, status);

        newWindow.setScene(scene);
    }

    private void handleEdit()
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
                alert.setContentText("Wrong input for change to");

                alert.showAndWait();
            }
        });
        Button back = new Button("back");
        back.getStyleClass().add("dark-blue");
        back.setMaxWidth(Double.MAX_VALUE);
        back.setOnAction(e -> {
            show();
        });
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

    private void handleLogout()
    {
        try {
            dataOutputStream.writeUTF("GetPortOfSeller " + account.getUsername());
            dataOutputStream.flush();
            Socket socket = new Socket("127.0.0.1", Integer.parseInt(dataInputStream.readUTF()));
            DataOutputStream temp = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            temp.writeUTF("exit");
            temp.flush();
            temp.close();
            dataOutputStream.writeUTF("logout");
            dataOutputStream.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Menu.account = null;
        UserMenu userMenu = new UserMenu(this);
        userMenu.show();
    }

}
