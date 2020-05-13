package Controller;

import Model.BuyerAccount;
import Model.Product;
import Model.Score;

public class ProductManager {
    private static Product product = null;

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

    public static double showAverageScore(){
        return product.getAverageScore();
    }

    public static String digest(){
        return product.digest();
    }

    public static String showFullInfo(){
        return product.showAllInfo();
    }

    public static String compare(int productId){
        Product secondPro = Database.getProductByID(productId);
        return product.compareWith(secondPro);
    }
}
