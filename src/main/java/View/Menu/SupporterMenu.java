package View.Menu;


import javafx.scene.Scene;
import javafx.scene.control.Button;

public class SupporterMenu extends Menu {

    public SupporterMenu(Menu parentMenu) {
        super("Supporter Menu", parentMenu);
    }

    @Override
    public void show () {
        super.setPane();
        Button button = new Button("chat With Buyers");
        button.setOnAction( e -> {
            new ChatRoomMenu(this).show();
        });
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> parentMenu.show());
        Scene scene = new Scene(mainPane, 1000, 600);
        window.setScene(scene);
        window.show();
    }
}
