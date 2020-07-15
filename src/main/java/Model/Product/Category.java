package Model.Product;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

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
        this.allSubCategoryNames = new ArrayList<>();
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


    public void removeSubCategory(String categoryName){
        allSubCategoryNames.remove(categoryName);
    }

    public void addSubCategory(String categoryName){
        allSubCategoryNames.add(categoryName);
    }

    public void removeProduct(int productId){
        if(allProductIds.contains(productId))
            allProductIds.remove(allProductIds.indexOf(productId));
    }

    public String showThisCategory(){
        return "Category{" + '\n' +
                "   name=" + name + '\n' +
                "   allSubCategories=" + allSubCategoryNames + '\n'+
                "   feature=" + feature + '\n' +
                "   parent=" + parent + '\n' +
                '}';
    }

    public void addProduct(int productId) {
        allProductIds.add(productId);
    }

    public Pane showCategoryGraphic() {
        Category category = this;

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Label name = new Label("Name : " + category.getName());
        name.setFont(Font.font(20));
        Label allSubCategory = new Label("SubCategory : " + category.getAllSubCategoryNames());
        allSubCategory.setFont(Font.font(20));
        Label feature = new Label("Feature : " + category.getFeature());
        feature.setFont(Font.font(20));
        Label productIds = new Label("All Products : " + category.getAllProductIds());
        productIds.setFont(Font.font(20));
        Label parent = new Label("Parent : " + category.getParent());
        parent.setFont(Font.font(20));

        GridPane.setConstraints(name, 0, 1 , 2 , 1);
        GridPane.setConstraints(allSubCategory, 0, 2 , 2 , 1);
        GridPane.setConstraints(feature, 0, 3 , 2 , 1);
        GridPane.setConstraints(productIds, 0, 5 , 2 , 1);
        GridPane.setConstraints(parent, 0, 6 , 2 , 1);

        gridPane.getChildren().addAll(name, allSubCategory, feature, productIds,parent);

        return gridPane;
    }



}
