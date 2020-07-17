package Model.Product;

import Model.Account.BuyerAccount;

import java.util.ArrayList;
import java.util.Date;

public class Auction {
    private ArrayList<BuyerAccount> allBuyers;
    private Product product;
    private long mostPrice;
    private BuyerAccount buyerWithMostPrice;
    private Date endDate;

    public Auction(Product product, Date endDate)
    {
        this.product = product;
        this.endDate = endDate;
        allBuyers = new ArrayList<>();
        mostPrice = 0;
    }

    public void joinAuction(BuyerAccount buyerAccount)
    {
        allBuyers.add(buyerAccount);
    }

    public boolean isAuctionAvailable()
    {
        Date date = new Date();
        return endDate.compareTo(date) > 0;
    }

    public boolean setMostPrice(long mostPrice, BuyerAccount buyerAccount)
    {
        if (!canSetPrice(buyerAccount))
            return false;
        if (mostPrice <=  this.mostPrice)
            return false;
        this.mostPrice = mostPrice;
        buyerWithMostPrice = buyerAccount;
        return true;
    }

    private boolean canSetPrice(BuyerAccount buyerAccount)
    {
        return true;
    }
}
