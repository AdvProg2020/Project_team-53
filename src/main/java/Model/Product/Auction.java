package Model.Product;

import Model.Account.BuyerAccount;

import java.util.ArrayList;
import java.util.Date;

public class Auction extends Thread{
    private ArrayList<BuyerAccount> allBuyers;
    private Product product;
    private long mostPrice;
    private BuyerAccount buyerWithMostPrice;
    private Date endDate;
    private Date currentDate;

    public Auction(Product product, Date endDate)
    {
        this.product = product;
        this.endDate = endDate;
        allBuyers = new ArrayList<>();
        mostPrice = 0;
        currentDate = new Date();
        this.start();
    }

    public void joinAuction(BuyerAccount buyerAccount)
    {
        allBuyers.add(buyerAccount);
    }

    public boolean isAuctionAvailable()
    {
        currentDate = new Date();
        return endDate.compareTo(currentDate) > 0;
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

    @Override
    public void run() {
        while (true)
        {
            if (!isAuctionAvailable())
            {
                //todo:buy things
                break;
            }
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
