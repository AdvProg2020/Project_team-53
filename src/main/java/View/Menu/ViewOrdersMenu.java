package View.Menu;

public class ViewOrdersMenu extends Menu {
    public ViewOrdersMenu(Menu parentMenu) {
        super("View Orders Menu", parentMenu);
        super.addToSubMenus(1, this.getShowOrderMenu());
        super.addToSubMenus(2, this.getRateMenu());
    }

    private Menu getShowOrderMenu()
    {
        return new Menu("Show Order Menu", this) {
            @Override
            public void show() {
                System.out.println("Please enter your orderID :\n(Enter back to return)");
            }

            @Override
            public void execute()
            {
                String input = scanner.nextLine();
                //todo
                this.execute();
            }
        };
    }

    private Menu getRateMenu()
    {
        return new Menu("Rate Menu", this) {
            @Override
            public void show() {
                System.out.println("Please enter your productID and your rate 1 to 5:\n(Enter back to return");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                //todo
                this.execute();
            }
        };
    }
}
