package ControllerTest;

import Controller.AllProductManager;
import Controller.Database;
import Controller.ProductManager;
import Model.Account.SellerAccount;
import Model.Product.Category;
import Model.Product.Product;
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
        String expected = " sellerUsername (a username) \n" +
                " rangeOfPrice (lower bound , upper bound) \n" +
                " categoryName (category name) \n" +
                " available \n" +
                " rangeOfScore (double more than) " + '\n' +
                " companyName (name of a company) \n" +
                " productName (name of a product) \n" +
                " categoryFeature (feature of a category)";
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
        AllProductManager.removeFilterOption("sellerUsername test");

        Assert.assertEquals(expected,result);
    }

    @Test
    public void addAndGetAndDeleteFilterOptionTest2()
    {
        AllProductManager.addFilterOption("sellerUsername test1");
        AllProductManager.getFilterOptions();
        String expected = "done";
        String result = AllProductManager.removeFilterOption("sellerUsername test1");


        Assert.assertEquals(expected,result);
    }

    @Test
    public void showAllProductsTest1() throws Exception
    {
        Product product1 = new Product("test1", "test1", "test1", true, 20, "test1", "test1", 2000);
        Product product2 = new Product("test2", "test2", "test2", true, 10, "test2", "test2", 1000);
        SellerAccount sellerAccount = new SellerAccount("test1", "test", "test", "testing", "test@gmail.com", "021", 1000, "test");
        Category category = new Category("test1", "test", "test");
        Database.addAllCategory(category);
        Database.addAllProduct(product1);
        Database.addAllProduct(product2);
        Database.addAllAccounts(sellerAccount);
        AllProductManager.sortByName();
        AllProductManager.addFilterOption("rangeOfPrice 0 3000");
        AllProductManager.addFilterOption("categoryName test1");
        AllProductManager.addFilterOption("available");
        AllProductManager.addFilterOption("rangeOfScore -1");
        AllProductManager.addFilterOption("companyName test");
        AllProductManager.addFilterOption("productName test1");
        AllProductManager.addFilterOption("categoryFeature test");

        String expected = product1.digest() + "\n----------------------\n";
        String result = AllProductManager.showAllProduct();
        Database.removeProduct(product1);
        Database.removeProduct(product2);
        Database.removeAccount(sellerAccount);
        Database.removeCategory(category);
        AllProductManager.removeFilterOption("companyName test");
        AllProductManager.removeFilterOption("productName test1");
        AllProductManager.removeFilterOption("categoryFeature test");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void showAllProductsTest2()
    {
        Product product1 = new Product("test1", "test1", "test1", true, 20, "test1", "test1", 2000);
        Product product2 = new Product("test2", "test2", "test2", true, 10, "test2", "test2", 1000);
        Database.addAllProduct(product1);
        Database.addAllProduct(product2);
        AllProductManager.sortByPrice();
        AllProductManager.addFilterOption("sellerUsername test1");
        AllProductManager.addFilterOption("rangeOfPrice 0 3000");
        AllProductManager.addFilterOption("categoryName test1");
        AllProductManager.addFilterOption("available");
        AllProductManager.addFilterOption("rangeOfScore -1");

        String expected = product1.digest() + "\n----------------------\n";
        String result = AllProductManager.showAllProduct();
        Database.removeProduct(product1);
        Database.removeProduct(product2);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void showAllProductsTest3()
    {
        Product product1 = new Product("test1", "test1", "test1", true, 20, "test1", "test1", 2000);
        Product product2 = new Product("test2", "test2", "test2", true, 10, "test2", "test2", 1000);
        Database.addAllProduct(product1);
        Database.addAllProduct(product2);
        AllProductManager.sortByScore();
        AllProductManager.addFilterOption("sellerUsername test1");
        AllProductManager.addFilterOption("rangeOfPrice 0 3000");
        AllProductManager.addFilterOption("categoryName test1");
        AllProductManager.addFilterOption("available");
        AllProductManager.addFilterOption("rangeOfScore -1");


        String expected = product1.digest() + "\n----------------------\n";
        String result = AllProductManager.showAllProduct();
        Database.removeProduct(product1);
        Database.removeProduct(product2);

        Assert.assertEquals(expected, result);
    }
}


