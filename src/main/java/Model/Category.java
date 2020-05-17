package Model;

import java.util.ArrayList;

public class Category {
    private String name;
    private String feature;
    private ArrayList<String> allSubCategoryNames;
    private String parent;
    private ArrayList<Integer> allProductIds;

    public Category(String name, String feature, String parent) {
        this.name = name;
        this.feature = feature;
        this.parent = parent;
        this.allProductIds = new ArrayList<>();
        allSubCategoryNames = new ArrayList<>();
    }

    public ArrayList<String> getAllSubCategoryNames() {
        return allSubCategoryNames;
    }

    public String getParent() {
        return parent;
    }

    public ArrayList<Integer> getAllProductIds() {
        return allProductIds;
    }

    public String getFeature() {
        return feature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }



    public void addSubCategory(String categoryName){
        allSubCategoryNames.add(categoryName);
    }

    public void removeProduct(int productId){
        allProductIds.remove(allProductIds.indexOf(productId));
    }

    public String showThisCategory(){
        return "Category{" +
                "   name=" + name + '\n' +
                "   feature='" + feature + '\n' +
                "   parent='" + parent + '\n' +
                '}';
    }

    public void addProduct(int productId) {
        allProductIds.add(productId);
    }
}
