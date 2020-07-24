package Controller;

import Model.Account.Account;
import Model.Messaging.Message;

public class ChatManager {

    public void addMessage(int chatId, String content, Account account){
        Database.getChatById(chatId).addMessage(new Message(account, content));
    }



}
