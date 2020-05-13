package ControllerTest;

import Controller.AccountManager;
import Controller.Database;
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
        String expected = "New buyer account registered.";
        String result = AccountManager.register("Buyer", "test", "test", "test", "test@gmail.com", "0919", "testing", 10000, "");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void registerTest4() throws Exception
    {
        String expected = "Your Request registered";
        String result = AccountManager.register("Seller", "test", "test", "test", "test@gmail.com", "0919", "testing", 10000, "");

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
        AccountManager.logIn("test", "testing");
        String result = AccountManager.edit("firstName", "tested");
        String expected = "changed successfully";
        AccountManager.logOut();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void editTest3() throws Exception
    {
        Database.initialize();
        AccountManager.logIn("test", "testing");
        String result = AccountManager.edit("lastName", "tested");
        String expected = "changed successfully";
        AccountManager.logOut();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void editTest4() throws Exception
    {
        Database.initialize();
        AccountManager.logIn("test", "testing");
        String result = AccountManager.edit("password", "tested");
        String expected = "changed successfully";
        AccountManager.logOut();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void editTest5() throws Exception
    {
        Database.initialize();
        AccountManager.logIn("test", "tested");
        String result = AccountManager.edit("email", "tested@gmail.com");
        String expected = "changed successfully";
        AccountManager.logOut();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void editTest6() throws Exception
    {
        Database.initialize();
        AccountManager.logIn("test", "tested");
        String result = AccountManager.edit("phoneNumber", "051");
        String expected = "changed successfully";
        AccountManager.logOut();

        Assert.assertEquals(expected, result);
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
        String result = AccountManager.logIn("test", "testing");
        String expected = "Wrong Password";

        Assert.assertEquals(expected, result);
    }


}
