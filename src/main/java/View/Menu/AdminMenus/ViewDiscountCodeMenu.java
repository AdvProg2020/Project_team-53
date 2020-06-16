package View.Menu.AdminMenus;

import Controller.AdminManager;
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
                System.out.println(AdminManager.showAllDiscount());
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
                System.out.println("Please enter the discountID\n(Enter back to return)");
            }
            @Override
            public void execute() {
                String input = scanner.nextLine();
                try
                {
                    Matcher matcher1 = getMatcher(input, "\\s*(\\d+)\\s*");
                    Matcher matcher2 = getMatcher(input, "^\\s*back\\s*$");
                    if(matcher2.find())
                    {
                        this.parentMenu.show();
                        this.parentMenu.execute();
                        return;
                    }
                    if(!matcher1.find())
                    {
                        throw new Exception("invalid input");
                    }
                  //  System.out.println(AdminManager.showDiscountWithId(Integer.parseInt(matcher1.group(1))));
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
                System.out.println("Please enter the discountID, field and new value of field\n(Enter back to return)");
            }
            @Override
            public void execute() {
                String input = scanner.nextLine();
                try
                {
                    Matcher matcher1 = getMatcher(input, "^\\s*(\\d+)\\s+(\\S+)\\s+(\\S+)\\s*$");
                    Matcher matcher2 = getMatcher(input, "^\\s*back\\s*$");
                    if(matcher2.find())
                    {
                        this.parentMenu.show();
                        this.parentMenu.execute();
                        return;
                    }
                    else if (!matcher1.find())
                    {
                        throw new Exception("invalid input");
                    }
                    System.out.println(AdminManager.editDiscount(Integer.parseInt(matcher1.group(1)), matcher1.group(2), matcher1.group(3)));
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
                System.out.println("Please enter discountID\n(Enter back to return)");
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
                    System.out.println(AdminManager.removeDiscount(Integer.parseInt(matcher1.group(1))));
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
