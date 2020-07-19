package Model.Product.DiscountAndOff;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Off {
    private static int allOffIds = 1;

    private int offId;
    private int maxValue;
    private int percent;
    private Date startDate;
    private Date endDate;
    private ArrayList<Integer> productIds;
    private String status;
    private String sellerUsername;

    public static int getAllOffIds() {
        return allOffIds;
    }

    public static void setAllOffIds(int allOffIds) {
        Off.allOffIds = allOffIds;
    }

    public Off(int maxValue, int percent, String startDate, String endDate, String sellerUsername, ArrayList<Integer> productIds) {
        this.maxValue = maxValue;
        this.percent = percent;
        try {
            this.startDate = new SimpleDateFormat("yyyy-MM-dd_HH:mm").parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            this.endDate = new SimpleDateFormat("yyyy-MM-dd_HH:mm").parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.sellerUsername = sellerUsername;
        this.productIds = productIds;
        this.offId = allOffIds;
        allOffIds++;
        this.status = "Waiting to add...";
    }

    public int getOffId() {
        return offId;
    }

    public ArrayList<Integer> getProductIds() {
        return productIds;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public int getPercent() {
        return percent;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getStatus() {
        return status;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public void setStartDate(String startDate) {
        try {
            this.startDate = new SimpleDateFormat("yyyy-MM-dd_HH:mm").parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setEndDate(String endDate) {
        try {
            this.endDate = new SimpleDateFormat("yyyy-MM-dd_HH:mm").parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String showInfo(){
        return "Off{" + '\n' +
                "   offId=" + offId + '\n' +
                "   maxValue=" + maxValue + '\n' +
                "   percent=" + percent + '\n' +
                "   startDate=" + startDate + '\n' +
                "   endDate=" + endDate + '\n' +
                "   productIds=" + productIds + '\n' +
                "   status=" + status + '\n' +
                "   sellerUsername=" + sellerUsername + '\n' +
                '}' ;
    }

    public String showOff(){
        return "Off{" + '\n' +
                "   offId=" + offId + '\n' +
                "   maxValue=" + maxValue + '\n' +
                "   percent=" + percent +'\n' +
                "   startDate=" + startDate +'\n' +
                "   endDate=" + endDate +'\n' +
                "   productIds=" + productIds + '\n' +
                "   status=" + status + '\n' +
                "   sellerUsername=" + sellerUsername + '\n' +
                '}';
    }

    public Pane showOffFullInfoGraphic() {
        Off off = this;

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);


        Label offIdLabel = new Label("OffID : " + off.getOffId());
        Label sellerUsername = new Label("Seller Username : " + off.getSellerUsername());
        Label maxValue = new Label("Max Value : " + off.getMaxValue());
        Label percent = new Label("Percent : " + off.getPercent());
        Label startDate = new Label("Start Date : " + off.getStartDate());
        Label endDate = new Label("End Date : " + off.getEndDate());
        Label status = new Label("Status : " + off.getStatus());
        Label productIdsTag = new Label("Products : ");
        Label productIds = new Label(off.getProductIds().toString());

        GridPane.setConstraints(offIdLabel, 0, 1 , 2 , 1);
        GridPane.setConstraints(sellerUsername, 0, 2 , 2 , 1);
        GridPane.setConstraints(maxValue, 0, 3 , 2 , 1);
        GridPane.setConstraints(percent, 0, 4 , 2 , 1);
        GridPane.setConstraints(startDate, 0, 5 , 2 , 1);
        GridPane.setConstraints(endDate, 0, 6 , 2 , 1);
        GridPane.setConstraints(status, 0, 7 , 2 , 1);
        GridPane.setConstraints(productIdsTag, 0, 9 , 2 , 1);
        GridPane.setConstraints(productIds, 1, 10 , 2 , 3);

        gridPane.getChildren().addAll(offIdLabel, sellerUsername, maxValue, percent, startDate,endDate,status,productIdsTag,productIds);

        return gridPane;
    }


}
