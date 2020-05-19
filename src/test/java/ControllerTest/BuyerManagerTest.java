package ControllerTest;

import Controller.AccountManager;
import Controller.BuyerManager;
import Controller.Database;
import Model.Account.BuyerAccount;
import Model.Cart;
import Model.Product.Product;
import org.junit.Assert;
import org.junit.Test;

public class BuyerManagerTest {

    @Test
    public void showAllDiscountTest() throws Exception
    {
        BuyerAccount buyerAccount = new BuyerAccount("test", "test", "test", "testing", "test@gmail.com", "021", 1000);
        Database.addAllAccounts(buyerAccount);
        AccountManager.logIn("test", "testing");
        String expected = "";
        String result = BuyerManager.showAllDiscounts();
        AccountManager.logOut();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void showCostOfCartTest()throws Exception
    {
        BuyerAccount buyerAccount = new BuyerAccount("test", "test", "test", "testing", "test@gmail.com", "021", 1000);
        Database.addAllAccounts(buyerAccount);
        AccountManager.logIn("test", "testing");
        long expected = 0;
        long result = BuyerManager.showCostOfCart();
        AccountManager.logOut();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void canIncreaseProductTest() throws  Exception
    {
        BuyerAccount buyerAccount = new BuyerAccount("test", "test", "test", "testing", "test@gmail.com", "021", 1000);
        Database.addAllAccounts(buyerAccount);
        AccountManager.logIn("test", "testing");
        Cart cart = new Cart(buyerAccount);
        Product product = new Product("product", "product", "product", true, 10, "product", "pro", 1000);
        Database.addAllProduct(product);
        cart.addToCart(product);

        Assert.assertTrue(BuyerManager.canIncreaseProduct(product.getProductId()));
        AccountManager.logOut();
        Database.removeProduct(product);
        Database.removeAccount(buyerAccount);
    }

    @Test
    public void canDecreaseProductTest1() throws  Exception
    {
        BuyerAccount buyerAccount = new BuyerAccount("test", "test", "test", "testing", "test@gmail.com", "021", 1000);
        Database.addAllAccounts(buyerAccount);
        AccountManager.logIn("test", "testing");
        Cart cart = new Cart(buyerAccount);
        buyerAccount.setCart(cart);
        Product product = new Product("product", "product", "product", true, 10, "product", "pro", 1000);
        Database.addAllProduct(product);
        cart.addToCart(product);
        String expected = "product eliminated successfully";
        String result = BuyerManager.DecreaseProduct(product.getProductId());

        Assert.assertEquals(expected, result);
        AccountManager.logOut();
        Database.removeProduct(product);
        Database.removeAccount(buyerAccount);
    }

    @Test
    public void canDecreaseProductTest2() throws  Exception
    {
        BuyerAccount buyerAccount = new BuyerAccount("test", "test", "test", "testing", "test@gmail.com", "021", 1000);
        Database.addAllAccounts(buyerAccount);
        AccountManager.logIn("test", "testing");
        Cart cart = new Cart(buyerAccount);
        buyerAccount.setCart(cart);
        Product product = new Product("product", "product", "product", true, 10, "product", "pro", 1000);
        Database.addAllProduct(product);
        String expected = "you haven't choosen this product yet";
        String result = BuyerManager.DecreaseProduct(product.getProductId());

        Assert.assertEquals(expected, result);
        AccountManager.logOut();
        Database.removeProduct(product);
        Database.removeAccount(buyerAccount);
    }

}