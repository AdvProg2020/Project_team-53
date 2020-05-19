package ModelTest;

import Controller.Database;
import Model.Product.DiscountAndOff.Off;
import Model.Request.EditOffRequest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class EditOffRequestTest {

    @Test
    public void acceptRequestTest1()
    {
        Off off = new Off(1000, 10, "2018-07-19_12:54:00", "2019-07-19_12:54:00", "Seller", new ArrayList<Integer>());
        Database.addAllOff(off);
        EditOffRequest editOffRequest = new EditOffRequest("startDate", "test", off.getOffId());
        Database.removeOff(off);
        String expected = "off not found";
        String result = editOffRequest.acceptRequest();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void acceptRequestTest2()
    {
        Off off = new Off(1000, 10, "2018-07-19_12:54:00", "2019-07-19_12:54:00", "Seller", new ArrayList<Integer>());
        Database.addAllOff(off);
        EditOffRequest editOffRequest = new EditOffRequest("startDate", "2018-07-19_12:54:00", off.getOffId());
        String expected = "Off changed successfully";
        String result = editOffRequest.acceptRequest();

        Assert.assertEquals(expected, result);
        Database.removeOff(off);
    }

    @Test
    public void acceptRequestTest3()
    {
        Off off = new Off(1000, 10, "2018-07-19_12:54:00", "2019-07-19_12:54:00", "Seller", new ArrayList<Integer>());
        Database.addAllOff(off);
        EditOffRequest editOffRequest = new EditOffRequest("endDate", "2018-07-19_12:54:00", off.getOffId());
        String expected = "Off changed successfully";
        String result = editOffRequest.acceptRequest();

        Assert.assertEquals(expected, result);
        Database.removeOff(off);
    }

    @Test
    public void acceptRequestTest4()
    {
        Off off = new Off(1000, 10, "2018-07-19_12:54:00", "2019-07-19_12:54:00", "Seller", new ArrayList<Integer>());
        Database.addAllOff(off);
        EditOffRequest editOffRequest = new EditOffRequest("percent", "20", off.getOffId());
        String expected = "Off changed successfully";
        String result = editOffRequest.acceptRequest();

        Assert.assertEquals(expected, result);
        Database.removeOff(off);
    }

    @Test
    public void acceptRequestTest5()
    {
        Off off = new Off(1000, 10, "2018-07-19_12:54:00", "2019-07-19_12:54:00", "Seller", new ArrayList<Integer>());
        Database.addAllOff(off);
        EditOffRequest editOffRequest = new EditOffRequest("maxValue", "2000", off.getOffId());
        String expected = "Off changed successfully";
        String result = editOffRequest.acceptRequest();

        Assert.assertEquals(expected, result);
        Database.removeOff(off);
    }

    @Test
    public void showTest()
    {
        Off off = new Off(1000, 10, "2018-07-19_12:54:00", "2019-07-19_12:54:00", "Seller", new ArrayList<Integer>());
        Database.addAllOff(off);
        EditOffRequest editOffRequest = new EditOffRequest("maxValue", "2000", off.getOffId());
        Database.addRequest(editOffRequest);
        String expected = "EditOffRequest{" + '\n' +
                "   requestId="+ editOffRequest.getId()+'\n'+
                "   field=" + "maxValue" + '\n' +
                "   changeTo=" + "2000" + '\n' +
                "   offId=" + off.getOffId() + '\n' +
                '}';
        String result = editOffRequest.show();

        Assert.assertEquals(expected, result);
        Database.removeRequest(editOffRequest);
        Database.removeOff(off);
    }


}
