package com.example.comp380project;


import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.awt.event.ItemEvent;
import java.io.IOException;

public class PantsPage {

    private Storefront storefront;
    private Cart cart;
    private Stage primaryStage;


    public PantsPage(Storefront storefront, Cart cart,Stage primaryStage){
        this.storefront = storefront;
        this.cart = cart;
        this.primaryStage = primaryStage;

    }

    public Scene getPantsPage() {
        HBox pageLabel = new HBox(); // HBox for Label
        pageLabel.setPadding(new Insets(0, 0, 0, 0));
        Label label = new Label("T-Shirts");
        label.setFont(Font.font("verdana", FontWeight.BOLD, 20));
        pageLabel.setAlignment(Pos.TOP_LEFT);
        pageLabel.getChildren().addAll(label);

        HBox logoHolder = new HBox();
        logoHolder.setAlignment(Pos.TOP_CENTER);
        logoHolder.setPadding(new Insets(-40, 0, 0, 0)); //Adjusts the positioning of the logo to be centered in top center

        // Image of Logo
        Image logo = new Image(getClass().getResourceAsStream("/AJAD Edited Logo.png"));
        ImageView AJADlogo = new ImageView(logo);
        AJADlogo.setFitHeight(100);
        AJADlogo.setFitWidth(100);
        AJADlogo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("Logo clicked");
                Storefront storefront = new Storefront();
                storefront.start(primaryStage);


            }
        });

        logoHolder.getChildren().addAll(AJADlogo);


        HBox viewCart = new HBox();
        viewCart.setAlignment(Pos.TOP_RIGHT);
        viewCart.setPadding(new Insets(-70, 95, 0, 0));

        Image cartImage = new Image(getClass().getResourceAsStream("/cart.png"));
        ImageView cartImageView = new ImageView(cartImage);
        cartImageView.setFitWidth(30);
        cartImageView.setFitHeight(30);


        Button cartButton = new Button("Cart");
        cartButton.setStyle("-fx-background-color: #12A822;");
        cartButton.setOnAction(event ->{
            CartPage cartPage = new CartPage(cart,storefront);
            primaryStage.setScene(cartPage.getCartScene(primaryStage));
        });
        viewCart.getChildren().addAll(cartButton,cartImageView);



        VBox topHolder = new VBox(); // Vertical Box holder for pageLabel and logoHolder
        topHolder.setAlignment(Pos.TOP_CENTER);
        topHolder.getChildren().addAll(pageLabel, logoHolder, viewCart);

        HBox PantsHolder = new HBox(200); //Horizontal Box that holds all T-Shirts

        // Testing VBoxFactory class
        Item blue_jeans = ItemFileReader.retrieveItem(5);
        VBox BlueJeansBox = VBoxFactory.createItemBox("/Blue Jeans.jpg", blue_jeans,cart);

        Item blk_chinos = ItemFileReader.retrieveItem(6);
        VBox BlackChinosBox = VBoxFactory.createItemBox("/Black Chinos.jpg", blk_chinos,cart);

        Item brown_pants = ItemFileReader.retrieveItem(7);
        VBox BrownPantsBox = VBoxFactory.createItemBox("/Brown Pants.jpg", brown_pants,cart);


        PantsHolder.setAlignment(Pos.CENTER);
        PantsHolder.getChildren().addAll(BlueJeansBox, BlackChinosBox, BrownPantsBox);

        BorderPane boxHolder = new BorderPane(); // holder for all VBoxes and HBoxes
        boxHolder.setStyle("-fx-background-color: white"); //Sets the background of the page to white

        boxHolder.setTop(topHolder);
        BorderPane.setAlignment(topHolder, Pos.TOP_LEFT);

        boxHolder.setCenter(PantsHolder);
        BorderPane.setAlignment(PantsHolder, Pos.CENTER);

        return new Scene(boxHolder);
    }

    private void showCart(){
        StringBuilder cartContents = new StringBuilder("Items in Cart:\n");
        cart.getItems().forEach((item, quantity)->
                cartContents.append(item.getName()).append("( ").append(")\n"));
        System.out.println(cartContents.toString());
    }


}

