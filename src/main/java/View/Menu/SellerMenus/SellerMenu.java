package View.Menu.SellerMenus;

import Controller.AllProductManager;
import Controller.SellerManager;
import View.Menu.Menu;
import View.Menu.PersonalInfoMenu;

import java.util.regex.Matcher;

public class SellerMenu extends Menu {

    public SellerMenu(Menu parentMenu) {
        super("Seller Menu", parentMenu);
        super.addToSubMenus(1, new PersonalInfoMenu(this));
        super.addToSubMenus(2, this.getViewCompanyInformMenu());
        super.addToSubMenus(3, this.getViewSalesHistoryMenu());
        super.addToSubMenus(4, new MangeProductsMenu(this));
        super.addToSubMenus(5, this.getAddProductMenu());
        super.addToSubMenus(6, this.getRemoveProductMenu());
        super.addToSubMenus(7, this.getShowAllCategoriesMenu());
        super.addToSubMenus(8, new ViewOffsMenu(this));
    }

    private Menu getViewCompanyInformMenu()
    {
        return new Menu("View Company Information Menu", this) {
            @Override
            public void show() {
                System.out.println("Company information is:\n(Enter back to return)");
            }

            @Override
            public void execute() {
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

    private Menu getViewSalesHistoryMenu()
    {
        return new Menu("View Sales History Menu", this) {
            @Override
            public void show() {
                System.out.println("Sales history is:\n(Enter back to return");
            }

            @Override
            public void execute() {
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

    private Menu getAddProductMenu()
    {
        return new Menu("Add Product Menu", this) {
            @Override
            public void show() {
                System.out.println("Please enter product status, name, available(true or false), number, description, category name and price in order");
                System.out.println("(Enter back to return)");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                try
                {
                    Matcher matcher1 = getMatcher(input, "^\\s*(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\d+)\\s*$");
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
                        System.out.println(SellerManager.sendAddProductRequest(matcher1.group(1), matcher1.group(2), Boolean.parseBoolean(matcher1.group(3)), Integer.parseInt(matcher1.group(4)), matcher1.group(5), matcher1.group(6), Integer.parseInt(matcher1.group(7))));
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

    private Menu getRemoveProductMenu()
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
                    Matcher matcher1 = getMatcher(input, "^\\s*(\\S+)\\s*$");
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
                        System.out.println(SellerManager.deleteProduct(Integer.parseInt(matcher1.group(1))));
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

    private Menu getShowAllCategoriesMenu()
    {
        return new Menu("Show All Categories Menu", this) {
            @Override
            public void show()
            {
                System.out.println("All categories are:\n(Enter back to return");
            }
            @Override
            public void execute() {
                System.out.println(AllProductManager.showAllCategories());
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
