package com.example.comp380project;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.List;

/**
 * Class that creates and holds GUI for ItemPage which displays Items
 */
public class ItemPage {

    private Storefront storefront;
    private Cart cart;
    private Stage primaryStage;

    /**
     * Constructor that holds contents included in ItemPage
     * @param storefront the storefront that is associated with the item page
     * @param cart
     * @param primaryStage
     */
    public ItemPage(Storefront storefront, Cart cart, Stage primaryStage){
        this.storefront = storefront;
        this.cart = cart;
        this.primaryStage = primaryStage;
    }

    /**
     * Creates interactable GUI interface that displays Items
     *
     * The page includes:
     * - A top section with a search functionality from SearchBoxFactory and navigation functionality from TopBoxFactory
     * - A center section that displays the particular items on the page
     *
     * @param type the specific category of items to be displayed on the page
     * @return Scene a Scene that displays the Item Page
     */
    public Scene getItemPage(String type){
        TopBoxFactory topBoxFactory = new TopBoxFactory(storefront, cart, primaryStage);
        SearchBoxFactory searchBoxFactory = new SearchBoxFactory(storefront, cart, primaryStage);

        HBox ItemHolder = new HBox(200); //Horizontal Box that holds all T-Shirts
        ItemHolder.setAlignment(Pos.CENTER);

        List<Item> items = ItemFileReader.retrieveAllItems();
        for (Item item: items){
            if (item.getCategory().equalsIgnoreCase(type)){
                VBox itemBox = ItemBoxFactory.createItemBox(item,cart);
                ItemHolder.getChildren().add(itemBox);
            }
        }

        BorderPane boxHolder = new BorderPane(); // holder for all VBoxes and HBoxes
        boxHolder.setStyle("-fx-background-color: white"); //Sets the background of the page to white
        boxHolder.setTop(new VBox(topBoxFactory.getTopSection(), searchBoxFactory.getSearchBox()));
        boxHolder.setCenter(ItemHolder);

        return new Scene(boxHolder);
    }
}
