package ControllerTest;

import Controller.Database;
import org.junit.Test;

import static org.junit.Assert.fail;

public class DataBaseTest {

    @Test
    public void readAndWriteOnFileTest()
    {
        try {
            Database.initialize();
            Database.writeDataOnFile();
        }
        catch (Exception e)
        {
            fail();
        }
    }
}
