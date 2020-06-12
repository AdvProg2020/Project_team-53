package Model.Request;

import Controller.Database;
import Model.Product.DiscountAndOff.Off;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class EditOffRequest extends Request{
    String field;
    String changeTo;
    int offId;

    public EditOffRequest(String field, String changeTo, int offId) {
        this.field = field;
        this.changeTo = changeTo;
        this.offId = offId;
        Off off = Database.getOffById(offId);
        off.setStatus("Waiting to apply changes...");
    }

    @Override
    public String acceptRequest() {
        Off off = Database.getOffById(offId);
        if (off == null){
            return "off not found";
        }
        if (field.equalsIgnoreCase("startDate")){
            off.setStartDate(changeTo);
        }
        else if (field.equalsIgnoreCase("endDate")){
            off.setEndDate(changeTo);
        }
        else if (field.equalsIgnoreCase("percent")){
            off.setPercent(Integer.parseInt(changeTo));
        }
        else if (field.equalsIgnoreCase("maxValue")){
            off.setMaxValue(Integer.parseInt(changeTo));
        }
        off.setStatus("Accepted");
        return "Off changed successfully";
    }

    @Override
    public String show() {
        return "EditOffRequest{" + '\n' +
                "   requestId="+ getId()+'\n'+
                "   field=" + field + '\n' +
                "   changeTo=" + changeTo + '\n' +
                "   offId=" + offId + '\n' +
                '}';
    }

    @Override
    public Pane showGraphical() {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Label reqID = new Label("RequestID : " + super.getId());
        Label offID = new Label("OffID : " + offId);
        Label changeField = new Label("Field : " + field);
        Label changeToLabel = new Label("Change To : " + changeTo);

        GridPane.setConstraints(reqID, 0, 0);
        GridPane.setConstraints(offID, 0,1);
        GridPane.setConstraints(changeField, 0, 2);
        GridPane.setConstraints(changeToLabel, 0, 3);

        gridPane.getChildren().addAll(reqID, offID, changeField, changeToLabel);

        return gridPane;
    }
}
