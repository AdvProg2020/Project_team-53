package Model;

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

    public Off(int maxValue, int percent, String startDate, String endDate, String sellerUsername, ArrayList<Integer> productIds) {
        this.maxValue = maxValue;
        this.percent = percent;
        try {
            this.startDate = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            this.endDate = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").parse(endDate);
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
}
