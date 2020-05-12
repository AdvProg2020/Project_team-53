package View.Menu.SellerMenus.ProductMenus;

import View.Menu.Menu;

import java.util.regex.Matcher;

public class CommentsMenu extends Menu {
    public CommentsMenu(Menu parentMenu) {
        super("Comments Menu", parentMenu);
        super.addToSubMenus(1, this.getShowCommentsAndScoreMenu());
        super.addToSubMenus(2, this.getAddCommentMenu());
    }

    private Menu getShowCommentsAndScoreMenu()
    {
        return new Menu("Show Comments And Score Menu", this) {
            @Override
            public void show() {
                System.out.println("This product comments and score\n(Enter back to return)");
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

    private Menu getAddCommentMenu()
    {
        return new Menu("Add Comment Menu", this) {
            @Override
            public void show() {
                System.out.println("Please enter title in first line and content in second line");
                System.out.println("(Enter back to return)");
            }

            @Override
            public void execute() {
                String title = scanner.nextLine();
                String content = scanner.nextLine();
                try
                {
                    Matcher matcher1 = getMatcher(title, "");
                    Matcher matcher2 = getMatcher(title, "^\\s*back\\s*$");
                    Matcher matcher3 = getMatcher(content, "");
                    Matcher matcher4 = getMatcher(content, "^\\s*back\\s*$");
                    if(matcher2.find() || matcher4.find())
                    {
                        this.parentMenu.show();
                        this.parentMenu.execute();
                        return;
                    }
                    else if(!matcher1.find() || !matcher3.find())
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
