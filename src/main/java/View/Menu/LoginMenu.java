package View.Menu;

import Controller.AccountManager;

import java.util.regex.Matcher;

public class LoginMenu extends Menu{

    public LoginMenu(Menu parentMenu) {
        super("Login Menu", parentMenu);
        super.addToSubMenus(1, this.getRegisterMenu());
        super.addToSubMenus(2, this.getLoginMenu());
        super.addToSubMenus(3, this.getLogoutMenu());
    }

    private Menu getRegisterMenu()
    {
        return new Menu("Register Menu", this) {
            @Override
            public void show()
            {
                System.out.println("Please enter role,username,first name,last name,email,phone number,password,credit in order");
                System.out.println("(Enter back to return)");
            }
            @Override
            public void execute()
            {
                String input = scanner.nextLine();
                try
                {
                    Matcher matcher1 = getMatcher(input, "^\\s*(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s*$");
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
                    System.out.println(AccountManager.register(matcher1.group(1),matcher1.group(2),matcher1.group(3),matcher1.group(4),matcher1.group(5),matcher1.group(6),matcher1.group(7),Integer.parseInt(matcher1.group(8)), ""));
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
                this.execute();
            }
        };
    }

    private Menu getLoginMenu()
    {
        return new Menu("Login Menu", this) {
            @Override
            public void show()
            {
                System.out.println("Please enter username and password\n(enter back to return)");
            }
            @Override
            public void execute() {
                String input = scanner.nextLine();
                try{
                    Matcher matcher1 = Menu.getMatcher(input, "^\\s*(\\S+)\\s+(\\S+)\\s*$");
                    Matcher matcher2 = Menu.getMatcher(input, "^\\s*back\\s*$");
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
                    System.out.println(AccountManager.logIn(matcher1.group(1), matcher1.group(2)));
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
                this.execute();
            }
        };
    }

    private Menu getLogoutMenu()
    {
        return new Menu("Logout Menu", this) {
            @Override
            public void show() {
            }

            @Override
            public void execute()
            {
                System.out.println(AccountManager.logOut());
                this.parentMenu.show();
                this.parentMenu.execute();
            }
        };
    }

}
