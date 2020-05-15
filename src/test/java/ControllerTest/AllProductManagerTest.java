package ControllerTest;

import Controller.AllProductManager;
import Controller.Database;
import Controller.ProductManager;
import Model.Product;
import org.junit.Assert;
import org.junit.Test;

public class AllProductManagerTest {

    @Test
    public void goToProductTest1()
    {
        String expected = "No product with this Id";
        String result = AllProductManager.goToProduct(-1);
    }

    @Test
    public void goToProductTest2()
    {
        Product product =new Product("test", "test", "test", true, 10, "test", "test", 1000);
        Database.addAllProduct(product);
        String expected = "Here is product menu";
        String result = AllProductManager.goToProduct(product.getProductId());
        Database.removeProduct(product);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void backToAllTest()
    {
        AllProductManager.backToAll();
        Assert.assertNull(ProductManager.getProduct());
    }

    @Test
    public void sortByNameTest()
    {
        String expected = "sorted with name.";
        String result = AllProductManager.sortByName();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void sortByPriceTest()
    {
        String expected = "sorted with price.";
        String result = AllProductManager.sortByPrice();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void sortByScoreTest(){
        String expected = "sorted with score.";
        String result = AllProductManager.sortByScore();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void showSortOptionTest()
    {
        String expected = "Name \n Price \n Score \n";
        String result = AllProductManager.showSortOption();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void ignoreSortAndGetSortedByTest()
    {
        AllProductManager.ignoreSort();
        String expected = "default sort.";
        String result = AllProductManager.getSortedBy();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void showFilterOptionTest()
    {
        String expected = "sellerUsername (a username) \n rangeOfPrice (lower bound , upper bound) \n categoryName (category name) \n available \n rangeOfScore (double more than)";
        String result = AllProductManager.showFilterOption();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void addAndGetAndDeleteFilterOptionTest1()
    {
        AllProductManager.addFilterOption("sellerUsername test");
        AllProductManager.getFilterOptions();
        String expected = "no such filter.";
        String result = AllProductManager.removeFilterOption("test");

        Assert.assertEquals(expected,result);
    }

    @Test
    public void addAndGetAndDeleteFilterOptionTest2()
    {
        AllProductManager.addFilterOption("sellerUsername test");
        AllProductManager.getFilterOptions();
        String expected = "done";
        String result = AllProductManager.removeFilterOption("sellerUsername test");

        Assert.assertEquals(expected,result);
    }
}
