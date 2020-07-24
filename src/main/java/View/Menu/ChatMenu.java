package View.Menu;

import Model.Messaging.Chat;
import Model.Messaging.Message;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.lang.reflect.Type;

public class ChatMenu extends Menu {
    private Chat chat;
    private int chatId;


    public ChatMenu(Menu parentMenu, int chatId) {
        super("chat menu", parentMenu);
        this.chatId = chatId;
    }


    @Override
    public void show() {
        setChat();
        super.setPane();
        mainPane.getChildren().add(chat.showChat());
        Scene scene = new Scene(super.mainPane, 1000, 600);
        window.setScene(scene);
        execute();
    }

    @Override
    public void execute() {
        TextField textField = chat.getTextField();
        chat.getBackButton().setOnAction(e -> {
            parentMenu.show();
        });
        textField.setOnAction(e -> {
            // if e == enter.key
            chat.addMessage(new Message(account, textField.getText()));
            try {
            dataOutputStream.writeUTF(  "addMessage"+ chatId + " " + textField.getText());
                dataOutputStream.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        while (true) {
            setChat();
        }
    }

    private void setChat()  {
        try {
            dataOutputStream.writeUTF("getChatById " + chatId);
            String input = dataInputStream.readUTF();
            if (input == null) return;
            Type type = new TypeToken<Chat>(){}.getType();
            Chat updatedChat = new Gson().fromJson(input, type);
            chat = updatedChat;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
