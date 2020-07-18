package ModelTest;

public class ProductTest {

    /*@Test
    public void setCategoryNameWithoutAddToCategoryTest()
    {
        Product product = new Product("test", "test", "Seller", true, 10, "test", "test", 1000);
        product.setCategoryNameWithoutAddToCategory("test");
        String expected = "test";
        String result = product.getCategoryName();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void setPriceTest()
    {
        Product product = new Product("test", "test", "Seller", true, 10, "test", "test", 1000);
        product.setPrice(1000);
        String expected = "1000";
        String result = Long.toString(product.getPrice());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void setStatusTest()
    {
        Product product = new Product("test", "test", "Seller", true, 10, "test", "test", 1000);
        product.setStatus("test");
        String expected = "test";
        String result = product.getStatus();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void setNameTest()
    {
        Product product = new Product("test", "test", "Seller", true, 10, "test", "test", 1000);
        product.setName("test");
        String expected = "test";
        String result = product.getName();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void setAvailableTest()
    {
        Product product = new Product("test", "test", "Seller", true, 10, "test", "test", 1000);
        product.setAvailable(true);

        Assert.assertTrue(product.isAvailable());
    }

    @Test
    public void setNumberTest()
    {
        Product product = new Product("test", "test", "Seller", true, 10, "test", "test", 1000);
        product.setNumber(20);
        int expected = 20;
        int result = product.getNumber();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void setDescriptionTest()
    {
        Product product = new Product("test", "test", "Seller", true, 10, "test", "test", 1000);
        product.setDescription("test");
        String expected = "test";
        String result = product.getDescription();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void setOffIdTest()
    {
        Product product = new Product("test", "test", "Seller", true, 10, "test", "test", 1000);
        product.setOffId(-1);

        Assert.assertNull(product.getOff());
    }

    @Test
    public void showCommentTest()
    {
        Product product = new Product("test", "test", "Seller", true, 10, "test", "test", 1000);
        String expected = "";
        String result = product.showComments();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void giveScoreTest()
    {
        Product product = new Product("test", "test", "Seller", true, 10, "test", "test", 1000);
        product.giveScore(new Score(0, "parham"));
        String expected = "0.0";
        String result = Double.toString(product.getAverageScore());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void doesHaveOffTest1()
    {
        Product product = new Product("test", "test", "Seller", true, 10, "test", "test", 1000);
        Assert.assertFalse(product.doesHaveOff());
    }

    @Test
    public void doesHaveOffTest2()
    {
        Product product = new Product("test", "test", "Seller", true, 10, "test", "test", 1000);
        Off off = new Off(1000, 10, "2011-07-19_12:54:00", "2012-07-19_12:54:00", "Seller", new ArrayList<Integer>());
        Database.addAllOff(off);
        product.setOffId(off.getOffId());
        Assert.assertFalse(product.doesHaveOff());
    }

    @Test
    public void doesHaveOffTest3()
    {
        Product product = new Product("test", "test", "Seller", true, 10, "test", "test", 1000);
        Off off = new Off(1000, 10, "2011-07-19_12:54:00", "2021-07-19_12:54:00", "Seller", new ArrayList<Integer>());
        Database.addAllOff(off);
        product.setOffId(off.getOffId());
        Assert.assertFalse(product.doesHaveOff());
    }

    @Test
    public void doesHaveOffTest4()
    {
        Product product = new Product("test", "test", "Seller", true, 10, "test", "test", 1000);
        Off off = new Off(1000, 10, "2011-07-19_12:54:00", "2021-07-19_12:54:00", "Seller", new ArrayList<Integer>());
        Database.addAllOff(off);
        product.setOffId(off.getOffId());
        off.setStatus("accepted");
        Assert.assertTrue(product.doesHaveOff());
    }*/
}
