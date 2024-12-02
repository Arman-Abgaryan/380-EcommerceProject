package com.example.comp380project;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


import javafx.scene.control.Label;


public class InvalidSearchPage {
    private Storefront storefront;
    private Cart cart;
    private Stage primaryStage;


    public InvalidSearchPage(Storefront storefront, Cart cart, Stage primaryStage) {
        this.storefront = storefront;
        this.cart = cart;
        this.primaryStage = primaryStage;
    }


    public Scene getInvalidSearchPage() {
        TopBoxFactory topBoxFactory = new TopBoxFactory(storefront, cart, primaryStage);
        SearchBoxFactory searchBoxFactory = new SearchBoxFactory(storefront, cart, primaryStage);


        BorderPane layout = new BorderPane();
        layout.setStyle("-fx-background-color: white");


        Label errorLabel = new Label("Invalid Search: Item not found");
        errorLabel.setAlignment(Pos.CENTER);
        errorLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        errorLabel.setStyle("-fx-text-fill: black");


        layout.setCenter(errorLabel);
        layout.setTop(new VBox(topBoxFactory.getTopSection(), searchBoxFactory.getSearchBox()));


        return new Scene(layout);
    }
}
