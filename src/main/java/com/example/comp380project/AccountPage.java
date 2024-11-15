package com.example.comp380project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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


    public AccountPage(Customer customer, Storefront storefront, Stage primaryStage) {
        this.storefront = storefront;
        this.customer = customer;
        this.primaryStage = primaryStage;
        this.cart = cart;
    }

    public Scene getAccountPage(){
        CustomerFileReader.retrieveAllCustomers();
        TopBoxFactory topBoxFactory = new TopBoxFactory(storefront, cart, primaryStage);

        BorderPane accountPageLayout = new BorderPane();
        accountPageLayout.setStyle("-fx-background-color: white"); //Sets the background of the page to white

        Label accountName = new Label("Hello " + customer.getUsername() + "!");
        accountName.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        BorderPane.setMargin(accountName, new Insets(5, 0, 0, 15));
        accountName.setAlignment(Pos.TOP_CENTER);


        BorderPane boxHolder = new BorderPane(); // holder for all VBoxes and HBoxes
        boxHolder.setStyle("-fx-background-color: white"); //Sets the background of the page to white
        boxHolder.setTop(new VBox(topBoxFactory.getTopSection()));
        boxHolder.setLeft(accountName);

        return new Scene(boxHolder);
    }

}
