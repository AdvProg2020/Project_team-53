package Model;

import java.util.ArrayList;

public class Category {
    private static ArrayList<Category> allCategories;
    private String name;
    private String feature;
    private ArrayList<String> allSubCategoryNames;
    private Category parent;
    private ArrayList<Integer> allProductIds;

    public Category(String name, String feature, Category parent, ArrayList<Integer> allProductIds) {
        this.name = name;
        this.feature = feature;
        this.parent = parent;
        this.allProductIds = allProductIds;
        allSubCategoryNames = new ArrayList<>();
        allCategories.add(this);
    }

    public ArrayList<String> getAllSubCategoryNames() {
        return allSubCategoryNames;
    }

    public Category getParent() {
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

    public static Category getCategoryByName(String categoryName){
        for (Category category : allCategories) {
            if (category.name.equals(categoryName))
                return category;
        }
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public void addProduct(int productId){
        allProductIds.add(productId);
    }

    public void addSubCategory(String categoryName){
        allSubCategoryNames.add(categoryName);
    }
}
