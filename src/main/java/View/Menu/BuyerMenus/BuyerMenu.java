package View.Menu.BuyerMenus;

import Model.Account.AdminAccount;
import Model.Account.BuyerAccount;
import Model.Account.SellerAccount;
import Model.Log.BuyLog;
import Model.Product.DiscountAndOff.Discount;
import View.Menu.ChatRoomMenu;
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
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

public class BuyerMenu extends Menu {

    public BuyerMenu(Menu parentMenu) {
        super("Buyer Menu", parentMenu);
        //super.addToSubMenus(1,new PersonalInfoMenu(this));
        //super.addToSubMenus(2, new ViewCartMenu(this));
        //super.addToSubMenus(3, new ViewOrdersMenu(this));
        //super.addToSubMenus(4, this.getViewBalanceMenu());
        //super.addToSubMenus(5, this.getViewDiscountCodesMenu());
        //super.addToSubMenus(6, this.getLogoutMenu());
    }

    public void show(){
        super.setPane();

        GridPane allButtons = new GridPane();
        allButtons.setVgap(10);

        Scene scene = new Scene(super.mainPane, 1000, 600);
        scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
        super.mainPane.getStyleClass().add("buyer-background");

        Button viewAllDiscounts = new Button("Discounts");
        viewAllDiscounts.setOnAction(e -> handleAllDiscountShow());
        viewAllDiscounts.getStyleClass().add("dark-blue");
        viewAllDiscounts.setMaxWidth(Double.MAX_VALUE);

        Button viewAllLogs = new Button("Logs");
        viewAllLogs.setOnAction(e -> handleAllLogs());
        viewAllLogs.getStyleClass().add("dark-blue");
        viewAllLogs.setMaxWidth(Double.MAX_VALUE);

        Button viewMyAuctions = new Button("My Auctions");
        viewMyAuctions.setOnAction(e -> handleMyAuctions());
        viewMyAuctions.getStyleClass().add("dark-blue");
        viewMyAuctions.setMaxWidth(Double.MAX_VALUE);

        Button viewCart = new Button("Cart");
        viewCart.getStyleClass().add("dark-blue");
        viewCart.setMaxWidth(Double.MAX_VALUE);
        viewCart.setOnAction(e -> {
            ViewCartMenu cartMenu = new ViewCartMenu(this);
            cartMenu.show();
        });

        Button editInfoButton = new Button("Edit");
        editInfoButton.getStyleClass().add("dark-blue");
        editInfoButton.setMaxWidth(Double.MAX_VALUE);
        editInfoButton.setOnAction(e -> handleEdit());

        Button showSupporterButton = new Button("Show Online Supporters");
        showSupporterButton.getStyleClass().add("dark-blue");
        showSupporterButton.setMaxWidth(Double.MAX_VALUE);
        showSupporterButton.setOnAction(e -> handleShowSupporters());

        Button logout = new Button("Logout");
        logout.getStyleClass().add("dark-blue");
        logout.setMaxWidth(Double.MAX_VALUE);
        logout.setOnAction(e -> handleLogout());

        Button changeCredit = new Button("Change Credit");
        changeCredit.getStyleClass().add("dark-blue");
        changeCredit.setMaxWidth(Double.MAX_VALUE);
        changeCredit.setOnAction(e -> handleChangeCredit());

        GridPane.setConstraints(viewAllDiscounts, 0, 0);
        GridPane.setConstraints(viewAllLogs, 0, 1);
        GridPane.setConstraints(viewMyAuctions, 0, 2);
        GridPane.setConstraints(viewCart,0, 3);
        GridPane.setConstraints(editInfoButton, 0, 4);
        GridPane.setConstraints(showSupporterButton, 0, 5);
        GridPane.setConstraints(changeCredit , 0 , 6);
        GridPane.setConstraints(logout, 0, 7);

        GridPane.setHalignment(viewAllDiscounts, HPos.CENTER);
        GridPane.setHalignment(viewAllLogs, HPos.CENTER);
        GridPane.setHalignment(viewMyAuctions, HPos.CENTER);
        GridPane.setHalignment(viewCart, HPos.CENTER);
        GridPane.setHalignment(editInfoButton, HPos.CENTER);
        GridPane.setHalignment(showSupporterButton, HPos.CENTER);
        GridPane.setHalignment(logout, HPos.CENTER);
        GridPane.setHalignment(changeCredit , HPos.CENTER);
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
        allButtons.getChildren().addAll(viewAllDiscounts, viewAllLogs, viewCart, editInfoButton, logout, viewMyAuctions, showSupporterButton , changeCredit);

        GridPane.setConstraints(pane, 0, 0);
        GridPane.setConstraints(allButtons, 3, 0);

        GridPane gridPane = new GridPane();
        gridPane.getChildren().addAll(pane, allButtons);
        gridPane.setHgap(25);
        gridPane.setAlignment(Pos.CENTER);

        super.mainPane.setCenter(gridPane);

        window.setScene(scene);
    }

    private void handleChangeCredit() {
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

        TextField much = new TextField();
        much.setPromptText("amount ");
        much.getStyleClass().add("textfield.css");


        TextField bankUsername = new TextField();
        bankUsername.setPromptText("Bank Username");
        bankUsername.getStyleClass().add("textfield.css");

        TextField bankPassword = new TextField();
        bankPassword.setPromptText("Bank Password");
        bankPassword.getStyleClass().add("textfield.css");


        TextField bankId = new TextField();
        bankId.setPromptText("Bank Id");
        bankId.getStyleClass().add("textfield.css");

        Button edit = new Button("edit");
        edit.getStyleClass().add("dark-blue");
        edit.setMaxWidth(Double.MAX_VALUE);
        edit.setOnAction(e -> {
            try {
                dataOutputStream.writeUTF("changeCredit " +  much.getText() + " " + bankUsername.getText() + " " + bankPassword.getText() + " " + bankId.getText());
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
        GridPane.setConstraints(much, 0, 1);
        GridPane.setConstraints(bankUsername, 0 , 2);
        GridPane.setConstraints(bankPassword, 0 , 3);
        GridPane.setConstraints(bankId, 0 , 4);

        GridPane.setConstraints(edit, 0, 5);
        GridPane.setConstraints(back, 0, 6);
        GridPane.setConstraints(status, 0, 7);
        GridPane.setHalignment(edit, HPos.CENTER);
        GridPane.setHalignment(back, HPos.CENTER);
        GridPane.setHalignment(bankUsername, HPos.CENTER);

        GridPane.setHalignment(bankPassword, HPos.CENTER);
        GridPane.setHalignment(bankId, HPos.CENTER);
        GridPane.setHalignment(status, HPos.CENTER);
        gridPane.getChildren().addAll(much, bankUsername , bankPassword , bankId , edit, back, status);
        super.mainPane.setCenter(gridPane);

        Menu.window.setScene(scene);
    }


    private void handleAllLogs() {
        super.setPane();
        ArrayList<BuyLog> allLogs = ((BuyerAccount)Menu.account).getBuyLogs();
        Scene scene = new Scene(super.mainPane, 1000, 600);
        scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
        super.mainPane.getStyleClass().add("admin-page");
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.getStyleClass().add("scroll-pane");
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        Label info = new Label("All Buy Logs");
        info.setFont(Font.font(25));
        GridPane.setHalignment(info, HPos.CENTER);
        info.setAlignment(Pos.CENTER);
        GridPane.setConstraints(info, 1, 0);
        gridPane.getChildren().add(info);
        int i = 1;
        for (BuyLog buyLog : allLogs) {
            Label label = new Label(String.valueOf(buyLog.getLogId()));
            label.setFont(Font.font(15));
            Button button = new Button("show");
            button.setMaxWidth(Double.MAX_VALUE);
            button.getStyleClass().add("dark-blue");
            button.setAlignment(Pos.CENTER);
            button.setOnAction(e -> {
                handleShowLog(buyLog);
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
        gridPane.getChildren().add(back);

        scrollPane.setContent(gridPane);
        super.mainPane.setCenter(scrollPane);

        Menu.window.setScene(scene);
    }

    private void handleShowSupporters()
    {
        new ChatRoomMenu(this).show();
    }

    private void handleMyAuctions()
    {
        try {
            super.setPane();
            ArrayList<String> allAuctions;
            dataOutputStream.writeUTF("GetAuctionOfAccount");
            dataOutputStream.flush();
            allAuctions = new Gson().fromJson(dataInputStream.readUTF(), new TypeToken<ArrayList<String>>(){}.getType());
            Scene scene = new Scene(super.mainPane, 1000, 600);
            scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
            super.mainPane.getStyleClass().add("admin-page");
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            scrollPane.setFitToHeight(true);
            scrollPane.setFitToWidth(true);
            scrollPane.getStyleClass().add("scroll-pane");
            GridPane gridPane = new GridPane();
            gridPane.setHgap(20);
            gridPane.setVgap(10);
            gridPane.setAlignment(Pos.CENTER);
            Label info = new Label("My Auctions");
            info.setFont(Font.font(25));
            GridPane.setHalignment(info, HPos.CENTER);
            info.setAlignment(Pos.CENTER);
            GridPane.setConstraints(info, 1, 0);
            gridPane.getChildren().add(info);
            int i = 1;
            for (String string : allAuctions) {
                Label label = new Label(string);
                label.setFont(Font.font(15));
                Button button = new Button("show");
                button.setMaxWidth(Double.MAX_VALUE);
                button.getStyleClass().add("dark-blue");
                button.setAlignment(Pos.CENTER);
                button.setOnAction(e -> {
                    handleShowAuction(string);
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

    private void handleShowLog(BuyLog buyLog) {
        Stage newWindow = new Stage();
        Pane pane = buyLog.showLogWithGraphic();
        ((GridPane)pane).setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane, 600, 400);
        scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
        pane.getStyleClass().add("admin-popup");

        newWindow.setScene(scene);
        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.setOnCloseRequest(e -> handleAllLogs());
        newWindow.showAndWait();
    }

    private void handleShowAuction(String string){}

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


    public void handleLogout()
    {
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

    public void handleAllDiscountShow()
    {
        super.setPane();
        Scene scene = new Scene(super.mainPane, 1000, 600);
        scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
        super.mainPane.getStyleClass().add("admin-page");
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.getStyleClass().add("scroll-pane");
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        Label info = new Label("All Discounts");
        info.setFont(Font.font(25));
        GridPane.setHalignment(info, HPos.CENTER);
        info.setAlignment(Pos.CENTER);
        GridPane.setConstraints(info, 1, 0);
        gridPane.getChildren().add(info);
        ArrayList<Integer> allDiscountIds = null;
        try {
            dataOutputStream.writeUTF("GetDiscountOfAccount");
            dataOutputStream.flush();
            allDiscountIds = new Gson().fromJson(dataInputStream.readUTF(), new TypeToken<ArrayList<Integer>>(){}.getType());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        int i = 1;
        for (Integer discountId : Objects.requireNonNull(allDiscountIds)) {

            Label label = new Label(discountId.toString());
            label.setFont(Font.font(15));
            Button button = new Button("show");
            button.setMaxWidth(Double.MAX_VALUE);
            button.getStyleClass().add("dark-blue");
            button.setAlignment(Pos.CENTER);
            button.setOnAction(e -> {
                handleShowDiscount(discountId);
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
        gridPane.getChildren().add(back);

        scrollPane.setContent(gridPane);
        super.mainPane.setCenter(scrollPane);

        Menu.window.setScene(scene);
    }

    private void handleShowDiscount(Integer discountId)
    {
        Discount discount = null;
        try {
            dataOutputStream.writeUTF("GetDiscountWithID " + discountId);
            dataOutputStream.flush();
            discount = new Gson().fromJson(dataInputStream.readUTF(), new TypeToken<Discount>(){}.getType());
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
        Stage newWindow = new Stage();
        Pane pane = Objects.requireNonNull(discount).viewDiscountInGraphic(Menu.account);
        ((GridPane)pane).setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane, 600, 400);
        scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
        pane.getStyleClass().add("admin-popup");

        newWindow.setScene(scene);
        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.setOnCloseRequest(e -> handleAllDiscountShow());
        newWindow.showAndWait();
    }
}

