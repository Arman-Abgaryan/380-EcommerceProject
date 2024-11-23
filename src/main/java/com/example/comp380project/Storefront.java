package com.example.comp380project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Represents the storefront page of the e-commerce application.
 * Provides navigation to various sections like items, cart, and account management.
 */
public class Storefront extends Application {
    Stage window;
    Scene scene;
    Button button;
    Customer customer;
    Cart cart;

    public Storefront(){
        this(null, new Cart());
    }

    /**
     * Constructs a Storefront with the specified customer and cart.
     *
     * @param customer the customer accessing the storefront
     * @param cart the cart associated with the customer
     */
    public Storefront(Customer customer, Cart cart){
        this.customer = customer;
        this.cart = (cart != null) ? cart : new Cart();
    }

    private void intializeCart(){
        if (cart == null){
            cart = new Cart();
        }
    }

    /**
     * Entry point for the JavaFX application.
     * Sets up the main storefront window and initializes the scene.
     *
     * @param StorefrontStage the primary stage for the storefront
     */
    public void start(Stage StorefrontStage) {
        CustomerFileReader.retrieveAllCustomers(); // Added to initialize next available customer ID

        window = StorefrontStage;
        window.setTitle("AJAD Ecommerce");
        StorefrontStage.setWidth(1200);
        StorefrontStage.setHeight(800);

        createStoreFront();
        window.show();
    }

    /**
     * Creates the storefront UI with options for product categories and navigation to the cart.
     *
     * The Storefront UI includes:
     * - The AJAD logo in the center of the page
     * - A choiceBox (dropdown menu) containing the pages the user can navigate to
     * - A "Go" button to navigate the user to the selected page
     * - A VBox called "selection" to hold these elements
     * - A "login" button on the top right of the page that navigates the user to the Login Page
     * - A VBox called "login" to hold the login button
     * - A BorderPane called "storefront" that organizes the layout of the VBoxes.
     */
    public void createStoreFront(){
        intializeCart();

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("T-Shirts", "Pants", "Sweaters","Cart");
        choiceBox.setValue("- Select -");
        choiceBox.setCursor(Cursor.HAND);

        button = new Button("Go");
        button.setCursor(Cursor.HAND);
        button.setOnAction(e -> getChoice(choiceBox));

        VBox selection = new VBox();
        Image logo = new Image(getClass().getResourceAsStream("/AJAD Edited Logo.png"));
        ImageView AJADlogo = new ImageView(logo);
        AJADlogo.setFitHeight(300);
        AJADlogo.setFitWidth(300);

        selection.setPadding(new Insets(0, 0, 0, 0));
        selection.setSpacing(20); //Sets space between drop-down menu and button
        selection.setAlignment(Pos.CENTER);
        if (customer != null) {
            Label welcomeUser = new Label("Welcome " + customer.getFirstName() + "!");
            welcomeUser.setFont(Font.font("Arial", FontWeight.BOLD, 24));
            welcomeUser.setAlignment(Pos.TOP_CENTER);
            selection.getChildren().add(welcomeUser);
        }
        selection.getChildren().addAll(AJADlogo, choiceBox, button);

        VBox login = new VBox();

        Image loginIcon = new Image(getClass().getResourceAsStream("/LoginIcon.jpg"));
        ImageView loginImageView = new ImageView(loginIcon);
        loginImageView.setFitWidth(30);
        loginImageView.setFitHeight(30);


        Button loginButton = new Button("login");
        if (customer != null){
            loginButton.setText(customer.getFirstName());
        }
        loginButton.setStyle("-fx-background-color: white;");
        loginButton.setGraphic(loginImageView);
        loginButton.setCursor(Cursor.HAND);
        loginButton.setOnAction(event ->{
            if (customer == null) {
                Stage loginStage = new Stage();
                LoginPage loginPage = new LoginPage(this);
                loginPage.start(loginStage);
            } else {
                AccountPage accountPage = new AccountPage(customer, this, window);
                window.setScene(accountPage.getAccountPage());
            }
        });

        login.setAlignment(Pos.TOP_RIGHT);
        login.getChildren().addAll(loginButton);

        BorderPane storefront = new BorderPane();
        storefront.setStyle("-fx-background-color: white");
        storefront.setCenter(selection);
        storefront.setTop(login);

        scene = new Scene(storefront);
        window.setScene(scene);
    }

    /**
     * Refreshes the storefront UI with updated customer and cart data.
     *
     * @param customer the updated customer
     * @param cart the updated cart
     */
    public void refreshUI(Customer customer, Cart cart) {
        this.customer = customer;
        if (cart != null){
            this.cart = cart;
        } else {
            this.cart = new Cart();
        }
        createStoreFront();
    }

    /**
     * Handles navigation based on the selected choice in the dropdown menu.
     *
     * @param choiceBox the dropdown menu for selecting options
     */
    private void getChoice(ChoiceBox<String> choiceBox) {
        String choice = choiceBox.getValue().toLowerCase();
        if (choice.equals("cart")){
            CartPage cartPage = new CartPage(cart != null ? cart : new Cart(), this);
            Scene cartScene = cartPage.getCartScene(window);
            window.setScene(cartScene);
        }
        else {
            ItemPage itemPage = new ItemPage(this, cart != null ? cart : new Cart(), window);
            window.setScene(itemPage.getItemPage(choice));
        }
    }

    /**
     * Returns the current scene of the storefront.
     *
     * @return the current scene
     */
    public Scene getScene(){
        return scene;
    }

    /**
     * Launches the application.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}
