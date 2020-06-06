package View.Menu.BuyerMenus;

import Controller.BuyerManager;
import Controller.Database;
import Controller.ProductManager;
import View.Menu.Menu;
import View.Menu.ProductMenus.ProductMenu;

import java.util.regex.Matcher;

public class ViewCartMenu extends Menu {

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
                System.out.println();
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
                try {
                    Matcher matcher1 = getMatcher(input, "^\\s*(\\d+)\\s*$");
                    Matcher matcher2 = getMatcher(input, "^\\s*back\\s*$");
                    if (matcher2.find()) {
                        this.parentMenu.show();
                        this.parentMenu.execute();
                        return;
                    } else if (!matcher1.find())
                    {
                        throw new Exception("invalid input");
                    }
                    ProductManager.setProduct(Database.getProductByID(Integer.parseInt(matcher1.group(1))));
                    ProductMenu productMenu = new ProductMenu(this);
                    productMenu.show();
                    productMenu.execute();
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
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
                try {
                    Matcher matcher1 = getMatcher(input, "^\\s*(\\d+)\\s*$");
                    Matcher matcher2 = getMatcher(input, "^\\s*back\\s*$");
                    if (matcher2.find()) {
                        this.parentMenu.show();
                        this.parentMenu.execute();
                        return;
                    } else if (!matcher1.find())
                    {
                        throw new Exception("invalid input");
                    }
                    System.out.println("not yet");
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
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
                try {
                    Matcher matcher1 = getMatcher(input, "^\\s*(\\d+)\\s*$");
                    Matcher matcher2 = getMatcher(input, "^\\s*back\\s*$");
                    if (matcher2.find()) {
                        this.parentMenu.show();
                        this.parentMenu.execute();
                        return;
                    } else if (!matcher1.find())
                    {
                        throw new Exception("invalid input");
                    }
                    System.out.println(BuyerManager.DecreaseProduct(Integer.parseInt(matcher1.group(1))));
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
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
                System.out.println(BuyerManager.showCostOfCart());
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

}
