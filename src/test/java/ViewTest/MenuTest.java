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
        String expected = "Main Menu:" + "\n" + "1) Login Menu" + "\n" + "2) User Menu" + "\n" + "3) Products Menu" + "\n" + "4) Off Menu" + "\n" + "5) exit" + "\n";
        mainMenu.show();

        Assert.assertEquals(expected.replaceAll("\n", "").replaceAll("\r", ""), outContent.toString().replaceAll("\n", "").replaceAll("\r", ""));
    }
}
