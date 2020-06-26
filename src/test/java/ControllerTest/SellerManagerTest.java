package ControllerTest;

import Controller.AccountManager;
import Controller.Database;
import Controller.SellerManager;
import Model.Product.DiscountAndOff.Off;
import Model.Product.Product;
import Model.Account.SellerAccount;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

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
        Database.removeAccount(sellerAccount);

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
        String expected = "Request send";
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
        String expected = "Request send";
        String result = SellerManager.deleteProduct(product.getProductId());
        Database.removeProduct(product);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void editOffTest1()
    {
        String expected = "no such off";
        String result = SellerManager.editOff(-1, "percent", "10");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void editOffTest2()
    {
        Database.initialize();
        Off off = new Off(1000, 10, "2018-07-19_12:54:00", "2019-07-19_12:54:00", "test", new ArrayList<Integer>());
        Database.addAllOff(off);
        AccountManager.logIn("Seller", "Seller");
        String expected = "You can't edit this off";
        String result = SellerManager.editOff(off.getOffId(), "percent", "10");

        Assert.assertEquals(expected, result);
        AccountManager.logOut();
    }

    @Test
    public void editOffTest3()
    {
        Database.initialize();
        Off off = new Off(1000, 10, "2018-07-19_12:54:00", "2019-07-19_12:54:00", "Seller", new ArrayList<Integer>());
        Database.addAllOff(off);
        AccountManager.logIn("Seller", "Seller");
        String expected = "Your request registered.";
        String result = SellerManager.editOff(off.getOffId(), "percent", "10");

        Assert.assertEquals(expected, result);
        AccountManager.logOut();
    }

    @Test
    public void addOffTest1()
    {
        Database.initialize();
        AccountManager.logIn("Seller", "Seller");
        Product product = new Product("test", "test", "test", true, 10, "test", "test", 1000);
        Database.addAllProduct(product);
        ArrayList<Integer> products = new ArrayList<Integer>();
        products.add(product.getProductId());
        String expected = "You can add off only on your products";
        String result = SellerManager.addNewOff(100, 10, "2018-07-19_12:54:00", "2019-07-19_12:54:00",products);

        Assert.assertEquals(expected, result);
        AccountManager.logOut();
        Database.removeProduct(product);
    }

    @Test
    public void addOffTest2()
    {
        Database.initialize();
        AccountManager.logIn("Seller", "Seller");
        Product product = new Product("test", "test", "Seller", true, 10, "test", "test", 1000);
        Database.addAllProduct(product);
        ArrayList<Integer> products = new ArrayList<Integer>();
        products.add(product.getProductId());
        String expected = "Your request registered";
        String result = SellerManager.addNewOff(100, 10, "2018-07-19_12:54:00", "2019-07-19_12:54:00",products);

        Assert.assertEquals(expected, result);
        AccountManager.logOut();
        Database.removeProduct(product);
    }

    @Test
    public void addOffTest3()
    {
        Database.initialize();
        AccountManager.logIn("Seller", "Seller");
        ArrayList<Integer> products = new ArrayList<Integer>();
        products.add(-1);
        String expected = "no such product";
        String result = SellerManager.addNewOff(100, 10, "2018-07-19_12:54:00", "2019-07-19_12:54:00",products);

        Assert.assertEquals(expected, result);
        AccountManager.logOut();
    }



}

