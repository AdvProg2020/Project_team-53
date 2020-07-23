package Model.Product;

import Controller.Database;
import Model.Product.CommentAndScore.Comment;
import Model.Product.CommentAndScore.Score;
import Model.Product.DiscountAndOff.Off;
import View.Menu.ViewModelsWithGraphic;
import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;

public class Product {
    static int numberOfAllProducts = 1;
    int productId;
    String status;
    String name;
    boolean available;
    int number;
    String description;
    double averageScore;
    String categoryName;
    int price;
    String sellerUsername;
    ArrayList<Score> scores = new ArrayList<>();
    ArrayList<Comment> comments = new ArrayList<>();
    int offId;
    boolean doesHasFile;
    String address;

    public static int getNumberOfAllProducts() {
        return numberOfAllProducts;
    }

    public static void setNumberOfAllProducts(int numberOfAllProducts) {
        Product.numberOfAllProducts = numberOfAllProducts;
    }

    public Product(String status, String name, String sellerUsername, boolean available, int number, String description, String categoryName, int price, boolean doesHasFile, String address) {
        this.status = status;
        this.name = name;
        this.sellerUsername = sellerUsername;
        this.available = available;
        this.number = number;
        this.description = description;
        this.productId = numberOfAllProducts;
        this.categoryName = categoryName;
        this.price = price;
        this.doesHasFile = doesHasFile;
        this.address = address;
        numberOfAllProducts++;
        averageScore = 0;
        this.offId = -1;
    }

    public int getProductId() {
        return productId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOffId(int offId) {
        this.offId = offId;
    }

    public String getStatus() {
        return status;
    }

    public void setCategoryNameAndChangeCategory(String categoryName) {
        if (Database.getCategoryByName(this.getCategoryName()) != null){
            Database.getCategoryByName(this.getCategoryName()).removeProduct(this.getProductId());
        }
        Category category = Database.getCategoryByName(categoryName);
        category.addProduct(this.getProductId());
    }

    public void setCategoryNameWithoutAddToCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getDescription() {
        return description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void giveScore(Score score) {
        double sum = averageScore * scores.size();
        sum += score.getScore();
        scores.add(score);
        averageScore = sum / scores.size();
    }

    public double getAverageScore() {
        return averageScore;
    }

    public String digest() {
        return "   ID= " + productId + '\n' +
                "   name= " + name + '\n' +
                "   sellerUsername= " + sellerUsername + '\n' +
                "   description= " + description + '\n' +
                "   averageScore= " + averageScore + '\n' +
                "   price= " + price + '\n';

    }

    public String showAllInfo() {
        return "productId=" + productId + '\n' +
                "status=" + status + '\n' +
                "name=" + name + '\n' +
                "sellerUsername=" + sellerUsername + '\n' +
                "available=" + available + '\n' +
                "number=" + number + '\n' +
                "description=" + description + '\n' +
                "averageScore=" + averageScore + '\n' +
                "categoryName=" + categoryName + '\n' +
                "price=" + price + '\n';

    }

    public String showComments(){
        StringBuilder res = new StringBuilder();

        for (Comment comment : comments) {
            res.append(comment.showComment() + "\n----------------\n");
        }
        return res.toString();
    }

    public String compareWith(Product secondPro) {
        return "name : " + this.getName() + " ---- " + secondPro.getName() + '\n' +
                "seller : " + this.getSellerUsername() + " ---- " + secondPro.getSellerUsername() + '\n' +
                "number : " + this.getNumber() + " ---- " + secondPro.getNumber() + '\n' +
                "available : " + this.isAvailable() + " ---- " + secondPro.isAvailable() + '\n' +
                "description : " + this.getDescription() + " ---- " + secondPro.getDescription() + '\n' +
                "averageScore : " + this.getAverageScore() + " ---- " + secondPro.getAverageScore() + '\n' +
                "categoryName : " + this.getCategoryName() + " ---- " + secondPro.getCategoryName() + '\n' +
                "price : " + this.getPrice() + " ---- " + secondPro.getPrice() + '\n';

    }

    public boolean isSeller(String username) {
        return sellerUsername.equalsIgnoreCase(username);
    }

    public void addComment(Comment comment) {
        comments.add(comment);

    }

    public boolean doesHaveOff(){
        Off off = Database.getOffById(offId);
        if (off == null)
            return false;
        Date date = new Date();
        if (date.compareTo(off.getEndDate()) > 0 || date.compareTo(off.getStartDate()) < 0)
            return false;
        if (!off.getStatus().equalsIgnoreCase("accepted"))
            return false;
        return true;
    }

    public Off getOff(){
        return Database.getOffById(offId);
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public Pane showProductFullInfoGraphic(){
        Product product = this;

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Image image = null;
        try {
            image = new Image(new FileInputStream("src\\resource\\ProductImages\\" + product.getProductId() + ".png"));
        }catch (Exception e){
            try {
                image = new Image(new FileInputStream("src\\resource\\ProductImages\\notFound.png"));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        ImageView productImage = new ImageView(image);
        productImage.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), productImage);
            scaleTransition.setToX(1.7f);
            scaleTransition.setToY(1.7f);
            scaleTransition.play();
        });
        productImage.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), productImage);
            scaleTransition.setToX(1f);
            scaleTransition.setToY(1f);
            scaleTransition.play();
        });
        productImage.setFitHeight(100);
        productImage.setFitWidth(100);
        productImage.setPreserveRatio(true);
        productImage.setSmooth(true);
        HBox hBox =new HBox();
        VBox vBox = new VBox();

        hBox.getChildren().add(productImage);

        Label name = new Label("Name : " + product.getName());
        Label averageScore = new Label("Average Score : " + product.getAverageScore());
        Pane scoreWithStar = getScoreWithStar();
        Label price = new Label("Price : " + product.getPrice());
        Label category = new Label("Category : " + product.getCategoryName());
        Label status = new Label("Status : " + product.getStatus());
        Label sellerUsername = new Label("Seller : " + product.getSellerUsername());
        Label available = new Label("Available : " + product.isAvailable());
        Label number = new Label("Number : " + product.getNumber());
        Label descriptionTag = new Label("Description : ");
        Label description = new Label(product.getDescription());
        Label hasFile = new Label("HasFile : " + doesHasFile);

        GridPane.setConstraints(name, 0, 1 , 2 , 1);
        GridPane.setConstraints(averageScore, 0, 2 , 2 , 1);
        GridPane.setConstraints(scoreWithStar, 0, 3, 2, 1);
        GridPane.setConstraints(price, 0, 4 , 2 , 1);
        GridPane.setConstraints(category, 0, 5 , 2 , 1);
        GridPane.setConstraints(status, 0, 6 , 2 , 1);
        GridPane.setConstraints(sellerUsername, 0, 7 , 2 , 1);
        GridPane.setConstraints(available, 0, 8 , 2 , 1);
        GridPane.setConstraints(number, 0, 9 , 2 , 1);
        GridPane.setConstraints(descriptionTag, 0, 10 , 2 , 1);
        GridPane.setConstraints(description, 1, 11 , 2 , 3);
        GridPane.setConstraints(hasFile, 0, 15, 2, 1);

        gridPane.getChildren().addAll( name, averageScore, scoreWithStar, price, category, status,sellerUsername,available,number,descriptionTag,description, hasFile);

        if (product.doesHaveOff()){
            try {
                Image offImage = new Image(new FileInputStream("src\\resource\\ProductImages\\Off.png"));
                ImageView offImageView = new ImageView(offImage);
                vBox.getChildren().addAll(offImageView);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Button viewOffButton = new Button("view off");
            Pane pane = ViewModelsWithGraphic.showOffFullInfoGraphic(product.getOff().getOffId());
            ((GridPane)pane).setAlignment(Pos.CENTER);
            gridPane.getChildren().add(viewOffButton);
            viewOffButton.getStyleClass().add("top-button");
            GridPane.setConstraints(viewOffButton, 3, 0);
            viewOffButton.setOnAction(e -> {
                BorderPane newPane = new BorderPane();
                newPane.setCenter(pane);
                Scene scene = new Scene(newPane, 400, 400);
                scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
                newPane.getStyleClass().add("viewOff-Button");
                Stage newWindow = new Stage();
                newWindow.setScene(scene);
                newWindow.showAndWait();
            });
        }

        if(!this.isAvailable() || this.getNumber() <= 0){
            try {
                Image notImage = new Image(new FileInputStream("src\\resource\\ProductImages\\notAvailable.png"));
                ImageView notImageView = new ImageView(notImage);
                notImageView.toFront();
                vBox.getChildren().addAll(notImageView);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        vBox.setSpacing(40);
        hBox.getChildren().addAll(vBox);
        hBox.setSpacing(-20);
        GridPane.setConstraints(hBox, 0 , 0 , 2 , 1);
        gridPane.getChildren().addAll(hBox);

        return gridPane;
    }

    public Pane getScoreWithStar()
    {
        Product product = this;
        int numberOfStars = 0;
        if(product.getAverageScore() >=0 && product.getAverageScore() < 1)
        {
            numberOfStars = 1;
        }
        else if(product.getAverageScore() >=1 && product.getAverageScore() < 2)
        {
            numberOfStars = 2;
        }
        else if(product.getAverageScore() >=2 && product.getAverageScore() < 3)
        {
            numberOfStars = 3;
        }
        else if(product.getAverageScore() >=3 && product.getAverageScore() < 4)
        {
            numberOfStars = 4;
        }
        else if(product.getAverageScore() >=4 && product.getAverageScore() <= 5)
        {
            numberOfStars = 5;
        }
        HBox hBox = new HBox();
        hBox.setSpacing(5);
        for (int i = 0; i < numberOfStars; i++)
        {
            File file1 = new File("Data" + File.separator + "Styles" + File.separator + "images" + File.separator + "star.jpg");
            FileInputStream fileInputStream1 = null;
            try {
                fileInputStream1 = new FileInputStream(file1);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image image1 = new Image(fileInputStream1);
            ImageView imageView1 = new ImageView(image1);
            imageView1.setFitWidth(20);
            imageView1.setFitHeight(20);
            hBox.getChildren().add(imageView1);
        }
        return hBox;
    }

    public ScrollPane showCommentsOfProduct() {
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        ArrayList<Comment> comments = this.comments;
        for (Comment comment : comments) {
            Label label = new Label(comment.showComment());
            vBox.getChildren().add(label);
        }
        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.setPannable(true);
        scrollPane.setPrefSize(400, 200);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        return scrollPane;
    }

    public String getAddressOfProduct()
    {
        return this.address;
    }

    public boolean doesHasFile()
    {
        return this.doesHasFile;
    }
}
