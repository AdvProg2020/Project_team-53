package ModelTest;

import Model.Log.Log;
import Model.Log.SellLog;
import Model.Account.SellerAccount;
import org.junit.Assert;
import org.junit.Test;

public class SellerAccountTest {


    @Test
    public void testSetUserName()
    {
        String expected = "Invalid Username";
        try{
            SellerAccount sellerAccount = new SellerAccount("testUN", "testFN", "testLN", "testing", "test@gmail.com", "091211111111", 10000, "tester");
            sellerAccount.setUsername("@@@@@@");
        }
        catch (Exception e)
        {
            String result = e.getMessage();
            Assert.assertEquals(expected, result);
        }
    }

    @Test
    public void testSetFirstName()
    {
        String expected = "Invalid FirstName";
        try{
            SellerAccount sellerAccount = new SellerAccount("testUN", "testFN", "testLN", "testing", "test@gmail.com", "091211111111", 10000, "tester");
            sellerAccount.setFirstName("@12###");
        }
        catch (Exception e)
        {
            String result = e.getMessage();
            Assert.assertEquals(expected, result);
        }
    }

    @Test
    public void testSetLAstName()
    {
        String expected = "Invalid LastName";
        try{
            SellerAccount sellerAccount = new SellerAccount("testUN", "testFN", "testLN", "testing", "test@gmail.com", "091211111111", 10000, "tester");
            sellerAccount.setLastName("@12###");
        }
        catch (Exception e)
        {
            String result = e.getMessage();
            Assert.assertEquals(expected, result);
        }
    }

    @Test
    public void testSetPassword()
    {
        String expected = "Weak or Invalid Password";
        try{
            SellerAccount sellerAccount = new SellerAccount("testUN", "testFN", "testLN", "testing", "test@gmail.com", "091211111111", 10000, "tester");
            sellerAccount.setPassword("1234");
        }
        catch (Exception e)
        {
            String result = e.getMessage();
            Assert.assertEquals(expected, result);
        }
    }

    @Test
    public void testSetEmail()
    {
        String expected = "Invalid Email";
        try{
            SellerAccount sellerAccount = new SellerAccount("testUN", "testFN", "testLN", "testing", "test@gmail.com", "091211111111", 10000, "tester");
            sellerAccount.setEmail("@test");
        }
        catch (Exception e)
        {
            String result = e.getMessage();
            Assert.assertEquals(expected, result);
        }
    }

    @Test
    public void testSetPhoneNumber()
    {
        String expected = "Invalid PhoneNumber";
        try{
            SellerAccount sellerAccount = new SellerAccount("testUN", "testFN", "testLN", "testing", "test@gmail.com", "091211111111", 10000, "tester");
            sellerAccount.setPhoneNumber("test");
        }
        catch (Exception e)
        {
            String result = e.getMessage();
            Assert.assertEquals(expected, result);
        }
    }

    @Test
    public void testGetUsername() throws Exception
    {
        SellerAccount sellerAccount = new SellerAccount("testUN", "testFN", "testLN", "testing", "test@gmail.com", "091211111111", 10000, "tester");
        String expected = "testUN";
        String result = sellerAccount.getUsername();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void testGetLasName() throws Exception
    {
        SellerAccount sellerAccount = new SellerAccount("testUN", "testFN", "testLN", "testing", "test@gmail.com", "091211111111", 10000, "tester");
        String expected = "testLN";
        String result = sellerAccount.getLastName();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void testGetPassword() throws Exception
    {
        SellerAccount sellerAccount = new SellerAccount("testUN", "testFN", "testLN", "testing", "test@gmail.com", "091211111111", 10000, "tester");
        String expected = "testing";
        String result = sellerAccount.getPassword();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void testGetEmail() throws Exception
    {
        SellerAccount sellerAccount = new SellerAccount("testUN", "testFN", "testLN", "testing", "test@gmail.com", "091211111111", 10000, "tester");
        String expected = "test@gmail.com";
        String result = sellerAccount.getEmail();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void testGetPhoneNumber() throws Exception
    {
        SellerAccount sellerAccount = new SellerAccount("testUN", "testFN", "testLN", "testing", "test@gmail.com", "091211111111", 10000, "tester");
        String expected = "091211111111";
        String result = sellerAccount.getPhoneNumber();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void testGetCredit() throws Exception
    {
        SellerAccount sellerAccount = new SellerAccount("testUN", "testFN", "testLN", "testing", "test@gmail.com", "091211111111", 10000, "tester");
        int expected = 10000;
        int result = sellerAccount.getCredit();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void getCompanyTest() throws Exception
    {
        SellerAccount sellerAccount = new SellerAccount("testUN", "testFN", "testLN", "testing", "test@gmail.com", "091211111111", 10000, "tester");
        String expected = "tester";
        String result = sellerAccount.getCompany();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void setCompanyTest()
    {
        try {
            SellerAccount sellerAccount = new SellerAccount("testUN", "testFN", "testLN", "testing", "test@gmail.com", "091211111111", 10000, "tester");
            sellerAccount.setCompany("    ");
        }
        catch (Exception e)
        {
            String expected = "Invalid Company Name";
            String result = e.getMessage();

            Assert.assertEquals(expected, result);
        }
    }

    @Test
    public void showInfoTest() throws Exception
    {
        SellerAccount sellerAccount = new SellerAccount("testUN", "testFN", "testLN", "testing", "test@gmail.com", "091211111111", 10000, "tester");
        String expected = "username='" + "testUN" + "'\n" +
                "firstName='" + "testFN" + "'\n" +
                "lastName='" + "testLN" + "'\n" +
                "email='" + "test@gmail.com" + "'\n" +
                "phoneNumber='" + "091211111111" + "'\n" +
                "credit='" + "10000" + "'" + "\n" +
        "company=" + "tester";
        String result = sellerAccount.showInfo();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void showAllLogTest() throws Exception
    {
        SellerAccount sellerAccount = new SellerAccount("testUN", "testFN", "testLN", "testing", "test@gmail.com", "091211111111", 10000, "tester");
        SellLog sellLog = new SellLog("2018-07-19_12:54:00", 1000, "test", 1, 1, 20,  "Test");
        sellerAccount.addSellLog(sellLog);
        Log log = sellLog;
        String expected = sellerAccount.showAllLog();
        String result = log.toString();

        Assert.assertEquals(expected, result);
    }
}
