package ModelTest;

import Controller.Database;
import Model.Product;
import Model.Request.EditProductRequest;
import org.junit.Assert;
import org.junit.Test;

public class EditProductRequestTest {

    @Test
    public  void acceptRequestTest1()
    {
        EditProductRequest editProductRequest = new EditProductRequest("status", "test", -1);
        String expected = "Product not found!";
        String result = editProductRequest.acceptRequest();

        Assert.assertEquals(expected, result);
    }

    @Test
    public  void acceptRequestTest2()
    {
        Product product = new Product("test", "test", "test", true, 10, "test", "test", 1000);
        Database.addAllProduct(product);
        EditProductRequest editProductRequest = new EditProductRequest("status", "test", product.getProductId());
        String expected = "Product changed successfully";
        String result = editProductRequest.acceptRequest();

        Assert.assertEquals(expected, result);
        Database.removeProduct(product);
    }

    @Test
    public  void acceptRequestTest3()
    {
        Product product = new Product("test", "test", "test", true, 10, "test", "test", 1000);
        Database.addAllProduct(product);
        EditProductRequest editProductRequest = new EditProductRequest("name", "test", product.getProductId());
        String expected = "Product changed successfully";
        String result = editProductRequest.acceptRequest();

        Assert.assertEquals(expected, result);
        Database.removeProduct(product);
    }

    @Test
    public  void acceptRequestTest4()
    {
        Product product = new Product("test", "test", "test", true, 10, "test", "test", 1000);
        Database.addAllProduct(product);
        EditProductRequest editProductRequest = new EditProductRequest("number", "15", product.getProductId());
        String expected = "Product changed successfully";
        String result = editProductRequest.acceptRequest();

        Assert.assertEquals(expected, result);
        Database.removeProduct(product);
    }

    @Test
    public  void acceptRequestTest5()
    {
        Product product = new Product("test", "test", "test", true, 10, "test", "test", 1000);
        Database.addAllProduct(product);
        EditProductRequest editProductRequest = new EditProductRequest("available", "true", product.getProductId());
        String expected = "Product changed successfully";
        String result = editProductRequest.acceptRequest();

        Assert.assertEquals(expected, result);
        Database.removeProduct(product);
    }

    @Test
    public  void acceptRequestTest6()
    {
        Product product = new Product("test", "test", "test", true, 10, "test", "test", 1000);
        Database.addAllProduct(product);
        EditProductRequest editProductRequest = new EditProductRequest("description", "test", product.getProductId());
        String expected = "Product changed successfully";
        String result = editProductRequest.acceptRequest();

        Assert.assertEquals(expected, result);
        Database.removeProduct(product);
    }

    @Test
    public void acceptRequestTest7()
    {
        Product product = new Product("test", "test", "test", true, 10, "test", "test", 1000);
        Database.addAllProduct(product);
        EditProductRequest editProductRequest = new EditProductRequest("price", "500", product.getProductId());
        String expected = "Product changed successfully";
        String result = editProductRequest.acceptRequest();

        Assert.assertEquals(expected, result);
        Database.removeProduct(product);
    }

    @Test
    public void showTest()
    {
        EditProductRequest editProductRequest = new EditProductRequest("price", "500", 1);
        Database.addRequest(editProductRequest);
        String expected = "EditProductRequest{" + '\n' +
                "   requestId="+ editProductRequest.getId()+'\n'+
                "   field=" + "price" + '\n' +
                "   changeTo=" + "500" + '\n' +
                "   productId=" + "1" +'\n' +
                '}';
        String result = editProductRequest.show();

        Assert.assertEquals(expected, result);
        Database.removeRequest(editProductRequest);
    }
}

