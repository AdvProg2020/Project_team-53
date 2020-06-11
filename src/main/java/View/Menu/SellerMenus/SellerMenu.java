package View.Menu.SellerMenus;

import Controller.AccountManager;
import View.Menu.Menu;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

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

    public void handleEdit()
    {
        super.setPane();
        VBox vBox = new VBox();
        Scene scene = new Scene(super.mainPane, 1000, 600);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        Label status = new Label();
        ChoiceBox<String> field = new ChoiceBox<>();
        field.getItems().addAll("FirstName", "LastName", "password", "email", "PhoneNumber", "company");
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
