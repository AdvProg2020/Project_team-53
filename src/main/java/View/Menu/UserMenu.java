package View.Menu;

import Controller.AccountManager;
import Model.Account.AdminAccount;
import Model.Account.BuyerAccount;
import Model.Account.SellerAccount;
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
        else if(AccountManager.getLoggedInAccount() instanceof AdminAccount)
        {
            adminMenu.show();
        }
        else if(AccountManager.getLoggedInAccount() == null)
        {
            System.out.println("You have to login first");
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
        else if(AccountManager.getLoggedInAccount() instanceof AdminAccount)
        {
            adminMenu.execute();
        }
        else if(AccountManager.getLoggedInAccount() == null)
        {
            super.parentMenu.execute();
        }
    }
}
