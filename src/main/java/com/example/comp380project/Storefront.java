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

public class Storefront extends Application {
    Stage window;
    Scene scene;
    Button button;
    Customer customer;
    Cart cart;

    public Storefront(){
        this(null, new Cart());
    }

    public Storefront(Customer customer, Cart cart){
        this.customer = customer;
        this.cart = (cart != null) ? cart : new Cart();
    }

    private void intializeCart(){
        if (cart == null){
            cart = new Cart();
        }
    }

    public void start(Stage StorefrontStage) {
        CustomerFileReader.retrieveAllCustomers(); // Added to initialize next available customer ID

        window = StorefrontStage;
        window.setTitle("AJAD Ecommerce");
        StorefrontStage.setWidth(1200);
        StorefrontStage.setHeight(800);

        createStoreFront();
        window.show();
    }

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
            Stage loginStage = new Stage();
            LoginPage loginPage = new LoginPage(this);
            loginPage.start(loginStage);
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

    public void refreshUI(Customer customer, Cart cart) {
        this.customer = customer;
        if (cart != null){
            this.cart = cart;
        } else {
            this.cart = new Cart();
        }
        createStoreFront();
    }

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

    public Scene getScene(){
        return scene;
    }

    public static void main(String[] args) {
        launch();
    }
}
