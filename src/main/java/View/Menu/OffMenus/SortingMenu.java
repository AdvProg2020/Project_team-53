package View.Menu.OffMenus;

import Controller.AllOffManager;
import View.Menu.LoginMenu;
import View.Menu.Menu;

import java.util.regex.Matcher;

public class SortingMenu extends Menu {

    public SortingMenu(Menu parentMenu) {
        super("Sorting Menu", parentMenu);
        super.addToSubMenus(1, this.getShowAvailableSortsMenu());
        super.addToSubMenus(2, this.getSortMenu());
        super.addToSubMenus(3, this.getCurrentSortMenu());
        super.addToSubMenus(4, this.getDisableSortMenu());
        super.addToSubMenus(5, new LoginMenu(this));
    }

    private Menu getShowAvailableSortsMenu()
    {
        return new Menu("Show Available Sorts Menu", this) {
            @Override
            public void show() {
                System.out.println("Available sorts are:\n(Enter back to return)");
            }

            @Override
            public void execute() {
                System.out.println(AllOffManager.showSortOption());
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

    private Menu getSortMenu()
    {
        return new Menu("Sort Menu", this) {
            @Override
            public void show() {
                System.out.println("Please enter an available sort:(Enter back to return)");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                try
                {
                    Matcher matcher1 = getMatcher(input, "^\\s*(MaxValue|Percent|StartDate|EndDate)\\s*$");
                    Matcher matcher2 = getMatcher(input, "^\\s*back\\s*$");
                    if(matcher2.find())
                    {
                        this.parentMenu.show();
                        this.parentMenu.execute();
                        return;
                    }
                    else if(matcher1.find())
                    {
                        String sortBy = matcher1.group(1);
                        if(sortBy.equalsIgnoreCase("maxvalue"))
                        {
                            System.out.println(AllOffManager.sortByMaxValue());
                        }
                        else if (sortBy.equalsIgnoreCase("Percent"))
                        {
                            System.out.println(AllOffManager.sortByPercent());
                        }
                        else if(sortBy.equalsIgnoreCase("startDate"))
                        {
                            System.out.println(AllOffManager.sortByStartDate());
                        }
                        else if(sortBy.equalsIgnoreCase("EndDate"))
                        {
                            System.out.println(AllOffManager.sortByEndDate());
                        }
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

    private Menu getCurrentSortMenu()
    {
        return new Menu("Current Sort Menu", this) {
            @Override
            public void show() {
                System.out.println("Current sort is:\n(Enter back to return)");
            }

            @Override
            public void execute() {
                System.out.println(AllOffManager.getSortedBy());
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

    private Menu getDisableSortMenu()
    {
        return new Menu("Disable Sort Menu", this) {
            @Override
            public void show() {
                System.out.println("Enter back to return");
            }

            @Override
            public void execute() {
                System.out.println(AllOffManager.ignoreSort());
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
}
