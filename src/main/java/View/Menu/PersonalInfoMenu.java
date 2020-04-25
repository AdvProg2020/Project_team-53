package View.Menu;

import Controller.AccountManager;

import java.util.regex.Matcher;

public class PersonalInfoMenu  extends Menu{
    public PersonalInfoMenu(Menu parentMenu) {
        super("Personal Info Menu", parentMenu);
        super.addToSubMenus(1, this.getViewMyPersonalInfoMenu());
    }

    private Menu getViewMyPersonalInfoMenu()
    {
        return new Menu("View My Personal Info Menu", this) {
            @Override
            public void show() {
                //todo
            }

            @Override
            public void execute()
            {
                //todo
            }
        };
    }

    private Menu editMyPersonalInfoMenu()
    {
        return new Menu("Edit My Personal Info Menu", this) {
            @Override
            public void show() {
                System.out.println("Please enter the field and new value of that field\n(Enter back to return");
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
                    AccountManager.edit(matcher1.group(1), matcher1.group(2));
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
