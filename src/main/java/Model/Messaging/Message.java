package Model.Messaging;

import Model.Account.Account;

public class Message {
  private Account owner;
  private String content;

  public Message(Account owner, String content) {
    this.owner = owner;
    this.content = content;
  }

  public Account getOwner() {
    return owner;
  }

  public String getContent() {
    return content;
  }
}
