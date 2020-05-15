package ControllerTest;

import Controller.AccountManager;
import Controller.Database;
import Controller.SellerManager;
import Model.Product;
import Model.SellerAccount;
import org.junit.Assert;
import org.junit.Test;

public class SellerManagerTest {
    @Test
    public void sendAddProductRequestTest() throws Exception
    {
        SellerAccount sellerAccount = new SellerAccount("testSeller", "test", "test", "testing", "test@gmail.com", "021", 1000, "test");
        Database.addAllAccounts(sellerAccount);
        AccountManager.logIn("testSeller", "testing");
        String expected = "Your request registered.";
        String result = SellerManager.sendAddProductRequest("test", "test", true, 10, "test", "test", 1000);
        AccountManager.logOut();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void sendEditProductRequestTest1() throws Exception
    {
        SellerAccount sellerAccount = new SellerAccount("testSeller", "test", "test", "testing", "test@gmail.com", "021", 1000, "test");
        Product product = new Product("test", "test", "testSeller", true, 10, "test", "test", 1000);
        Database.addAllAccounts(sellerAccount);
        Database.addAllProduct(product);
        AccountManager.logIn("testSeller", "testing");
        String expected = "Your request registered.";
        String result = SellerManager.sendEditProductRequest(product.getProductId(), "Status", "test");
        Database.removeProduct(product);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void sendEditProductRequestTest2() throws Exception
    {
        SellerAccount sellerAccount = new SellerAccount("testSeller", "test", "test", "testing", "test@gmail.com", "021", 1000, "test");
        Product product = new Product("test", "test", "test", true, 10, "test", "test", 1000);
        Database.addAllAccounts(sellerAccount);
        Database.addAllProduct(product);
        AccountManager.logIn("testSeller", "testing");
        String expected = "You can't edit this product.";
        String result = SellerManager.sendEditProductRequest(product.getProductId(), "Status", "test");
        Database.removeProduct(product);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void sendDeleteProductTest1() throws Exception
    {
        SellerAccount sellerAccount = new SellerAccount("testSeller", "test", "test", "testing", "test@gmail.com", "021", 1000, "test");
        Product product = new Product("test", "test", "testSeller", true, 10, "test", "test", 1000);
        Database.addAllAccounts(sellerAccount);
        Database.addAllProduct(product);
        AccountManager.logIn("testSeller", "testing");
        String expected = "Product removed successfully";
        String result = SellerManager.deleteProduct(product.getProductId());
        Database.removeProduct(product);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void sendDeleteProductTest2() throws Exception
    {
        SellerAccount sellerAccount = new SellerAccount("testSeller", "test", "test", "testing", "test@gmail.com", "021", 1000, "test");
        Product product = new Product("test", "test", "test", true, 10, "test", "test", 1000);
        Database.addAllAccounts(sellerAccount);
        Database.addAllProduct(product);
        AccountManager.logIn("testSeller", "testing");
        String expected = "You can't remove this product.";
        String result = SellerManager.deleteProduct(product.getProductId());
        Database.removeProduct(product);

        Assert.assertEquals(expected, result);
    }


}

