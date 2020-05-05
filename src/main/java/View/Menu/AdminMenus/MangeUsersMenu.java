package View.Menu.AdminMenus;

import View.Menu.Menu;

import java.util.regex.Matcher;

public class MangeUsersMenu extends Menu {
    public MangeUsersMenu(Menu parentMenu) {
        super("Manage Users Menu", parentMenu);
        super.addToSubMenus(1, this.getViewUsersMenu());
        super.addToSubMenus(2, this.getChangeTypeMenu());
        super.addToSubMenus(3, this.getDeleteUserMenu());
        super.addToSubMenus(4, this.getCreateNewManagerMenu());
    }

    private Menu getViewUsersMenu()
    {
        return new Menu("View users Menu", this) {
            @Override
            public void show() {
                System.out.println("Please enter username\n(Enter back to return");
            }

            @Override
            public void execute()
            {
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

    private Menu getChangeTypeMenu()
    {
        return new Menu("Change Type Menu", this) {
            @Override
            public void show()
            {
                System.out.println("Please enter the username and new role\n(Enter back to return)");
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

    private Menu getDeleteUserMenu()
    {
        return new Menu("Delete User Menu", this) {
            @Override
            public void show()
            {
                System.out.println("Please enter username\n(Enter back to return)");
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

    private Menu getCreateNewManagerMenu()
    {
        return new Menu("Create New Manager Menu", this) {
            @Override
            public void show()
            {
                System.out.println("Please enter features in order\n(Enter back to return");
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
