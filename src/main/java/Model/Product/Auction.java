package Model.Product;

import Controller.BuyerManager;
import Model.Account.BuyerAccount;

import java.util.ArrayList;
import java.util.Date;

public class Auction extends Thread{
    private int numberOfAllAuctions = 1;
    private int auctionID;
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
        this.auctionID = numberOfAllAuctions;
        numberOfAllAuctions++;
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
        if (!canSetPrice(buyerAccount, mostPrice))
            return false;
        if (mostPrice <=  this.mostPrice)
            return false;
        this.mostPrice = mostPrice;
        buyerWithMostPrice = buyerAccount;
        return true;
    }

    private boolean canSetPrice(BuyerAccount buyerAccount, long price)
    {
        return buyerAccount.getCredit() > price;
    }

    @Override
    public void run() {
        while (isAuctionAvailable()) {
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        BuyerManager buyerManager = new BuyerManager();
        buyerManager.addProductToCart(product, buyerWithMostPrice);
        buyerManager.pay(-1, buyerWithMostPrice, "");
    }

    public Product getProduct() {
        return product;
    }

    public boolean containBuyer(BuyerAccount buyerAccount)
    {
        return allBuyers.contains(buyerAccount);
    }

    public int getAuctionID() {
        return auctionID;
    }
}
