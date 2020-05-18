package ModelTest;

import Model.Log.BuyLog;
import Model.Log.SellLog;
import org.junit.Assert;
import org.junit.Test;

public class LogTest {

    @Test
    public void toStringTest1()
    {
        BuyLog buyLog = new BuyLog("2018-07-19_12:54:00", 1000, "test", 1, 1, 10, "Seller");
        String expected = "BuyLog{" +
                "   logId=" + "1" + '\n' +
                "   productId=" + "1" +'\n' +
                "   discountValue=" + "10" + '\n' +
                "   sellerUsername='" + "Seller" + '\n' +
                "   date='" + "2018-07-19_12:54:00" + '\n' +
                "   price=" + "1000" + '\n' +
                "   deliveryStatus='" + "test" + '\n' +
                '}';
        String result = buyLog.toString();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void toStringTest2()
    {
        SellLog sellLog = new SellLog("2018-07-19_12:54:00", 1000, "test", 1, 1, 10, "Buyer");
        String expected = "SellLog{" +
                "   logId=" + "1" + '\n' +
                "   productId=" + "1" + '\n' +
                "   OffValue=" + "10" + '\n' +
                "   buyerUsername='" + "Buyer" + '\n' +
                "   date='" + "2018-07-19_12:54:00" + '\n' +
                "   price=" + "1000" + '\n' +
                "   deliveryStatus='" + "test" + '\n' +
                '}';
        String result = sellLog.toString();
        Assert.assertEquals(expected, result);
    }
}


