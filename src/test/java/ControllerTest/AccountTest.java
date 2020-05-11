package ControllerTest;

import Model.Account;
import Model.AdminAccount;
import Model.BuyerAccount;
import mockit.Injectable;
import org.junit.Assert;
import org.junit.Test;

public class AccountTest {

    @Test
    public void accountConstructorExceptionsTest() {
        String expected;
        String actual = new String();
        expected = "Invalid Username";
        try {
            new BuyerAccount("heklo@@", "", "", "", "", "", 0);
        } catch (Exception e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(expected, actual);

        expected = "Invalid FirstName";
        try {
            new BuyerAccount("Faraz", "84jghopw", "", "", "", "", 0);
        } catch (Exception e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(expected, actual);

        expected = "Invalid LastName";
        try {
            new BuyerAccount("Faraz", "GH", "??ogf!goo", "", "", "", 0);
        } catch (Exception e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(expected, actual);

        expected = "Weak or Invalid Password";
        try {
            new BuyerAccount("Faraz", "GH", "Own", "good", "", "", 0);
        } catch (Exception e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(expected, actual);

        expected = "Weak or Invalid Password";
        try {
            new BuyerAccount("Faraz", "GH", "Own", "so crazy mam", "", "", 0);
        } catch (Exception e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(expected, actual);

        expected = "Invalid Email";
        try {
            new BuyerAccount("Faraz", "GH", "Own", "9800330030", "Faraz@isComing", "", 0);
        } catch (Exception e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(expected, actual);

        expected = "Invalid PhoneNumber";
        try {
            new BuyerAccount("Faraz", "GH", "Own", "9800330030", "Faraz.fdd@gmail.com", "no you don't", 0);
        } catch (Exception e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(expected, actual);
    }
}
