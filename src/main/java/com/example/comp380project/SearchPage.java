package com.example.comp380project;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.List;

/**
 * Class that handles the GUI for a search page.
 */
public class SearchPage {

    private Storefront storefront;
    private Cart cart;
    private Stage primaryStage;
    List<Item> searchResults;

    /**
     * Constructor for creating the search page based on search results.
     * @param storefront
     * @param cart
     * @param searchResults
     * @param primaryStage
     */
    public SearchPage(Storefront storefront, Cart cart, List<Item> searchResults, Stage primaryStage) {
        this.storefront = storefront;
        this.cart = cart;
        this.searchResults = searchResults;
        this.primaryStage = primaryStage;
    }

    /**
     * Getter method to retrieve SearchPage
     * @return searchPage with appropriate search results.
     */
    public Scene getSearchPage() {

        TopBoxFactory topBoxFactory = new TopBoxFactory(storefront, cart, primaryStage);
        SearchBoxFactory searchBoxFactory = new SearchBoxFactory(storefront, cart, primaryStage);

        FlowPane ItemHolder = new FlowPane(100,100);
        ItemHolder.setAlignment(Pos.CENTER);
        ItemHolder.setStyle("-fx-background-color: white");

        for (Item item : searchResults) {
            VBox itemBox = VBoxFactory.createItemBox(item, cart);
            ItemHolder.getChildren().add(itemBox);
        }

        ScrollPane scrollPane = new ScrollPane(ItemHolder);
        scrollPane.setPadding(new Insets(10));
        scrollPane.setStyle("-fx-background-color: white");
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        BorderPane boxHolder = new BorderPane();
        boxHolder.setStyle("-fx-background-color: white");
        boxHolder.setTop(new VBox(topBoxFactory.getTopSection(),searchBoxFactory.getSearchBox()));
        boxHolder.setCenter(scrollPane);

        Scene scene = new Scene(boxHolder);
        return scene;
    }
}


