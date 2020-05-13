package Controller;

import Model.Product;

import java.util.ArrayList;

public class AllProductManager {

    public static String goToProduct(int productId){
        if (Database.getProductByID(productId) == null){
            return "No product with this Id";
        }
        ProductManager.setProduct(Database.getProductByID(productId));
        return ProductManager.digest();
    }

    public static void backToAll(){
        ProductManager.setProduct(null);
    }

    public static String showAllProduct(){
        StringBuilder ans = new StringBuilder();
        ArrayList<Product> allProducts = Database.getAllProducts();
        for (Product product : allProducts) {
            ans.append(product.digest());
            ans.append("\n----------------------\n");
        }
        return ans.toString();
    }
}
