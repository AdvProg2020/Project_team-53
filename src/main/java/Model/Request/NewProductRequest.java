package Model.Request;

import Controller.Database;
import Model.Product;

public class NewProductRequest extends Request {

    String status;
    String name;
    String sellerUsername;
    boolean available;
    int number;
    String description;
    String categoryName;
    int price;

    public NewProductRequest(String status, String name, String sellerUsername, boolean available, int number, String description, String categoryName, int price) {
        this.status = status;
        this.name = name;
        this.sellerUsername = sellerUsername;
        this.available = available;
        this.number = number;
        this.description = description;
        this.categoryName = categoryName;
        this.price = price;
    }

    @Override
    public String acceptRequest() {
        Database.addAllProduct(new Product(status, name, sellerUsername, available, number, description , categoryName, price));
        return "product added successfully.";
    }

    @Override
    public String show() {
        return "NewProductRequest{" + "\n" +
                "   requestId="+ getId()+'\n'+
                "   status=" + status + '\n' +
                "   name=" + name + '\n' +
                "   sellerUsername=" + sellerUsername + '\n' +
                "   available=" + available +
                "   number=" + number +
                "   description=" + description + '\n' +
                "   categoryName=" + categoryName + '\n' +
                '}';
    }

}
