package View.Menu.BuyerMenus;

import View.Menu.Menu;

import java.util.regex.Matcher;

public class ViewOrdersMenu extends Menu {
    public ViewOrdersMenu(Menu parentMenu) {
        super("View Orders Menu", parentMenu);
        super.addToSubMenus(1, this.getShowOrderMenu());
        super.addToSubMenus(2, this.getGiveRateMenu());
    }

    private Menu getShowOrderMenu()
    {
        return new Menu("Show Order Menu", this) {
            @Override
            public void show() {
                System.out.println("Please enter your orderID :\n(Enter back to return)");
            }

            @Override
            public void execute()
            {
                String input = scanner.nextLine();
                //todo
                this.execute();
            }
        };
    }

    private Menu getGiveRateMenu()
    {
        return new Menu("Give Rate Menu", this) {
            @Override
            public void show() {
                System.out.println("Please enter your productID and your rate 1 to 5:\n(Enter back to return");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                try
                {
                    Matcher matcher1 = getMatcher(input, "^\\s*(\\d+)\\s+([1-5])\\s*$");
                    Matcher matcher2 = getMatcher(input, "^\\s*back\\s*$");
                    if(matcher2.find())
                    {
                        this.parentMenu.show();
                        this.parentMenu.execute();
                        return;
                    }
                    else if(!matcher1.find())
                    {
                        throw new Exception("invalid input");
                    }
                    else {
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
}
