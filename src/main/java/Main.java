import Controller.Database;
import View.Menu.MainMenu;
import View.Menu.Menu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Menu.setScanner(new Scanner(System.in));
        MainMenu mainMenu = new MainMenu();
        Database.initialize();
        mainMenu.show();
        mainMenu.execute();
    }
}
