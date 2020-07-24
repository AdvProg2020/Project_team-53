package View.Menu;

import Model.Account.Account;
import Model.Account.BuyerAccount;
import View.Menu.ProductsMenus.ProductsMenu;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
//import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.Socket;
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
   // protected static MediaPlayer song1;
   // protected static MediaPlayer song2;
    protected static Socket socket;
    protected static DataInputStream dataInputStream;
    protected static DataOutputStream dataOutputStream;
    protected static Account account;

    public static void setAccount(Account account) {
        Menu.account = account;
    }

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
            //song2.pause();
            //new MediaPlayer(new Media(new File("Data/Styles/music/song3.mp3").toURI().toString())).play();
            //song1.play();
            handleUser();
        });

        Button productButton = new Button("Products");
        productButton.getStyleClass().add("top-button");
        productButton.setMaxWidth(Double.MAX_VALUE);
        productButton.setOnAction(e -> {
            //song1.pause();
            //new MediaPlayer(new Media(new File("Data/Styles/music/song3.mp3").toURI().toString())).play();
            //song2.play();
            handleProduct();
        });

        Button auctionButton = new Button("Auctions");
        auctionButton.getStyleClass().add("top-button");
        auctionButton.setMaxWidth(Double.MAX_VALUE);
        auctionButton.setOnAction(e -> handleAuction());

        Button exitButton = new Button("exit");
        exitButton.getStyleClass().add("top-button");
        exitButton.setMaxWidth(Double.MAX_VALUE);
        exitButton.setOnAction(e -> {
            window.close();
            //song1.pause();
            //song2.pause();
        });


        mainButtons.getChildren().addAll(userButton, productButton, auctionButton, exitButton);

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

    public void handleAuction() {
        if (!(Menu.account instanceof BuyerAccount)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Process Fail");
            alert.setContentText("You have to login as Buyer first to see this page");

            alert.showAndWait();
            return;
        }
        new AuctionMenu(this).show();
    }

    public void show() {}

    public void execute(){ }

    public static void setSocket(Socket socket) {
        Menu.socket = socket;
    }

    public static void setDataInputStream(DataInputStream dataInputStream) {
        Menu.dataInputStream = dataInputStream;
    }

    public static void setDataOutputStream(DataOutputStream dataOutputStream) {
        Menu.dataOutputStream = dataOutputStream;
    }

    /*public static void setMusic()
    {
        Media media1 = new Media(new File("Data/Styles/music/song1.mp3").toURI().toString());
        song1 = new MediaPlayer(media1);
        song1.setCycleCount(MediaPlayer.INDEFINITE);
        song1.play();
        Media media2 = new Media(new File("Data/Styles/music/song2.mp3").toURI().toString());
        song2 = new MediaPlayer(media2);
        song2.setCycleCount(MediaPlayer.INDEFINITE);
    }*/
}
