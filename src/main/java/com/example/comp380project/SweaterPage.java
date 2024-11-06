package com.example.comp380project;

import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.awt.event.ItemEvent;
import java.io.IOException;
import java.util.List;

public class SweaterPage {

    private Storefront storefront;
    private Cart cart;
    private Stage primaryStage;


    public SweaterPage(Storefront storefront, Cart cart,Stage primaryStage) {
        this.storefront = storefront;
        this.cart = cart;
        this.primaryStage = primaryStage;
    }

    public Scene getSweaterPage() {

        HBox searchBorder = new HBox();
        // Border of Search Bar
        searchBorder.setAlignment(Pos.TOP_CENTER);
        searchBorder.setPadding(new Insets(80, 0, 0, 0)); // Sets position of light blue border
        searchBorder.setStyle("-fx-background-color: #00324b"); // Sets background of search bar to light blue


        HBox searchBar = new HBox();
        searchBar.setAlignment(Pos.TOP_CENTER);
        searchBar.setPadding(new Insets(-52, 0, 0, 0));


        // Search bar
        TextField searchField = new TextField(); // Creates search bar
        searchField.setPrefWidth(500); // Sets width of search bar
        searchField.setStyle("-fx-background-radius: 30"); // Rounds the edges of the search bar
        searchField.setPromptText("Search for an item"); // Sets default text of search bar
        searchField.setOnAction(actionEvent -> {
            performSearch(searchField.getText());
        });


        // Search Button
        Button searchButton = new Button("Search"); // Creates search button
        searchButton.setCursor(Cursor.HAND);
        searchBar.setSpacing(10); // Sets the space between the search bar and button
        searchButton.setOnAction(actionEvent -> {
            performSearch(searchField.getText());
        });
        searchBar.getChildren().addAll(searchField, searchButton);


        VBox searchHolder = new VBox();
        searchHolder.setAlignment(Pos.TOP_CENTER);
        searchHolder.getChildren().addAll(searchBorder, searchBar);

        HBox SweaterHolder = new HBox(200);

        Item black_sweater = ItemFileReader.retrieveItem(7);
        VBox BlackSweaterBox = VBoxFactory.createItemBox(black_sweater,cart);

        Item grey_sweater = ItemFileReader.retrieveItem(8);
        VBox GreySweaterBox = VBoxFactory.createItemBox(grey_sweater,cart);

        Item green_sweater = ItemFileReader.retrieveItem(9);
        VBox GreenSweaterBox = VBoxFactory.createItemBox(green_sweater,cart);

        SweaterHolder.setAlignment(Pos.CENTER);
        SweaterHolder.getChildren().addAll(BlackSweaterBox, GreySweaterBox, GreenSweaterBox);



        BorderPane topSection = new BorderPane();

        // Back to Home Button
        Button backButton = new Button("Back to Home");
        backButton.setCursor(Cursor.HAND);
        topSection.setLeft(backButton);
        BorderPane.setMargin(backButton, new Insets(30, 0, 0, 25));
        backButton.setOnAction(e -> {
            returnToStorefront();
        });


        // Logo
        Image logo = new Image(getClass().getResourceAsStream("/AJAD Edited Logo.png"));
        ImageView AJADlogo = new ImageView(logo);
        AJADlogo.setFitHeight(100);
        AJADlogo.setFitWidth(100);
        topSection.setCenter(AJADlogo);
        BorderPane.setMargin(AJADlogo, new Insets(-10, 0, 0, 0));
        AJADlogo.setCursor(Cursor.HAND);
        AJADlogo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                returnToStorefront();
            }
        });

        // Cart Button
        Image cartImage = new Image(getClass().getResourceAsStream("/CartIcon.jpg"));
        ImageView cartImageView = new ImageView(cartImage);
        cartImageView.setFitWidth(30);
        cartImageView.setFitHeight(30);


        Button cartButton = new Button("Cart");
        cartButton.setStyle("-fx-background-color: white;");
        cartButton.setGraphic(cartImageView);
        cartButton.setCursor(Cursor.HAND);
        cartButton.setOnAction(event ->{
            goToCartPage();
        });
        topSection.setRight(cartButton);
        BorderPane.setMargin(cartButton, new Insets(22, 30, 0, 0));



        BorderPane boxHolder = new BorderPane(); // holder for all VBoxes and HBoxes
        boxHolder.setStyle("-fx-background-color: white"); //Sets the background of the page to white


        boxHolder.setTop(new VBox(topSection, searchHolder));

        boxHolder.setCenter(SweaterHolder);

        return new Scene(boxHolder);

    }




    private void returnToStorefront() {
        storefront.createStoreFront();
        primaryStage.setScene(storefront.getScene());
    }


    private void goToCartPage() {
        CartPage cartPage = new CartPage(cart,storefront);
        primaryStage.setScene(cartPage.getCartScene(primaryStage));
    }

    public void performSearch(String query) {
        if(query.isEmpty()){
            return;
        }
        List<Item> queryList = SearchController.search(query);
        if(queryList.isEmpty()){
            returnToStorefront();
        }
        else {
            SearchPage searchPage = new SearchPage(storefront, cart, queryList, primaryStage);
            primaryStage.setScene(searchPage.getSearchPage());
        }
    }
}

