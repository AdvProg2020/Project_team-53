package Model;

import Model.Log.BuyLog;

import java.util.ArrayList;

public class BuyerAccount extends Account {
    private ArrayList<BuyLog> buyLogs = new ArrayList<>();
    private Cart cart;

    public BuyerAccount(String username, String firstName, String lastName, String password, String email, String phoneNumber, int credit) throws Exception {
        super(username, firstName, lastName, password, email, phoneNumber, credit);
        this.buyLogs = new ArrayList<>();
        cart = new Cart(this);
    }

    public boolean buyedProduct(int productId){
        for (BuyLog log : buyLogs) {
            if (log.getProductId() == productId)
                return true;
        }
        return false;
    }

    public void addBuyLog(BuyLog buyLog){
        buyLogs.add(buyLog);
    }

    public String showAllLog(){
        StringBuilder ans = new StringBuilder();
        for (BuyLog log : buyLogs) {
            ans.append(log.toString());
        }
        return ans.toString();
    }


}
