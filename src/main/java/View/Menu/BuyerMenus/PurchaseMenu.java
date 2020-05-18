package View.Menu.BuyerMenus;

import View.Menu.Menu;

import java.util.regex.Matcher;

public class PurchaseMenu extends Menu {

    private  Menu mySelf;

    public PurchaseMenu(Menu parentMenu) {
        super("Purchase Menu", parentMenu);
        super.addToSubMenus(1, this.getRegisterInfoMenu(this.parentMenu));
    }

    private Menu getRegisterInfoMenu(Menu parentMenu)
    {
        return new Menu("Register Information Menu", parentMenu) {
            @Override
            public void show() {
                System.out.println("Enter your address and phone number in format" +
                        "Address : (address)_PhoneNumber : (phone number)");
                System.out.println("Enter back to return or enter next to go next page");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                try{
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
                        Menu menu = getDiscountCodeMenu(this.parentMenu);
                        menu.show();
                        menu.execute();
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

    private static Menu getDiscountCodeMenu(Menu parentMenu)
    {
        return new Menu("Discount Code Menu", parentMenu) {
            @Override
            public void show() {
                System.out.println("Please enter your discount code");
                System.out.println("(Enter back to return or back to go next page)");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                try{
                    Matcher matcher1 = getMatcher(input, "^\\s*(\\d+)\\s*$");
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
                        Menu menu = getPaymentMenu(this.parentMenu);
                        menu.show();
                        menu.execute();
                    }
                    else if (matcher1.find())
                    {
                        Menu menu = getPaymentMenu(this.parentMenu);
                        menu.show();
                        menu.execute();
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

    private static Menu getPaymentMenu(Menu parentMenu)
    {
        return new Menu("Payment Menu", parentMenu) {
            @Override
            public void show() { }

            @Override
            public void execute() {
                parentMenu.show();
                parentMenu.execute();
            }
        };
    }
}
