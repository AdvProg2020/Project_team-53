package View.Menu;

public class BuyerMenu extends Menu {

    public BuyerMenu(Menu parentMenu) {
        super("Buyer Menu", parentMenu);
        //todo
        super.addToSubMenus(2, new ViewCartMenu(this));
        super.addToSubMenus(3, new ViewOrdersMenu(this));
        super.addToSubMenus(4, this.getViewBalanceMenu());
        super.addToSubMenus(5, this.getViewDiscountCodesMenu());
        super.addToSubMenus(6, new LoginMenu(this));
    }

    private Menu getViewBalanceMenu()
    {
        return new Menu("View Balance Menu", this) {
            @Override
            public void show() {
                System.out.println("Your Balance Is :\n(Enter back to return)");
            }

            @Override
            public void execute(){
                String input = scanner.nextLine();
            }

        };
    }

    private Menu getViewDiscountCodesMenu()
    {
        return new Menu("View Discount Menu", this) {
            @Override
            public void show() {
                System.out.println("Your discount codes are :\n(Enter back to return)");
            }
            @Override
            public void execute()
            {
                String input = scanner.nextLine();
            }
        };
    }
}
