package Model.Account;

import Controller.Database;
import Model.Cart;
import Model.Product.DiscountAndOff.Discount;
import Model.Log.BuyLog;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class BuyerAccount extends Account {
    private ArrayList<BuyLog> buyLogs = new ArrayList<>();
    private ArrayList<Integer> discountIds = new ArrayList<>();
    private HashMap<Integer, Integer> numberOfUse = new HashMap<>();
    private Cart cart;

    public BuyerAccount(String username, String firstName, String lastName, String password, String email, String phoneNumber, int credit) throws Exception {
        super(username, firstName, lastName, password, email, phoneNumber, credit);
        this.buyLogs = new ArrayList<>();
        cart = new Cart();
    }

    public boolean buyedProduct(int productId){
        for (BuyLog log : buyLogs) {
            if (log.getProductId() == productId)
                return true;
        }
        return false;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
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

    public String showLogById(int logId){
        for (BuyLog buyLog : buyLogs) {
            if (buyLog.getLogId() == logId)
                return buyLog.toString();
        }
        return "nothing to show";
    }

    public void addNewDiscount(int discountId){
        discountIds.add(discountId);
        numberOfUse.put(discountId , 0);
    }

    public boolean canUseDiscount(int discountId){
        Discount discount = Database.getDiscountById(discountId);
        if (discount == null)
            return false;

        if (!discountIds.contains(discountId))
            return false;

        if (numberOfUse.get(discountId) >= discount.getNumberOfTimes())
            return false;
        Date date = new Date();
        if (date.compareTo(discount.getEndDate()) > 0 || date.compareTo(discount.getStartDate()) < 0)
            return false;

        return true;
    }

    public void useDiscount(int discountId){
        numberOfUse.replace(discountId , numberOfUse.get(discountId)+1 );
        return;
    }

    public String showAllDiscounts(){
        StringBuilder res = new StringBuilder();
        for (Integer discountId : discountIds) {
            if (canUseDiscount(discountId))
            {
                res.append(Database.getDiscountById(discountId).showInfo() + "\n----------------------\n");
            }
        }
        return res.toString();
    }
}