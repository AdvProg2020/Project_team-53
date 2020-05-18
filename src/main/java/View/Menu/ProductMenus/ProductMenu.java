package View.Menu.ProductMenus;

import Controller.Database;
import Controller.ProductManager;
import View.Menu.LoginMenu;
import View.Menu.Menu;

import java.util.regex.Matcher;

public class ProductMenu extends Menu {
    public ProductMenu(Menu parentMenu) {
        super("Product Menu", parentMenu);
        super.addToSubMenus(1, new DigestMenu(this));
        super.addToSubMenus(2, this.getAttributesMenu());
        super.addToSubMenus(3, this.getCompareMenu());
        super.addToSubMenus(4, new CommentsMenu(this));
        super.addToSubMenus(5, new LoginMenu(this));
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
                System.out.println(ProductManager.showFullInfo());
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
                    else {
                        System.out.println(ProductManager.compare(Integer.parseInt(input)));
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

    @Override
    public void execute(){
        int input;
        try {
            String inputInString = scanner.nextLine();
            input = Integer.parseInt(inputInString);
            if((input > parentMenu.getSubMenus().size() + 1) || (input < 1))
            {
                throw new Exception("invalid input");
            }
        }
        catch (Exception e){
            System.out.println("your input is invalid");
            this.execute();
            return;
        }
        if(input == parentMenu.getSubMenus().size() + 1)
        {
            if(this.parentMenu == null)
            {
                Database.writeDataOnFile();
                System.exit(1);
            }
            else {
                ProductManager.setProduct(null);
                this.parentMenu.show();
                this.parentMenu.execute();
            }
        }
        else{
            parentMenu.getSubMenus().get(input).show();
            parentMenu.getSubMenus().get(input).execute();
        }
    }



}
