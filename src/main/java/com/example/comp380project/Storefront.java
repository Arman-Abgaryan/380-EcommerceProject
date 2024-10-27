package com.example.comp380project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Storefront extends Application {
    Stage window;
    Scene scene;
    Button button;
    Cart cart = new Cart();

    @Override
    public void start(Stage StorefrontStage) {
        window = StorefrontStage;
        window.setTitle("AJAD Ecommerce");
        StorefrontStage.setWidth(1200);
        StorefrontStage.setHeight(800);

        createStoreFront();

        window.show();

    }

    public void createStoreFront(){
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("T-Shirts", "Pants", "Shoes","Cart");
        choiceBox.setValue("- Select -");

        button = new Button("Go");
        button.setOnAction(e -> getChoice(choiceBox));

        VBox selection = new VBox();
        selection.setStyle("-fx-background-color: white");
        Image logo = new Image(getClass().getResourceAsStream("/AJAD Edited Logo.png"));
        ImageView AJADlogo = new ImageView(logo);
        AJADlogo.setFitHeight(300);
        AJADlogo.setFitWidth(300);

        selection.setPadding(new Insets(0, 0, 0, 0));
        selection.setSpacing(20); //Sets space between drop-down menu and button
        selection.setAlignment(Pos.CENTER);
        selection.getChildren().addAll(AJADlogo, choiceBox, button);

        scene = new Scene(selection);
        window.setScene(scene);
    }

    private void getChoice(ChoiceBox<String> choiceBox) {
        String choice = choiceBox.getValue();

        if (choice.equals("T-Shirts")) {
            TShirtPage tShirtPage = new TShirtPage(this,cart,window);
            Scene blankShirtScene = tShirtPage.getTShirtPage();
            window.setScene(blankShirtScene);
        } else if (choice.equals("Pants")) {
            System.out.println("Navigating to: " + choice);
        } else if (choice.equals("Shoes")) {
            System.out.println("Navigating to: " + choice);
        } else if  (choice.equals("Cart")){
            CartPage cartPage = new CartPage(cart,this);
            Scene cartScene = cartPage.getCartScene(window);
            window.setScene(cartScene);
        }
    }

    public static Storefront createStorefront(){
        return new Storefront();
    }

    public Scene getScene(){
        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
