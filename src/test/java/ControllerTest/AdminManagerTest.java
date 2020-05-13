package ControllerTest;

import Controller.AdminManager;
import org.junit.Assert;
import org.junit.Test;

public class AdminManagerTest {

    @Test
    public void showAccountWithUsernameTest1()
    {
        String expected = "No user with this username.";
        String result = AdminManager.showAccountWithUsername("TestUsername");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void addNewAdminAccountTest1()
    {
        String expected = "Exist account with this username.";
        String result = AdminManager.addNewAdminAccount("Admin", "a", "b", "test@gmail.com", "05345434234", "Admin", 1000);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void showRequestWithIDTest()
    {
        String expected = "No request with this id";
        String result = AdminManager.showRequestByiId(-1);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void acceptOrRejectRequestTest()
    {
        String expected = "No request with this id";
        String result = AdminManager.acceptOrRejectRequest(-1, true);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void deleteUserNameTest1()
    {
        String expected = "No user with this username";
        String result = AdminManager.deleteUsername("testUsername");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void deleteUserNameTest2()
    {
        AdminManager.addNewAdminAccount("test", "test", "test", "test@gmail.com", "09121111111", "testing", 1000);
        String expected = "Account deleted";
        String result = AdminManager.deleteUsername("test");

        Assert.assertEquals(expected, result);
    }
}




