package Model;

public abstract class Account {
    protected String username;
    protected String lastName;
    protected String password;
    protected String email;
    protected String phoneNumber;
    protected int credit;

    public String getUsername() {
        return username;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getCredit() {
        return credit;
    }

    public void setUsername(String username) throws Exception{
        if (!username.matches("[A-Za-z_0-9]+")){
            throw new Exception("Invalid Username");
        }
        this.username = username;
    }

    public void setLastName(String lastName) throws Exception{
        if (!lastName.matches("[A-Za-z]+")){
            throw new Exception("Invalid LastName");
        }
        this.lastName = lastName;
    }

    public void setPassword(String password) throws Exception{
        if (password.length() < 5 || !password.matches("\\S+")){
            throw new Exception("Weak or Invalid Password");
        }
        this.password = password;
    }

    public void setEmail(String email) throws Exception {
        if (!email.matches("\\S+@\\S+\\.\\S+")){
            throw new Exception("Invalid Email");
        }
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) throws Exception {
        if (!phoneNumber.matches("\\d+"))
            throw new Exception("Invalid PhoneNumber");
        this.phoneNumber = phoneNumber;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
