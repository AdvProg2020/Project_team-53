package View.Menu;

import Controller.AccountManager;
import Controller.AdminManager;
import Model.BuyerAccount;
import Model.SellerAccount;
import View.Menu.AdminMenus.AdminMenu;
import View.Menu.BuyerMenus.BuyerMenu;
import View.Menu.SellerMenus.SellerMenu;

public class UserMenu extends Menu {
    public UserMenu(Menu parentMenu) {
        super("User Menu", parentMenu);
    }

    BuyerMenu buyerMenu = new BuyerMenu(this.parentMenu);
    SellerMenu sellerMenu = new SellerMenu(this.parentMenu);
    AdminMenu adminMenu = new AdminMenu(this.parentMenu);

    @Override
    public void show()
    {
        if(AccountManager.getLoggedInAccount() instanceof BuyerAccount)
        {
            buyerMenu.show();
        }
        else if(AccountManager.getLoggedInAccount() instanceof SellerAccount)
        {
            sellerMenu.show();
        }
        else
        {
            adminMenu.show();
        }
    }

    @Override
    public void execute()
    {
        if(AccountManager.getLoggedInAccount() instanceof BuyerAccount)
        {
            buyerMenu.execute();
        }
        else if(AccountManager.getLoggedInAccount() instanceof SellerAccount)
        {
            sellerMenu.execute();
        }
        else
        {
            adminMenu.execute();
        }
    }
}
