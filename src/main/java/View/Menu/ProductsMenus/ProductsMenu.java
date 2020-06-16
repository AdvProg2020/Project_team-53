package View.Menu.ProductsMenus;

import Controller.AllProductManager;
import Controller.Database;
import Controller.ProductManager;
import Model.Product.Product;
import View.Menu.LoginMenu;
import View.Menu.Menu;
import View.Menu.ProductMenus.ProductMenu;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class ProductsMenu extends Menu {
    public ProductsMenu(Menu parentMenu) {
        super("Products Menu", parentMenu);
        super.addToSubMenus(1, this.getViewAllCategoriesMenu());
        super.addToSubMenus(2, new FilteringMenu(this));
        super.addToSubMenus(3, new SortingMenu(this));
        super.addToSubMenus(4, this.getShowProductsMenu());
        super.addToSubMenus(5, this.getProductMenu());
        super.addToSubMenus(6, new LoginMenu(this));
    }

    private Menu getViewAllCategoriesMenu()
    {
        return new Menu("View All Categories Menu", this) {
            @Override
            public void show()
            {
                System.out.println("All categories are:\n(Enter back to return)");
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

    private Menu getShowProductsMenu()
    {
        return new Menu("Show Products Menu", this) {
            @Override
            public void show() {
                super.setPane();
                //System.out.println("All products are:\n(Enter back to return)");
                ScrollPane scrollPane = new ScrollPane();
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                ArrayList<Product> allProducts = Database.getAllProducts();
                for (Product product : allProducts) {
                    mainPane.getChildren().add(getProductPane(product));
                }
                Scene scene = new Scene(mainPane, 1000, 600);

                window.setScene(scene);
                window.show();
            }

            @Override
            public void execute() {
                System.out.println(AllProductManager.showAllProduct());
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

            private Pane getProductPane(Product product){
                Pane pane = new Pane();
                Label label = new Label(product.getName());
                pane.getChildren().add(label);
                return pane;
            }
        };
    }

    private Menu getProductMenu()
    {
        return new Menu("Product Menu", this) {
            @Override
            public void show() {
                System.out.println("Please enter productID\n(Enter back to return)");
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
                        ProductMenu productMenu = new ProductMenu(this.parentMenu);
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
