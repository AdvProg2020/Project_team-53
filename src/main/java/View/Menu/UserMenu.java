package View.Menu;

import Controller.AccountManager;
import Model.BuyerAccount;
import Model.SellerAccount;
import View.Menu.AdminMenus.AdminMenu;
import View.Menu.BuyerMenus.BuyerMenu;

public class UserMenu extends Menu {
    public UserMenu(Menu parentMenu) {
        super("User Menu", parentMenu);
    }

    @Override
    public void show()
    {
        if(AccountManager.getLoggedInAccount() instanceof BuyerAccount)
        {
            new BuyerMenu(this.parentMenu).show();
        }
        else if(AccountManager.getLoggedInAccount() instanceof SellerAccount)
        {
            //todo
        }
        else
        {
            new AdminMenu(this.parentMenu).show();
        }
    }

    @Override
    public void execute()
    {
        if(AccountManager.getLoggedInAccount() instanceof BuyerAccount)
        {
            new BuyerMenu(this.parentMenu).execute();
        }
        else if(AccountManager.getLoggedInAccount() instanceof SellerAccount)
        {
            //todo
        }
        else
        {
            new AdminMenu(this.parentMenu).execute();
        }
    }
}
