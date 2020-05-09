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
    public void editTest() {
        String expected = "Invalid field";
        String result = AccountManager.edit("username", "test");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void logoutTest() {
        String expected = "You haven't logged in";
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
        String actual = AccountManager.logIn("Admin", "Admin");
        String expected = "Welcome Admin";
        Assert.assertEquals(expected, actual);
    }


}
