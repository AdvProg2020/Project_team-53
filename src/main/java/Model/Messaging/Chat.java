package Model.Messaging;

import Model.Account.Account;

import java.util.ArrayList;

public class Chat {
    ArrayList<Message> messages = new ArrayList<>();
    private Account user;
    private Account supporter;

    public Chat(Account user, Account supporter) {
        this.user = user;
        this.supporter = supporter;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public Account getUser() {
        return user;
    }

    public Account getSupporter() {
        return supporter;
    }
}
