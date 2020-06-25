package View.Menu.BuyerMenus;

import Controller.AccountManager;
import Model.Account.BuyerAccount;
import Model.Log.BuyLog;
import View.Menu.Menu;
import View.Menu.ViewModelsWithGraphic;
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
import java.util.ArrayList;

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

        Button logout = new Button("Logout");
        logout.getStyleClass().add("dark-blue");
        logout.setMaxWidth(Double.MAX_VALUE);
        logout.setOnAction(e -> handleLogout());

        Button back = new Button("Back");
        back.getStyleClass().add("dark-blue");
        back.setMaxWidth(Double.MAX_VALUE);
        back.setOnAction(e -> parentMenu.show());

        GridPane.setConstraints(viewAllDiscounts, 0, 0);
        GridPane.setConstraints(viewAllLogs, 0, 1);
        GridPane.setConstraints(viewCart,0, 2);
        GridPane.setConstraints(editInfoButton, 0, 3);
        GridPane.setConstraints(logout, 0, 4);
        GridPane.setConstraints(back, 0, 5);

        GridPane.setHalignment(viewAllDiscounts, HPos.CENTER);
        GridPane.setHalignment(viewAllLogs, HPos.CENTER);
        GridPane.setHalignment(viewCart, HPos.CENTER);
        GridPane.setHalignment(editInfoButton, HPos.CENTER);
        GridPane.setHalignment(logout, HPos.CENTER);
        GridPane.setHalignment(back, HPos.CENTER);

        Pane pane = ViewModelsWithGraphic.viewPersonalInfoInGraphic(AccountManager.getLoggedInAccount().getUsername());
        allButtons.getChildren().addAll(viewAllDiscounts, viewAllLogs, viewCart, editInfoButton, logout, back);

        GridPane.setConstraints(pane, 0, 0);
        GridPane.setConstraints(allButtons, 3, 0);

        GridPane gridPane = new GridPane();
        gridPane.getChildren().addAll(pane, allButtons);
        gridPane.setHgap(25);
        gridPane.setAlignment(Pos.CENTER);

        super.mainPane.setCenter(gridPane);

        window.setScene(scene);
    }

    private void handleAllLogs() {
        super.setPane();
        ArrayList<BuyLog> allLogs = ((BuyerAccount)AccountManager.getLoggedInAccount()).getBuyLogs();
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

        super.mainPane.setCenter(gridPane);

        Menu.window.setScene(scene);
    }

    private void handleShowLog(BuyLog buyLog) {
        Stage newWindow = new Stage();
        Pane pane = ViewModelsWithGraphic.showLogWithGraphic(buyLog);
        ((GridPane)pane).setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane, 600, 400);
        scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
        pane.getStyleClass().add("admin-popup");

        newWindow.setScene(scene);
        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.setOnCloseRequest(e -> handleAllLogs());
        newWindow.showAndWait();
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
                status.setText(AccountManager.edit(field.getValue(), changeTo.getText()));
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
        AccountManager.logOut();
        parentMenu.show();
    }

    public void handleAllDiscountShow()
    {
        super.setPane();
        ArrayList<Integer> allDiscountIds = ((BuyerAccount)AccountManager.getLoggedInAccount()).getDiscountIds();
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
        Label info = new Label("All Discounts");
        info.setFont(Font.font(25));
        GridPane.setHalignment(info, HPos.CENTER);
        info.setAlignment(Pos.CENTER);
        GridPane.setConstraints(info, 1, 0);
        gridPane.getChildren().add(info);
        int i = 1;
        for (Integer discountId : allDiscountIds) {
            if (!((BuyerAccount)AccountManager.getLoggedInAccount()).canUseDiscount(discountId))
                continue;

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

        super.mainPane.setCenter(gridPane);

        Menu.window.setScene(scene);
    }

    private void handleShowDiscount(Integer discountId)
    {
        Stage newWindow = new Stage();
        Pane pane = ViewModelsWithGraphic.viewDiscount(discountId);
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
