package View.Menu.BuyerMenus;

import Controller.AccountManager;
import Controller.BuyerManager;
import View.Menu.*;

import java.util.regex.Matcher;

public class BuyerMenu extends Menu {

    public BuyerMenu(Menu parentMenu) {
        super("Buyer Menu", parentMenu);
        super.addToSubMenus(1,new PersonalInfoMenu(this));
        super.addToSubMenus(2, new ViewCartMenu(this));
        super.addToSubMenus(3, new ViewOrdersMenu(this));
        super.addToSubMenus(4, this.getViewBalanceMenu());
        super.addToSubMenus(5, this.getViewDiscountCodesMenu());
        super.addToSubMenus(6, this.getLogoutMenu());
    }

    private Menu getViewBalanceMenu()
    {
        return new Menu("View Balance Menu", this) {
            @Override
            public void show() {
                System.out.println("Your Balance Is :\n(Enter back to return)");
            }

            @Override
            public void execute(){
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

    private Menu getViewDiscountCodesMenu()
    {
        return new Menu("View Discount Menu", this) {
            @Override
            public void show() {
                System.out.println("Your discount codes are :\n(Enter back to return)");
            }
            @Override
            public void execute()
            {
                System.out.println(BuyerManager.showAllDiscounts());
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

    private Menu getLogoutMenu()
    {
        return new Menu("Logout Menu", this) {
            @Override
            public void show() {
            }

            @Override
            public void execute()
            {
                System.out.println(AccountManager.logOut());
                this.parentMenu.show();
                this.parentMenu.execute();
            }
        };
    }
}
