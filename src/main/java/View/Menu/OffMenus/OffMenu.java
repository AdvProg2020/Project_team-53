package View.Menu.OffMenus;

import View.Menu.Menu;

public class OffMenu extends Menu {

    public OffMenu(Menu parentMenu) {
        super("Off Menu", parentMenu);
        /*super.addToSubMenus(1, this.getShowAllOffMenu());
        super.addToSubMenus(2, this.getProductMenu());
        super.addToSubMenus(3, new FilteringMenu(this));
        super.addToSubMenus(4, new SortingMenu(this));
        super.addToSubMenus(5, new LoginMenu(this));*/
    }

    /*private Menu getShowAllOffMenu()
    {
        return new Menu("Show All Off Menu", this) {
            @Override
            public void show() {
                System.out.println("All products are:\n(Enter back to return)");
            }

            @Override
            public void execute() {
                System.out.println(AllOffManager.showAllOffs());
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
    }*/
}
