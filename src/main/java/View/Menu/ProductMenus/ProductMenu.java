package View.Menu.ProductMenus;

import View.Menu.Menu;

import java.util.regex.Matcher;

public class ProductMenu extends Menu {
    public ProductMenu(Menu parentMenu) {
        super("Product Menu", parentMenu);
        super.addToSubMenus(1, new DigestMenu(this));
        super.addToSubMenus(2, this.getAttributesMenu());
        super.addToSubMenus(3, this.getCompareMenu());
        super.addToSubMenus(4, new CommentsMenu(this));
    }

    private Menu getAttributesMenu()
    {
        return new Menu("Attributes Menu", this) {
            @Override
            public void show() {
                System.out.println("This product attributes are:\n(Enter back to return)");
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

    private Menu getCompareMenu()
    {
        return new Menu("Compare Menu", this) {
            @Override
            public void show() {
                System.out.println("Please enter productID\n(Enter back to return)");
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
