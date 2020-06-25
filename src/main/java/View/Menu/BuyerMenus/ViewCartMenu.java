package View.Menu.BuyerMenus;

import Controller.AccountManager;
import Controller.BuyerManager;
import Controller.Database;
import Model.Account.BuyerAccount;
import Model.Cart;
import Model.Product.Product;
import View.Menu.Menu;
import View.Menu.ViewModelsWithGraphic;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ViewCartMenu extends Menu {
    private Label message = new Label();

    public ViewCartMenu(Menu parentMenu) {
        super("View Cart Menu", parentMenu);
//        super.addToSubMenus(1, this.getShowProductsMenu());
//        super.addToSubMenus(2, this.getViewProductMenu());
//        super.addToSubMenus(2, this.getIncreaseProductMenu());
//        super.addToSubMenus(3, this.getDecreaseProductMenu());
//        super.addToSubMenus(4, this.getShowTotalPriceMenu());
    }

    @Override
    public void show() {
        super.setPane();
        Cart cart = ((BuyerAccount) AccountManager.getLoggedInAccount()).getCart();
        ArrayList<Integer> allProductIds = cart.getProductsID();
        Scene scene = new Scene(super.mainPane, 1000, 600);
        scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
        super.mainPane.getStyleClass().add("cart");
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        Label info = new Label("Cart Products");
        info.setFont(Font.font(25));
        info.setAlignment(Pos.CENTER);
        GridPane.setHalignment(info, HPos.CENTER);
        GridPane.setConstraints(info, 1, 0);
        gridPane.getChildren().add(info);
        int i = 1;
        for (Integer productId : allProductIds) {
            Product product = Database.getProductByID(productId);
            String text = "";
            Label label = new Label();
            text = text + "ID(" + product.getProductId() + "):" + product.getName();
            label.setText(text);
            label.setFont(Font.font(15));

            Label numbers = new Label(String.valueOf(cart.getMuchOfProductID(productId)));

            Button showButton = new Button("show");
            showButton.setAlignment(Pos.CENTER);
            showButton.setMaxWidth(Double.MAX_VALUE);
            showButton.getStyleClass().add("dark-blue");
            showButton.setOnAction(e -> handleShowProduct(product.getProductId()));
            showButton.setAlignment(Pos.CENTER);

            Button increaseButton = new Button();
            increaseButton.setAlignment(Pos.CENTER);
            increaseButton.getStyleClass().add("dark-blue");
            File file1 = new File("Data" + File.separator + "Styles" + File.separator + "images" + File.separator + "plus.png");
            FileInputStream fileInputStream1 = null;
            try {
                fileInputStream1 = new FileInputStream(file1);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image image1 = new Image(fileInputStream1);
            ImageView imageView1 = new ImageView(image1);
            imageView1.setFitWidth(25);
            imageView1.setFitHeight(25);
            increaseButton.setGraphic(imageView1);
            increaseButton.setOnAction(e -> handleIncrease(product.getProductId()));
            increaseButton.setAlignment(Pos.CENTER);

            Button decreaseButton = new Button();
            decreaseButton.setAlignment(Pos.CENTER);
            decreaseButton.getStyleClass().add("dark-blue");
            File file2 = new File("Data" + File.separator + "Styles" + File.separator + "images" + File.separator + "minus.png");
            FileInputStream fileInputStream2 = null;
            try {
                fileInputStream2 = new FileInputStream(file2);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image image2 = new Image(fileInputStream2);
            ImageView imageView2 = new ImageView(image2);
            imageView2.setFitWidth(25);
            imageView2.setFitHeight(25);
            decreaseButton.setGraphic(imageView2);
            decreaseButton.setOnAction(e -> handleDecrease(product.getProductId()));
            decreaseButton.setAlignment(Pos.CENTER);

            GridPane.setConstraints(label, 0, i);
            GridPane.setConstraints(numbers, 2, i);
            GridPane.setConstraints(showButton, 3, i);
            GridPane.setConstraints(increaseButton, 4, i);
            GridPane.setConstraints(decreaseButton, 5, i);

            gridPane.getChildren().addAll(label, numbers, showButton, increaseButton, decreaseButton);
            i++;
        }

        Button back = new Button("back");
        back.setAlignment(Pos.CENTER);
        back.setMaxWidth(Double.MAX_VALUE);
        back.getStyleClass().add("dark-blue");
        back.setOnAction(e -> parentMenu.show());

        Button payButton = new Button("Pay");
        payButton.setAlignment(Pos.CENTER);
        payButton.setMaxWidth(Double.MAX_VALUE);
        payButton.getStyleClass().add("dark-blue");
        // TODO: 13-Jun-20 add function of paying
        payButton.setOnAction(e ->{
            Stage newWindow = new Stage();
            newWindow.initModality(Modality.APPLICATION_MODAL);

            handlePay(newWindow);

            newWindow.showAndWait();
        });
        Label costOfAll = new Label("cost : " + cart.getCost());

        GridPane.setConstraints(costOfAll, 5 , i);
        GridPane.setConstraints(back,5, i+1);
        GridPane.setConstraints(message, 1, i+2 , 5, 1);
        message.setFont(Font.font(15));
        GridPane.setHalignment(message, HPos.CENTER);
        GridPane.setConstraints(payButton, 4 , i+1);

        gridPane.getChildren().addAll(back, costOfAll, payButton, message);

        super.mainPane.setCenter(gridPane);

        Menu.window.setScene(scene);
    }

    private void handlePay(Stage newWindow) {
        TextField discountId = new TextField();
        TextField address = new TextField();

        Button submit = new Button("Pay");
        submit.setOnAction(e->{
            int id = -1;
            if (discountId.getText() != null && discountId.getText().matches("[0-9]+"))
                id = Integer.parseInt(discountId.getText());
            BuyerManager.pay(id);
        });

        VBox vBox = new VBox(address, discountId,submit);
        Scene scene = new Scene(vBox, 600,400);

        newWindow.setScene(scene);
    }

    private void handleDecrease(int productId) {
        message.setText(BuyerManager.DecreaseProduct(productId));
        show();
    }

    private void handleIncrease(int productId) {
        Product product = Database.getProductByID(productId);
        message.setText(BuyerManager.addProductToCart(product));
        show();
    }

    public void handleShowProduct(int productID)
    {
        Stage newWindow = new Stage();
        Pane pane = ViewModelsWithGraphic.showProductFullInfoGraphic(productID);
        ((GridPane)pane).setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane, 600, 400);

        newWindow.setScene(scene);
        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.setOnCloseRequest(e -> show());
        newWindow.showAndWait();

    }
}
