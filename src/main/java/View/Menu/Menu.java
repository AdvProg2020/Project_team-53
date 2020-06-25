package View.Menu;

import Controller.Database;
import View.Menu.ProductsMenus.ProductsMenu;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Menu{

    private String name;
    protected Menu parentMenu;
    private HashMap<Integer, Menu> subMenus;
    protected static Scanner scanner;
    protected BorderPane mainPane = new BorderPane();
    protected static Stage window;
    protected double width;
    protected double height;

    protected static Matcher getMatcher(String input, String regex)
    {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }

    public Menu(String name, Menu parentMenu)
    {
        this.name = name;
        this.parentMenu = parentMenu;
        subMenus = new HashMap<Integer, Menu>();
        width = 1000;
        height = 600;
    }

    public static void setScanner(Scanner scanner) {
        Menu.scanner = scanner;
    }

    public static void setWindow(Stage window) {
        Menu.window = window;
    }

    public void addToSubMenus(int menuNumber, Menu menu)
    {
        subMenus.put(menuNumber, menu);
    }

    public void setPane(){
        window.setResizable(false);
        mainPane = new BorderPane();
        mainPane.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());

        HBox mainButtons = new HBox();
        mainButtons.setSpacing(20);
        mainButtons.setAlignment(Pos.CENTER);

        Button userButton = new Button("User");
        userButton.getStyleClass().add("top-button");
        userButton.setMaxWidth(Double.MAX_VALUE);
        userButton.setOnAction(e -> {
            handleUser();
        });
        Button productButton = new Button("Products");
        productButton.getStyleClass().add("top-button");
        productButton.setMaxWidth(Double.MAX_VALUE);
        productButton.setOnAction(e -> {
            handleProduct();
        });


        Button exitButton = new Button("exit");
        exitButton.getStyleClass().add("top-button");
        exitButton.setMaxWidth(Double.MAX_VALUE);


        mainButtons.getChildren().addAll(userButton, productButton, exitButton);

        mainPane.setTop(mainButtons);
    }

    public HashMap<Integer, Menu> getSubMenus() {
        return subMenus;
    }

    protected void handleProduct(){
        ProductsMenu productsMenu = new ProductsMenu(this);
        productsMenu.show();
    }

    public void handleUser()
    {
        UserMenu userMenu = new UserMenu(this);
        userMenu.show();
    }

    public void show()
    {
    }

    public void execute(){
        int input;
        try {
            String inputInString = scanner.nextLine();
            input = Integer.parseInt(inputInString);
            if((input > this.subMenus.size() + 1) || (input < 1))
            {
                throw new Exception("invalid input");
            }
        }
        catch (Exception e){
            System.out.println("your input is invalid");
            this.execute();
            return;
        }
        if(input == this.subMenus.size() + 1)
        {
            if(this.parentMenu == null)
            {
                Database.writeDataOnFile();
                System.exit(1);
            }
            else {
                this.parentMenu.show();
                this.parentMenu.execute();
            }
        }
        else{
            this.subMenus.get(input).show();
            this.subMenus.get(input).execute();
        }
    }
}
