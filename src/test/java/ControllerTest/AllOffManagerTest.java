package ControllerTest;

import Controller.AllOffManager;
import Controller.AllProductManager;
import Controller.Database;
import Model.Product.DiscountAndOff.Off;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class AllOffManagerTest {

    @Test
    public void ignoreSortAndGetSortedByTest()
    {
        AllProductManager.setSortedBy("Default");
        String expected = "Default";
        String result = AllProductManager.getSortedBy();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void showSortOptionTest()
    {
        String expected = "MaxValue \n Percent \n StartDate \n EndDate";
        String result = AllOffManager.showSortOption();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void sortByMaxValueTest()
    {
        String expected = "sorted with maxValue.";
        String result = AllOffManager.sortByMaxValue();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void sortByMaxPercentTest()
    {
        String expected = "sorted with percent.";
        String result = AllOffManager.sortByPercent();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void sortByMaxStartDateTest()
    {
        String expected = "sorted with startDate.";
        String result = AllOffManager.sortByStartDate();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void sortByMaxEndDateTest()
    {
        String expected = "sorted with endDate.";
        String result = AllOffManager.sortByEndDate();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void showFilterOptionTest()
    {
        String expected = "sellerUsername (a username) \n status (Waiting to add...\\Waiting to apply changes...\\Accepted)";
        String result = AllOffManager.showFilterOption();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void addAndGetAndDeleteFilterOptionTest1()
    {
        AllOffManager.addFilterOption("sellerUsername test");
        AllOffManager.getFilterOptions();
        String expected = "no such filter.";
        String result = AllOffManager.removeFilterOption("test");
        AllOffManager.removeFilterOption("sellerUsername test");

        Assert.assertEquals(expected,result);
    }

    @Test
    public void addAndGetAndDeleteFilterOptionTest2()
    {
        AllOffManager.addFilterOption("sellerUsername test1");
        AllOffManager.getFilterOptions();
        String expected = "done";
        String result = AllOffManager.removeFilterOption("sellerUsername test1");


        Assert.assertEquals(expected,result);
    }

    @Test
    public void showAllProductsTest1()
    {
        Off off1 = new Off(1000, 10, "2018-07-19_12:54:00", "2019-07-19_12:54:00", "Seller1", new ArrayList<Integer>());
        Off off2 = new Off(1000, 10, "2018-07-19_12:54:00", "2019-07-19_12:54:00", "Seller2", new ArrayList<Integer>());
        off1.setStatus("Accepted");
        Database.addAllOff(off1);
        Database.addAllOff(off2);
        AllOffManager.sortByEndDate();
        AllOffManager.addFilterOption("sellerUsername Seller1");

        String expected = off1.showOff() + "\n----------------------\n";
        String result = AllOffManager.showAllOffs();
        Database.removeOff(off1);
        Database.removeOff(off2);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void showAllProductsTest2()
    {
        Off off1 = new Off(1000, 10, "2018-07-19_12:54:00", "2019-07-19_12:54:00", "Seller1", new ArrayList<Integer>());
        Off off2 = new Off(1000, 10, "2018-07-19_12:54:00", "2019-07-19_12:54:00", "Seller2", new ArrayList<Integer>());
        off1.setStatus("Accepted");
        Database.addAllOff(off1);
        Database.addAllOff(off2);
        AllOffManager.sortByStartDate();
        AllOffManager.addFilterOption("status Accepted");

        String expected = off1.showOff() + "\n----------------------\n";
        String result = AllOffManager.showAllOffs();
        Database.removeOff(off1);
        Database.removeOff(off2);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void showAllProductsTest3()
    {
        Off off1 = new Off(1000, 10, "2018-07-19_12:54:00", "2019-07-19_12:54:00", "Seller1", new ArrayList<Integer>());
        Off off2 = new Off(1000, 10, "2018-07-19_12:54:00", "2019-07-19_12:54:00", "Seller2", new ArrayList<Integer>());
        off1.setStatus("Accepted");
        Database.addAllOff(off1);
        Database.addAllOff(off2);
        AllOffManager.sortByMaxValue();
        AllOffManager.addFilterOption("status Accepted");

        String expected = off1.showOff() + "\n----------------------\n";
        String result = AllOffManager.showAllOffs();
        Database.removeOff(off1);
        Database.removeOff(off2);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void showAllProductsTest4()
    {
        Off off1 = new Off(1000, 10, "2018-07-19_12:54:00", "2019-07-19_12:54:00", "Seller1", new ArrayList<Integer>());
        Off off2 = new Off(1000, 10, "2018-07-19_12:54:00", "2019-07-19_12:54:00", "Seller2", new ArrayList<Integer>());
        off1.setStatus("Accepted");
        Database.addAllOff(off1);
        Database.addAllOff(off2);
        AllOffManager.sortByPercent();
        AllOffManager.addFilterOption("status Accepted");

        String expected = off1.showOff() + "\n----------------------\n";
        String result = AllOffManager.showAllOffs();
        Database.removeOff(off1);
        Database.removeOff(off2);

        Assert.assertEquals(expected, result);
    }
}
