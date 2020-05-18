package View.Menu.OffMenus;

import Controller.AllOffManager;
import Controller.Database;
import Controller.ProductManager;
import View.Menu.Menu;
import View.Menu.ProductMenus.ProductMenu;

import java.util.regex.Matcher;

public class OffMenu extends Menu {

    public OffMenu(Menu parentMenu) {
        super("Off Menu", parentMenu);
        super.addToSubMenus(1, this.getShowAllProductMenu());
        super.addToSubMenus(2, this.getProductMenu());
        super.addToSubMenus(3, new FilteringMenu(this));
        super.addToSubMenus(4, new SortingMenu(this));
    }

    private Menu getShowAllProductMenu()
    {
        return new Menu("Show All Products Menu", this) {
            @Override
            public void show() {
                System.out.println("All products are:\n(Enter back to return)");
            }

            @Override
            public void execute() {
                System.out.println(AllOffManager.showAllProduct());
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

    private Menu getProductMenu()
    {
        return new Menu("Product Menu", this) {
            @Override
            public void show() {
                System.out.println("Please enter productID\n(Enter back to return");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                try
                {
                    Matcher matcher1 = getMatcher(input, "\\s*(\\d+)\\s*");
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
                    else
                    {
                        ProductManager.setProduct(Database.getProductByID(Integer.parseInt(matcher1.group(1))));
                        ProductMenu productMenu = new ProductMenu(this);
                        productMenu.show();
                        productMenu.execute();
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
}
