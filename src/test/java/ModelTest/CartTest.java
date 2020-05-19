package ModelTest;

import Controller.Database;
import Model.Account.Account;
import Model.Cart;
import Model.Product;
import org.junit.Assert;
import org.junit.Test;

public class CartTest {

    @Test
    public void getCostTest()
    {
        Database.initialize();
        Account account = Database.getAccountByUsername("parham");
        Cart cart = new Cart(account);
        Product product =new Product("product", "product", "product", true, 10, "product", "pro", 1000);
        Database.addAllProduct(product);
        cart.addToCart(product);
        long expected = 1000;
        long result = cart.getCost();

        Assert.assertEquals(expected, result);
        Database.removeProduct(product);
    }

    @Test
    public void getAllProductTest()
    {
        Database.initialize();
        Account account = Database.getAccountByUsername("parham");
        Cart cart = new Cart(account);
        Product product = new Product("product", "product", "product", true, 10, "product", "pro", 1000);
        Database.addAllProduct(product);
        cart.addToCart(product);
        String expected = "Name : " + product.getName() + "_ID : " + product.getProductId() + '\n';
        String result = cart.getAllProducts();

        Assert.assertEquals(expected, result);
        Database.removeProduct(product);
    }

    @Test
    public void increaseProductTest1()
    {
        Database.initialize();
        Account account = Database.getAccountByUsername("parham");
        Cart cart = new Cart(account);
        Product product = new Product("product", "product", "product", true, 1, "product", "pro", 1000);
        Database.addAllProduct(product);
        cart.addToCart(product);

        Assert.assertFalse(cart.increaseProduct(product.getProductId()));
        Database.removeProduct(product);
    }

    @Test
    public void increaseProductTest2()
    {
        Database.initialize();
        Account account = Database.getAccountByUsername("parham");
        Cart cart = new Cart(account);
        Product product = new Product("product", "product", "product", true, 1, "product", "pro", 1000);
        Database.addAllProduct(product);
        cart.addToCart(product);

        Assert.assertFalse(cart.increaseProduct(-1));
        Database.removeProduct(product);
    }

    @Test
    public void increaseProductTest3()
    {
        Database.initialize();
        Account account = Database.getAccountByUsername("parham");
        Cart cart = new Cart(account);
        Product product = new Product("product", "product", "product", true, 2, "product", "pro", 1000);
        Database.addAllProduct(product);
        cart.addToCart(product);

        Assert.assertTrue(cart.increaseProduct(product.getProductId()));
        Database.removeProduct(product);
    }

    @Test
    public void decreaseProductTest1()
    {
        Database.initialize();
        Account account = Database.getAccountByUsername("parham");
        Cart cart = new Cart(account);
        Product product = new Product("product", "product", "product", true, 2, "product", "pro", 1000);
        Database.addAllProduct(product);
        cart.addToCart(product);

        Assert.assertFalse(cart.decreaseProduct(-1));
        Database.removeProduct(product);
    }

    @Test
    public void decreaseProductTest2()
    {
        Database.initialize();
        Account account = Database.getAccountByUsername("parham");
        Cart cart = new Cart(account);
        Product product = new Product("product", "product", "product", true, 2, "product", "pro", 1000);
        Database.addAllProduct(product);
        cart.addToCart(product);

        Assert.assertTrue(cart.decreaseProduct(product.getProductId()));
        Database.removeProduct(product);
    }

    @Test
    public void getMuchOfProductIDTest1()
    {
        Database.initialize();
        Account account = Database.getAccountByUsername("parham");
        Cart cart = new Cart(account);
        Product product = new Product("product", "product", "product", true, 2, "product", "pro", 1000);
        Database.addAllProduct(product);
        cart.addToCart(product);
        int result = cart.getMuchOfProductID(product.getProductId());
        int expected = 1;

        Assert.assertEquals(expected, result);
        Database.removeProduct(product);
    }

    @Test
    public void getMuchOfProductIDTest2()
    {
        Database.initialize();
        Account account = Database.getAccountByUsername("parham");
        Cart cart = new Cart(account);
        int result = cart.getMuchOfProductID(-1);
        int expected = 0;

        Assert.assertEquals(expected, result);
    }
}
