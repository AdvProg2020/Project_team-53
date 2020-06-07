import Controller.Database;
import View.Menu.MainMenu;
import View.Menu.Menu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {

    public static void main(String[] args){
        launch(args);
        Menu.setScanner(new Scanner(System.in));
        MainMenu mainMenu = new MainMenu();
        Database.initialize();
        mainMenu.show();
        mainMenu.execute();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage window = new Stage();
        Pane layout = new Pane();
        layout.getChildren().add(new Button("Hello"));
        Scene scene = new Scene(layout, 100, 100);
        window.setScene(scene);
        window.show();
    }
}
