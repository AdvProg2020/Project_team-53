package View.Menu.AdminMenus;

import View.Menu.Menu;

public class ManageAllProductsMenu extends Menu {
    public ManageAllProductsMenu(Menu parentMenu) {
        super("Manage all products Menu", parentMenu);
        //super.addToSubMenus(1, this.getRemoveProductMenu());
    }

    /*private Menu getRemoveProductMenu()
    {
        return new Menu("Remove Product Menu", this) {
            @Override
            public void show() {
                System.out.println("Please enter productID\n(Enter back to return)");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                try
                {
                    Matcher matcher1 = getMatcher(input, "^\\s*(\\d+)\\s*$");
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
                        System.out.println(AdminManager.deleteProduct(Integer.parseInt(matcher1.group(1))));
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
