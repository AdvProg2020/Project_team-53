package View.Menu.SellerMenus;

import Controller.AdminManager;
import View.Menu.Menu;
import View.Menu.PersonalInfoMenu;

import javax.lang.model.element.PackageElement;
import java.util.regex.Matcher;

public class SellerMenu extends Menu {

    public SellerMenu(Menu parentMenu) {
        super("Seller Menu", parentMenu);
        super.addToSubMenus(1, new PersonalInfoMenu(this));
        super.addToSubMenus(2, this.getViewCompanyInformMenu());
        super.addToSubMenus(3, this.getViewSalesHistoryMenu());
    }

    private Menu getViewCompanyInformMenu()
    {
        return new Menu("View Company Information Menu", this) {
            @Override
            public void show() {
                System.out.println("Company information is:\n(Enter back to return)");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                try
                {
                    Matcher matcher2 = getMatcher(input, "^\\s*back\\s*$");
                    if(matcher2.find())
                    {
                        this.parentMenu.show();
                        this.parentMenu.execute();
                        return;
                    }
                    else
                        throw new Exception("Invalid Input");
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
                this.execute();
            }
        };
    }

    private Menu getViewSalesHistoryMenu()
    {
        return new Menu("View Sales History Menu", this) {
            @Override
            public void show() {
                System.out.println("Sales history is:\n(Enter back to return");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                try
                {
                    Matcher matcher2 = getMatcher(input, "^\\s*back\\s*$");
                    if(matcher2.find())
                    {
                        this.parentMenu.show();
                        this.parentMenu.execute();
                        return;
                    }
                    else
                        throw new Exception("Invalid Input");
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