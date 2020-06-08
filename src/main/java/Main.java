import Controller.Database;
import View.Menu.MainMenu;
import View.Menu.Menu;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {
    public static Stage window = new Stage();

    public static void main(String[] args){
        launch(args);
        //mainMenu.execute();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Menu.setScanner(new Scanner(System.in));
        Menu.setWindow(window);
        MainMenu mainMenu = new MainMenu();
        Database.initialize();
        mainMenu.show();

    }
}
