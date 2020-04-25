package View.Menu;

public class ViewCartMenu extends Menu{

    public ViewCartMenu(Menu parentMenu) {
        super("View Cart Menu", parentMenu);
        super.addToSubMenus(1, this.getShowProductsMenu());
        super.addToSubMenus(2, this.getViewProductMenu());
        super.addToSubMenus(2, this.getIncreaseProductMenu());
        super.addToSubMenus(3, this.getDecreaseProductMenu());
        super.addToSubMenus(4, this.getShowTotalPriceMenu());
    }

    private Menu getShowProductsMenu()
    {
        return new Menu("Show Product Menu", this) {
            @Override
            public void show()
            {
                System.out.println("Your products are :\n(Enter back to return)");
            }

            @Override
            public void execute()
            {
                String input = scanner.nextLine();
            }
        };
    }

    private Menu getViewProductMenu()
    {
        return new Menu("View Product Menu", this) {
            @Override
            public void show() {
                System.out.println("Please enter your productID :\n(Enter back to return)");
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

    private Menu getIncreaseProductMenu()
    {
        return new Menu("Increase Product Menu", this) {
            @Override
            public void show() {
                System.out.println("Please enter your productID :\n(Enter back to return)");
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

    private Menu getDecreaseProductMenu()
    {
        return new Menu("Increase Product Menu", this) {
            @Override
            public void show() {
                System.out.println("Please enter your productID :\n(Enter back to return)");
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

    private Menu getShowTotalPriceMenu()
    {
        return new Menu("Show Total Price Menu", this) {
            @Override
            public void show() {
                System.out.println("Your total price is :\n(Enter back to return)");
            }

            @Override
            public void execute()
            {
                String input = scanner.nextLine();
            }
        };
    }

}
