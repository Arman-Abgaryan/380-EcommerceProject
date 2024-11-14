package com.example.comp380project;


import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;


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


        TopBoxFactory topBoxFactory = new TopBoxFactory(storefront, cart, primaryStage);
        SearchBoxFactory searchBoxFactory = new SearchBoxFactory(storefront, cart, primaryStage);




        HBox SweaterHolder = new HBox(200);


        Item black_sweater = ItemFileReader.retrieveItem(7);
        VBox BlackSweaterBox = VBoxFactory.createItemBox(black_sweater,cart);


        Item grey_sweater = ItemFileReader.retrieveItem(8);
        VBox GreySweaterBox = VBoxFactory.createItemBox(grey_sweater,cart);


        Item green_sweater = ItemFileReader.retrieveItem(9);
        VBox GreenSweaterBox = VBoxFactory.createItemBox(green_sweater,cart);


        SweaterHolder.setAlignment(Pos.CENTER);
        SweaterHolder.getChildren().addAll(BlackSweaterBox, GreySweaterBox, GreenSweaterBox);




        BorderPane boxHolder = new BorderPane(); // holder for all VBoxes and HBoxes
        boxHolder.setStyle("-fx-background-color: white"); //Sets the background of the page to white


        boxHolder.setTop(new VBox(topBoxFactory.getTopSection(), searchBoxFactory.getSearchBox()));


        boxHolder.setCenter(SweaterHolder);


        return new Scene(boxHolder);


    }


    private void showCart(){
        StringBuilder cartContents = new StringBuilder("Items in Cart:\n");
        cart.getItems().forEach((item, quantity)->
                cartContents.append(item.getName()).append("( ").append(")\n"));
        System.out.println(cartContents.toString());
    }
}


