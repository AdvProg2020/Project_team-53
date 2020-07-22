package View.Menu;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ChatMenu extends Menu {


    public ChatMenu(Menu parentMenu) {
        super("chat menu", parentMenu);
    }


    @Override
    public void show() {
        super.setPane();
        VBox vBox = getVBox();

        Scene scene = new Scene(super.mainPane, 1000, 600);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        scrollPane.setContent(vBox);

        mainPane.getChildren().add(scrollPane);

        window.setScene(scene);
    }

    private VBox getVBox() {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);


        return vBox;
    }


}
