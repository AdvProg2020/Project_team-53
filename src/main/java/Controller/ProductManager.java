package Controller;

import Model.BuyerAccount;
import Model.Product;
import Model.Score;

public class ProductManager {
    private static Product product;

    public static void setProduct(Product setTo) {
        product = setTo;
    }

    public static Product getProduct() {
        return product;
    }

    public static String giveScore(int score){
        BuyerAccount account = (BuyerAccount) AccountManager.getLoggedInAccount();
        if (!account.buyedProduct(product.getProductId())){
            return "You have to buy product to give score.";
        }
        product.giveScore(new Score(score , account.getUsername()));
        return "Your Score submitted.";
    }


}
