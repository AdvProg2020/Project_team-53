package Model.Product.DiscountAndOff;

import Model.Account.Account;
import Model.Account.BuyerAccount;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

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

    public static int getAllDiscountId() {
        return allDiscountId;
    }

    public static void setAllDiscountId(int allDiscountId) {
        Discount.allDiscountId = allDiscountId;
    }

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

    public void setNumberOfTimes(int numberOfTimes) {
        this.numberOfTimes = numberOfTimes;
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

    public Pane viewDiscountInGraphic(Account account) {
        Discount discount = this;

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Label disID = new Label("DiscountId : " + discount.getDiscountId());
        Label maxValue = new Label("Max Value : " + discount.getMaxValue());
        Label percent = new Label("Percent : " + discount.getPercent());
        Label startDate = new Label("Start Date : " + discount.getStartDate());
        Label endDate = new Label("End Date : " + discount.getEndDate());
        int x = discount.getNumberOfTimes();
        if (account instanceof BuyerAccount)
            x -= ((BuyerAccount) account).getNumberOfUse(discountId);
        Label remainigTimes = new Label("Times Of Use : " + x);


        GridPane.setConstraints(disID, 0 , 0);
        GridPane.setConstraints(maxValue, 0, 1);
        GridPane.setConstraints(percent, 0, 2);
        GridPane.setConstraints(startDate, 0, 3);
        GridPane.setConstraints(endDate, 0, 4);
        GridPane.setConstraints(remainigTimes, 0, 5);

        gridPane.getChildren().addAll(disID, maxValue, percent, startDate, endDate, remainigTimes);

        return gridPane;

    }


}
