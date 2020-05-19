package View.Menu.ProductsMenus;

import Controller.AllProductManager;
import View.Menu.LoginMenu;
import View.Menu.Menu;

import java.util.regex.Matcher;

public class FilteringMenu extends Menu {

    public FilteringMenu(Menu parentMenu) {
        super("Filtering Menu", parentMenu);
        super.addToSubMenus(1, this.getShowAvailableFiltersMenu());
        super.addToSubMenus(2, this.getAddFilterMenu());
        super.addToSubMenus(3, this.getCurrentFiltersMenu());
        super.addToSubMenus(4, this.getDisableFilterMenu());
        super.addToSubMenus(5, new LoginMenu(this));
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
                System.out.println(AllProductManager.showFilterOption());
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
                    Matcher matcher1 = getMatcher(input, "^\\s*sellerUsername\\s+(\\S+)\\s*$");
                    Matcher matcher2 = getMatcher(input, "^\\s*rangeOfPrice\\s+(\\d+)\\s+(\\d+)\\s*$");
                    Matcher matcher3 = getMatcher(input, "^\\s*categoryName\\s+(\\S+)\\s*$");
                    Matcher matcher4 = getMatcher(input, "^\\s*available\\s*$");
                    Matcher matcher5 = getMatcher(input, "^\\s*rangeOfScore\\s+(\\d[\\.\\d+]?)$");
                    Matcher matcher6 = getMatcher(input, "^\\s*companyName\\s+(\\S+)\\s*$");
                    Matcher matcher7 = getMatcher(input, "^\\s*productName\\s+(\\S+)\\s*$");
                    Matcher matcher8 = getMatcher(input, "^\\s*categoryFeature\\s+(\\S+)\\s*$");
                    Matcher matcher9 = getMatcher(input, "^\\s*back\\s*$");
                    if(matcher9.find())
                    {
                        this.parentMenu.show();
                        this.parentMenu.execute();
                        return;
                    }
                    else if(matcher1.find())
                    {
                        System.out.println(AllProductManager.addFilterOption("sellerUsername " + matcher1.group(1)));
                    }
                    else if(matcher2.find())
                    {
                        System.out.println(AllProductManager.addFilterOption("rangeOfPrice " + matcher2.group(1) + " " + matcher2.group(2)));
                    }
                    else if(matcher3.find())
                    {
                        System.out.println(AllProductManager.addFilterOption("categoryName " + matcher3.group(1)));
                    }
                    else if(matcher4.find())
                    {
                        System.out.println(AllProductManager.addFilterOption("available"));
                    }
                    else if(matcher5.find())
                    {
                        System.out.println(AllProductManager.addFilterOption("rangeOfScore " + matcher5.group(1)));
                    }
                    else if (matcher6.find())
                    {
                        System.out.println(AllProductManager.addFilterOption("companyName " + matcher6.group(1)));
                    }
                    else if (matcher7.find())
                    {
                        System.out.println(AllProductManager.addFilterOption("productName " + matcher7.group(1)));
                    }
                    else if (matcher8.find())
                    {
                        System.out.println(AllProductManager.addFilterOption("categoryFeature " + matcher8.group(1)));
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

    private Menu getCurrentFiltersMenu()
    {
        return new Menu("Current Filters Menu", this) {
            @Override
            public void show() {
                System.out.println("Current filters are:\n(Enter back to return)");
            }

            @Override
            public void execute() {
                System.out.println(AllProductManager.getFilterOptions());
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
                    Matcher matcher2 = getMatcher(input, "^\\s*back\\s*$");
                    if(matcher2.find())
                    {
                        this.parentMenu.show();
                        this.parentMenu.execute();
                        return;
                    }
                    else
                    {
                        System.out.println(AllProductManager.removeFilterOption(input));
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
