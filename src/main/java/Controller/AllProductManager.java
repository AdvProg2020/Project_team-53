package Controller;

import Model.Product;

import java.util.ArrayList;

public class AllProductManager {

    private static ArrayList<Product> allProducts = Database.getAllProducts();
    private static String sortedBy = "default sort.";

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
        sortedBy = "default sort.";
        allProducts = Database.getAllProducts();
        return  showAllProduct();
    }

    public static String sortByName(){
        sortedBy = "Sorted by name.";
        allProducts.sort(AllProductManager::compareWithName);
        return showAllProduct();
    }

    public static String sortByPrice(){
        sortedBy = "Sorted by price.";
        allProducts.sort(AllProductManager::compareWithPrice);
        return showAllProduct();
    }

    public static String sortByScore(){
        sortedBy = "Sorted by score.";
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
        return "Name \n Price \n Score \n";
    }

    public static String getSortedBy(){
        return sortedBy;
    }

    public static String filterOption(){
        return "sellerUsername \n rangeOfPrice \n categoryName \n available \n rangeOfScore";
    }

    public static String filterWithSellerUsername(String username){
        ArrayList<Product> tempArray = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.isSeller(username)){
                tempArray.add(product);
            }
        }
        allProducts = tempArray;
        return showAllProduct();
    }

    public static String filterWithRangeOfPrice(int lower, int higher){
        ArrayList<Product> tempArray = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getPrice() >= lower && product.getPrice() <= higher){
                tempArray.add(product);
            }
        }
        allProducts = tempArray;
        return showAllProduct();
    }

    public static String filterWithCategoryName(String categoryName){
        ArrayList<Product> tempArray = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getCategoryName().equalsIgnoreCase(categoryName)){
                tempArray.add(product);
            }
        }
        allProducts = tempArray;
        return showAllProduct();
    }

    public static String filterWithAvailable(){
        ArrayList<Product> tempArray = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.isAvailable()){
                tempArray.add(product);
            }
        }
        allProducts = tempArray;
        return showAllProduct();
    }

    public static String filterWitRangeOfScore(double moreThan){
        ArrayList<Product> tempArray = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getAverageScore() >= moreThan){
                tempArray.add(product);
            }
        }
        allProducts = tempArray;
        return showAllProduct();
    }


}
