package ModelTest;

import Controller.Database;
import Model.BuyerAccount;
import Model.Cart;
import Model.Discount;
import Model.Log.BuyLog;
import org.junit.Assert;
import org.junit.Test;

public class BuyerAccountTest {

    @Test
    public void showAllLOgTest() throws Exception
    {
        BuyLog buyLog = new BuyLog("2018-07-19_12:54:00", 1000, "test", 1, 1, 10, "Seller");
        BuyerAccount buyerAccount = new BuyerAccount("test", "test", "test", "testing", "test@gmail.com", "021", 1000);
        buyerAccount.setCart(new Cart(buyerAccount));
        buyerAccount.addBuyLog(buyLog);
        String expected = buyLog.toString();
        String result = buyerAccount.showAllLog();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void showAllDiscountTest() throws Exception
    {
        BuyerAccount buyerAccount = new BuyerAccount("test", "test", "test", "testing", "test@gmail.com", "021", 1000);
        Discount discount = new Discount(2000, 20, "2018-06-20_12:30", "2019-06-20_12:30", 10);
        Database.addAllDiscount(discount);
        buyerAccount.addNewDiscount(discount.getDiscountId());
        String expected = discount.showInfo() + "\n----------------------\n";
        String result = buyerAccount.showAllDiscounts();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void canUseDiscountTest1() throws Exception
    {
        BuyerAccount buyerAccount = new BuyerAccount("test", "test", "test", "testing", "test@gmail.com", "021", 1000);

        Assert.assertFalse(buyerAccount.canUseDiscount(-1));
    }

    @Test
    public void canUseDiscountTest2() throws Exception
    {
        BuyerAccount buyerAccount = new BuyerAccount("test", "test", "test", "testing", "test@gmail.com", "021", 1000);
        Discount discount = new Discount(2000, 20, "2018-06-20_12:30", "2019-06-20_12:30", 10);
        Database.addAllDiscount(discount);

        Assert.assertFalse(buyerAccount.canUseDiscount(discount.getDiscountId()));
        Database.removeDiscount(discount);
    }

    @Test
    public void canUseDiscountTest3() throws Exception
    {
        BuyerAccount buyerAccount = new BuyerAccount("test", "test", "test", "testing", "test@gmail.com", "021", 1000);
        Discount discount = new Discount(2000, 20, "2020-06-20_12:30", "2011-06-20_12:30", 10);
        Database.addAllDiscount(discount);
        buyerAccount.addNewDiscount(discount.getDiscountId());

        Assert.assertFalse(buyerAccount.canUseDiscount(discount.getDiscountId()));
        Database.removeDiscount(discount);
    }

    @Test
    public void canUseDiscountTest4() throws Exception
    {
        BuyerAccount buyerAccount = new BuyerAccount("test", "test", "test", "testing", "test@gmail.com", "021", 1000);
        Discount discount = new Discount(2000, 20, "2011-06-20_12:30", "2021-06-20_12:30", 10);
        Database.addAllDiscount(discount);
        buyerAccount.addNewDiscount(discount.getDiscountId());

        Assert.assertTrue(buyerAccount.canUseDiscount(discount.getDiscountId()));
        Database.removeDiscount(discount);
    }

    @Test
    public void canUseDiscountTest5() throws Exception
    {
        BuyerAccount buyerAccount = new BuyerAccount("test", "test", "test", "testing", "test@gmail.com", "021", 1000);
        Discount discount = new Discount(2000, 20, "2020-06-20_12:30", "2011-06-20_12:30", 1);
        Database.addAllDiscount(discount);
        buyerAccount.addNewDiscount(discount.getDiscountId());
        buyerAccount.useDiscount(discount.getDiscountId());

        Assert.assertFalse(buyerAccount.canUseDiscount(discount.getDiscountId()));
        Database.removeDiscount(discount);
    }

}
