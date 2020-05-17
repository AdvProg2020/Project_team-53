package View.Menu.AdminMenus;

import Controller.AdminManager;
import View.Menu.Menu;
import View.Menu.PersonalInfoMenu;

import java.util.ArrayList;
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
                System.out.println("Please enter max value(with numbers), percent(without % sign), start date, " +
                        "end date(both in format YYYY-MM-DDhh:mm) and how much it can use(with numbers) in first line and " +
                        "user names in second line\n(Enter back to return)");
            }
            @Override
            public void execute() {
                String input = scanner.nextLine();
                String name = scanner.nextLine();
                try
                {
                    Matcher matcher1 = getMatcher(input, "^\\s*(\\d+)\\s+(\\d+)\\s+(\\S+)\\s+(\\S+)\\s+(\\d+)\\s*$");
                    Matcher matcher2 = getMatcher(input, "^\\s*back\\s*$");
                    Matcher matcher3 = getMatcher(name, "\\s*(\\S+)\\s*");
                    Matcher matcher4 = getMatcher(name, "^\\s*back\\s*$");
                    if(matcher2.find() || matcher4.find())
                    {
                        this.parentMenu.show();
                        this.parentMenu.execute();
                        return;
                    }
                    else if(!matcher1.find())
                    {
                        throw new Exception("invalid input");
                    }
                    ArrayList<String> allNames = new ArrayList<>();
                    while (matcher3.find())
                    {
                        String username = matcher3.group();
                        username = username.trim();
                        allNames.add(username);
                    }
                    if(allNames.isEmpty())
                    {
                        throw new Exception("invalid input");
                    }
                    System.out.println(AdminManager.addNewDiscount(Integer.parseInt(matcher1.group(1)), Integer.parseInt(matcher1.group(2)), matcher1.group(3), matcher1.group(4), Integer.parseInt(matcher1.group(5)), allNames));
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
