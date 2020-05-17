package Controller;

import Model.BuyerAccount;
import Model.Cart;
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


}
