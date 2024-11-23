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
     * @param storefront the storefront associated with the Search Page
     * @param cart the cart associated with the Search Page for managing items from the search results
     * @param searchResults list of items returned from a search
     * @param primaryStage the primary stage that displays the Search Page
     */
    public SearchPage(Storefront storefront, Cart cart, List<Item> searchResults, Stage primaryStage) {
        this.storefront = storefront;
        this.cart = cart;
        this.searchResults = searchResults;
        this.primaryStage = primaryStage;
    }

    /**
     * Creates and returns the SearchPage
     *
     * The SearchPage includes:
     - A top section with a search functionality from SearchBoxFactory and navigation functionality from TopBoxFactory
     - A scrollable display of results from the search query
     *
     * @return searchPage with appropriate search results.
     */
    public Scene getSearchPage() {

        TopBoxFactory topBoxFactory = new TopBoxFactory(storefront, cart, primaryStage);
        SearchBoxFactory searchBoxFactory = new SearchBoxFactory(storefront, cart, primaryStage);

        FlowPane ItemHolder = new FlowPane(100,100);
        ItemHolder.setAlignment(Pos.CENTER);
        ItemHolder.setStyle("-fx-background-color: white");

        for (Item item : searchResults) {
            VBox itemBox = ItemBoxFactory.createItemBox(item, cart);
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


