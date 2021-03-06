package ControllerTest;

public class AllProductManagerTest {

    /*@Test
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
    public void showSortOptionTest()
    {
        String expected = "Name \n Price \n Score \n";
        String result = AllProductManager.showSortOption();

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
        AllProductManager.setSortedBy("Name");
        AllProductManager.addFilterOption("Range Of Price 0 3000");
        AllProductManager.addFilterOption("Category Name test1");
        AllProductManager.addFilterOption("Available");
        AllProductManager.addFilterOption("Higher Score Than -1");
        AllProductManager.addFilterOption("Company Name test");
        AllProductManager.addFilterOption("Product Name test1");
        AllProductManager.addFilterOption("Category Feature test");

        String expected = product1.digest() + "\n----------------------\n";
        String result = AllProductManager.showAllProduct();
        Database.removeProduct(product1);
        Database.removeProduct(product2);
        Database.removeAccount(sellerAccount);
        Database.removeCategory(category);
        AllProductManager.removeFilterOption("Company Name test");
        AllProductManager.removeFilterOption("Product Name test1");
        AllProductManager.removeFilterOption("Category Feature test");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void showAllProductsTest2()
    {
        Product product1 = new Product("test1", "test1", "test1", true, 20, "test1", "test1", 2000);
        Product product2 = new Product("test2", "test2", "test2", true, 10, "test2", "test2", 1000);
        Database.addAllProduct(product1);
        Database.addAllProduct(product2);
        AllProductManager.setSortedBy("Price");
        AllProductManager.addFilterOption("Seller Username test1");
        AllProductManager.addFilterOption("Range Of Price 0 3000");
        AllProductManager.addFilterOption("Category Name test1");
        AllProductManager.addFilterOption("Available");
        AllProductManager.addFilterOption("Higher Score Than -1");

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
        AllProductManager.setSortedBy("Score");
        AllProductManager.addFilterOption("Seller Username test1");
        AllProductManager.addFilterOption("Range Of Price 0 3000");
        AllProductManager.addFilterOption("Category Name test1");
        AllProductManager.addFilterOption("Available");
        AllProductManager.addFilterOption("Higher Score Than -1");


        String expected = product1.digest() + "\n----------------------\n";
        String result = AllProductManager.showAllProduct();
        Database.removeProduct(product1);
        Database.removeProduct(product2);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void showAllProductsTest4()
    {
        Product product1 = new Product("test1", "test1", "test1", true, 20, "test1", "test1", 2000);
        Product product2 = new Product("test2", "test2", "test2", true, 10, "test2", "test2", 1000);
        Database.addAllProduct(product1);
        Database.addAllProduct(product2);
        AllProductManager.setSortedBy("Score");
        AllProductManager.addFilterOption("Seller Username test1");
        AllProductManager.addFilterOption("Range Of Price 0 3000");
        AllProductManager.addFilterOption("Category Name test1");
        AllProductManager.addFilterOption("Available");
        AllProductManager.addFilterOption("Higher Score Than -1");
        AllProductManager.addFilterOption("Have Off");


        Assert.assertEquals(0, AllProductManager.showProductArray().size()  );
    }*/
}


