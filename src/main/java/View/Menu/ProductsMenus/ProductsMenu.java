package View.Menu.ProductsMenus;

import Model.Product.Category;
import Model.Product.Product;
import View.Menu.LoginMenu;
import View.Menu.Menu;
import View.Menu.ProductMenus.ProductMenu;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import java.io.IOException;
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
        try{
            super.setPane();
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            scrollPane.setFitToHeight(true);
            scrollPane.setFitToWidth(true);
            dataOutputStream.writeUTF("ShowAllProduct");
            dataOutputStream.flush();
            ArrayList<Product> allProducts = new Gson().fromJson(dataInputStream.readUTF(), new TypeToken<ArrayList<Product>>(){}.getType());
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
            dataOutputStream.writeUTF("GetSortedBy");
            dataOutputStream.flush();
            sortOption.setValue(dataInputStream.readUTF());
            sortOption.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
                try {
                    dataOutputStream.writeUTF("SetSortedBy " + newValue);
                    dataOutputStream.flush();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
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
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
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
            try {
                dataOutputStream.writeUTF("SetProduct " + product.getProductId());
                dataOutputStream.flush();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            new ProductMenu(this).show();
        });
        HBox hBox = new HBox();
        VBox vBox = new VBox();
        hBox.getChildren().addAll(imageView);
        if (product.doesHaveOff()) {
            try {
                Image offImage = new Image(new FileInputStream("src\\resource\\ProductImages\\Off.png"));
                ImageView offImageView = new ImageView(offImage);
                vBox.getChildren().addAll(offImageView);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        Label label = new Label(product.getProductId() + " : " + product.getName());
        label.setFont(Font.font(20));
        Pane score = product.getScoreWithStar();
        Label price = new Label("Price: " + product.getPrice());
        price.setFont(Font.font(15));
        GridPane.setConstraints(hBox, 0, 0);
        GridPane.setConstraints(label, 0, 1);
        GridPane.setConstraints(price, 0, 2);
        GridPane.setConstraints(score, 0, 3);
        GridPane.setConstraints(button, 0, 4);
        GridPane.setHalignment(score, HPos.CENTER);
        GridPane.setHalignment(label, HPos.CENTER);
        GridPane.setHalignment(button, HPos.CENTER);

        if(!product.isAvailable() || product.getNumber() <= 0){
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

        gridPane.getChildren().addAll(hBox, label, price, score, button);
        gridPane.setVgap(10);
        return gridPane;
    }

    private void handleShowCategory(Stage newWindow) {
        try {
            dataOutputStream.writeUTF("AllCategories");
            dataOutputStream.flush();
            ArrayList<Category> allCategories = new Gson().fromJson(dataInputStream.readUTF(), new TypeToken<ArrayList<Category>>(){}.getType());
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
                    handleShowOneCategory(category , newWindow);
                });
                button.setAlignment(Pos.CENTER);
                GridPane.setConstraints(label, 0, i);
                GridPane.setConstraints(button, 2, i);
                gridPane.getChildren().addAll(label, button);
                i++;
            }


            newWindow.setScene(scene);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void handleShowOneCategory(Category category, Stage newWindow) {
        Pane pane = category.showCategoryGraphic();
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
        try {
            GridPane gridPane = new GridPane();
            dataOutputStream.writeUTF("GetFilterOption");
            dataOutputStream.flush();
            //ArrayList<String> filters = AllProductManager.getFilterOptions();
            ArrayList<String> filters = new Gson().fromJson(dataInputStream.readUTF(), new TypeToken<ArrayList<String>>(){}.getType());
            //ArrayList<String> filters2 = AllProductManager.getFilterOptions();

            System.out.println(filters);
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
                    try {
                        dataOutputStream.writeUTF("RemoveFilter " + filter);
                        dataOutputStream.flush();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                    handleFiltering(newWindow);
                    show();
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
                try {
                    dataOutputStream.writeUTF("AddFilter " + filterOption.getValue() + " " + newFilter.getText());
                    dataOutputStream.flush();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                //AllProductManager.addFilterOption(filterOption.getValue() + " " + newFilter.getText());
                handleFiltering(newWindow);
                show();
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
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

}
