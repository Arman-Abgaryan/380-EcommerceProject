package com.example.comp380project;

import javafx.scene.Scene;
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


        BorderPane accountPageLayout = new BorderPane();
        accountPageLayout.setStyle("-fx-background-color: white"); //Sets the background of the page to white

        accountPageLayout.setTop(new VBox(topBoxFactory.getTopSection()));

        return new Scene(accountPageLayout);
    }

}
