 package Controller;

import Model.Product.DiscountAndOff.Off;

import java.util.ArrayList;

public class AllOffManager {

    private static String sortedBy = "default sort.";
    private static ArrayList<Off> allOffs = new ArrayList<>();
    private static ArrayList<String> filterOptions = new ArrayList<>();

    public static String ignoreSort(){
        sortedBy = "default sort.";
        return  "sort ignored";
    }

    public static String sortByMaxValue(){
        sortedBy = "Sorted by maxValue.";
        return "sorted with maxValue.";
    }

    public static String sortByPercent(){
        sortedBy = "Sorted by percent.";
        return "sorted with percent.";
    }

    public static String sortByStartDate(){
        sortedBy = "Sorted by startDate.";
        return "sorted with startDate.";
    }

    public static String sortByEndDate(){
        sortedBy = "Sorted by endDate.";
        return "sorted with endDate.";
    }

    public static String showSortOption(){
        return "MaxValue \n Percent \n StartDate \n EndDate";
    }

    public static String getSortedBy(){
        return sortedBy;
    }

    public static String showAllOffs(){

        allOffs = Database.getAllOffs();

        doFiltering();
        if (sortedBy.endsWith("maxValue."))
            allOffs.sort(AllOffManager::compareWithMaxValue);
        else if (sortedBy.endsWith("percent."))
            allOffs.sort(AllOffManager::compareWithPercent);
        else if (sortedBy.endsWith("startDate."))
            allOffs.sort(AllOffManager::compareWithStartDate);
        else if (sortedBy.endsWith("endDate."))
            allOffs.sort(AllOffManager::compareWithEndDate);

        StringBuilder ans = new StringBuilder();
        for (Off off : allOffs) {
            ans.append(off.showOff());
            ans.append("\n----------------------\n");
        }
        return ans.toString();
    }

    private static int compareWithMaxValue(Off o1, Off o2){
        return o1.getMaxValue() > o2.getMaxValue() ? 1 : -1;
    }

    private static int compareWithPercent(Off o1, Off o2){
        return o1.getPercent() > o2.getPercent() ? 1 : -1;
    }

    private static int compareWithStartDate(Off o1, Off o2){
        return o1.getStartDate().compareTo(o2.getStartDate());
    }

    private static int compareWithEndDate(Off o1, Off o2){
        return o1.getEndDate().compareTo(o2.getEndDate());
    }

    public static String showFilterOption(){
        return "sellerUsername (a username) \n status (Waiting to add...\\Waiting to apply changes...\\Accepted)";
    }

    public static String getFilterOptions() {
        return filterOptions.toString();
    }

    public static String addFilterOption(String filter){
        filterOptions.add(filter);
        return "Done!";
    }

    public static String removeFilterOption(String filter){
        if (filterOptions.contains(filter)){
            filterOptions.remove(filter);
            return "done";
        }
        return "no such filter.";
    }

    private static void filterWithSellerUsername(String username){
        ArrayList<Off> tempArray = new ArrayList<>();
        for (Off off : allOffs) {
            if (off.getSellerUsername().equalsIgnoreCase(username)){
                tempArray.add(off);
            }
        }
        allOffs = tempArray;
    }

    private static void filterWithStatus(String status){
        ArrayList<Off> tempArray = new ArrayList<>();
        for (Off off : allOffs) {
            if (off.getStatus().contains(status)){
                tempArray.add(off);
            }
        }
        allOffs = tempArray;
    }

    private static void doFiltering(){

        for (String filterOption : filterOptions) {
            if (filterOption.startsWith("sellerUsername")){
                filterWithSellerUsername(filterOption.split(" ")[1]);
            }
            else if (filterOption.startsWith("status")){
                filterWithStatus(filterOption.split(" ")[1]);
            }
        }
    }

}
