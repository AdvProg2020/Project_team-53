package View.Menu.AminMenus;

import View.Menu.Menu;

import java.util.regex.Matcher;

public class ManageRequestsMenu extends Menu {
    public ManageRequestsMenu(Menu parentMenu) {
        super("Manage Requests Menu", parentMenu);
        super.addToSubMenus(1, this.getShowAllRequestsMenu());
        super.addToSubMenus(2, this.getRequestsDetailsMenu());
        super.addToSubMenus(3, this.getAcceptRequestsMenu());
        super.addToSubMenus(4, this.getDeclineRequestsMenu());
    }

    private Menu getShowAllRequestsMenu()
    {
        return new Menu("Show All Requests Menu",this) {
            @Override
            public void show() {
                System.out.println("All requests are:\n(Enter back to return");
            }

            @Override
            public void execute() {
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

    private Menu getRequestsDetailsMenu()
    {
        return new Menu("Requests Details Menu", this) {
            @Override
            public void show()
            {
                System.out.println("Please enter requestID\n(Enter back to return)");
            }
            @Override
            public void execute() {
                String input = scanner.nextLine();
                try
                {
                    Matcher matcher1 = getMatcher(input, "");
                    Matcher matcher2 = getMatcher(input, "^\\s*back\\s*$");
                    if(matcher2.find())
                    {
                        this.parentMenu.show();
                        this.parentMenu.execute();
                        return;
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

    private Menu getAcceptRequestsMenu()
    {
        return new Menu("Accept Requests Menu", this) {
            @Override
            public void show() {
                System.out.println("Please enter requestID\n(Enter back to return");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                try
                {
                    Matcher matcher1 = getMatcher(input, "");
                    Matcher matcher2 = getMatcher(input, "^\\s*back\\s*$");
                    if(matcher2.find())
                    {
                        this.parentMenu.show();
                        this.parentMenu.execute();
                        return;
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

    private Menu getDeclineRequestsMenu()
    {
        return new Menu("Decline Requests Menu", this) {
            @Override
            public void show() {
                System.out.println("Please enter requestID\n(Enter back to return");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                try
                {
                    Matcher matcher1 = getMatcher(input, "");
                    Matcher matcher2 = getMatcher(input, "^\\s*back\\s*$");
                    if(matcher2.find())
                    {
                        this.parentMenu.show();
                        this.parentMenu.execute();
                        return;
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
