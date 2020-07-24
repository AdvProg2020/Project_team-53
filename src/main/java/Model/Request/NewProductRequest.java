package Model.Request;

import Controller.AdminManager;
import Controller.Database;
import Model.Product.Product;

public class NewProductRequest extends Request {

    String status;
    String name;
    String sellerUsername;
    boolean available;
    int number;
    String description;
    String categoryName;
    int price;
    boolean hasFile;
    String address;

    public NewProductRequest(String status, String name, String sellerUsername, boolean available, int number, String description, String categoryName, int price, boolean hasFile, String addrses) {
        this.status = status;
        this.name = name;
        this.sellerUsername = sellerUsername;
        this.available = available;
        this.number = number;
        this.description = description;
        this.categoryName = categoryName;
        this.price = price;
        this.hasFile = hasFile;
        this.address = addrses;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public boolean isAvailable() {
        return available;
    }

    public int getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String acceptRequest(AdminManager adminManager) {
        Product product = new Product(status, name, sellerUsername, available, number, description , categoryName, price, hasFile, address);
        Database.addAllProduct(product);
        Database.getCategoryByName(product.getCategoryName()).addProduct(product.getProductId());
        return "product added successfully.";
    }

    @Override
    public String show() {
        return "NewProductRequest{" + "\n" +
                "   requestId="+ this.getId()+'\n'+
                "   status=" + this.getStatus() + '\n' +
                "   name=" + this.getName() + '\n' +
                "   sellerUsername=" + this.getSellerUsername() + '\n' +
                "   available=" + this.isAvailable() + '\n' +
                "   number=" + this.getNumber() + '\n' +
                "   description=" + this.getDescription() + '\n' +
                "   categoryName=" + this.getCategoryName() + '\n' +
                '}';
    }

}
