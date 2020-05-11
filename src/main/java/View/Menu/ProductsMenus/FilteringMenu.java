package View.Menu.ProductsMenus;

import View.Menu.Menu;
import sun.nio.cs.ext.MacCentralEurope;

import java.util.regex.Matcher;

public class FilteringMenu extends Menu {

    public FilteringMenu(Menu parentMenu) {
        super("Filtering Menu", parentMenu);
        super.addToSubMenus(1, this.getShowAvailableFiltersMenu());
        super.addToSubMenus(2, this.getAddFilterMenu());
        super.addToSubMenus(3, this.getCurrentFiltersMenu());
        super.addToSubMenus(4, this.getDisableFilterMenu());
    }

    private Menu getShowAvailableFiltersMenu()
    {
        return new Menu("Show Available Filters Menu", this) {
            @Override
            public void show() {
                System.out.println("Available filters are:\n(Enter back to return)");
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

    private Menu getAddFilterMenu()
    {
        return new Menu("Add Filters Menu", this) {
            @Override
            public void show() {
                System.out.println("Please enter filter to add:(Enter back to return)");
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

    private Menu getCurrentFiltersMenu()
    {
        return new Menu("Current Filters Menu", this) {
            @Override
            public void show() {
                System.out.println("Current filters are:\n(Enter back to return)");
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

    private Menu getDisableFilterMenu()
    {
        return new Menu("Disable Filter Menu", this) {
            @Override
            public void show() {
                System.out.println("Please enter filter to disable:(Enter back to return)");
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
