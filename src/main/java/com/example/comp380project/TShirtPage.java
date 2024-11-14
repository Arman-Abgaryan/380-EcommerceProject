package com.example.comp380project;
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
import javafx.stage.Stage;




import java.awt.event.ItemEvent;
import java.io.IOException;
import java.util.List;




public class TShirtPage {


    private Storefront storefront;
    private Cart cart;
    private Stage primaryStage;


    public TShirtPage(Storefront storefront, Cart cart,Stage primaryStage){
        this.storefront = storefront;
        this.cart = cart;
        this.primaryStage = primaryStage;
    }


    public Scene getTShirtPage() {


        TopBoxFactory topBoxFactory = new TopBoxFactory(storefront, cart, primaryStage);
        SearchBoxFactory searchBoxFactory = new SearchBoxFactory(storefront, cart, primaryStage);




        HBox TShirtHolder = new HBox(200); //Horizontal Box that holds all T-Shirts


        // Testing VBoxFactory class
        Item blk_shirt = ItemFileReader.retrieveItem(1);
        VBox BlackTShirtBox = VBoxFactory.createItemBox( blk_shirt,cart);


        Item wt_shirt = ItemFileReader.retrieveItem(2);
        VBox WhiteTShirtBox = VBoxFactory.createItemBox( wt_shirt,cart);


        Item gray_shirt = ItemFileReader.retrieveItem(3);
        VBox GreyTShirtBox = VBoxFactory.createItemBox( gray_shirt,cart);


        TShirtHolder.setAlignment(Pos.CENTER);
        TShirtHolder.getChildren().addAll(BlackTShirtBox, WhiteTShirtBox, GreyTShirtBox);




        BorderPane boxHolder = new BorderPane(); // holder for all VBoxes and HBoxes
        boxHolder.setStyle("-fx-background-color: white"); //Sets the background of the page to white


        boxHolder.setTop(new VBox(topBoxFactory.getTopSection(), searchBoxFactory.getSearchBox()));


        boxHolder.setCenter(TShirtHolder);


        return new Scene(boxHolder);
    }


    private void showCart() {
        StringBuilder cartContents = new StringBuilder("Items in Cart:\n");
        cart.getItems().forEach((item, quantity)->
                cartContents.append(item.getName()).append("( ").append(")\n"));
        System.out.println(cartContents.toString());
    }
}
