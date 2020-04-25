package View.Menu;

public class MainMenu extends Menu{

    public MainMenu() {
        super("Main Menu", null);
        super.addToSubMenus(1, new LoginMenu(this));
        super.addToSubMenus(2, new UserMenu(this));
    }
}
