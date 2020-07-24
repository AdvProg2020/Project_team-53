package View.Menu;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ChatRoomMenu extends Menu {
    public ChatRoomMenu(Menu parentMenu) {
        super("ChatRoom", parentMenu);
    }

    public void show() {
        super.setPane();
        ScrollPane scrollPane = new ScrollPane();
        VBox vBox = getSupporters();
        vBox.setSpacing(10);
        scrollPane.setContent(vBox);
        mainPane.getChildren().add(scrollPane);
        Scene scene = new Scene(mainPane, 1000, 600);
        window.setScene(scene);
        window.show();
    }

    private VBox getSupporters() {
        VBox vBox = new VBox();
        try {
            dataOutputStream.writeUTF("getAllSupporters");
            dataOutputStream.flush();
            String input = dataInputStream.readUTF();
            String[] splitInput = input.split("\\n");
            for (String s : splitInput) {
                Button button = new Button( "User: " + s);
                vBox.getChildren().add(button);
                button.setOnAction(e -> {
                    String[] split = button.getText().split(" ");
                    try {
                    dataOutputStream.writeUTF("chatWith " + split[1]);
                        new ChatMenu(this, dataInputStream.readInt());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Button button = new Button("back");
        button.setOnAction(e -> {
            parentMenu.show();
        });
        vBox.getChildren().add(button);
        return vBox;
    }
}
