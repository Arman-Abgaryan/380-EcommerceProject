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


public class SearchBoxFactory {

    private Storefront storefront;
    private Cart cart;
    private Stage primaryStage;

    public SearchBoxFactory(Storefront storefront, Cart cart, Stage primaryStage) {
        this.storefront = storefront;
        this.cart = cart;
        this.primaryStage = primaryStage;
    }

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


    public void performSearch(String query) {
        if(query.isEmpty()){
            return;
        }
        List<Item> queryList = SearchController.search(query);
        if(queryList.isEmpty()){
            storefront.createStoreFront();
            primaryStage.setScene(storefront.getScene());        }
        else {
            SearchPage searchPage = new SearchPage(storefront, cart, queryList, primaryStage);
            primaryStage.setScene(searchPage.getSearchPage());
        }
    }


}
