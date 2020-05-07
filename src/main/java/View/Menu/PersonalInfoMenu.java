package View.Menu;

import Controller.AccountManager;

import java.util.regex.Matcher;

public class PersonalInfoMenu  extends Menu{
    public PersonalInfoMenu(Menu parentMenu) {
        super("Personal Info Menu", parentMenu);
        super.addToSubMenus(1, this.getViewMyPersonalInfoMenu());
        super.addToSubMenus(2, this.getEditMyPersonalInfoMenu());
    }

    private Menu getViewMyPersonalInfoMenu()
    {
        return new Menu("View My Personal Info Menu", this) {
            @Override
            public void show() {
                System.out.println("Your personal information's are:\n(Enter back to return)");
            }

            @Override
            public void execute()
            {
                System.out.println(AccountManager.viewPersonalInfo());
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
                    else{
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

    private Menu getEditMyPersonalInfoMenu()
    {
        return new Menu("Edit My Personal Info Menu", this) {
            @Override
            public void show() {
                System.out.println("Please enter the field and new value of that field\n(Enter back to return)");
            }

            @Override
            public void execute()
            {
                String input;
                try {
                    input = scanner.nextLine();
                    Matcher matcher1 = getMatcher(input, "^\\s*(\\S+)\\s+(\\S+)\\s*$");
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
                    System.out.println(AccountManager.edit(matcher1.group(1), matcher1.group(2)));
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
