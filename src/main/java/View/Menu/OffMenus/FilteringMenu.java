package View.Menu.OffMenus;

import Controller.AllOffManager;
import View.Menu.Menu;

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
                System.out.println(AllOffManager.showFilterOption());
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
                    Matcher matcher2 = getMatcher(input, "^\\s*status\\s+[(Waiting to add...|Waiting to apply changes...|Accepted)]\\s*$");
                    Matcher matcher6 = getMatcher(input, "^\\s*back\\s*$");
                    if(matcher6.find())
                    {
                        this.parentMenu.show();
                        this.parentMenu.execute();
                        return;
                    }
                    else if(matcher1.find())
                    {
                        System.out.println(AllOffManager.addFilterOption("sellerUsername " + matcher1.group(1)));
                    }
                    else if(matcher2.find())
                    {
                        System.out.println(AllOffManager.addFilterOption("status " + matcher2.group(1)));
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
                System.out.println(AllOffManager.getFilterOptions());
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
                        System.out.println(AllOffManager.removeFilterOption(input));
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
