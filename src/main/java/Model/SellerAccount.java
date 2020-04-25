package Model;

public class SellerAccount extends Account{
    private String company;

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
