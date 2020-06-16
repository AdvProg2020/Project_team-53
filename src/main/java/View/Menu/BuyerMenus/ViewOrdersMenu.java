package View.Menu.BuyerMenus;

import Controller.AccountManager;
import View.Menu.Menu;

import java.util.regex.Matcher;

public class ViewOrdersMenu extends Menu {
    public ViewOrdersMenu(Menu parentMenu) {
        super("View Orders Menu", parentMenu);
        super.addToSubMenus(1, this.getShowOrderMenu());
        super.addToSubMenus(2, this.getGiveRateMenu());
    }

    private Menu getShowAllOrdersMenu()
    {
        return new Menu("Show All Orders Menu", this) {
            @Override
            public void show() {
                System.out.println("All orders are:\n(Enter back to return)");
            }

            @Override
            public void execute() {
                System.out.println(AccountManager.viewLogs());
                String input = scanner.nextLine();
                try
                {
                    Matcher matcher2 = getMatcher(input, "^\\s*back\\s*$");
                    if(matcher2.find())
                    {
                        this.parentMenu.show();
                        this.parentMenu.execute();
                        return;
                    }
                    else
                        throw new Exception("Invalid Input");
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
                this.execute();
            }
        };
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
                try
                {
                    Matcher matcher1 = getMatcher(input, "^\\s*(\\d+)\\s*$");
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
                      //  System.out.println(AccountManager.viewLogsWithId(Integer.parseInt(matcher1.group(1))));
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
                        System.out.println();
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
