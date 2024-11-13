package com.example.comp380project;


import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.util.List;


public class TopBoxFactory {
    private Storefront storefront;
    private Cart cart;
    private Stage primaryStage;


    public TopBoxFactory(Storefront storefront, Cart cart, Stage primaryStage) {
        this.storefront = storefront;
        this.cart = cart;
        this.primaryStage = primaryStage;
    }


    public BorderPane getTopSection() {
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
        Image cartImage = new Image(getClass().getResourceAsStream("/cart.png"));
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


        return topSection;
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
