package ModelTest;

import Controller.Database;
import Model.Off;
import Model.Product;
import Model.Request.AddNewOffRequest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class AddNewOffRequestTest {

    @Test
    public void acceptRequestTest1()
    {
        AddNewOffRequest addNewOffRequest = new AddNewOffRequest(-1);
        String expected = "no such off";
        String result = addNewOffRequest.acceptRequest();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void acceptRequestTest2()
    {
        ArrayList<Integer> id = new ArrayList<>();
        Product product = new Product("test", "test", "test", true, 10, "test", "test", 1000);
        Database.addAllProduct(product);
        id.add(product.getProductId());
        Off off = new Off(1000, 10, "2018-07-19_12:54:00", "2019-07-19_12:54:00", "Seller", id);
        Database.addAllOff(off);
        AddNewOffRequest addNewOffRequest = new AddNewOffRequest(off.getOffId());
        String expected = "Off request added";
        String result = addNewOffRequest.acceptRequest();

        Assert.assertEquals(expected, result);
        Database.removeOff(off);
        Database.removeProduct(product);
    }

    @Test
    public void showTest1()
    {
        AddNewOffRequest addNewOffRequest = new AddNewOffRequest(-1);
        String expected = "no such off";
        String result = addNewOffRequest.show();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void showTest2()
    {
        Off off = new Off(1000, 10, "2018-07-19_12:54:00", "2019-07-19_12:54:00", "Seller", new ArrayList<Integer>());
        Database.addAllOff(off);
        AddNewOffRequest addNewOffRequest = new AddNewOffRequest(off.getOffId());
        Database.addRequest(addNewOffRequest);
        String expected = "AddNewOffRequest{" + '\n' +
                "requestId=" + addNewOffRequest.getId() + '\n' +
                off.showInfo() + '\n' +
                '}';
        String result = addNewOffRequest.show();

        Assert.assertEquals(expected, result);
        Database.removeOff(off);
        Database.removeRequest(addNewOffRequest);
    }
}
