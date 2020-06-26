package View.Menu;

import View.Menu.OffMenus.OffMenu;
import View.Menu.ProductsMenus.ProductsMenu;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


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
        Label welcome = new Label("WELCOME");
        welcome.setAlignment(Pos.CENTER);
        GridPane gridPane = new GridPane();
        super.mainPane.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
        super.mainPane.getStyleClass().add("main");
        GridPane.setHalignment(welcome, HPos.CENTER);
        GridPane.setConstraints(welcome, 0, 0);
        gridPane.getChildren().add(welcome);
        gridPane.setAlignment(Pos.CENTER);
        try {
            File file = new File("Data" + File.separator + "Styles" + File.separator + "Fonts" + File.separator + "KenneyBlocks.ttf");
            FileInputStream fileInputStream = new FileInputStream(file);
            welcome.setFont(Font.loadFont(fileInputStream, 100));

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        mainPane.setCenter(gridPane);
    }

    @Override
    public void show() {
        setPane();
        Scene scene = new Scene(mainPane ,1000 ,600);
        Menu.window.setScene(scene);
        Menu.window.show();
    }
}
