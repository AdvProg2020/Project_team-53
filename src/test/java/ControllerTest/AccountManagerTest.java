package ControllerTest;

import Controller.AccountManager;
import Controller.Database;
import Model.BuyerAccount;
import Model.SellerAccount;
import org.junit.Assert;
import org.junit.Test;

public class AccountManagerTest {

    @Test
    public void registerTest1() throws Exception {
        String expected = "Role is invalid!";
        String result = AccountManager.register("Admin", "a", "b", "c", "d", "e", "f", 1000, "");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void registerTest2() {
        String expected = "Weak or Invalid Password";
        String result = new String();
        try {
            result = AccountManager.register("buyer", "a", "b", "c", "d", "e", "f", 1000, "");
        } catch (Exception e) {
            result = e.getMessage();
        }
        Assert.assertEquals(expected, result);
    }

    @Test
    public void registerTest3() throws Exception
    {
        Database.removeAccount(Database.getAccountByUsername("test"));

        String expected = "New buyer account registered.";
        String result = AccountManager.register("Buyer", "test", "test", "test", "test@gmail.com", "0919", "testing", 10000, "");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void registerTest4() throws Exception
    {
        Database.removeAccount(Database.getAccountByUsername("test"));
        String expected = "Your Request registered";
        String result = AccountManager.register("Seller", "test", "test", "test", "test@gmail.com", "0919", "testing", 10000, "");
//        for (Account account : Database.getAllAccounts()) {
//            result = result + account.getUsername() + "\n";
//        }
//        result = result + Database.getAccountByUsername("test").getUsername();
        Assert.assertEquals(expected, result);
    }

    @Test
    public void editTest1() {
        String expected = "Invalid field";
        String result = AccountManager.edit("username", "test");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void editTest2() throws Exception
    {
        Database.initialize();
        AccountManager.logIn("parham", "parham");
        String result = AccountManager.edit("firstName", "tested");
        String expected = "changed successfully";
        AccountManager.logOut();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void editTest3() throws Exception
    {
        Database.initialize();
        AccountManager.logIn("parham", "parham");
        String result = AccountManager.edit("lastName", "tested");
        String expected = "changed successfully";
        AccountManager.logOut();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void editTest4() throws Exception
    {
        Database.initialize();
        AccountManager.logIn("parham", "parham");
        String result = AccountManager.edit("password", "parham");
        String expected = "changed successfully";
        AccountManager.logOut();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void editTest5() throws Exception
    {
        Database.initialize();
        AccountManager.logIn("parham", "parham");
        String result = AccountManager.edit("email", "tested@gmail.com");
        String expected = "changed successfully";
        AccountManager.logOut();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void editTest6() throws Exception
    {
        Database.initialize();
        AccountManager.logIn("parham", "parham");
        String result = AccountManager.edit("phoneNumber", "051");
        String expected = "changed successfully";
        AccountManager.logOut();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void editTest7()
    {
        Database.initialize();
        AccountManager.logIn("Seller", "Seller");
        String expected = "changed successfully";
        String result = AccountManager.edit("company", "justTest");
        AccountManager.logOut();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void editTest8()
    {
        Database.initialize();
        AccountManager.logIn("Seller", "Seller");
        String expected = "Invalid Company Name";
        String result = AccountManager.edit("company", "just test");
        AccountManager.logOut();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void editTest9()
    {
        Database.initialize();
        AccountManager.logIn("parham", "parham");
        try {
            AccountManager.edit("firstname", "@@@");
        }
        catch (Exception e)
        {
            String expected = "Invalid FirstName";
            String result = e.getMessage();
            Assert.assertEquals(expected, result);
        }
        AccountManager.logOut();
    }

    @Test
    public void editTest10()
    {
        Database.initialize();
        AccountManager.logIn("parham", "parham");
        try {
            AccountManager.edit("lastname", "@@@");
        }
        catch (Exception e)
        {
            String expected = "Invalid LastName";
            String result = e.getMessage();
            Assert.assertEquals(expected, result);
        }
        AccountManager.logOut();
    }

    @Test
    public void editTest11()
    {
        Database.initialize();
        AccountManager.logIn("parham", "parham");
        try {
            AccountManager.edit("password", "@@@");
        }
        catch (Exception e)
        {
            String expected = "Weak or Invalid Password";
            String result = e.getMessage();
            Assert.assertEquals(expected, result);
        }
        AccountManager.logOut();
    }

    @Test
    public void editTest12()
    {
        Database.initialize();
        AccountManager.logIn("parham", "parham");
        try {
            AccountManager.edit("email", "aaaa");
        }
        catch (Exception e)
        {
            String expected = "Invalid Email";
            String result = e.getMessage();
            Assert.assertEquals(expected, result);
        }
        AccountManager.logOut();
    }

    @Test
    public void editTest13()
    {
        Database.initialize();
        AccountManager.logIn("parham", "parham");
        try {
            AccountManager.edit("phoneNumber", "@@@@@");
        }
        catch (Exception e)
        {
            String expected = "Invalid PhoneNumber";
            String result = e.getMessage();
            Assert.assertEquals(expected, result);
        }
        AccountManager.logOut();
    }

    @Test
    public void logoutTest1() {
        AccountManager.logOut();
        String expected = "You haven't logged in";
        String result = AccountManager.logOut();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void logoutTest2()
    {
        Database.initialize();
        AccountManager.logIn("Admin", "Admin");
        String expected = "logged out successfully";
        String result = AccountManager.logOut();
        AccountManager.logOut();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void loginTest1() {
        String actual = AccountManager.logIn("Faraz", "GH");
        String expected = "No User with this name";
        Assert.assertEquals(expected, actual);
    }

    @Test
    //Todo: make manual allAccounts and test it
    public void loginTest2(){
        Database.initialize();
        String actual = AccountManager.logIn("Admin", "Admin");
        String expected = "Welcome Admin";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void loginTest3()
    {
        Database.initialize();
        AccountManager.logIn("Admin", "Admin");
        String result = AccountManager.logIn("test", "tested");
        String expected = "You have already loggedIn. You have to logout first.";
        AccountManager.logOut();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void loginTest4()
    {
        Database.initialize();
        String result = AccountManager.logIn("parham", "testing");
        String expected = "Wrong Password";

        Assert.assertEquals(expected, result);
    }

    @Test
    public void showLogTest1()
    {
        Database.initialize();
        AccountManager.logIn("Admin", "Admin");
        String expected = "nothing to show.";
        String result = AccountManager.viewLogs();
        AccountManager.logOut();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void showLogTest2() throws Exception
    {
        BuyerAccount buyerAccount = new BuyerAccount("test", "test", "test", "testing", "test@gmail.com", "021", 1000);
        Database.addAllAccounts(buyerAccount);
        AccountManager.logIn("test", "testing");
        String expected = "";
        String result = AccountManager.viewLogs();
        AccountManager.logOut();
        Database.removeAccount(buyerAccount);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void showLogTest3() throws Exception
    {
        SellerAccount sellerAccount = new SellerAccount("test", "test", "test", "testing", "test@gmail.com", "021", 1000, "test");
        Database.addAllAccounts(sellerAccount);
        AccountManager.logIn("test", "testing");
        String expected = "";
        String result = AccountManager.viewLogs();
        AccountManager.logOut();
        Database.removeAccount(sellerAccount);

        Assert.assertEquals(expected, result);
    }


}
