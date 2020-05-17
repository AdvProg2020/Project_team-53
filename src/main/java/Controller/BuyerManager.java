package Controller;

import Model.BuyerAccount;

public class BuyerManager {

    public String showAllDiscount(){
        BuyerAccount buyerAccount = (BuyerAccount) AccountManager.getLoggedInAccount();
        return buyerAccount.showAllDiscounts();
    }

    public long showCostOfCart(){
        BuyerAccount buyerAccount = (BuyerAccount) AccountManager.getLoggedInAccount();
        return buyerAccount.getCart().getCost();
    }


}
