package com.example.comp380project;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


/**
 * Class that creates the top section of the GUI that contains the back to home button, logo, and cart button
 */
public class TopBoxFactory {
    private Storefront storefront;
    private Cart cart;
    private Stage primaryStage;


    /**
     * Default constructor for creating the top section of the GUI
     * @param storefront the storefront that is associated with the GUI
     * @param cart the cart that is associated with the cart button in the GUI
     * @param primaryStage the window where the GUI is displayed
     */
    public TopBoxFactory(Storefront storefront, Cart cart, Stage primaryStage) {
        this.storefront = storefront;
        this.cart = cart;
        this.primaryStage = primaryStage;
    }


    /**
     * Creates a BorderPane called topSection for the elements at the top of each page in the GUI
     *
     * topSection includes:
     * - "Back to Home" button that returns the user to the Storefront when pressed
     * - The AJAD Logo that also acts as a "Back to Home" button
     * - "Cart" button that navigates the user to their respective cart when clicked
     *
     * @return topSection
     */
    public BorderPane getTopSection() {
        BorderPane topSection = new BorderPane();

        // Back to Home Button
        Button backButton = new Button("Back to Home");
        backButton.setCursor(Cursor.HAND);
        topSection.setLeft(backButton);
        BorderPane.setMargin(backButton, new Insets(30, 0, 0, 25));
        backButton.setOnAction(e -> {
            returnToStorefront();
        });

        // Logo
        Image logo = new Image(getClass().getResourceAsStream("/AJAD Edited Logo.png"));
        ImageView AJADlogo = new ImageView(logo);
        AJADlogo.setFitHeight(100);
        AJADlogo.setFitWidth(100);
        topSection.setCenter(AJADlogo);
        BorderPane.setMargin(AJADlogo, new Insets(-10, 0, 0, 0));
        AJADlogo.setCursor(Cursor.HAND);
        AJADlogo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                returnToStorefront();
            }
        });

        // Cart Button
        Image cartImage = new Image(getClass().getResourceAsStream("/cart.png"));
        ImageView cartImageView = new ImageView(cartImage);
        cartImageView.setFitWidth(30);
        cartImageView.setFitHeight(30);

        Button cartButton = new Button("Cart");
        cartButton.setStyle("-fx-background-color: white;");
        cartButton.setGraphic(cartImageView);
        cartButton.setCursor(Cursor.HAND);
        cartButton.setOnAction(event ->{
            goToCartPage();
        });
        topSection.setRight(cartButton);
        BorderPane.setMargin(cartButton, new Insets(22, 30, 0, 0));

        return topSection;
    }


    /**
     * Method that handles returning the user to the Storefront page
     */
    private void returnToStorefront() {
        storefront.createStoreFront();
        primaryStage.setScene(storefront.getScene());
    }


    /**
     * Method that handles navigating the user to their respective cart
     */
    private void goToCartPage() {
        CartPage cartPage = new CartPage(cart,storefront);
        primaryStage.setScene(cartPage.getCartScene(primaryStage));
    }

}