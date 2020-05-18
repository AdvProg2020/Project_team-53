package View.Menu.BuyerMenus;

import View.Menu.Menu;

import java.util.regex.Matcher;

public class PurchaseMenu extends Menu {

    private Menu grandpaMenu;

    public PurchaseMenu(Menu parentMenu) {
        super("Purchase Menu", parentMenu);
    }

    private Menu getDiscountCodeMenu()
    {
        return new Menu("Discount Code Menu", this) {
            @Override
            public void show() {
                System.out.println("Please enter your discount code");
                System.out.println("(Enter back to return or back to go next page)");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                try{
                    Matcher matcher1 = getMatcher(input, "^\\s*(\\S+)\\s*$");
                    Matcher matcher2 = getMatcher(input, "^\\s*next\\s*$");
                    Matcher matcher3 = PurchaseMenu.getMatcher(input, "\\s*back\\s*");
                    if (matcher3.find())
                    {
                        this.parentMenu.show();
                        this.parentMenu.execute();
                        return;
                    }
                    else if (matcher2.find())
                    {

                    }
                    else if (matcher1.find())
                    {

                    }
                    else
                    {
                        throw new Exception("invalid input");
                    }
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
                this.execute();
            }
        };
    }

    private Menu getPaymentMenu()
    {
        return new Menu("Payment Menu", this) {
            @Override
            public void show() {
                super.show();
            }

            @Override
            public void execute() {
                grandpaMenu.show();
                grandpaMenu.execute();
            }
        };
    }
}
