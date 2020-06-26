package Controller;

import Model.Account.BuyerAccount;
import Model.Account.SellerAccount;
import Model.Cart;
import Model.Log.Log;
import Model.Product.DiscountAndOff.Discount;
import Model.Product.Product;

public class BuyerManager {

    public static String showAllDiscounts() {
        BuyerAccount buyerAccount = (BuyerAccount) AccountManager.getLoggedInAccount();
        return buyerAccount.showAllDiscounts();
    }

    public static long showCostOfCart() {
        BuyerAccount buyerAccount = (BuyerAccount) AccountManager.getLoggedInAccount();
        return buyerAccount.getCart().getCost();
    }

    public static boolean canIncreaseProduct(int productId) {
        BuyerAccount buyerAccount = (BuyerAccount) AccountManager.getLoggedInAccount();
        return buyerAccount.getCart().increaseProduct(productId);
    }

    public static String DecreaseProduct(int productId) {
        BuyerAccount buyerAccount = (BuyerAccount) AccountManager.getLoggedInAccount();
        if (!buyerAccount.getCart().decreaseProduct(productId))
            return "you haven't chosen this product yet";
        Database.getProductByID(productId).setNumber(Database.getProductByID(productId).getNumber() + 1);
        return "product eliminated successfully";
    }

    public static void addNewProductToCart(Product product) {
        BuyerAccount buyerAccount = (BuyerAccount) AccountManager.getLoggedInAccount();
        buyerAccount.getCart().addToCart(product);
    }

    public static String addProductToCart(Product product) {
        if (product.getNumber() < 1 || !product.isAvailable())
            return "unfortunately we don't have this product now";
        BuyerAccount buyerAccount = (BuyerAccount) AccountManager.getLoggedInAccount();
        Cart cart = buyerAccount.getCart();
        if (!cart.increaseProduct(product.getProductId()))
            addNewProductToCart(product);
        product.setNumber(product.getNumber() - 1);
        return "product added to cart";
    }

    public static boolean canBuy(int discountId) {
        BuyerAccount buyerAccount = (BuyerAccount) AccountManager.getLoggedInAccount();
        long cost = buyerAccount.getCart().getCost();
        if (buyerAccount.canUseDiscount(discountId))
            cost -= Math.min(cost * Database.getDiscountById(discountId).getPercent() / 100, Database.getDiscountById(discountId).getMaxValue());
        if (cost > buyerAccount.getCredit())
            return false;
        //Todo: complete the conditions
        return true;//just for make ok compile error
    }

    public static void buy(int discountId) {
        BuyerAccount buyerAccount = (BuyerAccount) AccountManager.getLoggedInAccount();
        long cost = buyerAccount.getCart().getCost();
        buyerAccount.setCredit((int) (buyerAccount.getCredit() - cost));
        payToSeller(discountId);
        // Todo: get the seller cost
        if (buyerAccount.canUseDiscount(discountId)) {
            buyerAccount.useDiscount(discountId);
            if (discountId != -1) {
                Discount discount = Database.getDiscountById(discountId);
                // max value
                buyerAccount.setCredit(buyerAccount.getCredit() + Math.min((int) cost * discount.getPercent() / 100, discount.getMaxValue()));
                // Todo: check up the line above
            }
        }
        buyerAccount.setCart(new Cart());
    }

    public static String showCart() {
        BuyerAccount buyerAccount = (BuyerAccount) AccountManager.getLoggedInAccount();
        return buyerAccount.getCart().showCart();
    }

    public static String pay(int discountId) {
        if (Database.getDiscountById(discountId) == null && discountId != -1)
            return " your discount is not valid";
        if (discountId!=-1 && !((BuyerAccount) AccountManager.getLoggedInAccount()).canUseDiscount(discountId))
            return "You can't use this discount";
        if (!canBuy(discountId))
            return "Not enough money";
        else {
            buy(discountId);
            return "product bought successfully";
        }
    }

    public static void payToSeller(int discountId) {
        BuyerAccount buyerAccount = (BuyerAccount) AccountManager.getLoggedInAccount();
        int discountValue = 0;
        if (buyerAccount.canUseDiscount(discountId))
            discountValue = Database.getDiscountById(discountId).getPercent();
        if (discountId == -1)
            discountValue = 0;
        for (Integer productId : buyerAccount.getCart().getProductsID()) {
            Product product = Database.getProductByID(productId);
            SellerAccount sellerAccount = (SellerAccount) Database.getAccountByUsername(product.getSellerUsername());
            sellerAccount.setCredit(sellerAccount.getCredit() + product.getPrice() * buyerAccount.getCart().getMuchOfProductID(productId));
            int maxValue = product.getPrice();
            int offValue = 0;
            if (product.doesHaveOff()) {
                maxValue = product.getOff().getMaxValue();
                offValue = product.getPrice() * product.getOff().getPercent() / 100;
                sellerAccount.setCredit(sellerAccount.getCredit() - Math.min(offValue, maxValue) * buyerAccount.getCart().getMuchOfProductID(productId));
            }
            Log.addLog(buyerAccount.getUsername(), product.getSellerUsername(), product.getPrice(), productId, Math.min(offValue, maxValue), discountValue * product.getPrice() / 100);
        }
    }
}
