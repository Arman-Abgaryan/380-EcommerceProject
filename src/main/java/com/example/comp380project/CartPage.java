package com.example.comp380project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Map;

public class CartPage{

    private Cart cart;
    private Storefront storefront;
    private Label totalLabel;

    private static CartPage instance;

    public CartPage(Cart cart, Storefront storefront ){
        this.cart = cart;
        this.storefront = storefront;
    }

    public static CartPage getInstance(Cart cart, Storefront storefront) {
        if (instance == null){
            instance = new CartPage(cart,storefront);
        }
        return instance;
    }

    public Scene getCartScene(Stage primaryStage){
        BorderPane layout = new BorderPane();
        layout.setStyle("-fx-background-color: white"); // Adds White Background
        layout.setPadding(new Insets(20));

        VBox itemList = new VBox();
        itemList.setAlignment(Pos.TOP_LEFT);
        itemList.setSpacing(10);

        //Populate item list
        for (Map.Entry<Item,Integer> entry: cart.getItems().entrySet()){
            Item item = entry.getKey();
            int quantity = entry.getValue();

            //null check for Image
            if (item == null || item.getName()==null || item.getImagePath()== null){
                System.out.print("Null");
                continue;
            }

            //Image View
            Image itemImage = new Image(getClass().getResourceAsStream(item.getImagePath()));
            ImageView imageView = new ImageView(itemImage);
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);


            Label itemLabel = new Label(item.getName() + " x " + quantity + " - $"+ String.format("%.2f", (item.getPrice() * quantity)));
            itemLabel.setFont(Font.font("calibri", FontWeight.BOLD, FontPosture.REGULAR,15));
            itemLabel.setStyle("-fx-padding:5px");

            //Buttons for Increasing
            Button increaseButton = new Button("+");
            increaseButton.setCursor(Cursor.HAND);
            increaseButton.setOnAction(event -> {
                cart.addItem(item);
                itemLabel.setText(item.getName()+ " x " + cart.getItems().get(item) + " - $" + String.format("%.2f", (item.getPrice() * cart.getItems().get(item))));
                updateTotal();
            });

            //Button for Decreasing
            Button decreaseButton = new Button("-");
            decreaseButton.setCursor(Cursor.HAND);
            decreaseButton.setOnAction(event -> {
                Integer currentQuantity = cart.getItems().get(item);

                if (currentQuantity != null){
                    if (currentQuantity > 1){
                        cart.removeItem(item);
                        int newQuantity = cart.getItems().get(item);

                        itemLabel.setText(item.getName()+ " x "+ newQuantity + " - $" + String.format("%.2f", (item.getPrice()*newQuantity)));


                    } else {
                        cart.removeItem(item);
                        itemList.getChildren().remove(itemLabel.getParent());
                    }
                    updateTotal();
                }
            });

            HBox itemBox = new HBox(10);
            itemBox.getChildren().addAll(imageView,itemLabel,increaseButton,decreaseButton);
            itemList.getChildren().add(itemBox);
            }

        totalLabel = new Label ("Total: $" + String.format("%.2f", cart.getTotalAmount()));
        totalLabel.setStyle("-fx-font-weight: bold;");

        // Go Back to HomePage
        Button backButton = new Button("Back to Home");
        backButton.setCursor(Cursor.HAND);
        backButton.setOnAction(event -> {
            storefront.createStoreFront();
            primaryStage.setScene(storefront.getScene());
        });

        layout.setCenter(itemList);
        layout.setBottom(totalLabel);
        layout.setAlignment(totalLabel, Pos.CENTER);
        layout.setTop(backButton);
        layout.setAlignment(backButton,Pos.TOP_CENTER);

        Scene cartScene = new Scene(layout,400,300);
        return cartScene;
        }

    // use to update Total when incrementing and decrementing
    private void updateTotal(){
        totalLabel.setText("Total: $" + String.format("%.2f", cart.getTotalAmount()));
    }
}
