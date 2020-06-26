package View.Menu.ProductsMenus;

import Controller.AllProductManager;
import Controller.Database;
import Controller.ProductManager;
import Model.Product.Category;
import Model.Product.Product;
import View.Menu.LoginMenu;
import View.Menu.Menu;
import View.Menu.ProductMenus.ProductMenu;
import View.Menu.ViewModelsWithGraphic;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ProductsMenu extends Menu {
    public ProductsMenu(Menu parentMenu) {
        super("Products Menu", parentMenu);
        super.addToSubMenus(2, new FilteringMenu(this));
        super.addToSubMenus(3, new SortingMenu(this));
        super.addToSubMenus(6, new LoginMenu(this));
    }

    @Override
    public void show() {
        super.setPane();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        ArrayList<Product> allProducts = AllProductManager.showProductArray();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(mainPane, 1000, 600);
        scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
        scrollPane.getStyleClass().add("scroll-pane");
        super.mainPane.getStyleClass().add("main");
        int row = 0;
        int column =0;
        for (Product product : allProducts) {
            gridPane.add(getProductPane(product), column%3, row/3);
            column++;
            row++;
        }
        ChoiceBox<String> sortOption = new ChoiceBox<>();
        sortOption.getStyleClass().add("choicebox.css");
        sortOption.getItems().setAll("Default", "Name", "Price", "Score");
        sortOption.setValue(AllProductManager.getSortedBy());
        sortOption.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            AllProductManager.setSortedBy(newValue);
            show();
        });

        Button filter = new Button("Filter");
        filter.setAlignment(Pos.CENTER);
        filter.setMaxWidth(Double.MAX_VALUE);
        filter.getStyleClass().add("dark-blue");
        filter.setOnAction(e -> {

            Stage newWindow = new Stage();

            handleFiltering(newWindow);

            newWindow.setOnCloseRequest(ee->{
                show();
            });
            newWindow.initModality(Modality.APPLICATION_MODAL);
            newWindow.showAndWait();
        });

        Button categoryShow = new Button("Category");
        categoryShow.setMaxWidth(Double.MAX_VALUE);
        categoryShow.setAlignment(Pos.CENTER);
        categoryShow.getStyleClass().add("dark-blue");
        categoryShow.setOnAction(e -> {

            Stage newWindow = new Stage();

            handleShowCategory(newWindow);

            newWindow.setOnCloseRequest(ee->{
                show();
            });
            newWindow.initModality(Modality.APPLICATION_MODAL);
            newWindow.showAndWait();
        });

        HBox button = new HBox(new Label("Sorted by ") , sortOption , filter, categoryShow);
        button.setSpacing(10);
        BorderPane borderPane = new BorderPane();
        gridPane.setAlignment(Pos.CENTER);
        borderPane.setCenter(gridPane);
        gridPane.setHgap(100);
        gridPane.setVgap(50);

        scrollPane.setContent(borderPane);

        VBox vBox = new VBox(button, scrollPane);
        vBox.setSpacing(15);

        mainPane.setCenter(vBox);
        window.setScene(scene);
        window.show();
    }

    private Pane getProductPane(Product product) {
        GridPane gridPane = new GridPane();
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
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        Button button = new Button("show");
        button.setAlignment(Pos.CENTER);
        button.setMaxWidth(Double.MAX_VALUE);
        button.getStyleClass().add("dark-blue");
        button.setOnAction(e -> {
            ProductManager.setProduct(product);
            new ProductMenu(this).show();
        });
        HBox hBox = new HBox(imageView);
        if (product.doesHaveOff()) {
            try {
                Image offImage = new Image(new FileInputStream("src\\resource\\ProductImages\\Off.png"));
                ImageView offImageView = new ImageView(offImage);
                hBox.getChildren().addAll(offImageView);
                hBox.setSpacing(-30);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        Label label = new Label(product.getName());
        label.setFont(Font.font(20));
        Pane score = ViewModelsWithGraphic.getScoreWithStar(product);

        GridPane.setConstraints(hBox, 0, 0);
        GridPane.setConstraints(label, 0, 1);
        GridPane.setConstraints(score, 0, 2);
        GridPane.setConstraints(button, 0, 3);
        GridPane.setHalignment(score, HPos.CENTER);
        GridPane.setHalignment(label, HPos.CENTER);
        GridPane.setHalignment(button, HPos.CENTER);

        gridPane.getChildren().addAll(hBox, label, score, button);
        gridPane.setVgap(10);
        return gridPane;
    }

    private void handleShowCategory(Stage newWindow) {
        ArrayList<Category> allCategories = Database.getAllCategories();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(gridPane ,1000, 600);
        scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
        gridPane.getStyleClass().add("main");
        Label info = new Label("All Categories");
        info.setFont(Font.font(25));
        info.setAlignment(Pos.CENTER);
        GridPane.setConstraints(info, 1, 0);
        gridPane.getChildren().add(info);
        int i = 1;
        for (Category category : allCategories) {
            String text = "";
            Label label = new Label();
            text = text + category.getName();
            label.setText(text);
            label.setFont(Font.font(15));
            Button button = new Button("show");
            button.setMaxWidth(Double.MAX_VALUE);
            button.getStyleClass().add("dark-blue");
            button.setOnAction(e -> {
                handleShowOneCategory(category.getName() , newWindow);
            });
            button.setAlignment(Pos.CENTER);
            GridPane.setConstraints(label, 0, i);
            GridPane.setConstraints(button, 2, i);
            gridPane.getChildren().addAll(label, button);
            i++;
        }


        newWindow.setScene(scene);
    }

    private void handleShowOneCategory(String categoryName, Stage newWindow) {
        Pane pane = ViewModelsWithGraphic.showCategoryGraphic(categoryName);
        ((GridPane)pane).setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane, 1000, 600);
        scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
        pane.getStyleClass().add("main");

        Button back = new Button("Back");
        back.getStyleClass().add("dark-blue");
        back.setAlignment(Pos.CENTER);
        back.setOnAction(e -> {
            handleShowCategory(newWindow);
        });

        GridPane.setConstraints(back, 4, 1);

        pane.getChildren().addAll(back);

        newWindow.setScene(scene);
    }

    private void handleFiltering(Stage newWindow) {
        GridPane gridPane = new GridPane();
        ArrayList<String> filters = AllProductManager.getFilterOptions();
        Label allFilter = new Label("All Filters");
        GridPane.setConstraints(allFilter, 0 , 0);
        gridPane.getChildren().addAll(allFilter);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(gridPane, 450, 450);
        scene.getStylesheets().add(new File("Data/Styles/Buttons.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/textfield.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/backgrounds.css").toURI().toString());
        scene.getStylesheets().add(new File("Data/Styles/choicebox.css").toURI().toString());
        gridPane.getStyleClass().add("main");


        int i=1 ;
        for (String filter : filters) {
            Label label = new Label(filter);
            Button remove = new Button("remove");
            remove.setMaxWidth(Double.MAX_VALUE);
            remove.setAlignment(Pos.CENTER);
            remove.getStyleClass().add("record-sales");
            remove.setOnAction(e -> {
                AllProductManager.removeFilterOption(filter);
                handleFiltering(newWindow);
            });
            GridPane.setConstraints(label , 0, i, 2, 1);
            GridPane.setConstraints(remove, 2, i);
            gridPane.getChildren().addAll(label, remove);
            i++;
        }

        TextField newFilter = new TextField();
        newFilter.getStyleClass().add("textfield.css");
        Button addFilter = new Button("add filter");

        ChoiceBox<String> filterOption = new ChoiceBox<>();
        filterOption.getStyleClass().add("choice-box");

        addFilter.setOnAction(e -> {
            AllProductManager.addFilterOption(filterOption.getValue() + " " + newFilter.getText());
            show();
            handleFiltering(newWindow);
        });
        filterOption.getItems().setAll("Seller Username", "Range Of Price", "Available", "Category Name", "Higher Score Than"
                , "Company Name", "Product Name","Category Feature", "Have Off");
        filterOption.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if (newValue.equalsIgnoreCase("Available") || newValue.equalsIgnoreCase("Have Off")){
                newFilter.setDisable(true);
            }
            else if (newValue.equalsIgnoreCase("Range Of Price")){
                newFilter.setPromptText("Enter lower and higher bound with space");
            }
            else
                newFilter.setPromptText("");
        });

        GridPane.setConstraints(filterOption, 0 , i);
        GridPane.setConstraints(newFilter , 1, i);
        GridPane.setConstraints(addFilter, 2, i);
        gridPane.getChildren().addAll(filterOption, newFilter, addFilter);

        newWindow.setScene(scene);
    }

}
