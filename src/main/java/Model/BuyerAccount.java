package Model;

public class BuyerAccount extends Account {
    public BuyerAccount(String username, String firstName, String lastName, String password, String email, String phoneNumber, int credit) throws Exception {
        super(username, firstName, lastName, password, email, phoneNumber, credit);
    }

    public boolean buyedProduct(int productId){
        // TODO: 13-May-20 - from Log
        return true;
    }


}
