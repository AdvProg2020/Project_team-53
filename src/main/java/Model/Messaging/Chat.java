package Model.Messaging;

import Model.Account.Account;

import java.util.ArrayList;

public class Chat {
    private ArrayList<Message> messages = new ArrayList<>();
    private ArrayList<Account> members;

    public Chat(ArrayList<Account> members) {
        this.members = members;
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
}
