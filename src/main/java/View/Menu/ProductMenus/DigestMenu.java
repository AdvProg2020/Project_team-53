package View.Menu.ProductMenus;

import View.Menu.Menu;

public class DigestMenu extends Menu {

    public DigestMenu(Menu parentMenu) {
        super("Digest Menu", parentMenu);
        /*super.addToSubMenus(1, this.getProductInformationMenu());
        super.addToSubMenus(2, this.getAddToCartMenu());
        super.addToSubMenus(3, new LoginMenu(this));*/
    }

    /*private Menu getProductInformationMenu()
    {
        return new Menu("Product Information Menu", this) {
            @Override
            public void show() {
                System.out.println("Product information is:\n(Enter back to return)");
            }

            @Override
            public void execute() {
                System.out.println(ProductManager.digest());
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

    private Menu getAddToCartMenu()
    {
        return new Menu("Add To Cart Menu", this) {
            @Override
            public void show() {
                System.out.println("\n(Enter back to return)");
            }

            @Override
            public void execute()
            {
                System.out.println(BuyerManager.addProductToCart(ProductManager.getProduct()));
                String input = scanner.nextLine();
                Matcher matcher2 = getMatcher(input, "^\\s*back\\s*$");
                if (matcher2.find()) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                    return;
                }
                this.show();
                this.execute();
            }
        };
    }*/
}
