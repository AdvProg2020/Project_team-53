package Controller;

import Model.BuyerAccount;
import Model.Cart;
import Model.Discount;
import Model.Product;

public class BuyerManager {

    public static String showAllDiscounts(){
        BuyerAccount buyerAccount = (BuyerAccount) AccountManager.getLoggedInAccount();
        return buyerAccount.showAllDiscounts();
    }

    public static long showCostOfCart(){
        BuyerAccount buyerAccount = (BuyerAccount) AccountManager.getLoggedInAccount();
        return buyerAccount.getCart().getCost();
    }

    public static boolean canIncreaseProduct(int productId){
        BuyerAccount buyerAccount = (BuyerAccount) AccountManager.getLoggedInAccount();
        return buyerAccount.getCart().increaseProduct(productId);
    }

    public static boolean DecreaseProduct(int productId){
        BuyerAccount buyerAccount = (BuyerAccount) AccountManager.getLoggedInAccount();
        return buyerAccount.getCart().decreaseProduct(productId);
    }

    public static void addNewProductToCart(Product product){
        BuyerAccount buyerAccount = (BuyerAccount) AccountManager.getLoggedInAccount();
        buyerAccount.getCart().addToCart(product);
    }

    public static void addProductToCart(Product product){
        BuyerAccount buyerAccount = (BuyerAccount) AccountManager.getLoggedInAccount();
        Cart cart = buyerAccount.getCart();
        if (!cart.increaseProduct(product.getProductId()))
            addNewProductToCart(product);
    }

    public boolean canBuy(int discountId){
        BuyerAccount buyerAccount = (BuyerAccount) AccountManager.getLoggedInAccount();
        long cost = buyerAccount.getCart().getCost();
        //Todo: complete the conditions
        return true;
    }

    public void buy(int discountId){
        BuyerAccount buyerAccount = (BuyerAccount) AccountManager.getLoggedInAccount();
        long cost = buyerAccount.getCart().getCost();
        buyerAccount.setCredit((int) (buyerAccount.getCredit() - cost));
        // Todo: get the seller cost
        buyerAccount.setCart(new Cart(buyerAccount));
        if (buyerAccount.canUseDiscount(discountId)){
            buyerAccount.useDiscount(discountId);
            Discount discount = Database.getDiscountById(discountId);
            buyerAccount.setCredit(buyerAccount.getCredit() + (int)cost*discount.getPercent());
            // Todo: check up the line above
        }
    }

    public boolean pay(int discountId){
        if (!canBuy(discountId))
            return false;
        else {
            buy(discountId);
            return true;
        }
    }

}
