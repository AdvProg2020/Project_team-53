package Controller;

import Model.Account.Account;
import Model.Account.BuyerAccount;
import Model.Account.SellerAccount;
import Model.Cart;
import Model.Log.Log;
import Model.Product.Auction;
import Model.Product.DiscountAndOff.Discount;
import Model.Product.Product;

import java.util.Objects;

public class BuyerManager {

    public String showAllDiscounts(Account account) {
        BuyerAccount buyerAccount = (BuyerAccount) account;
        return buyerAccount.showAllDiscounts();
    }

    public long showCostOfCart(Account account) {
        BuyerAccount buyerAccount = (BuyerAccount) account;
        return buyerAccount.getCart().getCost();
    }

    public boolean canIncreaseProduct(int productId, Account account) {
        BuyerAccount buyerAccount = (BuyerAccount) account;
        return buyerAccount.getCart().increaseProduct(productId);
    }

    public String decreaseProduct(int productId, Account account) {
        BuyerAccount buyerAccount = (BuyerAccount) account;
        if (!buyerAccount.getCart().decreaseProduct(productId))
            return "you haven't chosen this product yet";
        Database.getProductByID(productId).setNumber(Database.getProductByID(productId).getNumber() + 1);
        return "product eliminated successfully";
    }

    public void addNewProductToCart(Product product, Account account) {
        BuyerAccount buyerAccount = (BuyerAccount) account;
        buyerAccount.getCart().addToCart(product);
    }

    public String addProductToCart(Product product, Account account) {
        if (product.getNumber() < 1 || !product.isAvailable())
            return "unfortunately we don't have this product now";
        BuyerAccount buyerAccount = (BuyerAccount) account;
        Cart cart = buyerAccount.getCart();
        if (!cart.increaseProduct(product.getProductId()))
            addNewProductToCart(product, account);
        product.setNumber(product.getNumber() - 1);
        return "product added to cart";
    }

    public boolean canBuy(int discountId, Account account) {
        BuyerAccount buyerAccount = (BuyerAccount) account;
        long cost = buyerAccount.getCart().getCost();
        if (buyerAccount.canUseDiscount(discountId))
            cost -= Math.min(cost * Database.getDiscountById(discountId).getPercent() / 100, Database.getDiscountById(discountId).getMaxValue());
        if (cost > buyerAccount.getCredit())
            return false;
        //Todo: complete the conditions
        return true;//just for make ok compile error
    }

    public void buy(int discountId, Account account, String addressOfBuyer) {
        BuyerAccount buyerAccount = (BuyerAccount) account;
        long cost = buyerAccount.getCart().getCost();
        buyerAccount.setCredit((int) (buyerAccount.getCredit() - cost));
        payToSeller(discountId, account, addressOfBuyer);
        // Todo: get the seller cost
        if (buyerAccount.canUseDiscount(discountId)) {
            buyerAccount.useDiscount(discountId);
            if (discountId != -1) {
                Discount discount = Database.getDiscountById(discountId);
                // max value
                buyerAccount.setCredit(buyerAccount.getCredit() + Math.min((int) cost * discount.getPercent() / 100, discount.getMaxValue()));
                // Todo: check up the line above
            }
        }
        buyerAccount.setCart(new Cart());
    }

    public String showCart(Account account) {
        BuyerAccount buyerAccount = (BuyerAccount) account;
        return buyerAccount.getCart().showCart();
    }

    public String pay(int discountId, Account account, String addressOfBuyer) {
        if (Database.getDiscountById(discountId) == null && discountId != -1)
            return " your discount is not valid";
        if (discountId!=-1 && !((BuyerAccount) account).canUseDiscount(discountId))
            return "You can't use this discount";
        if (!canBuy(discountId, account))
            return "Not enough money";
        else {
            buy(discountId,account, addressOfBuyer);
            return "product bought successfully";
        }
    }

    public void payToSeller(int discountId, Account account, String addressOfBuyer) {
        BuyerAccount buyerAccount = (BuyerAccount) account;
        int discountValue = 0;
        if (buyerAccount.canUseDiscount(discountId))
            discountValue = Database.getDiscountById(discountId).getPercent();
        if (discountId == -1)
            discountValue = 0;
        for (Integer productId : buyerAccount.getCart().getProductsID()) {
            Product product = Database.getProductByID(productId);
            SellerAccount sellerAccount = (SellerAccount) Database.getAccountByUsername(product.getSellerUsername());
            sellerAccount.setCredit(sellerAccount.getCredit() + product.getPrice() * buyerAccount.getCart().getMuchOfProductID(productId));
            int maxValue = product.getPrice();
            int offValue = 0;
            if (product.doesHaveOff()) {
                maxValue = product.getOff().getMaxValue();
                offValue = product.getPrice() * product.getOff().getPercent() / 100;
                sellerAccount.setCredit(sellerAccount.getCredit() - Math.min(offValue, maxValue) * buyerAccount.getCart().getMuchOfProductID(productId));
            }
            Log.addLog(buyerAccount.getUsername(), product.getSellerUsername(), product.getPrice(), productId, Math.min(offValue, maxValue), discountValue * product.getPrice() / 100, addressOfBuyer, product.doesHasFile());
        }
    }

    public String bankPay(Account account , int discountId , String address , String bankUsername , String bankPassword , String bankID){
        if (Database.getDiscountById(discountId) == null && discountId != -1)
            return " your discount is not valid";
        if (discountId!=-1 && !((BuyerAccount) account).canUseDiscount(discountId))
            return "You can't use this discount";
        else {
            String res = bankBuy(discountId,account, address);
            if (res.equals("done successfully"))
                return "product bought successfully";
            return res;
        }
    }

    private String bankBuy(int discountId, Account account, String address) {
        return "";
    }

    public void joinAuction(Auction auction, Account account){
        auction.joinAuction((BuyerAccount) account);
    }

    public String setPriceForAuction(int auctionID, int price, Account account)
    {
        Auction auction = Database.getAuctionByID(auctionID);
        if (!Objects.requireNonNull(auction).setMostPrice(price, (BuyerAccount) account))
            return "Fail to set price";
        return "Your price submitted";
    }
}
