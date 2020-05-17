package Controller;

import Model.BuyerAccount;
import Model.Cart;
import Model.Product;

public class BuyerManager {

    public String showAllDiscounts(){
        BuyerAccount buyerAccount = (BuyerAccount) AccountManager.getLoggedInAccount();
        return buyerAccount.showAllDiscounts();
    }

    public long showCostOfCart(){
        BuyerAccount buyerAccount = (BuyerAccount) AccountManager.getLoggedInAccount();
        return buyerAccount.getCart().getCost();
    }

    public boolean canIncreaseProduct(int productId){
        BuyerAccount buyerAccount = (BuyerAccount) AccountManager.getLoggedInAccount();
        return buyerAccount.getCart().increaseProduct(productId);
    }

    public boolean DecreaseProduct(int productId){
        BuyerAccount buyerAccount = (BuyerAccount) AccountManager.getLoggedInAccount();
        return buyerAccount.getCart().decreaseProduct(productId);
    }

    public void addNewProductToCart(Product product){
        BuyerAccount buyerAccount = (BuyerAccount) AccountManager.getLoggedInAccount();
        buyerAccount.getCart().addToCart(product);
    }

    public void addProductToCart(Product product){
        BuyerAccount buyerAccount = (BuyerAccount) AccountManager.getLoggedInAccount();
        Cart cart = buyerAccount.getCart();
        if (!cart.increaseProduct(product.getProductId()))
            addNewProductToCart(product);
    }


}
