package com.example.comp380project;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.List;


/**
 * Class that creates the search bar near the top of the GUI
 */
public class SearchBoxFactory {


    private Storefront storefront;
    private Cart cart;
    private Stage primaryStage;


    /**
     * Default constructor for SearchBoxFactory that creates the search bar
     * @param storefront the storefront where the search bar is used
     * @param cart the cart associated with the storefront
     * @param primaryStage the primary stage that displays ths search bar and its results when searching for an item
     */
    public SearchBoxFactory(Storefront storefront, Cart cart, Stage primaryStage) {
        this.storefront = storefront;
        this.cart = cart;
        this.primaryStage = primaryStage;
    }


    /**
     * Creates a VBox called "searchHolder" that holds all elements of the search bar
     *
     * searchHolder includes:
     * - "searchBorder" Hbox that creates a dark blue border around the search bar
     * - "searchBar" HBox that creates the search bar and button
     * - "searchHolder" VBox that holds "searchBorder" and "searchBar"
     *
     * @return searchHolder and its contents
     */
    public VBox getSearchBox() {
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




        return searchHolder;
    }




    /**
     * Executes and handles the search functionlity for an item based on a query
     * @param query the query the user enters into the search bar
     */
    public void performSearch(String query) {
        if(query.isEmpty()){
            return;
        }
        List<Item> queryList = SearchController.search(query);
        if(queryList.isEmpty()) {
            InvalidSearchPage invalidSearchPage = new InvalidSearchPage(storefront, cart, primaryStage);
            primaryStage.setScene(invalidSearchPage.getInvalidSearchPage());
        }
        else {
            SearchPage searchPage = new SearchPage(storefront, cart, queryList, primaryStage);
            primaryStage.setScene(searchPage.getSearchPage());
        }
    }


}