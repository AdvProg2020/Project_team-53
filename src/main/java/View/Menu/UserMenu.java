package View.Menu;

import Controller.AccountManager;
import Model.Account;
import Model.BuyerAccount;
import Model.SellerAccount;

import java.awt.image.PackedColorModel;

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
            //todo
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
            //todo
        }
    }
}
