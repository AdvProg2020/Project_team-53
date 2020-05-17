package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Discount {
    private static int allDiscountId = 1;

    private int discountId;
    private int maxValue;
    private int percent;
    private Date startDate;
    private Date endDate;
    private int numberOfTimes;

    public Discount(int maxValue, int percent, String startDate, String endDate, int numberOfTimes) {
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
        this.numberOfTimes = numberOfTimes;
        this.discountId = allDiscountId;
        allDiscountId++;
    }

    public int getDiscountId() {
        return discountId;
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

    public int getNumberOfTimes() {
        return numberOfTimes;
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

    public String showInfo() {
        return  "Discount{" + '\n' +
                "   discountId=" + discountId + '\n' +
                "   maxValue=" + maxValue + '\n'+
                "   percent=" + percent + '\n' +
                "   startDate=" + startDate + '\n' +
                "   endDate=" + endDate + '\n'+
                "   numberOfTimes=" + numberOfTimes + '\n'+
                '}';
    }

}
