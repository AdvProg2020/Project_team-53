package View.Menu.ProductMenus;

import Controller.ProductManager;
import View.Menu.LoginMenu;
import View.Menu.Menu;

import java.util.regex.Matcher;

public class CommentsMenu extends Menu {
    public CommentsMenu(Menu parentMenu) {
        super("Comments Menu", parentMenu);
        super.addToSubMenus(1, this.getShowCommentsAndScoreMenu());
        super.addToSubMenus(2, this.getAddCommentMenu());
        super.addToSubMenus(3, new LoginMenu(this));
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
                System.out.println(ProductManager.showAverageScore());
                System.out.println(ProductManager.viewAllComments());
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
                    Matcher matcher2 = getMatcher(title, "^\\s*back\\s*$");
                    Matcher matcher4 = getMatcher(content, "^\\s*back\\s*$");
                    if(matcher2.find() || matcher4.find())
                    {
                        this.parentMenu.show();
                        this.parentMenu.execute();
                        return;
                    }
                    else
                    {
                        title = title.trim();
                        content = content.trim();
                        System.out.println(ProductManager.giveComment(title, content));
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
