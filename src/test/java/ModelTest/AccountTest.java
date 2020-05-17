package ModelTest;

import Controller.Database;
import Model.Account;
import Model.BuyerAccount;
import org.junit.Assert;
import org.junit.Test;

public class AccountTest {

    String expected = new String();
    String actual = new String();

    @Test
    public void usernameTest() {
        expected = "Invalid Username";
        try {
            new BuyerAccount("heklo@@", "", "", "", "", "", 0);
        } catch (Exception e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void firstNameTest() {
        expected = "Invalid FirstName";
        try {
            new BuyerAccount("Faraz", "84jghopw", "", "", "", "", 0);
        } catch (Exception e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void lastNameTest() {
        expected = "Invalid LastName";
        try {
            new BuyerAccount("Faraz", "GH", "??ogf!goo", "", "", "", 0);
        } catch (Exception e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void weakPasswordTest() {
        expected = "Weak or Invalid Password";
        try {
            new BuyerAccount("Faraz", "GH", "Own", "good", "", "", 0);
        } catch (Exception e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void invalidPasswordTest() {
        expected = "Weak or Invalid Password";
        try {
            new BuyerAccount("Faraz", "GH", "Own", "so crazy mam", "", "", 0);
        } catch (Exception e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void emailTest() {
        expected = "Invalid Email";
        try {
            new BuyerAccount("Faraz", "GH", "Own", "9800330030", "Faraz@isComing", "", 0);
        } catch (Exception e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void phoneNumberTest() {
        expected = "Invalid PhoneNumber";
        try {
            new BuyerAccount("Faraz", "GH", "Own", "9800330030", "Faraz.fdd@gmail.com", "no you don't", 0);
        } catch (Exception e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setCreditTest()
    {
        Database.initialize();
        Account account = Database.getAccountByUsername("parham");
        account.setCredit(1000000);
        long expected = 1000000;
        long result = account.getCredit();

        Assert.assertEquals(expected, result);
    }
}
