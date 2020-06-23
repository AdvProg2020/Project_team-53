package ModelTest;

import Model.Request.NewProductRequest;
import org.junit.Assert;
import org.junit.Test;

public class NewProductRequestTest {
    @Test
    public void showTest(){
        NewProductRequest request = new NewProductRequest("available", "product", "seller", true, 10 ,"awli", "name" , 100);

        String expected = "NewProductRequest{" + "\n" +
                "   requestId="+ request.getId()+'\n'+
                "   status=" + "available" + '\n' +
                "   name=" + "product" + '\n' +
                "   sellerUsername=" + "seller" + '\n' +
                "   available=" + "true" + '\n' +
                "   number=" + "10" + '\n' +
                "   description=" + "awli" + '\n' +
                "   categoryName=" + "name" + '\n' +
                '}';
        String result = request.show();
        Assert.assertEquals(expected, result);
    }
//
//    @Test
//    public void acceptTest(){
//
//    }
}
