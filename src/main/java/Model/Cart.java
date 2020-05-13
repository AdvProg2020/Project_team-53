package Model;

import Controller.Database;

import java.util.ArrayList;
import java.util.HashMap;

public class Cart {
    Account owner;
    ArrayList<Integer> productsID;
    HashMap<Integer, Integer> muchOfProductID;

    public Cart(Account account)
    {
        owner = account;
        productsID = new ArrayList<>();
        muchOfProductID = new HashMap<>();
    }

    public void addToCart(Product product)
    {
        productsID.add(product.getProductId());
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
            allProductsNameAndId.concat("Name : " + product.getName() + "_ID : " + product.getProductId() + '\n');
        }
        return allProductsNameAndId;
    }

    public boolean increaseProduct(int productID)
    {
        muchOfProductID.replace(productID, muchOfProductID.get(productID) + 1);
        return true;
    }

    public boolean decreaseProduct(int productID)
    {
        muchOfProductID.replace(productID, muchOfProductID.get(productID) - 1);
        return true;
    }


}
