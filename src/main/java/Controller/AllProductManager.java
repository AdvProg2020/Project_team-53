package Controller;

import Model.Product.Category;
import Model.Product.Product;
import Model.Account.SellerAccount;

import java.util.ArrayList;

public class AllProductManager {

    private ArrayList<Product> allProducts = Database.getAllProducts();
    private String sortedBy = "Default";
    private ArrayList<String> filterOptions = new ArrayList<>();

    /*public String goToProduct(int productId){
        if (Database.getProductByID(productId) == null){
            return "No product with this Id";
        }
        ProductManager.setProduct(Database.getProductByID(productId));
        return "Here is product menu";
    }*/

    /*public void backToAll(){
        setProduct(null);
    }*/

    public String showAllProduct(){

        allProducts = new ArrayList<>();
        allProducts.addAll(Database.getAllProducts());

        doFiltering();
        if (sortedBy.endsWith("name."))
            allProducts.sort(this::compareWithName);
        else if (sortedBy.endsWith("price."))
            allProducts.sort(this::compareWithPrice);
        else if (sortedBy.endsWith("score."))
            allProducts.sort(this::compareWithScore);

        StringBuilder ans = new StringBuilder();
        for (Product product : allProducts) {
            ans.append(product.digest());
            ans.append("\n----------------------\n");
        }
        return ans.toString();
    }

    public ArrayList<Product> showProductArray(){
        allProducts = new ArrayList<>();
        allProducts.addAll(Database.getAllProducts());

        doFiltering();
        if (sortedBy.endsWith("Name"))
            allProducts.sort(this::compareWithName);
        else if (sortedBy.endsWith("Price"))
            allProducts.sort(this::compareWithPrice);
        else if (sortedBy.endsWith("Score"))
            allProducts.sort(this::compareWithScore);
        return  allProducts;
    }

    public String setSortedBy(String sortedBy) {
        this.sortedBy = sortedBy;
        return sortedBy;
    }

    private int compareWithPrice(Product p1, Product p2){
        return p1.getPrice() > p2.getPrice() ? 1 : -1;
    }

    private int compareWithName(Product p1, Product p2){
        return p1.getName().compareTo(p2.getName());
    }

    private int compareWithScore(Product p1, Product p2){
        return p1.getAverageScore() > p2.getAverageScore() ? 1 : -1;
    }

    public String showSortOption(){
        return "Name \n Price \n Score \n";
    }

    public String getSortedBy(){
        return sortedBy;
    }

    public String showFilterOption(){
        return " sellerUsername (a username) \n" +
                " rangeOfPrice (lower bound , upper bound) \n" +
                " categoryName (category name) \n" +
                " available \n" +
                " rangeOfScore (double more than) " + '\n' +
                " companyName (name of a company) \n" +
                " productName (name of a product) \n" +
                " categoryFeature (feature of a category)";
    }

    public ArrayList<String> getFilterOptions() {
        return filterOptions;
    }

    public String addFilterOption(String filter){
        filterOptions.add(filter);
        return "Done!";
    }

    public String removeFilterOption(String filter){
        if (filterOptions.contains(filter)){
            filterOptions.remove(filter);
            return "done";
        }
        return "no such filter.";
    }

    private void filterWithSellerUsername(String username){
        ArrayList<Product> tempArray = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.isSeller(username)){
                tempArray.add(product);
            }
        }
        allProducts = tempArray;
    }

    public String showAllCategories(){
        ArrayList<Category> allCategories = Database.getAllCategories();
        StringBuilder res = new StringBuilder();
        for (Category category : allCategories) {
            res.append(category.showThisCategory());
        }
        return res.toString();
    }

    private void filterWithRangeOfPrice(int lower, int higher){
        ArrayList<Product> tempArray = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getPrice() >= lower && product.getPrice() <= higher){
                tempArray.add(product);
            }
        }
        allProducts = tempArray;
    }

    private void filterWithCategoryName(String categoryName){
        ArrayList<Product> tempArray = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getCategoryName().equalsIgnoreCase(categoryName)){
                tempArray.add(product);
            }
        }
        allProducts = tempArray;
    }

    private void filterWithAvailable(){
        ArrayList<Product> tempArray = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.isAvailable()){
                tempArray.add(product);
            }
        }
        allProducts = tempArray;
    }

    private void filterWitRangeOfScore(double moreThan){
        ArrayList<Product> tempArray = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getAverageScore() >= moreThan){
                tempArray.add(product);
            }
        }
        allProducts = tempArray;
    }

    private void filterWithCompanyName(String companyName) {
        ArrayList<Product> tempArray = new ArrayList<>();
        for (Product product : allProducts) {
            SellerAccount sellerAccount = (SellerAccount)Database.getAccountByUsername(product.getSellerUsername());
            if (sellerAccount != null && sellerAccount.getCompany().equalsIgnoreCase(companyName)){
                tempArray.add(product);
            }
        }
        allProducts = tempArray;
    }

    private void filterWithProductName(String productName) {
        ArrayList<Product> tempArray = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getName().equalsIgnoreCase(productName)){
                tempArray.add(product);
            }
        }
        allProducts = tempArray;

    }

    private void filterWithCategoryFeature(String feature) {
        ArrayList<Product> tempArray = new ArrayList<>();
        for (Product product : allProducts) {
            Category category = Database.getCategoryByName(product.getCategoryName());
            if (category.getFeature().equalsIgnoreCase(feature)){
                tempArray.add(product);
            }
        }
        allProducts = tempArray;

    }

    private void filterWithHAveOff() {
        ArrayList<Product> tempArray = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.doesHaveOff()){
                tempArray.add(product);
            }
        }
        allProducts = tempArray;
    }

    private void doFiltering(){

        for (String filterOption : filterOptions) {
            System.out.println(filterOption);

            if (filterOption.startsWith("Seller Username")){
                filterWithSellerUsername(filterOption.split(" ")[2]);
            }
            else if (filterOption.startsWith("Range Of Price")){
                int x = Integer.parseInt(filterOption.split(" ")[3]);
                int y = Integer.parseInt(filterOption.split(" ")[4]);
                filterWithRangeOfPrice(x, y);
            }
            else if (filterOption.startsWith("Available")){
                filterWithAvailable();
            }
            else if (filterOption.startsWith("Category Name")){
                filterWithCategoryName(filterOption.split(" ")[2]);
            }
            else if (filterOption.startsWith("Higher Score Than")){
                double x = Double.parseDouble(filterOption.split(" ")[3]);
                filterWitRangeOfScore(x);
            }
            else if (filterOption.startsWith("Company Name")){
                filterWithCompanyName(filterOption.split(" ")[2]);
            }
            else if (filterOption.startsWith("Product Name")){
                filterWithProductName(filterOption.split(" ")[2]);
            }
            else if (filterOption.startsWith("Category Feature")){
                filterWithCategoryFeature(filterOption.split(" ")[2]);
            }
            else if (filterOption.startsWith("Have Off")){
                filterWithHAveOff();
            }
        }
    }


}
