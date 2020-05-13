package View.Menu.ProductMenus;

import Controller.ProductManager;
import View.Menu.Menu;

import java.util.regex.Matcher;

public class DigestMenu extends Menu {

    public DigestMenu(Menu parentMenu) {
        super("Digest Menu", parentMenu);
        super.addToSubMenus(1, this.getProductInformationMenu());
        super.addToSubMenus(2, this.getAddToCartMenu());
    }

    private Menu getProductInformationMenu()
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
                System.out.println("Please enter seller:\n(Enter back to return");
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
                    else if(!matcher1.find())
                    {
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
}
