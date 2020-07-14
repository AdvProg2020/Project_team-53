package Model;

import Model.Account.Account;

import java.util.ArrayList;

public class Chat {
    private ArrayList<String> supporterMessages = new ArrayList<>();
    private ArrayList<String> userMessages = new ArrayList<>();
    private Account user;
    private Account supporter;

    public Chat(Account user, Account supporter) {
        this.user = user;
        this.supporter = supporter;
    }

    public void sendSupporterMessage(String message) {
        supporterMessages.add(message);
    }

    public void sendUserMessage(String message) {
        userMessages.add(message);
    }

    public ArrayList<String> getSupporterMessages() {
        return supporterMessages;
    }

    public ArrayList<String> getUserMessages() {
        return userMessages;
    }

    public Account getUser() {
        return user;
    }

    public Account getSupporter() {
        return supporter;
    }
}
