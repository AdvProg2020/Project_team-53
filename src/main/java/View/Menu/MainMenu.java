package View.Menu;

import View.Menu.OffMenus.OffMenu;
import View.Menu.ProductsMenus.ProductsMenu;

public class MainMenu extends Menu{

    public MainMenu() {
        super("Main Menu", null);
        super.addToSubMenus(1, new LoginMenu(this));
        super.addToSubMenus(2, new UserMenu(this));
        super.addToSubMenus(3, new ProductsMenu(this));
        super.addToSubMenus(4, new OffMenu(this));
    }
}
