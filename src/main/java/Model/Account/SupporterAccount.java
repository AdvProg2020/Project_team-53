package Model.Account;

import Model.Messaging.Chat;

import java.util.ArrayList;

public class SupporterAccount extends Account {
    private ArrayList<Chat> chats = new ArrayList<>();

    public SupporterAccount(String username, String firstName, String lastName, String password, String email, String phoneNumber, int credit) throws Exception {
        super(username, firstName, lastName, password, email, phoneNumber, credit);
    }


}
