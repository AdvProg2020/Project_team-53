package ViewTest;

import View.Menu.MainMenu;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MenuTest {

    @Test
    public void mainMenuShowTest()
    {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        MainMenu mainMenu = new MainMenu();
        String expected = "Main Menu:" + "\n" + "1) Login Menu" + "\n" + "2) User Menu" + "\n" + "3) Products Menu" + "\n" + "4) exit" + "\n";
        mainMenu.show();

        Assert.assertEquals(expected.replaceAll("\n", "").replaceAll("\r", ""), outContent.toString().replaceAll("\n", "").replaceAll("\r", ""));
    }

   /* @Test
    public void loginMenuShowTest()
    {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        LoginMenu loginMenu = new LoginMenu(new MainMenu());
        String expected = "Login Menu:" + "\n" + "1) Register Menu" + "\n" + "2) Login Menu" + "\n" + "3) Logout Menu" + "\n" + "4) back" + "\n";
        loginMenu.show();

        Assert.assertEquals(expected.replaceAll("\n", "").replaceAll("\r", ""), outContent.toString().replaceAll("\n", "").replaceAll("\r", ""));
    }*/

}
