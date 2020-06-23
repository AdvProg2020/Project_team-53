package ModelTest;

import Model.Request.NewSellerRequest;
import org.junit.Assert;
import org.junit.Test;

public class NewSellerRequestTest {

    @Test
    public void showTest(){
        NewSellerRequest request = new NewSellerRequest("sell", "first", "last", "pass", "email" ,"company", 123 , "00");

        String expected = "NewSellerRequest" + '{' + '\n' +
                "   requestId="+ request.getId()+'\n'+
                "   username=" + "sell" + '\n' +
                "   firstName=" + "first" + '\n' +
                "   lastName=" + "last" + '\n' +
                "   password=" + "pass" + '\n' +
                "   email=" + "email" + '\n' +
                "   company=" + "company" + '\n' +
                "   credit=" + "123" + '\n' +
                "   phoneNumber=" + "00" + '\n' +
                '}';
        String result = request.show();
        Assert.assertEquals(expected, result);
    }

    @Test
    public void acceptTest(){
        NewSellerRequest request = new NewSellerRequest("sell", "first", "last", "pass", "email" ,"company", 123 , "00");

        String expected = "Weak or Invalid Password";
        String result = request.acceptRequest();
        Assert.assertEquals(expected, result);

    }
}
