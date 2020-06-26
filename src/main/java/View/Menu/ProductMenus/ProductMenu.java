package View.Menu.ProductMenus;

import Controller.AccountManager;
import Controller.BuyerManager;
import Controller.Database;
import Controller.ProductManager;
import Model.Account.BuyerAccount;
import View.Menu.Menu;
import View.Menu.ViewModelsWithGraphic;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
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
        GridPane gridPane = new GridPane();
        Pane pane = ViewModelsWithGraphic.showProductFullInfoGraphic(ProductManager.getProduct().getProductId());
        gridPane.setAlignment(Pos.CENTER);
        GridPane main = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(20);
        main.setHgap(25);
        Scene scene = new Scene(main, 750, 500);
        scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
        main.getStyleClass().add("admin-popup");

        Button addToCart = new Button("Add To Cart");
        addToCart.getStyleClass().add("dark-blue");
        addToCart.setMaxWidth(Double.MAX_VALUE);
        addToCart.setOnAction(e -> handleAddToCart(ProductManager.getProduct().getProductId()));
        Button giveScore = new Button("Give Score");
        giveScore.getStyleClass().add("dark-blue");
        giveScore.setMaxWidth(Double.MAX_VALUE);
        giveScore.setOnAction(e -> handleGiveScore());
        Button comment = new Button("Comment");
        comment.getStyleClass().add("dark-blue");
        comment.setMaxWidth(Double.MAX_VALUE);
        comment.setOnAction(e -> handleComment());
        TextField productID = new TextField();
        productID.getStyleClass().add("textfield.css");
        productID.setPromptText("productID to compare");
        Button compare = new Button("Compare");
        compare.getStyleClass().add("dark-blue");
        compare.setMaxWidth(Double.MAX_VALUE);
        compare.setOnAction(e -> {
            try {
                System.out.println(productID.getText());
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

        GridPane.setConstraints(addToCart, 0, 0);
        GridPane.setConstraints(giveScore, 0, 1);
        GridPane.setConstraints(comment, 0,2);
        GridPane.setConstraints(productID, 1, 0);
        GridPane.setConstraints(compare, 1, 1);

        gridPane.getChildren().addAll(addToCart, giveScore, comment, productID, compare);

        GridPane.setConstraints(pane, 1, 0);
        GridPane.setConstraints(gridPane, 4, 0);
        GridPane.setHalignment(pane, HPos.CENTER);


        main.getChildren().addAll(pane, gridPane);

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
        if (!(AccountManager.getLoggedInAccount() instanceof BuyerAccount))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Process Fail");
            alert.setContentText("You have to login as a buyer");

            alert.showAndWait();
            return;
        }
        String res = BuyerManager.addProductToCart(Objects.requireNonNull(Database.getProductByID(productID)));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Process Result");
        alert.setContentText(res);

        alert.showAndWait();
    }

    public void handleComment()
    {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(20);
        gridPane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(gridPane, 600, 400);
        scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
        gridPane.getStyleClass().add("admin-popup");
        TextField title = new TextField();
        title.setPromptText("Title");
        title.getStyleClass().add("text-field");
        TextField content = new TextField();
        content.setPromptText("Content");
        content.getStyleClass().add("text-field");
        Label status = new Label();
        Button comment = new Button("Comment");
        comment.getStyleClass().add("dark-blue");
        comment.setOnAction(e -> {
            if (AccountManager.getLoggedInAccount() == null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Process Fail");
                alert.setContentText("You have to login first");

                alert.showAndWait();
                return;
            }
            status.setText(ProductManager.giveComment(title.getText(), content.getText()));
        });
        Button back = new Button("Back");
        back.getStyleClass().add("dark-blue");
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

        newWindow.setScene(scene);
    }

    public void handleGiveScore()
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
        try {
            Stage stage = new Stage();
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            Scene scene = new Scene(hBox, 600, 200);
            scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
            scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
            hBox.getStyleClass().add("admin-popup");
            Button button1 = new Button();
            File file1 = new File("Data" + File.separator + "Styles" + File.separator + "images" + File.separator + "score1.png");
            FileInputStream fileInputStream1 = new FileInputStream(file1);
            Image image1 = new Image(fileInputStream1);
            ImageView imageView1 = new ImageView(image1);
            imageView1.setFitWidth(70);
            imageView1.setFitHeight(70);
            button1.setGraphic(imageView1);
            button1.getStyleClass().add("dark-blue");
            button1.setOnAction(e -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Process Result");
                alert.setContentText(ProductManager.giveScore(1));
                stage.close();

                alert.showAndWait();
            });
            Button button2 = new Button();
            File file2 = new File("Data" + File.separator + "Styles" + File.separator + "images" + File.separator + "score2.png");
            FileInputStream fileInputStream2 = new FileInputStream(file2);
            Image image2 = new Image(fileInputStream2);
            ImageView imageView2 = new ImageView(image2);
            imageView2.setFitWidth(70);
            imageView2.setFitHeight(70);
            button2.setGraphic(imageView2);
            button2.getStyleClass().add("dark-blue");
            button2.setOnAction(e -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Process Result");
                alert.setContentText(ProductManager.giveScore(2));
                stage.close();

                alert.showAndWait();
            });
            Button button3 = new Button();
            File file3 = new File("Data" + File.separator + "Styles" + File.separator + "images" + File.separator + "score3.png");
            FileInputStream fileInputStream3 = new FileInputStream(file3);
            Image image3 = new Image(fileInputStream3);
            ImageView imageView3 = new ImageView(image3);
            imageView3.setFitWidth(70);
            imageView3.setFitHeight(70);
            button3.setGraphic(imageView3);
            button3.getStyleClass().add("dark-blue");
            button3.setOnAction(e -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Process Result");
                alert.setContentText(ProductManager.giveScore(3));
                stage.close();

                alert.showAndWait();
            });
            Button button4 = new Button();
            File file4 = new File("Data" + File.separator + "Styles" + File.separator + "images" + File.separator + "score4.png");
            FileInputStream fileInputStream4 = new FileInputStream(file4);
            Image image4 = new Image(fileInputStream4);
            ImageView imageView4 = new ImageView(image4);
            imageView4.setFitWidth(70);
            imageView4.setFitHeight(70);
            button4.setGraphic(imageView4);
            button4.getStyleClass().add("dark-blue");
            button4.setOnAction(e -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Process Result");
                alert.setContentText(ProductManager.giveScore(4));
                stage.close();

                alert.showAndWait();
            });
            Button button5 = new Button();
            File file5 = new File("Data" + File.separator + "Styles" + File.separator + "images" + File.separator + "score5.png");
            FileInputStream fileInputStream5 = new FileInputStream(file5);
            Image image5 = new Image(fileInputStream5);
            ImageView imageView5 = new ImageView(image5);
            imageView5.setFitWidth(70);
            imageView5.setFitHeight(70);
            button5.setGraphic(imageView5);
            button5.getStyleClass().add("dark-blue");
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
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
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
        gridPane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(gridPane, 600 , 400);
        scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
        gridPane.getStyleClass().add("admin-popup");
        GridPane product1 = (GridPane) ViewModelsWithGraphic.showProductFullInfoGraphic(ProductManager.getProduct().getProductId());
        GridPane product2 = (GridPane) ViewModelsWithGraphic.showProductFullInfoGraphic(productID);
        Button back = new Button("Back");
        back.getStyleClass().add("dark-blue");
        back.setOnAction(e -> newWindow.setScene(setScene()));

        GridPane.setConstraints(product1, 0, 0);
        GridPane.setConstraints(product2, 3, 0);
        GridPane.setConstraints(back, 1, 12);
        GridPane.setHalignment(product1, HPos.CENTER);
        GridPane.setHalignment(product2, HPos.CENTER);
        GridPane.setHalignment(back, HPos.CENTER);

        gridPane.getChildren().addAll(product1, product2, back);

        newWindow.setScene(scene);
    }


}
