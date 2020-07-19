package View.Menu.SellerMenus;

import View.Menu.Menu;

public class MangeProductsMenu extends Menu {
    public MangeProductsMenu(Menu parentMenu) {
        super("Manage Products Menu", parentMenu);
        /*super.addToSubMenus(1, this.getViewProductMenu());
        super.addToSubMenus(2, this.getViewBuyersMenu());
        super.addToSubMenus(3, this.getEditProductMenu());*/
    }

    /*private Menu getViewProductMenu()
    {
        return new Menu("View Product Menu", this) {
            @Override
            public void show() {
                System.out.println("Please enter productID\n(Enter back to return)");
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
                    else if(!matcher1.find())
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

    private Menu getViewBuyersMenu()
    {
        return new Menu("View Buyers Menu", this) {
            @Override
            public void show() {
                System.out.println("Please enter productID\n(Enter back to return)");
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
                    else if(!matcher1.find())
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

    private Menu getEditProductMenu()
    {
        return new Menu("Edit Product Menu", this) {
            @Override
            public void show() {
                System.out.println("Please enter productID, field that you want to change, new value of that field in order\n(Enter back to return)");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                try
                {
                    Matcher matcher1 = getMatcher(input, "^\\s*(\\S+)\\s+(\\S+)\\s+(\\S+)\\s*$");
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
                    else
                    {
                        System.out.println(SellerManager.sendEditProductRequest(Integer.parseInt(matcher1.group(1)), matcher1.group(2), matcher1.group(3)));
                    }
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
