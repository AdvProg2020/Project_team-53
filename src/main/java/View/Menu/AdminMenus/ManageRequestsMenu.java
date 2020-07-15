package View.Menu.AdminMenus;

import View.Menu.Menu;

public class ManageRequestsMenu extends Menu {
    public ManageRequestsMenu(Menu parentMenu) {
        super("Manage Requests Menu", parentMenu);
        /*super.addToSubMenus(1, this.getShowAllRequestsMenu());
        super.addToSubMenus(2, this.getRequestsDetailsMenu());
        super.addToSubMenus(3, this.getAcceptRequestsMenu());
        super.addToSubMenus(4, this.getDeclineRequestsMenu());*/
    }

    /*private Menu getShowAllRequestsMenu()
    {
        return new Menu("Show All Requests Menu",this) {
            @Override
            public void show() {
                System.out.println("All requests are:\n(Enter back to return)");
            }

            @Override
            public void execute() {
               // System.out.println(AdminManager.showAllRequests());
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
                   // System.out.println(AdminManager.showRequestByiId(Integer.parseInt(matcher1.group(1))));
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
                System.out.println("Please enter requestID\n(Enter back to return)");
            }

            @Override
            public void execute() {
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
                    System.out.println(AdminManager.acceptOrRejectRequest(Integer.parseInt(matcher1.group(1)), true));
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
                System.out.println("Please enter requestID\n(Enter back to return)");
            }

            @Override
            public void execute() {
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
                    System.out.println(AdminManager.acceptOrRejectRequest(Integer.parseInt(matcher1.group(1)), false));
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
                this.execute();
            }
        };
    }*/
}
