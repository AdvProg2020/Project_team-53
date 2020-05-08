package View.Menu.SellerMenus;

import View.Menu.Menu;

import java.util.regex.Matcher;

public class MangeProductsMenu extends Menu {
    public MangeProductsMenu(Menu parentMenu) {
        super("Manage Products Menu", parentMenu);
        super.addToSubMenus(1, this.getViewProductMenu());
        super.addToSubMenus(2, this.getViewBuyersMenu());
        super.addToSubMenus(3, this.getEditProductMenu());
    }

    private Menu getViewProductMenu()
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
}
