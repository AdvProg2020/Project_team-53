package Controller;

import Model.Product;

import java.util.ArrayList;

public class AllProductManager {

    static ArrayList<Product> allProducts = Database.getAllProducts();

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
        for (Product product : allProducts) {
            ans.append(product.digest());
            ans.append("\n----------------------\n");
        }
        return ans.toString();
    }

    public static String ignoreAllFilterAndSort(){
        allProducts = Database.getAllProducts();
        return  showAllProduct();
    }

    public static String sortByName(){
        allProducts.sort(AllProductManager::compareWithName);
        return showAllProduct();
    }

    public static String sortByPrice(){
        allProducts.sort(AllProductManager::compareWithPrice);
        return showAllProduct();
    }

    public static String sortByScore(){
        allProducts.sort(AllProductManager::compareWithScore);
        return showAllProduct();
    }

    public static int compareWithPrice(Product p1, Product p2){
        return p1.getPrice() > p2.getPrice() ? 1 : -1;
    }

    public static int compareWithName(Product p1, Product p2){
        return p1.getName().compareTo(p2.getName());
    }

    public static int compareWithScore(Product p1, Product p2){
        return p1.getAverageScore() > p2.getAverageScore() ? 1 : -1;
    }

    public static String sortOption(){
        return "Name \n Price \n Score";
    }
}
