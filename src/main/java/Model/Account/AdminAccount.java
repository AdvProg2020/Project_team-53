package Model.Account;

public class AdminAccount extends Account {
    public AdminAccount(String username, String firstName, String lastName, String password, String email, String phoneNumber, int credit) throws Exception {
        super(username, firstName, lastName, password, email, phoneNumber, credit);
    }
}
