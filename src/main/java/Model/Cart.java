package Model;

import Controller.Database;
import Model.Product.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class Cart {
    //private Account owner;
    private ArrayList<Integer> productsID;
    private HashMap<Integer, Integer> muchOfProductID;

    public Cart()
    {
        //owner = account;
        productsID = new ArrayList<>();
        muchOfProductID = new HashMap<>();
    }

    public String showCart(){
        String result = new String() ;
        for (Integer productId : productsID) {
            result += Database.getProductByID(productId).getName() + " " + muchOfProductID.get(productId) + "\n";
        }
        return result;
    }

    public void addToCart(Product product)
    {
        productsID.add(product.getProductId());
        muchOfProductID.put(product.getProductId(), 1);
    }

    public ArrayList<Integer> getProductsID() {
        return productsID;
    }

    public int getMuchOfProductID(int productId) {
        for (Integer productIdS : muchOfProductID.keySet()) {
            if (productIdS == productId)
                return muchOfProductID.get(productIdS);
        }
        return 0;
    }

    public long getCost()
    { // Todo: impress the off in price
        int cost = 0;
        for (Integer productId : productsID) {
            Product product = Database.getProductByID(productId);
            cost += product.getPrice() * muchOfProductID.get(productId);
            if (product.doesHaveOff())
                cost -= muchOfProductID.get(productId)*(Math.min(product.getPrice()*product.getOff().getPercent()/100, product.getOff().getMaxValue()));
            // check up the line above
        }
        return  cost;
    }

    public String getAllProducts()
    {
        String allProductsNameAndId = "";
        for (Integer productID : productsID) {
            Product product = Database.getProductByID(productID);
            allProductsNameAndId = allProductsNameAndId + "Name : " + product.getName() + "_ID : " + product.getProductId() + "\n";
        }
        return allProductsNameAndId;
    }

    public boolean increaseProduct(int productID)
    {
        if(!muchOfProductID.containsKey(productID))
        {
            return false;
        }
        if(0 >= Database.getProductByID(productID).getNumber())
        {
            return false;
        }
        muchOfProductID.replace(productID, muchOfProductID.get(productID) + 1);
        return true;
    }

    public boolean decreaseProduct(int productID)
    {
        if(!muchOfProductID.containsKey(productID))
        {
            return false;
        }
        else if(muchOfProductID.get(productID) - 1 == 0)
        {
            productsID.remove(productsID.indexOf(productID));
            muchOfProductID.remove(productID);
            return true;
        }
        muchOfProductID.replace(productID, muchOfProductID.get(productID) - 1);
        return true;
    }

}
