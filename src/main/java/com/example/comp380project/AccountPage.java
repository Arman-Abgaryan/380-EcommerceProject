package com.example.comp380project;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


import static javafx.application.Application.launch;


public class AccountPage {

    Customer customer;
    private Storefront storefront;
    private Stage primaryStage;
    private Cart cart;


    /**
     *
     * @param customer
     * @param storefront
     * @param primaryStage
     */

    public AccountPage(Customer customer, Storefront storefront, Stage primaryStage) {
        this.storefront = storefront;
        this.customer = customer;
        this.primaryStage = primaryStage;
        this.cart = CartFileReader.retrieveCart(customer.getId());
    }

    /**
     *
     * @return
     */

    public Scene getAccountPage(){
        CustomerFileReader.retrieveAllCustomers();
        TopBoxFactory topBoxFactory = new TopBoxFactory(storefront, cart, primaryStage);

        /*BorderPane topSection = new BorderPane();
        // Back to Home Button
        Button backButton = new Button("Back to Home");
        backButton.setCursor(Cursor.HAND);
        topSection.setLeft(backButton);
        BorderPane.setMargin(backButton, new Insets(30, 0, 0, 25));
        backButton.setOnAction(e -> {
            storefront.createStoreFront();
            primaryStage.setScene(storefront.getScene());
        });

        // Logo
        Image logo = new Image(getClass().getResourceAsStream("/AJAD Edited Logo.png"));
        ImageView AJADlogo = new ImageView(logo);
        AJADlogo.setFitHeight(100);
        AJADlogo.setFitWidth(100);
        topSection.setCenter(AJADlogo);
        BorderPane.setMargin(AJADlogo, new Insets(-10, 95, 0, 0));
        AJADlogo.setCursor(Cursor.HAND);
        AJADlogo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                storefront.createStoreFront();
                primaryStage.setScene(storefront.getScene());
            }
        });

        HBox accountNameBorder = new HBox();
        accountNameBorder.setPadding(new Insets(0, 0, 0, 15));
        accountNameBorder.setStyle("-fx-background-color: #e8e8e8; -fx-pref-height: 50px;");
        accountNameBorder.setAlignment(Pos.CENTER_LEFT);

        // Account Name Label
        Label accountName = new Label("Hello " + customer.getUsername() + "!");
        accountName.setFont(Font.font("Verdana", 20));
        accountNameBorder.getChildren().add(accountName);

         */

        BorderPane accountPageLayout = new BorderPane();
        accountPageLayout.setStyle("-fx-background-color: white"); //Sets the background of the page to white

        accountPageLayout.setTop(new VBox(topBoxFactory.getTopSection()));

        return new Scene(accountPageLayout);
    }

}
