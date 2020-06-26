import Controller.Database;
import View.Menu.MainMenu;
import View.Menu.Menu;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage window = new Stage();

    public static void main(String[] args){
        Database.initialize();
        launch(args);
        Database.writeDataOnFile();
        //mainMenu.execute();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Menu.setWindow(window);
        MainMenu mainMenu = new MainMenu();
        mainMenu.show();
    }
}
