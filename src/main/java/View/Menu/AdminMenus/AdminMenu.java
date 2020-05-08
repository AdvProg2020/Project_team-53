package View.Menu.AdminMenus;

import View.Menu.Menu;
import View.Menu.PersonalInfoMenu;

import java.util.regex.Matcher;

public class AdminMenu extends Menu {

    public AdminMenu(Menu parentMenu) {
        super("Admin Menu", parentMenu);
        super.addToSubMenus(1, new PersonalInfoMenu(this));
        super.addToSubMenus(2,new MangeUsersMenu(this));
        super.addToSubMenus(3, new ManageAllProductsMenu(this));
        super.addToSubMenus(4, getCreateDiscountCodeMenu());
        super.addToSubMenus(5, new ViewDiscountCodeMenu(this));
        super.addToSubMenus(6, new ManageRequestsMenu(this));
        super.addToSubMenus(7, new ManageCategoriesMenu(this));
    }

    private Menu getCreateDiscountCodeMenu()
    {
        return new Menu("Create Discount Code Menu", this) {
            @Override
            public void show()
            {
                System.out.println("Please enter features\n(Enter back to return)");
            }
            @Override
            public void execute() {
                String input = scanner.nextLine();
                try
                {
                    Matcher matcher1 = getMatcher(input, "");
                    Matcher matcher2 = getMatcher(input, "^\\s*back\\s*$");
                    if(matcher2.find())
                    {
                        this.parentMenu.show();
                        this.parentMenu.execute();
                        return;
                    }
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
                this.execute();
            }
        };
    }


}
