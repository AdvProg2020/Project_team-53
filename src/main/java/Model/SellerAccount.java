package Model;

public class SellerAccount extends Account{
    private String company;

    public SellerAccount(String username, String firstName, String lastName, String password, String email, String phoneNumber, int credit, String company) {
        super(username, firstName, lastName, password, email, phoneNumber, credit);
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) throws Exception {
        if (!company.matches("\\S+")){
            throw new Exception("Invalid Company Name");
        }
        this.company = company;
    }
}
