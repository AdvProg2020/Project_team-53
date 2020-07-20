package View.Menu;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class AuctionMenu extends Menu{
    public AuctionMenu(Menu parentMenu) {
        super("Auction Menu", parentMenu);
    }

    @Override
    public void show() {
        super.setPane();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        ArrayList<String> allAuctions = null;
        try {
            dataOutputStream.writeUTF("AllAuction");
            dataOutputStream.flush();
            allAuctions = new Gson().fromJson(dataInputStream.readUTF(), new TypeToken<ArrayList<String>>(){}.getType());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(mainPane, 1000, 600);
        scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
        scrollPane.getStyleClass().add("scroll-pane");
        super.mainPane.getStyleClass().add("main");
        Label info = new Label("All Auctions");
        info.setFont(Font.font(25));
        GridPane.setHalignment(info, HPos.CENTER);
        GridPane.setConstraints(info, 1, 0);
        gridPane.getChildren().add(info);
        int i = 1;
        for (String string : Objects.requireNonNull(allAuctions)) {
            String text = "";
            Label label = new Label();
            text = text + string;
            label.setText(text);
            label.setFont(Font.font(15));
            Button button = new Button("Join");
            button.setOnAction(e -> handleJoinAuction(string));
            button.setAlignment(Pos.CENTER);
            button.getStyleClass().add("dark-blue");
            button.setMaxWidth(Double.MAX_VALUE);
            GridPane.setConstraints(label, 0, i);
            GridPane.setConstraints(button, 2, i);
            gridPane.getChildren().addAll(label, button);
            i++;
        }

        scrollPane.setContent(gridPane);
        super.mainPane.setCenter(scrollPane);

        Menu.window.setScene(scene);
    }

    private void handleJoinAuction(String string)
    {
        try {
            String output ="JoinAuction "  + string.split(":")[1].split("_")[0];
            dataOutputStream.writeUTF(output);
            dataOutputStream.flush();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Process Result");
            alert.setContentText("You joined auction successfully");

            alert.showAndWait();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
