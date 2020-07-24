package Model.Messaging;

import Model.Account.Account;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Chat {
    private static int idNumber = 0;
    private ArrayList<Message> messages = new ArrayList<>();
    private ArrayList<Account> members;
    private TextField textField;
    private int id;
    private Button back = new Button("Back");

    public TextField getTextField() {
        return textField;
    }

    public Chat(ArrayList<Account> members) {
        this.members = members;
        id = idNumber++;
    }

    public int getId() {
        return id;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void addMember(Account member) {
        members.add(member);
    }

    public void deleteMember(Account member) {
        members.remove(member);
    }

    public ArrayList<String> getContent() {
        ArrayList<String> content = new ArrayList<>();
        for (Message message : messages) {
            content.add(message.getOwner() + ": " + message.getContent());
        }
        return content;
    }

    public ScrollPane showChat() {
        ScrollPane chatRoom = new ScrollPane();
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        ArrayList<String> content = getContent();
        for (String message : content) {
            vBox.getChildren().add(new Label(message));
        }
        TextField textField = new TextField();
        this.textField = textField;
        vBox.getChildren().add(back);
        vBox.getChildren().add(textField);
        chatRoom.setContent(vBox);
        return chatRoom;
    }

    public Button getBackButton() {
        return back;
    }
}
