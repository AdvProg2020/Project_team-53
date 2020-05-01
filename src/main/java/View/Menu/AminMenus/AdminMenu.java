package View.Menu.AminMenus;

import View.Menu.Menu;
import View.Menu.PersonalInfoMenu;

public class AdminMenu extends Menu {

    public AdminMenu(Menu parentMenu) {
        super("Admin Menu", parentMenu);
        super.addToSubMenus(1, new PersonalInfoMenu(this));
    }


}
