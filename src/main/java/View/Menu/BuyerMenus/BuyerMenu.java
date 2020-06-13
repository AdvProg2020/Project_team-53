package View.Menu.BuyerMenus;

import Controller.AccountManager;
import Model.Account.BuyerAccount;
import Model.Log.BuyLog;
import View.Menu.Menu;
import View.Menu.ViewModelsWithGraphic;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

        VBox allButtons = new VBox();
        allButtons.setSpacing(10);

        Button viewAllDiscounts = new Button("Discounts");
        viewAllDiscounts.setOnAction(e -> handleAllDiscountShow());

        Button viewAllLogs = new Button("Logs");
        viewAllLogs.setOnAction(e -> handleAllLogs());

        Button viewCart = new Button("Cart");
        viewCart.setOnAction(e -> {
            ViewCartMenu cartMenu = new ViewCartMenu(this);
            cartMenu.show();
        });

        Button editInfoButton = new Button("Edit");
        editInfoButton.setOnAction(e -> handleEdit());

        Button logout = new Button("Logout");
        logout.setOnAction(e -> handleLogout());

        Button back = new Button("Back");
        back.setOnAction(e -> parentMenu.show());

        allButtons.getChildren().addAll(viewAllDiscounts, viewAllLogs, viewCart, editInfoButton, logout, back);

        HBox hBox = new HBox(ViewModelsWithGraphic.viewPersonalInfoInGraphic(AccountManager.getLoggedInAccount().getUsername()) , allButtons);
        hBox.setSpacing(20);

        Scene scene = new Scene(super.mainPane, 1000, 600);
        super.mainPane.setCenter(hBox);

        window.setScene(scene);

    }

    private void handleAllLogs() {
        super.setPane();
        ArrayList<BuyLog> allLogs = ((BuyerAccount)AccountManager.getLoggedInAccount()).getBuyLogs();
        Scene scene = new Scene(super.mainPane, 1000, 600);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        Label info = new Label("All Buy Logs");
        info.setFont(Font.font(25));
        info.setAlignment(Pos.CENTER);
        GridPane.setConstraints(info, 1, 0);
        gridPane.getChildren().add(info);
        int i = 1;
        for (BuyLog buyLog : allLogs) {
            Label label = new Label(String.valueOf(buyLog.getLogId()));
            label.setFont(Font.font(15));
            Button button = new Button("show");
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
        back.setOnAction(e -> show());
        GridPane.setConstraints(back,1, i);
        back.setAlignment(Pos.CENTER);
        gridPane.getChildren().add(back);

        super.mainPane.setCenter(gridPane);

        Menu.window.setScene(scene);
    }

    private void handleShowLog(BuyLog buyLog) {
        Stage newWindow = new Stage();
        Pane pane = buyLog.showWithGraphic();
        ((GridPane)pane).setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane, 600, 400);

        newWindow.setScene(scene);
        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.setOnCloseRequest(e -> handleAllLogs());
        newWindow.showAndWait();
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


    public void handleAllDiscountShow()
    {
        super.setPane();
        ArrayList<Integer> allDiscountIds = ((BuyerAccount)AccountManager.getLoggedInAccount()).getDiscountIds();
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
        for (Integer discountId : allDiscountIds) {
            if (!((BuyerAccount)AccountManager.getLoggedInAccount()).canUseDiscount(discountId))
                continue;

            Label label = new Label(discountId.toString());
            label.setFont(Font.font(15));
            Button button = new Button("show");
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
        back.setOnAction(e -> show());
        GridPane.setConstraints(back,1, i);
        back.setAlignment(Pos.CENTER);
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

        newWindow.setScene(scene);
        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.setOnCloseRequest(e -> handleAllDiscountShow());
        newWindow.showAndWait();
    }



}
