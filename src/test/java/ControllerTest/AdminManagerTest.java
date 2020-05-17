package ControllerTest;

import Controller.AdminManager;
import Controller.Database;
import Model.Product;
import Model.Request.Request;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class AdminManagerTest {

    @Test
    public void showAccountWithUsernameTest1()
    {
        String expected = "No user with this username.";
        String result = AdminManager.showAccountWithUsername("TestUsername");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void showAccountWithUsernameTest2()
    {
        Database.initialize();
        String expected = Database.getAccountByUsername("parham").showInfo();
        String result = AdminManager.showAccountWithUsername("parham");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void addNewAdminAccountTest1()
    {
        Database.initialize();
        String expected = "Exist account with this username.";
        String result = AdminManager.addNewAdminAccount("Admin", "a", "b", "c@c.c", "021", "123456", 1000);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void addNewAdminAccountTest2()
    {
        Database.initialize();
        String expected = "New admin account registered.";
        String result = AdminManager.addNewAdminAccount("test1", "a", "b", "c@c.c", "021", "123456", 1000);
        AdminManager.deleteUsername("test1");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void addNewAdminAccountTest3()
    {
        String expected = "Invalid Username";
        String result = AdminManager.addNewAdminAccount("@@@@@", "a", "b", "c@c.c", "021", "123456", 1000);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void addNewAdminAccountTest4()
    {
        String expected = "Exist account with this username.";
        String result = AdminManager.addNewAdminAccount("Admin", "a", "b", "test@gmail.com", "05345434234", "Admin", 1000);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void showRequestWithIDTest()
    {
        String expected = "No request with this id";
        String result = AdminManager.showRequestByiId(-1);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void showAllRequestTest()
    {
        Database.initialize();
        ArrayList<Request> requests = Database.getAllRequest();
        StringBuilder mid = new StringBuilder();
        for (Request request : requests) {
            mid.append(request.show() + "\n+++++++++++++++++++++++++++++++++++++++\n");
        }
        String expected = mid.toString();
        String result = AdminManager.showAllRequests();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void acceptOrRejectRequestTest()
    {
        String expected = "No request with this id";
        String result = AdminManager.acceptOrRejectRequest(-1, true);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void deleteUserNameTest1()
    {
        String expected = "No user with this username";
        String result = AdminManager.deleteUsername("testUsername");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void deleteUserNameTest2()
    {
        AdminManager.addNewAdminAccount("test", "test", "test", "test@gmail.com", "09121111111", "testing", 1000);
        String expected = "Account deleted";
        String result = AdminManager.deleteUsername("test");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void editCategoryTest1()
    {
        String expected = "no such category";
        String result = AdminManager.editCategory("test", "name", "new");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void editCategoryTest2()
    {
        String expected = "";
    }

    @Test
    public void editCategoryTest3()
    {
        AdminManager.addNewCategory("Category", "Category", "Category");
        String expected = "changed successfully";
        String result = AdminManager.editCategory("Category", "name", "Category");
        Database.removeCategory(Database.getCategoryByName("Category"));

        Assert.assertEquals(expected, result);
    }

    @Test
    public void editCategoryTest4()
    {
        AdminManager.addNewCategory("Category", "Category", "Category");
        String expected = "changed successfully";
        String result = AdminManager.editCategory("Category", "parentName", "Category");
        Database.removeCategory(Database.getCategoryByName("Category"));

        Assert.assertEquals(expected, result);
    }

    @Test
    public void editCategoryTest5()
    {
        AdminManager.addNewCategory("Category", "Category", "Category");
        String expected = "changed successfully";
        String result = AdminManager.editCategory("Category", "feature", "Category");
        Database.removeCategory(Database.getCategoryByName("Category"));

        Assert.assertEquals(expected, result);
    }

    @Test
    public void editCategoryTest6()
    {
        AdminManager.addNewCategory("Category", "Category", "Category");
        String expected = "changed successfully";
        String result = AdminManager.editCategory("Category", "addNewSubCategory", "category");
        Database.removeCategory(Database.getCategoryByName("Category"));

        Assert.assertEquals(expected, result);
    }

    @Test
    public void editCategoryTest7()
    {
        Product product = new Product("test", "test", "test", true, 10, "test", "Category", 10000);
        Database.addAllProduct(product);
        AdminManager.addNewCategory("Category", "Category", "Category");
        String expected = "changed successfully";
        String result = AdminManager.editCategory("Category", "addNewProduct", Integer.toString(product.getProductId()));
        Database.removeProduct(product);
        Database.removeCategory(Database.getCategoryByName("Category"));

        Assert.assertEquals(expected, result);
    }

    @Test
    public void deleteCategoryTest1()
    {
        String expected = "no such category.";
        String result = AdminManager.deleteCategory("test");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void deleteCategoryTest2()
    {
        AdminManager.addNewCategory("Category", "Category", "Category");
        String expected = "category deleted";
        String result = AdminManager.deleteCategory("Category");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void deleteProductTest()
    {
        Product product = new Product("test", "test", "test", true, 10, "test", "Category", 10000);
        Database.addAllProduct(product);
        String expected = "Product removed successfully";
        String result = AdminManager.deleteProduct(product.getProductId());

        Assert.assertEquals(expected, result);
    }

}




