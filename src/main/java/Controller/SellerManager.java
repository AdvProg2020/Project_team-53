package Controller;

import Model.Product;
import Model.Request.EditProductRequest;
import Model.Request.NewProductRequest;

public class SellerManager {

    public static String sendAddProductRequest(String status, String name, boolean available, int number, String description, String categoryName, int price){
        Database.addRequest(new NewProductRequest(status, name, AccountManager.getLoggedInAccount().getUsername(), available, number, description, categoryName, price ));
        return "Your request registered.";
    }

    public static String sendEditProductRequest(int productId, String field, String changeTo){
        Product product = Database.getProductByID(productId);
        if (!product.isSeller(AccountManager.getLoggedInAccount().getUsername())){
            return "You can't edit this product.";
        }
        Database.addRequest(new EditProductRequest(field, changeTo, productId));
        return "Your request registered.";
    }

    public static String deleteProduct(int productId){
        Product product = Database.getProductByID(productId);
        if (!product.isSeller(AccountManager.getLoggedInAccount().getUsername())){
            return "You can't remove this product.";
        }
        Database.removeProduct(product);
        return "Product removed successfully";
    }
}
