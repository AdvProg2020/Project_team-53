package Model;

import Controller.Database;

import java.util.ArrayList;
import java.util.HashMap;

public class Cart {
    //private Account owner;
    private ArrayList<Integer> productsID;
    private HashMap<Integer, Integer> muchOfProductID;

    public Cart(Account account)
    {
        //owner = account;
        productsID = new ArrayList<>();
        muchOfProductID = new HashMap<>();
    }

    public void addToCart(Product product)
    {
        productsID.add(product.getProductId());
        muchOfProductID.put(product.getProductId(), 1);
    }

    public long getCost()
    {
        int cost = 0;
        for (Integer productId : productsID) {
            cost += Database.getProductByID(productId).getPrice() * muchOfProductID.get(productId);
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
        if(muchOfProductID.get(productID) + 1 > Database.getProductByID(productID).getNumber())
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
