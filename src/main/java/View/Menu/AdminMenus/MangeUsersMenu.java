package View.Menu.AdminMenus;

import View.Menu.Menu;

public class MangeUsersMenu extends Menu {
    public MangeUsersMenu(Menu parentMenu) {
        super("Manage Users Menu", parentMenu);
        /*super.addToSubMenus(1, this.getVieWAllUsersMenu());
        super.addToSubMenus(2, this.getViewUsersMenu());
        super.addToSubMenus(3, this.getChangeTypeMenu());
        super.addToSubMenus(4, this.getDeleteUserMenu());
        super.addToSubMenus(5, this.getCreateNewManagerMenu());*/
    }

    /*private Menu getVieWAllUsersMenu()
    {
        return new Menu("VieW All Users Menu", this) {
            @Override
            public void show() {
                System.out.println("All users are:\n(Enter back to return)");
            }

            @Override
            public void execute() {
               // System.out.println(AdminManager.showAllAccount());
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

    private Menu getViewUsersMenu()
    {
        return new Menu("View users Menu", this) {
            @Override
            public void show() {
                System.out.println("Please enter username\n(Enter back to return)");
            }

            @Override
            public void execute()
            {
                String input = scanner.nextLine();
                try {
                    Matcher matcher1 = getMatcher(input, "^\\s*(\\S+)\\s*$");
                    Matcher matcher2 = getMatcher(input, "^\\s*back\\s*$");
                    if (matcher2.find()) {
                        this.parentMenu.show();
                        this.parentMenu.execute();
                        return;
                    } else if (!matcher1.find())
                    {
                        throw new Exception("invalid input");
                    }
               //     System.out.println(AdminManager.showAccountWithUsername(matcher1.group(1)));
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
                    Matcher matcher1 = getMatcher(input, "^\\s*(\\S+)\\s*$");
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
                    System.out.println(AdminManager.deleteUsername(matcher1.group(1)));
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
                System.out.println("Please enter username,first name,last name,email,phone number,password,credit in order\n(Enter back to return)");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                try
                {
                    Matcher matcher1 = getMatcher(input, "^\\s*(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s*$");
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
                    System.out.println(AdminManager.addNewAdminAccount(matcher1.group(1), matcher1.group(2), matcher1.group(3), matcher1.group(4), matcher1.group(5), matcher1.group(6), Integer.parseInt(matcher1.group(7))));
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
