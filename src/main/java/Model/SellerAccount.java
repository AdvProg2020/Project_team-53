package Model;

import Model.Log.SellLog;

import java.util.ArrayList;

public class SellerAccount extends Account{
    private String company;
    private ArrayList<SellLog> sellLogs ;
    public SellerAccount(String username, String firstName, String lastName, String password, String email, String phoneNumber, int credit, String company) throws Exception{
        super(username, firstName, lastName, password, email, phoneNumber, credit);
        this.company = company;
        sellLogs = new ArrayList<>();
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

    @Override
    public String showInfo() {
        return super.showInfo() + '\n' +
                "company=" + company;

    }

    public void addSellLog(SellLog sellLog){
        sellLogs.add(sellLog);
    }


    public String showAllLog(){
        StringBuilder ans = new StringBuilder();
        for (SellLog log : sellLogs) {
            ans.append(log.toString());
        }
        return ans.toString();
    }
}
