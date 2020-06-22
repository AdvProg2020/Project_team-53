package View.Menu.ProductMenus;

import Controller.AccountManager;
import Controller.BuyerManager;
import Controller.Database;
import Controller.ProductManager;
import View.Menu.Menu;
import View.Menu.ViewModelsWithGraphic;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

public class ProductMenu extends Menu {
    private Stage newWindow;
    public ProductMenu(Menu parentMenu) {
        super("Product Menu", parentMenu);
        /*super.addToSubMenus(1, new DigestMenu(this));
        super.addToSubMenus(2, this.getAttributesMenu());
        super.addToSubMenus(3, this.getCompareMenu()); //
        super.addToSubMenus(4, new CommentsMenu(this));
        super.addToSubMenus(5, new LoginMenu(this));*/
    }

    @Override
    public void show() {
        newWindow = new Stage();
        newWindow.setScene(setScene());

        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.showAndWait();
    }

    public Scene setScene()
    {
        GridPane gridPane = (GridPane) ViewModelsWithGraphic.showFullInfoGraphic(ProductManager.getProduct().getProductId());
        gridPane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(gridPane, 600, 400);

        Button addToCart = new Button("Add To Cart");
        addToCart.setOnAction(e -> handleAddToCart(ProductManager.getProduct().getProductId()));
        Button showComments = new Button("Show Comments");
        Button giveScore = new Button("Give Score");
        giveScore.setOnAction(e -> handleGiveScore());
        Button comment = new Button("Comment");
        comment.setOnAction(e -> handleComment());
        TextField productID = new TextField();
        productID.setPromptText("productID to compare");
        Button compare = new Button("Compare");
        compare.setOnAction(e -> {
            try {
                handleCompare(Integer.parseInt(productID.getText()));
            }
            catch (Exception ex)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Process Fail");
                alert.setContentText("Wrong input for productID");

                alert.showAndWait();
            }
        });

        GridPane.setConstraints(addToCart, 3, 2);
        GridPane.setConstraints(giveScore, 3, 3);
        GridPane.setConstraints(comment, 3,4);
        GridPane.setConstraints(productID, 4, 2);
        GridPane.setConstraints(compare, 4, 3);

        gridPane.getChildren().addAll(addToCart, giveScore, comment, productID, compare);

        return scene;
    }

    public void handleAddToCart(int productID)
    {
        if (AccountManager.getLoggedInAccount() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Process Fail");
            alert.setContentText("You have to login first");

            alert.showAndWait();
            return;
        }
        BuyerManager.addProductToCart(Objects.requireNonNull(Database.getProductByID(productID)));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Process Result");
        alert.setContentText(BuyerManager.addProductToCart(Objects.requireNonNull(Database.getProductByID(productID))));

        alert.showAndWait();
    }

    public void handleComment()
    {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(20);
        gridPane.setAlignment(Pos.CENTER);
        TextField title = new TextField();
        title.setPromptText("Title");
        TextField content = new TextField();
        content.setPromptText("Content");
        Label status = new Label();
        Button comment = new Button("Comment");
        comment.setOnAction(e -> {
            status.setText(ProductManager.giveComment(title.getText(), content.getText()));
        });
        Button back = new Button("Back");
        back.setOnAction(e -> {
            newWindow.setScene(setScene());
        });
        ScrollPane scrollPane = ViewModelsWithGraphic.showCommentsOfProduct(ProductManager.getProduct().getProductId());
        GridPane.setConstraints(scrollPane, 0, 0);
        GridPane.setConstraints(title, 0, 1);
        GridPane.setConstraints(content, 0, 2);
        GridPane.setConstraints(comment, 0, 3);
        GridPane.setConstraints(back, 0, 4);
        GridPane.setConstraints(status, 0, 5);
        GridPane.setHalignment(comment, HPos.CENTER);
        GridPane.setHalignment(back, HPos.CENTER);

        gridPane.getChildren().addAll(scrollPane, title, content, comment, back, status);

        Scene scene = new Scene(gridPane, 600, 400);
        newWindow.setScene(scene);
    }

    public void handleGiveScore()
    {
        String message = "";
        Stage stage = new Stage();
        HBox hBox = new HBox();
        Scene scene = new Scene(hBox, 250, 100);
        Button button1 = new Button("1");
        button1.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Process Result");
            alert.setContentText(ProductManager.giveScore(1));
            stage.close();

            alert.showAndWait();
        });
        Button button2 = new Button("2");
        button2.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Process Result");
            alert.setContentText(ProductManager.giveScore(2));
            stage.close();

            alert.showAndWait();
        });
        Button button3 = new Button("3");
        button3.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Process Result");
            alert.setContentText(ProductManager.giveScore(3));
            stage.close();

            alert.showAndWait();
        });
        Button button4 = new Button("4");
        button4.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Process Result");
            alert.setContentText(ProductManager.giveScore(4));
            stage.close();

            alert.showAndWait();
        });
        Button button5 = new Button("5");
        button5.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Process Result");
            alert.setContentText(ProductManager.giveScore(5));
            stage.close();

            alert.showAndWait();
        });
        hBox.getChildren().addAll(button1, button2,button3, button4, button5);

        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void handleCompare(int productID)
    {
        if (Database.getProductByID(productID) == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Process Fail");
            alert.setContentText("There is no product with this ID");

            alert.showAndWait();
            return;
        }
        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane, 600 , 400);
        GridPane product1 = (GridPane) ViewModelsWithGraphic.showFullInfoGraphic(ProductManager.getProduct().getProductId());
        GridPane product2 = (GridPane) ViewModelsWithGraphic.showFullInfoGraphic(productID);
        Button back = new Button("Back");
        back.setOnAction(e -> newWindow.setScene(setScene()));

        GridPane.setConstraints(product1, 0, 0);
        GridPane.setConstraints(product2, 3, 0);
        GridPane.setConstraints(back, 1, 12);

        gridPane.getChildren().addAll(product1, product1, back);

        newWindow.setScene(scene);
    }


}
