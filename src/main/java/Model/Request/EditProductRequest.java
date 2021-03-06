package Model.Request;

import Controller.AdminManager;
import Controller.Database;
import Model.Product.Product;

public class EditProductRequest extends Request{
    String field;
    String changeTo;
    int productId;

    public EditProductRequest(String field, String changeTo, int productId) {
        this.field = field;
        this.changeTo = changeTo;
        this.productId = productId;
    }

    public String getField() {
        return field;
    }

    public String getChangeTo() {
        return changeTo;
    }

    public int getProductId() {
        return productId;
    }

    @Override
    public String acceptRequest(AdminManager adminManager) {
        Product product = Database.getProductByID(productId);
        if (product == null)
            return "Product not found!";
        if (field.equalsIgnoreCase("status")){
            product.setStatus(changeTo);
        }
        else if (field.equalsIgnoreCase("name")){
            product.setName(changeTo);
        }
        else if (field.equalsIgnoreCase("available")){
            product.setAvailable(Boolean.parseBoolean(changeTo));
        }
        else if (field.equalsIgnoreCase("number")){
            product.setNumber(Integer.parseInt(changeTo));
        }
        else if (field.equalsIgnoreCase("description")){
            product.setDescription(changeTo);
        }
        else if (field.equalsIgnoreCase("categoryName")){
            product.setCategoryNameAndChangeCategory(changeTo);
        }
        else if (field.equalsIgnoreCase("price")){
            product.setPrice(Integer.parseInt(changeTo));
        }
        return "Product changed successfully";
    }

    @Override
    public String show() {
        return "EditProductRequest{" + '\n' +
                "   requestId="+ getId()+'\n'+
                "   field=" + this.getField() + '\n' +
                "   changeTo=" + this.getChangeTo() + '\n' +
                "   productId=" + this.getProductId() +'\n' +
                '}';
    }

}
