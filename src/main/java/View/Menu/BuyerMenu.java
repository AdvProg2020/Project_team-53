package View.Menu;

public class BuyerMenu extends Menu {

    public BuyerMenu(Menu parentMenu) {
        super("Buyer Menu", parentMenu);
        //todo
        super.addToSubMenus(2,new ViewCartMenu(this));
    }
}
