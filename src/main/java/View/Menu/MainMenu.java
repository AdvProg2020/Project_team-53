package View.Menu;

import View.Menu.OffMenus.OffMenu;
import View.Menu.ProductsMenus.ProductsMenu;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;



public class MainMenu extends Menu{

    public MainMenu() {
        super("Main Menu", null);
        super.addToSubMenus(1, new LoginMenu(this));
        super.addToSubMenus(2, new UserMenu(this));
        super.addToSubMenus(3, new ProductsMenu(this));
        super.addToSubMenus(4, new OffMenu(this));
    }

    @Override
    public void setPane() {
        super.setPane();
        Label nothing = new Label("Nothing");
        mainPane.setCenter(new HBox(nothing));
    }

    @Override
    public void show() {
        setPane();
        Scene scene = new Scene(mainPane ,500 ,500);
        Menu.window.setScene(scene);
        Menu.window.show();
    }
}
