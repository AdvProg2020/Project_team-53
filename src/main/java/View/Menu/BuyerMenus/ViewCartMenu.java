package View.Menu.BuyerMenus;

import Model.Cart;
import Model.Product.Product;
import View.Menu.Menu;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

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
        try {
            super.setPane();
            Cart cart = null;
            try {
                dataOutputStream.writeUTF("GetCartOfAccount");
                dataOutputStream.flush();
                cart = new Gson().fromJson(dataInputStream.readUTF(), new TypeToken<Cart>(){}.getType());
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
            ArrayList<Integer> allProductIds = Objects.requireNonNull(cart).getProductsID();
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
            Label priceOneLabel = new Label("Price of one");
            Label priceAllLabel = new Label("Price of All");
            Label numberOf = new Label("Number");
            priceOneLabel.setStyle("-fx-font-weight: bold");
            priceAllLabel.setStyle("-fx-font-weight: bold");
            numberOf.setStyle("-fx-font-weight: bold");

            GridPane.setConstraints(numberOf, 2, 1);
            GridPane.setConstraints(priceOneLabel, 3, 1);
            GridPane.setConstraints(priceAllLabel, 4, 1);
            gridPane.getChildren().addAll(numberOf, priceOneLabel, priceAllLabel);

            int i = 2;
            for (Integer productId : allProductIds) {
                dataOutputStream.writeUTF("GetProduct " + productId);
                dataOutputStream.flush();
                Product product = new Gson().fromJson(dataInputStream.readUTF(), new TypeToken<Product>(){}.getType());
                String text = "";
                Label label = new Label();
                text = text + "ID(" + product.getProductId() + "):" + product.getName();
                label.setText(text);
                label.setFont(Font.font(15));

                Label numbers = new Label(String.valueOf(cart.getMuchOfProductID(productId)));

                int priceOfOne = product.getPrice();
                if (product.doesHaveOff())
                    priceOfOne -= (Math.min(product.getPrice()*product.getOff().getPercent()/100, product.getOff().getMaxValue()));
                Label priceOne = new Label(String.valueOf(priceOfOne));
                Label priceAll = new Label(String.valueOf(priceOfOne*cart.getMuchOfProductID(productId)));
                priceOne.setFont(Font.font(15));
                priceAll.setFont(Font.font(15));

                Button showButton = new Button("show");
                showButton.setAlignment(Pos.CENTER);
                showButton.setMaxWidth(Double.MAX_VALUE);
                showButton.getStyleClass().add("dark-blue");
                showButton.setOnAction(e -> handleShowProduct(product));
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
                GridPane.setConstraints(priceOne,3 , i);
                GridPane.setConstraints(priceAll,4, i);
                GridPane.setConstraints(showButton, 5, i);
                GridPane.setConstraints(increaseButton, 6, i);
                GridPane.setConstraints(decreaseButton, 7, i);

                gridPane.getChildren().addAll(label, numbers,priceOne, priceAll, showButton, increaseButton, decreaseButton);
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
            payButton.setOnAction(e ->{
                Stage newWindow = new Stage();
                newWindow.initModality(Modality.APPLICATION_MODAL);

                handlePay(newWindow);

                newWindow.showAndWait();
            });
            long totalCost = 0;
            try {
                dataOutputStream.writeUTF("GetCostOfAccountCart");
                dataOutputStream.flush();
                totalCost = Long.parseLong(dataInputStream.readUTF());
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
            Label costOfAll = new Label("cost : " + totalCost);
            costOfAll.setFont(Font.font(20));
            GridPane.setConstraints(costOfAll, 7 , i);
            GridPane.setConstraints(back,7, i+1);
            GridPane.setConstraints(message, 1, i+2 , 7, 1);
            message.setFont(Font.font(15));
            GridPane.setHalignment(message, HPos.CENTER);
            GridPane.setConstraints(payButton, 6 , i+1);

            gridPane.getChildren().addAll(back, costOfAll, payButton, message);

            super.mainPane.setCenter(gridPane);

            Menu.window.setScene(scene);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void handlePay(Stage newWindow) {
        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane, 600,400);
        scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
        gridPane.getStyleClass().add("cart");

        TextField discountId = new TextField();
        discountId.setPromptText("DiscountID");
        discountId.getStyleClass().add("text-field");
        TextField address = new TextField();
        address.setPromptText("Address");
        address.getStyleClass().add("text-field");

        Button submit = new Button("Pay");
        submit.getStyleClass().add("dark-blue");
        submit.setAlignment(Pos.CENTER);
        submit.setOnAction(e->{
            int id = -1;
            if (discountId.getText() != null && discountId.getText().matches("[0-9]+"))
                id = Integer.parseInt(discountId.getText());
            String res = "";
            try {
                dataOutputStream.writeUTF("Pay " + id);
                dataOutputStream.flush();
                res = dataInputStream.readUTF();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());;
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Result");
            alert.setHeaderText("Process Result");
            alert.setContentText(res);
            alert.showAndWait();
            show();
            newWindow.close();
        });

        GridPane.setConstraints(discountId, 0, 0);
        GridPane.setConstraints(address, 0, 1);
        GridPane.setConstraints(submit, 0, 2);
        GridPane.setHalignment(submit, HPos.CENTER);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.getChildren().addAll(discountId, address, submit);

        newWindow.setScene(scene);
    }

    private void handleDecrease(int productId) {
        try {
            dataOutputStream.writeUTF("DecreaseProduct " + productId);
            dataOutputStream.flush();
            message.setText(dataInputStream.readUTF());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        show();
    }

    private void handleIncrease(int productId) {
        try {
            dataOutputStream.writeUTF("IncreaseProduct " + productId);
            dataOutputStream.flush();
            message.setText(dataInputStream.readUTF());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        show();
    }

    private void handleShowProduct(Product product)
    {
        Stage newWindow = new Stage();
        Pane pane = product.showProductFullInfoGraphic();
        ((GridPane)pane).setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane, 600, 400);

        newWindow.setScene(scene);
        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.setOnCloseRequest(e -> show());
        newWindow.showAndWait();

    }
}
