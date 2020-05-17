package View.Menu.SellerMenus;

import Controller.SellerManager;
import View.Menu.Menu;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class ViewOffsMenu extends Menu {
    public ViewOffsMenu(Menu parentMenu) {
        super("View Offs Menu", parentMenu);
        super.addToSubMenus(1, this.getViewOffMenu());
        super.addToSubMenus(2, this.getAddOffMenu());
        super.addToSubMenus(3, this.getEditOffMenu());
    }

    private Menu getViewOffMenu()
    {
        return new Menu("View Off Menu", this) {
            @Override
            public void show() {
                System.out.println("Please enter offID\n(Enter back to return)");
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

    private Menu getEditOffMenu()
    {
        return new Menu("Edit Off Menu", this) {
            @Override
            public void show() {
                System.out.println("Please enter offID, field and new value of field\n(Enter back to return)");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                try
                {
                    Matcher matcher1 = getMatcher(input, "^\\s*(\\d+)\\s+(\\S+)\\s+(\\S+)\\s*$");
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
                    System.out.println(SellerManager.editOff(Integer.parseInt(matcher1.group(1)), matcher1.group(2), matcher1.group(3)));
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
                this.execute();
            }
        };
    }

    private Menu getAddOffMenu()
    {
        return new Menu("Add Off Menu", this) {
            @Override
            public void show() {
                System.out.println("Please enter max value(with numbers), percent(without % sign), start date and " +
                        "end date(both in format YYYY-MM-DD_hh:mm) in first line and " +
                        "productIDs in second line\n(Enter back to return)");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                String name = scanner.nextLine();
                try
                {
                    Matcher matcher1 = getMatcher(input, "^\\s*(\\d+)\\s+(\\d+)\\s+(\\S+)\\s+(\\S+)\\s*$");
                    Matcher matcher2 = getMatcher(input, "^\\s*back\\s*$");
                    Matcher matcher3 = getMatcher(name, "\\s*(\\d+)\\s*");
                    Matcher matcher4 = getMatcher(name, "^\\s*back\\s*$");
                    if(matcher2.find() || matcher4.find())
                    {
                        this.parentMenu.show();
                        this.parentMenu.execute();
                        return;
                    }
                    else if(!matcher1.find())
                    {
                        throw new Exception("invalid input");
                    }
                    ArrayList<Integer> allNames = new ArrayList<>();
                    while (matcher3.find())
                    {
                        String productID = matcher3.group();
                        productID = productID.trim();
                        allNames.add(Integer.parseInt(productID));
                    }
                    if(allNames.isEmpty())
                    {
                        throw new Exception("invalid input");
                    }
                    System.out.println(SellerManager.addNewOff(Integer.parseInt(matcher1.group(1)), Integer.parseInt(matcher1.group(1)), matcher1.group(3), matcher1.group(4), allNames));
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
