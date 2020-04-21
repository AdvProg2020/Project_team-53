package View.Menu;

import java.util.HashMap;
import java.util.Scanner;

public abstract class Menu {

    private String name;
    protected Menu parentMenu;
    private HashMap<Integer, Menu> subMenus;
    protected static Scanner scanner;

    public Menu(String name, Menu parentMenu)
    {
        this.name = name;
        this.parentMenu = parentMenu;
        subMenus = new HashMap<Integer, Menu>();
    }

    public static void setScanner(Scanner scanner) {
        Menu.scanner = scanner;
    }

    public void addToSubMenus(int menuNumber, Menu menu)
    {
        subMenus.put(menuNumber, menu);
    }

    public void show()
    {
        System.out.println(this.name + ":");
        for (Integer menuNumber : subMenus.keySet()) {
            System.out.println(menuNumber + ") " + subMenus.get(menuNumber).name);
        }
        if(parentMenu == null)
        {
            System.out.println(Integer.toString(this.subMenus.size() + 1) + ") exit");
        }
        else{
            System.out.println(Integer.toString(this.subMenus.size() + 1) + ") back");
        }
    }

    public void execute(){
        int input;
        try {
            input = Integer.parseInt(scanner.nextLine());
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