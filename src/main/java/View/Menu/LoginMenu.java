package View.Menu;

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
                System.out.println("");
            }
            @Override
            public void execute()
            {
                String input = scanner.nextLine();
            }
        };
    }

    private Menu getLoginMenu()
    {
        return new Menu("Login Menu", this) {
            @Override
            public void show()
            {
                System.out.println("");
            }
            @Override
            public void execute() {
                String input = scanner.nextLine();
            }
        };
    }

    private Menu getLogoutMenu()
    {
        return new Menu("Logout Menu", this) {
            @Override
            public void show() {
                System.out.println("");
            }

            @Override
            public void execute()
            {
                String input = scanner.nextLine();
            }
        };
    }

}
