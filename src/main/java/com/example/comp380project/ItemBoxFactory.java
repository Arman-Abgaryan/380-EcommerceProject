package com.example.comp380project;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.util.Duration;

/**
 * Factory class used to create multiple vboxes for Item objects
 */
public class ItemBoxFactory {

    /**
     * Method that creates a vbox called "itemBox" for an item
     *
     * itemBox includes:
     * - The image of the item called pulled 'imagePath' in the Items.CSV file
     * - A label that displays the name of the item
     * - A label that displays the price of the item
     * - A choiceBox (dropdown menu) containing the sizes for the item that a user can choose from
     * - A label that displays the quantity of a specific item added to the cart
     * - An "add to cart" button adds the particular item to the cart
     *
     * @param item the specific item being displayed on the page
     * @param cart the cart to that item can be added to
     *
     * @return vbox with corresponding item attributes
     */
    public static VBox createItemBox(Item item, Cart cart) {

        VBox itemBox = new VBox();
        itemBox.setAlignment(Pos.CENTER);
        itemBox.setSpacing(10);

        Image itemImage = new Image(ItemBoxFactory.class.getResourceAsStream(item.getImagePath()));
        ImageView imageView = new ImageView(itemImage);
        imageView.setFitHeight(250);
        imageView.setFitWidth(250);

        Label itemName = new Label(item.getName());
        Label itemPrice = new Label("$" + String.valueOf(item.getPrice()));

       //Select Size
        ChoiceBox<String> sizeChoiceBox = new ChoiceBox<>();
        sizeChoiceBox.getItems().add("Select a Size");
        sizeChoiceBox.setCursor(Cursor.HAND);
        String[] sizes = item.getSizes();
        sizeChoiceBox.getItems().addAll(sizes);
        sizeChoiceBox.setValue("Select a Size");

        Label countLabel = new Label("Added 0");
        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.setCursor(Cursor.HAND);
        int [] addCount = {0};

        addToCartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String selectedSize = sizeChoiceBox.getValue();
                if (!"Select a Size".equals(selectedSize)) {
                    Item itemwithSize = new Item(item.getId(), item.getName(), item.getPrice(), item.getStock(), item.getImagePath(), selectedSize);
                    cart.addItem(itemwithSize);
                    addCount[0]++;
                    countLabel.setText("Items: " + addCount[0]);

                    addToCartButton.setText("Added to Cart!");
                    PauseTransition delay = new PauseTransition(Duration.seconds(2));
                    delay.setOnFinished(e -> addToCartButton.setText("Add to cart"));
                    delay.play();
                    System.out.println(selectedSize + " " + item.getName() + " added to the cart!");

                } else{
                    System.out.println("Please select a size.");
                }

            }
        });

        itemBox.getChildren().addAll(imageView, itemName, itemPrice, sizeChoiceBox, addToCartButton);

        return itemBox;
    }
}