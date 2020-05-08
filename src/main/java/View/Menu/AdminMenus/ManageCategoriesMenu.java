package View.Menu.AdminMenus;

import View.Menu.Menu;

import java.util.regex.Matcher;

public class ManageCategoriesMenu extends Menu {
    public ManageCategoriesMenu(Menu parentMenu) {
        super("Manage Categories Menu ", parentMenu);
        super.addToSubMenus(1, this.getShowAllCategoriesMenu());
        super.addToSubMenus(2, this.getEditCategoryMenu());
        super.addToSubMenus(3, this.getAddCategoryMenu());
        super.addToSubMenus(4, this.getRemoveCategoryMenu());
    }

    private Menu getShowAllCategoriesMenu()
    {
        return new Menu("Show All Categories Menu", this) {
            @Override
            public void show()
            {
                System.out.println("All categories are:\n(Enter back to return)");
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

    private Menu getEditCategoryMenu()
    {
        return new Menu("Edit Category Menu", this) {
            @Override
            public void show() {
                System.out.println("Please enter category name\n(Enter back to return)");
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

    private Menu getAddCategoryMenu()
    {
        return new Menu("Add Category Menu", this) {
            @Override
            public void show() {
                System.out.println("Please enter category name\n(Enter back to return)");
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

    private Menu getRemoveCategoryMenu()
    {
        return new Menu("Remove Category Menu", this) {
            @Override
            public void show() {
                System.out.println("Please enter category name\n(Enter back to return)");
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
}
