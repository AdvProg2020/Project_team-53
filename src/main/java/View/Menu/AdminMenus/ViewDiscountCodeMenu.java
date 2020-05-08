package View.Menu.AdminMenus;

import View.Menu.Menu;

import java.util.regex.Matcher;

public class ViewDiscountCodeMenu extends Menu {

    public ViewDiscountCodeMenu(Menu parentMenu) {
        super("View Discount Codes Menu", parentMenu);
        super.addToSubMenus(1, this.getShowAllDiscountsMenu());
        super.addToSubMenus(2, this.getViewDiscountCodeMenu());
        super.addToSubMenus(3, this.getEditDiscountCodeMenu());
        super.addToSubMenus(4, this.getRemoveDiscountCodeMenu());
    }

    private Menu getShowAllDiscountsMenu()
    {
        return new Menu("Show All Discounts Menu", this) {
            @Override
            public void show()
            {
                System.out.println("All discounts are:\n(Enter back to return)");
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
    private Menu getViewDiscountCodeMenu()
    {
        return new Menu("View Discount Code Menu", this) {
            @Override
            public void show()
            {
                System.out.println("Please enter the code\n(Enter back tp return)");
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

    private Menu getEditDiscountCodeMenu()
    {
        return new Menu("Edit Discount Code Menu", this) {
            @Override
            public void show()
            {
                System.out.println("Please enter the code\n(Enter back tp return)");
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

    private Menu getRemoveDiscountCodeMenu()
    {
        return new Menu("Remove Discount Code Menu", this) {
            @Override
            public void show()
            {
                System.out.println("Please enter the code\n(Enter back tp return)");
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
