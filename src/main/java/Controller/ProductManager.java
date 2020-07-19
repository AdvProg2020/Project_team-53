package Controller;

import Model.Account.Account;
import Model.Account.BuyerAccount;
import Model.Product.CommentAndScore.Comment;
import Model.Product.CommentAndScore.Score;
import Model.Product.Product;

public class ProductManager {
    private Product product = null;

    public void setProduct(Product setTo) {
        product = setTo;
    }

    public Product getProduct() {
        return product;
    }

    public String giveScore(int score, Account account1){
        BuyerAccount account = (BuyerAccount) account1;
        if (!account.buyerProduct(product.getProductId())){
            return "You have to buy product to give score.";
        }
        product.giveScore(new Score(score , account.getUsername()));
        return "Your Score submitted.";
    }

    public double showAverageScore(){
        return product.getAverageScore();
    }

    public String digest(){
        return product.digest();
    }

    public String showFullInfo(){
        return product.showAllInfo();
    }

    public String compare(int productId){
        Product secondPro = Database.getProductByID(productId);
        return product.compareWith(secondPro);
    }

    public String giveComment(String title, String content, Account account){
        BuyerAccount buyerAccount = (BuyerAccount) account;
        product.addComment(new Comment(title, content, buyerAccount.getUsername(), buyerAccount.buyerProduct(product.getProductId())));
        return "Your comment registered.";
    }

}
