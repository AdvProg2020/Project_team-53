package View.Menu;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PersonalInfoMenu  extends Menu{
    public PersonalInfoMenu(Menu parentMenu) {
        super("Personal Info Menu", parentMenu);
    }

    @Override
    public void show() {
        super.setPane();
        VBox vBox = new VBox();
        Scene scene = new Scene(super.mainPane, 1000, 600);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        Button showInfo = new Button("View Personal Info");
        showInfo.setOnAction(e -> {
            handleViewInfo();
        });
        Button editInfo = new Button("Edit Info");
        editInfo.setOnAction(e -> {
            handleEdit();
        });
        Button back = new Button("back");
        back.setOnAction(e -> {
            parentMenu.show();
        });
        vBox.getChildren().addAll(showInfo, editInfo, back);
        super.mainPane.setCenter(vBox);

        window.setScene(scene);
    }

    public void handleViewInfo()
    {
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
        field.getItems().addAll("first name", "last name", "password", "email", "phone number");
        TextField changeTo = new TextField();
        changeTo.setPromptText("change to");
        Button edit = new Button("edit");
        edit.setOnAction(e -> {
            //status.setText(AccountManager.edit(field.getText(), changeTo.getText()));
        });
        Button back = new Button("back");
        back.setOnAction(e -> {
            show();
        });
        vBox.getChildren().addAll(field, changeTo, edit, back, status);
        super.mainPane.setCenter(vBox);

        Menu.window.setScene(scene);
    }
}
