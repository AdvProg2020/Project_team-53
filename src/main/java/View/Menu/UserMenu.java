package View.Menu;

import Model.Account.Account;
import Model.Account.AdminAccount;
import Model.Account.BuyerAccount;
import Model.Account.SellerAccount;
import View.Menu.AdminMenus.AdminMenu;
import View.Menu.BuyerMenus.BuyerMenu;
import View.Menu.SellerMenus.SellerMenu;

public class UserMenu extends Menu {
    BuyerMenu buyerMenu = new BuyerMenu(this.parentMenu);
    SellerMenu sellerMenu = new SellerMenu(this.parentMenu);
    AdminMenu adminMenu = new AdminMenu(this.parentMenu);
    LoginMenu loginMenu = new LoginMenu(this.parentMenu);
    Account account;
    public UserMenu(Menu parentMenu) {
        super("User Menu", parentMenu);
    }

    @Override
    public void show()
    {
        if(Menu.account instanceof BuyerAccount)
        {
            buyerMenu.show();
        }
        else if(Menu.account instanceof SellerAccount)
        {
            sellerMenu.show();
        }
        else if(Menu.account instanceof AdminAccount)
        {
            adminMenu.show();
        }
        else if(Menu.account == null)
        {
            loginMenu.show();
        }
    }
}
