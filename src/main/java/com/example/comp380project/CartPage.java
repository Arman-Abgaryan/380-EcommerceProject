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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Map;

/**
 * Class that holds and displays items in Cart along with their price
 */
public class CartPage{

    private Cart cart;
    private Storefront storefront;
    private Label totalLabel;

    private static CartPage instance;

    /**
     * Constructor for CartPage contents
     * @param cart the cart that contains the items to be displayed on the page
     * @param storefront the storefront that is associated with the cart
     */
    public CartPage(Cart cart, Storefront storefront ){
        this.cart = cart;
        this.storefront = storefront;
    }

//    public static CartPage getInstance(Cart cart, Storefront storefront) {
//        if (instance == null){
//            instance = new CartPage(cart,storefront);
//        }
//        return instance;
//    }


    /**
     * Method that creates GUI interface that displays Cart Contents
     *
     * The GUI includes:
     * - A list of all items in the cart
     * - Buttons to increase or decrease the quantity of each item in the cart
     * - A label at the bottom of the page that displays the total cost of the items in the cart
     * - A "back to home button" that navigates the user back to the Storefront page
     * - A "confirm purchase" button that navigates the user to the receipt page to purchase the items in the cart
     *
     * @param primaryStage the main window that displays the Cart Page
     * @return a Scene that displays the Cart Page
     */
    public Scene getCartScene(Stage primaryStage){
        BorderPane layout = new BorderPane();
        layout.setStyle("-fx-background-color: white"); // Adds White Background
        layout.setPadding(new Insets(20));

        VBox itemList = new VBox();
        itemList.setStyle("-fx-background-color: white"); // Adds White Background
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

            //Show size
            Label itemLabel = new Label(item.getSize()+ " " +item.getName()+" x " + cart.getItems().get(item) + " - $" + String.format("%.2f", (item.getPrice() * cart.getItems().get(item))));
            itemLabel.setFont(Font.font("calibri", FontWeight.BOLD, FontPosture.REGULAR,15));
            itemLabel.setStyle("-fx-padding:5px");


            //Buttons for Increasing
            Button increaseButton = new Button("+");
            increaseButton.setCursor(Cursor.HAND);
            increaseButton.setStyle("-fx-background-color: lightgreen; -fx-font-size: 12px;");
            increaseButton.setOnAction(event -> {
                cart.addItem(item);
                itemLabel.setText(item.getSize()+ " " +item.getName()+" x " + cart.getItems().get(item) + " - $" + String.format("%.2f", (item.getPrice() * cart.getItems().get(item))));
                updateTotal();
            });

            //Button for Decreasing
            Button decreaseButton = new Button("-");
            decreaseButton.setCursor(Cursor.HAND);
            decreaseButton.setStyle("-fx-background-color: salmon; -fx-font-size: 12px;");
            decreaseButton.setOnAction(event -> {
                cart.removeItem(item);// Remove item from the cart (decreases quantity)

                int newQuantity = cart.getItems().getOrDefault(item, 0);
                if (newQuantity > 0) {
                    itemLabel.setText(item.getSize()+ " " +item.getName() + " x " + newQuantity + " - $" + String.format("%.2f", (item.getPrice() * newQuantity)));
                } else {
                    itemList.getChildren().remove(itemLabel.getParent());  // Remove item from UI if quantity reaches 0
                }
                updateTotal();  // Update total amount in the cart
            });




            HBox itemBox = new HBox(10);
            itemBox.getChildren().addAll(imageView,itemLabel,increaseButton,decreaseButton);
            itemList.getChildren().add(itemBox);
            }

        totalLabel = new Label ("Total: $" + String.format("%.2f", cart.getTotalAmount()));
        totalLabel.setStyle("-fx-font-weight: bold;");

        BorderPane topPane = new BorderPane();

        // Go Back to HomePage

        Button backButton = new Button();
        Image homeIcon = new Image(getClass().getResourceAsStream("/Home.png"));
        ImageView homeImageView = new ImageView(homeIcon);
        homeImageView.setFitWidth(30);
        homeImageView.setFitHeight(30);

        backButton.setGraphic(homeImageView);
        backButton.setStyle("-fx-background-color: transparent;");
        backButton.setCursor(Cursor.HAND);

        topPane.setLeft(backButton);
        BorderPane.setMargin(backButton, new Insets(3, 0, 0, -5));
        backButton.setOnAction(e -> {
            storefront.createStoreFront();
            primaryStage.setScene(storefront.getScene());
        });

        // Logo
        Image logo = new Image(getClass().getResourceAsStream("/AJAD Edited Logo.png"));
        ImageView AJADlogo = new ImageView(logo);
        AJADlogo.setFitHeight(100);
        AJADlogo.setFitWidth(100);
        topPane.setCenter(AJADlogo);
        BorderPane.setMargin(AJADlogo, new Insets(-32, -50, 0, 0));
        AJADlogo.setCursor(Cursor.HAND);
        AJADlogo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                storefront.createStoreFront();
                primaryStage.setScene(storefront.getScene());
            }
        });

        //Go to ReceiptPage
        Button receiptButton = new Button("Proceed to Checkout");
        receiptButton.setCursor(Cursor.HAND);
        topPane.setRight(receiptButton);
        receiptButton.setOnAction(event -> {
            CheckoutPage checkoutPage = new CheckoutPage(cart,storefront);
            primaryStage.setScene(checkoutPage.getCheckoutScene(primaryStage));
        });

        Separator separator = new Separator();
        separator.setStyle("-fx-background-color: #cccccc; -fx-pref-height: 1;");

        ScrollPane scroll = new ScrollPane();
        scroll.setStyle("-fx-background-color: white"); // Adds White Background
        scroll.setFitToHeight(true);
        scroll.setFitToWidth(true);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setContent(itemList);


        layout.setCenter(scroll);
        layout.setBottom(totalLabel);
        layout.setAlignment(totalLabel, Pos.CENTER);
        layout.setTop(new VBox(topPane, separator));


        Scene cartScene = new Scene(layout,400,300);
        return cartScene;
        }

    /**
     * Update Total Price when Incrementing quantity of items
     */
    // use to update Total when incrementing and decrementing
    private void updateTotal(){
        totalLabel.setText("Total: $" + String.format("%.2f", cart.getTotalAmount()));
    }
}
