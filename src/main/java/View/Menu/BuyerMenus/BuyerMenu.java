package View.Menu.BuyerMenus;

import Controller.AccountManager;
import View.Menu.Menu;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

        Button editInfoButton = new Button("Edit");
        editInfoButton.setOnAction(e -> handleEdit());

        Button logout = new Button("Logout");
        logout.setOnAction(e -> handleLogout());

        Button back = new Button("Back");
        back.setOnAction(e -> parentMenu.show());

        allButtons.getChildren().addAll(editInfoButton, logout, back);

        HBox hBox = new HBox(AccountManager.viewPersonalInfoInGraphic() , allButtons);
        hBox.setSpacing(20);

        Scene scene = new Scene(super.mainPane, 1000, 600);
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
    }

}
