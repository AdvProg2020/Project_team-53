package ModelTest;

import Controller.Database;
import Model.Category;
import Model.Product;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class CategoryTest {

    @Test
    public void getAllSubCategoryNamesTest()
    {
        Category category = new Category("test", "test", null);

        Assert.assertEquals(new ArrayList<>(), category.getAllSubCategoryNames());
    }

    @Test
    public void getAllProductIdsTest()
    {
        Category category = new Category("test", "test", null);

        Assert.assertEquals(new ArrayList<>(), category.getAllProductIds());
    }

    @Test
    public void getParentTest()
    {
        Category category = new Category("test", "test", null);

        Assert.assertNull(category.getParent());
    }

    @Test
    public void getNameTest()
    {
        Category category = new Category("test", "test", null);
        String expected = "test";
        String result = category.getName();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void getFeatureTest()
    {
        Category category = new Category("test", "test", null);
        String expected = "test";
        String result = category.getFeature();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void setNameTest()
    {
        Category category = new Category("test", "test", null);
        category.setName("tested");
        String expected = "tested";
        String result = category.getName();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void setFeatureTest()
    {
        Category category = new Category("test", "test", null);
        category.setFeature("tested");
        String expected = "tested";
        String result = category.getFeature();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void setParentTest()
    {
        Category category = new Category("test", "test", null);
        category.setParent("test");

        Assert.assertNotNull(category.getParent());
    }

    @Test
    public void addSubProductTest()
    {
        Category category = new Category("test", "test", null);
        Database.addAllCategory(category);
        Product product = new Product("product", "product", "product", true, 10, "product", "test", 1000);
        Database.addAllProduct(product);
        category.addProduct(product.getProductId());

        Assert.assertNull(Database.getCategoryByName("test"));
        Database.removeProduct(product);
        //Database.removeC
        // category(category);
    }

    @Test
    public void addSubCategoryTest()
    {
        Category category = new Category("test", "test", null);
        category.addSubCategory("test");

        Assert.assertNotNull(category.getAllSubCategoryNames());
    }

    @Test
    public void removeProductTest()
    {
        Product product = new Product("product", "product", "product", true, 10, "product", "test", 1000);
        Database.addAllProduct(product);
        Category category = new Category("test", "test", null);
        Database.addAllCategory(category);
        category.addProduct(product.getProductId());
        category.removeProduct(product.getProductId());

        Assert.assertTrue(category.getAllProductIds().isEmpty());
        Database.removeProduct(product);
        Database.removeCategory(category);
    }

}
