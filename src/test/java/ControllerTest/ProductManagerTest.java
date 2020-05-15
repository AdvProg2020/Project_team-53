package ControllerTest;

import Controller.AccountManager;
import Controller.Database;
import Controller.ProductManager;
import Model.Product;
import org.junit.Assert;
import org.junit.Test;

public class ProductManagerTest {

    @Test
    public void setAndGetTest()
    {
        ProductManager.setProduct(new Product("test", "test", "test", true, 10, "test", "test", 1000));
        String expected = "test";
        String result = ProductManager.getProduct().getName();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void giveScoreTest()
    {
        ProductManager.setProduct(new Product("test", "test", "test", true, 10, "test", "test", 1000));
        Database.initialize();
        AccountManager.logIn("buyer", "buyer");
        String expected = "You have to buy product to give score.";
        String result = ProductManager.giveScore(2);
        AccountManager.logOut();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void showAverageScoreTest()
    {
        ProductManager.setProduct(new Product("test", "test", "test", true, 10, "test", "test", 1000));
        int  expected = 0;
        int result = (int) ProductManager.showAverageScore();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void digestTest()
    {
        ProductManager.setProduct(new Product("test", "test", "test", true, 10, "test", "test", 1000));
        String expected = "   ID= " + ProductManager.getProduct().getProductId() + '\n' +
                "   name= " + "test" + '\n' +
                "   sellerUsername= " + "test" + '\n' +
                "   description= " + "test" + '\n' +
                "   averageScore= " + "0.0" + '\n' +
                "   price= " + "1000" + '\n';
        String result = ProductManager.digest();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void showAllInfoTest(){
        ProductManager.setProduct(new Product("test", "test", "test", true, 10, "test", "test", 1000));
        String expected = "productId=" + ProductManager.getProduct().getProductId()  + '\n' +
                "status=" + "test" + '\n' +
                "name=" + "test" + '\n' +
                "sellerUsername=" + "test" + '\n' +
                "available=" + "true" + '\n'+
                "number=" + "10" + '\n' +
                "description=" + "test" + '\n' +
                "averageScore=" + "0.0" + '\n' +
                "categoryName=" + "test" + '\n' +
                "price=" + "1000" + '\n' ;
        String result = ProductManager.showFullInfo();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void compareTest()
    {
        ProductManager.setProduct(new Product("test", "test", "test", true, 10, "test", "test", 1000));
        Product product = new Product("test1", "test1", "test1", true, 10, "test1", "test", 1000);
        Database.addAllProduct(product);
        String expected =  "name : " + ProductManager.getProduct().getName() + " ---- " + product.getName() +'\n' +
                "sellers : " + ProductManager.getProduct().getSellerUsername() + " ---- " + product.getSellerUsername() +'\n' +
                "number : " + ProductManager.getProduct().getNumber() + " ---- " + product.getNumber() +'\n' +
                "available : " + ProductManager.getProduct().isAvailable() + " ---- " + product.isAvailable() +'\n' +
                "description : " + ProductManager.getProduct().getDescription() + " ---- " + product.getDescription() +'\n' +
                "averageScore : " + ProductManager.getProduct().getAverageScore() + " ---- " + product.getAverageScore() +'\n' +
                "categoryName : " + ProductManager.getProduct().getCategoryName() + " ---- " + product.getCategoryName() +'\n' +
                "price : " + ProductManager.getProduct().getPrice() + " ---- " + product.getPrice() +'\n' ;
        String result = ProductManager.compare(product.getProductId());
        Database.removeProduct(product);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void giveCommentTest()
    {
        AccountManager.logIn("buyer", "buyer");
        Product product1 = new Product("test1", "test1", "test1", true, 20, "test1", "test1", 2000);
        ProductManager.setProduct(product1);
        String expected = "Your comment registered.";
        String result = ProductManager.giveComment("test", "test");
        AccountManager.logOut();

        Assert.assertEquals(expected, result);

    }
}
