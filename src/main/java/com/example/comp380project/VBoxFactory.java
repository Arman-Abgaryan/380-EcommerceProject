package com.example.comp380project;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.util.Duration;

public class VBoxFactory {

    // Method to create a VBox for an item
    public static VBox createItemBox(String imagePath, Item item, Cart cart) {

        VBox itemBox = new VBox();
        itemBox.setAlignment(Pos.CENTER);
        itemBox.setSpacing(10);

        Image itemImage = new Image(VBoxFactory.class.getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(itemImage);
        imageView.setFitHeight(250);
        imageView.setFitWidth(250);

        Label itemName = new Label(item.getName());
        Label itemPrice = new Label("$" + String.valueOf(item.getPrice()));
        Label countLabel = new Label("Added 0");
        Button addToCartButton = new Button("Add to Cart");
        int [] addCount = {0};

        addToCartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                cart.addItem(item);
                addCount[0]++;
                countLabel.setText("Items: " + addCount[0]);

                addToCartButton.setText("Added to Cart!");
                PauseTransition delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished(e -> addToCartButton.setText("Add to cart"));
                delay.play();
                System.out.println(item.getName() + " added to the cart!");


            }
        });

        itemBox.getChildren().addAll(imageView, itemName, itemPrice,countLabel, addToCartButton);

        return itemBox;
    }
}