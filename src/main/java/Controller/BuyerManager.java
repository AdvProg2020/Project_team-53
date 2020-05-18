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

    public static String DecreaseProduct(int productId){
        BuyerAccount buyerAccount = (BuyerAccount) AccountManager.getLoggedInAccount();
        if (!buyerAccount.getCart().decreaseProduct(productId))
            return "you haven't choosen this product yet";
        Database.getProductByID(productId).setNumber(Database.getProductByID(productId).getNumber() + 1);
        return "product eliminated successfully";
    }

    public static void addNewProductToCart(Product product){
        BuyerAccount buyerAccount = (BuyerAccount) AccountManager.getLoggedInAccount();
        buyerAccount.getCart().addToCart(product);
    }

    public static String addProductToCart(Product product){
        if (product.getNumber() < 1)
            return "unfortunately we don't have this product now";
        product.setNumber(product.getNumber() - 1);
        BuyerAccount buyerAccount = (BuyerAccount) AccountManager.getLoggedInAccount();
        Cart cart = buyerAccount.getCart();
        if (!cart.increaseProduct(product.getProductId()))
            addNewProductToCart(product);
        return "product added to cart";
    }

    public boolean canBuy(int discountId){
        BuyerAccount buyerAccount = (BuyerAccount) AccountManager.getLoggedInAccount();
        long cost = buyerAccount.getCart().getCost();
        //Todo: complete the conditions
        return true;//just for make ok compile error
    }

    public void buy(int discountId){
        BuyerAccount buyerAccount = (BuyerAccount) AccountManager.getLoggedInAccount();
        long cost = buyerAccount.getCart().getCost();
        buyerAccount.setCredit((int) (buyerAccount.getCredit() - cost));
        // Todo: get the seller cost
        if (buyerAccount.canUseDiscount(discountId)){
            buyerAccount.useDiscount(discountId);
            Discount discount = Database.getDiscountById(discountId);
            buyerAccount.setCredit(buyerAccount.getCredit() + (int)cost*discount.getPercent());
            // Todo: check up the line above
        }
        buyerAccount.setCart(new Cart(buyerAccount));
    }

    public String pay(int discountId){
        if (!canBuy(discountId))
            return "there is a problem in process";
        else {
            buy(discountId);
            return "product bought successfully";
        }
    }

}
